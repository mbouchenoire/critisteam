package com.mbouchenoire.critisteam;

import com.mbouchenoire.critisteam.error.SteamReviewsException;

/**
 * A dao used to query user reviews data.
 *
 * There is currently no API provided by Valve to query user reviews data,
 * this interface exists to welcome a future API without breaking the
 * current implementation and allow back-porting.
 *
 * @author mbouchenoire
 */
public interface UserReviewsSummaryRepository {

    /**
     * Retrives an user reviews summary of an Steam App using its id.
     *
     * @param appId the Steam App id of which the reviews summary will be retrieved.
     * @param timePeriod the time period used to filter reviews.
     * @return the user reviews summary.
     * @throws SteamReviewsException
     */
    UserReviewsSummary getSummary(int appId, TimePeriod timePeriod) throws SteamReviewsException;
}
