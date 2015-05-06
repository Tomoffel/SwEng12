package de.shelp.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User implements Serializable {

    private static final long serialVersionUID = -471315499574973048L;

    @Id
    private String name;
    private String email;
    private String password;
    private Calendar creationDate;

    public User() {
	super();
    }

    public User(String name, String email, String password, Calendar creationDate) {
	super();
	this.name = name;
	this.email = email;
	this.password = password;
	this.creationDate = creationDate;
    }

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

    public Calendar getCreationDate() {
	return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
	this.creationDate = creationDate;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

}
