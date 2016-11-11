package com.mbouchenoire.critisteam;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * @author mbouchenoire
 */
public class URLConnectionHtmlRetrieverTest extends TestCase {

    public URLConnectionHtmlRetrieverTest(String testName) {
        super(testName);
    }

    public static TestSuite suite() {
        return new TestSuite(URLConnectionHtmlRetrieverTest.class);
    }

    public void testRetrieve() throws IOException {
        final String html = new URLConnectionHtmlRetriever().retrieve("http://www.w3schools.com/");
        final Document page = Jsoup.parse(html);
        final Element element = page.select("#fblikeframe").first();
        assertNotNull(element);
    }
}
