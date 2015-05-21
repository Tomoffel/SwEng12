package de.shelp.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Wishlist {

    @Id
    @GeneratedValue
    private long id;
    private User owner;

    @OneToMany(mappedBy = "owner")
    private List<WishlistItem> wishes;

    @OneToOne(mappedBy = "wishlist")
    private Request request;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public User getOwner() {
	return owner;
    }

    public void setOwner(User owner) {
	this.owner = owner;
    }

    public List<WishlistItem> getWishes() {
	return wishes;
    }

    public void setWishes(List<WishlistItem> wishes) {
	this.wishes = wishes;
    }

    public Request getRequest() {
	return request;
    }

    public void setRequest(Request request) {
	this.request = request;
    }
    
    @Override
    public String toString() {
        return "Wunschliste " + id + " von " + owner;
    }

}
