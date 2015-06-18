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
import de.shelp.exception.ShelpException;
import de.shelp.util.FriendDtoAssembler;
import de.shelp.util.ShelpHelper;

/**
 * Webservice-Schnittstelle zur Behandlung von Freundschaften
 * 
 * @author anwender
 *
 */
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

    @EJB
    private ShelpHelper helper;

    /**
     * EJB zur Beauftragung zum Versenden von E-Mails
     */
    @EJB
    private MailRequesterBean mailRequester;

    /**
     * Gibt aktuelle Freunde zurück
     * 
     * @param sessionId Aktuelle SessionId
     * @return FriendsResponse
     */
    public FriendsResponse getFriends(int sessionId) {
	FriendsResponse response = new FriendsResponse();

	try {
	    ShelpSession session = helper.checkSession(sessionId, daoUser);

	    User user = session.getUser();

	    List<Friendship> friends = user.getFriendships();

	    List<FriendshipTO> friendsTO = new ArrayList<FriendshipTO>();
	    for (Friendship f : friends) {
		friendsTO.add(dtoAssembler.makeDTO(f));
	    }

	    response.setFriends(friendsTO);
	    LOGGER.info(friendsTO.size()
		    + " Freundschaften wurde zur SessionId " + sessionId
		    + " gefunden.");
	} catch (ShelpException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}

	return response;

    }

    /**
     * Akzeptiert eine Freundschaft
     * 
     * @param friendshipId ID der Freundschaft
     * @param sessionId Aktuelle SessionID
     * @return
     */
    public ReturnCodeResponse acceptFriendship(int friendshipId, int sessionId) {
	return changeFriendShip(friendshipId, FriendshipStatus.ACCEPT,
		sessionId);
    }

    /**
     * Lehnt eine Freundschaft ab
     * 
     * @param friendshipId
     * @param sessionId
     * @return
     */
    public ReturnCodeResponse deniedFriendship(int friendshipId, int sessionId) {
	return changeFriendShip(friendshipId, FriendshipStatus.DENIED,
		sessionId);
    }

    /**
     * Löscht eine Freundschaft
     * 
     * @param friendshipId
     * @param sessionId
     * @return
     */
    public ReturnCodeResponse deleteFriendship(int friendshipId, int sessionId) {
	ReturnCodeResponse response = new ReturnCodeResponse();
	try {
	    Friendship friendship = checkFriendship(sessionId, friendshipId,
		    false);

	    daoFriend.deleteFriendship(friendship);
	    LOGGER.info("Die Freundschaft " + friendshipId + " wurde gelöscht.");
	} catch (ShelpException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}
	return response;
    }

    /**
     * Ändert eine Freundschaft
     * 
     * @param friendshipId
     * @param status
     * @param sessionId
     * @return
     */
    private ReturnCodeResponse changeFriendShip(int friendshipId,
	    FriendshipStatus status, int sessionId) {
	ReturnCodeResponse response = new ReturnCodeResponse();

	try {
	    Friendship friendship = checkFriendship(sessionId, friendshipId,
		    true);
	    friendship.setStatus(status);
	    friendship.setChangeOn(new Date());
	    daoFriend.saveFriendship(friendship);

	    LOGGER.info("Der Status der Freundschaft " + friendshipId
		    + " wurde auf  " + status + " gewechselt.");

	} catch (ShelpException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}

	return response;
    }

    /**
     * Fügt einen neuen Freund hinzu
     * 
     * @param sessionId
     * @param friendId
     * @return
     */
    public ReturnCodeResponse addFriend(int sessionId, String friendId) {
	ReturnCodeResponse response = new ReturnCodeResponse();

	try {
	    ShelpSession session = helper.checkSession(sessionId, daoUser);
	    User targetUser = helper.checkUser(friendId, daoUser);
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

		LOGGER.info("Zwischen " + session.getUser() + " und "
			+ targetUser
			+ " wurde eine Freunschaftsanfrage erstellt.");
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

    /**
     * Method to check a Friendship
     * 
     * @param sessionId
     * @param friendshipId
     * @param checkChange
     * @return
     * @throws ShelpException
     */
    private Friendship checkFriendship(int sessionId, int friendshipId,
	    boolean checkChange) throws ShelpException {

	ShelpSession session = helper.checkSession(sessionId, daoUser);

	Friendship friendship = daoFriend.findFriendshipById(friendshipId);
	if (friendship == null) {
	    LOGGER.info("Freundschaft existiert nicht!");
	    throw new ShelpException(ReturnCode.ERROR,
		    "Freundschaft existiert nicht!");
	}
	if (checkChange) {
	    if (!friendship.getRecipientUser().equals(session.getUser())) {
		LOGGER.warn("Zugriff verweigert. Anfragende Session "
			+ session.getId()
			+ " ist nicht der Empfänger der Freundschaftsanfrage "
			+ friendshipId + "!");
		throw new ShelpException(
			ReturnCode.PERMISSION_DENIED,
			"Zugriff verweigert. Anfragende Session "
				+ session.getId()
				+ " ist nicht der Empfänger der Freundschaftsanfrage "
				+ friendshipId + "!");
	    } else if (!friendship.getStatus().equals(FriendshipStatus.ASKED)) {
		LOGGER.warn("Freundschaftsanfrage wurde schon angenommen/abgelehnt.");
		throw new ShelpException(ReturnCode.ERROR,
			"Freundschaftsanfrage wurde schon angenommen/abgelehnt.");
	    }
	} else if (!friendship.getInitiatorUser().equals(session.getUser())
		&& !friendship.getRecipientUser().equals(session.getUser())) {
	    LOGGER.warn("Zugriff verweigert. Anfragende Session "
		    + session.getId() + " ist nicht an der Freundschaft "
		    + friendshipId + " beteiligt!");
	    throw new ShelpException(ReturnCode.PERMISSION_DENIED,
		    "Zugriff verweigert. Anfragende Session " + session.getId()
			    + " ist nicht an der Freundschaft " + friendshipId
			    + " beteiligt!");
	}
	

	String logMessage = "Freundschaft " + friendship
		+ " wurde verändern. Status ist nun " + friendship.getStatus();
	LOGGER.info(logMessage);
	mailRequester.printLetter(logMessage, friendship.getRecipientUser().getEmail());
	mailRequester.printLetter(logMessage, friendship.getInitiatorUser().getEmail());
	return friendship;

    }

}
