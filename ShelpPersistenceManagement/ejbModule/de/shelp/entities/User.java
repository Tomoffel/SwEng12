package de.shelp.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = -471315499574973048L;

	@Id
	private String email;
	private String password;
	private Calendar creationDate;

	@OneToMany(mappedBy = "targetUser")
	private List<Rating> ratings;

	@OneToMany(mappedBy = "owner")
	private List<Tour> tours;

	public User() {
		super();
	}

	public User(String email, String password, Calendar creationDate) {
		super();
		this.email = email;
		this.password = password;
		this.creationDate = creationDate;
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

	@Override
	public String toString() {
		return email;
	}

	public boolean isFriend(User user) {
		// TODO implement function with friend list
		return true;
	}

	public List<Tour> getTours() {
		return tours;
	}

	public void setTours(List<Tour> tours) {
		this.tours = tours;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List <Rating> ratings) {
		this.ratings = ratings;
	}

}
