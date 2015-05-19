package de.shelp.entities;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import de.shelp.enums.ApprovalStatus;
import de.shelp.enums.Capacity;
import de.shelp.enums.DeliveryCondition;
import de.shelp.enums.PaymentCondition;
import de.shelp.enums.TourStatus;

@Entity
public class Tour {

    @Id
    @GeneratedValue
    private long id;

    @Enumerated(EnumType.ORDINAL)
    private ApprovalStatus approvalStatus;

    @Enumerated(EnumType.ORDINAL)
    private Capacity capacity;

    @Enumerated(EnumType.ORDINAL)
    private PaymentCondition paymentConditions;

    @Enumerated(EnumType.ORDINAL)
    private DeliveryCondition deliveryConditions;

    @Enumerated(EnumType.ORDINAL)
    private TourStatus status;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "tour")
    private List<Request> request;

    @OneToOne
    private Location location;

    private Calendar updatedOn;
    private Calendar time;

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

    public Location getLocation() {
	return location;
    }

    public void setLocation(Location location) {
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

    public User getOwner() {
	return owner;
    }

    public void setOwner(User owner) {
	this.owner = owner;
    }

    public Calendar getUpdatedOn() {
	return updatedOn;
    }

    public void setUpdatedOn(Calendar updatedOn) {
	this.updatedOn = updatedOn;
    }

    public List<Request> getRequest() {
	return request;
    }

    public void setRequest(List<Request> request) {
	this.request = request;
    }

    public TourStatus getStatus() {
	return status;
    }

    public void setStatus(TourStatus status) {
	this.status = status;
    }

}
