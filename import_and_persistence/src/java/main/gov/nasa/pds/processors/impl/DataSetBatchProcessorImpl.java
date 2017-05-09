/*
 * Copyright (C) 2012 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl;

import gov.nasa.pds.processors.DataSetBatchProcessor;
import gov.nasa.pds.processors.DataSetProcessor;
import gov.nasa.pds.services.DataSetProcessingConfigurationException;
import gov.nasa.pds.services.DataSetProcessingException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;

import com.topcoder.commons.utils.LoggingWrapperUtility;
import com.topcoder.commons.utils.ValidationUtility;
import com.topcoder.util.log.Level;
import com.topcoder.util.log.Log;

/**
 * <p>
 * The {@code DataSetBatchProcessorImpl} class implements {@code DataSetBatchProcessor} interface by providing
 * implementation of {@code processDataSets} method.
 * </p>
 *
 * <strong>Thread Safety:</strong> This class is not thread safe.
 *
 * @author KennyAlive
 * @version 1.0
 */
public class DataSetBatchProcessorImpl implements DataSetBatchProcessor, InitializingBean {
    /**
     * Constant for the class name of this class. Used for logging.
     */
    private static final String CLASS_NAME = DataSetBatchProcessorImpl.class.getName();

    /**
     * The {@code Log} instance used for logging. It is initialized with Spring setter dependency injection. Cannot be
     * null after initialization. Has a setter.
     */
    private Log logger;

    /**
     * The {@code DataSetSelector} instance used to select datasets for processing. It is initialized with Spring
     * setter dependency injection. Cannot be null after initialization. Has a setter.
     */
    private DataSetSelector dataSetSelector;

    /**
     * The {@code DataSetProcessor} instance used to process selected datasets. It is initialized with Spring setter
     * dependency injection. Cannot be null after initialization. Has a setter.
     */
    private DataSetProcessor dataSetProcessor;

    /**
     * Defines the dataset to start processing from. The datasets before this dataset are skipped. The startDataSet
     * and all datasets after it are processed. The datasets are ordered in lexicographical order. It is initialized
     * with Spring setter dependency injection. Can be null if configuration property does not specify its value. Has
     * a setter.
     */
    private String startDataSet;

    /**
     * Defines the dataset that will be the last dataset in processing. After it the processor stops working. The
     * datasets are ordered in lexicographical order. It is initialized with Spring setter dependency injection. Can
     * be null if configuration property does not specify its value. Has a setter.
     */
    private String endDataSet;

    /**
     * The list of datasets that were not processed due to some error. Initialized in constructor. Can not be null.
     */
    private List<String> faultyDataSets;

    /**
     * Creates an instance of {@code DataSetBatchProcessorImpl}.
     */
    public DataSetBatchProcessorImpl() {
        faultyDataSets = new ArrayList<String>();
    }

    /**
     * Sets the logger.
     *
     * @param logger
     *            the logger to set
     */
    public void setLogger(Log logger) {
        this.logger = logger;
    }

    /**
     * Sets the dataset selector.
     *
     * @param dataSetSelector
     *            the dataset selector to set
     */
    public void setDataSetSelector(DataSetSelector dataSetSelector) {
        this.dataSetSelector = dataSetSelector;
    }

    /**
     * Sets the dataset processor.
     *
     * @param dataSetProcessor
     *            the dataset processor to set
     */
    public void setDataSetProcessor(DataSetProcessor dataSetProcessor) {
        this.dataSetProcessor = dataSetProcessor;
    }

    /**
     * Sets the start dataset.
     *
     * @param startDataSet
     *            the start dataset to set
     */
    public void setStartDataSet(String startDataSet) {
        this.startDataSet = startDataSet;
    }

    /**
     * Sets the end dataset.
     *
     * @param endDataSet
     *            the end dataset to set
     */
    public void setEndDataSet(String endDataSet) {
        this.endDataSet = endDataSet;
    }

    /**
     * Checks whether this class was initialized by Spring properly.
     *
     * Required parameters:
     * <ul>
     * <li>logger</li>
     * <li>dataSetSelector</li>
     * <li>dataSetProcessor</li>
     * </ul>
     *
     * Optional parameters:
     * <ul>
     * <li>startDataSet</li>
     * <li>endDataSet</li>
     * </ul>
     *
     * @throws DataSetProcessingConfigurationException
     *             if the class was not initialized properly
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        ValidationUtility.checkNotNull(logger, "logger",
                DataSetProcessingConfigurationException.class);
        ValidationUtility.checkNotNull(dataSetSelector, "dataSetSelector",
                DataSetProcessingConfigurationException.class);
        ValidationUtility.checkNotNull(dataSetProcessor, "dataSetProcessor",
                DataSetProcessingConfigurationException.class);

        if (startDataSet.trim().isEmpty()) {
            startDataSet = null;
        }
        if (endDataSet.trim().isEmpty()) {
            endDataSet = null;
        }
    }

    /**
     * Processes the datasets.
     *
     * @param args
     *            optional arguments (not-null, can be empty)
     */
    @Override
    public void processDataSets(String[] args) {
        String signature = CLASS_NAME + ".processDataSets()";
        LoggingWrapperUtility.logEntrance(logger, signature, null, null);

        // 1. Select datasets for processing.
        List<DataSetInfo> dataSets = Helper.selectDataSets(dataSetSelector, startDataSet, endDataSet, logger);

        if (dataSets == null) {
            return;
        }

        // 2. Process datasets.
        Date start = new Date();
        int processed = 0;

        for (DataSetInfo dataSetInfo : dataSets) {
            Date start2 = new Date();
            String directoryName = dataSetInfo.getDirectoryName();

            logger.log(Level.INFO, "Processing dataset: {0} ...", directoryName);

            try {
                dataSetProcessor.processDataSet(dataSetInfo.getPath());
            } catch (DataSetProcessingException e) {
                faultyDataSets.add(directoryName);
                logger.log(Level.ERROR, "Failed to process dataset {0}", directoryName);
            }

            processed++;

            logger.log(Level.INFO, "Done in {0}, {1} dataset(s) left",
                    Helper.getTimingString(start2, new Date()), dataSets.size() - processed);
        }

        // 3. Log summary
        if (!faultyDataSets.isEmpty()) {
            logger.log(Level.INFO, "{0} datasets were not processed due to critical error. Please see log messages.",
                    faultyDataSets.size());
            logger.log(Level.INFO, "The faulty dataset are:");
            for (String directoryName : faultyDataSets) {
                logger.log(Level.INFO, ">>> {0}", directoryName);
            }
        }
        logger.log(Level.INFO, "Finished datasets processing in {0}", Helper.getTimingString(start, new Date()));
        LoggingWrapperUtility.logExit(logger, signature, null);
    }
}
