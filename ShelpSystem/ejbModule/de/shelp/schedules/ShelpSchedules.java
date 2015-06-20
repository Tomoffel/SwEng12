package de.shelp.schedules;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.jboss.logging.Logger;

import de.shelp.dao.local.ShelpFriendDAOLocal;
import de.shelp.dao.local.ShelpTourDAOLocal;
import de.shelp.dao.local.ShelpUserDAOLocal;
import de.shelp.entities.Friendship;
import de.shelp.entities.ShelpSession;
import de.shelp.entities.Tour;

/**
 * Zentrales Steuerprogramm um unn�tigt {@link ShelpSession}, abgelaufene
 * Fahrten und abgelehnt Freundschaften anzupassen. F�hrt daf�r die Methoden
 * {@link #removeUnneededSessions()}, {@link #closeTours()} und
 * {@link #deleteDeniedFriendships()}
 * 
 * @author Jos Werner
 *
 */
@Singleton
@Startup
public class ShelpSchedules {

    private static final Logger LOGGER = Logger.getLogger(ShelpSchedules.class);

    /**
     * EJB zur Abfrage von Datens�tzen von Benutzern. Referenz auf die EJB wird
     * per Dependency Injection gef�llt.
     */
    @EJB(beanName = "ShelpUserDAO", beanInterface = ShelpUserDAOLocal.class)
    private ShelpUserDAOLocal userDao;

    /**
     * EJB zur Abfrage von Datens�tzen von Fahrten. Referenz auf die EJB wird
     * per Dependency Injection gef�llt.
     */
    @EJB(beanName = "ShelpTourDAO", beanInterface = ShelpTourDAOLocal.class)
    private ShelpTourDAOLocal tourDao;

    /**
     * EJB zur Abfrage von Datens�tzen von Freundschaften. Referenz auf die EJB
     * wird per Dependency Injection gef�llt.
     */
    @EJB(beanName = "ShelpFriendDAO", beanInterface = ShelpFriendDAOLocal.class)
    private ShelpFriendDAOLocal friendDao;

    /**
     * Entfernt immer um 2 Uhr nachts alle {@link ShelpSession} die seit �ber
     * einer Stunde nichts mehr getan haben.
     */
    @Schedule(hour = "2")
    public void removeUnneededSessions() {
	List<ShelpSession> sessions = userDao.getSessions();
	LOGGER.info(sessions.size()
		+ " exstierende Sessions werden auf ihre G�ltigkeit gepr�ft");

	for (ShelpSession shelpSession : sessions) {
	    // Alle Sessions die eine Stunde nichts getan haben werden entfernt
	    if (shelpSession.getUpdatedOn().before(
		    new Date(new Date().getTime() - 3600000))) {
		LOGGER.info(shelpSession.getId()
			+ " ist abgelaufen und wird entfernt.");
		userDao.closeSession(shelpSession.getId());
	    }

	}
    }

    /**
     * Alle 5 Minuten werden alle offenen Fahrten �berpr�ft und geschlossen
     * falls ihr Zeitpunkt �berschritten wurde.
     */
    @Schedule(hour = "*", minute = "0,5,10,15,20,25,30,35,40,45,50,55")
    public void closeTours() {
	List<Tour> tours = tourDao.getOpenTours();
	LOGGER.info(tours.size()
		+ " existierende Fahrten werden auf ihre G�ltigkeit gepr�ft");

	for (Tour tour : tours) {
	    // Alle Fahrten die vorr�ber sind werden abgeschlossen
	    if (tour.getTime().before(new Date())) {
		LOGGER.info(tour
			+ " ist vorbei und wird auf abgeschlossen gesetzt.");
		tourDao.closeTour(tour);
	    }
	}
    }

    /**
     * Jeden Sonntag um 2 Uhr nachts werden alle abgelehnten Freundschaften aus
     * der Datenbank entfernt.
     */
    @Schedule(dayOfWeek = "Sun", hour = "2")
    public void deleteDeniedFriendships() {
	List<Friendship> friendships = friendDao.getDeniedFriendships();
	LOGGER.info(friendships.size()
		+ " abgelehnte Freundschaften werden entfernt");

	for (Friendship friendship : friendships) {
	    // Alle abgelehnten Freundschaftsanfragen werden entfernt
	    LOGGER.info(friendship
		    + " wurde abgelehnt und wird entg�ltig entfernt.");
	    friendDao.deleteFriendship(friendship);
	}
    }

}
