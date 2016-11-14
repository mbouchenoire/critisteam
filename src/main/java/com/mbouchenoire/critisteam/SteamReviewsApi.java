package com.mbouchenoire.critisteam;

import com.mbouchenoire.critisteam.error.SteamReviewsException;

/**
 * @author mbouchenoire
 */
public class SteamReviewsApi implements UserReviewsSummaryRepository {

    private final UserReviewsSummaryRepository userReviewsSummaryRepository;

    public SteamReviewsApi() {
        super();
        this.userReviewsSummaryRepository = new UserReviewsSummaryAppLandingPageScraper(new URLConnectionHtmlRetriever());
    }

    @Override
    public UserReviewsSummary getSummary(int appId, final TimePeriod timePeriod) throws SteamReviewsException {
        return userReviewsSummaryRepository.getSummary(appId, timePeriod);
    }

    protected UserReviewsSummaryRepository getUserReviewsSummaryRepository() {
        return this.userReviewsSummaryRepository;
    }
}
