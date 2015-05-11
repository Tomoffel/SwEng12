package de.shelp.entities;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import de.shelp.enums.RequestStatus;

//@Entity
public class Request {

    @Id
    @GeneratedValue
    private long id;
    private User sourceUser;
    private User targetUser;

    @ManyToOne
    private Tour tour;
//    private Wishlist wishlist;
    private String notice;
    private Calendar updatedOn;

    @Enumerated(EnumType.ORDINAL)
    private RequestStatus status;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public User getSourceUser() {
	return sourceUser;
    }

    public void setSourceUser(User sourceUser) {
	this.sourceUser = sourceUser;
    }

    public User getTargetUser() {
	return targetUser;
    }

    public void setTargetUser(User targetUser) {
	this.targetUser = targetUser;
    }

    public Tour getTour() {
	return tour;
    }

    public void setTour(Tour tour) {
	this.tour = tour;
    }

//    public Wishlist getWishlist() {
//	return wishlist;
//    }
//
//    public void setWishlist(Wishlist wishlist) {
//	this.wishlist = wishlist;
//    }

    public String getNotice() {
	return notice;
    }

    public void setNotice(String notice) {
	this.notice = notice;
    }

    public Calendar getUpdatedOn() {
	return updatedOn;
    }

    public void setUpdatedOn(Calendar updatedOn) {
	this.updatedOn = updatedOn;
    }

    public RequestStatus getStatus() {
	return status;
    }

    public void setStatus(RequestStatus status) {
	this.status = status;
    }

}
