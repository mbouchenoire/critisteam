package com.mbouchenoire.critisteam;

import java.io.IOException;

/**
 * An interface is defined for HTML retrieving because multiple
 * libraries and methods can be used for this bottlenecking process.
 *
 * @author mbouchenoire
 */
public interface HtmlRetriever {

    /**
     * Retrieve the HTML accessible at a given URL. The HTML may
     * simply be within the HTTP response body, or wrapped inside
     * a Json attribute (for example).
     * @param url the URL where the HTML needs to be retrieved.
     * @return the "main" HTML available at the given URL (within the HTTP response body, or wrapped somewhere).
     * @throws IOException when an error occurred while retrieving the HTML.
     */
    String retrieve(final String url) throws IOException;
}
