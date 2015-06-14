package de.shelp.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import de.shelp.enums.RequestStatus;

@Entity
public class Request {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private User sourceUser;
    @ManyToOne
    private User targetUser;

    @ManyToOne
    private Tour tour;

    @OneToMany(mappedBy="owner", cascade = CascadeType.ALL)
    private List <WishlistItem> wishes;

    private String notice;
    private Date updatedOn;

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


    public List<WishlistItem> getWishes() {
		return wishes;
	}

	public void setWishes(List<WishlistItem> wishes) {
		this.wishes = wishes;
	}

	public String getNotice() {
	return notice;
    }

    public void setNotice(String notice) {
	this.notice = notice;
    }

    public Date getUpdatedOn() {
	return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
	this.updatedOn = updatedOn;
    }

    public RequestStatus getStatus() {
	return status;
    }

    public void setStatus(RequestStatus status) {
	this.status = status;
    }
    
    @Override
    public String toString() {
        return "Anfrage: " + id + " von " + sourceUser + " zu " + targetUser + " zur Fahrt " + tour ;
    }

}
