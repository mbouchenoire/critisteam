package com.mbouchenoire.critisteam;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @mbouchenoire
 */
public class UserProfileTest extends TestCase {

    private static final String NAME = "name";
    private static final int OWNED_GAMES_NUMBER = 1;
    private static final int REVIEWS_NUMBER = 1;

    private static final UserProfile userProfile1 = new UserProfile(NAME, OWNED_GAMES_NUMBER, REVIEWS_NUMBER);
    private static final UserProfile userProfile2 = new UserProfile(NAME, OWNED_GAMES_NUMBER, REVIEWS_NUMBER);
    private static final UserProfile userProfile3 = new UserProfile(NAME + "1", OWNED_GAMES_NUMBER + 1, REVIEWS_NUMBER + 1);

    public UserProfileTest(String testName) {
        super(testName);
    }

    public static TestSuite suite() {
        return new TestSuite(UserProfileTest.class);
    }

    public void testInvalidName() {
        try {
            new UserProfile(null, OWNED_GAMES_NUMBER, REVIEWS_NUMBER);
            assertTrue(false);
        } catch(IllegalArgumentException iae) {
            assertNotNull(iae);
        }

        try {
            new UserProfile("", OWNED_GAMES_NUMBER, REVIEWS_NUMBER);
            assertTrue(false);
        } catch(IllegalArgumentException iae) {
            assertNotNull(iae);
        }
    }

    public void testNegativeOnwedGamesNumber() {
        try {
            new UserProfile(NAME, -1, REVIEWS_NUMBER);
            assertTrue(false);
        } catch(IllegalArgumentException iae) {
            assertNotNull(iae);
        }
    }

    public void testNegativeReviewsNumber() {
        try {
            new UserProfile(NAME, OWNED_GAMES_NUMBER, -1);
            assertTrue(false);
        } catch(IllegalArgumentException iae) {
            assertNotNull(iae);
        }
    }

    public void testEquals() {
        new EqualsAndHashCodeTest(userProfile1, userProfile2, userProfile3).testEquals();
    }

    public void testHashCode() {
        new EqualsAndHashCodeTest(userProfile1, userProfile2, userProfile3).testHashCode();
    }

    public void testToString() {
        assertEquals(NAME, userProfile1.toString());
    }
}
