package de.shelp.test;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import de.shelp.integration.FriendIntegration;
import de.shelp.integration.FriendIntegrationService;

/**
 * Testet alle Webservice-Schnittstellen zur UserIntegration.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FriendIntegrationTest {

    private static FriendIntegration remoteSystem;

    /**
     * Baut einmalig die Verbindung zum Server auf
     */
    @BeforeClass
    public static void initTestCase() {
	FriendIntegrationService service = new FriendIntegrationService();
	remoteSystem = service.getFriendIntegrationPort();
    }

    /**
     * Testet ob ein neuer Benutzer registriert werden kann. Erwartet das OK und
     * der angelegte User zurück gegeben wird.
     */
    @Test
    public void aTestRegUserSuccess() {
	
    }

}
