package de.shelp.dto.user;

import java.util.Calendar;

public class ShelpSessionTO {

    private String id;
    private UserTO user;
    private Calendar creationOn;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public UserTO getUser() {
	return user;
    }

    public void setUser(UserTO user) {
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
	return id;
    }
}
