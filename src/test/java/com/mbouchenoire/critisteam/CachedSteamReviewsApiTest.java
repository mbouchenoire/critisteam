package com.mbouchenoire.critisteam;

import com.google.common.base.Stopwatch;
import com.mbouchenoire.critisteam.error.SteamReviewsException;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @author mbouchenoire
 */
public class CachedSteamReviewsApiTest extends TestCase {

    private static final int APP_ID = 730; // CS:GO
    private static final TimePeriod TIME_PERIOD = TimePeriod.OVERALL;

    private final SteamReviewsApiTest apiTester;

    public CachedSteamReviewsApiTest(String testName) {
        super(testName);
        final SteamReviewsApi cachedApi = new CachedSteamReviewsApi(new CacheConfig(5, TimeUnit.MINUTES));
        this.apiTester = new SteamReviewsApiTest("CachedSteamReviewsApiTest", cachedApi);
    }

    public static TestSuite suite() {
        return new TestSuite(CachedSteamReviewsApiTest.class);
    }

    public void testGetSummaryInvalidAppId() {
        apiTester.testGetSummaryInvalidAppId();
    }

    public void testGetSummaryOverall() throws SteamReviewsException {
        apiTester.testGetSummaryOverall();
    }

    public void testGetSummaryRecent() throws SteamReviewsException {
        apiTester.testGetSummaryRecent();
    }

    public void testGetSummaryRecentAbsent() throws SteamReviewsException {
        apiTester.testGetSummaryRecentAbsent();
    }

    public void testGetSummarySecondAccessFaster() throws SteamReviewsException {
        final SteamReviewsApi cachedApi = new CachedSteamReviewsApi(new CacheConfig(1, TimeUnit.HOURS));

        final Stopwatch firstStopWatch = Stopwatch.createStarted();
        cachedApi.getSummary(APP_ID, TIME_PERIOD);
        firstStopWatch.stop();

        final Stopwatch secondStopWatch = Stopwatch.createStarted();
        cachedApi.getSummary(APP_ID, TIME_PERIOD);
        secondStopWatch.stop();

        assertTrue(firstStopWatch.elapsed(TimeUnit.NANOSECONDS) > secondStopWatch.elapsed(TimeUnit.NANOSECONDS));
    }

    public void testCachedApiGetSummaryFasterThanNoCache() throws SteamReviewsException {
        final SteamReviewsApi noCacheApi = new SteamReviewsApi();
        final SteamReviewsApi cachedApi = new CachedSteamReviewsApi(new CacheConfig(1, TimeUnit.HOURS));

        final Stopwatch noCacheStopwatch = Stopwatch.createStarted();
        noCacheApi.getSummary(APP_ID, TIME_PERIOD);
        noCacheApi.getSummary(APP_ID, TIME_PERIOD);
        noCacheApi.getSummary(APP_ID, TIME_PERIOD);
        noCacheStopwatch.stop();

        final Stopwatch cachedStopwatch = Stopwatch.createStarted();
        cachedApi.getSummary(APP_ID, TIME_PERIOD);
        cachedApi.getSummary(APP_ID, TIME_PERIOD);
        cachedApi.getSummary(APP_ID, TIME_PERIOD);
        cachedStopwatch.stop();

        assertTrue(noCacheStopwatch.elapsed(TimeUnit.NANOSECONDS) > cachedStopwatch.elapsed(TimeUnit.NANOSECONDS));
    }

    public void testGetReviews() throws SteamReviewsException {
        apiTester.testGetReviews();
    }

    public void testGetReviewsSecondAccessFaster() throws SteamReviewsException {
        final SteamReviewsApi cachedApi = new CachedSteamReviewsApi(new CacheConfig(1, TimeUnit.HOURS));

        final Stopwatch firstStopWatch = Stopwatch.createStarted();
        cachedApi.getReviews(APP_ID, SteamSupportedLanguage.defaultLanguage());
        firstStopWatch.stop();

        final Stopwatch secondStopWatch = Stopwatch.createStarted();
        cachedApi.getReviews(APP_ID, SteamSupportedLanguage.defaultLanguage());
        secondStopWatch.stop();

        assertTrue(firstStopWatch.elapsed(TimeUnit.NANOSECONDS) > secondStopWatch.elapsed(TimeUnit.NANOSECONDS));
    }

    public void testCachedApiGetReviewsFasterThanNoCache() throws SteamReviewsException {
        final SteamReviewsApi noCacheApi = new SteamReviewsApi();
        final SteamReviewsApi cachedApi = new CachedSteamReviewsApi(new CacheConfig(1, TimeUnit.HOURS));

        final Stopwatch noCacheStopwatch = Stopwatch.createStarted();
        noCacheApi.getReviews(APP_ID, SteamSupportedLanguage.defaultLanguage());
        noCacheApi.getReviews(APP_ID, SteamSupportedLanguage.defaultLanguage());
        noCacheApi.getReviews(APP_ID, SteamSupportedLanguage.defaultLanguage());
        noCacheStopwatch.stop();

        final Stopwatch cachedStopwatch = Stopwatch.createStarted();
        cachedApi.getReviews(APP_ID, SteamSupportedLanguage.defaultLanguage());
        cachedApi.getReviews(APP_ID, SteamSupportedLanguage.defaultLanguage());
        cachedApi.getReviews(APP_ID, SteamSupportedLanguage.defaultLanguage());
        cachedStopwatch.stop();

        assertTrue(noCacheStopwatch.elapsed(TimeUnit.NANOSECONDS) > cachedStopwatch.elapsed(TimeUnit.NANOSECONDS));
    }

    public void testGetReviewsDifferentLanguages() throws SteamReviewsException {
        apiTester.testGetReviewsDifferentLanguages();
    }
}
