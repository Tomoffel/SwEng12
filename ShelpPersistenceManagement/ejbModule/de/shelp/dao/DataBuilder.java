package de.shelp.dao;

import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.jboss.logging.Logger;

import de.shelp.entities.ApprovalStatus;
import de.shelp.entities.Capacity;
import de.shelp.entities.DeliveryCondition;
import de.shelp.entities.Location;
import de.shelp.entities.PaymentCondition;

@Startup
@Singleton
public class DataBuilder {

    private static final Logger LOGGER = Logger.getLogger(DataBuilder.class);

    @PersistenceContext
    EntityManager em;

    @Resource
    private String location1, plz1, location2, plz2, location3, plz3,
	    location4, plz4, location5, plz5, location6, plz6,
	    approvalStatusAll, approvalStatusFriend, smallTrunk, middleTrunk,
	    hugeTrunk, bring, pickup, cashInAdvance, cash, paypal;

    @PostConstruct
    private void init() {
	CriteriaQuery<Location> criteria = em.getCriteriaBuilder().createQuery(
		Location.class);
	criteria.select(criteria.from(Location.class));
	List<Location> resultList = em.createQuery(criteria).getResultList();
	for (Iterator<Location> iterator = resultList.iterator(); iterator
		.hasNext();) {
	    Location location = iterator.next();
	    em.remove(location);
	}
	LOGGER.info("Alle Locations entfernt. Erstelle Anfangslocations");

	em.persist(new Location(location1, plz1));
	em.persist(new Location(location2, plz2));
	em.persist(new Location(location3, plz3));
	em.persist(new Location(location4, plz4));
	em.persist(new Location(location5, plz5));
	em.persist(new Location(location6, plz6));

	em.persist(new ApprovalStatus(approvalStatusAll));
	em.persist(new ApprovalStatus(approvalStatusFriend));

	em.persist(new Capacity(smallTrunk));
	em.persist(new Capacity(middleTrunk));
	em.persist(new Capacity(hugeTrunk));
	
	em.persist(new DeliveryCondition(bring));
	em.persist(new DeliveryCondition(pickup));
	
	em.persist(new PaymentCondition(cash));
	em.persist(new PaymentCondition(cashInAdvance));
	em.persist(new PaymentCondition(paypal));

    }

}
