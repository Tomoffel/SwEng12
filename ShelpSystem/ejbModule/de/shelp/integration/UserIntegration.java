package de.shelp.integration;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;

import de.shelp.dao.ShelpUserDAOLocal;
import de.shelp.dto.ReturnCodeResponse;
import de.shelp.dto.user.UserResponse;
import de.shelp.dto.user.UserTO;
import de.shelp.dto.user.UsersResponse;
import de.shelp.entities.ShelpSession;
import de.shelp.entities.User;
import de.shelp.enums.ReturnCode;
import de.shelp.exception.InvalidLoginException;
import de.shelp.exception.ShelpException;
import de.shelp.exception.UserExistEcxeption;
import de.shelp.util.UserDtoAssembler;

@WebService
@WebContext(contextRoot = "/shelp")
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

    public UserResponse regUser(String username, String password, String email) {
	UserResponse response = new UserResponse();
	try {
	    User user = this.dao.findUserByName(username);
	    if (user == null) {
		user = this.dao.createUser(username, password, email);
		ShelpSession session = dao.createSession(user);
		LOGGER.info("Benutzer " + user + " erfolgreich angelegt. Session=" + session);
		response.setSession(dtoAssembler.makeDTO(session));
	    } else {
		LOGGER.info("Registrierung fehlgeschlag. Benutzername existiert schon " + user);
		throw new UserExistEcxeption(ReturnCode.ERROR, "Registrierung fehlgeschlag. Benutername schon vergeben.");
	    }
	} catch (ShelpException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}
	return response;
    }

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

    public ReturnCodeResponse logout(int sessionId) {
	dao.closeSession(sessionId);
	ReturnCodeResponse response = new ReturnCodeResponse();
	LOGGER.info("Logout erfolgreich. Session=" + sessionId);
	return response;
    }

    public UsersResponse searchUsers(String searchText) {
	UsersResponse response = new UsersResponse();

	List<User> users = dao.searchUsers(searchText);

	List<UserTO> usersTO = new ArrayList<UserTO>();
	for (User u : users) {
	    usersTO.add(dtoAssembler.makeDTO(u));
	}

	response.addUserList(usersTO);
	
	return response;
    }
}
