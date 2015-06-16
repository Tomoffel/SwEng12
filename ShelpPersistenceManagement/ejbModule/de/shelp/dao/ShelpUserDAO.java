package de.shelp.dao;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import de.shelp.dao.local.ShelpUserDAOLocal;
import de.shelp.entities.ShelpSession;
import de.shelp.entities.User;

/**
 * Session Bean implementation class ShelpUserDAO
 */
@Stateless
public class ShelpUserDAO implements ShelpUserDAOLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User findUserByName(String email) {
	return em.find(User.class, email);
    }

    @Override
    public ShelpSession createSession(User user) {
	ShelpSession newSession = new ShelpSession(user);
	em.persist(newSession);
	return newSession;
    }

    @Override
    public User createUser( String password, String email) {
	User user = new User(email, password, new GregorianCalendar());
	em.persist(user);
	return user;
    }

    @Override
    public boolean closeSession(int sessionId) {
	ShelpSession session = em.find(ShelpSession.class, sessionId);
	if (session == null) {
	    return false;
	}
	em.remove(session);
	return true;
    }

    @Override
    public List<User> searchUsers(String searchText) {
	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
	Root<User> user = criteriaQuery.from(User.class);
	criteriaQuery.select(user);
	criteriaQuery.where(criteriaBuilder.like(user.<String> get("email"), "%" + searchText + "%"));
	return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public ShelpSession getSession(int sessionId) {
	ShelpSession session = em.find(ShelpSession.class, sessionId);
	return session;
    }

    @Override
    public void updateSession(ShelpSession session) {
	session.setUpdatedOn(new Date());
	em.persist(session);
    }

}
