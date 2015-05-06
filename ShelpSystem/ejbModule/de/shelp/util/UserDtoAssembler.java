package de.shelp.util;

import javax.ejb.Stateless;

import de.shelp.dto.user.ShelpSessionTO;
import de.shelp.entities.ShelpSession;

@Stateless
public class UserDtoAssembler {

    public ShelpSessionTO makeDTO(ShelpSession shelpSession) {
	ShelpSessionTO dto = new ShelpSessionTO();
	dto.setId(shelpSession.getId());
	dto.setUsername(shelpSession.getUsername());
	dto.setCreationOn(shelpSession.getCreationOn());
	return dto;
    }

}
