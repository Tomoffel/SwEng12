package de.shelp.entities;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ShelpSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    private User user;
    private Calendar creationOn;

    public ShelpSession() {
    }

    public ShelpSession(User user) {
	this.user = user;
	this.creationOn = new GregorianCalendar();
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Calendar getCreationOn() {
	return creationOn;
    }

    public void setCreationOn(Calendar creationOn) {
	this.creationOn = creationOn;
    }

    @Override
    public String toString() {
	return String.valueOf(id);
    }

}
