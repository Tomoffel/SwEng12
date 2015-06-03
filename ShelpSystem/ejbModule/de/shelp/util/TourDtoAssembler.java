package de.shelp.util;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.shelp.dto.state.LocationTO;
import de.shelp.dto.tour.TourTO;
import de.shelp.entities.Location;
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

    // public Tour makeDAO(TourTO tourTO) {
    // Tour dao = new Tour();
    // dao.setApprovalStatus(tourTO.getApprovalStatus());
    // dao.setLocation(makeDAO(tourTO.getLocation()));
    // dao.setCapacity(tourTO.getCapacity());
    // dao.setPaymentConditions(tourTO.getPaymentConditions());
    // dao.setDeliveryConditions(tourTO.getDeliveryConditions());
    // dao.setTime(tourTO.getTime());
    // return dao;
    // }

    public Location makeDAO(LocationTO locationTO) {
	Location dao = new Location();
	dao.setId(locationTO.getId());
	dao.setDescription(locationTO.getDescription());
	dao.setZipcode(locationTO.getZipcode());
	return dao;
    }

    public TourTO makeDTO(Tour tour) {
	TourTO dto = new TourTO();
	dto.setApprovalStatus(stateDtoAssembler.makeDTO(tour.getApprovalStatus()));
	dto.setLocation(makeDTO(tour.getLocation()));
	dto.setCapacity(stateDtoAssembler.makeDTO(tour.getCapacity()));
	dto.setPaymentCondition(stateDtoAssembler.makeDTO(tour.getPaymentCondition()));
	dto.setDeliveryCondition(stateDtoAssembler.makeDTO(tour.getDeliveryCondition()));
	dto.setTime(tour.getTime().getTime());
	dto.setId(tour.getId());
	dto.setOwner(dtoAssembler.makeDTO(tour.getOwner()));
	dto.setStatus(tour.getStatus());
	dto.setUpdatedOn(tour.getUpdatedOn());
	// TODO change to requests of tour
	dto.setRequest(null);

	return dto;
    }

    private LocationTO makeDTO(Location location) {
	LocationTO dto = new LocationTO();
	dto.setId(location.getId());
	dto.setDescription(location.getDescription());
	dto.setZipcode(location.getZipcode());
	return dto;
    }

}
