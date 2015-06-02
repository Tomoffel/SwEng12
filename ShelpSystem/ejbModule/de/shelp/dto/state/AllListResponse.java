package de.shelp.dto.state;

import java.util.List;

import de.shelp.dto.ReturnCodeResponse;

public class AllListResponse extends ReturnCodeResponse {

    private static final long serialVersionUID = -3883979253357422621L;

    private List<ApprovalStatusTO> states;
    private List<CapacityTO> capacities;
    private List<DeliveryConditionTO> deliveryConditions;
    private List<PaymentConditionTO> paymentConditions;
    private List<LocationTO> locations;

    public List<LocationTO> getLocations() {
	return locations;
    }

    public void setLocations(List<LocationTO> locations) {
	this.locations = locations;
    }

    public List<ApprovalStatusTO> getStates() {
	return states;
    }

    public List<CapacityTO> getCapacities() {
	return capacities;
    }

    public List<DeliveryConditionTO> getDeliveryConditions() {
	return deliveryConditions;
    }

    public List<PaymentConditionTO> getPaymentConditions() {
	return paymentConditions;
    }

    public void setStates(List<ApprovalStatusTO> list) {
	this.states = list;
    }

    public void setCapacities(List<CapacityTO> list) {
	this.capacities = list;
    }

    public void setDeliveryConditions(List<DeliveryConditionTO> list) {
	this.deliveryConditions = list;
    }

    public void setPaymentConditions(List<PaymentConditionTO> list) {
	this.paymentConditions = list;
    }

}
