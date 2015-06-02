package de.shelp.dto.friend;

import java.util.Calendar;

import de.shelp.dto.user.UserTO;

public class FriendShipTO {

    private long id;
    private UserTO initiatorUser;
    private UserTO recipientUser;
    private FriendshipStatusTO status;
    private Calendar changedOn;
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
	public FriendshipStatusTO getStatus() {
		return status;
	}
	public void setStatus(FriendshipStatusTO status) {
		this.status = status;
	}
	public Calendar getChangedOn() {
		return changedOn;
	}
	public void setChangedOn(Calendar changedOn) {
		this.changedOn = changedOn;
	}


}
