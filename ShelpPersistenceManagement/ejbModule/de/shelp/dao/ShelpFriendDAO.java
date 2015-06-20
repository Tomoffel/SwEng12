package de.shelp.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import de.shelp.dao.local.ShelpFriendDAOLocal;
import de.shelp.entities.Friendship;
import de.shelp.enums.FriendshipStatus;

/**
 * Session Bean Klasse in der alle Methoden der {@link ShelpFriendDAOLocal}
 * ausimplementiert sind um alle Datenbankzugriffe für die
 * Freundschaftsverwaltung zu realisieren.
 * 
 * @author Thomas Sennekamp
 *
 */
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

    @Override
    public List<Friendship> getDeniedFriendships() {
	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	CriteriaQuery<Friendship> criteriaQuery = criteriaBuilder
		.createQuery(Friendship.class);
	Root<Friendship> friendship = criteriaQuery.from(Friendship.class);
	criteriaQuery.select(friendship);
	criteriaQuery.where(criteriaBuilder.equal(
		friendship.<FriendshipStatus> get("status"),
		FriendshipStatus.DENIED));
	return em.createQuery(criteriaQuery).getResultList();
    }

}
