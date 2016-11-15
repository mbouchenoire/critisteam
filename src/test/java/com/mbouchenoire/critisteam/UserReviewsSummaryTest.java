package com.mbouchenoire.critisteam;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author mbouchenoire
 */
public class UserReviewsSummaryTest extends TestCase {

    private static final UserReviewsSummary summary1 = new UserReviewsSummary(100, 50, UserReviewsSummaryLabel.MIXED);
    private static final UserReviewsSummary summary2 = new UserReviewsSummary(100, 50, UserReviewsSummaryLabel.MIXED);
    private static final UserReviewsSummary summary3 = new UserReviewsSummary(200, 80, UserReviewsSummaryLabel.POSITIVE);

    public UserReviewsSummaryTest(String testName) {
        super(testName);
    }

    public static TestSuite suite() {
        return new TestSuite(UserReviewsSummaryTest.class);
    }

    public void testEquals() {
        assertFalse(summary1.equals(null));
        assertFalse(summary1.equals(new Object()));
        assertTrue(summary1.equals(summary1));
        assertTrue(summary1.equals(summary2));
        assertFalse(summary1.equals(summary3));
    }

    public void testHashCode() {
        assertTrue(summary1.hashCode() == summary2.hashCode());
        assertFalse(summary1.hashCode() == summary3.hashCode());
    }

    public void testToString() {
        assertEquals(summary1.toString(), "50% - Mixed (100 reviews)");
    }
}
