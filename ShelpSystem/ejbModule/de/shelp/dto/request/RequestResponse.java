package de.shelp.dto.request;

import de.shelp.dto.ReturnCodeResponse;

/**
 * Klasse repräsentiert die Rückgabe für eine Anfrage ({@link RequestTO}).
 * Erbt von {@link ReturnCodeResponse}.
 * 
 * @author Thomas Sennekamp
 *
 */
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
