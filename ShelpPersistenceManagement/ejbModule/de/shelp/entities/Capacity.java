package de.shelp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entität die eine Kapazität respräsentiert. Umfasst eine Id und eine
 * Beschreibung.
 * 
 * @author Jos Werner
 *
 */
@Entity
public class Capacity {

    @Id
    @GeneratedValue
    private int id;
    private String description;

    public Capacity() {
    }

    public Capacity(String description) {
	super();
	this.description = description;
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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((description == null) ? 0 : description.hashCode());
	result = prime * result + id;
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
	Capacity other = (Capacity) obj;
	if (description == null) {
	    if (other.description != null)
		return false;
	} else if (!description.equals(other.description))
	    return false;
	if (id != other.id)
	    return false;
	return true;
    }

}
