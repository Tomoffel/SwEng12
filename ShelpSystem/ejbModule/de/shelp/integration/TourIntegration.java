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
import de.shelp.enums.TourStatus;
import de.shelp.exception.PermissionDeniedException;
import de.shelp.exception.SessionNotExistException;
import de.shelp.exception.ShelpException;
import de.shelp.exception.TourNotExistException;
import de.shelp.exception.TourNotValidException;
import de.shelp.util.RequestDtoAssembler;
import de.shelp.util.ShelpHelper;
import de.shelp.util.TourDtoAssembler;

/**
 * Webservice der alle nötigen Methoden zur Fahrtenverwaltung bereitstellt. Über
 * die Schnittstelle können Fahrten angelegt
 * {@link #createTour(int, long, int, int, int, long, int)}, gesucht
 * {@link #searchTours(int, long, int, long, long, boolean, int)} und abgesagt
 * {@link #deleteTour(long, int)} werden. Außerdem können alle Fahrten
 * {@link #getTours(int)}, alle aktualisierten Fahrten
 * {@link #getUpdatedTours(int)} einer Session und die Anfragen einer Fahrt
 * {@link #getRequestsOfTour(long, int)} abgefragt werden. <br>
 * Jeder Schritt wird über die Logausgabe dokumentiert. Außerdem werden alle
 * Entitäten vor der Rückgabe in Data Transfer Objekte umgewandelt.
 * 
 * @author Jos Werner
 *
 */
@WebService
@WebContext(contextRoot = "/shelp")
@Stateless
public class TourIntegration {

    private static final Logger LOGGER = Logger
	    .getLogger(TourIntegration.class);

    /**
     * EJB zur Abfrage von Datensätzen der Fahren. Referenz auf die EJB wird per
     * Dependency Injection gefüllt.
     */
    @EJB(beanName = "ShelpTourDAO", beanInterface = ShelpTourDAOLocal.class)
    private ShelpTourDAOLocal tourDao;

    /**
     * EJB zur Abfrage von Datensätzen der Benutzer. Referenz auf die EJB wird
     * per Dependency Injection gefüllt.
     */
    @EJB(beanName = "ShelpUserDAO", beanInterface = ShelpUserDAOLocal.class)
    private ShelpUserDAOLocal userDao;

    /**
     * EJB zur Erzeugung von DataTransferObjects von Fahrten
     */
    @EJB
    private TourDtoAssembler tourDtoAssembler;

    /**
     * EJB zur Erzeugung von DataTransferObjects von Anfragen
     */
    @EJB
    private RequestDtoAssembler requestDtoAssembler;

    /**
     * EJB zur Beauftragung zum Versenden von E-Mails
     */
    @EJB
    private MailRequesterBean mailRequester;

    /**
     * EJB zur Einbindung von generellen Hilfsmethoden
     */
    @EJB
    private ShelpHelper helper;

    /**
     * Schnittstelle die genutzt werden kann um eine Fahrt ({@link Tour}) zu
     * erstellen. Setzt den {@link TourStatus} der fahrt auf PLANNED. Prüft ob
     * alle Ids existieren und ob die sessionId aktuell angemeldet ist. Ist eine
     * der Bedingungen nicht erfüllt wird der {@link ReturnCode} auf ERROR
     * gesetzt. Andernfalls wird die Fahrt in der Datenbank gespeichert.
     * 
     * @param approvalStatusId
     *            - Id des Freigabestatus der Fahrt (muss in der Datenbank
     *            existieren)
     * @param locationId
     *            - Id des Ortes der Fahrt (muss in der Datenbank existieren)
     * @param capacityId
     *            - Id der Kapazität der Fahrt (muss in der Datenbank
     *            existieren)
     * @param paymentConditionId
     *            - Id der Bezahlungbedingung (muss in der Datenbank existieren)
     * @param deliveryConditionId
     *            - Id der Lieferbedingung (muss in der Datenbank existieren)
     * @param time
     *            - Zeitpunkt der Fahrt
     * @param sessionId
     *            - SessionId die die Fahrt anlegt (muss aktuell angemeldet
     *            sein)
     * 
     * @return einen {@link ReturnCodeResponse} der entweder den Status OK oder
     *         ERROR + Fehlernachricht beinhaltet
     */
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
	    tour.setUpdated(false);

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

