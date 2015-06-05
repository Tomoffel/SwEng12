package de.shelp.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.shelp.dao.local.ShelpFriendDAOLocal;
import de.shelp.entities.Friendship;

@Stateless
public class ShelpFriendDAO implements ShelpFriendDAOLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void saveFriendship(Friendship friendship) {
	em.persist(friendship);
    }

    @Override
    public Friendship findFriendshipById(int friendshipHash) {
	return em.find(Friendship.class, friendshipHash);
    }

    @Override
    public void deleteFriendship(Friendship friendship) {
	em.remove(friendship);
    }

}
