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
import de.shelp.integration.ReturnCode;
import de.shelp.integration.ReturnCodeResponse;
import de.shelp.integration.ShelpSessionTO;
import de.shelp.integration.StateIntegration;
import de.shelp.integration.StateIntegrationService;
import de.shelp.integration.TourIntegration;
import de.shelp.integration.TourIntegrationService;
import de.shelp.integration.TourResponse;
import de.shelp.integration.TourTO;
import de.shelp.integration.ToursResponse;
import de.shelp.integration.UserIntegration;
import de.shelp.integration.UserIntegrationService;
import de.shelp.integration.UserResponse;

/**
 * Testet alle Webservice-Schnittstellen zur TourIntegration.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RequestIntegrationTest {

    private static TourTO tour1 = new TourTO();
    private static TourTO tour2 = new TourTO();
    private static TourTO tour3 = new TourTO();
    private static TourTO tour4 = new TourTO();
    private static TourTO tour5 = new TourTO();

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
    }

    /**
     * Testet, ob eine neue Fahrt erstellt werden kann. Erwartet ein OK.
     */
    @Test
    public void aTestCreateTour() {
	ReturnCodeResponse createTour = tourIntegrationPort.createTour(tour1
		.getApprovalStatus().getId(), tour1.getLocation().getId(),
		tour1.getCapacity().getId(), tour1.getPaymentCondition()
			.getId(), tour1.getDeliveryCondition().getId(), tour1
			.getTime(), session1.getId());
	Assert.assertEquals(ReturnCode.OK, createTour.getReturnCode());
    }

    @Test
    /*
     * Erwarte, das TargetUser nicht existiert
     */
    public void bTestCreateRequest() {
	ToursResponse searchTour = tourIntegrationPort.searchTours(states
		.get(0).getId(), locations.get(0).getId(), capacities.get(1)
		.getId(), calendarInOneDay.getTime().getTime(),
		calendarInThreeDays.getTime().getTime(), false, session1
			.getId());

	TourResponse tour = tourIntegrationPort.getTour(searchTour.getTours()
		.get(0).getId(), session1.getId());

	ArrayList<String> wishes = new ArrayList<String>();
	wishes.add("Test");
	
	// oben musste noch der remote zugriff hinzugefügt werden (wie bei Tours nur für Request)
	ReturnCodeResponse createRequest = remote.createRequest(session1.getUser().getEmail(), tour.getTour().getId(),
		wishes, "Kommentar", session1.getId());

	Assert.assertEquals(createRequest.getReturnCode(), ReturnCode.ERROR);
    }

}
