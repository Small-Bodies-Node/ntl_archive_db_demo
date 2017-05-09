/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors;

import gov.nasa.pds.services.DataSetProcessingException;

/**
 * This interface defines the contract for importing.
 * 
 * Thread Safety: The implementations should be effectively thread-safe.
 */
public interface DataDictionaryImportProcessor {
    /**
     * 
     * Imports the data dictionary into the application.
     * 
     * @throws DataSetProcessingException
     *             - if there is an error while extracting the files
     */
    void importDataDictionary() throws DataSetProcessingException;
}
