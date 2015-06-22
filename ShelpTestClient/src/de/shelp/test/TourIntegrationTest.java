package de.shelp.test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import de.shelp.integration.AllListResponse;
import de.shelp.integration.ApprovalStatusTO;
import de.shelp.integration.CapacityTO;
import de.shelp.integration.DeliveryConditionTO;
import de.shelp.integration.FriendIntegration;
import de.shelp.integration.FriendIntegrationService;
import de.shelp.integration.FriendsResponse;
import de.shelp.integration.LocationTO;
import de.shelp.integration.PaymentConditionTO;
import de.shelp.integration.RequestIntegration;
import de.shelp.integration.RequestIntegrationService;
import de.shelp.integration.RequestTO;
import de.shelp.integration.RequestsResponse;
import de.shelp.integration.ReturnCode;
import de.shelp.integration.ReturnCodeResponse;
import de.shelp.integration.ShelpSessionTO;
import de.shelp.integration.StateIntegration;
import de.shelp.integration.StateIntegrationService;
import de.shelp.integration.TourIntegration;
import de.shelp.integration.TourIntegrationService;
import de.shelp.integration.TourTO;
import de.shelp.integration.ToursResponse;
import de.shelp.integration.UserIntegration;
import de.shelp.integration.UserIntegrationService;
import de.shelp.integration.UserResponse;

