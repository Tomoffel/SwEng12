package de.shelp.dto.user;

import java.util.Calendar;

public class ShelpSessionTO {

    private String id;
    private String username;
    private Calendar creationOn;

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
