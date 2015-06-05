package de.shelp.util;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.shelp.dto.request.RequestTO;
import de.shelp.entities.Request;

@Stateless
public class RequestDtoAssembler {

    /**
     * EJB zur Erzeugung von DataTransferObjects
     */
    @EJB
    private UserDtoAssembler userDtoAssembler;

    /**
     * EJB zur Erzeugung von DataTransferObjects
     */
    @EJB
    private TourDtoAssembler tourDtoAssembler;

    public RequestTO makeDTO(Request request) {
	RequestTO dto = new RequestTO();

	dto.setId(request.getId());
	dto.setNotice(request.getNotice());
	dto.setSourceUser(userDtoAssembler.makeDTO(request.getSourceUser()));
	dto.setTargetUser(userDtoAssembler.makeDTO(request.getTargetUser()));
	dto.setTour(tourDtoAssembler.makeDTO(request.getTour()));
	dto.setUpdatedOn(request.getUpdatedOn().getTime());
	// TODO add the wishlists
	dto.setWishlist(null);

	return dto;
    }

}
