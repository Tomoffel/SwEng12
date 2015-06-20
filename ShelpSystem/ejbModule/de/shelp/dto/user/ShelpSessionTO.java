package de.shelp.dto.user;

import java.util.Date;

import de.shelp.entities.User;

/**
 * TO-Objekt das eine Session respräsentiert. Umfasst eine Id, den Besitzer (
 * {@link User}) und das Änderungsdatum.
 * 
 * @author Thomas Sennekamp
 *
 */
public class ShelpSessionTO {

    private int id;
    private UserTO user;
    private Date updatedOn;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public UserTO getUser() {
	return user;
    }

    public void setUser(UserTO user) {
	this.user = user;
    }

    public Date getUpdatedOn() {
	return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
	this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
	return String.valueOf(id);
    }
}