/**
 * Testet alle Webservice-Schnittstellen zur TourIntegration. * 
 * @author Thomas Sennekamp
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TourIntegrationTest {

    private static TourIntegration remoteSystem;

    private static TourTO tour1 = new TourTO();
    private static TourTO tour2 = new TourTO();
    private static TourTO tour3 = new TourTO();
    private static TourTO tour4 = new TourTO();
    private static TourTO tour5 = new TourTO();
    private static TourTO tour6 = new TourTO();
    private static TourTO tour7 = new TourTO();

    private static List<LocationTO> locations;

    private static ShelpSessionTO session1;

    private static GregorianCalendar calendarInTwoDays;

    private static GregorianCalendar calendarInThreeDays;

    private static GregorianCalendar calendarInOneDay;

    private static GregorianCalendar calendarInFourDays;

    private static ShelpSessionTO session2;
    private static ShelpSessionTO session3;

    private static List<CapacityTO> capacities;

    private static List<DeliveryConditionTO> deliveryConditions;

    private static List<PaymentConditionTO> paymentConditions;

    private static List<ApprovalStatusTO> states;

    /**
     * Baut einmalig die Verbindung zum Server auf setzt wichtige
     * Klassenvariablen für die Testfälle
     */
    @BeforeClass
    public static void initTestCase() {
	TourIntegrationService service = new TourIntegrationService();
	remoteSystem = service.getTourIntegrationPort();

	UserIntegrationService userService = new UserIntegrationService();
	UserIntegration userIntegrationPort = userService
		.getUserIntegrationPort();

	StateIntegrationService stateService = new StateIntegrationService();
	StateIntegration stateRemoteSystem = stateService
		.getStateIntegrationPort();

	AllListResponse allLists = stateRemoteSystem.getAllLists();

	locations = allLists.getLocations();
	capacities = allLists.getCapacities();
	deliveryConditions = allLists.getDeliveryConditions();
	paymentConditions = allLists.getPaymentConditions();
	states = allLists.getStates();

	calendarInTwoDays = new GregorianCalendar();
	calendarInTwoDays.add(Calendar.DAY_OF_MONTH, 2);

	calendarInThreeDays = new GregorianCalendar();
	calendarInThreeDays.add(Calendar.DAY_OF_MONTH, 3);

	calendarInOneDay = new GregorianCalendar();
	calendarInOneDay.add(Calendar.DAY_OF_MONTH, 1);

	calendarInFourDays = new GregorianCalendar();
	calendarInFourDays.add(Calendar.DAY_OF_MONTH, 4);

	tour1.setApprovalStatus(states.get(0));
	tour1.setLocation(locations.get(0));
	tour1.setCapacity(capacities.get(1));
	tour1.setPaymentCondition(paymentConditions.get(1));
	tour1.setDeliveryCondition(deliveryConditions.get(0));
	tour1.setTime(calendarInTwoDays.getTime().getTime());

	tour2.setLocation(locations.get(2));
	tour2.setPaymentCondition(paymentConditions.get(0));

	tour3.setApprovalStatus(states.get(0));
	tour3.setLocation(locations.get(1));
	tour3.setCapacity(capacities.get(2));
	tour3.setPaymentCondition(paymentConditions.get(2));
	tour3.setDeliveryCondition(deliveryConditions.get(1));
	tour3.setTime(calendarInTwoDays.getTime().getTime());

	tour4.setApprovalStatus(states.get(0));
	tour4.setLocation(locations.get(5));
	tour4.setCapacity(capacities.get(2));
	tour4.setPaymentCondition(paymentConditions.get(2));
	tour4.setDeliveryCondition(deliveryConditions.get(1));
	tour4.setTime(calendarInTwoDays.getTime().getTime());

	tour5.setApprovalStatus(states.get(0));
	tour5.setLocation(locations.get(2));
	tour5.setCapacity(capacities.get(2));
	tour5.setPaymentCondition(paymentConditions.get(2));
	tour5.setDeliveryCondition(deliveryConditions.get(1));
	tour5.setTime(calendarInFourDays.getTime().getTime());

	tour6.setApprovalStatus(states.get(1));
	tour6.setLocation(locations.get(0));
	tour6.setCapacity(capacities.get(1));
	tour6.setPaymentCondition(paymentConditions.get(2));
	tour6.setDeliveryCondition(deliveryConditions.get(1));
	tour6.setTime(calendarInTwoDays.getTime().getTime());

	tour7.setApprovalStatus(states.get(1));
	tour7.setLocation(locations.get(1));
	tour7.setCapacity(capacities.get(1));
	tour7.setPaymentCondition(paymentConditions.get(2));
	tour7.setDeliveryCondition(deliveryConditions.get(1));
	tour7.setTime(calendarInFourDays.getTime().getTime());

	UserResponse loginResponse = userIntegrationPort.regUser(
		"thomas@sennekamp.de", "test123");
	if (loginResponse.getReturnCode() == ReturnCode.ERROR) {
	    loginResponse = userIntegrationPort.login("thomas@sennekamp.de",
		    "test123");
	}
	session1 = loginResponse.getSession();

	loginResponse = userIntegrationPort.regUser("theresa@sennekamp.de",
		"test123");
	if (loginResponse.getReturnCode() == ReturnCode.ERROR) {
	    loginResponse = userIntegrationPort.login("theresa@sennekamp.de",
		    "test123");
	}
	session2 = loginResponse.getSession();

	loginResponse = userIntegrationPort.regUser("jos@sennekamp.de",
		"test123");
	if (loginResponse.getReturnCode() == ReturnCode.ERROR) {
	    loginResponse = userIntegrationPort.login("jos@sennekamp.de",
		    "test123");
	}
	session3 = loginResponse.getSession();

	FriendIntegrationService friendService = new FriendIntegrationService();
	FriendIntegration friendRemoteSystem = friendService
		.getFriendIntegrationPort();

	friendRemoteSystem.addFriend(session1.getId(), session2.getUser()
		.getEmail());

	FriendsResponse friends = friendRemoteSystem.getFriends(session2
		.getId());

	friendRemoteSystem.acceptFriendship(
		friends.getFriends().get(0).getId(), session2.getId());

	remoteSystem.createTour(tour3.getApprovalStatus().getId(), tour3
		.getLocation().getId(), tour1.getCapacity().getId(), tour3
		.getPaymentCondition().getId(), tour3.getDeliveryCondition()
		.getId(), tour3.getTime(), session1.getId());
	remoteSystem.createTour(tour4.getApprovalStatus().getId(), tour4
		.getLocation().getId(), tour1.getCapacity().getId(), tour4
		.getPaymentCondition().getId(), tour4.getDeliveryCondition()
		.getId(), tour4.getTime(), session2.getId());
	remoteSystem.createTour(tour5.getApprovalStatus().getId(), tour5
		.getLocation().getId(), tour1.getCapacity().getId(), tour5
		.getPaymentCondition().getId(), tour5.getDeliveryCondition()
		.getId(), tour5.getTime(), session2.getId());
	remoteSystem.createTour(tour6.getApprovalStatus().getId(), tour6
		.getLocation().getId(), tour6.getCapacity().getId(), tour6
		.getPaymentCondition().getId(), tour6.getDeliveryCondition()
		.getId(), tour6.getTime(), session1.getId());
	remoteSystem.createTour(tour7.getApprovalStatus().getId(), tour7
		.getLocation().getId(), tour6.getCapacity().getId(), tour7
		.getPaymentCondition().getId(), tour7.getDeliveryCondition()
		.getId(), tour7.getTime(), session1.getId());
    }

    /**
     * Testet ob eine neue Fahrt erstellt werden kann. Erwartet ein OK.
     */
    @Test
    public void aTestCreateTour() {
	ReturnCodeResponse createTour = remoteSystem.createTour(tour1
		.getApprovalStatus().getId(), tour1.getLocation().getId(),
		tour1.getCapacity().getId(), tour1.getPaymentCondition()
			.getId(), tour1.getDeliveryCondition().getId(), tour1
			.getTime(), session1.getId());
	Assert.assertEquals(ReturnCode.OK, createTour.getReturnCode());
    }

    /**
     * Testet alle Fehler an die beim Fahrterstellen auftreten können.
     */
    @Test
    public void bTestCreateTourFail() {
	// Toureinstellungen falsch
	ReturnCodeResponse createTour = remoteSystem.createTour(100, 100, 5,
		105, 200, 0L, session1.getId());
	Assert.assertEquals(ReturnCode.ERROR, createTour.getReturnCode());

	// Session existiert nicht
	createTour = remoteSystem.createTour(tour1.getApprovalStatus().getId(),
		tour1.getLocation().getId(), tour1.getCapacity().getId(), tour1
			.getPaymentCondition().getId(), tour1
			.getDeliveryCondition().getId(), tour1.getTime(), 5000);
	Assert.assertEquals(ReturnCode.ERROR, createTour.getReturnCode());
    }

    /**
     * Testet ob Fahrten gesucht werden können die für alle Freigegeben wurden
     * und direkt am Ort sind. Erwartet das die Fahrten die vorher angelegt
     * wurden passend gefunden werden.
     */
    @Test
    public void cTestSearchToursAllDirect() {

	ToursResponse searchTour = remoteSystem.searchTours(states.get(0)
		.getId(), locations.get(0).getId(), capacities.get(1).getId(),
		calendarInOneDay.getTime().getTime(), calendarInThreeDays
			.getTime().getTime(), true, session2.getId());

	Assert.assertEquals(searchTour.getTours().size(), 2);
	Assert.assertEquals(searchTour.getTours().get(0).getLocation()
		.getDescription(), locations.get(0).getDescription());
	Assert.assertEquals(searchTour.getTours().get(1).getLocation()
		.getDescription(), locations.get(0).getDescription());
    }

    /**
     * Testet ob Fahrten gesucht werden können die für "Nur Freunde" Freigegeben
     * wurden und direkt am Ort sind. Erwartet das die Fahrten die vorher
     * angelegt wurden passend gefunden werden.
     */
    @Test
    public void cTestSearchToursFriendDirect() {
	ToursResponse searchTour = remoteSystem.searchTours(states.get(1)
		.getId(), locations.get(0).getId(), capacities.get(1).getId(),
		calendarInOneDay.getTime().getTime(), calendarInThreeDays
			.getTime().getTime(), true, session2.getId());

	Assert.assertEquals(searchTour.getTours().size(), 1);
	Assert.assertEquals(searchTour.getTours().get(0).getLocation()
		.getDescription(), locations.get(0).getDescription());
    }

    /**
     * Testet ob Fahrten gesucht werden können die für alle Freigegeben wurden
     * und im Umfeld des Ortes sind. Erwartet das die Fahrten die vorher
     * angelegt wurden passend gefunden werden.
     */
    @Test
    public void dTestSearchToursAllNear() {
	ToursResponse searchTour = remoteSystem.searchTours(states.get(0)
		.getId(), locations.get(0).getId(), capacities.get(1).getId(),
		calendarInOneDay.getTime().getTime(), calendarInThreeDays
			.getTime().getTime(), false, session2.getId());

	Assert.assertEquals(searchTour.getTours().size(), 3);
	Assert.assertEquals(searchTour.getTours().get(0).getLocation()
		.getDescription(), locations.get(0).getDescription());
	Assert.assertEquals(searchTour.getTours().get(1).getLocation()
		.getDescription(), locations.get(0).getDescription());
	Assert.assertEquals(searchTour.getTours().get(2).getLocation()
		.getDescription(), locations.get(1).getDescription());
    }

    /**
     * Testet ob Fahrten gesucht werden können die für "Nur Freunde" Freigegeben
     * wurden und im Umfeld des Ortes sind. Erwartet das die Fahrten die vorher
     * angelegt wurden passend gefunden werden.
     */
    @Test
    public void dTestSearchToursFriendNear() {
	ToursResponse searchTour = remoteSystem.searchTours(states.get(1)
		.getId(), locations.get(0).getId(), capacities.get(1).getId(),
		calendarInOneDay.getTime().getTime(), calendarInThreeDays
			.getTime().getTime(), false, session2.getId());

	Assert.assertEquals(searchTour.getTours().size(), 1);
	Assert.assertEquals(searchTour.getTours().get(0).getLocation()
		.getDescription(), locations.get(0).getDescription());
    }

    /**
     * Testet alle Fehler an die beim Fahrtensuchen auftreten können.
     */
    @Test
    public void eTestSearchToursFail() {
	// Session existiert nicht
	ToursResponse searchTour = remoteSystem.searchTours(states.get(1)
		.getId(), locations.get(0).getId(), capacities.get(1).getId(),
		calendarInOneDay.getTime().getTime(), calendarInThreeDays
			.getTime().getTime(), false, 500);

	Assert.assertEquals(ReturnCode.ERROR, searchTour.getReturnCode());
    }

    /**
     * Testet ob eine Fahrt abgesagt werden können.
     */
    @Test
    public void fTestDeleteTour() {
	ToursResponse searchTour = remoteSystem.searchTours(states.get(0)
		.getId(), locations.get(0).getId(), capacities.get(1).getId(),
		calendarInOneDay.getTime().getTime(), calendarInThreeDays
			.getTime().getTime(), true, session2.getId());

	ReturnCodeResponse tour = remoteSystem.deleteTour(searchTour.getTours()
		.get(0).getId(), session1.getId());
	Assert.assertEquals(ReturnCode.OK, tour.getReturnCode());
    }

    /**
     * Testet alle Fehler an die beim Fahrtensuchen auftreten können.
     */
    @Test
    public void fTestDeleteTourFail() {
	ToursResponse searchTour = remoteSystem.searchTours(states.get(0)
		.getId(), locations.get(0).getId(), capacities.get(1).getId(),
		calendarInOneDay.getTime().getTime(), calendarInThreeDays
			.getTime().getTime(), true, session2.getId());

	// Session existiert nicht
	ReturnCodeResponse tour = remoteSystem.deleteTour(searchTour.getTours()
		.get(0).getId(), 5000);
	Assert.assertEquals(ReturnCode.ERROR, tour.getReturnCode());

	// Tour existiert nicht
	tour = remoteSystem.deleteTour(5000, session1.getId());
	Assert.assertEquals(ReturnCode.ERROR, tour.getReturnCode());
    }

    /**
     * Testet ab ob die Fahrten von einem Benutzer abgefragt werden können.
     */
    @Test
    public void gTestGetTours() {
	ToursResponse tours = remoteSystem.getTours(session2.getId());
	Assert.assertEquals(ReturnCode.OK, tours.getReturnCode());
	List<TourTO> tours2 = tours.getTours();
	Assert.assertEquals(4, tours2.size());
	Assert.assertEquals(locations.get(5).getDescription(), tours2.get(2)
		.getLocation().getDescription());
    }

    /**
     * Testet alle Fehler an die beim Abfragen der Fahrten auftreten können.
     */
    @Test
    public void hTestGetToursFail() {
	// Session existiert nicht
	ToursResponse tours = remoteSystem.getTours(5000);
	Assert.assertEquals(ReturnCode.ERROR, tours.getReturnCode());
    }

    /**
     * Testet ob die Anfragen zu einer Fahrt abgefragt werden können.
     */
    @Test
    public void iTestGetRequestsOfTour() {
	RequestIntegrationService requestIntegrationService = new RequestIntegrationService();
	RequestIntegration remote = requestIntegrationService
		.getRequestIntegrationPort();

	ToursResponse searchTour = remoteSystem
		.searchTours(states.get(0).getId(),
			tour1.getLocation().getId(), tour1.getCapacity()
				.getId(), calendarInOneDay.getTime().getTime(),
			calendarInThreeDays.getTime().getTime(), true, session2
				.getId());
	Assert.assertEquals(1, searchTour.getTours().size());

	TourTO tour = searchTour.getTours().get(0);
	remote.createRequest(tour.getId(), "Test1", session2.getId(),
		"Brot\nBier\n");

	remote.createRequest(tour.getId(), "Test2", session3.getId(),
		"Apfel\nKuchen\n");

	RequestsResponse requestsOfTour = remoteSystem.getRequestsOfTour(
		(int) tour.getId(), session1.getId());
	List<RequestTO> requests = requestsOfTour.getRequests();

	Assert.assertEquals(2, requests.size());
	Assert.assertEquals("Test1", requests.get(0).getNotice());
	Assert.assertEquals("Test2", requests.get(1).getNotice());
    }

    /**
     * Testet alle Fehler die beim Anfrageabfragen auftreten können.
     */
    @Test
    public void jTestGetRequestsOfTourFail() {
	ToursResponse searchTour = remoteSystem
		.searchTours(states.get(0).getId(),
			tour1.getLocation().getId(), tour1.getCapacity()
				.getId(), calendarInOneDay.getTime().getTime(),
			calendarInThreeDays.getTime().getTime(), true, session2
				.getId());
	Assert.assertEquals(1, searchTour.getTours().size());

	TourTO tour = searchTour.getTours().get(0);

	// Session existiert nicht
	RequestsResponse requestsOfTour = remoteSystem.getRequestsOfTour(
		(int) tour.getId(), 5000);
	Assert.assertEquals(ReturnCode.ERROR, requestsOfTour.getReturnCode());

	// Tour existiert nicht
	requestsOfTour = remoteSystem.getRequestsOfTour(5000, session1.getId());
	Assert.assertEquals(ReturnCode.ERROR, requestsOfTour.getReturnCode());

	// Session existiert nicht
	requestsOfTour = remoteSystem.getRequestsOfTour((int) tour.getId(),
		session3.getId());
	Assert.assertEquals(ReturnCode.PERMISSION_DENIED,
		requestsOfTour.getReturnCode());
    }

    /**
     * Testet ob alle aktualisierten Fahrten abgefragt werden können.
     */
    @Test
    public void kTestGetUpdateTourSuccess() {
	ToursResponse updatedTours = remoteSystem.getUpdatedTours(session2
		.getId());
	Assert.assertEquals(1, updatedTours.getTours().size());
    }

    /**
     * Testet alle Fehler an die beim abfragen der Aktualisierten Anfragen
     * auftreten können.
     */
    @Test
    public void TestGetUpdateTourFail() {
	// Session existiert nicht
	ToursResponse updatedTours = remoteSystem.getUpdatedTours(5000);
	Assert.assertEquals(ReturnCode.ERROR, updatedTours.getReturnCode());
    }
}
