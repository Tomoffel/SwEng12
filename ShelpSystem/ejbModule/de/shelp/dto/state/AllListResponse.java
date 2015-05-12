package de.shelp.dto.state;

import java.util.List;

import de.shelp.dto.ReturnCodeResponse;
import de.shelp.enums.ApprovalStatus;
import de.shelp.enums.Capacity;
import de.shelp.enums.DeliveryCondition;
import de.shelp.enums.PaymentCondition;

public class AllListResponse extends ReturnCodeResponse {

    private static final long serialVersionUID = -3883979253357422621L;

    private ApprovalStatus[] states = ApprovalStatus.values();
    private Capacity[] capacities = Capacity.values();
    private DeliveryCondition[] deliveryConditions = DeliveryCondition.values();
    private PaymentCondition[] paymentConditions = PaymentCondition.values();
    private List<LocationTO> locations;

    public List<LocationTO> getLocations() {
	return locations;
    }

    public void setLocations(List<LocationTO> locations) {
	this.locations = locations;
    }

    public ApprovalStatus[] getStates() {
	return states;
    }

    public Capacity[] getCapacities() {
	return capacities;
    }

    public DeliveryCondition[] getDeliveryConditions() {
	return deliveryConditions;
    }

    public PaymentCondition[] getPaymentConditions() {
	return paymentConditions;
    }

    public void setStates(ApprovalStatus[] states) {
	this.states = states;
    }

    public void setCapacities(Capacity[] capacities) {
	this.capacities = capacities;
    }

    public void setDeliveryConditions(DeliveryCondition[] deliveryConditions) {
	this.deliveryConditions = deliveryConditions;
    }

    public void setPaymentConditions(PaymentCondition[] paymentConditions) {
	this.paymentConditions = paymentConditions;
    }

}
