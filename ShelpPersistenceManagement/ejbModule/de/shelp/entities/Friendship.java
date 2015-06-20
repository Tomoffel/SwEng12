package de.shelp.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import de.shelp.enums.FriendshipStatus;

/**
 * Entität die einen Freundschaft respräsentiert. Umfasst eine Id, Ersteller (
 * {@link User}), Empfänger ({@link User}), Status ({@link FriendshipStatus})
 * und ein Änderungsdatum.
 * 
 * @author Thomas Sennekamp
 *
 */
@Entity
public class Friendship {

    private static final long serialVersionUID = -471315499574973048L;

    @Id
    private int id;

    @ManyToOne
    private User initiatorUser;

    @ManyToOne
    private User recipientUser;

    @Enumerated(EnumType.ORDINAL)
    private FriendshipStatus status;

    private Date changeOn;

    public Friendship() {
    }

    /**
     * Erstellt den Hash für eine neue Freundschaft. Der Hash besteht aus dem
     * Hash des Erstellers und Empfängers
     * 
     * @return der Hash der Freundschaft
     */
    public int getFriendshipHash() {
	return initiatorUser.hashCode() + recipientUser.hashCode();
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public User getInitiatorUser() {
	return initiatorUser;
    }

    public void setInitiatorUser(User initiatorUser) {
	this.initiatorUser = initiatorUser;
    }

    public User getRecipientUser() {
	return recipientUser;
    }

    public void setRecipientUser(User recipientUser) {
	this.recipientUser = recipientUser;
    }

    public FriendshipStatus getStatus() {
	return status;
    }

    public void setStatus(FriendshipStatus status) {
	this.status = status;
    }

    public Date getChangeOn() {
	return changeOn;
    }

    public void setChangeOn(Date changeOn) {
	this.changeOn = changeOn;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "Freundschaft zwischen " + initiatorUser + " und "
		+ recipientUser;
    }

}
