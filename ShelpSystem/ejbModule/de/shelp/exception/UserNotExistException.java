package de.shelp.exception;

import de.shelp.enums.ReturnCode;

/**
 * Erweiterung der ShelpException {@link ShelpException}. Wird aufgerufen bei nicht existierendem Benutzer.
 * @author Thomas Sennekamp
 *
 */
public class UserNotExistException extends ShelpException {

    private static final long serialVersionUID = -372119263353118945L;

    public UserNotExistException(String message) {
    	super(ReturnCode.ERROR, message);
    }

}
