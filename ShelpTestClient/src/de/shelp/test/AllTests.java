package de.shelp.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Testet alle Testfälle
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ UserIntegrationTest.class, StateIntegrationTest.class,
	FriendIntegrationTest.class, RequestIntegrationTest.class,
	TourIntegrationTest.class, RatingIntegrationTest.class, })
public class AllTests {

}
