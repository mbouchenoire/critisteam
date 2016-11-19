package com.mbouchenoire.critisteam.utils;

import com.mbouchenoire.critisteam.SteamSupportedLanguage;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mbouchenoire
 */
public class HttpHeadersUtils {

    public static Map<String, String> acceptLanguageOnly(final SteamSupportedLanguage language) {
        if (language == null)
            throw new IllegalArgumentException("Language cannot be null.");

        final Map<String, String> headers = new HashMap<String, String>();
        final Map.Entry<String, String> acceptLanguageHeader = acceptLanguage(language);
        headers.put(acceptLanguageHeader.getKey(), acceptLanguageHeader.getValue());
        return headers;
    }

    public static Map.Entry<String, String> acceptLanguage(final SteamSupportedLanguage language) {
        if (language == null)
            throw new IllegalArgumentException("Language cannot be null.");

        return new AbstractMap.SimpleImmutableEntry<String, String>("Accept-Language", language.getIso639_1());
    }
}
