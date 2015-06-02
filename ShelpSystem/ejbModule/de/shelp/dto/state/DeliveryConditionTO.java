package de.shelp.dto.state;


public class DeliveryConditionTO {
    
    private int id;
    private String description;
    
    public DeliveryConditionTO() {
    }

    public DeliveryConditionTO(String description) {
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
