package com.mbouchenoire.critisteam;

import com.mbouchenoire.critisteam.error.SteamReviewsException;

import java.util.Collection;

/**
 * @author mbouchenoire
 */
public class SteamReviewsApi implements UserReviewsSummaryRepository, UserReviewsRepository {

    private final UserReviewsSummaryRepository userReviewsSummaryRepository;
    private final UserReviewsRepository userReviewsRepository;

    public SteamReviewsApi() {
        super();
        final HtmlRetriever htmlRetriever = new URLConnectionHtmlRetriever();
        this.userReviewsSummaryRepository = new UserReviewsSummaryAppLandingPageScraper(htmlRetriever);
        this.userReviewsRepository = new UserReviewsAppLandingPageScraper(htmlRetriever);
    }

    @Override
    public UserReviewsSummary getSummary(int appId, final TimePeriod timePeriod) throws SteamReviewsException {
        return userReviewsSummaryRepository.getSummary(appId, timePeriod);
    }

    @Override
    public Collection<UserReview> getReviews(int appId, final SteamSupportedLanguage language) throws SteamReviewsException {
        return userReviewsRepository.getReviews(appId, language);
    }
}
