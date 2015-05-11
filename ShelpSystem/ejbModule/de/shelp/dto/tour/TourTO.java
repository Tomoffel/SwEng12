package de.shelp.dto.tour;

import java.util.Calendar;
import java.util.List;

import de.shelp.dto.request.RequestTO;
import de.shelp.dto.state.LocationTO;
import de.shelp.dto.user.UserTO;
import de.shelp.enums.ApprovalStatus;
import de.shelp.enums.Capacity;
import de.shelp.enums.DeliveryCondition;
import de.shelp.enums.PaymentCondition;
import de.shelp.enums.TourStatus;

public class TourTO {

    private long id;
    private ApprovalStatus approvalStatus;
    private LocationTO location;
    private Capacity capacity;
    private PaymentCondition paymentConditions;
    private DeliveryCondition deliveryConditions;
    private Calendar time;
    private List<RequestTO> request;
    private UserTO owner;
    private Calendar updatedOn;
    private TourStatus status;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public ApprovalStatus getApprovalStatus() {
	return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
	this.approvalStatus = approvalStatus;
    }

    public LocationTO getLocation() {
	return location;
    }

    public void setLocation(LocationTO location) {
	this.location = location;
    }

    public Capacity getCapacity() {
	return capacity;
    }

    public void setCapacity(Capacity capacity) {
	this.capacity = capacity;
    }

    public PaymentCondition getPaymentConditions() {
	return paymentConditions;
    }

    public void setPaymentConditions(PaymentCondition paymentConditions) {
	this.paymentConditions = paymentConditions;
    }

    public DeliveryCondition getDeliveryConditions() {
	return deliveryConditions;
    }

    public void setDeliveryConditions(DeliveryCondition deliveryConditions) {
	this.deliveryConditions = deliveryConditions;
    }

    public Calendar getTime() {
	return time;
    }

    public void setTime(Calendar time) {
	this.time = time;
    }

    public UserTO getOwner() {
	return owner;
    }

    public void setOwner(UserTO owner) {
	this.owner = owner;
    }

    public Calendar getUpdatedOn() {
	return updatedOn;
    }

    public void setUpdatedOn(Calendar updatedOn) {
	this.updatedOn = updatedOn;
    }

    public List<RequestTO> getRequest() {
	return request;
    }

    public void setRequest(List<RequestTO> request) {
	this.request = request;
    }

    public TourStatus getStatus() {
	return status;
    }

    public void setStatus(TourStatus status) {
	this.status = status;
    }

    public boolean isValid() {
	return approvalStatus != null && location != null && capacity != null && paymentConditions != null && deliveryConditions != null && time != null;
    }

}
