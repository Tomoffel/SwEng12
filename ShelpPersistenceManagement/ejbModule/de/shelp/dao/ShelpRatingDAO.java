package de.shelp.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.shelp.dao.local.ShelpRatingDAOLocal;
import de.shelp.entities.Rating;

@Stateless
public class ShelpRatingDAO implements ShelpRatingDAOLocal {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void createRating(Rating newRating) {
	    em.persist(newRating);
	}

}
