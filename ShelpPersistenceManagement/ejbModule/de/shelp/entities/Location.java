package de.shelp.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Location {

    @Id
    private long id;
    private String description;
    private String zipcode;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getZipcode() {
	return zipcode;
    }

    public void setZipcode(String zipcode) {
	this.zipcode = zipcode;
    }

}
