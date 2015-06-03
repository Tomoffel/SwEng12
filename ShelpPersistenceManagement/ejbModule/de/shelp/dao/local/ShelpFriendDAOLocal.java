package de.shelp.dao.local;

import de.shelp.entities.Friendship;
import de.shelp.entities.FriendshipStatus;

public interface ShelpFriendDAOLocal {
	
	public void createFriendship(Friendship friendship);

	public FriendshipStatus getFriendshipStatus(String description);

	public Friendship findFriendshipById(int friendshipHash);

	public FriendshipStatus getFriendShipStatusById(int friendshipStatusId);


}
