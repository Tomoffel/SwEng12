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

	public ReturnCodeResponse acceptRequest(long requestId,
			List<Integer> acceptedIds, int sessionId) {
		
		ReturnCodeResponse response = new ReturnCodeResponse();

		try {
			Request request = checkRequest(sessionId, requestId, false);

			List<WishlistItem> wishes = request.getWishes();
			for (WishlistItem wishlistItem : wishes) {
				if(acceptedIds.contains(wishlistItem.getId())) {
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

	public ReturnCodeResponse createRequest(String targetUserId, long tourId,
			List<String> wishes, String notice, int sessionId) {
		ReturnCodeResponse response = new ReturnCodeResponse();


		ShelpSession session = daoUser.getSession(sessionId);
		User targetUser = daoUser.findUserByName(targetUserId);
		try {
			if (targetUser == null) {
				LOGGER.info("TargetUser existiert nicht.");
				throw new UserNotExistEcxeption(ReturnCode.ERROR,
						"TargetUser existiert nicht.");
			}
			if (session == null) {
				LOGGER.info("Session-Id existiert nicht.");
				throw new UserNotExistEcxeption(ReturnCode.ERROR,
						"Session-Id existiert nicht.");
			}
			if (targetUser.equals(session.getUser())) {
				LOGGER.info("Sich selbst eine Anfrage senden ergibt doch keinen Sinn! "
						+ session.getUser());
				throw new ShelpException(ReturnCode.ERROR,
						"Sich selbst eine Anfrage senden ergibt doch keinen Sinn!"
								+ session.getUser());
			}

			Tour tour = tourDao.getTour(tourId);

			if (tour == null) {
				LOGGER.info("Fahrt existiert nicht!");
				throw new TourNotExistException("Fahrt existiert nicht!");
			}
			
			Request request = new Request();
			request.setSourceUser(session.getUser());
			request.setTargetUser(targetUser);
			request.setTour(tour);
			request.setNotice(notice);
			request.setUpdatedOn(new Date());
			
			List<WishlistItem> wishlistItems = new ArrayList<WishlistItem>();
			
			for (String string : wishes) {
				WishlistItem item = new WishlistItem();
				item.setText(string);
				item.setChecked(false);
				item.setOwner(request);
				wishlistItems.add(item);
			}
			
			request.setWishes(wishlistItems);
			daoRequest.createRequest(request);
			tour.setUpdatedOn(new Date());
			tourDao.saveTour(tour);
		}

		catch (ShelpException e) {
			response.setReturnCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		}

		return response;
	}

	public RequestResponse getRequest(long requestId, int sessionId) {

		RequestResponse response = new RequestResponse();

		try {
			Request request = checkRequest(sessionId, requestId, true);

			response.setRequestTO(requestDtoAssembler.makeDTO(request));
			LOGGER.info("Anfrage wurde zurückgegeben.");

		} catch (ShelpException e) {
			response.setReturnCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		}

		return response;
	}

	public ReturnCodeResponse deleteRequest(long requestId, int sessionId) {
		ReturnCodeResponse response = new ReturnCodeResponse();

		try {
			Request request = checkRequest(sessionId, requestId, true);

			daoRequest.deleteRequest(request);
			LOGGER.info("Anfrage wurde gelöscht.");

		} catch (ShelpException e) {
			response.setReturnCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		}

		return response;
	}

	public RequestsResponse getUpdatedRequests(int sessionId, long timestamp) {

		RequestsResponse response = new RequestsResponse();

		//TODO: Session Überprüfung in Helferfunction auslagern
		
		
		
		ShelpSession session = daoUser.getSession(sessionId);
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

	private Request checkRequest(int sessionId, long requestId, boolean checkBoth)
			throws ShelpException {

		ShelpSession session = daoUser.getSession(sessionId);
		if (session == null) {
			LOGGER.info("Session nicht gültig.");
			throw new SessionNotExistException("Session nicht gültig.");
		}

		User user = session.getUser();

		Request request = daoRequest.getRequestById(requestId);

		if (request == null) {
			LOGGER.info("Anfrage nicht gültig.");
			throw new ShelpException(ReturnCode.ERROR,
					"Anfrage existiert nicht!");
		}

		
		if ((checkBoth &&(request.getTargetUser().equals(user)
				|| request.getSourceUser().equals(user))) || !checkBoth && request.getTargetUser().equals(user)) {

			return request;

		} else {
			LOGGER.info("Anfrage nicht erlaubt.");
			throw new ShelpException(ReturnCode.PERMISSION_DENIED,
					"Anfrage nicht erlaubt!");
		}

	}

}
