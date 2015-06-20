package de.shelp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entität die einen Benutzer respräsentiert. Umfasst eine E-Mailadresse (Id),
 * das Passwort und ein Erstelldatum.
 * 
 * @author Jos Werner
 *
 */
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = -471315499574973048L;

    @Id
    private String email;
    private String password;
    private Calendar creationDate;

    @OneToMany(mappedBy = "sourceUser")
    private List<Request> ownRequests;

    @OneToMany(mappedBy = "targetUser")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "owner")
    private List<Tour> tours;

    @OneToMany(mappedBy = "initiatorUser")
    private List<Friendship> initiators;

    @OneToMany(mappedBy = "recipientUser")
    private List<Friendship> recipients;

    public User() {
	super();
    }

    public User(String email, String password, Calendar creationDate) {
	this();
	this.email = email;
	this.password = password;
	this.creationDate = creationDate;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Calendar getCreationDate() {
	return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
	this.creationDate = creationDate;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @Override
    public String toString() {
	return email;
    }

    public boolean isFriend(User user) {
	return getFriends().contains(user);
    }

    public List<Tour> getTours() {
	return tours;
    }

    public void setTours(List<Tour> tours) {
	this.tours = tours;
    }

    public List<Rating> getRatings() {
	return ratings;
    }

    public void setRatings(List<Rating> ratings) {
	this.ratings = ratings;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((creationDate == null) ? 0 : creationDate.hashCode());
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result
		+ ((password == null) ? 0 : password.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	User other = (User) obj;
	if (creationDate == null) {
	    if (other.creationDate != null)
		return false;
	} else if (!creationDate.equals(other.creationDate))
	    return false;
	if (email == null) {
	    if (other.email != null)
		return false;
	} else if (!email.equals(other.email))
	    return false;
	if (password == null) {
	    if (other.password != null)
		return false;
	} else if (!password.equals(other.password))
	    return false;
	return true;
    }

    public List<Friendship> getInitiators() {
	return initiators;
    }

    public void setInitiators(List<Friendship> initiators) {
	this.initiators = initiators;
    }

    public List<Friendship> getRecipients() {
	return recipients;
    }

    public void setRecipients(List<Friendship> recipients) {
	this.recipients = recipients;
    }

    /**
     * Gibt alle Freundschaften ({@link Friendship}) des Benutzers zurück
     * 
     * @return Liste mit allen Freundschaften
     */
    public List<Friendship> getFriendships() {
	List<Friendship> friends = new ArrayList<Friendship>();
	if (initiators != null) {
	    friends.addAll(initiators);
	}
	if (recipients != null) {
	    friends.addAll(recipients);
	}
	return friends;
    }

    /**
     * Gibt alle Freunde des Benutzers zurück
     * 
     * @return Liste mit Freunden
     */
    public List<User> getFriends() {
	List<User> friends = new ArrayList<User>();
	for (Friendship friendship : initiators) {
	    friends.add(friendship.getRecipientUser());
	}
	for (Friendship friendship : recipients) {
	    friends.add(friendship.getInitiatorUser());
	}

	return friends;
    }

    public List<Request> getOwnRequests() {
	return ownRequests;
    }

    public void setOwnRequests(List<Request> ownRequests) {
	this.ownRequests = ownRequests;
    }
}
