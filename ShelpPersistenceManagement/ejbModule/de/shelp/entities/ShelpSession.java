package de.shelp.entities;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ShelpSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String username;
    private Calendar creationOn;

    public ShelpSession(User user) {
	this.username = user.getName();
	this.creationOn = new GregorianCalendar();
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public Calendar getCreationOn() {
	return creationOn;
    }

    public void setCreationOn(Calendar creationOn) {
	this.creationOn = creationOn;
    }

    @Override
    public String toString() {
	return id;
    }

}
