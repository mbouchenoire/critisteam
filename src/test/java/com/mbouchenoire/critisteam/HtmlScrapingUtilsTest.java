package com.mbouchenoire.critisteam;

import com.mbouchenoire.critisteam.utils.HtmlScrapingUtils;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author mbouchenoire
 */
public class HtmlScrapingUtilsTest extends TestCase {

    public HtmlScrapingUtilsTest(String testName) {
        super(testName);
    }

    public static TestSuite suite() {
        return new TestSuite(HtmlScrapingUtilsTest.class);
    }

    public void testFindNumbersNullArgument() {
        try {
            HtmlScrapingUtils.findNumbers(null);
            assertTrue(false);
        } catch(IllegalArgumentException iae) {
            assertNotNull(iae);
        }
    }

    public void testFindPlainNumbers() {
        int[] numbers = HtmlScrapingUtils.findNumbers("38 personne(s) sur 41 (93%) ont trouvé cette évaluation utile");
        assertEquals(numbers[0], 38);
        assertEquals(numbers[1], 41);
        assertEquals(numbers[2], 93);
    }

    public void testFindNumbersSeparatedByCommas() {
        int[] numbers = HtmlScrapingUtils.findNumbers("6,632 of 6,949 people (95%) found this review helpful 304 1,523 people found this review funny");
        assertEquals(numbers[0], 6632);
        assertEquals(numbers[1], 6949);
        assertEquals(numbers[2], 95);
        assertEquals(numbers[3], 304);
        assertEquals(numbers[4], 1523);
    }

    public void testFindDoublesNullArgument() {
        try {
            HtmlScrapingUtils.findDoubles(null);
            assertTrue(false);
        } catch(IllegalArgumentException iae) {
            assertNotNull(iae);
        }
    }

    public void testFindDoubles() {
        double[] doubles = HtmlScrapingUtils.findDoubles("514.8 62.1 874");
        assertEquals(doubles[0], 514.8d);
        assertEquals(doubles[1], 62.1d);
        assertEquals(doubles[2], 874d);
    }
}
