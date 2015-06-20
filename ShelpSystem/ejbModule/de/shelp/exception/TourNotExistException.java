package de.shelp.exception;

import de.shelp.enums.ReturnCode;

/**
 * Erweiterung der ShelpException {@link ShelpException}. Wird aufgerufen bei nicht existierender Tour.
 * @author Thomas Sennekamp
 *
 */
public class TourNotExistException extends ShelpException {

    private static final long serialVersionUID = -4056569342625712900L;

    public TourNotExistException(String message) {
	super(ReturnCode.ERROR, message);
    }

}
