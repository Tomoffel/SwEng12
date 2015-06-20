package de.shelp.dto.state;

import java.util.List;

import de.shelp.dto.ReturnCodeResponse;

/**
 * Klasse repräsentiert die Rückgabe für die Lieferbedingungen ({@link DeliveryConditionTO}). Erbt von
 * {@link ReturnCodeResponse}.
 * 
 * @author Jos Werner
 *
 */
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
