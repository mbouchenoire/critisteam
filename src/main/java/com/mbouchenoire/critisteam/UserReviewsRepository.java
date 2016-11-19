package com.mbouchenoire.critisteam;

import com.mbouchenoire.critisteam.error.SteamReviewsException;

import java.util.Collection;

/**
 * @author mbouchenoire
 */
public interface UserReviewsRepository {

    Collection<UserReview> getReviews(int appId, SteamSupportedLanguage language) throws SteamReviewsException;
}
