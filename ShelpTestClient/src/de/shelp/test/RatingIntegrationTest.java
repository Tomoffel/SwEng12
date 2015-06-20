package de.shelp.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import de.shelp.integration.RatingIntegration;
import de.shelp.integration.RatingIntegrationService;
import de.shelp.integration.RatingResponse;
import de.shelp.integration.ReturnCode;
import de.shelp.integration.ReturnCodeResponse;
import de.shelp.integration.ShelpSessionTO;
import de.shelp.integration.UserIntegration;
import de.shelp.integration.UserIntegrationService;
import de.shelp.integration.UserResponse;

/**
 * Testet alle Webservice-Schnittstellen zur RatingIntegration.
 * 
 * @author Jos Werner
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RatingIntegrationTest {

    private static RatingIntegration remoteSystem;
    private static ShelpSessionTO session1;
    private static ShelpSessionTO session2;

    /**
     * Baut einmalig die Verbindung zum Server auf
     */
    @BeforeClass
    public static void initTestCase() {
	RatingIntegrationService service = new RatingIntegrationService();
	remoteSystem = service.getRatingIntegrationPort();

	UserIntegrationService userService = new UserIntegrationService();
	UserIntegration userIntegrationPort = userService
		.getUserIntegrationPort();

	UserResponse loginResponse = userIntegrationPort.regUser(
		"thomas@sennekamp.de", "test123");
	if (loginResponse.getReturnCode() == ReturnCode.ERROR) {
	    loginResponse = userIntegrationPort.login("thomas@sennekamp.de",
		    "test123");
	}
	session1 = loginResponse.getSession();

	loginResponse = userIntegrationPort.regUser("theresa@sennekamp.de",
		"test123");
	if (loginResponse.getReturnCode() == ReturnCode.ERROR) {
	    loginResponse = userIntegrationPort.login("theresa@sennekamp.de",
		    "test123");
	}
	session2 = loginResponse.getSession();
    }

    /**
     * Testet, ob die Erstellung eines Rating erfolgreich ist.
     */
    @Test
    public void aTestCreateRatingSuccess() {
	ReturnCodeResponse createRating = remoteSystem.createRating(session2
		.getUser().getEmail(), 45, "Guter Fahrer", session1.getId());
	Assert.assertEquals(ReturnCode.OK, createRating.getReturnCode());
    }

    /**
     * Testet, ob die Erstellung von Ratings fehlschl�gt.
     */
    @Test
    public void bTestCreateRatingFailed() {
	// Session existiert nicht
	ReturnCodeResponse createRating = remoteSystem.createRating(session2
		.getUser().getEmail(), 4, "Guter Fahrer", 5000);
	Assert.assertEquals(ReturnCode.ERROR, createRating.getReturnCode());
	Assert.assertEquals("Session-Id existiert nicht.",
		createRating.getMessage());

	// targetuser existiert nicht
	createRating = remoteSystem.createRating("nichtvorhanden", 4,
		"Guter Fahrer", session1.getId());
	Assert.assertEquals(ReturnCode.ERROR, createRating.getReturnCode());
	Assert.assertEquals("Benutzer nichtvorhanden existiert nicht.",
		createRating.getMessage());

	// eigene bewertung nicht m�glich
	createRating = remoteSystem.createRating(session2.getUser().getEmail(),
		4, "Guter Fahrer", session2.getId());
	Assert.assertEquals(ReturnCode.ERROR, createRating.getReturnCode());
	Assert.assertEquals("Man darf sich nicht selbst bewerten "
		+ session2.getUser().getEmail(), createRating.getMessage());

	// bewertung kleiner 1
	createRating = remoteSystem.createRating(session2.getUser().getEmail(),
		0, "Guter Fahrer", session1.getId());
	Assert.assertEquals(ReturnCode.ERROR, createRating.getReturnCode());
	Assert.assertEquals("Ung�ltiges Rating 0.0", createRating.getMessage());

	// bewertung gr��er 5
	createRating = remoteSystem.createRating(session2.getUser().getEmail(),
		60, "Guter Fahrer", session1.getId());
	Assert.assertEquals(ReturnCode.ERROR, createRating.getReturnCode());
	Assert.assertEquals("Ung�ltiges Rating 6.0", createRating.getMessage());
    }

    /**
     * Testet, ob Ratings erfolgreich angerufen werden k�nnen.
     */
    @Test
    public void cTestGetRatingsSuccess() {
	// session1 mit keinen Bewertungen
	RatingResponse ratings = remoteSystem.getRatings(session1.getUser()
		.getEmail());
	Assert.assertEquals(ReturnCode.OK, ratings.getReturnCode());
	Assert.assertEquals(0, ratings.getRatings().size());

	// session2 mit einer Bewertungen aus Testfall a
	ratings = remoteSystem.getRatings(session2.getUser().getEmail());
	Assert.assertEquals(ReturnCode.OK, ratings.getReturnCode());
	Assert.assertEquals(1, ratings.getRatings().size());
	Assert.assertEquals(45, ratings.getRatings().get(0).getRating(), 0.01);
	Assert.assertEquals("Guter Fahrer", ratings.getRatings().get(0)
		.getNotice());
    }

    /**
     * Testet, ob  Ratings nicht erfolgreich angerufen werden k�nnen.
     */
    @Test
    public void cTestGetRatingsFailed() {
	// ung�ltiger Benutzer
	RatingResponse ratings = remoteSystem.getRatings("nichtvorhanden");
	Assert.assertEquals(ReturnCode.ERROR, ratings.getReturnCode());
    }
}
