package de.shelp.dao.local;

import javax.ejb.Local;

import de.shelp.entities.Request;
@Local
public interface ShelpRequestDAOLocal {

	Request getRequestById(long requestId);

	void deleteRequest(Request request);

	void createRequest(Request request);

}
