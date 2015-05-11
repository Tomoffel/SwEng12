package de.shelp.dto.state;

import de.shelp.dto.ReturnCodeResponse;
import de.shelp.enums.ApprovalStatus;

public class ApprovalStatusResponse extends ReturnCodeResponse {

    private static final long serialVersionUID = -8707856420583962264L;

    private final ApprovalStatus[] states = ApprovalStatus.values();

    public ApprovalStatus[] getStates() {
	return states;
    }

}
