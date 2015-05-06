package de.shelp.exception;

import de.shelp.enums.ReturnCode;

public class InvalidLoginException extends ShelpException {

    private static final long serialVersionUID = -6619543851336912279L;

    public InvalidLoginException(String message) {
	super(ReturnCode.ERROR, message);
    }

}
