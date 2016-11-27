package com.mbouchenoire.critisteam;

import com.mbouchenoire.critisteam.error.SteamReviewsException;
import com.mbouchenoire.critisteam.error.NoRecentReviewsException;
import com.mbouchenoire.critisteam.error.SteamAppNotFoundException;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * @author mbouchenoire
 */
public class UserReviewsSummaryRepositoryTest {

    private static final int RECENT_AND_OVERALL_REVIEWS_APP_ID = 730; // CS:GO
    private static final int NO_RECENT_REVIEWS_APP_ID = 4900; // Zen of Sudoku

    private final UserReviewsSummaryRepository repository;

    public UserReviewsSummaryRepositoryTest(UserReviewsSummaryRepository repository) {
        this.repository = repository;
    }

    public void testGetSummaryInvalidAppId() {
        try {
            repository.getSummary(-1, TimePeriod.OVERALL);
            assertTrue(false);
        } catch (SteamAppNotFoundException sanfe) {
            assertNotNull(sanfe);
        } catch (SteamReviewsException cse) {
            assertTrue(false);
        }
    }

    public void testGetSummaryOverall() throws SteamReviewsException {
        final UserReviewsSummary summary = repository.getSummary(RECENT_AND_OVERALL_REVIEWS_APP_ID, TimePeriod.OVERALL);
        testSummary(summary);
    }

    public void testGetSummaryRecent() throws SteamReviewsException {
        final UserReviewsSummary summary = repository.getSummary(RECENT_AND_OVERALL_REVIEWS_APP_ID, TimePeriod.RECENT);
        testSummary(summary);
    }

    public void testGetSummaryRecentAbsent() throws SteamReviewsException {
        try {
            repository.getSummary(NO_RECENT_REVIEWS_APP_ID, TimePeriod.RECENT);
            assertTrue(false);
        } catch (NoRecentReviewsException nrre) {
            assertNotNull(nrre);
        }
    }

    private static void testSummary(final UserReviewsSummary summary) {
        assertNotNull(summary);
        assertTrue(summary.getReviewsNumber() > 0);
        assertTrue(summary.getPositivePercentage() > 0);
        assertTrue(summary.getPositivePercentage() < 100);
        assertNotNull(summary.getLabel());
    }
}
