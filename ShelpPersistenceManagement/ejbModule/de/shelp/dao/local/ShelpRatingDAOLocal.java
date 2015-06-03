package de.shelp.dao.local;

import javax.ejb.Local;

import de.shelp.entities.Rating;

@Local
public interface ShelpRatingDAOLocal {

	public void createRating(Rating newRating);
	
}



