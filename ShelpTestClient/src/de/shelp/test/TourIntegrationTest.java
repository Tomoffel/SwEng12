package de.shelp.test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.shelp.integration.ApprovalStatus;
import de.shelp.integration.Capacity;
import de.shelp.integration.DeliveryCondition;
import de.shelp.integration.LocationTO;
import de.shelp.integration.PaymentCondition;
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

public class TourIntegrationTest {

    private static TourIntegration remoteSystem;

    private TourTO tour1 = new TourTO();
    private TourTO tour2 = new TourTO();
    private TourTO tour3 = new TourTO();
    private TourTO tour4 = new TourTO();
    private TourTO tour5 = new TourTO();

    private List<LocationTO> locations;

    private ShelpSessionTO session1;

    private GregorianCalendar calendarInTwoDays;

    private GregorianCalendar calendarInThreeDays;

    private GregorianCalendar calendarInOneDay;

    private GregorianCalendar calendarInFourDays;

    private ShelpSessionTO session2;

    @Before
    public void initTestCase() {
	TourIntegrationService service = new TourIntegrationService();
	remoteSystem = service.getTourIntegrationPort();

	UserIntegrationService userService = new UserIntegrationService();
	UserIntegration userIntegrationPort = userService.getUserIntegrationPort();

	StateIntegrationService stateService = new StateIntegrationService();
	StateIntegration stateRemoteSystem = stateService.getStateIntegrationPort();

	locations = stateRemoteSystem.getLocations().getLocations();
	calendarInTwoDays = new GregorianCalendar();
	calendarInTwoDays.add(Calendar.DAY_OF_MONTH, 2);

	calendarInThreeDays = new GregorianCalendar();
	calendarInThreeDays.add(Calendar.DAY_OF_MONTH, 3);

	calendarInOneDay = new GregorianCalendar();
	calendarInOneDay.add(Calendar.DAY_OF_MONTH, 1);

	calendarInFourDays = new GregorianCalendar();
	calendarInFourDays.add(Calendar.DAY_OF_MONTH, 4);

	tour1.setApprovalStatus(ApprovalStatus.ALL);
	tour1.setLocation(locations.get(0));
	tour1.setCapacity(Capacity.LARGE_TRUNK);
	tour1.setPaymentConditions(PaymentCondition.CASH);
	tour1.setDeliveryConditions(DeliveryCondition.BRING);
	tour1.setTime(calendarToXMLGregorianCalendar(calendarInTwoDays));

	tour2.setLocation(locations.get(2));
	tour2.setPaymentConditions(PaymentCondition.CASH_IN_ADVANCE);

	tour3.setApprovalStatus(ApprovalStatus.ALL);
	tour3.setLocation(locations.get(1));
	tour3.setCapacity(Capacity.MIDDLE_TRUNK);
	tour3.setPaymentConditions(PaymentCondition.CASH);
	tour3.setDeliveryConditions(DeliveryCondition.BRING);
	tour3.setTime(calendarToXMLGregorianCalendar(calendarInTwoDays));

	tour4.setApprovalStatus(ApprovalStatus.ALL);
	tour4.setLocation(locations.get(5));
	tour4.setCapacity(Capacity.MIDDLE_TRUNK);
	tour4.setPaymentConditions(PaymentCondition.CASH);
	tour4.setDeliveryConditions(DeliveryCondition.BRING);
	tour4.setTime(calendarToXMLGregorianCalendar(calendarInTwoDays));

	tour5.setApprovalStatus(ApprovalStatus.ALL);
	tour5.setLocation(locations.get(2));
	tour5.setCapacity(Capacity.MIDDLE_TRUNK);
	tour5.setPaymentConditions(PaymentCondition.CASH);
	tour5.setDeliveryConditions(DeliveryCondition.BRING);
	tour5.setTime(calendarToXMLGregorianCalendar(calendarInFourDays));

	UserResponse loginResponse = userIntegrationPort.regUser("Thomas", "test123", "thomas@sennekamp.de");
	if (loginResponse.getReturnCode() == ReturnCode.ERROR) {
	    loginResponse = userIntegrationPort.login("Thomas", "test123");
	}
	session1 = loginResponse.getSession();

	loginResponse = userIntegrationPort.regUser("Theresa", "test123", "thomas@sennekamp.de");
	if (loginResponse.getReturnCode() == ReturnCode.ERROR) {
	    loginResponse = userIntegrationPort.login("Theresa", "test123");
	}
	session2 = loginResponse.getSession();
    }

    @Test
    public void aTestCreateTour() {
	ReturnCodeResponse createTour = remoteSystem.createTour(tour1, session1.getId());
	Assert.assertEquals(ReturnCode.OK, createTour.getReturnCode());
    }

    @Test
    public void bTestCreateTourNotFull() {
	ReturnCodeResponse createTour = remoteSystem.createTour(tour2, session1.getId());
	Assert.assertEquals(ReturnCode.ERROR, createTour.getReturnCode());
    }

    // TODO check with friendship
    @Test
    public void cTestSearchToursAllDirect() {
	remoteSystem.createTour(tour3, session1.getId());
	remoteSystem.createTour(tour4, session2.getId());
	remoteSystem.createTour(tour5, session2.getId());

	ToursResponse searchTour = remoteSystem.searchTour(ApprovalStatus.ALL, locations.get(0), calendarToXMLGregorianCalendar(calendarInOneDay), calendarToXMLGregorianCalendar(calendarInThreeDays),
		true, session1.getId());

	Assert.assertEquals(searchTour.getTours().size(), 1);
	Assert.assertEquals(searchTour.getTours().get(0).getLocation().getDescription(), locations.get(0).getDescription());
    }

    @Test
    public void cTestGetTour() {
	// ReturnCodeResponse createTour = remoteSystem.createTour(tour1,
	// session1.getId());
	// Assert.assertEquals(ReturnCode.OK, createTour.getReturnCode());
    }

    private static XMLGregorianCalendar calendarToXMLGregorianCalendar(Calendar calendar) {
	try {
	    DatatypeFactory dtf = DatatypeFactory.newInstance();
	    XMLGregorianCalendar xgc = dtf.newXMLGregorianCalendar();
	    xgc.setYear(calendar.get(Calendar.YEAR));
	    xgc.setMonth(calendar.get(Calendar.MONTH) + 1);
	    xgc.setDay(calendar.get(Calendar.DAY_OF_MONTH));
	    xgc.setHour(calendar.get(Calendar.HOUR_OF_DAY));
	    xgc.setMinute(calendar.get(Calendar.MINUTE));
	    xgc.setSecond(calendar.get(Calendar.SECOND));
	    xgc.setMillisecond(calendar.get(Calendar.MILLISECOND));

	    // Calendar ZONE_OFFSET and DST_OFFSET fields are in milliseconds.
	    int offsetInMinutes = (calendar.get(Calendar.ZONE_OFFSET) + calendar.get(Calendar.DST_OFFSET)) / (60 * 1000);
	    xgc.setTimezone(offsetInMinutes);
	    return xgc;
	} catch (DatatypeConfigurationException e) {
	    e.printStackTrace();
	    return null;
	}

    }

}