    /**
     * Schnittstelle um nach Fahrten ({@link Tour}) zu suchen. Die Fahrten
     * können nach Freigabestatus, Ort, Kapazität und Zeitraum gefiltert werden.
     * Außerdem kann direkt am Ort oder im Umfeld (gleiche PLZ) gesucht werden.
     * Eigene Fahrten werden nicht zurückgegeben. Gibt es den Freigabestatus,
     * den Ort, die Kapazität oder die sessionId nicht wird der
     * {@link ReturnCode} ERROR zurückgegeben.
     * 
     * @param approvalStatusId
     *            - Id des Freigabestatus der Fahrt (muss in der Datenbank
     *            existieren)
     * @param locationId
     *            - Id des Ortes der Fahrt (muss in der Datenbank existieren)
     * @param capacityId
     *            - Id der Kapazität der Fahrt (muss in der Datenbank
     *            existieren)
     * @param startTime
     *            - Anfangszeitpunkt der Suche
     * @param endTime
     *            - Endzeitpunkt der Suche
     * @param directSearch
     *            - Nur für den Ort (true) oder auch im Umfeld (false)
     * @param sessionId
     * 
     * @return einen {@link ToursResponse} mit dem Status OK und allen gefunden
     *         Fahrten oder Status ERROR + Fehlermeldung
     */
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

    /**
     * Schnittstelle um alle Fahrten ({@link Tour}) der übergebenen SessionId zu
     * erhalten. Kontrolliert ob die Session wirklich existiert, falls nicht
     * wird der {@link ReturnCode} ERROR zurückgegeben.
     * 
     * @param sessionId
     * @return einen {@link ToursResponse} mit dem {@link ReturnCode} OK und
     *         allen Fahrten der Session oder ERROR + eine Fehlermeldung
     */
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

    /**
     * Schnittstelle um eine Fahrt ({@link Tour}) abzusagen. Der
     * {@link TourStatus} der Fahrt wird auf CANCELLED gesetzt. Kontrolliert ob
     * die FahrtId und SessionId existiert und gibt andernfalls den
     * {@link ReturnCode} ERROR zurück. Überprüft außerdem ob der Benutzer (
     * {@link User}) aus der Session auch der Besitzer er Fahrt ist. Ist es
     * nicht so wird der {@link ReturnCode} PERMISSION_DENIED zurückgegeben.
     * 
     * @param tourId
     *            - Id der Fahrt die geschlossen werden soll
     * @param sessionId
     *            - Id der Session die die Fahrt schließen möchte
     * @return einen {@link ReturnCodeResponse} der entweder den Status OK oder
     *         ERROR/PERMISSION_DENIED + Fehlernachricht beinhaltet
     */
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
	    List<Request> requests = tour.getRequest();
	    for (Request request : requests) {
		mailRequester.printLetter(logMessage, request.getSourceUser()
			.getEmail());
	    }
	} catch (ShelpException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}

	return response;
    }

    /**
     * Schnittstelle um alle in der Zwischenzeit aktualisierten Fahrten (
     * {@link Tour}) der SessionId zu erhalten. Eine Fahrt wird immer dann
     * aktualisiert, wenn eine Anfrage an die Fahrt geschickt wurde. Überprüft
     * ob die SessionId existiert und gibt {@link ReturnCode} ERROR zurück falls
     * nicht.
     * 
     * @param sessionId
     *            - Id der Session die die aktualisieren Fahrten haben möchte
     * 
     * @return einen {@link ToursResponse} mit dem {@link ReturnCode} OK und
     *         allen aktualisierten Fahrten der Session oder ERROR +
     *         Fehlermeldung
     */
    public ToursResponse getUpdatedTours(int sessionId) {
	ToursResponse response = new ToursResponse();

	try {
	    ShelpSession session = helper.checkSession(sessionId, userDao);
	    // Hole alle Fahrten eines Benutzers
	    List<Tour> tours = session.getUser().getTours();

	    List<TourTO> resultList = new ArrayList<TourTO>();
	    // Prüfe ob die Fahrt seid dem letzten Abruf aktualisiert wurde
	    for (Tour tour : tours) {
		if (tour.isUpdated()) {
		    // tour aufnehmen und wieder als nicht aktualisiert
		    // abspeichern
		    resultList.add(tourDtoAssembler.makeDTO(tour));
		    tour.setUpdated(false);
		    tourDao.saveTour(tour);
		}
	    }

	    response.setTours(resultList);
	    LOGGER.info(resultList.size() + " aktualisierte Fahrten gefunden.");
	} catch (ShelpException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}

	return response;
    }

    /**
     * Schnittstelle um die Anfragen ({@link Request}) einer Fahrt ({@link Tour}
     * ) zu erhalten. Überprüft ob die TourId und SessionId existiert und gibt
     * den {@link ReturnCode} ERROR zurück falls nicht. Prüft außerdem ob dem
     * Benutzer {@link User} aus der Session die Fahrt gehört und gibt den
     * {@link ReturnCode} PERMISSION_DENIED zurück falls nicht.
     * 
     * @param tourId
     *            - Id der Fahrt für die die Anfragen abgefragt werden
     * @param sessionId
     *            - Id der Session die die Anfragen abfragen möchte
     * 
     * @return einen {@link RequestsResponse} mit dem {@link ReturnCode} OK und
     *         allen Anfragen zur Fahrt oder ERROR/PERMISSION_DENIED +
     *         Fehlermeldung
     */
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
		throw new PermissionDeniedException("Zugriff verweigert! "
			+ session.getUser()
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
