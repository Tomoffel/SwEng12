package de.shelp.util;

import javax.ejb.Stateless;

import de.shelp.dto.user.ShelpSessionTO;
import de.shelp.dto.user.UserTO;
import de.shelp.entities.ShelpSession;
import de.shelp.entities.User;

@Stateless
public class UserDtoAssembler {

    public ShelpSessionTO makeDTO(ShelpSession shelpSession) {
	ShelpSessionTO dto = new ShelpSessionTO();
	dto.setId(shelpSession.getId());
	dto.setUser(makeDTO(shelpSession.getUser()));
	dto.setUpdatedOn(shelpSession.getUpdatedOn());
	return dto;
    }

    public UserTO makeDTO(User user) {
	UserTO dto = new UserTO();
	dto.setEmail(user.getEmail());
	return dto;
    }

}
