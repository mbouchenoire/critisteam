package com.mbouchenoire.critisteam;

import com.mbouchenoire.critisteam.error.SteamReviewsException;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author mbouchenoire
 */
public class SteamReviewsApiTest extends TestCase {

    private final SteamReviewsApi api;
    private final UserReviewsSummaryRepositoryTest userReviewsSummaryRepositoryTester;

    public SteamReviewsApiTest(String testName) {
        this(testName, new SteamReviewsApi());
    }

    public SteamReviewsApiTest(String testName, SteamReviewsApi api) {
        super(testName);
        this.api = api;
        this.userReviewsSummaryRepositoryTester = new UserReviewsSummaryRepositoryTest(api);
    }

    public static TestSuite suite() {
        return new TestSuite(SteamReviewsApiTest.class);
    }

    public void testGetSummaryInvalidAppId() {
        userReviewsSummaryRepositoryTester.testGetSummaryInvalidAppId();
    }

    public void testGetSummaryOverall() throws SteamReviewsException {
        userReviewsSummaryRepositoryTester.testGetSummaryOverall();
    }

    public void testGetSummaryRecent() throws SteamReviewsException {
        userReviewsSummaryRepositoryTester.testGetSummaryRecent();
    }

    public void testGetSummaryRecentAbsent() throws SteamReviewsException {
        userReviewsSummaryRepositoryTester.testGetSummaryRecentAbsent();
    }
}
