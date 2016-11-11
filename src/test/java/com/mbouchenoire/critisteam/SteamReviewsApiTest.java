package com.mbouchenoire.critisteam;

import com.mbouchenoire.critisteam.error.SteamReviewsException;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author mbouchenoire
 */
public class SteamReviewsApiTest extends TestCase {

    private static final SteamReviewsApi api = new SteamReviewsApi();

    private static final UserReviewsSummaryRepositoryTest tester
            = new UserReviewsSummaryRepositoryTest(api);

    public SteamReviewsApiTest(String testName) {
        super(testName);
    }

    public static TestSuite suite() {
        return new TestSuite(SteamReviewsApiTest.class);
    }

    public void testGetSummaryInvalidAppId() {
        tester.testGetSummaryInvalidAppId();
    }

    public void testGetSummaryOverall() throws SteamReviewsException {
        tester.testGetSummaryOverall();
    }

    public void testGetSummaryRecent() throws SteamReviewsException {
        tester.testGetSummaryRecent();
    }

    public void testGetSummaryRecentAbsent() throws SteamReviewsException {
        tester.testGetSummaryRecentAbsent();
    }
}
