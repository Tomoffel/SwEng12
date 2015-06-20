package de.shelp.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Ruft alle Testf�lle auf. Da die JUnit-Test nicht im Fokus der
 * Pr�fungsleistung liegen wurde aus Zeitgr�nden eine 100% Konformit�t der
 * Unit-Tests nicht eingehaltet. Daher ergibt nur die gesamte TestSuite ein
 * positives Ergebis, da es zwischen den Testf�llen Abh�ngigkeiten gibt, die die
 * Testergebnisse beeinflussen. Desweiteren k�nnen die Testf�lle nur auf einer
 * neuen/geleerten Datenbank ausgef�hrt werden.
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ UserIntegrationTest.class, StateIntegrationTest.class,
	FriendIntegrationTest.class, RequestIntegrationTest.class,
	TourIntegrationTest.class, RatingIntegrationTest.class, })
public class AllTests {

}
