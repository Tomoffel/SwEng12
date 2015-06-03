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
import de.shelp.entities.ShelpSession;
import de.shelp.entities.User;
import de.shelp.enums.FriendshipStatus;
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
     * EJB zur Abfrage von Datensätzen Referenz auf die EJB wird per Dependency
     * Injection gefüllt.
     */
    @EJB(beanName = "ShelpUserDAO", beanInterface = ShelpUserDAOLocal.class)
    private ShelpUserDAOLocal daoUser;

    /**
     * EJB zur Abfrage von Datensätzen Referenz auf die EJB wird per Dependency
     * Injection gefüllt.
     */
    @EJB(beanName = "ShelpFriendDAO", beanInterface = ShelpFriendDAOLocal.class)
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
		LOGGER.info("Session nicht gültig.");
		throw new SessionNotExistException("Session nicht gültig.");
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

    public ReturnCodeResponse acceptFriendship(int friendshipId, int sessionId) {
	return changeFriendShip(friendshipId, FriendshipStatus.ACCEPT,
		sessionId);
    }

    public ReturnCodeResponse deniedFriendship(int friendshipId, int sessionId) {
	return changeFriendShip(friendshipId, FriendshipStatus.DENIED,
		sessionId);
    }

    public ReturnCodeResponse deleteFriendship(int friendshipId, int sessionId) {
	ReturnCodeResponse response = new ReturnCodeResponse();
	try {
	    Friendship friendship = checkFriendship(sessionId, friendshipId);

	    daoFriend.deleteFriendship(friendship);
	} catch (ShelpException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}
	return response;
    }

    private ReturnCodeResponse changeFriendShip(int friendshipId,
	    FriendshipStatus status, int sessionId) {
	ReturnCodeResponse response = new ReturnCodeResponse();

	try {
	    Friendship friendship = checkFriendship(sessionId, friendshipId);
	    friendship.setStatus(status);
	    daoFriend.saveFriendship(friendship);

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
		throw new SessionNotExistException(
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

	    friendship.setStatus(FriendshipStatus.ASKED);

	    Friendship dbFriendship = daoFriend.findFriendshipById(friendship
		    .getFriendshipHash());
	    if (dbFriendship == null) {
		friendship.setId(friendship.getFriendshipHash());
		friendship.setChangeOn(new Date());
		daoFriend.saveFriendship(friendship);
	    } else {

		if (dbFriendship.getStatus().equals(FriendshipStatus.ASKED)) {
		    LOGGER.info("Anfrage wurde schon gestellt zwischen "
			    + session.getUser() + " und " + targetUser + ".");
		    throw new ShelpException(ReturnCode.ERROR,
			    "Anfrage wurde schon gestellt zwischen "
				    + session.getUser() + " und " + targetUser
				    + ".");
		} else if (dbFriendship.getStatus().equals(
			FriendshipStatus.DENIED)) {
		    LOGGER.info("Anfrage wurde schon abgelehnt zwischen "
			    + session.getUser() + " und " + targetUser + ".");
		    throw new ShelpException(ReturnCode.ERROR,
			    "Anfrage wurde schon abgelehnt zwischen "
				    + session.getUser() + " und " + targetUser
				    + ".");
		} else if (dbFriendship.getStatus().equals(
			FriendshipStatus.ACCEPT)) {
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

    private Friendship checkFriendship(int sessionId, int friendshipId)
	    throws ShelpException {

	ShelpSession session = daoUser.getSession(sessionId);
	if (session == null) {
	    LOGGER.info("Session-Id existiert nicht.");
	    throw new SessionNotExistException("Session-Id existiert nicht.");
	}

	Friendship friendship = daoFriend.findFriendshipById(friendshipId);
	if (friendship == null) {
	    LOGGER.info("Freundschaft existiert nicht!");
	    throw new ShelpException(ReturnCode.ERROR,
		    ("Freundschaft existiert nicht!"));
	}

	if (friendship.getInitiatorUser().equals(session.getUser())
		|| friendship.getRecipientUser().equals(session.getUser())) {
	    return friendship;
	} else {
	    LOGGER.warn("Zugriff verweigert. Anfragende Session "
		    + session.getId() + " ist nicht an der Freundschaft "
		    + friendshipId + " beteiligt!");
	    throw new ShelpException(
		    ReturnCode.PERMISSION_DENIED,
		    ("Zugriff verweigert. Anfragende Session "
			    + session.getId()
			    + " ist nicht an der Freundschaft " + friendshipId + " beteiligt!"));
	}
    }

}
