package de.shelp.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.shelp.integration.AllListResponse;
import de.shelp.integration.ApprovalStatus;
import de.shelp.integration.ApprovalStatusResponse;
import de.shelp.integration.CapacitiesResponse;
import de.shelp.integration.Capacity;
import de.shelp.integration.DeliveryCondition;
import de.shelp.integration.DeliveryConditionResponse;
import de.shelp.integration.LocationResponse;
import de.shelp.integration.LocationTO;
import de.shelp.integration.PaymentCondition;
import de.shelp.integration.PaymentConditionsResponse;
import de.shelp.integration.StateIntegration;
import de.shelp.integration.StateIntegrationService;

public class StateIntegrationTest {

    private static StateIntegration remoteSystem;
    private ArrayList<String> locationValues;

    @Before
    public void initTestCase() {
	StateIntegrationService service = new StateIntegrationService();
	remoteSystem = service.getStateIntegrationPort();

	locationValues = new ArrayList<String>();
	locationValues.add("Lidl York Center");
	locationValues.add("Aldi York Center");
	locationValues.add("Saturn York Center");
	locationValues.add("Saturn Arkaden");
	locationValues.add("Lidl Friedrich-Ebert-Straﬂe");
	locationValues.add("Toom Richtung Hiltrup");
    }

    @Test
    public void testApprovalStatus() {
	ApprovalStatusResponse approvalStatus = remoteSystem.getApprovalStatus();
	checkApprovalStatus(approvalStatus.getStates());
    }

    private void checkApprovalStatus(List<ApprovalStatus> list) {
	Assert.assertTrue(list.contains(ApprovalStatus.ALL));
	Assert.assertTrue(list.contains(ApprovalStatus.FRIENDS_ONLY));
    }

    @Test
    public void testCapacities() {
	CapacitiesResponse capacities = remoteSystem.getCapacities();
	checkCapacities(capacities.getCapacities());
    }

    private void checkCapacities(List<Capacity> list) {
	Assert.assertTrue(list.contains(Capacity.LARGE_TRUNK));
	Assert.assertTrue(list.contains(Capacity.MIDDLE_TRUNK));
	Assert.assertTrue(list.contains(Capacity.SMALL_TRUNK));
    }

    @Test
    public void testDeliveryCondition() {
	DeliveryConditionResponse conditions = remoteSystem.getDeliveryConditions();
	checkDeliveryCondition(conditions.getConditions());
    }

    private void checkDeliveryCondition(List<DeliveryCondition> list) {
	Assert.assertTrue(list.contains(DeliveryCondition.BRING));
	Assert.assertTrue(list.contains(DeliveryCondition.PICKUP));
    }

    @Test
    public void testPaymentCondition() {
	PaymentConditionsResponse conditions = remoteSystem.getPaymentConditions();
	checkPaymentCondition(conditions.getConditions());
    }

    private void checkPaymentCondition(List<PaymentCondition> list) {
	Assert.assertTrue(list.contains(PaymentCondition.CASH));
	Assert.assertTrue(list.contains(PaymentCondition.CASH_IN_ADVANCE));
    }

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
