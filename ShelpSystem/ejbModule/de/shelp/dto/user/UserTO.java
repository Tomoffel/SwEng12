package de.shelp.dto.user;

import java.io.Serializable;

public class UserTO implements Serializable {

    private static final long serialVersionUID = -3538812124589566926L;
    
    private String name;
    private String email;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    @Override
    public String toString() {
	return name;
    }

}
