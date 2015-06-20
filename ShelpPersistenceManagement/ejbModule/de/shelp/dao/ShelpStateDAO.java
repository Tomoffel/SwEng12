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
 * Session Bean Klasse in der alle Methoden der {@link ShelpStateDAOLocal}
 * ausimplementiert sind um alle Datenbankzugriffe für die Listenverwaltung zu
 * realisieren.
 * 
 * @author Jos Werner
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
	CriteriaQuery<DeliveryCondition> criteria = em.getCriteriaBuilder()
		.createQuery(DeliveryCondition.class);
	criteria.select(criteria.from(DeliveryCondition.class));
	List<DeliveryCondition> resultList = em.createQuery(criteria)
		.getResultList();
	return resultList;
    }

    @Override
    public List<PaymentCondition> getPaymentConditions() {
	CriteriaQuery<PaymentCondition> criteria = em.getCriteriaBuilder()
		.createQuery(PaymentCondition.class);
	criteria.select(criteria.from(PaymentCondition.class));
	List<PaymentCondition> resultList = em.createQuery(criteria)
		.getResultList();
	return resultList;
    }

    @Override
    public Location getLocation(long locationId) {
	return em.find(Location.class, locationId);
    }

    @Override
    public ApprovalStatus getApprovalStatus(int approvalStatusId) {
	return em.find(ApprovalStatus.class, approvalStatusId);
    }

    @Override
    public Capacity getCapacity(int capacityId) {
	return em.find(Capacity.class, capacityId);
    }

    @Override
    public PaymentCondition getPaymentCondition(int paymentConditionId) {
	return em.find(PaymentCondition.class, paymentConditionId);
    }

    @Override
    public DeliveryCondition getDeliveryCondition(int deliveryConditionId) {
	return em.find(DeliveryCondition.class, deliveryConditionId);
    }

}
