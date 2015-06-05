package de.shelp.dto.request;

import de.shelp.dto.tour.TourTO;
import de.shelp.dto.user.UserTO;
import de.shelp.enums.RequestStatus;

public class RequestTO {

    private long id;
    private UserTO sourceUser;
    private UserTO targetUser;
    private TourTO tour;
    private WishlistTO wishlist;
    private String notice;
    private long updatedOn;
    private RequestStatus status;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public UserTO getSourceUser() {
	return sourceUser;
    }

    public void setSourceUser(UserTO sourceUser) {
	this.sourceUser = sourceUser;
    }

    public UserTO getTargetUser() {
	return targetUser;
    }

    public void setTargetUser(UserTO targetUser) {
	this.targetUser = targetUser;
    }

    public TourTO getTour() {
	return tour;
    }

    public void setTour(TourTO tour) {
	this.tour = tour;
    }

    public WishlistTO getWishlist() {
	return wishlist;
    }

    public void setWishlist(WishlistTO wishlist) {
	this.wishlist = wishlist;
    }

    public String getNotice() {
	return notice;
    }

    public void setNotice(String notice) {
	this.notice = notice;
    }

    public long getUpdatedOn() {
	return updatedOn;
    }

    public void setUpdatedOn(long updatedOn) {
	this.updatedOn = updatedOn;
    }

    public RequestStatus getStatus() {
	return status;
    }

    public void setStatus(RequestStatus status) {
	this.status = status;
    }

}
