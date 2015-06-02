package de.shelp.dto.state;

import java.util.List;

import de.shelp.dto.ReturnCodeResponse;

public class CapacitiesResponse extends ReturnCodeResponse {

    private static final long serialVersionUID = -8707856420583962264L;

    private List<CapacityTO> capacities;

    public List<CapacityTO> getCapacities() {
	return capacities;
    }

    public void setCapacities(List<CapacityTO> capacities) {
	this.capacities = capacities;
    }

}
