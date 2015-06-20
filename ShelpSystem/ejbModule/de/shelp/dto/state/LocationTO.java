package de.shelp.dto.state;

/**
 * TO-Objekt das einen Ort respr�sentiert. Umfasst eine Id, eine Beschreibung und
 * die PLZ.
 * 
 * @author Jos Werner
 *
 */
public class LocationTO {

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
