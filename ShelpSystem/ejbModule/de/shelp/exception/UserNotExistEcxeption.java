package de.shelp.exception;

import de.shelp.enums.ReturnCode;

public class UserNotExistEcxeption extends ShelpException {

    private static final long serialVersionUID = -372119263353118945L;

    public UserNotExistEcxeption(ReturnCode errorCode, String message) {
	super(errorCode, message);
    }

}
