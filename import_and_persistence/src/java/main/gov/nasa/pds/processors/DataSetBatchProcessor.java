/*
 * Copyright (C) 2012 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors;

/**
 * This interface defines the contract for processing multiple datasets.
 *
 * Thread Safety: The implementations are not required to be thread-safe.
 */
public interface DataSetBatchProcessor {
    /**
     * Processes the configured list of datasets. The implementations should not interrupt processing if fail to
     * process a dataset. Instead an error message should be logged and the processor should proceed with the rest of
     * the datasets.
     *
     * @param args
     *            optional arguments (not-null, can be empty)
     */
    void processDataSets(String[] args);
}
