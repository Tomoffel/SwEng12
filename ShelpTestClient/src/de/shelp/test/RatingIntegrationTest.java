package de.shelp.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import de.shelp.integration.RatingIntegration;
import de.shelp.integration.RatingIntegrationService;
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
public class RatingIntegrationTest {

    private static RatingIntegration remoteSystem;

    /**
     * Baut einmalig die Verbindung zum Server auf
     */
    @BeforeClass
    public static void initTestCase() {
	RatingIntegrationService service = new RatingIntegrationService();
	remoteSystem = service.getRatingIntegrationPort();
    }

    /**
     * Testet ob ein neuer Benutzer registriert werden kann. Erwartet das OK und
     * der angelegte User zurück gegeben wird.
     */
    @Test
    public void aTestRegUserSuccess() {
	
    }

   
}
