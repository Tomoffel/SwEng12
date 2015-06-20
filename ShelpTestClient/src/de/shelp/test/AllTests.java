package de.shelp.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Ruft alle Testfälle auf. Da die JUnit-Test nicht im Fokus der
 * Prüfungsleistung liegen wurde aus Zeitgründen eine 100% Konformität der
 * Unit-Tests nicht eingehaltet. Daher ergibt nur die gesamte TestSuite ein
 * positives Ergebis, da es zwischen den Testfällen Abhängigkeiten gibt, die die
 * Testergebnisse beeinflussen. Desweiteren können die Testfälle nur auf einer
 * neuen/geleerten Datenbank ausgeführt werden.
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ UserIntegrationTest.class, StateIntegrationTest.class,
	FriendIntegrationTest.class, RequestIntegrationTest.class,
	TourIntegrationTest.class, RatingIntegrationTest.class, })
public class AllTests {

}
