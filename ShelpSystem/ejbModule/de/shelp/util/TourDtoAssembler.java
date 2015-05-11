package de.shelp.util;

import javax.ejb.Stateless;

import de.shelp.dto.state.LocationTO;
import de.shelp.dto.tour.TourTO;
import de.shelp.entities.Location;
import de.shelp.entities.Tour;

@Stateless
public class TourDtoAssembler {

    public Tour makeDAO(TourTO tourTO) {
	Tour dao = new Tour();
	dao.setApprovalStatus(tourTO.getApprovalStatus());
	dao.setLocation(makeDAO(tourTO.getLocation()));
	dao.setCapacity(tourTO.getCapacity());
	dao.setPaymentConditions(tourTO.getPaymentConditions());
	dao.setDeliveryConditions(tourTO.getDeliveryConditions());
	dao.setTime(tourTO.getTime());
	return dao;
    }

    public Location makeDAO(LocationTO locationTO) {
	Location dao = new Location();
	dao.setId(locationTO.getId());
	dao.setDescription(locationTO.getDescription());
	dao.setZipcode(locationTO.getZipcode());
	return dao;
    }

}
