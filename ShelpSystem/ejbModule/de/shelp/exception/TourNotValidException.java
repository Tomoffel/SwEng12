package de.shelp.exception;

import de.shelp.enums.ReturnCode;

/**
 * Erweiterung der ShelpException {@link ShelpException}. Wird aufgerufen bei ungültig ausgefüllter Tour.
 * @author Jos Werner
 *
 */
public class TourNotValidException extends ShelpException {

    private static final long serialVersionUID = -3535393280885592950L;

    public TourNotValidException(ReturnCode errorCode, String message) {
	super(errorCode, message);
    }

}
