package de.shelp.dto.request;

/**
 * TO-Objekt das einen Wunsch respräsentiert. Umfasst eine Id, einen Text und ein
 * Flag ob der Wunsch aktzeptiert wurde oder nicht.
 * 
 * @author Thomas Sennekamp
 *
 */
public class WishlistItemTO {

    private int id;
    private String text;
    private boolean checked;

    private RequestTO owner;

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

    public RequestTO getOwner() {
	return owner;
    }

    public void setOwner(RequestTO owner) {
	this.owner = owner;
    }

    @Override
    public String toString() {
	return "Wunschlistobjekt: " + text + " von " + owner;
    }

}
