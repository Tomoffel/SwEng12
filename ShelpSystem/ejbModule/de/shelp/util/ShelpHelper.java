package de.shelp.util;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import de.shelp.dao.local.ShelpUserDAOLocal;
import de.shelp.entities.ShelpSession;
import de.shelp.entities.User;
import de.shelp.exception.SessionNotExistException;
import de.shelp.exception.UserNotExistException;

/**
 * Hilfsklasse die zwei Methoden zur Überprüfung enthält. 1. Methode
 * {@link #checkSession(int, ShelpUserDAOLocal)} zur Überprüftung der aktuellen
 * Session auf Gültigkeit. 2. Methode
 * {@link #checkUser(String, ShelpUserDAOLocal)} zur Überprüfung eines Benutzers
 * {@link User}.
 * 
 * In Fehlerfällen wird die entsprechende Exception
 * {@link SessionNotExistException} oder {@link UserNotExistException} geworfen.
 * 
 * @author Thomas Sennekamp
 *
 */
@Stateless
@LocalBean
public class ShelpHelper {
	private static final Logger LOGGER = Logger.getLogger(ShelpHelper.class);

	/**
	 * Hilfsmethode zur Überprüfung einer SessionID
	 * 
	 * @param sessionId
	 * @return session Gültige Session
	 * @throws SessionNotExistException
	 *             Wird im Fehlerfall geworfen.
	 */
	public ShelpSession checkSession(int sessionId, ShelpUserDAOLocal dao)
			throws SessionNotExistException {

		// get current Session
		ShelpSession session = dao.getSession(sessionId);

		if (session == null) {
			String message = "Session-Id existiert nicht.";
			LOGGER.info(message);
			throw new SessionNotExistException(message);
		}

		// Session hat etwas getan und muss aktualisiert werden
		dao.updateSession(session);

		return session;
	}

	/**
	 * Hilfsfunktion zur Überprüfung eines Benutzers {@link User} anhand der
	 * UserID.
	 * 
	 * @param sessionId
	 *            Aktuelle Session-ID
	 * @return User Gültiger User
	 * @throws SessionNotExistException
	 *             Wird im Fehlerfall geworfen.
	 */
	public User checkUser(String userId, ShelpUserDAOLocal dao)
			throws UserNotExistException {

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
