package com.mbouchenoire.critisteam;

import com.mbouchenoire.critisteam.error.SteamReviewsException;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author mbouchenoire
 */
public class UserReviewsSummaryAppLandingPageScraperTest extends TestCase {

    private static final UserReviewsSummaryAppLandingPageScraper scraper
            = new UserReviewsSummaryAppLandingPageScraper(new URLConnectionHtmlRetriever());

    private static final UserReviewsSummaryRepositoryTest tester
            = new UserReviewsSummaryRepositoryTest(scraper);

    public UserReviewsSummaryAppLandingPageScraperTest(String testName) {
        super(testName);
    }

    public static TestSuite suite() {
        return new TestSuite(UserReviewsSummaryAppLandingPageScraperTest.class);
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
