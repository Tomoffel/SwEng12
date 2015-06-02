package de.shelp.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Friendship {

	private static final long serialVersionUID = -471315499574973048L;
	
	@Id
	private int id;
	
	@ManyToOne
	private User initiatorUser;
	
	@ManyToOne
	private User recipientUser;
	
	
	private FriendshipStatus status;
	private Date changeOn;
	
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



	
	
	
	
}
