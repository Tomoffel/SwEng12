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
	UserResponse loginResponse = remoteSystem.regUser("Thomas", "test123", "thomas@sennekamp.de");
	Assert.assertEquals(loginResponse.getReturnCode(), ReturnCode.OK);
	Assert.assertEquals(loginResponse.getSession().getUser().getName(), "Thomas");
	Assert.assertEquals(loginResponse.getSession().getUser().getEmail(), "thomas@sennekamp.de");
    }

    @Test
    public void bTestRegUserExist() {
	UserResponse loginResponse = remoteSystem.regUser("Thomas", "test123", "thomas@sennekamp.de");
	Assert.assertEquals(loginResponse.getReturnCode(), ReturnCode.ERROR);
    }

    @Test
    public void cTestLoginAndLogoutSuccess() {
	UserResponse loginResponse = remoteSystem.login("Thomas", "test123");
	Assert.assertEquals(loginResponse.getReturnCode(), ReturnCode.OK);
	Assert.assertEquals(loginResponse.getSession().getUser().getName(), "Thomas");
	Assert.assertEquals(loginResponse.getSession().getUser().getEmail(), "thomas@sennekamp.de");
	ReturnCodeResponse logout = remoteSystem.logout(loginResponse.getSession().getId());
	Assert.assertEquals(logout.getReturnCode(), ReturnCode.OK);
    }

    @Test
    public void dTestLoginAndLogoutFailed() {
	UserResponse loginResponse = remoteSystem.login("Thomas", "test12");
	Assert.assertEquals(loginResponse.getReturnCode(), ReturnCode.ERROR);

	ReturnCodeResponse logout = remoteSystem.logout(10);
	Assert.assertEquals(logout.getReturnCode(), ReturnCode.ERROR);
    }

    @Test
    public void eTestSearchUsers() {
	// generate some testusers
	remoteSystem.regUser("Jos", "test123", "thomas@sennekamp.de");
	remoteSystem.regUser("Theresa", "test123", "thomas@sennekamp.de");
	remoteSystem.regUser("Roman", "test123", "thomas@sennekamp.de");
	remoteSystem.regUser("Josef", "test123", "thomas@sennekamp.de");
	remoteSystem.regUser("Peter", "test123", "thomas@sennekamp.de");

	UsersResponse searchUsers = remoteSystem.searchUsers("Roman");
	Assert.assertEquals("Roman", searchUsers.getUsers().get(0).getName());

	searchUsers = remoteSystem.searchUsers("Jos");
	Assert.assertEquals("Jos", searchUsers.getUsers().get(0).getName());
	Assert.assertEquals("Josef", searchUsers.getUsers().get(1).getName());

	searchUsers = remoteSystem.searchUsers("Th");
	Assert.assertEquals("Thomas", searchUsers.getUsers().get(0).getName());
	Assert.assertEquals("Theresa", searchUsers.getUsers().get(1).getName());

	searchUsers = remoteSystem.searchUsers("kasdsdfsdf");
	Assert.assertEquals(0, searchUsers.getUsers().size());
    }
}
