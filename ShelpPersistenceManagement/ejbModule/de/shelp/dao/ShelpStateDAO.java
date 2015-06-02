package de.shelp.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import de.shelp.dao.local.ShelpStateDAOLocal;
import de.shelp.entities.ApprovalStatus;
import de.shelp.entities.Capacity;
import de.shelp.entities.DeliveryCondition;
import de.shelp.entities.Location;
import de.shelp.entities.PaymentCondition;

/**
 * Session Bean implementation class ShelpStateDAO
 */
@Stateless
public class ShelpStateDAO implements ShelpStateDAOLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Location> getLocations() {
	CriteriaQuery<Location> criteria = em.getCriteriaBuilder().createQuery(
		Location.class);
	criteria.select(criteria.from(Location.class));
	List<Location> resultList = em.createQuery(criteria).getResultList();
	return resultList;
    }

    @Override
    public List<ApprovalStatus> getApprovalStates() {
	CriteriaQuery<ApprovalStatus> criteria = em.getCriteriaBuilder()
		.createQuery(ApprovalStatus.class);
	criteria.select(criteria.from(ApprovalStatus.class));
	List<ApprovalStatus> resultList = em.createQuery(criteria)
		.getResultList();
	return resultList;
    }

    @Override
    public List<Capacity> getCapacities() {
	CriteriaQuery<Capacity> criteria = em.getCriteriaBuilder().createQuery(
		Capacity.class);
	criteria.select(criteria.from(Capacity.class));
	List<Capacity> resultList = em.createQuery(criteria).getResultList();
	return resultList;
    }

    @Override
    public List<DeliveryCondition> getDeliveryConditions() {
	CriteriaQuery<DeliveryCondition> criteria = em.getCriteriaBuilder().createQuery(
		DeliveryCondition.class);
	criteria.select(criteria.from(DeliveryCondition.class));
	List<DeliveryCondition> resultList = em.createQuery(criteria).getResultList();
	return resultList;
    }

    @Override
    public List<PaymentCondition> getPaymentConditions() {
	CriteriaQuery<PaymentCondition> criteria = em.getCriteriaBuilder().createQuery(
		PaymentCondition.class);
	criteria.select(criteria.from(PaymentCondition.class));
	List<PaymentCondition> resultList = em.createQuery(criteria).getResultList();
	return resultList;
    }

}
