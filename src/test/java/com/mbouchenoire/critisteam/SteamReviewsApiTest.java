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
    private final UserReviewsRepositoryTest userReviewsRepositoryTest;

    public SteamReviewsApiTest(String testName) {
        this(testName, new SteamReviewsApi());
    }

    public SteamReviewsApiTest(String testName, SteamReviewsApi api) {
        super(testName);
        this.api = api;
        this.userReviewsSummaryRepositoryTester = new UserReviewsSummaryRepositoryTest(api);
        this.userReviewsRepositoryTest = new UserReviewsRepositoryTest(api);
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

    public void testGetReviews() throws SteamReviewsException {
        userReviewsRepositoryTest.testGetReviews();
    }

    public void testGetReviewsDifferentLanguages() throws SteamReviewsException {
        userReviewsRepositoryTest.testGetReviewsDifferentLanguages();
    }
}
