package de.shelp.dto.tour;

import java.util.Date;
import java.util.List;

import de.shelp.dto.request.RequestTO;
import de.shelp.dto.state.ApprovalStatusTO;
import de.shelp.dto.state.CapacityTO;
import de.shelp.dto.state.DeliveryConditionTO;
import de.shelp.dto.state.LocationTO;
import de.shelp.dto.state.PaymentConditionTO;
import de.shelp.dto.user.UserTO;
import de.shelp.enums.TourStatus;

public class TourTO {

    private long id;
    private ApprovalStatusTO approvalStatus;
    private LocationTO location;
    private CapacityTO capacity;
    private PaymentConditionTO paymentCondition;
    private DeliveryConditionTO deliveryCondition;
    private long time;
    private List<RequestTO> request;
    private UserTO owner;
    private Date updatedOn;
    private TourStatus status;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public ApprovalStatusTO getApprovalStatus() {
	return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatusTO approvalStatus) {
	this.approvalStatus = approvalStatus;
    }

    public LocationTO getLocation() {
	return location;
    }

    public void setLocation(LocationTO location) {
	this.location = location;
    }

    public CapacityTO getCapacity() {
	return capacity;
    }

    public void setCapacity(CapacityTO capacity) {
	this.capacity = capacity;
    }

    public PaymentConditionTO getPaymentCondition() {
	return paymentCondition;
    }

    public void setPaymentCondition(PaymentConditionTO paymentCondition) {
	this.paymentCondition = paymentCondition;
    }

    public DeliveryConditionTO getDeliveryCondition() {
	return deliveryCondition;
    }

    public void setDeliveryCondition(DeliveryConditionTO deliveryCondition) {
	this.deliveryCondition = deliveryCondition;
    }

    public long getTime() {
	return time;
    }

    public void setTime(long time) {
	this.time = time;
    }

    public UserTO getOwner() {
	return owner;
    }

    public void setOwner(UserTO owner) {
	this.owner = owner;
    }

    public Date getUpdatedOn() {
	return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
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
	return approvalStatus != null && location != null && capacity != null && paymentCondition != null && deliveryCondition != null;
    }

}
