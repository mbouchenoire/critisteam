package com.mbouchenoire.critisteam;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mbouchenoire.critisteam.error.SteamReviewsException;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author mbouchenoire
 */
public class CachedSteamReviewsApi extends SteamReviewsApi {

    private final LoadingCache<Map.Entry<Integer, TimePeriod>, UserReviewsSummary> summariesCache;
    private final LoadingCache<Map.Entry<Integer, SteamSupportedLanguage>, Collection<UserReview>> userReviewsCache;

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

        this.summariesCache = CacheBuilder.newBuilder()
                .expireAfterWrite(cacheConfig.getDuration(), cacheConfig.getTimeUnit())
                .build(new CacheLoader<Map.Entry<Integer, TimePeriod>, UserReviewsSummary>() {
                    @Override
                    public UserReviewsSummary load(Map.Entry<Integer, TimePeriod> params) throws Exception {
                        return superGetSummary(params.getKey(), params.getValue());
                    }
                });

        this.userReviewsCache = CacheBuilder.newBuilder()
                .expireAfterAccess(cacheConfig.getDuration(), cacheConfig.getTimeUnit())
                .build(new CacheLoader<Map.Entry<Integer, SteamSupportedLanguage>, Collection<UserReview>>() {
                    @Override
                    public Collection<UserReview> load(Map.Entry<Integer, SteamSupportedLanguage> params) throws Exception {
                        return superGetReviews(params.getKey(), params.getValue());
                    }
                });
    }

    private UserReviewsSummary superGetSummary(int appId, TimePeriod timePeriod) throws SteamReviewsException {
        return super.getSummary(appId, timePeriod);
    }

    @Override
    public UserReviewsSummary getSummary(int appId, final TimePeriod timePeriod) throws SteamReviewsException {
        try {
            final Map.Entry params = new AbstractMap.SimpleImmutableEntry(appId, timePeriod);

            synchronized (summariesCache) {
                return summariesCache.get(params);
            }
        } catch (ExecutionException ee) {
            final Throwable cause = ee.getCause();

            if (cause instanceof SteamReviewsException) {
                throw (SteamReviewsException)cause;
            } else {
                throw new SteamReviewsException("An error occurred while retrieving user reviews summary.", ee);
            }
        }
    }

    private Collection<UserReview> superGetReviews(int appId, SteamSupportedLanguage language) throws SteamReviewsException {
        return super.getReviews(appId, language);
    }

    @Override
    public Collection<UserReview> getReviews(int appId, final SteamSupportedLanguage language) throws SteamReviewsException {
        try {
            final Map.Entry params = new AbstractMap.SimpleImmutableEntry(appId, language);

            synchronized (userReviewsCache) {
                return userReviewsCache.get(params);
            }
        } catch (ExecutionException ee) {
            final Throwable cause = ee.getCause();

            if (cause instanceof SteamReviewsException) {
                throw (SteamReviewsException)cause;
            } else {
                throw new SteamReviewsException("An error occurred while retrieving user reviews.", ee);
            }
        }
    }
}
