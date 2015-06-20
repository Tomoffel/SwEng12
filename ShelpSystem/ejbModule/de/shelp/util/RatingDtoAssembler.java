package de.shelp.util;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.shelp.dto.rating.RatingTO;
import de.shelp.entities.Rating;


/**
 * Klasse zur Umwandlung von RatingObjekten in transportfähige Objekte.
 * @author Thomas Sennekamp
 *
 */
@Stateless
public class RatingDtoAssembler {
	
    /**
     * EJB zur Erzeugung von DataTransferObjects
     */
    @EJB
    private UserDtoAssembler dtoAssembler;

    /**
     * Methode zum Umwandeln des Objektes
     * @param r
     * @return
     */
    public RatingTO makeDTO(Rating r) {
	RatingTO dto = new RatingTO();

	dto.setId(r.getId());
	dto.setSourceUser(dtoAssembler.makeDTO(r.getSourceUser()));
	dto.setTargetUser(dtoAssembler.makeDTO(r.getTargetUser()));
	dto.setNotice(r.getNotice());
	dto.setRating(r.getRating());
	return dto;
    }

}


