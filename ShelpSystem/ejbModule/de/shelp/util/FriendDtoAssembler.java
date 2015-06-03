package de.shelp.util;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.shelp.dto.friend.FriendshipTO;
import de.shelp.entities.Friendship;

@Stateless
public class FriendDtoAssembler {

    /**
     * EJB zur Erzeugung von DataTransferObjects
     */
    @EJB
    private UserDtoAssembler dtoAssembler;

    public FriendshipTO makeDTO(Friendship f) {

	FriendshipTO dto = new FriendshipTO();
	dto.setInitiatorUser(dtoAssembler.makeDTO(f.getInitiatorUser()));
	dto.setRecipientUser(dtoAssembler.makeDTO(f.getRecipientUser()));
	dto.setId(f.getId());
	dto.setStatus(f.getStatus());

	return dto;

    }

}
