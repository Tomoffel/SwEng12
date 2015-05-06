package de.shelp.dao;

import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import de.shelp.entities.ShelpSession;
import de.shelp.entities.User;

/**
 * Session Bean implementation class XbankDAO
 */
@Stateless
public class ShelpUserDAO implements ShelpUserDAOLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User findUserByName(String username) {
	return em.find(User.class, username);
    }

    @Override
    public ShelpSession createSession(User user) {
	ShelpSession newSession = new ShelpSession(user);
	em.persist(newSession);
	return newSession;
    }

    @Override
    public User createUser(String username, String password, String email) {
	User user = new User(username, email, password, new GregorianCalendar());
	em.persist(user);
	return user;
    }

    @Override
    public void closeSession(int sessionId) {
	ShelpSession session = em.find(ShelpSession.class, sessionId);
	em.remove(session);
    }

    @Override
    public List<User> searchUsers(String searchText) {
	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
	Root<User> user = criteriaQuery.from(User.class);
	criteriaBuilder.like(user.<String> get("name"), searchText);

	TypedQuery<User> query = em.createQuery(criteriaQuery);
	//TODO second criteria: check email

	return query.getResultList();
    }

}
