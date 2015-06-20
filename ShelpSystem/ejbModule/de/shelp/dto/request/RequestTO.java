package de.shelp.dto.request;

import java.util.List;

import de.shelp.dto.tour.TourTO;
import de.shelp.dto.user.UserTO;
import de.shelp.entities.Tour;
import de.shelp.entities.User;
import de.shelp.entities.WishlistItem;
import de.shelp.enums.RequestStatus;

/**
 * Entität die einen Anfrage respräsentiert. Umfasst eine Id, den Ersteller (
 * {@link User}), den Empfänger ({@link User}), eine Fahrt ({@link Tour}), eine
 * Liste mit Wünschen {@link WishlistItem}, eine Beschreibung, einen Status (
 * {@link RequestStatus}) und ein Flag zum setzen ob die Anfrage geändert wurde.
 * 
 * @author Thomas Sennekamp
 *
 */
public class RequestTO {

    private long id;
    private UserTO sourceUser;
    private UserTO targetUser;
    private TourTO tour;
    private List<WishlistItemTO> wishes;
    private String notice;
    private boolean updated;
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

    public List<WishlistItemTO> getWishes() {
	return wishes;
    }

    public void setWishes(List<WishlistItemTO> wishes) {
	this.wishes = wishes;
    }

    public String getNotice() {
	return notice;
    }

    public void setNotice(String notice) {
	this.notice = notice;
    }

    public boolean isUpdated() {
	return updated;
    }

    public void setUpdated(boolean updated) {
	this.updated = updated;
    }

    public RequestStatus getStatus() {
	return status;
    }

    public void setStatus(RequestStatus status) {
	this.status = status;
    }

}
