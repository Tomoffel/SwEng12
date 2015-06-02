package de.shelp.dto.friend;


public class FriendshipStatusTO {

    private int id;
    private String description;

    public FriendshipStatusTO( String description) {
	super();
	this.description = description;
    }

    public FriendshipStatusTO() {
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

}
