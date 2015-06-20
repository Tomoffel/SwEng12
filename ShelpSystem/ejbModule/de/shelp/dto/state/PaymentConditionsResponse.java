package de.shelp.dto.state;

import java.util.List;

import de.shelp.dto.ReturnCodeResponse;

/**
 * Klasse repräsentiert die Rückgabe für die Bezahlmethoden ({@link PaymentConditionTO}). Erbt von
 * {@link ReturnCodeResponse}.
 * 
 * @author Jos Werner
 *
 */
public class PaymentConditionsResponse extends ReturnCodeResponse {

    private static final long serialVersionUID = -8707856420583962264L;

    private List<PaymentConditionTO> conditions;

    public List<PaymentConditionTO> getConditions() {
	return conditions;
    }

    public void setConditions(List<PaymentConditionTO> list) {
	this.conditions = list;
    }

}
