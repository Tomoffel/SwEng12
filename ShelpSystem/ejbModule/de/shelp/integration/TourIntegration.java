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
import de.shelp.dto.tour.TourResponse;
import de.shelp.dto.tour.TourTO;
import de.shelp.dto.tour.ToursResponse;
import de.shelp.entities.ApprovalStatus;
import de.shelp.entities.Location;
import de.shelp.entities.Request;
import de.shelp.entities.ShelpSession;
import de.shelp.entities.Tour;
import de.shelp.entities.User;
import de.shelp.enums.ReturnCode;
import de.shelp.exception.PermissionDeniedException;
import de.shelp.exception.ShelpException;
import de.shelp.exception.TourNotExistException;
import de.shelp.exception.TourNotValidException;
import de.shelp.util.RequestDtoAssembler;
import de.shelp.util.TourDtoAssembler;

@WebService
@WebContext(contextRoot = "/shelp")
@Stateless
public class TourIntegration {

    private static final Logger LOGGER = Logger
	    .getLogger(TourIntegration.class);

    /**
     * EJB zur Abfrage von Datens�tzen Referenz auf die EJB wird per Dependency
     * Injection gef�llt.
     */
    @EJB(beanName = "ShelpTourDAO", beanInterface = ShelpTourDAOLocal.class)
    private ShelpTourDAOLocal tourDao;

    /**
     * EJB zur Abfrage von Datens�tzen Referenz auf die EJB wird per Dependency
     * Injection gef�llt.
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

    public ReturnCodeResponse createTour(int approvalStatusId, long locationId,
	    int capacityId, int paymentConditionId, int deliveryConditionId,
	    long time, int sessionId) {
	ReturnCodeResponse response = new ReturnCodeResponse();
	try {
	    Tour tour = new Tour();
	    tour.setApprovalStatus(tourDao.getApprovalStatus(approvalStatusId));
	    tour.setLocation(tourDao.getLocation(locationId));
	    tour.setCapacity(tourDao.getCapacity(capacityId));
	    tour.setPaymentConditions(tourDao
		    .getPaymentCondition(paymentConditionId));
	    tour.setDeliveryConditions(tourDao
		    .getDeliveryCondition(deliveryConditionId));
	    tour.setTime(new Date(time));

	    if (!tour.isValid()) {
		LOGGER.warn("Es sind nicht alle Felder der Fahrt gef�llt.");
		throw new TourNotValidException(ReturnCode.ERROR,
			"Es sind nicht alle Felder der Fahrt gef�llt.");
	    }

	    ShelpSession session = userDao.getSession(sessionId);

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

	Location location = tourDao.getLocation(locationId);
	User currentUser = userDao.getSession(sessionId).getUser();

	List<Tour> tours = null;
	if (directSearch) {
	    LOGGER.info("Sucht nach Fahrten zu " + location.getDescription());
	    tours = tourDao.search(tourDao.getApprovalStatus(approvalStatusId),
		    location, tourDao.getCapacity(capacityId), new Date(
			    startTime), new Date(endTime), currentUser);
	} else {
	    LOGGER.info("Sucht nach Fahrten in der N�he von "
		    + location.getDescription());
	    tours = tourDao.searchNear(
		    tourDao.getApprovalStatus(approvalStatusId), location,
		    tourDao.getCapacity(capacityId), new Date(startTime),
		    new Date(endTime), currentUser);
	}
	LOGGER.info(tours.size() + " Fahrt(en) gefunden");

	List<TourTO> responseList = new ArrayList<TourTO>();
	for (Tour tour : tours) {
	    responseList.add(tourDtoAssembler.makeDTO(tour));
	}

	response.setTours(responseList);

	return response;
    }

    public TourResponse getTour(long tourId, int sessionId) {
	TourResponse response = new TourResponse();

	try {
	    response.setTour(tourDtoAssembler.makeDTO(this.getTourIntern(
		    tourId, sessionId)));
	} catch (TourNotExistException | PermissionDeniedException e) {
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
	    ShelpSession session = userDao.getSession(sessionId);
	    if (!tour.getOwner().isFriend(session.getUser())) {
		LOGGER.warn("Zugriff verweigert! " + session.getUser()
			+ " ist nicht der Besitzer der Fahrt!");
		throw new PermissionDeniedException("Zugriff verweigert! "
			+ session.getUser()
			+ " ist nicht der Besitzer der Fahrt!");
	    }

	    tourDao.cancleTour(tour);
	} catch (TourNotExistException | PermissionDeniedException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}

	return response;
    }

    public ToursResponse getUpdatedTours(int sessionId, long timepoint) {
	ToursResponse response = new ToursResponse();

	ShelpSession session = userDao.getSession(sessionId);
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

	return response;
    }

    public RequestsResponse getRequestsOfTour(int tourId, int sessionId) {
	RequestsResponse response = new RequestsResponse();

	try {
	    Tour tour = this.getTourIntern(tourId, sessionId);
	    response.setTour(tourDtoAssembler.makeDTO(tour));

	    List<RequestTO> resultRequests = new ArrayList<RequestTO>();

	    List<Request> requests = tour.getRequest();
	    for (Request request : requests) {
		resultRequests.add(requestDtoAssembler.makeDTO(request));
	    }

	    response.setRequests(resultRequests);
	} catch (TourNotExistException | PermissionDeniedException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}

	return response;
    }

    private Tour getTourIntern(long tourId, int sessionId)
	    throws TourNotExistException, PermissionDeniedException {
	Tour tour = tourDao.getTour(tourId);
	if (tour == null) {
	    LOGGER.warn("Fahrt existiert nicht: " + tourId);
	    throw new TourNotExistException("Fahrt existiert nicht: " + tourId);
	}
	ShelpSession session = userDao.getSession(sessionId);
	if (tour.getApprovalStatus().equals(new ApprovalStatus("Nur Freunde"))
		&& !tour.getOwner().isFriend(session.getUser())) {
	    LOGGER.warn("Zugriff verweigert! " + session.getUser()
		    + " ist kein Freund von " + tour.getOwner());
	    throw new PermissionDeniedException("Zugriff verweigert! "
		    + session.getUser() + " ist kein Freund von "
		    + tour.getOwner());
	}
	return tour;
    }

}
