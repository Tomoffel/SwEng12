package de.shelp.dto.state;

import java.util.List;

import de.shelp.dto.ReturnCodeResponse;

public class DeliveryConditionResponse extends ReturnCodeResponse {

    private static final long serialVersionUID = -8707856420583962264L;

    private List<DeliveryConditionTO> conditions;

    public void setConditions(List<DeliveryConditionTO> list) {
	this.conditions = list;
    }

    public List<DeliveryConditionTO> getConditions() {
	return conditions;
    }

}
