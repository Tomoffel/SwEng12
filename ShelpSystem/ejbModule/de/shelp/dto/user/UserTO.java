package de.shelp.dto.user;

import java.io.Serializable;

public class UserTO implements Serializable {

    private static final long serialVersionUID = -3538812124589566926L;

    private String email;

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    @Override
    public String toString() {
	return email;
    }

}
