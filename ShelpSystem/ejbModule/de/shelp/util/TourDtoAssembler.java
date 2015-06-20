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

/**
 * Klasse zur Umwandlung von Touren
 * 
 * @author Jos Werner
 *
 */
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

	/**
	 * EJB zur Erzeugung von DataTransferObjects
	 */
	@EJB
	private RequestDtoAssembler requestDtoAssembler;

	
	/**
	 *  Methode zum Umwandeln des Objektes
	 * @param tour
	 * @return Umgewandelts Objekt
	 */
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
		dto.setUpdated(tour.isUpdated());
		dto.setRequest(makeDTO(tour.getRequest()));

		return dto;
	}

	/**
	 *  Methode zum Umwandeln des Objektes
	 * @param requests 
	 * @return  Umgewandelts Objekt
	 */
	private List<RequestTO> makeDTO(List<Request> requests) {
		List<RequestTO> dtos = new ArrayList<RequestTO>();
		for (Request request : requests) {
			RequestTO dto = new RequestTO();

			dto.setId(request.getId());
			dto.setNotice(request.getNotice());
			dto.setSourceUser(userDtoAssembler.makeDTO(request.getSourceUser()));
			dto.setTargetUser(userDtoAssembler.makeDTO(request.getTargetUser()));
			dto.setUpdated(request.isUpdated());
			dto.setStatus(request.getStatus());
			dto.setWishes(requestDtoAssembler.makeDTO(request.getWishes()));
			dtos.add(dto);
		}

		return dtos;
	}

	/**
	 *  Methode zum Umwandeln des Objektes
	 * @param location
	 * @return Umgewandelts Objekt
	 */
	private LocationTO makeDTO(Location location) {
		LocationTO dto = new LocationTO();
		dto.setId(location.getId());
		dto.setDescription(location.getDescription());
		dto.setZipcode(location.getZipcode());
		return dto;
	}

}
