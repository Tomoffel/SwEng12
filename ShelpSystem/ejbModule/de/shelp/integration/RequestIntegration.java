package de.shelp.integration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;

import de.shelp.dao.local.ShelpRequestDAOLocal;
import de.shelp.dao.local.ShelpTourDAOLocal;
import de.shelp.dao.local.ShelpUserDAOLocal;
import de.shelp.dto.ReturnCodeResponse;
import de.shelp.dto.request.RequestResponse;
import de.shelp.dto.request.RequestTO;
import de.shelp.dto.request.RequestsResponse;
import de.shelp.entities.Request;
import de.shelp.entities.ShelpSession;
import de.shelp.entities.Tour;
import de.shelp.entities.User;
import de.shelp.entities.WishlistItem;
import de.shelp.enums.ReturnCode;
import de.shelp.exception.SessionNotExistException;
import de.shelp.exception.ShelpException;
import de.shelp.exception.TourNotExistException;
import de.shelp.exception.UserNotExistEcxeption;
import de.shelp.util.RequestDtoAssembler;

@WebService
@WebContext(contextRoot = "/shelp")
@Stateless
public class RequestIntegration {

	private static final Logger LOGGER = Logger
			.getLogger(RequestIntegration.class);

	/**
	 * EJB zur Abfrage von Datensätzen Referenz auf die EJB wird per Dependency
	 * Injection gefüllt.
	 */
	@EJB(beanName = "ShelpUserDAO", beanInterface = ShelpUserDAOLocal.class)
	private ShelpUserDAOLocal daoUser;

	/**
	 * EJB zur Abfrage von Datensätzen Referenz auf die EJB wird per Dependency
	 * Injection gefüllt.
	 */
	@EJB(beanName = "ShelpRequestDAO", beanInterface = ShelpRequestDAOLocal.class)
	private ShelpRequestDAOLocal daoRequest;

	/**
	 * EJB zur Erzeugung von DataTransferObjects
	 */
	@EJB
	private RequestDtoAssembler requestDtoAssembler;

	/**
	 * EJB zur Abfrage von Datensätzen Referenz auf die EJB wird per Dependency
	 * Injection gefüllt.
	 */
	@EJB(beanName = "ShelpTourDAO", beanInterface = ShelpTourDAOLocal.class)
	private ShelpTourDAOLocal tourDao;

	/**
	 * Method to accept a request
	 * 
	 * @param requestId
	 * @param acceptedIds
	 * @param sessionId
	 * @return
	 */
	public ReturnCodeResponse acceptRequest(long requestId,
			List<Integer> acceptedIds, int sessionId) {

		ReturnCodeResponse response = new ReturnCodeResponse();

		try {
			Request request = checkRequest(sessionId, requestId, false);

			List<WishlistItem> wishes = request.getWishes();
			for (WishlistItem wishlistItem : wishes) {
				if (acceptedIds.contains(wishlistItem.getId())) {
					wishlistItem.setChecked(true);
				}
			}
			daoRequest.createRequest(request);

		} catch (ShelpException e) {
			response.setReturnCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		}

		return response;
	}

	/**
	 * Method to create a request
	 * 
	 * @param targetUserId
	 * @param tourId
	 * @param notice
	 * @param sessionId
	 * @param wishes
	 * @return returnCodeResponse
	 * @throws SessionNotExistException 
	 */
	public ReturnCodeResponse createRequest(String targetUserId, long tourId,
			String notice, int sessionId, String... wishes) throws SessionNotExistException
			{

		// create empty response
		ReturnCodeResponse response = new ReturnCodeResponse();

		// check current Session
		// ShelpSession session = daoUser.getSession(sessionId);
		ShelpSession session = checkSession(sessionId);

		// get targetUser
		User targetUser = daoUser.findUserByName(targetUserId);

		try {
			// check parameter
			if (targetUser == null) {
				String message = "TargetUser existiert nicht.";
				LOGGER.info(message);
				throw new UserNotExistEcxeption(ReturnCode.ERROR, message);
			}
			if (targetUser.equals(session.getUser())) {
				String message = "Sich selbst eine Anfrage senden, ergibt doch garkeinen Sinn lieber "
						+ session.getUser();
				LOGGER.info(message);
				throw new ShelpException(ReturnCode.ERROR, message);
			}

			// get current Tour
			Tour tour = tourDao.getTour(tourId);

			if (tour == null) {
				String message = "Fahrt existiert nicht";
				LOGGER.info(message);
				throw new TourNotExistException(message);
			}

			// create request if parameter are ok
			Request request = new Request();
			request.setSourceUser(session.getUser());
			request.setTargetUser(targetUser);
			request.setTour(tour);
			request.setNotice(notice);
			request.setUpdatedOn(new Date());

			// create list for wishlistitems
			List<WishlistItem> wishlistItems = new ArrayList<WishlistItem>();

			for (String string : wishes) {
				WishlistItem item = new WishlistItem();
				item.setText(string);
				item.setChecked(false);
				item.setOwner(request);
				wishlistItems.add(item);
			}

			// TODO transaktion
			// set created wishlist to request
			request.setWishes(wishlistItems);

			// create request
			daoRequest.createRequest(request);

			// update time for tour
			tour.setUpdatedOn(new Date());

			// save tour
			tourDao.saveTour(tour);

			LOGGER.info("Anfrage wurde gestellt.");
		}

		catch (ShelpException e) {
			response.setReturnCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		}

		return response;
	}

