package com.mbouchenoire.critisteam;

import com.mbouchenoire.critisteam.error.SteamReviewsException;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author mbouchenoire
 */
public class UserReviewsAppLandingPageScraperTest extends TestCase {

    private static final UserReviewsAppLandingPageScraper scraper
            = new UserReviewsAppLandingPageScraper(new URLConnectionHtmlRetriever());

    private static final UserReviewsRepositoryTest tester
            = new UserReviewsRepositoryTest(scraper);

    public UserReviewsAppLandingPageScraperTest(String testName) {
        super(testName);
    }

    public static TestSuite suite() {
        return new TestSuite(UserReviewsAppLandingPageScraperTest.class);
    }

    public void testGetReviews() throws SteamReviewsException {
        tester.testGetReviews();
    }
}
