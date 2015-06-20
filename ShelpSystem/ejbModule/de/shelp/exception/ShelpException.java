package de.shelp.exception;

import de.shelp.enums.ReturnCode;
/**
 * Grundlegende Fehlermeldung für das ShelpSystem
 * @author Thomas Sennekamp
 *
 */

public class ShelpException extends Exception {

	private static final long serialVersionUID = -1658425297634781761L;
	private ReturnCode errorCode;

	public ShelpException(ReturnCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public ReturnCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ReturnCode errorCode) {
		this.errorCode = errorCode;
	}

}
