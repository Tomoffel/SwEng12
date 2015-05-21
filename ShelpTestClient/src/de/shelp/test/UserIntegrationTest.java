package de.shelp.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.shelp.integration.ReturnCode;
import de.shelp.integration.ReturnCodeResponse;
import de.shelp.integration.UserIntegration;
import de.shelp.integration.UserIntegrationService;
import de.shelp.integration.UserResponse;
import de.shelp.integration.UsersResponse;

public class UserIntegrationTest {

    private static UserIntegration remoteSystem;

    @Before
    public void initTestCase() {
	UserIntegrationService service = new UserIntegrationService();
	remoteSystem = service.getUserIntegrationPort();
    }

    @Test
    public void aTestRegUserSuccess() {
	UserResponse loginResponse = remoteSystem.regUser( "thomas@sennekamp.de", "test123");
	Assert.assertEquals(loginResponse.getReturnCode(), ReturnCode.OK);
	Assert.assertEquals(loginResponse.getSession().getUser().getEmail(), "thomas@sennekamp.de");
    }

    @Test
    public void bTestRegUserExist() {
	UserResponse loginResponse = remoteSystem.regUser("thomas@sennekamp.de", "test123");
	Assert.assertEquals(loginResponse.getReturnCode(), ReturnCode.ERROR);
    }

    @Test
    public void cTestLoginAndLogoutSuccess() {
	UserResponse loginResponse = remoteSystem.login("thomas@sennekamp.de", "test123");
	Assert.assertEquals(loginResponse.getReturnCode(), ReturnCode.OK);
	Assert.assertEquals(loginResponse.getSession().getUser().getEmail(), "thomas@sennekamp.de");
	ReturnCodeResponse logout = remoteSystem.logout(loginResponse.getSession().getId());
	Assert.assertEquals(logout.getReturnCode(), ReturnCode.OK);
    }

    @Test
    public void dTestLoginAndLogoutFailed() {
	UserResponse loginResponse = remoteSystem.login("thomas@sennekamp.de", "test12");
	Assert.assertEquals(loginResponse.getReturnCode(), ReturnCode.ERROR);

	ReturnCodeResponse logout = remoteSystem.logout(10);
	Assert.assertEquals(logout.getReturnCode(), ReturnCode.ERROR);
    }

    @Test
    public void eTestSearchUsers() {
	// generate some testusers
	remoteSystem.regUser( "jos@sennekamp.de", "test123");
	remoteSystem.regUser( "theresa@sennekamp.de", "test123");
	remoteSystem.regUser( "roman@sennekamp.de", "test123");
	remoteSystem.regUser( "josef@sennekamp.de", "test123");
	remoteSystem.regUser( "peter@sennekamp.de", "test123");

	UsersResponse searchUsers = remoteSystem.searchUsers("roman");
	Assert.assertEquals("roman@sennekamp.de", searchUsers.getUsers().get(0).getEmail());

	searchUsers = remoteSystem.searchUsers("jos");
	Assert.assertEquals("jos@sennekamp.de", searchUsers.getUsers().get(0).getEmail());
	Assert.assertEquals("josef@sennekamp.de", searchUsers.getUsers().get(1).getEmail());

	searchUsers = remoteSystem.searchUsers("th");
	Assert.assertEquals("thomas@sennekamp.de", searchUsers.getUsers().get(0).getEmail());
	Assert.assertEquals("theresa@sennekamp.de", searchUsers.getUsers().get(1).getEmail());

	searchUsers = remoteSystem.searchUsers("kasdsdfsdf");
	Assert.assertEquals(0, searchUsers.getUsers().size());
    }
}
