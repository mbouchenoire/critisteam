package com.mbouchenoire.critisteam;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mbouchenoire.critisteam.error.SteamReviewsException;

import java.util.AbstractMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author mbouchenoire
 */
abstract class SteamReviewsApiCacheManager<KeyType, ValueType> {

    private final LoadingCache<Map.Entry<Integer, KeyType>, ValueType> cache;

    public SteamReviewsApiCacheManager(final CacheConfig cacheConfig) {
        this.cache = init(cacheConfig);
    }

    private LoadingCache<Map.Entry<Integer, KeyType>, ValueType> init(final CacheConfig cacheConfig) {
        return CacheBuilder.newBuilder()
                .expireAfterWrite(cacheConfig.getDuration(), cacheConfig.getTimeUnit())
                .build(new CacheLoader<Map.Entry<Integer, KeyType>, ValueType>() {
                    @Override
                    public ValueType load(Map.Entry<Integer, KeyType> params) throws Exception {
                        return loadNewEntry(params.getKey(), params.getValue());
                    }
                });
    }

    protected abstract ValueType loadNewEntry(int appId, KeyType key) throws SteamReviewsException;

    public ValueType load(int appId, KeyType key) throws SteamReviewsException {
        try {
            final Map.Entry params = new AbstractMap.SimpleImmutableEntry(appId, key);

            synchronized (cache) {
                return (ValueType)cache.get(params);
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
}
