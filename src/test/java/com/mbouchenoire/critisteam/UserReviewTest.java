package com.mbouchenoire.critisteam;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.lang.StringUtils;

/**
 * @author mbouchenoire
 */
public class UserReviewTest extends TestCase {

    private static final String ID = "123";
    private static final UserProfile PROFILE = new UserProfile("test_profile", 1, 1);
    private static final int HOURS_ON_RECORD = 1;
    private static final boolean IS_RECOMMENDATION = true;
    private static final String CONTENT = "cool!";
    private static final int RATINGS_NUMBER = 1;
    private static final int HELPFUL_NUMBER = 1;
    private static final int FUNNY_NUMBER = 1;

    private static final UserReview userReview1 = new UserReview(ID, PROFILE, HOURS_ON_RECORD, IS_RECOMMENDATION,
                                                                    CONTENT, RATINGS_NUMBER, HELPFUL_NUMBER, FUNNY_NUMBER);

    private static final UserReview userReview2 = new UserReview(ID, PROFILE, HOURS_ON_RECORD, IS_RECOMMENDATION,
                                                                    CONTENT, RATINGS_NUMBER, HELPFUL_NUMBER, FUNNY_NUMBER);

    private static final UserReview userReview3 = new UserReview(ID + "0", PROFILE, HOURS_ON_RECORD + 1, !IS_RECOMMENDATION,
                                                                    CONTENT + "!", RATINGS_NUMBER + 1, HELPFUL_NUMBER + 1,
                                                                    FUNNY_NUMBER + 1);

    public UserReviewTest(String testName) {
        super(testName);
    }

    public static TestSuite suite() {
        return new TestSuite(UserReviewTest.class);
    }

    public void testNullId() {
        try {
            new UserReview(null, PROFILE, HOURS_ON_RECORD, IS_RECOMMENDATION, CONTENT, RATINGS_NUMBER, HELPFUL_NUMBER, FUNNY_NUMBER);
            assertTrue(false);
        } catch(IllegalArgumentException iae) {
            assertNotNull(iae);
        }
    }

    public void testNullUserProfile() {
        try {
            new UserReview(ID, null, HOURS_ON_RECORD, IS_RECOMMENDATION, CONTENT, RATINGS_NUMBER, HELPFUL_NUMBER, FUNNY_NUMBER);
            assertTrue(false);
        } catch(IllegalArgumentException iae) {
            assertNotNull(iae);
        }
    }

    public void testNegativeHoursOnRecord() {
        try {
            new UserReview(ID, PROFILE, -1, IS_RECOMMENDATION, CONTENT, RATINGS_NUMBER, HELPFUL_NUMBER, FUNNY_NUMBER);
            assertTrue(false);
        } catch(IllegalArgumentException iae) {
            assertNotNull(iae);
        }
    }

    public void testInvalidContent() {
        try {
            new UserReview(ID, PROFILE, HOURS_ON_RECORD, IS_RECOMMENDATION, null, RATINGS_NUMBER, HELPFUL_NUMBER, FUNNY_NUMBER);
            assertTrue(false);
        } catch(IllegalArgumentException iae) {
            assertNotNull(iae);
        }

        try {
            new UserReview(ID, PROFILE, HOURS_ON_RECORD, IS_RECOMMENDATION, "", RATINGS_NUMBER, HELPFUL_NUMBER, FUNNY_NUMBER);
            assertTrue(false);
        } catch(IllegalArgumentException iae) {
            assertNotNull(iae);
        }
    }

    public void testNegativeRatingsNumber() {
        try {
            new UserReview(ID, PROFILE, HOURS_ON_RECORD, IS_RECOMMENDATION, CONTENT, -1, HELPFUL_NUMBER, FUNNY_NUMBER);
            assertTrue(false);
        } catch(IllegalArgumentException iae) {
            assertNotNull(iae);
        }
    }

    public void testNegativeHelpfulRatingsNumber() {
        try {
            new UserReview(ID, PROFILE, HOURS_ON_RECORD, IS_RECOMMENDATION, CONTENT, RATINGS_NUMBER, -1, FUNNY_NUMBER);
            assertTrue(false);
        } catch(IllegalArgumentException iae) {
            assertNotNull(iae);
        }
    }

    public void testNegativeFunnyRatingsNumber() {
        try {
            new UserReview(ID, PROFILE, HOURS_ON_RECORD, IS_RECOMMENDATION, CONTENT, RATINGS_NUMBER, HELPFUL_NUMBER, -1);
            assertTrue(false);
        } catch(IllegalArgumentException iae) {
            assertNotNull(iae);
        }
    }

    public void testEquals() {
        new EqualsAndHashCodeTest(userReview1, userReview2, userReview3).testEquals();
    }

    public void testHashCode() {
        new EqualsAndHashCodeTest(userReview1, userReview2, userReview3).testHashCode();
    }

    public void testToString() {
        assertEquals(ID, userReview1.toString());
    }

    public static void testUserReview(final UserReview userReview) {
        assertTrue(StringUtils.isNotEmpty(userReview.getId()));
        assertTrue(StringUtils.isNotEmpty(userReview.getContent()));
        assertTrue(userReview.getRatingsNumber() >= 0);
        assertTrue(userReview.getHelpfulRatingsNumber() >= 0);
        assertTrue(userReview.getFunnyRatingsNumber() >= 0);
        assertTrue(userReview.getHoursOnRecord() >= 0);

        assertNotNull(userReview.getProfile());
        assertTrue(StringUtils.isNotEmpty(userReview.getProfile().getName()));
        assertTrue(userReview.getProfile().getOwnedGamesNumber() > 0);
        assertTrue(userReview.getProfile().getReviewsNumber() > 0);
    }
}
