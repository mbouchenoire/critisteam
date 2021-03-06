package com.mbouchenoire.critisteam;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author mbouchenoire
 */
public class UserReviewsSummaryLabelTest extends TestCase {

    public UserReviewsSummaryLabelTest(String testName) {
        super(testName);
    }

    public static TestSuite suite() {
        return new TestSuite(UserReviewsSummaryLabelTest.class);
    }

    public void testIsBetterThan() {
        assertTrue(UserReviewsSummaryLabel.POSITIVE.isBetterThan(UserReviewsSummaryLabel.NEGATIVE));
        assertFalse(UserReviewsSummaryLabel.NEGATIVE.isBetterThan(UserReviewsSummaryLabel.POSITIVE));
        assertFalse(UserReviewsSummaryLabel.POSITIVE.isBetterThan(UserReviewsSummaryLabel.POSITIVE));
    }

    public void testFromRank() {
        final UserReviewsSummaryLabel label = UserReviewsSummaryLabel.POSITIVE;
        assertEquals(label, UserReviewsSummaryLabel.fromRank(label.getRank()));

        try {
            UserReviewsSummaryLabel.fromRank(-1);
            assertTrue(false);
        } catch(IllegalArgumentException iae) {
            assertNotNull(iae);
        }
    }

    public void testFromText() {
        final UserReviewsSummaryLabel label = UserReviewsSummaryLabel.POSITIVE;
        assertEquals(label, UserReviewsSummaryLabel.fromText(label.getText()));

        try {
            UserReviewsSummaryLabel.fromText("?");
            assertTrue(false);
        } catch(IllegalArgumentException iae) {
            assertNotNull(iae);
        }
    }
}
