package de.shelp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import de.shelp.dao.local.ShelpFriendDAOLocal;
import de.shelp.entities.Friendship;
import de.shelp.entities.FriendshipStatus;

public class ShelpFriendDAO implements ShelpFriendDAOLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createFriendship(Friendship friendship) {
	em.persist(friendship);
    }

    @Override
    public FriendshipStatus getFriendshipStatus(String description) {
	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	CriteriaQuery<FriendshipStatus> criteriaQuery = criteriaBuilder
		.createQuery(FriendshipStatus.class);
	Root<FriendshipStatus> fs = criteriaQuery.from(FriendshipStatus.class);
	criteriaQuery.select(fs);
	criteriaQuery.where(criteriaBuilder.like(
		fs.<String> get("description"), description));
	List<FriendshipStatus> resultList = em.createQuery(criteriaQuery)
		.getResultList();
	if (resultList.size() == 1) {
	    return resultList.get(0);
	}
	return null;
    }

    @Override
    public Friendship findFriendshipById(int friendshipHash) {
	return em.find(Friendship.class, friendshipHash);
    }

    @Override
    public FriendshipStatus getFriendShipStatusById(int friendshipStatusId) {
	return em.find(FriendshipStatus.class, friendshipStatusId);
    }

}
