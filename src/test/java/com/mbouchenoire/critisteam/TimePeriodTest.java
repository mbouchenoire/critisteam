package com.mbouchenoire.critisteam;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author mbouchenoire
 */
public class TimePeriodTest extends TestCase {

    public TimePeriodTest(String testName) {
        super(testName);
    }

    public static TestSuite suite() {
        return new TestSuite(TimePeriodTest.class);
    }

    public void testFromText() {
        assertEquals(TimePeriod.RECENT, TimePeriod.fromText("Recent"));
        assertEquals(TimePeriod.RECENT, TimePeriod.fromText("recent"));
        assertEquals(TimePeriod.OVERALL, TimePeriod.fromText("Overall"));
        assertEquals(TimePeriod.OVERALL, TimePeriod.fromText("overall"));
    }

    public void testToString() {
        assertEquals(TimePeriod.OVERALL.getText(), TimePeriod.OVERALL.toString());
    }
}
