package de.shelp.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import de.shelp.integration.ReturnCode;
import de.shelp.integration.ReturnCodeResponse;
import de.shelp.integration.UserIntegration;
import de.shelp.integration.UserIntegrationService;
import de.shelp.integration.UserResponse;
import de.shelp.integration.UsersResponse;

/**
 * Testet alle Webservice-Schnittstellen zur UserIntegration.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserIntegrationTest {

	private static UserIntegration remoteSystem;

	/**
	 * Baut einmalig die Verbindung zum Server auf
	 */
	@BeforeClass
	public static void initTestCase() {
		UserIntegrationService service = new UserIntegrationService();
		remoteSystem = service.getUserIntegrationPort();
	}

	/**
	 * Testet ob ein neuer Benutzer registriert werden kann. Erwartet das OK und
	 * der angelegte User zurückgegeben wird.
	 */
	@Test
	public void aTestRegUserSuccess() {
		UserResponse loginResponse = remoteSystem.regUser(
				"thomas@sennekamp.de", "test123");
		Assert.assertEquals(loginResponse.getReturnCode(), ReturnCode.OK);
		Assert.assertEquals(loginResponse.getSession().getUser().getEmail(),
				"thomas@sennekamp.de");
	}

	/**
	 * Testet ob ein schon existierender Benutzer angemeldet werden kann.
	 * Erwartet das ein ERROR zurück gegeben wird.
	 */
	@Test
	public void bTestRegUserExist() {
		UserResponse loginResponse = remoteSystem.regUser(
				"thomas@sennekamp.de", "test123");
		Assert.assertEquals(loginResponse.getReturnCode(), ReturnCode.ERROR);
	}

	/**
	 * Testet ob ein Benutzer an- und abgemeldet weden kann. Erwarte beim
	 * Anmelden ein OK + den User und beim Abmelden ein OK.
	 */
	@Test
	public void cTestLoginAndLogoutSuccess() {
		UserResponse loginResponse = remoteSystem.login("thomas@sennekamp.de",
				"test123");
		Assert.assertEquals(loginResponse.getReturnCode(), ReturnCode.OK);
		Assert.assertEquals(loginResponse.getSession().getUser().getEmail(),
				"thomas@sennekamp.de");
		ReturnCodeResponse logout = remoteSystem.logout(loginResponse
				.getSession().getId());
		Assert.assertEquals(logout.getReturnCode(), ReturnCode.OK);
	}

	/**
	 * Testet ein fehlerhaftes Login (Falsches Passwort, nicht existierenden
	 * Benutzer) und Logout (ungültige Session). Erwartet das immer ein ERROR
	 * zurück gegeben wird.
	 */
	@Test
	public void dTestLoginAndLogoutFailed() {
		UserResponse loginResponse = remoteSystem.login("thomas@sennekamp.de",
				"test12");
		Assert.assertEquals(loginResponse.getReturnCode(), ReturnCode.ERROR);

		loginResponse = remoteSystem.login("thomas", "test123");
		Assert.assertEquals(loginResponse.getReturnCode(), ReturnCode.ERROR);

		ReturnCodeResponse logout = remoteSystem.logout(10);
		Assert.assertEquals(logout.getReturnCode(), ReturnCode.ERROR);
	}

	/**
	 * Testet ob die Benutzersuche funktioniert.
	 */
	@Test
	public void eTestSearchUsers() {
		// generate some testusers
		remoteSystem.regUser("jos@sennekamp.de", "test123");
		remoteSystem.regUser("theresa@sennekamp.de", "test123");
		remoteSystem.regUser("roman@sennekamp.de", "test123");
		remoteSystem.regUser("josef@sennekamp.de", "test123");
		remoteSystem.regUser("peter@sennekamp.de", "test123");

		UsersResponse searchUsers = remoteSystem.searchUsers("roman");
		Assert.assertEquals("roman@sennekamp.de", searchUsers.getUsers().get(0)
				.getEmail());

		searchUsers = remoteSystem.searchUsers("jos");
		Assert.assertEquals("jos@sennekamp.de", searchUsers.getUsers().get(0)
				.getEmail());
		Assert.assertEquals("josef@sennekamp.de", searchUsers.getUsers().get(1)
				.getEmail());

		searchUsers = remoteSystem.searchUsers("th");
		Assert.assertEquals("thomas@sennekamp.de", searchUsers.getUsers()
				.get(0).getEmail());
		Assert.assertEquals("theresa@sennekamp.de",
				searchUsers.getUsers().get(1).getEmail());

		searchUsers = remoteSystem.searchUsers("kasdsdfsdf");
		Assert.assertEquals(0, searchUsers.getUsers().size());
	}
}
