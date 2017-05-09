/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTestCases extends TestCase {
    /**
     * Aggregates all tests in this class.
     * 
     * @return Test suite aggregating all tests.
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite();
        suite.addTest(BaseClassTest.suite());
        suite.addTest(EmptyJSONTest.suite());
        suite.addTest(EmptyMetadataObjectTest.suite());
        suite.addTest(JSONTest.suite());
        suite.addTest(MetadataObjectTest.suite());
        return suite;
    }

}
