package com.mbouchenoire.critisteam;

import com.mbouchenoire.critisteam.utils.HttpHeadersUtils;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Map;

/**
 * @author mbouchenoire
 */
public class HttpHeadersUtilsTest extends TestCase {

    private static final String ACCEPT_LANGUAGE_HEADER_KEY = "Accept-Language";
    private static final String ACCEPT_LANGUAGE_FRENCH_VALUE = "fr";

    public HttpHeadersUtilsTest(String testName) {
        super(testName);
    }

    public static TestSuite suite() {
        return new TestSuite(HttpHeadersUtilsTest.class);
    }

    public void testAcceptLanguages() {
        final Map.Entry<String, String> header = HttpHeadersUtils.acceptLanguage(SteamSupportedLanguage.FRENCH);
        assertEquals(ACCEPT_LANGUAGE_HEADER_KEY, header.getKey());
        assertEquals(ACCEPT_LANGUAGE_FRENCH_VALUE, header.getValue());
    }

    public void testAcceptLanguagesOnly() {
        final Map<String, String> headers = HttpHeadersUtils.acceptLanguageOnly(SteamSupportedLanguage.FRENCH);
        assertEquals(1, headers.size());
        assertEquals(ACCEPT_LANGUAGE_FRENCH_VALUE, headers.get(ACCEPT_LANGUAGE_HEADER_KEY));
    }
}
