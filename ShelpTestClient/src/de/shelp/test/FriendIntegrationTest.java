package de.shelp.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import de.shelp.integration.FriendIntegration;
import de.shelp.integration.FriendIntegrationService;
import de.shelp.integration.FriendsResponse;
import de.shelp.integration.FriendshipStatus;
import de.shelp.integration.FriendshipTO;
import de.shelp.integration.ReturnCode;
import de.shelp.integration.ReturnCodeResponse;
import de.shelp.integration.ShelpSessionTO;
import de.shelp.integration.UserIntegration;
import de.shelp.integration.UserIntegrationService;
import de.shelp.integration.UserResponse;

/**
 * Testet alle Webservice-Schnittstellen zur FriendIntegration.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FriendIntegrationTest {

    private static FriendIntegration remoteSystem;
    private static ShelpSessionTO session1;
    private static ShelpSessionTO session2;
    private static ShelpSessionTO session3;
    private static ShelpSessionTO session4;

    /**
     * Baut einmalig die Verbindung zum Server auf und setzt zentrale
     * Informationen für die Testfälle.
     */
    @BeforeClass
    public static void initTestCase() {
	FriendIntegrationService service = new FriendIntegrationService();
	remoteSystem = service.getFriendIntegrationPort();

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

	loginResponse = userIntegrationPort.regUser("roman@sennekamp.de",
		"test123");
	if (loginResponse.getReturnCode() == ReturnCode.ERROR) {
	    loginResponse = userIntegrationPort.login("roman@sennekamp.de",
		    "test123");
	}
	session3 = loginResponse.getSession();

	loginResponse = userIntegrationPort.regUser("jos@sennekamp.de",
		"test123");
	if (loginResponse.getReturnCode() == ReturnCode.ERROR) {
	    loginResponse = userIntegrationPort.login("jos@sennekamp.de",
		    "test123");
	}
	session4 = loginResponse.getSession();
    }

    /**
     * Überprüft ob eine Freundschaft erfolgreich angefragt werden kann.
     * Erwartet das immer OK zurück gegeben wird.
     */
    @Test
    public void aTestFriendshipAskedSuccess() {
	ReturnCodeResponse addFriend = remoteSystem.addFriend(session1.getId(),
		session2.getUser().getEmail());
	Assert.assertEquals(ReturnCode.OK, addFriend.getReturnCode());
	addFriend = remoteSystem.addFriend(session1.getId(), session3.getUser()
		.getEmail());
	Assert.assertEquals(ReturnCode.OK, addFriend.getReturnCode());
	addFriend = remoteSystem.addFriend(session1.getId(), session4.getUser()
		.getEmail());
	Assert.assertEquals(ReturnCode.OK, addFriend.getReturnCode());
    }

    /**
     * Überprüft alle Fehler die während der Anfrage einer Freundschaft
     * auftreten können. Erwartet das keine Freundschaft angelegt wird.
     */
    @Test
    public void bTestFriendshipAskedFailed() {
	// Session existiert nicht
	ReturnCodeResponse addFriend = remoteSystem.addFriend(5000, session2
		.getUser().getEmail());
	Assert.assertEquals(ReturnCode.ERROR, addFriend.getReturnCode());
	Assert.assertEquals("Session-Id existiert nicht.",
		addFriend.getMessage());

	// targetuser existiert nicht
	addFriend = remoteSystem.addFriend(session1.getId(), "nicht vorhanden");
	Assert.assertEquals(ReturnCode.ERROR, addFriend.getReturnCode());
	Assert.assertEquals("Benutzer nicht vorhanden existiert nicht.",
		addFriend.getMessage());

	// eigene Freundschaft nicht möglich
	addFriend = remoteSystem.addFriend(session2.getId(), session2.getUser()
		.getEmail());
	Assert.assertEquals(ReturnCode.ERROR, addFriend.getReturnCode());
	Assert.assertEquals("Man kann nicht mit sich selbst befreundet sein "
		+ session2.getUser().getEmail(), addFriend.getMessage());
    }

    /**
     * Überprüft ob Freundschaften eines Benutzers angefragt werden können.
     * Erwartet das die vorher angelegten Freundschaften gefunden werden.
     */
    @Test
    public void cTestGetFriendshipsFirstSuccess() {
	FriendsResponse friends = remoteSystem.getFriends(session1.getId());
	Assert.assertEquals(ReturnCode.OK, friends.getReturnCode());
	Assert.assertEquals(3, friends.getFriends().size());
	Assert.assertEquals(FriendshipStatus.ASKED, friends.getFriends().get(0)
		.getStatus());
	Assert.assertEquals(FriendshipStatus.ASKED, friends.getFriends().get(1)
		.getStatus());
	Assert.assertEquals(FriendshipStatus.ASKED, friends.getFriends().get(2)
		.getStatus());

	friends = remoteSystem.getFriends(session2.getId());
	Assert.assertEquals(ReturnCode.OK, friends.getReturnCode());
	Assert.assertEquals(1, friends.getFriends().size());
	Assert.assertEquals(FriendshipStatus.ASKED, friends.getFriends().get(0)
		.getStatus());

	friends = remoteSystem.getFriends(session3.getId());
	Assert.assertEquals(ReturnCode.OK, friends.getReturnCode());
	Assert.assertEquals(1, friends.getFriends().size());
	Assert.assertEquals(FriendshipStatus.ASKED, friends.getFriends().get(0)
		.getStatus());

	friends = remoteSystem.getFriends(session4.getId());
	Assert.assertEquals(ReturnCode.OK, friends.getReturnCode());
	Assert.assertEquals(1, friends.getFriends().size());
	Assert.assertEquals(FriendshipStatus.ASKED, friends.getFriends().get(0)
		.getStatus());
    }

    /**
     * Prüft alle Fehler beim Abfragen der Freunde eines Benutzer ab. Erwartet
     * immer einen Fehler.
     */
    @Test
    public void dTestGetFriendshipsFailed() {
	// ungültiger Benutzer
	FriendsResponse friends = remoteSystem.getFriends(500);
	Assert.assertEquals(ReturnCode.ERROR, friends.getReturnCode());
    }

    /**
     * Testet ob eine Freundschaftsanfrage abgelehnt werden kann. Erwartet OK.
     */
    @Test
    public void eTestFriendshipDeniedSuccess() {
	FriendsResponse friends = remoteSystem.getFriends(session2.getId());
	List<FriendshipTO> friendOfSessions2 = friends.getFriends();

	ReturnCodeResponse deniedFriendship = remoteSystem.deniedFriendship(
		friendOfSessions2.get(0).getId(), session2.getId());
	Assert.assertEquals(ReturnCode.OK, deniedFriendship.getReturnCode());
    }

    /**
     * Überprüft alle Fehler die während der Freundschaftablehnung auftreten
     * können. Erwartet das es immer einen Fehler gibt.
     */
    @Test
    public void fTestFriendshipDeniedFailed() {
	FriendsResponse friends = remoteSystem.getFriends(session2.getId());
	List<FriendshipTO> friendOfSessions2 = friends.getFriends();
	friends = remoteSystem.getFriends(session3.getId());
	List<FriendshipTO> friendOfSessions3 = friends.getFriends();

	// Session-Id existiert nicht.
	ReturnCodeResponse deniedFriendship = remoteSystem.deniedFriendship(
		friendOfSessions2.get(0).getId(), 5000);
	Assert.assertEquals(ReturnCode.ERROR, deniedFriendship.getReturnCode());
	Assert.assertEquals("Session-Id existiert nicht.",
		deniedFriendship.getMessage());

	// Freundschaft existiert nicht!
	deniedFriendship = remoteSystem.deniedFriendship(1, session2.getId());
	Assert.assertEquals(ReturnCode.ERROR, deniedFriendship.getReturnCode());
	Assert.assertEquals("Freundschaft existiert nicht!",
		deniedFriendship.getMessage());

	// Zugriff verweigert. Anfragende Session xx ist nicht der Empfänger der
	// Freundschaftsanfrage xx!
	deniedFriendship = remoteSystem.deniedFriendship(
		friendOfSessions3.get(0).getId(), session1.getId());
	Assert.assertEquals(ReturnCode.PERMISSION_DENIED,
		deniedFriendship.getReturnCode());
	Assert.assertEquals("Zugriff verweigert. Anfragende Session "
		+ session1.getId()
		+ " ist nicht der Empfänger der Freundschaftsanfrage "
		+ friendOfSessions3.get(0).getId() + "!",
		deniedFriendship.getMessage());

	// Freundschaftsanfrage wurde schon angenommen/abgelehnt.
	deniedFriendship = remoteSystem.deniedFriendship(
		friendOfSessions2.get(0).getId(), session2.getId());
	Assert.assertEquals(ReturnCode.ERROR, deniedFriendship.getReturnCode());
	Assert.assertEquals(
		"Freundschaftsanfrage wurde schon angenommen/abgelehnt.",
		deniedFriendship.getMessage());
    }

    /**
     * Testet ob eine Freundschaftsanfrage angenommen werden kann. Erwartet OK.
     */
    @Test
    public void gTestFriendshipAcceptSuccess() {
	FriendsResponse friends = remoteSystem.getFriends(session3.getId());
	List<FriendshipTO> friendOfSessions3 = friends.getFriends();
	ReturnCodeResponse acceptFriendship = remoteSystem.acceptFriendship(
		friendOfSessions3.get(0).getId(), session3.getId());
	Assert.assertEquals(ReturnCode.OK, acceptFriendship.getReturnCode());
    }

    /**
     * Überprüft alle Fehler die während der Freundschaftannahme auftreten
     * können. Erwartet das es immer einen Fehler gibt.
     */
    @Test
    public void hTestFriendshipAcceptFailed() {
	FriendsResponse friends = remoteSystem.getFriends(session3.getId());
	List<FriendshipTO> friendOfSessions3 = friends.getFriends();
	friends = remoteSystem.getFriends(session4.getId());
	List<FriendshipTO> friendOfSessions4 = friends.getFriends();

	// Session-Id existiert nicht.
	ReturnCodeResponse acceptFriendship = remoteSystem.acceptFriendship(
		friendOfSessions3.get(0).getId(), 5000);
	Assert.assertEquals(ReturnCode.ERROR, acceptFriendship.getReturnCode());
	Assert.assertEquals("Session-Id existiert nicht.",
		acceptFriendship.getMessage());

	// Freundschaft existiert nicht!
	acceptFriendship = remoteSystem.acceptFriendship(1, session3.getId());
	Assert.assertEquals(ReturnCode.ERROR, acceptFriendship.getReturnCode());
	Assert.assertEquals("Freundschaft existiert nicht!",
		acceptFriendship.getMessage());

	// Zugriff verweigert. Anfragende Session xx ist nicht der Empfänger der
	// Freundschaftsanfrage xx!
	acceptFriendship = remoteSystem.acceptFriendship(
		friendOfSessions4.get(0).getId(), session1.getId());
	Assert.assertEquals(ReturnCode.PERMISSION_DENIED,
		acceptFriendship.getReturnCode());
	Assert.assertEquals("Zugriff verweigert. Anfragende Session "
		+ session1.getId()
		+ " ist nicht der Empfänger der Freundschaftsanfrage "
		+ friendOfSessions4.get(0).getId() + "!",
		acceptFriendship.getMessage());

	// Freundschaftsanfrage wurde schon angenommen/abgelehnt.
	acceptFriendship = remoteSystem.acceptFriendship(
		friendOfSessions3.get(0).getId(), session3.getId());
	Assert.assertEquals(ReturnCode.ERROR, acceptFriendship.getReturnCode());
	Assert.assertEquals(
		"Freundschaftsanfrage wurde schon angenommen/abgelehnt.",
		acceptFriendship.getMessage());
    }

    /**
     * Überprüft alle Fehler die während dem Löschen einer Freundschaft
     * auftreten können. Erwartet das es immer einen Fehler gibt.
     */
    @Test
    public void jTestFriendshipDeleteFailed() {
	FriendsResponse friends = remoteSystem.getFriends(session3.getId());
	List<FriendshipTO> friendOfSessions3 = friends.getFriends();
	friends = remoteSystem.getFriends(session4.getId());
	List<FriendshipTO> friendOfSessions4 = friends.getFriends();

	// Session-Id existiert nicht.
	ReturnCodeResponse deleteFriendship = remoteSystem.deleteFriendship(
		friendOfSessions3.get(0).getId(), 5000);
	Assert.assertEquals(ReturnCode.ERROR, deleteFriendship.getReturnCode());
	Assert.assertEquals("Session-Id existiert nicht.",
		deleteFriendship.getMessage());

	// Freundschaft existiert nicht!
	deleteFriendship = remoteSystem.deleteFriendship(1, session3.getId());
	Assert.assertEquals(ReturnCode.ERROR, deleteFriendship.getReturnCode());
	Assert.assertEquals("Freundschaft existiert nicht!",
		deleteFriendship.getMessage());

	// Zugriff verweigert. Anfragende Session xx ist nicht an der
	// Freundschaft xx beteiligt!
	deleteFriendship = remoteSystem.deleteFriendship(
		friendOfSessions4.get(0).getId(), session3.getId());
	Assert.assertEquals(ReturnCode.PERMISSION_DENIED,
		deleteFriendship.getReturnCode());
	Assert.assertEquals("Zugriff verweigert. Anfragende Session "
		+ session3.getId() + " ist nicht an der Freundschaft "
		+ friendOfSessions4.get(0).getId() + " beteiligt!",
		deleteFriendship.getMessage());
    }

    /**
     * Überprüft alle Fehler die beim erstellen einer Freundschaft auftreten
     * wenn diese schon existiert. Erwarte immer einen Fehler.
     */
    @Test
    public void kTestFriendshipAskedExist() {
	// Anfrage wurde schon gestellt zwischen xx und yy.
	ReturnCodeResponse addFriend = remoteSystem.addFriend(session4.getId(),
		session1.getUser().getEmail());
	Assert.assertEquals(ReturnCode.ERROR, addFriend.getReturnCode());
	Assert.assertEquals("Anfrage wurde schon gestellt zwischen "
		+ session4.getUser().getEmail() + " und "
		+ session1.getUser().getEmail() + ".", addFriend.getMessage());

	// Anfrage wurde schon abgelehnt zwischen xx und yy.
	addFriend = remoteSystem.addFriend(session1.getId(), session2.getUser()
		.getEmail());
	Assert.assertEquals(ReturnCode.ERROR, addFriend.getReturnCode());
	Assert.assertEquals("Anfrage wurde schon abgelehnt zwischen "
		+ session1.getUser().getEmail() + " und "
		+ session2.getUser().getEmail() + ".", addFriend.getMessage());

	// Anfrage wurde schon angenommen zwischen xx und yy.
	addFriend = remoteSystem.addFriend(session3.getId(), session1.getUser()
		.getEmail());
	Assert.assertEquals(ReturnCode.ERROR, addFriend.getReturnCode());
	Assert.assertEquals("Anfrage wurde schon angenommen zwischen "
		+ session3.getUser().getEmail() + " und "
		+ session1.getUser().getEmail() + ".", addFriend.getMessage());
    }

    /**
     * Überprüft ob sich durch die vorherigen Testfälle der Status der
     * Freundschaften angepasst hat.
     */
    @Test
    public void lTestGetFriendshipsChangedStatus() {
	FriendsResponse friends = remoteSystem.getFriends(session1.getId());
	Assert.assertEquals(ReturnCode.OK, friends.getReturnCode());
	Assert.assertEquals(3, friends.getFriends().size());

	List<FriendshipStatus> friendshipStates = new ArrayList<FriendshipStatus>();
	friendshipStates.add(friends.getFriends().get(0).getStatus());
	friendshipStates.add(friends.getFriends().get(1).getStatus());
	friendshipStates.add(friends.getFriends().get(2).getStatus());

	Assert.assertTrue(friendshipStates.contains(FriendshipStatus.DENIED));
	Assert.assertTrue(friendshipStates.contains(FriendshipStatus.ACCEPT));
	Assert.assertTrue(friendshipStates.contains(FriendshipStatus.ASKED));

	friends = remoteSystem.getFriends(session2.getId());
	Assert.assertEquals(ReturnCode.OK, friends.getReturnCode());
	Assert.assertEquals(1, friends.getFriends().size());
	Assert.assertEquals(FriendshipStatus.DENIED, friends.getFriends()
		.get(0).getStatus());

	friends = remoteSystem.getFriends(session3.getId());
	Assert.assertEquals(ReturnCode.OK, friends.getReturnCode());
	Assert.assertEquals(1, friends.getFriends().size());
	Assert.assertEquals(FriendshipStatus.ACCEPT, friends.getFriends()
		.get(0).getStatus());

	friends = remoteSystem.getFriends(session4.getId());
	Assert.assertEquals(ReturnCode.OK, friends.getReturnCode());
	Assert.assertEquals(1, friends.getFriends().size());
	Assert.assertEquals(FriendshipStatus.ASKED, friends.getFriends().get(0)
		.getStatus());
    }

    /**
     * Überprüft ob eine Freundschaft gelöscht werden kann. Erwartet das die
     * Freundschaft gelöscht wird.
     */
    @Test
    public void mTestFriendshipDeleteSuccess() {
	FriendsResponse friends = remoteSystem.getFriends(session2.getId());
	List<FriendshipTO> friendOfSessions2 = friends.getFriends();
	friends = remoteSystem.getFriends(session3.getId());
	List<FriendshipTO> friendOfSessions3 = friends.getFriends();
	friends = remoteSystem.getFriends(session4.getId());
	List<FriendshipTO> friendOfSessions4 = friends.getFriends();

	ReturnCodeResponse deleteFriendship = remoteSystem.deleteFriendship(
		friendOfSessions2.get(0).getId(), session1.getId());
	Assert.assertEquals(ReturnCode.OK, deleteFriendship.getReturnCode());
	deleteFriendship = remoteSystem.deleteFriendship(
		friendOfSessions3.get(0).getId(), session3.getId());
	Assert.assertEquals(ReturnCode.OK, deleteFriendship.getReturnCode());
	deleteFriendship = remoteSystem.deleteFriendship(
		friendOfSessions4.get(0).getId(), session4.getId());
	Assert.assertEquals(ReturnCode.OK, deleteFriendship.getReturnCode());

	// Prüfe ob wirklich alle gelöscht
	friends = remoteSystem.getFriends(session1.getId());
	Assert.assertEquals(ReturnCode.OK, friends.getReturnCode());
	Assert.assertEquals(0, friends.getFriends().size());

    }
}
