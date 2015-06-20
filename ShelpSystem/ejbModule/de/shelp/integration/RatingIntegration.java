package de.shelp.integration;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;

import de.shelp.dao.local.ShelpRatingDAOLocal;
import de.shelp.dao.local.ShelpUserDAOLocal;
import de.shelp.dto.ReturnCodeResponse;
import de.shelp.dto.rating.RatingResponse;
import de.shelp.dto.rating.RatingTO;
import de.shelp.entities.Rating;
import de.shelp.entities.ShelpSession;
import de.shelp.entities.User;
import de.shelp.enums.ReturnCode;
import de.shelp.exception.ShelpException;
import de.shelp.util.RatingDtoAssembler;
import de.shelp.util.ShelpHelper;

/**
 * Webservice der alle nötigen Methoden zur Bewertungsverwaltung bereitstellt.
 * Über die Schnittstelle können Bewertungen angelegt
 * {@link #createRating(String, int, String, int)} und ausgelesen
 * {@link #getRatings(String)} werden. <br>
 * Jeder Schritt wird über die Logausgabe dokumentiert. Außerdem werden alle
 * Entitäten vor der Rückgabe in Data Transfer Objekte umgewandelt.
 * 
 * @author Thomas Sennekamp
 *
 */
@WebService
@WebContext(contextRoot = "/shelp")
@Stateless
public class RatingIntegration {

    /**
     * EJB zur Abfrage von Datensätzen der Bewertungen. Referenz auf die EJB
     * wird per Dependency Injection gefüllt.
     */
    @EJB(beanName = "ShelpRatingDAO", beanInterface = ShelpRatingDAOLocal.class)
    private ShelpRatingDAOLocal daoRating;

    /**
     * EJB zur Abfrage von Datensätzen der Benutzer- Referenz auf die EJB wird
     * per Dependency Injection gefüllt.
     */
    @EJB(beanName = "ShelpUserDAO", beanInterface = ShelpUserDAOLocal.class)
    private ShelpUserDAOLocal daoUser;

    /**
     * EJB zur Erzeugung von DataTransferObjects von Bewertungen
     */
    @EJB
    private RatingDtoAssembler dtoAssembler;

    /**
     * EJB zur Einbindung von generellen Hilfsmethoden
     */
    @EJB
    private ShelpHelper helper;

    private static final Logger LOGGER = Logger
	    .getLogger(RatingIntegration.class);

    /**
     * Schnittstelle die genutzt werden kann um zu einem Benutzer ({@link User})
     * alle Bewertungen ({@link Rating}) zu bekommen- Prüft ob der Benutzer in
     * der Datenbank existiert und gibt {@link ReturnCode} ERROR zurück falls
     * nicht.
     * 
     * @param userName
     *            - die E-Mail des Benutzers dessen Ratings angefragt werden
     * @return einen {@link RatingResponse} mit {@link ReturnCode} OK und allen
     *         Bewertungen des Benutzers oder {@link ReturnCode} ERROR +
     *         Fehlermeldung
     */
    public RatingResponse getRatings(String userName) {
	RatingResponse response = new RatingResponse();

	try {
	    User user = helper.checkUser(userName, daoUser);

	    List<Rating> ratings = user.getRatings();

	    List<RatingTO> ratingsTO = new ArrayList<RatingTO>();
	    for (Rating r : ratings) {
		ratingsTO.add(dtoAssembler.makeDTO(r));
	    }

	    response.setRatings(ratingsTO);

	    LOGGER.info("Es wurde(n) " + ratingsTO.size()
		    + " Bewertung(en) für den Benutzer " + userName
		    + " gefunden.");
	} catch (ShelpException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}
	return response;

    }

    /**
     * Schnittstelle die genutzt werden kann um eine Bewertung ({@link Rating})
     * zu einem Benutzers ({@link User}) zu erstellen. Prüft ob der Zielnutzer
     * und die SessionId existieren und gibt {@link ReturnCode} ERROR zurück
     * falls nicht. Überprüft außerdem ob das Rating im gültigen Bereich
     * zwischen 10 und 50 liegt und ob der Benutzer aus der Session und der
     * Zielnutzer ungleich sind. Falls nicht wieder ERROR. <br>
     * Das Rating muss vor der Übertragung mal 10 genommen werden (d.h. 4,5 wird
     * zu 45 usw.). Dies ist nötig da die Übertragung eines Floats mit kSoap
     * nicht geht und halbe Sterne möglich sein sollen. <br>
     * Der Ersteller der Fahrt wird automatisch aus der SessionId ermittelt.
     * 
     * @param targetUserId
     *            - E-Mail des zu bewertenden Nutzers
     * @param rating
     *            - die Bewertung in Sternen
     * @param notice
     *            - eine kurzer Text vom Bewerter
     * @param sessionId
     *            - die Id der Session des bewertenden Nutzers
     * 
     * @return ein {@link ReturnCodeResponse} mit {@link ReturnCode} OK oder
     *         ERROR + Fehlermeldung
     */
    public ReturnCodeResponse createRating(String targetUserId, int rating,
	    String notice, int sessionId) {
	ReturnCodeResponse response = new ReturnCodeResponse();

	try {
	    ShelpSession session = helper.checkSession(sessionId, daoUser);
	    User targetUser = helper.checkUser(targetUserId, daoUser);

	    if (targetUser.equals(session.getUser())) {
		LOGGER.info("Man darf sich nicht selbst bewerten "
			+ session.getUser());
		throw new ShelpException(ReturnCode.ERROR,
			"Man darf sich nicht selbst bewerten "
				+ session.getUser());
	    }
	    if (rating < 10 || rating > 50) {
		LOGGER.info("Ungültiges Rating " + rating);
		throw new ShelpException(ReturnCode.ERROR, "Ungültiges Rating "
			+ (rating / 10f));
	    }

	    Rating newRating = new Rating();
	    newRating.setTargetUser(targetUser);
	    newRating.setSourceUser(session.getUser());
	    newRating.setRating(rating);
	    newRating.setNotice(notice);

	    daoRating.createRating(newRating);

	    LOGGER.info("Benutzer " + session.getUser() + " hat " + targetUser
		    + " mit " + newRating.getRating() + " Sternen bewertet.");

	} catch (ShelpException e) {
	    response.setReturnCode(e.getErrorCode());
	    response.setMessage(e.getMessage());
	}

	return response;
    }
}
