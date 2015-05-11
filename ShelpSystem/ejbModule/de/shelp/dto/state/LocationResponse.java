package de.shelp.dto.state;

import java.util.List;

import de.shelp.dto.ReturnCodeResponse;

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
