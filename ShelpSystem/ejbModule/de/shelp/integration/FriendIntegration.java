package de.shelp.integration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;

import de.shelp.dao.local.ShelpFriendDAOLocal;
import de.shelp.dao.local.ShelpUserDAOLocal;
import de.shelp.dto.ReturnCodeResponse;
import de.shelp.dto.friend.FriendsResponse;
import de.shelp.dto.friend.FriendshipTO;
import de.shelp.entities.Friendship;
import de.shelp.entities.FriendshipStatus;
import de.shelp.entities.ShelpSession;
import de.shelp.entities.User;
import de.shelp.enums.ReturnCode;
import de.shelp.exception.SessionNotExistException;
import de.shelp.exception.ShelpException;
import de.shelp.exception.UserNotExistEcxeption;
import de.shelp.util.FriendDtoAssembler;

@WebService
@WebContext(contextRoot = "/shelp")
@Stateless
public class FriendIntegration {

    private static final Logger LOGGER = Logger
	    .getLogger(FriendIntegration.class);

    /**
     * EJB zur Abfrage von Datens�tzen Referenz auf die EJB wird per Dependency
     * Injection gef�llt.
     */
    @EJB(beanName = "ShelpUserDAO", beanInterface = ShelpUserDAOLocal.class)
    private ShelpUserDAOLocal daoUser;

    /**
     * EJB zur Abfrage von Datens�tzen Referenz auf die EJB wird per Dependency
     * Injection gef�llt.
     */
    @EJB(beanName = "ShelpFriendshipDAO", beanInterface = ShelpFriendDAOLocal.class)
    private ShelpFriendDAOLocal daoFriend;

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
		LOGGER.info("Session nicht g�ltig.");
		throw new SessionNotExistException("Session nicht g�ltig.");
	    }

	    User user = session.getUser();

	    List<Friendship> friends = user.getFriends();

	    List<FriendshipTO> friendsTO = new ArrayList<FriendshipTO>();
	    for (Friendship f : friends) {
		friendsTO.add(dtoAssembler.makeDTO(f));
	    }

	} catch (ShelpException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}
	return response;

    }

    public ReturnCodeResponse changeFriendShip(int friendshipId,
	    int friendshipStatusId) {
	ReturnCodeResponse response = new ReturnCodeResponse();

	try {
	    Friendship friendship = daoFriend.findFriendshipById(friendshipId);
	    if (friendship == null) {
		LOGGER.info("Freundschaft existiert nicht!");
		throw new ShelpException(ReturnCode.ERROR,
			("Freundschaft existiert nicht!"));
	    }

	    friendship.setStatus(daoFriend
		    .getFriendShipStatusById(friendshipStatusId));
	    daoFriend.createFriendship(friendship);
	} catch (ShelpException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}

	return response;
    }

    public ReturnCodeResponse addFriend(int sessionId, String friendId) {
	ReturnCodeResponse response = new ReturnCodeResponse();

	ShelpSession session = daoUser.getSession(sessionId);
	User targetUser = daoUser.findUserByName(friendId);

	try {
	    if (targetUser == null) {
		LOGGER.info("TargetUser existiert nicht.");
		throw new UserNotExistEcxeption(ReturnCode.ERROR,
			"TargetUser existiert nicht.");
	    }
	    if (session == null) {
		LOGGER.info("Session-Id existiert nicht.");
		throw new UserNotExistEcxeption(ReturnCode.ERROR,
			"Session-Id existiert nicht.");
	    }
	    if (targetUser.equals(session.getUser())) {
		LOGGER.info("Man kann nicht mit sich selbst befreundet sein "
			+ session.getUser());
		throw new ShelpException(ReturnCode.ERROR,
			"Man kann nicht mit sich selbst befreundet sein "
				+ session.getUser());
	    }

	    Friendship friendship = new Friendship();
	    friendship.setInitiatorUser(session.getUser());
	    friendship.setRecipientUser(targetUser);

	    // TODO Strings sinnvoll auslagern
	    FriendshipStatus asked = daoFriend.getFriendshipStatus("Angefragt");
	    friendship.setStatus(asked);

	    Friendship dbFriendship = daoFriend.findFriendshipById(friendship
		    .getFriendshipHash());
	    if (dbFriendship == null) {
		friendship.setId(friendship.getFriendshipHash());
		friendship.setChangeOn(new Date());
		daoFriend.createFriendship(friendship);
	    } else {
		FriendshipStatus denied = daoFriend
			.getFriendshipStatus("Abgelehnt");
		FriendshipStatus accepted = daoFriend
			.getFriendshipStatus("Aktzeptiert");

		if (dbFriendship.getStatus().equals(asked)) {
		    LOGGER.info("Anfrage wurde schon gestellt zwischen "
			    + session.getUser() + " und " + targetUser + ".");
		    throw new ShelpException(ReturnCode.ERROR,
			    "Anfrage wurde schon gestellt zwischen "
				    + session.getUser() + " und " + targetUser
				    + ".");
		} else if (dbFriendship.getStatus().equals(denied)) {
		    LOGGER.info("Anfrage wurde schon abgelehnt zwischen "
			    + session.getUser() + " und " + targetUser + ".");
		    throw new ShelpException(ReturnCode.ERROR,
			    "Anfrage wurde schon abgelehnt zwischen "
				    + session.getUser() + " und " + targetUser
				    + ".");
		} else if (dbFriendship.getStatus().equals(accepted)) {
		    LOGGER.info("Anfrage wurde schon angenommen zwischen "
			    + session.getUser() + " und " + targetUser + ".");
		    throw new ShelpException(ReturnCode.ERROR,
			    "Anfrage wurde schon angenommen zwischen "
				    + session.getUser() + " und " + targetUser
				    + ".");
		}
	    }

	} catch (ShelpException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}
	return response;
    }

}
