package de.shelp.dao.local;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import de.shelp.entities.ApprovalStatus;
import de.shelp.entities.Capacity;
import de.shelp.entities.Location;
import de.shelp.entities.Tour;
import de.shelp.entities.User;
import de.shelp.enums.TourStatus;

/**
 * Interface das vorgibt welche Methoden für die datenbankseitige
 * Fahrtenverwaltung benötigt werden.
 * 
 * @author Jos Werner
 *
 */
@Local
public interface ShelpTourDAOLocal {

    /**
     * Speichert eine Fahrt ({@link Tour}) in der Datenbank ab. Setzt den
     * {@link TourStatus} aus PLANNED und den Besitzer.
     * 
     * @param tour
     *            - zu erstellende Fahrt
     * @param user
     *            - Besitzer der Fahrt
     * @return die angelegte Fahrt
     */
    public Tour createTour(Tour tour, User user);

    /**
     * Sucht nach Fahren in der Datenbank. Gibt nur fahrten zurück die den
     * gleichen Freigabestatus ({@link ApprovalStatus}), Ort ({@link Location})
     * und die gleiche Kapazität ({@link Capacity}) haben und zwischen Anfangs-
     * und Enddatum liegen. Bei dem Freigabestatus "Alle" werden auch die
     * Fahrten der Freunde des Benutzers ausgegeben. <br>
     * Eigene und geschlossene Fahrten werden ebenfalls nicht berücksichtigt.
     * 
     * @param approvalStatus
     *            - Freigabestatus nach dem gefiltert werden soll
     * @param location
     *            - Ort der gesucht werden soll
     * @param capacity
     *            - Kapazität die gesucht werden soll
     * @param startDate
     *            - früherster Anfangszeitpunkt der Fahrt
     * @param endDate
     *            - spätestester Endzeitpunkt der Fahrt
     * @param currentUser
     *            - Benutzer der die Anfrage stellt
     * 
     * @return Liste von Fahrten die gefunden wurden
     */
    public List<Tour> search(ApprovalStatus approvalStatus, Location location,
	    Capacity capacity, Date startDate, Date endDate, User currentUser);

    /**
     * Sucht nach Fahren in der Datenbank. Gibt nur fahrten zurück die den
     * gleichen Freigabestatus ({@link ApprovalStatus}) und die gleiche
     * Kapazität ({@link Capacity}) haben und zwischen Anfangs- und Enddatum
     * liegen. Bei dem Freigabestatus "Alle" werden auch die Fahrten der Freunde
     * des Benutzers ausgegeben. <br>
     * Zusätzlich werden alle Orte ({@link Location}) ermittelt die in der nähe
     * des angegeben Ortes liegen und auch alle Fahrten zu diesen Orten
     * zurückgegeben. Ein Ort liegt in der Nähe, wenn er die gleiche PLZ hat.<br>
     * Eigene und geschlossene Fahrten werden ebenfalls nicht berücksichtigt.
     * 
     * @param approvalStatus
     *            - Freigabestatus nach dem gefiltert werden soll
     * @param location
     *            - Ort in dessen Nähe gesucht werden soll
     * @param capacity
     *            - Kapazität die gesucht werden soll
     * @param startDate
     *            - früherster Anfangszeitpunkt der Fahrt
     * @param endDate
     *            - spätestester Endzeitpunkt der Fahrt
     * @param currentUser
     *            - Benutzer der die Anfrage stellt
     * 
     * @return Liste von Fahrten die gefunden wurden
     */
    public List<Tour> searchNear(ApprovalStatus approvalStatus,
	    Location location, Capacity capacity, Date startTime, Date endTime,
	    User currentUser);

    /**
     * Gibt die zu der Id passende Fahrt ({@link Tour}) zurück.
     * 
     * @param tourId
     *            - Id der gesuchten Fahrt
     * @return gefunde Fahrt oder null
     */
    public Tour getTour(long tourId);

    /**
     * Sagt eine Fahrt ({@link Tour}) ab. Der Status der Fahrt wird auf
     * CANCELLED gesetzt.
     * 
     * @param tour
     *            - die Fahrt die abgesagt werden soll.
     */
    public void cancleTour(Tour tour);

    /**
     * Speichert eine Fahrt ({@link Tour}) in der Datenbank ab.
     * 
     * @param tour
     *            - zu speichernde Fahrt
     */
    public void saveTour(Tour tour);

    /**
     * Schließt eine Fahrt ({@link Tour}) ab. Setzt den {@link TourStatus} auf
     * CLOSED
     * 
     * @param tour
     *            - die zu schließende Fahrt
     */
    public void closeTour(Tour tour);

    /**
     * Holt alle offenen Fahrten ({@link Tour}) aus der Datenbank. Offene
     * Fahrten haben den {@link TourStatus} PLANNED
     * 
     * @return Liste mit gefundenen Fahrten
     */
    public List<Tour> getOpenTours();

}
