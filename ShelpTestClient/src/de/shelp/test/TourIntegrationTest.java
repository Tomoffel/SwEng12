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
import de.shelp.integration.LocationTO;
import de.shelp.integration.PaymentConditionTO;
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
public class TourIntegrationTest {

    private static TourIntegration remoteSystem;

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
	tour1.setPaymentConditions(paymentConditions.get(1));
	tour1.setDeliveryConditions(deliveryConditions.get(0));
	tour1.setTime(calendarInTwoDays.getTime());

	tour2.setLocation(locations.get(2));
	tour2.setPaymentConditions(paymentConditions.get(0));

	tour3.setApprovalStatus(states.get(0));
	tour3.setLocation(locations.get(1));
	tour3.setCapacity(capacities.get(2));
	tour3.setPaymentConditions(paymentConditions.get(2));
	tour3.setDeliveryConditions(deliveryConditions.get(1));
	tour3.setTime(calendarInTwoDays.getTime());

	tour4.setApprovalStatus(states.get(0));
	tour4.setLocation(locations.get(5));
	tour4.setCapacity(capacities.get(2));
	tour4.setPaymentConditions(paymentConditions.get(2));
	tour4.setDeliveryConditions(deliveryConditions.get(1));
	tour4.setTime(calendarInTwoDays.getTime());

	tour5.setApprovalStatus(states.get(0));
	tour5.setLocation(locations.get(2));
	tour5.setCapacity(capacities.get(2));
	tour5.setPaymentConditions(paymentConditions.get(2));
	tour5.setDeliveryConditions(deliveryConditions.get(1));
	tour5.setTime(calendarInFourDays.getTime());

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
     * Testet ob eine neue Fahrt erstellt werden kann. Erwartet ein OK.
     */
    @Test
    public void aTestCreateTour() {
	ReturnCodeResponse createTour = remoteSystem.createTour(tour1
		.getApprovalStatus().getId(), tour1.getLocation().getId(),
		tour1.getCapacity().getId(), tour1.getPaymentConditions()
			.getId(), tour1.getDeliveryConditions().getId(), tour1
			.getTime().getTime(), session1.getId());
	Assert.assertEquals(ReturnCode.OK, createTour.getReturnCode());
    }

    /**
     * Testet ob eine unvollständige Fahrt erstellt werden kann. Erwartet ein
     * ERROR.
     */
    @Test
    public void bTestCreateTourNotFull() {
	ReturnCodeResponse createTour = remoteSystem.createTour(100, 100, 5,
		105, 200, 0L, session1.getId());
	Assert.assertEquals(ReturnCode.ERROR, createTour.getReturnCode());
    }

    // TODO check with friendship
    @Test
    public void cTestSearchToursAllDirect() {
	remoteSystem.createTour(tour3.getApprovalStatus().getId(), tour3
		.getLocation().getId(), tour1.getCapacity().getId(), tour3
		.getPaymentConditions().getId(), tour3.getDeliveryConditions()
		.getId(), tour3.getTime().getTime(), session1.getId());
	remoteSystem.createTour(tour4.getApprovalStatus().getId(), tour4
		.getLocation().getId(), tour1.getCapacity().getId(), tour4
		.getPaymentConditions().getId(), tour4.getDeliveryConditions()
		.getId(), tour4.getTime().getTime(), session2.getId());
	remoteSystem.createTour(tour5.getApprovalStatus().getId(), tour5
		.getLocation().getId(), tour1.getCapacity().getId(), tour5
		.getPaymentConditions().getId(), tour5.getDeliveryConditions()
		.getId(), tour5.getTime().getTime(), session2.getId());

	ToursResponse searchTour = remoteSystem.searchTour(states.get(0)
		.getId(), locations.get(0).getId(), calendarInOneDay.getTime()
		.getTime(), calendarInThreeDays.getTime().getTime(), true,
		session1.getId());

	Assert.assertEquals(searchTour.getTours().size(), 1);
	Assert.assertEquals(searchTour.getTours().get(0).getLocation()
		.getDescription(), locations.get(0).getDescription());
    }

    @Test
    public void dTestSearchToursAllNear() {
	ToursResponse searchTour = remoteSystem.searchTour(states.get(0)
		.getId(), locations.get(0).getId(), calendarInOneDay.getTime()
		.getTime(), calendarInThreeDays.getTime().getTime(), false,
		session1.getId());

	Assert.assertEquals(searchTour.getTours().size(), 2);
	Assert.assertEquals(searchTour.getTours().get(0).getLocation()
		.getDescription(), locations.get(0).getDescription());
	Assert.assertEquals(searchTour.getTours().get(1).getLocation()
		.getDescription(), locations.get(1).getDescription());
    }

    @Test
    public void eTestGetTour() {
	ToursResponse searchTour = remoteSystem.searchTour(states.get(0)
		.getId(), locations.get(0).getId(), calendarInOneDay.getTime()
		.getTime(), calendarInThreeDays.getTime().getTime(), false,
		session1.getId());

	TourResponse tour = remoteSystem.getTour(searchTour.getTours().get(0)
		.getId(), session1.getId());
	Assert.assertEquals(ReturnCode.OK, tour.getReturnCode());
	Assert.assertEquals(tour.getTour().getId(), searchTour.getTours()
		.get(0).getId());
    }

    @Test
    public void fTestGetTourNotExist() {
	TourResponse tour = remoteSystem.getTour(500, session1.getId());
	Assert.assertEquals(ReturnCode.ERROR, tour.getReturnCode());
    }

    // TODO test with friendship
    @Test
    public void gTestGetTourPermissionDenied() {
	ToursResponse searchTour = remoteSystem.searchTour(states.get(0)
		.getId(), locations.get(0).getId(), calendarInOneDay.getTime()
		.getTime(), calendarInThreeDays.getTime().getTime(), false,
		session1.getId());

	// TourResponse tour =
	// remoteSystem.getTour(searchTour.getTours().get(0).getId(),
	// session2.getId());
	// Assert.assertEquals(ReturnCode.PERMISSION_DENIED,
	// tour.getReturnCode());
    }

    // TODO test with request and update

}
