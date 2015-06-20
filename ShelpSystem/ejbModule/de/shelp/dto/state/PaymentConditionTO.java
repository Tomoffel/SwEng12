package de.shelp.dto.state;

/**
 * TO-Objekte das eine Bezahlmethode respr�sentiert. Umfasst eine Id und eine Beschreibung.
 * 
 * @author Jos Werner
 *
 */
public class PaymentConditionTO {

    private int id;
    private String description;

    public PaymentConditionTO() {
    }

    public PaymentConditionTO(String description) {
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
