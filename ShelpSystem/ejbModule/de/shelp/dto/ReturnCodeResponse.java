package de.shelp.dto;

import java.io.Serializable;

import de.shelp.enums.ReturnCode;

public class ReturnCodeResponse implements Serializable {

    private static final long serialVersionUID = 3397348747136795401L;

    private ReturnCode returnCode;
    private String message;

    public ReturnCodeResponse() {
	this.returnCode = ReturnCode.OK;
    }

    public ReturnCode getReturnCode() {
	return returnCode;
    }

    public void setReturnCode(ReturnCode returnCode) {
	this.returnCode = returnCode;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }
}
