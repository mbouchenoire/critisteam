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

    private final EqualsAndHashCodeTest equalsAndHashCodeTester =
            new EqualsAndHashCodeTest(summary1, summary2, summary3);

    public UserReviewsSummaryTest(String testName) {
        super(testName);
    }

    public static TestSuite suite() {
        return new TestSuite(UserReviewsSummaryTest.class);
    }

    public void testEquals() {
        equalsAndHashCodeTester.testEquals();
    }

    public void testHashCode() {
        equalsAndHashCodeTester.testHashCode();
    }

    public void testToString() {
        assertEquals(summary1.toString(), "50% - Mixed (100 reviews)");
    }
}
