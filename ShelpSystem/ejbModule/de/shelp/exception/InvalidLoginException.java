package de.shelp.exception;

import de.shelp.enums.ReturnCode;

/**
 * Erweiterung der ShelpException {@link ShelpException}. Wird aufgerufen bei
 * ung�ltigem Login.
 * 
 * @author Jos Werner
 *
 */
public class InvalidLoginException extends ShelpException {

    private static final long serialVersionUID = -6619543851336912279L;

    public InvalidLoginException(String message) {
	super(ReturnCode.ERROR, message);
    }

}
