package de.shelp.dto;

import java.io.Serializable;

import de.shelp.enums.ReturnCode;

/**
 * Oberklasse f�r alle R�ckgaben vom Server an den Client. Gibt generelle
 * Strukturen f�r die R�ckgaben vor. Darunter f�llt R�ckgabecode der Vorgibt ob
 * serverseitig alles gut gelaufen (OK) ist oder es Probleme gab
 * (ERROR|PERMISSION_DENIED). Im Fehlerfall wird au�erdem eine Nachricht
 * �bergeben. Standardm��ig wird der Status auf OK gesetzt.
 * 
 * @author Jos Werner
 *
 */
public class ReturnCodeResponse implements Serializable {

    private static final long serialVersionUID = 3397348747136795401L;

    private ReturnCode returnCode;
    private String message;

    public ReturnCodeResponse() {
	this.returnCode = ReturnCode.OK;
    }

    public ReturnCode getReturnCode() {
	return returnCode;
    }

    public void setReturnCode(ReturnCode returnCode) {
	this.returnCode = returnCode;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }
}
