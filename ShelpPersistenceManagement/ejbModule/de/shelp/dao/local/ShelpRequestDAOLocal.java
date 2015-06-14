package de.shelp.dao.local;

import javax.ejb.Local;

import de.shelp.entities.Request;
@Local
public interface ShelpRequestDAOLocal {

	public Request getRequestById(long requestId);

	public void deleteRequest(Request request);

	public void persistRequest(Request request);
	
}
