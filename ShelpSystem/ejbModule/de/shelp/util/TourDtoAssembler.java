package de.shelp.util;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.shelp.dto.request.RequestTO;
import de.shelp.dto.state.LocationTO;
import de.shelp.dto.tour.TourTO;
import de.shelp.entities.Location;
import de.shelp.entities.Request;
import de.shelp.entities.Tour;

@Stateless
public class TourDtoAssembler {

    /**
     * EJB zur Erzeugung von DataTransferObjects
     */
    @EJB
    private UserDtoAssembler dtoAssembler;

    @EJB
    private StateDtoAssembler stateDtoAssembler;

    /**
     * EJB zur Erzeugung von DataTransferObjects
     */
    @EJB
    private UserDtoAssembler userDtoAssembler;

    public TourTO makeDTO(Tour tour) {
	TourTO dto = new TourTO();
	dto.setApprovalStatus(stateDtoAssembler.makeDTO(tour
		.getApprovalStatus()));
	dto.setLocation(makeDTO(tour.getLocation()));
	dto.setCapacity(stateDtoAssembler.makeDTO(tour.getCapacity()));
	dto.setPaymentCondition(stateDtoAssembler.makeDTO(tour
		.getPaymentCondition()));
	dto.setDeliveryCondition(stateDtoAssembler.makeDTO(tour
		.getDeliveryCondition()));
	dto.setTime(tour.getTime().getTime());
	dto.setId(tour.getId());
	dto.setOwner(dtoAssembler.makeDTO(tour.getOwner()));
	dto.setStatus(tour.getStatus());
	dto.setUpdatedOn(tour.getUpdatedOn());
	dto.setRequest(makeDTO(tour.getRequest()));

	return dto;
    }

    private List<RequestTO> makeDTO(List<Request> requests) {
	List<RequestTO> dtos = new ArrayList<RequestTO>();
	for (Request request : requests) {
	    RequestTO dto = new RequestTO();
	    
	    dto.setId(request.getId());
	    dto.setNotice(request.getNotice());
	    dto.setSourceUser(userDtoAssembler.makeDTO(request.getSourceUser()));
	    dto.setTargetUser(userDtoAssembler.makeDTO(request.getTargetUser()));
	    dto.setUpdatedOn(request.getUpdatedOn().getTime());
	    dto.setStatus(request.getStatus());
	    dtos.add(dto);
	}

	return dtos;
    }

    private LocationTO makeDTO(Location location) {
	LocationTO dto = new LocationTO();
	dto.setId(location.getId());
	dto.setDescription(location.getDescription());
	dto.setZipcode(location.getZipcode());
	return dto;
    }

}
