package de.shelp.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class WishlistItem {

    @Id
    private int id;
    private String text;
    private boolean checked;

    @ManyToOne
    private Wishlist wishlist;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public boolean isChecked() {
	return checked;
    }

    public void setChecked(boolean checked) {
	this.checked = checked;
    }

    public Wishlist getWishlist() {
	return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
	this.wishlist = wishlist;
    }

}
