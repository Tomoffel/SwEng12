package de.shelp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entität die einen Bewertung respräsentiert. Umfasst eine Id, den Ersteller (
 * {@link User}), den Empfänger ({@link User}), ein Rating (in Sternen 10 - 50)
 * und eine Beschreibung der Bewertung.
 * 
 * @author Thomas Sennekamp
 *
 */
@Entity
public class Rating {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "source_user")
    private User sourceUser;

    @ManyToOne
    @JoinColumn(name = "target_User")
    private User targetUser;

    private int rating;
    private String notice;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public User getSourceUser() {
	return sourceUser;
    }

    public void setSourceUser(User sourceUser) {
	this.sourceUser = sourceUser;
    }

    public User getTargetUser() {
	return targetUser;
    }

    public void setTargetUser(User targetUser) {
	this.targetUser = targetUser;
    }

    public int getRating() {
	return rating;
    }

    public void setRating(int rating) {
	this.rating = rating;
    }

    public String getNotice() {
	return notice;
    }

    public void setNotice(String notice) {
	this.notice = notice;
    }

}
