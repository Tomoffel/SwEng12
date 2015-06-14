package de.shelp.util;

import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import de.shelp.dao.local.ShelpUserDAOLocal;
import de.shelp.entities.ShelpSession;
import de.shelp.entities.User;
import de.shelp.exception.SessionNotExistException;
import de.shelp.exception.UserNotExistException;

@Stateless
public class ShelpHelper {
    private static final Logger LOGGER = Logger
	    .getLogger(ShelpHelper.class);

    /**
     * Helper-method to check current session
     * 
     * @param sessionId
     * @return valid session
     * @throws SessionNotExistException
     */
    public ShelpSession checkSession(int sessionId, ShelpUserDAOLocal dao)
	    throws SessionNotExistException {

	// get current Session
	ShelpSession session =dao.getSession(sessionId);

	if (session == null) {
	    String message = "Session-Id existiert nicht.";
	    LOGGER.info(message);
	    throw new SessionNotExistException(message);
	}
	return session;
    }

    /**
     * Helper-method to check current session
     * 
     * @param sessionId
     * @return valid session
     * @throws SessionNotExistException
     */
    public User checkUser(String userId, ShelpUserDAOLocal dao) throws UserNotExistException {

	// get current Session
	User user = dao.findUserByName(userId);

	if (user == null) {
	    String message = "Benutzer " + userId + " existiert nicht.";
	    LOGGER.info(message);
	    throw new UserNotExistException(message);
	}
	return user;
    }

}
