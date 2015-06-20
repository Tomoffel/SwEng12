package de.shelp.dto.user;

import de.shelp.dto.ReturnCodeResponse;

/**
 * Klasse repräsentiert die Rückgabe für die {@link ShelpSessionTO}. Erbt von
 * {@link ReturnCodeResponse}.
 * 
 * @author Jos Werner
 *
 */
public class UserResponse extends ReturnCodeResponse {

    private static final long serialVersionUID = -7696441059450266251L;

    private ShelpSessionTO session;

    public void setSession(ShelpSessionTO session) {
	this.session = session;
    }

    public ShelpSessionTO getSession() {
	return session;
    }

}
