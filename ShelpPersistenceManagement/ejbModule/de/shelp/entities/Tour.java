package de.shelp.entities;

import java.util.Date;
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

import de.shelp.enums.TourStatus;

@Entity
public class Tour {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "approval_status_id")
    private ApprovalStatus approvalStatus;
    
    @ManyToOne
    @JoinColumn(name = "capacity_id")
    private Capacity capacity;
    
    @ManyToOne
    @JoinColumn(name = "payment_condition_id")
    private PaymentCondition paymentConditions;
    
    @ManyToOne
    @JoinColumn(name = "devlivery_condition_id")
    private DeliveryCondition deliveryConditions;
    
    @Enumerated(EnumType.ORDINAL)
    private TourStatus status;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "tour")
    private List<Request> request;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    private Date updatedOn;
    private Date time;

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

    public Date getTime() {
	return time;
    }

    public void setTime(Date time) {
	this.time = time;
    }

    public User getOwner() {
	return owner;
    }

    public void setOwner(User owner) {
	this.owner = owner;
    }

    public Date getUpdatedOn() {
	return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
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

    @Override
    public String toString() {
        return "Fahrt: " + id + " zu " + location + " von " + owner ;
    }
    
    public boolean isValid() {
	return approvalStatus != null && location != null && capacity != null && paymentConditions != null && deliveryConditions != null && time != null;
    }
    
}
