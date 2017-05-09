/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors;

import gov.nasa.pds.services.DataSetProcessingException;

/**
 * This interface defines the contract for processing a data set. <i>Dataset processing</i> means any processing
 * activity over the given dataset, like storing the dataset to DB, creating dataset cache or retrieving interesting
 * data.
 *
 * Thread Safety: The implementations are not required to be thread-safe.
 */
public interface DataSetProcessor {
    /**
     * Processes the dataset located on the given path.
     *
     * @throws DataSetProcessingException
     *             if there is any error while processing the dataset
     */
    void processDataSet(String dataSetPath) throws DataSetProcessingException;
}
