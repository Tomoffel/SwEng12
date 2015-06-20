package de.shelp.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import de.shelp.integration.AllListResponse;
import de.shelp.integration.ApprovalStatusResponse;
import de.shelp.integration.ApprovalStatusTO;
import de.shelp.integration.CapacitiesResponse;
import de.shelp.integration.CapacityTO;
import de.shelp.integration.DeliveryConditionResponse;
import de.shelp.integration.DeliveryConditionTO;
import de.shelp.integration.LocationResponse;
import de.shelp.integration.LocationTO;
import de.shelp.integration.PaymentConditionTO;
import de.shelp.integration.PaymentConditionsResponse;
import de.shelp.integration.StateIntegration;
import de.shelp.integration.StateIntegrationService;

/**
 * Testet alle Webservice-Schnittstellen zur StateIntegration.
 * 
 * @author Thomas Sennekamp
 */
public class StateIntegrationTest {

    private static StateIntegration remoteSystem;
    private static ArrayList<String> locationValues;
    private static ArrayList<String> statesValues;
    private static ArrayList<String> capacityValues;
    private static ArrayList<String> deliveryValues;
    private static ArrayList<String> paymentValues;

    /**
     * Baut einmalig die Verbindung zum Server auf und legt alle benötigten Locations in einer Liste an.
     */
    @BeforeClass
    public static void initTestCase() {
	StateIntegrationService service = new StateIntegrationService();
	remoteSystem = service.getStateIntegrationPort();

	locationValues = new ArrayList<String>();
	locationValues.add("Lidl York Center");
	locationValues.add("Aldi York Center");
	locationValues.add("Saturn York Center");
	locationValues.add("Saturn Arkaden");
	locationValues.add("Lidl Friedrich-Ebert-Straße");
	locationValues.add("Toom Richtung Hiltrup");
	
	statesValues = new ArrayList<String>();
	statesValues.add("Alle");
	statesValues.add("Nur Freunde");
	
	capacityValues = new ArrayList<String>();
	capacityValues.add("Kleiner Kofferraum");
	capacityValues.add("Mittlerer Kofferraum");
	capacityValues.add("Großer Kofferraum");
	
	deliveryValues = new ArrayList<String>();
	deliveryValues.add("Abholen");
	deliveryValues.add("Bringen");
	
	paymentValues = new ArrayList<String>();
	paymentValues.add("Barzahlung");
	paymentValues.add("Vorkasse");
	paymentValues.add("PayPal");
    }

    /**
     * Testet ob der Freigabestatus abgefragt werden kann.
     */
    @Test
    public void testApprovalStatus() {
	ApprovalStatusResponse approvalStatus = remoteSystem.getApprovalStatus();
	checkApprovalStatus(approvalStatus.getStates());
    }

    private void checkApprovalStatus(List<ApprovalStatusTO> list) {
	for (ApprovalStatusTO approvalStatusTO : list) {
	    Assert.assertTrue(statesValues.contains(approvalStatusTO.getDescription()));
	}
    }

    /**
     * Testet ob der Kapazität abgefragt werden kann.
     */
    @Test
    public void testCapacities() {
	CapacitiesResponse capacities = remoteSystem.getCapacities();
	checkCapacities(capacities.getCapacities());
    }

    private void checkCapacities(List<CapacityTO> list) {
	for (CapacityTO capacityTO : list) {
	    Assert.assertTrue(capacityValues.contains(capacityTO.getDescription()));
	}
    }

    /**
     * Testet ob der Lieferbedingungen abgefragt werden können.
     */
    @Test
    public void testDeliveryCondition() {
	DeliveryConditionResponse conditions = remoteSystem.getDeliveryConditions();
	checkDeliveryCondition(conditions.getConditions());
    }

    private void checkDeliveryCondition(List<DeliveryConditionTO> list) {
	for (DeliveryConditionTO deliveryTO : list) {
	    Assert.assertTrue(deliveryValues.contains(deliveryTO.getDescription()));
	}
    }

    /**
     * Testet ob der Bezahlmethoden abgefragt werden können.
     */
    @Test
    public void testPaymentCondition() {
	PaymentConditionsResponse conditions = remoteSystem.getPaymentConditions();
	checkPaymentCondition(conditions.getConditions());
    }

    private void checkPaymentCondition(List<PaymentConditionTO> list) {
	for (PaymentConditionTO paymentTO : list) {
	    Assert.assertTrue(paymentValues.contains(paymentTO.getDescription()));
	}
    }

    /**
     * Testet ob der Lokations abgefragt werden können.
     */
    @Test
    public void testLocation() {
	LocationResponse locations = remoteSystem.getLocations();
	checkLocation(locations.getLocations());
    }

    private void checkLocation(List<LocationTO> list) {
	for (LocationTO locationTO : list) {
	    Assert.assertTrue(locationValues.contains(locationTO.getDescription()));
	}
    }

    /**
     * Testet ob alle Statusse in einmal abgefragt werden kann.
     */
    @Test
    public void testAll() {
	AllListResponse allLists = remoteSystem.getAllLists();
	checkApprovalStatus(allLists.getStates());
	checkCapacities(allLists.getCapacities());
	checkDeliveryCondition(allLists.getDeliveryConditions());
	checkLocation(allLists.getLocations());
	checkPaymentCondition(allLists.getPaymentConditions());

    }

}
