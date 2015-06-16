package de.shelp.dao.local;

import java.util.List;

import javax.ejb.Local;

import de.shelp.entities.Friendship;

@Local
public interface ShelpFriendDAOLocal {

    public void saveFriendship(Friendship friendship);

    public Friendship findFriendshipById(int friendshipHash);

    public void deleteFriendship(Friendship friendship);

    public List<Friendship> getDeniedFriendships();

}
