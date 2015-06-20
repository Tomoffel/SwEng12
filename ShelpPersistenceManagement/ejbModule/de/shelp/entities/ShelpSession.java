package de.shelp.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import de.shelp.enums.TourStatus;

/**
 * Entität die einen Session respräsentiert. Umfasst eine Id, den Ersteller (
 * {@link User}), den Empfänger ({@link User}), eine Fahrt ({@link Tour}), eine
 * Liste mit Wünschen {@link WishlistItem}, eine Beschreibung, einen Status (
 * {@link TourStatus}) und ein Flag zum setzen ob die Fahrt geändert wurde.
 * 
 * @author Thomas Sennekamp
 *
 */
@Entity
public class ShelpSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    private User user;
    private Date updatedOn;

    public ShelpSession() {
    }

    public ShelpSession(User user) {
	this.user = user;
	this.updatedOn = new Date();
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
