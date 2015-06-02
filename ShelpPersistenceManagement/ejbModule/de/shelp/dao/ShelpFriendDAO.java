package de.shelp.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.shelp.dao.local.ShelpFriendDAOLocal;
import de.shelp.entities.Friendship;

public class ShelpFriendDAO implements ShelpFriendDAOLocal {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void createFriend(Friendship newFriend) {
		em.persist(newFriend);

	}

}
