package de.shelp.dao.local;

import javax.ejb.Local;

import de.shelp.entities.Request;
import de.shelp.enums.RequestStatus;

/**
 * Interface das vorgibt welche Methoden für die datenbankseitige
 * Abfrageverwaltung benötigt werden.
 * 
 * @author Thomas Sennekamp
 *
 */
@Local
public interface ShelpRequestDAOLocal {

    /**
     * Sucht nach einer Anfrage ({@link Request}) in der Datenbank.
     * 
     * @param requestId
     *            - Id der zu suchenden Anfrage
     * @return die gefundene Anfrage oder null
     */
    public Request getRequestById(long requestId);

    /**
     * Setzt den {@link RequestStatus} einer Anfrage auf REMOVED.
     * 
     * @param request
     *            - die zu ändernde Anfrage
     */
    public void deleteRequest(Request request);

    /**
     * Speichert eine Anfrage in der Datenbank ab.
     * 
     * @param request
     *            - die zu speichernde Anfrage.
     */
    public void persistRequest(Request request);

}
