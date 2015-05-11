package de.shelp.dto.state;

import de.shelp.dto.ReturnCodeResponse;
import de.shelp.enums.DeliveryCondition;

public class DeliveryConditionResponse extends ReturnCodeResponse {

    private static final long serialVersionUID = -8707856420583962264L;

    private final DeliveryCondition[] conditions = DeliveryCondition.values();

    public DeliveryCondition[] getConditions() {
	return conditions;
    }

}
