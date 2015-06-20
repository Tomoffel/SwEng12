package de.shelp.util;

import javax.ejb.Stateless;

import de.shelp.dto.user.ShelpSessionTO;
import de.shelp.dto.user.UserTO;
import de.shelp.entities.ShelpSession;
import de.shelp.entities.User;

/** Klasse zur Umwandlung von UserObjekten
 * @author Jos Werner
 */
@Stateless
public class UserDtoAssembler {

	/**
	 * Methode zum Umwandeln des Objektes
	 * @param shelpSession
	 * @return Umgewandelts Objekt
	 */
	public ShelpSessionTO makeDTO(ShelpSession shelpSession) {
		ShelpSessionTO dto = new ShelpSessionTO();
		dto.setId(shelpSession.getId());
		dto.setUser(makeDTO(shelpSession.getUser()));
		dto.setUpdatedOn(shelpSession.getUpdatedOn());
		return dto;
	}

	/**
	 * Methode zum Umwandeln des Objektes
	 * @param user
	 * @return Umgewandelts Objekt
	 */
	public UserTO makeDTO(User user) {
		UserTO dto = new UserTO();
		dto.setEmail(user.getEmail());
		return dto;
	}

}
