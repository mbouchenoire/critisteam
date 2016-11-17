package com.mbouchenoire.critisteam;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.concurrent.TimeUnit;

/**
 * @author mbouchenoire
 */
public class CacheConfigTest extends TestCase {

    final CacheConfig cacheConfig1 = new CacheConfig(1, TimeUnit.SECONDS);
    final CacheConfig cacheConfig2 = new CacheConfig(1, TimeUnit.SECONDS);
    final CacheConfig cacheConfig3 = new CacheConfig(2, TimeUnit.MINUTES);

    private final EqualsAndHashCodeTest equalsAndHashCodeTester
            = new EqualsAndHashCodeTest(cacheConfig1, cacheConfig2, cacheConfig3);

    public CacheConfigTest(String testName) {
        super(testName);
    }

    public static TestSuite suite() {
        return new TestSuite(CacheConfigTest.class);
    }

    public void testNegativeDuration() {
        try {
            new CacheConfig(-1, TimeUnit.SECONDS);
            assertTrue(false);
        } catch(IllegalArgumentException iae) {
            assertNotNull(iae);
        }
    }

    public void testNullTimeUnit() {
        try {
            new CacheConfig(1, null);
            assertTrue(false);
        } catch (IllegalArgumentException iae) {
            assertNotNull(iae);
        }
    }

    public void testEquals() {
        equalsAndHashCodeTester.testEquals();
    }

    public void testHashCode() {
        equalsAndHashCodeTester.testHashCode();
    }

    public void testToString() {
        assertEquals("1 SECONDS", cacheConfig1.toString());
    }
}
