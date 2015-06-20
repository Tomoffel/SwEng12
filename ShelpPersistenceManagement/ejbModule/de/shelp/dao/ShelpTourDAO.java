package de.shelp.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import de.shelp.dao.local.ShelpTourDAOLocal;
import de.shelp.entities.ApprovalStatus;
import de.shelp.entities.Capacity;
import de.shelp.entities.Location;
import de.shelp.entities.Tour;
import de.shelp.entities.User;
import de.shelp.enums.TourStatus;

/**
 * Session Bean Klasse in der alle Methoden der {@link ShelpTourDAOLocal}
 * ausimplementiert sind um alle Datenbankzugriffe für die Fahrtenverwaltung zu
 * realisieren.
 * 
 * @author Jos Werner
 */
@Stateless
public class ShelpTourDAO implements ShelpTourDAOLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Tour createTour(Tour tour, User user) {
	tour.setOwner(user);
	tour.setStatus(TourStatus.PLANNED);
	em.persist(tour);
	return tour;
    }

    @Override
    public List<Tour> search(ApprovalStatus approvalStatus, Location location,
	    Capacity capacity, Date startTime, Date endTime, User currentUser) {
	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	CriteriaQuery<Tour> criteriaQuery = criteriaBuilder
		.createQuery(Tour.class);
	Root<Tour> tour = criteriaQuery.from(Tour.class);

	List<Tour> searchedTours = new ArrayList<Tour>();

	// Geht alle Freunde durch und filtert nach den Fahrten die den
	// Ansprüchen gerecht werden
	List<User> friends = currentUser.getFriends();
	for (User friend : friends) {
	    List<Tour> tours = friend.getTours();
	    for (Tour tourOfUser : tours) {
		if (tourOfUser.getApprovalStatus().getDescription()
			.equals("Nur Freunde")
			&& location.equals(tourOfUser.getLocation())
			&& capacity.equals(tourOfUser.getCapacity())
			&& tourOfUser.getTime().after(startTime)
			&& tourOfUser.getTime().before(endTime)
			&& tourOfUser.getStatus().equals(TourStatus.PLANNED)) {
		    searchedTours.add(tourOfUser);
		}
	    }
	}

	if (approvalStatus.getDescription().equals("Alle")) {
	    Predicate andClause = criteriaBuilder.and(criteriaBuilder.equal(
		    tour.<Location> get("location"), location), criteriaBuilder
		    .and(criteriaBuilder.between(tour.<Date> get("time"),
			    startTime, endTime), criteriaBuilder.equal(
			    tour.<ApprovalStatus> get("approvalStatus"),
			    approvalStatus), criteriaBuilder.equal(
			    tour.<Capacity> get("capacity"), capacity),
			    criteriaBuilder.equal(
				    tour.<TourStatus> get("status"),
				    TourStatus.PLANNED)), criteriaBuilder
		    .notEqual(tour.<User> get("owner"), currentUser));
	    criteriaQuery.select(tour);
	    criteriaQuery.where(andClause);
	    searchedTours.addAll(em.createQuery(criteriaQuery).getResultList());
	}

	return searchedTours;
    }

    @Override
    public List<Tour> searchNear(ApprovalStatus approvalStatus,
	    Location location, Capacity capacity, Date startTime, Date endTime,
	    User currentUser) {
	// Alle Orte die in der Nähe des Ortes sind suchen
	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	CriteriaQuery<Location> criteriaQuery = criteriaBuilder
		.createQuery(Location.class);
	Root<Location> locationQuery = criteriaQuery.from(Location.class);
	criteriaQuery.select(locationQuery);
	criteriaQuery.where(criteriaBuilder.equal(
		locationQuery.<String> get("zipcode"), location.getZipcode()));

	List<Location> locationList = em.createQuery(criteriaQuery)
		.getResultList();

	List<Tour> resultList = new ArrayList<Tour>();
	// Alle gefundenen Orte durchgehen und passende Fahrten dazu aufnehmen
	for (Location locationSearch : locationList) {
	    resultList.addAll(search(approvalStatus, locationSearch, capacity,
		    startTime, endTime, currentUser));
	}

	return resultList;
    }

    @Override
    public Tour getTour(long tourId) {
	return em.find(Tour.class, tourId);
    }

    @Override
    public void cancleTour(Tour tour) {
	tour.setStatus(TourStatus.CANCELLED);
	em.persist(tour);
    }

   

    @Override
    public void saveTour(Tour tour) {
	em.persist(tour);
    }

    @Override
    public void closeTour(Tour tour) {
	tour.setStatus(TourStatus.CLOSED);
	saveTour(tour);
    }

    @Override
    public List<Tour> getOpenTours() {
	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	CriteriaQuery<Tour> criteriaQuery = criteriaBuilder
		.createQuery(Tour.class);
	Root<Tour> tour = criteriaQuery.from(Tour.class);
	criteriaQuery.select(tour);
	criteriaQuery.where(criteriaBuilder.equal(
		tour.<TourStatus> get("status"), TourStatus.PLANNED));
	return em.createQuery(criteriaQuery).getResultList();
    }

}
