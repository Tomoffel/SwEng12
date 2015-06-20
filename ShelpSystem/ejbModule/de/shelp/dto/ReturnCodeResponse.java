package de.shelp.dto;

import java.io.Serializable;

import de.shelp.enums.ReturnCode;

/**
 * Oberklasse für alle Rückgaben vom Server an den Client. Gibt generelle
 * Strukturen für die Rückgaben vor. Darunter fällt Rückgabecode der Vorgibt ob
 * serverseitig alles gut gelaufen (OK) ist oder es Probleme gab
 * (ERROR|PERMISSION_DENIED). Im Fehlerfall wird außerdem eine Nachricht
 * übergeben. Standardmäßig wird der Status auf OK gesetzt.
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
