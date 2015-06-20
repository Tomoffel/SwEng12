package de.shelp.dto.friend;

import de.shelp.dto.user.UserTO;
import de.shelp.entities.User;
import de.shelp.enums.FriendshipStatus;

/**
 * TO-Objekt das eine Freundschaft respräsentiert. Umfasst eine Id, Ersteller (
 * {@link User}), Empfänger ({@link User}), Status ({@link FriendshipStatus})
 * und ein Änderungsdatum.
 * 
 * @author Thomas Sennekamp
 *
 */
public class FriendshipTO {

    private int id;
    private UserTO initiatorUser;
    private UserTO recipientUser;
    private FriendshipStatus status;
    private long changedOn;

    public FriendshipTO() {
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
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

    public long getChangedOn() {
	return changedOn;
    }

    public void setChangedOn(long changedOn) {
	this.changedOn = changedOn;
    }

}
