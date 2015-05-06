package de.shelp.integration;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;

import de.shelp.dao.ShelpUserDAOLocal;
import de.shelp.dto.user.UserResponse;
import de.shelp.entities.ShelpSession;
import de.shelp.entities.User;
import de.shelp.exception.InvalidLoginException;
import de.shelp.exception.ShelpException;
import de.shelp.util.UserDtoAssembler;

@WebService
@WebContext(contextRoot = "/user")
@Stateless
public class UserIntegration {

    private static final Logger LOGGER = Logger.getLogger(UserIntegration.class);

    /**
     * EJB zur Abfrage von Datensätzen Referenz auf die EJB wird per Dependency
     * Injection gefüllt.
     */
    @EJB(beanName = "ShelpUserDAO", beanInterface = ShelpUserDAOLocal.class)
    private ShelpUserDAOLocal dao;

    /**
     * EJB zur Erzeugung von DataTransferObjects
     */
    @EJB
    private UserDtoAssembler dtoAssembler;

    public UserResponse login(String username, String password) {
	UserResponse response = new UserResponse();
	try {
	    User user = this.dao.findUserByName(username);
	    if (user != null && user.getPassword().equals(password)) {
		ShelpSession session = dao.createSession(user);
		LOGGER.info("Login erfolgreich. Session=" + session);
		response.setSession(dtoAssembler.makeDTO(session));
	    } else {
		LOGGER.info("Login fehlgeschlagen, da Kunde unbekannt oder Passwort falsch. username=" + username);
		throw new InvalidLoginException("Login fehlgeschlagen, da Kunde unbekannt oder Passwort falsch. username=" + user.getName());
	    }
	} catch (ShelpException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}
	return response;
    }

}
