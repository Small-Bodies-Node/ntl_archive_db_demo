/*
 * Copyright (C) 2012 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl;

import java.util.List;
import java.util.Map;

import com.topcoder.util.log.Log;

/**
 * This interface defines the contract for dataset utilities. The dataset utility is a peace of functionality the does
 * some well-defined operations over the given datasets and the behavior can be controlled by the command line
 * arguments.
 *
 * Thread Safety: The implementations are not required to be thread-safe.
 */
interface DataSetUtility {
    /**
     * Sets the logger that can be used by the utility.
     *
     * @param logger
     *            the logger to set
     */
    void setLogger(Log logger);

    /**
     * Gets the utility name.
     *
     * @return the utility name
     */
    String getName();

    /**
     * Runs the utility.
     * 
     * @param dataSets
     *            the datasets to work with
     * @param args
     *            the required arguments
     * @param options
     *            the optional named parameters
     */
    void run(List<DataSetInfo> dataSets, List<String> args, Map<String, String> options);
}
