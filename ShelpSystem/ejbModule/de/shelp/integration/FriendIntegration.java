package de.shelp.integration;

import javax.ejb.Stateless;
import javax.jws.WebService;

import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;

import de.shelp.dto.ReturnCodeResponse;
import de.shelp.dto.friend.FriendShipTO;
import de.shelp.dto.friend.FriendsResponse;
import de.shelp.dto.rating.RatingResponse;
import de.shelp.dto.rating.RatingTO;

@WebService
@WebContext(contextRoot = "/shelp")
@Stateless
public class FriendIntegration {

    private static final Logger LOGGER = Logger.getLogger(FriendIntegration.class);

    public FriendsResponse getFriends(int sessionId) {
	FriendsResponse response = new FriendsResponse();

	
	
	return response;
    }

    public ReturnCodeResponse changeFriendShip(FriendShipTO friendShip) {
	ReturnCodeResponse response = new ReturnCodeResponse();

	return response;
    }

    public ReturnCodeResponse addFriend(int sessionId, long friendId) {
	ReturnCodeResponse response = new ReturnCodeResponse();

	return response;
    }
}
