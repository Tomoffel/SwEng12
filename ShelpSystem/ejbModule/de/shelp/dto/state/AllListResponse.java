package de.shelp.dto.state;

import java.util.List;

import de.shelp.dto.ReturnCodeResponse;
import de.shelp.enums.ApprovalStatus;
import de.shelp.enums.Capacity;
import de.shelp.enums.DeliveryCondition;
import de.shelp.enums.PaymentCondition;

public class AllListResponse extends ReturnCodeResponse {

    private static final long serialVersionUID = -3883979253357422621L;
    
    private final ApprovalStatus[] states = ApprovalStatus.values();
    private final Capacity[] capacities = Capacity.values();
    private final DeliveryCondition[] deliveryConditions = DeliveryCondition.values();
    private final PaymentCondition[] paymentConditions = PaymentCondition.values();
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

}
