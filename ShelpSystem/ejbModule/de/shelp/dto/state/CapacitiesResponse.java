package de.shelp.dto.state;

import de.shelp.dto.ReturnCodeResponse;
import de.shelp.enums.Capacity;

public class CapacitiesResponse extends ReturnCodeResponse {

    private static final long serialVersionUID = -8707856420583962264L;

    private Capacity[] capacities = Capacity.values();

    public Capacity[] getCapacities() {
	return capacities;
    }

    public void setCapacities(Capacity[] capacities) {
	this.capacities = capacities;
    }

}
