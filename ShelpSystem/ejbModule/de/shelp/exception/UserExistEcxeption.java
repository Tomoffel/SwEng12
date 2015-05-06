package de.shelp.exception;

import de.shelp.enums.ReturnCode;

public class UserExistEcxeption extends ShelpException {

    private static final long serialVersionUID = -372119263353118945L;

    public UserExistEcxeption(ReturnCode errorCode, String message) {
	super(errorCode, message);
    }

}
