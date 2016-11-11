package com.mbouchenoire.critisteam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Retrieve the HTML in the HTTP response body provided
 * by a GET request on the given URL, using {@link URLConnection}.
 *
 * @author mbouchenoire
 * @see URLConnection
 */
public final class URLConnectionHtmlRetriever implements HtmlRetriever {

    @Override
    public String retrieve(String url) throws IOException {
        try {
            final URL u = new URL(url);
            final URLConnection yc = u.openConnection();
            final BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            final StringBuffer sb = new StringBuffer();
            String currentLine;

            while ((currentLine = in.readLine()) != null) {
                sb.append(currentLine);
            }

            in.close();

            return sb.toString();
        } catch (MalformedURLException mue) {
            throw new IllegalArgumentException(mue);
        }
    }
}
