package de.shelp.dto.request;

import de.shelp.dto.ReturnCodeResponse;

public class RequestResponse extends ReturnCodeResponse {

    private static final long serialVersionUID = -639667324721449213L;

    private RequestTO requestTO;

	public RequestTO getRequestTO() {
		return requestTO;
	}

	public void setRequestTO(RequestTO requestTO) {
		this.requestTO = requestTO;
	}
    

}
