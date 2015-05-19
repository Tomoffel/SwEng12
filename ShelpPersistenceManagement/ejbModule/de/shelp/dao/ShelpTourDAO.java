package de.shelp.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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
    public List<Tour> search(ApprovalStatus approvalStatus, Location location, Calendar startTime, Calendar endTime, User currentUser) {
	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	CriteriaQuery<Tour> criteriaQuery = criteriaBuilder.createQuery(Tour.class);
	Root<Tour> tour = criteriaQuery.from(Tour.class);

	List<Tour> searchedTours = new ArrayList<Tour>();
	// TODO get friends of current user and get their active tours with
	// friends_only state

	if (approvalStatus == ApprovalStatus.ALL) {
	    Predicate andClause = criteriaBuilder.and(
		    criteriaBuilder.equal(tour.<Location> get("location"), location),
		    criteriaBuilder.and(criteriaBuilder.between(tour.<Calendar> get("time"), startTime, endTime),
			    criteriaBuilder.equal(tour.<ApprovalStatus> get("approvalStatus"), ApprovalStatus.ALL)));
	    criteriaQuery.select(tour);
	    criteriaQuery.where(andClause);
	    searchedTours.addAll(em.createQuery(criteriaQuery).getResultList());
	}

	return searchedTours;
    }

    @Override
    public List<Tour> searchNear(ApprovalStatus approvalStatus, Location location, Calendar startTime, Calendar endTime, User currentUser) {
	// Get all locations, filter by zipcode of given location
	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	CriteriaQuery<Location> criteriaQuery = criteriaBuilder.createQuery(Location.class);
	Root<Location> locationQuery = criteriaQuery.from(Location.class);
	criteriaQuery.select(locationQuery);
	criteriaQuery.where(criteriaBuilder.equal(locationQuery.<String> get("zipcode"), location.getZipcode()));

	List<Location> locationList = em.createQuery(criteriaQuery).getResultList();

	// search all tours of location list and at to result
	List<Tour> resultList = new ArrayList<Tour>();

	for (Location locationSearch : locationList) {
	    resultList.addAll(search(approvalStatus, locationSearch, startTime, endTime, currentUser));
	}

	return resultList;
    }

    @Override
    public Tour getTour(int tourId) {
	return em.find(Tour.class, tourId);
    }

    @Override
    public void cancleTour(Tour tour) {
	tour.setStatus(TourStatus.CANCLED);
	tour.setUpdatedOn(new GregorianCalendar());
	em.persist(tour);
    }

}
