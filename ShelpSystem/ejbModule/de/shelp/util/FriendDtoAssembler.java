package de.shelp.util;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.shelp.dto.friend.FriendshipTO;
import de.shelp.entities.Friendship;

/**
 * Klasse zur Umwandlung von UserObjekten in transportfähige Objekte.
 * @author Jos Werner
 *
 */

@Stateless
public class FriendDtoAssembler {

    /**
     * EJB zur Erzeugung von DataTransferObjects
     */
    @EJB
    private UserDtoAssembler dtoAssembler;

    /**
     * Methode zum Umwandeln des Objektes
     * @param f Objekt vom Typ FriendShip zur Umwandlung
     * @return Transportfähiges Objekt 
     */
    public FriendshipTO makeDTO(Friendship f) {

	FriendshipTO dto = new FriendshipTO();
	dto.setInitiatorUser(dtoAssembler.makeDTO(f.getInitiatorUser()));
	dto.setRecipientUser(dtoAssembler.makeDTO(f.getRecipientUser()));
	dto.setId(f.getId());
	dto.setStatus(f.getStatus());
	dto.setChangedOn(f.getChangeOn().getTime());

	return dto;

    }

}
