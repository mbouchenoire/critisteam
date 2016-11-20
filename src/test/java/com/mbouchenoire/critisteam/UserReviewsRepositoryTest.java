package com.mbouchenoire.critisteam;

import com.mbouchenoire.critisteam.error.SteamReviewsException;

import java.util.Collection;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * @author mbouchenoire
 */
public class UserReviewsRepositoryTest {

    private static final int APP_ID = 730; // CS:GO

    private static final SteamSupportedLanguage LANGUAGE = SteamSupportedLanguage.FRENCH;

    private final UserReviewsRepository repository;

    public UserReviewsRepositoryTest(UserReviewsRepository repository) {
        this.repository = repository;
    }

    public void testGetReviews() throws SteamReviewsException {
        final Collection<UserReview> reviews = repository.getReviews(APP_ID, LANGUAGE);
        assertNotNull(reviews);
        assertTrue(reviews.size() > 0);
    }

    public void testGetReviewsDifferentLanguages() throws SteamReviewsException {
        final Collection<UserReview> englishReviews = repository.getReviews(APP_ID, SteamSupportedLanguage.ENGLISH);
        final Collection<UserReview> frenchReviews = repository.getReviews(APP_ID, SteamSupportedLanguage.FRENCH);

        for(UserReview englishReview: englishReviews) {
            UserReviewTest.testUserReview(englishReview);

            assertFalse(frenchReviews.contains(englishReview));
        }
    }
}
