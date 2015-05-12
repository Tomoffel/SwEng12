package de.shelp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class WishlistItem {

    @Id
    @GeneratedValue
    private int id;
    private String text;
    private boolean checked;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Wishlist owner;

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

    public Wishlist getOwner() {
	return owner;
    }

    public void setOwner(Wishlist wishlist) {
	this.owner = wishlist;
    }

}
