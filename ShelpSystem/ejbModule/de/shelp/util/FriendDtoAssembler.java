package de.shelp.util;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.shelp.dto.friend.FriendshipTO;
import de.shelp.dto.friend.FriendshipStatusTO;
import de.shelp.dto.rating.RatingTO;
import de.shelp.entities.Friendship;
import de.shelp.entities.FriendshipStatus;

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
		dto.setStatus(makeDTO(f.getStatus()));
		
		return dto;

	}

	private FriendshipStatusTO makeDTO(FriendshipStatus status) {
		FriendshipStatusTO friendShipStatus = new FriendshipStatusTO();
		friendShipStatus.setDescription(status.getDescription());
		friendShipStatus.setId(status.getId());
		return friendShipStatus;
	}

}
