package de.shelp.integration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;

import de.shelp.dao.local.ShelpTourDAOLocal;
import de.shelp.dao.local.ShelpUserDAOLocal;
import de.shelp.dto.ReturnCodeResponse;
import de.shelp.dto.request.RequestTO;
import de.shelp.dto.request.RequestsResponse;
import de.shelp.dto.tour.TourTO;
import de.shelp.dto.tour.ToursResponse;
import de.shelp.entities.ApprovalStatus;
import de.shelp.entities.Capacity;
import de.shelp.entities.Location;
import de.shelp.entities.Request;
import de.shelp.entities.ShelpSession;
import de.shelp.entities.Tour;
import de.shelp.entities.User;
import de.shelp.enums.ReturnCode;
import de.shelp.exception.PermissionDeniedException;
import de.shelp.exception.SessionNotExistException;
import de.shelp.exception.ShelpException;
import de.shelp.exception.TourNotExistException;
import de.shelp.exception.TourNotValidException;
import de.shelp.util.RequestDtoAssembler;
import de.shelp.util.ShelpHelper;
import de.shelp.util.TourDtoAssembler;

@WebService
@WebContext(contextRoot = "/shelp")
@Stateless
public class TourIntegration {

    private static final Logger LOGGER = Logger
	    .getLogger(TourIntegration.class);

    /**
     * EJB zur Abfrage von Datensätzen Referenz auf die EJB wird per Dependency
     * Injection gefüllt.
     */
    @EJB(beanName = "ShelpTourDAO", beanInterface = ShelpTourDAOLocal.class)
    private ShelpTourDAOLocal tourDao;

    /**
     * EJB zur Abfrage von Datensätzen Referenz auf die EJB wird per Dependency
     * Injection gefüllt.
     */
    @EJB(beanName = "ShelpUserDAO", beanInterface = ShelpUserDAOLocal.class)
    private ShelpUserDAOLocal userDao;

    /**
     * EJB zur Erzeugung von DataTransferObjects
     */
    @EJB
    private TourDtoAssembler tourDtoAssembler;

    /**
     * EJB zur Erzeugung von DataTransferObjects
     */
    @EJB
    private RequestDtoAssembler requestDtoAssembler;

    /**
     * EJB zur Beauftragung zum Versenden von E-Mails
     */
    @EJB
    private MailRequesterBean mailRequester;

    @EJB
    private ShelpHelper helper;

    public ReturnCodeResponse createTour(int approvalStatusId, long locationId,
	    int capacityId, int paymentConditionId, int deliveryConditionId,
	    long time, int sessionId) {
	ReturnCodeResponse response = new ReturnCodeResponse();
	try {
	    Tour tour = new Tour();
	    tour.setApprovalStatus(tourDao.getApprovalStatus(approvalStatusId));
	    tour.setLocation(tourDao.getLocation(locationId));
	    tour.setCapacity(tourDao.getCapacity(capacityId));
	    tour.setPaymentCondition(tourDao
		    .getPaymentCondition(paymentConditionId));
	    tour.setDeliveryCondition(tourDao
		    .getDeliveryCondition(deliveryConditionId));
	    tour.setTime(new Date(time));
	    tour.setUpdatedOn(new Date());

	    if (!tour.isValid()) {
		LOGGER.warn("Es sind nicht alle Felder der Fahrt gefüllt.");
		throw new TourNotValidException(ReturnCode.ERROR,
			"Es sind nicht alle Felder der Fahrt gefüllt.");
	    }

	    ShelpSession session = helper.checkSession(sessionId, userDao);

	    tour = tourDao.createTour(tour, session.getUser());

	    LOGGER.info("Fahrt wurde erstellt " + tour.getId() + " nach "
		    + tour.getLocation().getDescription());
	} catch (ShelpException ex) {
	    response.setReturnCode(ex.getErrorCode());
	    response.setMessage(ex.getMessage());
	}

	return response;
    }

    public ToursResponse searchTours(int approvalStatusId, long locationId,
	    int capacityId, long startTime, long endTime, boolean directSearch,
	    int sessionId) {
	ToursResponse response = new ToursResponse();

	try {
	    Location location = tourDao.getLocation(locationId);
	    User currentUser = helper.checkSession(sessionId, userDao)
		    .getUser();
	    ApprovalStatus approvalStatus = tourDao
		    .getApprovalStatus(approvalStatusId);
	    Capacity capacity = tourDao.getCapacity(capacityId);

	    if (location == null || approvalStatus == null || capacity == null) {
		LOGGER.warn("Suche ist nicht vollständig");
		throw new ShelpException(ReturnCode.ERROR,
			"Suche ist nicht vollständig.");
	    }

	    List<Tour> tours = null;
	    if (directSearch) {
		LOGGER.info("Sucht nach Fahrten zu "
			+ location.getDescription());
		tours = tourDao.search(approvalStatus, location, capacity,
			new Date(startTime), new Date(endTime), currentUser);
	    } else {
		LOGGER.info("Sucht nach Fahrten in der Nähe von "
			+ location.getDescription());
		tours = tourDao.searchNear(approvalStatus, location, capacity,
			new Date(startTime), new Date(endTime), currentUser);
	    }
	    LOGGER.info(tours.size() + " Fahrt(en) gefunden");

	    List<TourTO> responseList = new ArrayList<TourTO>();
	    for (Tour tour : tours) {
		responseList.add(tourDtoAssembler.makeDTO(tour));
	    }

	    response.setTours(responseList);
	} catch (ShelpException ex) {
	    response.setReturnCode(ex.getErrorCode());
	    response.setMessage(ex.getMessage());
	}

	return response;
    }

