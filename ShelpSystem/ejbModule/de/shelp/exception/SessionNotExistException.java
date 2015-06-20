package de.shelp.exception;

import de.shelp.enums.ReturnCode;

/**
 * Erweiterung der ShelpException {@link ShelpException}. Wird aufgerufen bei nicht existierender Session.
 * @author Jos Werner
 *
 */
public class SessionNotExistException extends ShelpException {

    private static final long serialVersionUID = 236507510589449361L;

    public SessionNotExistException(String message) {
	super(ReturnCode.ERROR, message);
    }

}
