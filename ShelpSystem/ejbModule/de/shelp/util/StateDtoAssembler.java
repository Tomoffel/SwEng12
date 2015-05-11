package de.shelp.util;

import javax.ejb.Stateless;

import de.shelp.dto.state.LocationTO;
import de.shelp.entities.Location;

@Stateless
public class StateDtoAssembler {

    public LocationTO makeDTO(Location location) {
	LocationTO dto = new LocationTO();
	
	dto.setId(location.getId());
	dto.setDescription(location.getDescription());
	dto.setZipcode(location.getZipcode());
	
	return dto;
    }

}
