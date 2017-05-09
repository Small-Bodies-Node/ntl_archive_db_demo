/*
 * Copyright (C) 2011-2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services.impl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * <p>
 * Aggregates all tests.
 * </p>
 * 
 * <p>
 * Version 1.1 changes [PDS - Import and Persistence Update - Assembly Contest]:
 * <ol>
 * <li>Restored JDBCConversionPersistenceTest.</li>
 * </ol>
 * </p>
 * 
 * @author KennyAlive
 * @version 1.1
 */
public class AllTestCases extends TestCase {
    /**
     * Aggregates all tests in this class.
     *
     * @return Test suite aggregating all tests.
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite();
        suite.addTest(JDBCConversionPersistenceTest.suite());
        suite.addTest(JDBCDataDictionaryImportPersistenceTest.suite());
        suite.addTest(JDBCMetadataValidationManagerTest.suite());
        return suite;
    }
}