    // public TourResponse getTour(long tourId, int sessionId) {
    // TourResponse response = new TourResponse();
    //
    // try {
    // response.setTour(tourDtoAssembler.makeDTO(this.getTourIntern(
    // tourId, sessionId)));
    // } catch (ShelpException e) {
    // response.setReturnCode(e.getErrorCode());
    // response.setMessage(e.getMessage());
    // }
    //
    // return response;
    // }

    public ToursResponse getTours(int sessionId) {
	ToursResponse response = new ToursResponse();
	try {
	    ShelpSession session = helper.checkSession(sessionId, userDao);

	    List<Tour> tours = session.getUser().getTours();
	    List<TourTO> dtoTours = new ArrayList<TourTO>();
	    for (Tour tour : tours) {
		dtoTours.add(tourDtoAssembler.makeDTO(tour));
	    }

	    LOGGER.info(tours.size() + " Fahrten wurden gefunden.");

	    response.setTours(dtoTours);
	} catch (SessionNotExistException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}

	return response;
    }

    public ReturnCodeResponse deleteTour(long tourId, int sessionId) {
	ReturnCodeResponse response = new ReturnCodeResponse();

	try {
	    Tour tour = tourDao.getTour(tourId);
	    if (tour == null) {
		LOGGER.warn("Fahrt existiert nicht: " + tourId);
		throw new TourNotExistException("Fahrt existiert nicht: "
			+ tourId);
	    }
	    ShelpSession session = helper.checkSession(sessionId, userDao);
	    if (!tour.getOwner().equals(session.getUser())) {
		LOGGER.warn("Zugriff verweigert! " + session.getUser()
			+ " ist nicht der Besitzer der Fahrt!");
		throw new PermissionDeniedException("Zugriff verweigert! "
			+ session.getUser()
			+ " ist nicht der Besitzer der Fahrt!");
	    }

	    tourDao.cancleTour(tour);
	    String logMessage = "Fahrt wurde abgesagt " + tour;
	    LOGGER.info(logMessage);
	   // mailRequester.printLetter(logMessage);
	} catch (ShelpException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}

	return response;
    }

    public ToursResponse getUpdatedTours(int sessionId, long timepoint) {
	ToursResponse response = new ToursResponse();

	try {
	    ShelpSession session = helper.checkSession(sessionId, userDao);
	    // get all tours of the user
	    List<Tour> tours = session.getUser().getTours();

	    List<TourTO> resultList = new ArrayList<TourTO>();
	    // check if tour has updated after timepoint
	    for (Tour tour : tours) {
		if (tour.getUpdatedOn().after(new Date(timepoint))) {
		    resultList.add(tourDtoAssembler.makeDTO(tour));
		}
	    }

	    response.setTours(resultList);
	} catch (ShelpException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}

	return response;
    }

    public RequestsResponse getRequestsOfTour(long tourId, int sessionId) {
	RequestsResponse response = new RequestsResponse();

	try {
	    Tour tour = tourDao.getTour(tourId);
	    if (tour == null) {
		LOGGER.warn("Fahrt existiert nicht: " + tourId);
		throw new TourNotExistException("Fahrt existiert nicht: "
			+ tourId);
	    }
	    ShelpSession session = helper.checkSession(sessionId, userDao);
	    if (!tour.getOwner().equals(session.getUser())) {
		LOGGER.warn("Zugriff verweigert! " + session.getUser()
			+ " ist nicht der Besitzer der Fahrt.");
		throw new PermissionDeniedException("Zugriff verweigert! " + session.getUser()
			+ " ist nicht der Besitzer der Fahrt.");
	    }
	    response.setTour(tourDtoAssembler.makeDTO(tour));

	    List<RequestTO> resultRequests = new ArrayList<RequestTO>();

	    List<Request> requests = tour.getRequest();
	    for (Request request : requests) {
		resultRequests.add(requestDtoAssembler.makeDTO(request));
	    }

	    response.setRequests(resultRequests);
	} catch (ShelpException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}

	return response;
    }

}
