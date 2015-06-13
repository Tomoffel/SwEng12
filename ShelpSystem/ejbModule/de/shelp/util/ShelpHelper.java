package de.shelp.util;

import javax.ejb.EJB;

import org.jboss.logging.Logger;

import de.shelp.dao.local.ShelpUserDAOLocal;
import de.shelp.entities.ShelpSession;
import de.shelp.exception.SessionNotExistException;
import de.shelp.integration.RequestIntegration;

public class ShelpHelper {
	private static final Logger LOGGER = Logger
			.getLogger(RequestIntegration.class);
	
	/**
	 * EJB zur Abfrage von Datensätzen Referenz auf die EJB wird per Dependency
	 * Injection gefüllt.
	 */
	@EJB(beanName = "ShelpUserDAO", beanInterface = ShelpUserDAOLocal.class)
	private static ShelpUserDAOLocal daoUser;

	/**
	 * Helper-method to check current session
	 * 
	 * @param sessionId
	 * @return valid session
	 * @throws SessionNotExistException 
	 */
	public static ShelpSession checkSession(int sessionId)
			throws SessionNotExistException {

		// get current Session
		ShelpSession session = daoUser.getSession(sessionId);

		if (session == null) {
			String message = "Session-Id existiert nicht.";
			LOGGER.info(message);
			throw new SessionNotExistException(message);
		}
		return session;
	}
	
}
