package com.mbouchenoire.critisteam;

import com.mbouchenoire.critisteam.error.SteamReviewsException;

/**
 * @author mbouchenoire
 */
public class SteamReviewsApi implements UserReviewsSummaryRepository {

    private final UserReviewsSummaryRepository userReviewsSummaryRepository;

    public SteamReviewsApi() {
        this(new UserReviewsSummaryAppLandingPageScraper(new URLConnectionHtmlRetriever()));
    }

    private SteamReviewsApi(final UserReviewsSummaryRepository userReviewsSummaryRepository) {
        super();

        if (userReviewsSummaryRepository == null)
            throw new NullPointerException("dao");

        if (userReviewsSummaryRepository instanceof SteamReviewsApi)
            throw new IllegalArgumentException("The delegate UserReviewsSummaryRepository cannot be a Critisteam class instance.");

        this.userReviewsSummaryRepository = userReviewsSummaryRepository;
    }

    @Override
    public UserReviewsSummary getSummary(int appId, final TimePeriod timePeriod) throws SteamReviewsException {
        return userReviewsSummaryRepository.getSummary(appId, timePeriod);
    }
}