	/**
	 * Method to get Request
	 * 
	 * @param requestId
	 * @param sessionId
	 * @return requestResponse
	 */
	public RequestResponse getRequest(long requestId, int sessionId) {

		// create empty response
		RequestResponse response = new RequestResponse();

		try {
			// check request
			Request request = checkRequest(sessionId, requestId, true);

			// transform request
			response.setRequestTO(requestDtoAssembler.makeDTO(request));
			LOGGER.info("Anfrage wurde zurückgegeben.");

		} catch (ShelpException e) {
			response.setReturnCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		}

		return response;
	}

	/**
	 * Method to delete a request
	 * 
	 * @param requestId
	 * @param sessionId
	 * @return ReturnCodeResponse
	 */
	public ReturnCodeResponse deleteRequest(long requestId, int sessionId) {

		// create empty response
		ReturnCodeResponse response = new ReturnCodeResponse();

		try {
			// check request
			Request request = checkRequest(sessionId, requestId, true);

			// delete request
			daoRequest.deleteRequest(request);
			LOGGER.info("Anfrage wurde gelöscht.");

		} catch (ShelpException e) {
			response.setReturnCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		}

		return response;
	}

	/**
	 * Method to get updated request
	 * 
	 * @param sessionId
	 * @param timestamp
	 * @return
	 * @throws SessionNotExistException 
	 */
	public RequestsResponse getUpdatedRequests(int sessionId, long timestamp)
			throws SessionNotExistException {

		// create empty response
		RequestsResponse response = new RequestsResponse();

		// check current session
		ShelpSession session = checkSession(sessionId);

		// get all request of the user
		List<Request> requests = session.getUser().getOwnRequests();

		List<RequestTO> resultList = new ArrayList<RequestTO>();
		// check if tour has updated after timepoint
		for (Request request : requests) {
			if (request.getUpdatedOn().after(new Date(timestamp))) {
				resultList.add(requestDtoAssembler.makeDTO(request));
			}
		}

		response.setRequests(resultList);

		return response;
	}

	/**
	 * Helper-method to check request
	 * 
	 * @param sessionId
	 * @param requestId
	 * @param checkBoth
	 * @return
	 * @throws ShelpException
	 */
	private Request checkRequest(int sessionId, long requestId,
			boolean checkBoth) throws ShelpException {

		// get current Session
		ShelpSession session = checkSession(sessionId);

		// get user from session
		User user = session.getUser();

		// get current request
		Request request = daoRequest.getRequestById(requestId);

		// check request
		if (request == null) {
			LOGGER.info("Anfrage nicht gültig.");
			throw new ShelpException(ReturnCode.ERROR,
					"Anfrage existiert nicht!");
		}

		if ((checkBoth && (request.getTargetUser().equals(user) || request
				.getSourceUser().equals(user)))
				|| !checkBoth
				&& request.getTargetUser().equals(user)) {

			return request;

		} else {
			LOGGER.info("Anfrage nicht erlaubt.");
			throw new ShelpException(ReturnCode.PERMISSION_DENIED,
					"Anfrage nicht erlaubt!");
		}

	}

	/**
	 * Helper-method to check current session
	 * 
	 * @param sessionId
	 * @return
	 * @throws UserNotExistEcxeption
	 * @throws SessionNotExistException 
	 */
	private ShelpSession checkSession(int sessionId)
			throws SessionNotExistException {

		// get current Session
		ShelpSession session = daoUser.getSession(sessionId);

		if (session == null) {
			String message = "Session-Id existiert nicht.";
			LOGGER.info(message);
			throw new SessionNotExistException(message);
		}
		return session;
	}

}
