package de.shelp.dto.friend;

import java.util.List;

import de.shelp.dto.ReturnCodeResponse;
import de.shelp.dto.rating.RatingTO;
import de.shelp.entities.Friendship;

public class FriendsResponse extends ReturnCodeResponse {

	private static final long serialVersionUID = 8974472260940989428L;
	private List<FriendShipTO> friendshipTO;

	public List<FriendShipTO> getFriends() {
		return friendshipTO;
	}

	public void setFriends(List<FriendShipTO> friendsTO) {
		this.friendshipTO = friendsTO;

	}

}
