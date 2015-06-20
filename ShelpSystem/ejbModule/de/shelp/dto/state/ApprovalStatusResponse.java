package de.shelp.dto.state;

import java.util.List;

import de.shelp.dto.ReturnCodeResponse;

/**
 * Klasse repräsentiert die Rückgabe für die Freigabestatusse (
 * {@link ApprovalStatusTO}). Erbt von {@link ReturnCodeResponse}.
 * 
 * @author Jos Werner
 *
 */
public class ApprovalStatusResponse extends ReturnCodeResponse {

    private static final long serialVersionUID = -8707856420583962264L;

    private List<ApprovalStatusTO> states;

    public List<ApprovalStatusTO> getStates() {
	return states;
    }

    public void setStates(List<ApprovalStatusTO> states) {
	this.states = states;
    }

}
