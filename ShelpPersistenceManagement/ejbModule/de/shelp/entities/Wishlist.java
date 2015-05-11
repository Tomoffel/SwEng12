package de.shelp.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


public class Wishlist {

    @Id
    @GeneratedValue
    private long id;
    private User owner;

//    @OneToMany(mappedBy = "wishlist")
    private List<WishlistItem> wishes;

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

}
