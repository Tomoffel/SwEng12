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
	dto.setUsername(shelpSession.getUsername());
	dto.setCreationOn(shelpSession.getCreationOn());
	return dto;
    }

    public UserTO makeDTO(User user) {
	UserTO dto = new UserTO();
	dto.setName(user.getName());
	dto.setEmail(user.getEmail());
	return dto;
    }

}
