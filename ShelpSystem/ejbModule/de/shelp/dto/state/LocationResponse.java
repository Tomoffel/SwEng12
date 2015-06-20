package de.shelp.dto.state;

import java.util.List;

import de.shelp.dto.ReturnCodeResponse;

/**
 * Klasse repräsentiert die Rückgabe für die Orte ({@link LocationTO}). Erbt von
 * {@link ReturnCodeResponse}.
 * 
 * @author Jos Werner
 *
 */
public class LocationResponse extends ReturnCodeResponse {

    private static final long serialVersionUID = -5488476431539920165L;

    private List<LocationTO> locations;

    public List<LocationTO> getLocations() {
	return locations;
    }

    public void setLocations(List<LocationTO> locations) {
	this.locations = locations;
    }

}
