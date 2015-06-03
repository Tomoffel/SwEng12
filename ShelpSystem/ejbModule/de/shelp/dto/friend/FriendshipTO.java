package de.shelp.dto.friend;

import java.util.Calendar;

import de.shelp.dto.user.UserTO;
import de.shelp.enums.FriendshipStatus;

public class FriendshipTO {

    private long id;
    private UserTO initiatorUser;
    private UserTO recipientUser;
    private FriendshipStatus status;
    private Calendar changedOn;
    
    public FriendshipTO() {
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public UserTO getInitiatorUser() {
	return initiatorUser;
    }

    public void setInitiatorUser(UserTO initiatorUser) {
	this.initiatorUser = initiatorUser;
    }

    public UserTO getRecipientUser() {
	return recipientUser;
    }

    public void setRecipientUser(UserTO recipientUser) {
	this.recipientUser = recipientUser;
    }

    public FriendshipStatus getStatus() {
	return status;
    }

    public void setStatus(FriendshipStatus status) {
	this.status = status;
    }

    public Calendar getChangedOn() {
	return changedOn;
    }

    public void setChangedOn(Calendar changedOn) {
	this.changedOn = changedOn;
    }

}
