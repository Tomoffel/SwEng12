package de.shelp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entität die eine Lieferbedingung respräsentiert. Umfasst eine Id und eine
 * Beschreibung.
 * 
 * @author Jos Werner
 *
 */
@Entity
public class DeliveryCondition {
    
    @Id
    @GeneratedValue
    private int id;
    private String description;
    
    public DeliveryCondition() {
    }

    public DeliveryCondition(String description) {
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


}
