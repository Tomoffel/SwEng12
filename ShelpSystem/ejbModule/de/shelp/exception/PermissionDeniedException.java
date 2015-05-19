package de.shelp.exception;

import de.shelp.enums.ReturnCode;

public class PermissionDeniedException extends ShelpException {

    private static final long serialVersionUID = 168428959819500389L;

    public PermissionDeniedException(String message) {
	super(ReturnCode.PERMISSION_DENIED, message);
	// TODO Auto-generated constructor stub
    }

}
