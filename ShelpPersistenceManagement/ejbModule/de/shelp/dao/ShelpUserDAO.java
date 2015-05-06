package de.shelp.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
