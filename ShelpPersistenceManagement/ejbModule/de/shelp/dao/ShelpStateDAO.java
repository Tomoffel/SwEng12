package de.shelp.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import de.shelp.dao.local.ShelpStateDAOLocal;
import de.shelp.entities.Location;

/**
 * Session Bean implementation class ShelpStateDAO
 */
@Stateless
public class ShelpStateDAO implements ShelpStateDAOLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Location> getLocations() {
	CriteriaQuery<Location> criteria = em.getCriteriaBuilder().createQuery(Location.class);
	criteria.select(criteria.from(Location.class));
	List<Location> resultList = em.createQuery(criteria).getResultList();
	return resultList;
    }

}
