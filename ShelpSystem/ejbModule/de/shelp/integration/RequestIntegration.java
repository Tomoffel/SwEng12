package de.shelp.integration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebService;

import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;

import de.shelp.dao.local.ShelpRequestDAOLocal;
import de.shelp.dao.local.ShelpTourDAOLocal;
import de.shelp.dao.local.ShelpUserDAOLocal;
import de.shelp.dto.ReturnCodeResponse;
import de.shelp.dto.request.RequestTO;
import de.shelp.dto.request.RequestsResponse;
import de.shelp.entities.Request;
import de.shelp.entities.ShelpSession;
import de.shelp.entities.Tour;
import de.shelp.entities.User;
import de.shelp.entities.WishlistItem;
import de.shelp.enums.RequestStatus;
import de.shelp.enums.ReturnCode;
import de.shelp.exception.SessionNotExistException;
import de.shelp.exception.ShelpException;
import de.shelp.exception.TourNotExistException;
import de.shelp.util.RequestDtoAssembler;
import de.shelp.util.ShelpHelper;

/**
 * Webservice der alle notwendigen Methoden zur Anfrageverwaltung bereitstellt.
 * Über die Schnittstellen können Anfrage akzeptiert
 * {@link #acceptRequest(long, String, int)}, erstellt
 * {@link #createRequest(long, String, int, String)}, und gelöscht werden
 * {@link #deleteRequest(long, int)}. Zudem können alle Anfrage {@link
 * getRequests(int) sowie alle aktualisierten Anfrage
 * 
 * @link #getUpdatedRequests(int)} abgefragt werden. Jeder Schritt wird über die
 *       Logausgabe dokumentiert. Außerdem werden alle Entitäten vor der
 *       Rückgabe in Data Transfer Objekte umgewandelt.
 * 
 * @author Thomas Sennekamp
 * 
 *
 */
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
     * EJB zur Beauftragung zum Versenden von E-Mails
     */
    @EJB
    private MailRequesterBean mailRequester;

    /**
     * EJB zur Abfrage von Datensätzen Referenz auf die EJB wird per Dependency
     * Injection gefüllt.
     */
    @EJB(beanName = "ShelpTourDAO", beanInterface = ShelpTourDAOLocal.class)
    private ShelpTourDAOLocal tourDao;

    @EJB
    private ShelpHelper helper;

    /**
     * Schnittstelle, die genutzt werden kann um eine Anfrage ({@link Request})
     * zu akzeptieren. Es wird zunächst geprüft, ob die Anfrage und die mit ihr
     * verbundene Session gültig ist. Weiterhin wird geprüft, ob die Anfrage
     * bereits akzeptiert wurde. Anschließend wird überprüft, welche Einträge
     * der Wunschliste auf der Einkaufliste akzeptiert wurden und dem
     * entsprechend der Anfragestatus {@link RequestStatus} auf DENIED(alle
     * abgelehnt), ACCEPT(alle akzeptiert),PARTLY_ACCEPT(teilweise akzeptiert)
     * gesetzt. <br>
     * Die Ids werden dabei als String mit dem Trennungszeichen "\n" übertragen.
     * Dies ist nötig da kSoap keine Listen übertragen kann.<br>
     * Abschließend wird die Anfrage inkl. neuem Status in der Datenbank
     * gespeichert sowie eine Mail an den entsprechenden Benutzer gesendet.
     * 
     * @param requestId
     *            ID der Anfrage (muss in der Datenbank existieren)
     * @param acceptedIds
     *            IDs der akzeptierten Wunschliste-Einträge als String getrennt
     *            durch "\n"
     * @param sessionId
     *            SessionId die die Fahrt anlegt (muss aktuell angemeldet sein)
     * @return einen {@link ReturnCodeResponse} der entweder den Status OK oder
     *         ERROR + Fehlernachricht beinhaltet
     */
    public ReturnCodeResponse acceptRequest(long requestId, String acceptedIds,
	    int sessionId) {

	ReturnCodeResponse response = new ReturnCodeResponse();

	try {
	    Request request = checkRequest(sessionId, requestId, false);

	    if (!request.getStatus().equals(RequestStatus.ASKED)) {
		LOGGER.warn("Anfrage wurde schon angenommen/abgelehnt");
		throw new ShelpException(ReturnCode.ERROR,
			"Anfrage wurde schon angenommen/abgelehnt");
	    }

	    boolean acceptOneItem = false;
	    boolean acceptAllItem = true;

	    List<WishlistItem> wishes = request.getWishes();
	    ArrayList<String> ids = new ArrayList<String>(
		    Arrays.asList(acceptedIds.split("\n")));
	    for (WishlistItem wishlistItem : wishes) {
		if (ids.contains(String.valueOf(wishlistItem.getId()))) {
		    acceptOneItem = true;
		    wishlistItem.setChecked(true);
		} else {
		    acceptAllItem = false;
		}
	    }
	    request.setUpdated(true);

	    if (!acceptOneItem) {
		request.setStatus(RequestStatus.DENIED);
	    } else if (acceptAllItem) {
		request.setStatus(RequestStatus.ACCEPT);
	    } else {
		request.setStatus(RequestStatus.PARTLY_ACCEPT);
	    }

	    String logMessage = "Die Anfrage zur Fahrt "
		    + request.getTour().getLocation()
		    + " wurde auf den Status " + request.getStatus()
		    + " gesetzt.";
	    LOGGER.info(logMessage);
	    mailRequester.printLetter(logMessage, request.getSourceUser()
		    .getEmail());

	    daoRequest.persistRequest(request);

	} catch (ShelpException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}

	return response;
    }

    /**
     * Schnittstelle die genutzt werden kann umd eine Anfrage zu erstellen. (
     * {@link Request}) Es wird zunächstgeprüft, ob die Anfrage und die mit ihr
     * verbundene Session gültig ist. Weiterhin wird geprüft, ob die verbundene
     * Tour ({@link Tour}) überhaupt existiert. Im Fehlerfall wird eine
     * Fehlermeldung geworfen. Als nächstes wird geprüft, ob eine Anfrage an
     * seine eigene Tour versucht wird, dies ist ebenfalls nicht erlaubt und
     * wird unterbunden. <br>
     * Anschließend wird die Anfrage erstellt und mit den übergebenen Inhalten
     * gefüllt und in der Datenbank gespeichert sowie eine Status-Email an den
     * Besitzer der Tour gesendet.<br>
     * Die Wünsche werden dabei als String mit dem Trennungszeichen "\n"
     * übertragen. Dies ist nötig da kSoap keine Listen übertragen kann.
     * 
     * @param targetUserId
     *            UserID des Besitzer der Tour (muss in der Datenbank
     *            existieren)
     * @param tourId
     *            Aktuelle TourId (muss in der Datenbank existieren)
     * @param notice
     *            Notiz des Anfragenden (optional für den Benutzer)
     * @param sessionId
     *            Aktuelle SessionID (muss aktuell angemeldet sein)
     * @param wishes
     *            Liste mit Einkaufswünschen als String getrennt durch "\n"
     * @return einen {@link ReturnCodeResponse} der entweder den Status OK oder
     *         ERROR + Fehlernachricht beinhaltet
     * @throws SessionNotExistException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ReturnCodeResponse createRequest(long tourId, String notice,
	    int sessionId, String wishes) {

	// create empty response
	ReturnCodeResponse response = new ReturnCodeResponse();

	try {
	    // check current Session
	    ShelpSession session = helper.checkSession(sessionId, daoUser);

	    // get current Tour
	    Tour tour = tourDao.getTour(tourId);

	    if (tour == null) {
		String message = "Fahrt existiert nicht.";
		LOGGER.info(message);
		throw new TourNotExistException(message);
	    }

	    // get targetUser
	    User targetUser = tour.getOwner();

	    if (targetUser.equals(session.getUser())) {
		String message = "Sich selbst eine Anfrage senden, ergibt doch gar keinen Sinn lieber "
			+ session.getUser();
		LOGGER.info(message);
		throw new ShelpException(ReturnCode.ERROR, message);
	    }

	    // create request if parameter are ok
	    Request request = new Request();
	    request.setSourceUser(session.getUser());
	    request.setTargetUser(targetUser);
	    request.setTour(tour);
	    request.setNotice(notice);
	    request.setUpdated(false);
	    request.setStatus(RequestStatus.ASKED);

	    // create list for wishlistitems
	    List<WishlistItem> wishlistItems = new ArrayList<WishlistItem>();

	    String[] split = wishes.split("\n");

	    for (String string : split) {
		WishlistItem item = new WishlistItem();
		item.setText(string);
		item.setChecked(false);
		item.setOwner(request);
		wishlistItems.add(item);
	    }

	    // set created wishlist to request
	    request.setWishes(wishlistItems);

	    // create request
	    daoRequest.persistRequest(request);

	    // update time for tour
	    tour.setUpdated(true);

	    // save tour
	    tourDao.saveTour(tour);

	    String logMessage = "Zu der Fahrt " + tour.getLocation() + " ("
		    + tour.getId() + ") wurde eine Anfrage gestellt.";
	    LOGGER.info(logMessage);
	    mailRequester.printLetter(logMessage, tour.getOwner().getEmail());

	} catch (ShelpException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}

	return response;
    }

    /**
     * Schnittstelle, die genutzt werden kann um Anfragen {@link Request})
     * abzufragen. Es wird zunächstgeprüft, ob die mit ihr verbundene Session
     * gültig ist und gibt {@link ReturnCode} ERROR zurück, falls nicht.
     * Anschließend werden alle eigenen, gültigen Anfragen {@link Request }
     * abgerufen und zurückgegeben.
     * 
     * @param sessionId
     *            Aktuelle SessionID (muss aktuell angemeldet sein)
     * @return einen {@link ReturnCodeResponse} der entweder den Status OK oder
     *         ERROR + Fehlernachricht beinhaltet
     */
    public RequestsResponse getRequests(int sessionId) {
	RequestsResponse response = new RequestsResponse();
	try {
	    ShelpSession session = helper.checkSession(sessionId, daoUser);

	    List<Request> requests = session.getUser().getOwnRequests();
	    List<RequestTO> dtoRequests = new ArrayList<RequestTO>();
	    for (Request request : requests) {
		if (!request.getStatus().equals(RequestStatus.REMOVED)) {
		    dtoRequests.add(requestDtoAssembler.makeDTO(request));
		}
	    }

	    LOGGER.info(requests.size() + " Anfragen gefunden.");

	    response.setRequests(dtoRequests);
	} catch (SessionNotExistException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}

	return response;
    }

    /**
     * Schnittstelle, die genutzt werden kann um eine Anfrage zu löschen. Es
     * wird zunächstgeprüft, ob die Anfrage und die mit ihr verbundene Session
     * gültig ist und gibt {@link ReturnCode} ERROR zurück, falls nicht. Im
     * Erfolgsfall wird die Anfrage gelöscht.
     * 
     * @param requestId
     *            ID der zu löschenden Anfrage
     * @param sessionId
     *            Aktuelle SessionID (muss aktuell angemeldet sein)
     * @return einen {@link ReturnCodeResponse} der entweder den Status OK oder
     *         ERROR + Fehlernachricht beinhaltet
     */
    public ReturnCodeResponse deleteRequest(long requestId, int sessionId) {

	// Erstelle Antwortobjekt
	ReturnCodeResponse response = new ReturnCodeResponse();

	try {
	    // überprüfe Anfrage
	    Request request = checkRequest(sessionId, requestId, true);

	    // lösche Anfrage
	    daoRequest.deleteRequest(request);
	    LOGGER.info("Anfrage wurde gelöscht.");

	} catch (ShelpException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}

	return response;
    }

    /**
     * Schnittstelle zur Abfrage von aktualisierten Anfragen. Es wird
     * zunächstgeprüft, ob die verbundene Session gültig ist und gibt
     * {@link ReturnCode} ERROR zurück, Anschließend werden die aktualiserten
     * Anfragen aus der Datenbank abgerufen und zurückgegeben. Zurückgelieferte
     * Anfragen werden als nicht aktualisiert markiert und wieder in der
     * Datenbank gespeichert.
     * 
     * @param sessionId
     *            Aktuelle SessionID (muss aktuell angemeldet sein)
     * @return einen {@link ReturnCodeResponse} der entweder den Status OK oder
     *         ERROR + Fehlernachricht beinhaltet
     * @throws SessionNotExistException
     */
    public RequestsResponse getUpdatedRequests(int sessionId) {

	// Erstelle Antwortobjekt
	RequestsResponse response = new RequestsResponse();
	try {
	    // überprüfe session
	    ShelpSession session = helper.checkSession(sessionId, daoUser);

	    // hole alle Anfragen des Benutzers
	    List<Request> requests = session.getUser().getOwnRequests();

	    List<RequestTO> resultList = new ArrayList<RequestTO>();
	    // überprüfe, ob Tour als aktualisiert markiert ist
	    for (Request request : requests) {
		if (request.isUpdated()) {
		    // request aufnehmen und wieder als nicht aktualisiert
		    // abspeichern
		    resultList.add(requestDtoAssembler.makeDTO(request));
		    request.setUpdated(false);
		    daoRequest.persistRequest(request);
		}
	    }
	    response.setRequests(resultList);
	    LOGGER.info(requests.size() + " aktualisierte Anfragen gefunden.");
	} catch (ShelpException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}

	return response;
    }

    /**
     * Hilffunktion - Überprüft eine Anfrage auf Gültigkeit
     * 
     * @param sessionId
     *            Aktuelle SessionId
     * @param requestId
     *            ID der zu überprüfenden Anfrage
     * @param checkBoth
     *            Steuerparameter für die Überprüfung
     * @return
     * @throws ShelpException
     */
    private Request checkRequest(int sessionId, long requestId,
	    boolean checkBoth) throws ShelpException {

	// hole aktuelle session
	ShelpSession session = helper.checkSession(sessionId, daoUser);

	// hole aktuellen user
	User user = session.getUser();

	// hole aktuelle anfrage
	Request request = daoRequest.getRequestById(requestId);

	// überprüfe Anfrage
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
}
