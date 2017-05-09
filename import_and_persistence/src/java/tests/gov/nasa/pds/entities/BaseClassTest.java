/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Test cases for Base classes.
 */
public class BaseClassTest extends TestCase {

    /**
     * Aggregates all tests in this class.
     * 
     * @return Test suite aggregating all tests.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(BaseClassTest.class);
    }

    /**
     * Test {@link NamedEntity#equals(Object)} method.
     */
    @Test
    public void testNameEntityEquals() {
        Column column1 = new Column();
        column1.setName("1");

        Column column2 = new Column();
        column2.setName("1");

        assertTrue("columns should be the same.", column1.equals(column2));

    }

    /**
     * Test {@link NamedEntity#equals(Object)} method.
     */
    @Test
    public void testNameEntityNotEquals() {
        Column column1 = new Column();
        column1.setName("1");

        Column column2 = new Column();
        column2.setName("2");

        assertFalse("columns should not be the same.", column1.equals(column2));
    }

    /**
     * Test {@link IdentifiableEntity#equals(Object)} method.
     */
    @Test
    public void testIdEntityEquals() {
        ObjectAlias objectAlias1 = new ObjectAlias();
        objectAlias1.setId(1);

        ObjectAlias objectAlias2 = new ObjectAlias();
        objectAlias2.setId(1);

        assertTrue("ObjectAlias should be the same.", objectAlias1.equals(objectAlias2));
    }

    /**
     * Test {@link IdentifiableEntity#equals(Object)} method.
     */
    @Test
    public void testIdEntityNotEquals() {
        ObjectAlias objectAlias1 = new ObjectAlias();
        objectAlias1.setId(1);

        ObjectAlias objectAlias2 = new ObjectAlias();
        objectAlias2.setId(2);

        assertFalse("ObjectAlias should not be the same.", objectAlias1.equals(objectAlias2));
    }
}
