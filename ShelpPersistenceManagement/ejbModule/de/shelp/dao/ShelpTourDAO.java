package de.shelp.dao;

import java.util.Calendar;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import de.shelp.dao.local.ShelpTourDAOLocal;
import de.shelp.entities.Location;
import de.shelp.entities.Tour;
import de.shelp.entities.User;
import de.shelp.enums.ApprovalStatus;
import de.shelp.enums.TourStatus;

/**
 * Session Bean implementation class ShelpTourDAO
 */
@Stateless
public class ShelpTourDAO implements ShelpTourDAOLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Tour createTour(Tour tour, User user) {
	tour.setOwner(user);
	tour.setStatus(TourStatus.PLANED);
	em.persist(tour);
	return tour;
    }

    @Override
    public void search(ApprovalStatus approvalStatus, Location location, Calendar startTime, Calendar endTime) {
	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	CriteriaQuery<Tour> criteriaQuery = criteriaBuilder.createQuery(Tour.class);
	Root<Tour> tour = criteriaQuery.from(Tour.class);

	criteriaBuilder.between(tour.<Calendar> get("time"), startTime, endTime);

	// TypedQuery<User> query = em.createQuery(criteriaQuery);
	// // TODO second criteria: check email
	//
	// return query.getResultList();
    }

    @Override
    public void searchNear(ApprovalStatus approvalStatus, Location location, Calendar startTime, Calendar endTime) {

    }

}
