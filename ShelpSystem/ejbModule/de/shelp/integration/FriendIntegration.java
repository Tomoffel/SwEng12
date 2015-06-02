package de.shelp.integration;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;

import de.shelp.dao.local.ShelpUserDAOLocal;
import de.shelp.dto.ReturnCodeResponse;
import de.shelp.dto.friend.FriendShipTO;
import de.shelp.dto.friend.FriendsResponse;
import de.shelp.entities.Friendship;
import de.shelp.entities.ShelpSession;
import de.shelp.entities.User;
import de.shelp.exception.SessionNotExistException;
import de.shelp.exception.ShelpException;
import de.shelp.util.FriendDtoAssembler;
import de.shelp.util.RatingDtoAssembler;

@WebService
@WebContext(contextRoot = "/shelp")
@Stateless
public class FriendIntegration {

	private static final Logger LOGGER = Logger
			.getLogger(FriendIntegration.class);

	/**
	 * EJB zur Abfrage von Datensätzen Referenz auf die EJB wird per Dependency
	 * Injection gefüllt.
	 */
	@EJB(beanName = "ShelpUserDAO", beanInterface = ShelpUserDAOLocal.class)
	private ShelpUserDAOLocal daoUser;
	
	
	/**
	 * EJB zur Erzeugung von DataTransferObjects
	 */
	@EJB
	private FriendDtoAssembler dtoAssembler;

	public FriendsResponse getFriends(int sessionId)
			throws SessionNotExistException {
		FriendsResponse response = new FriendsResponse();

		ShelpSession session = daoUser.getSession(sessionId);

		try {
			if (session == null) {
				LOGGER.info("Session nicht gültig.");
				throw new SessionNotExistException("Session nicht gültig.");
			}

			User user = session.getUser();

			List<Friendship> friends = user.getFriends();

			List<FriendShipTO> friendsTO = new ArrayList<FriendShipTO>();
			for (Friendship f : friends) {
				friendsTO.add(dtoAssembler.makeDTO(f));
			}

		} catch (ShelpException e) {
			response.setReturnCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		}
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
