package de.shelp.dto.rating;

import de.shelp.dto.user.UserTO;

public class RatingTO {

	private long id;
	private UserTO sourceUser;
	private UserTO targetUser;
	private int rating;
	private String notice;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public UserTO getSourceUser() {
		return sourceUser;
	}
	public void setSourceUser(UserTO sourceUser) {
		this.sourceUser = sourceUser;
	}
	public UserTO getTargetUser() {
		return targetUser;
	}
	public void setTargetUser(UserTO targetUser) {
		this.targetUser = targetUser;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	
	
}
