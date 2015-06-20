package de.shelp.dto.friend;

import java.util.List;

import de.shelp.dto.ReturnCodeResponse;

/**
 * Klasse repräsentiert die Rückgabe für mehrere Freundschaften (
 * {@link FriendshipTO}) in einer Liste. Erbt von {@link ReturnCodeResponse}.
 * 
 * @author Thomas Sennekamp
 *
 */
public class FriendsResponse extends ReturnCodeResponse {

    private static final long serialVersionUID = 8974472260940989428L;
    private List<FriendshipTO> friendshipTO;

    public List<FriendshipTO> getFriends() {
	return friendshipTO;
    }

    public void setFriends(List<FriendshipTO> friendsTO) {
	this.friendshipTO = friendsTO;

    }

}
