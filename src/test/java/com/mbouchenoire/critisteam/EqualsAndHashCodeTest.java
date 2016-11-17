package com.mbouchenoire.critisteam;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * @author mbouchenoire
 */
public class EqualsAndHashCodeTest {

    private final Object object1;
    private final Object object2;
    private final Object object3;

    /**
     *
     * @param object1 must be equals to object 2 (not the same instance)
     * @param object2 must be equals to object 1 (not the same instance)
     * @param object3 must not be equals to object 1 or object 2
     */
    public EqualsAndHashCodeTest(Object object1, Object object2, Object object3) {
        this.object1 = object1;
        this.object2 = object2;
        this.object3 = object3;
    }

    public void testEquals() {
        assertFalse(object1.equals(null));
        assertFalse(object1.equals(new Object()));
        assertTrue(object1.equals(object1));
        assertTrue(object1.equals(object2));
        assertFalse(object1.equals(object3));
    }

    public void testHashCode() {
        assertFalse(object1.hashCode() == new Object().hashCode()); // this test seems sketchy
        assertTrue(object1.hashCode() == object2.hashCode());
        assertFalse(object1.hashCode() == object3.hashCode());
    }
}
