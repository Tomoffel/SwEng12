package de.shelp.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import de.shelp.entities.ApprovalStatus;
import de.shelp.entities.Capacity;
import de.shelp.entities.DeliveryCondition;
import de.shelp.entities.Location;
import de.shelp.entities.PaymentCondition;

/**
 * Singleton Klasse die beim Serverstart gestartet wird und die Anfangswerte in
 * die Datenbank füllt. Überprüft dabei immer zuerst ob die Werte nicht schon in
 * der Datenbank existieren.
 * 
 * @author Jos Werner
 *
 */
@Startup
@Singleton
public class DataBuilder {

    @PersistenceContext
    EntityManager em;

    @Resource
    private String location1, plz1, location2, plz2, location3, plz3,
	    location4, plz4, location5, plz5, location6, plz6,
	    approvalStatusAll, approvalStatusFriend, smallTrunk, middleTrunk,
	    hugeTrunk, bring, pickup, cashInAdvance, cash, paypal;

    @PostConstruct
    private void init() {
	List<String> desc = new ArrayList<String>();

	CriteriaQuery<Location> criteria = em.getCriteriaBuilder().createQuery(
		Location.class);
	criteria.select(criteria.from(Location.class));
	List<Location> resultList = em.createQuery(criteria).getResultList();
	for (Iterator<Location> iterator = resultList.iterator(); iterator
		.hasNext();) {
	    desc.add(iterator.next().getDescription());
	}

	if (!desc.contains(location1)) {
	    em.persist(new Location(location1, plz1));
	}
	if (!desc.contains(location2)) {
	    em.persist(new Location(location2, plz2));
	}
	if (!desc.contains(location3)) {
	    em.persist(new Location(location3, plz3));
	}
	if (!desc.contains(location4)) {
	    em.persist(new Location(location4, plz4));
	}
	if (!desc.contains(location5)) {
	    em.persist(new Location(location5, plz5));
	}
	if (!desc.contains(location6)) {
	    em.persist(new Location(location6, plz6));
	}

	desc.clear();
	CriteriaQuery<ApprovalStatus> criteriaAs = em.getCriteriaBuilder()
		.createQuery(ApprovalStatus.class);
	criteriaAs.select(criteriaAs.from(ApprovalStatus.class));
	List<ApprovalStatus> resultListAs = em.createQuery(criteriaAs)
		.getResultList();
	for (Iterator<ApprovalStatus> iterator = resultListAs.iterator(); iterator
		.hasNext();) {
	    desc.add(iterator.next().getDescription());
	}
	if (!desc.contains(approvalStatusAll)) {
	    em.persist(new ApprovalStatus(approvalStatusAll));
	}
	if (!desc.contains(approvalStatusFriend)) {
	    em.persist(new ApprovalStatus(approvalStatusFriend));
	}

	desc.clear();
	CriteriaQuery<Capacity> criteriaCa = em.getCriteriaBuilder()
		.createQuery(Capacity.class);
	criteriaCa.select(criteriaCa.from(Capacity.class));
	List<Capacity> resultListCa = em.createQuery(criteriaCa)
		.getResultList();
	for (Iterator<Capacity> iterator = resultListCa.iterator(); iterator
		.hasNext();) {
	    desc.add(iterator.next().getDescription());
	}

	if (!desc.contains(smallTrunk)) {
	    em.persist(new Capacity(smallTrunk));
	}
	if (!desc.contains(middleTrunk)) {
	    em.persist(new Capacity(middleTrunk));
	}
	if (!desc.contains(hugeTrunk)) {
	    em.persist(new Capacity(hugeTrunk));
	}

	desc.clear();
	CriteriaQuery<DeliveryCondition> criteriaDc = em.getCriteriaBuilder()
		.createQuery(DeliveryCondition.class);
	criteriaDc.select(criteriaDc.from(DeliveryCondition.class));
	List<DeliveryCondition> resultListDc = em.createQuery(criteriaDc)
		.getResultList();
	for (Iterator<DeliveryCondition> iterator = resultListDc.iterator(); iterator
		.hasNext();) {
	    desc.add(iterator.next().getDescription());
	}
	if (!desc.contains(bring)) {
	    em.persist(new DeliveryCondition(bring));
	}
	if (!desc.contains(pickup)) {
	    em.persist(new DeliveryCondition(pickup));
	}

	desc.clear();
	CriteriaQuery<PaymentCondition> criteriaPc = em.getCriteriaBuilder()
		.createQuery(PaymentCondition.class);
	criteriaPc.select(criteriaPc.from(PaymentCondition.class));
	List<PaymentCondition> resultListPc = em.createQuery(criteriaPc)
		.getResultList();
	for (Iterator<PaymentCondition> iterator = resultListPc.iterator(); iterator
		.hasNext();) {
	    desc.add(iterator.next().getDescription());
	}

	if (!desc.contains(cash)) {
	    em.persist(new PaymentCondition(cash));
	}
	if (!desc.contains(cashInAdvance)) {
	    em.persist(new PaymentCondition(cashInAdvance));
	}
	if (!desc.contains(paypal)) {
	    em.persist(new PaymentCondition(paypal));
	}

    }

}
