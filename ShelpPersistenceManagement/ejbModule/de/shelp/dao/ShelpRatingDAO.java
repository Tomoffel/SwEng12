package de.shelp.dao;

import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import de.shelp.dao.local.ShelpRatingDAOLocal;
import de.shelp.dao.local.ShelpUserDAOLocal;
import de.shelp.entities.Rating;
import de.shelp.entities.User;

public class ShelpRatingDAO implements ShelpRatingDAOLocal {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void createRating(Rating newRating) {
		em.persist(newRating);

	}

}
