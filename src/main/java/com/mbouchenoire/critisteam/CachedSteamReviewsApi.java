package com.mbouchenoire.critisteam;

import com.mbouchenoire.critisteam.error.SteamReviewsException;

import java.util.Collection;

/**
 * @author mbouchenoire
 */
public class CachedSteamReviewsApi extends SteamReviewsApi {

    private final SteamReviewsApiCacheManager<TimePeriod, UserReviewsSummary> summariesCache;
    private final SteamReviewsApiCacheManager<SteamSupportedLanguage, Collection<UserReview>> userReviewsCache;

    /**
     * Creates a {@link CachedSteamReviewsApi} instance (with summariesCache management),
     * configured by the {@link CacheConfig} argument.
     *
     * @param cacheConfig
     */
    public CachedSteamReviewsApi(final CacheConfig cacheConfig) {
        super();

        if (cacheConfig == null)
            throw new IllegalArgumentException("Cache configuration cannot be null.");

        this.summariesCache = new SteamReviewsApiCacheManager<TimePeriod, UserReviewsSummary>(cacheConfig) {
            @Override
            public UserReviewsSummary loadNewEntry(int appId, TimePeriod timePeriod) throws SteamReviewsException {
                return superGetSummary(appId, timePeriod);
            }
        };

        this.userReviewsCache = new SteamReviewsApiCacheManager<SteamSupportedLanguage, Collection<UserReview>>(cacheConfig) {
            @Override
            protected Collection<UserReview> loadNewEntry(int appId, SteamSupportedLanguage steamSupportedLanguage) throws SteamReviewsException {
                return superGetReviews(appId, steamSupportedLanguage);
            }
        };
    }

    private UserReviewsSummary superGetSummary(int appId, TimePeriod timePeriod) throws SteamReviewsException {
        return super.getSummary(appId, timePeriod);
    }

    private Collection<UserReview> superGetReviews(int appId, SteamSupportedLanguage language) throws SteamReviewsException {
        return super.getReviews(appId, language);
    }

    @Override
    public UserReviewsSummary getSummary(int appId, final TimePeriod timePeriod) throws SteamReviewsException {
        return this.summariesCache.load(appId, timePeriod);
    }

    @Override
    public Collection<UserReview> getReviews(int appId, final SteamSupportedLanguage language) throws SteamReviewsException {
        return this.userReviewsCache.load(appId, language);
    }
}
