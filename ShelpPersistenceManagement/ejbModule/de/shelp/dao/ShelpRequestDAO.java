package de.shelp.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.shelp.dao.local.ShelpRequestDAOLocal;
import de.shelp.entities.Request;
import de.shelp.enums.RequestStatus;

@Stateless
public class ShelpRequestDAO implements ShelpRequestDAOLocal {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Request getRequestById(long requestId) {
	return em.find(Request.class, requestId);
    }

    @Override
    public void deleteRequest(Request request) {
	request.setStatus(RequestStatus.REMOVED);
	em.persist(request);
    }

    @Override
    public void persistRequest(Request request) {
	em.persist(request);
    }

}
