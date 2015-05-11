package de.shelp.dto.state;

import de.shelp.dto.ReturnCodeResponse;
import de.shelp.enums.PaymentCondition;

public class PaymentConditionsResponse extends ReturnCodeResponse {

    private static final long serialVersionUID = -8707856420583962264L;

    private final PaymentCondition[] conditions = PaymentCondition.values();

    public PaymentCondition[] getConditions() {
	return conditions;
    }

}
