package de.shelp.dto.friend;

import java.util.Calendar;

import de.shelp.dto.user.UserTO;
import de.shelp.enums.FriendShipStatus;

public class FriendShipTO {

    private long id;
    private UserTO firstUser;
    private UserTO secondUser;
    private FriendShipStatus status;
    private Calendar changedOn;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public UserTO getFirstUser() {
	return firstUser;
    }

    public void setFirstUser(UserTO firstUser) {
	this.firstUser = firstUser;
    }

    public UserTO getSecondUser() {
	return secondUser;
    }

    public void setSecondUser(UserTO secondUser) {
	this.secondUser = secondUser;
    }

    public FriendShipStatus getStatus() {
	return status;
    }

    public void setStatus(FriendShipStatus status) {
	this.status = status;
    }

    public Calendar getChangedOn() {
	return changedOn;
    }

    public void setChangedOn(Calendar changedOn) {
	this.changedOn = changedOn;
    }

}
