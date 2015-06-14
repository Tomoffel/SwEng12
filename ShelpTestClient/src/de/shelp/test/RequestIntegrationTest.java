package de.shelp.test;

import java.util.ArrayList;
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
import de.shelp.integration.LocationTO;
import de.shelp.integration.PaymentConditionTO;
import de.shelp.integration.RequestIntegration;
import de.shelp.integration.RequestIntegrationService;
import de.shelp.integration.RequestStatus;
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
import de.shelp.integration.WishlistItemTO;

/**
 * Testet alle Webservice-Schnittstellen zur TourIntegration.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RequestIntegrationTest {

    private static TourTO tour1 = new TourTO();
    private static TourTO tour2 = new TourTO();
    private static TourTO tour3 = new TourTO();
    private static TourTO tour4 = new TourTO();

    private static List<LocationTO> locations;

    private static ShelpSessionTO session1;

    private static GregorianCalendar calendarInTwoDays;

    private static GregorianCalendar calendarInThreeDays;

    private static GregorianCalendar calendarInOneDay;

    private static GregorianCalendar calendarInFourDays;

    private static ShelpSessionTO session2;

    private static List<CapacityTO> capacities;

    private static List<DeliveryConditionTO> deliveryConditions;

    private static List<PaymentConditionTO> paymentConditions;

    private static List<ApprovalStatusTO> states;
    private static TourIntegration tourIntegrationPort;
    private static RequestIntegration remote;

    private static List<String> wishes = new ArrayList<String>();

    /**
     * Baut einmalig die Verbindung zum Server auf setzt wichtige
     * Klassenvariablen für die Testfälle
     */
    @BeforeClass
    public static void initTestCase() {
	RequestIntegrationService requestIntegrationService = new RequestIntegrationService();
	remote = requestIntegrationService.getRequestIntegrationPort();

	TourIntegrationService tourIntegrationService = new TourIntegrationService();
	tourIntegrationPort = tourIntegrationService.getTourIntegrationPort();

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

	tour3.setApprovalStatus(states.get(0));
	tour3.setLocation(locations.get(1));
	tour3.setCapacity(capacities.get(2));
	tour3.setPaymentCondition(paymentConditions.get(2));
	tour3.setDeliveryCondition(deliveryConditions.get(1));
	tour3.setTime(calendarInTwoDays.getTime().getTime());

	tour4.setApprovalStatus(states.get(0));
	tour4.setLocation(locations.get(2));
	tour4.setCapacity(capacities.get(2));
	tour4.setPaymentCondition(paymentConditions.get(2));
	tour4.setDeliveryCondition(deliveryConditions.get(1));
	tour4.setTime(calendarInTwoDays.getTime().getTime());

	tour2.setApprovalStatus(states.get(0));
	tour2.setLocation(locations.get(3));
	tour2.setCapacity(capacities.get(2));
	tour2.setPaymentCondition(paymentConditions.get(2));
	tour2.setDeliveryCondition(deliveryConditions.get(1));
	tour2.setTime(calendarInTwoDays.getTime().getTime());

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

	tourIntegrationPort.createTour(tour1.getApprovalStatus().getId(), tour1
		.getLocation().getId(), tour1.getCapacity().getId(), tour1
		.getPaymentCondition().getId(), tour1.getDeliveryCondition()
		.getId(), tour1.getTime(), session1.getId());

	tourIntegrationPort.createTour(tour2.getApprovalStatus().getId(), tour2
		.getLocation().getId(), tour2.getCapacity().getId(), tour2
		.getPaymentCondition().getId(), tour2.getDeliveryCondition()
		.getId(), tour2.getTime(), session1.getId());

	tourIntegrationPort.createTour(tour3.getApprovalStatus().getId(), tour3
		.getLocation().getId(), tour3.getCapacity().getId(), tour3
		.getPaymentCondition().getId(), tour1.getDeliveryCondition()
		.getId(), tour3.getTime(), session2.getId());

	tourIntegrationPort.createTour(tour4.getApprovalStatus().getId(), tour4
		.getLocation().getId(), tour4.getCapacity().getId(), tour4
		.getPaymentCondition().getId(), tour4.getDeliveryCondition()
		.getId(), tour4.getTime(), session2.getId());

	wishes.add("Eis");
	wishes.add("Bier");
	wishes.add("Kartoffel");
    }

    /**
     * 
     */
    @Test
    public void aTestCreateRequestSuccess() {
	ToursResponse searchTour = tourIntegrationPort
		.searchTours(states.get(0).getId(),
			tour1.getLocation().getId(), tour1.getCapacity()
				.getId(), calendarInOneDay.getTime().getTime(),
			calendarInThreeDays.getTime().getTime(), true, session1
				.getId());
	Assert.assertEquals(1, searchTour.getTours().size());
	TourTO tour = searchTour.getTours().get(0);

	String wishesString = "";
	for (String string : wishes) {
	    wishesString += string + "\n";
	}

	ReturnCodeResponse createRequest = remote.createRequest(tour.getId(),
		"Test1", session2.getId(), wishesString);
	Assert.assertEquals(ReturnCode.OK, createRequest.getReturnCode());
    }

    @Test
    public void bTestCreateRequestFailed() {

	ToursResponse searchTour = tourIntegrationPort
		.searchTours(states.get(0).getId(),
			tour1.getLocation().getId(), tour1.getCapacity()
				.getId(), calendarInOneDay.getTime().getTime(),
			calendarInThreeDays.getTime().getTime(), true, session1
				.getId());
	Assert.assertEquals(1, searchTour.getTours().size());
	TourTO tour = searchTour.getTours().get(0);

	// Sich selbst eine Anfrage stellen
	ReturnCodeResponse createRequest = remote.createRequest(tour.getId(),
		"Geht nicht", session1.getId(), "bla");

	Assert.assertEquals(createRequest.getReturnCode(), ReturnCode.ERROR);
	Assert.assertEquals(createRequest.getMessage(),
		"Sich selbst eine Anfrage senden, ergibt doch gar keinen Sinn lieber "
			+ session1.getUser().getEmail());

	// Session existiert nicht
	createRequest = remote.createRequest(tour.getId(), "Kommentar",
		56456456, "bla");
	Assert.assertEquals(createRequest.getReturnCode(), ReturnCode.ERROR);
	Assert.assertEquals(createRequest.getMessage(),
		"Session-Id existiert nicht.");

	// Fahrt existiert nicht
	createRequest = remote.createRequest(50000, "Kommentar",
		session2.getId(), "bla");
	Assert.assertEquals(createRequest.getReturnCode(), ReturnCode.ERROR);
	Assert.assertEquals(createRequest.getMessage(),
		"Fahrt existiert nicht.");
    }

    @Test
    public void cTestGetRequestsSuccess() {
	ToursResponse searchTour = tourIntegrationPort
		.searchTours(states.get(0).getId(),
			tour2.getLocation().getId(), tour2.getCapacity()
				.getId(), calendarInOneDay.getTime().getTime(),
			calendarInThreeDays.getTime().getTime(), true, session2
				.getId());
	Assert.assertEquals(1, searchTour.getTours().size());
	TourTO tour = searchTour.getTours().get(0);
	remote.createRequest(tour.getId(), "Test2", session2.getId(), "bla\n");

	RequestsResponse requests = remote.getRequests(session2.getId());
	Assert.assertEquals(ReturnCode.OK, requests.getReturnCode());

	List<RequestTO> requests2 = requests.getRequests();
	Assert.assertEquals(2, requests2.size());
	Assert.assertEquals("Test1", requests2.get(0).getNotice());
	Assert.assertEquals("Test2", requests2.get(1).getNotice());

	List<WishlistItemTO> wishes2 = requests2.get(0).getWishes();
	ArrayList<String> remoteWishes = new ArrayList<String>();
	for (WishlistItemTO wishlistItemTO : wishes2) {
	    remoteWishes.add(wishlistItemTO.getText());
	}

	Assert.assertArrayEquals(wishes.toArray(new String[wishes.size()]),
		remoteWishes.toArray(new String[remoteWishes.size()]));
    }

    @Test
    public void dTestGetRequestsFailed() {
	RequestsResponse requests = remote.getRequests(5000);
	Assert.assertEquals(ReturnCode.ERROR, requests.getReturnCode());
    }

    @Test
    public void eTestAcceptRequestsPartlySuccess() {
	RequestsResponse requests = remote.getRequests(session2.getId());
	List<RequestTO> requests2 = requests.getRequests();

	RequestTO request = requests2.get(0);

	WishlistItemTO id = request.getWishes().get(0);

	ReturnCodeResponse acceptRequest = remote.acceptRequest(
		request.getId(), String.valueOf(id.getId()), session1.getId());
	Assert.assertEquals(ReturnCode.OK, acceptRequest.getReturnCode());

	requests = remote.getRequests(session2.getId());
	request = requests2.get(0);
	id = request.getWishes().get(0);
	Assert.assertEquals(true, id.isChecked());
	Assert.assertEquals(RequestStatus.PARTLY_ACCEPT, request.getStatus());
    }

    @Test
    public void fTestAcceptRequestsSuccess() {
	RequestsResponse requests = remote.getRequests(session2.getId());
	List<RequestTO> requests2 = requests.getRequests();

	RequestTO request = requests2.get(1);

	WishlistItemTO id = request.getWishes().get(0);

	ReturnCodeResponse acceptRequest = remote.acceptRequest(
		request.getId(), String.valueOf(id.getId()), session1.getId());
	Assert.assertEquals(ReturnCode.OK, acceptRequest.getReturnCode());

	requests = remote.getRequests(session2.getId());
	request = requests2.get(1);
	id = request.getWishes().get(0);
	Assert.assertEquals(true, id.isChecked());
	Assert.assertEquals(RequestStatus.ACCECPT, request.getStatus());
    }

    @Test
    public void gTestDeniedRequestsSuccess() {
	ToursResponse searchTour = tourIntegrationPort
		.searchTours(states.get(0).getId(),
			tour3.getLocation().getId(), tour3.getCapacity()
				.getId(), calendarInOneDay.getTime().getTime(),
			calendarInThreeDays.getTime().getTime(), true, session2
				.getId());
	Assert.assertEquals(1, searchTour.getTours().size());

	TourTO tour = searchTour.getTours().get(0);
	remote.createRequest(tour.getId(), "Test3", session1.getId(),
		"bla\nund\nso\n");

	RequestsResponse requests = remote.getRequests(session1.getId());
	List<RequestTO> requests2 = requests.getRequests();

	RequestTO request = requests2.get(0);

	ReturnCodeResponse acceptRequest = remote.acceptRequest(
		request.getId(), "", session2.getId());
	Assert.assertEquals(ReturnCode.OK, acceptRequest.getReturnCode());

	requests = remote.getRequests(session1.getId());
	request = requests2.get(1);
	Assert.assertEquals(RequestStatus.DENIED, request.getStatus());
    }

    @Test
    public void hTestAcceptRequestsFailed() {
	RequestsResponse requests = remote.getRequests(session1.getId());
	List<RequestTO> requests2 = requests.getRequests();

	RequestTO request = requests2.get(0);

	// Anfrage existiert nicht
	ReturnCodeResponse acceptRequest = remote.acceptRequest(5000,
		"0\n1\n2\n", session2.getId());
	Assert.assertEquals(ReturnCode.ERROR, acceptRequest.getReturnCode());

	// Session existiert nicht
	acceptRequest = remote
		.acceptRequest(request.getId(), "0\n1\n2\n", 5000);
	Assert.assertEquals(ReturnCode.ERROR, acceptRequest.getReturnCode());

	// Annahme von falscher Session
	acceptRequest = remote.acceptRequest(request.getId(), "0\n1\n2\n",
		session1.getId());
	Assert.assertEquals(ReturnCode.ERROR, acceptRequest.getReturnCode());
	
	//Anfrage wurde schon angenommen / abgelehnt
	acceptRequest = remote.acceptRequest(request.getId(), "0\n1\n2\n",
		session2.getId());
	Assert.assertEquals(ReturnCode.ERROR, acceptRequest.getReturnCode());
    }

}