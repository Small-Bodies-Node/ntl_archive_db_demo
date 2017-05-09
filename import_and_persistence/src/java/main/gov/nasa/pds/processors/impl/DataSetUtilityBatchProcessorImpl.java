/*
 * Copyright (C) 2012 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl;

import gov.nasa.pds.processors.DataSetBatchProcessor;
import gov.nasa.pds.services.DataSetProcessingConfigurationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import com.topcoder.commons.utils.LoggingWrapperUtility;
import com.topcoder.commons.utils.ValidationUtility;
import com.topcoder.util.log.Level;
import com.topcoder.util.log.Log;

/**
 * <p>
 * The {@code DataSetUtilityBatchProcessorImpl} class implements {@code DataSetBatchProcessor} interface by providing
 * implementation of {@code processDataSets()} method.
 * </p>
 *
 * <strong>Thread Safety:</strong> This class is not thread safe.
 *
 * @author KennyAlive
 * @version 1.0
 */
public class DataSetUtilityBatchProcessorImpl implements DataSetBatchProcessor, InitializingBean {
    /**
     * Constant for the class name of this class. Used for logging.
     */
    private static final String CLASS_NAME = DataSetUtilityBatchProcessorImpl.class.getName();

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
     * Defines the dataset to start processing from. The datasets before this dataset are skipped. The startDataSet
     * dataset and all datasets after it are processed. The datasets are ordered in lexicographical order. It is
     * initialized with Spring setter dependency injection. Can be null if configuration property does not specify its
     * value. Has a setter.
     */
    private String startDataSet;

    /**
     * Defines the dataset that will be the last dataset in processing. After it the processor stops working. The
     * datasets are ordered in lexicographical order. It is initialized with Spring setter dependency injection. Can
     * be null if configuration property does not specify its value. Has a setter.
     */
    private String endDataSet;

    /**
     * The utility name. It is initialized with Spring setter dependency injection. Can not be null. Has a setter.
     */
    private String utilityName;

    /**
     * The list of registered utilities. Initialized in constructor. Can not be null.
     */
    private List<DataSetUtility> utilities;

    /**
     * Creates an instance of {@code DataSetUtilityBatchProcessorImpl}.
     */
    public DataSetUtilityBatchProcessorImpl() {
        utilities = new ArrayList<DataSetUtility>();
        utilities.add(new UtilityList());
        utilities.add(new UtilityTableStructure());
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
     * Sets the utility name.
     *
     * @param utilityName
     *            the utility name
     */
    public void setUtilityName(String utilityName) {
        this.utilityName = utilityName;
    }

    /**
     * Checks whether this class was initialized by Spring properly.
     *
     * Required parameters:
     * <ul>
     * <li>logger</li>
     * <li>dataSetSelector</li>
     * <li>utilityName</li>
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
        ValidationUtility.checkNotNull(utilityName, "utilityName",
                DataSetProcessingConfigurationException.class);

        if (startDataSet.trim().isEmpty()) {
            startDataSet = null;
        }
        if (endDataSet.trim().isEmpty()) {
            endDataSet = null;
        }

        for (DataSetUtility utility : utilities) {
            utility.setLogger(logger);
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

        // 1. Select the utility.
        DataSetUtility utility = null;
        for (DataSetUtility curUtility : utilities) {
            if (curUtility.getName().equalsIgnoreCase(utilityName)) {
                utility = curUtility;
                break;
            }
        }
        if (utility == null) {
            logger.log(Level.INFO, "Unknown utility: {0}", utilityName);
            StringBuilder sb = new StringBuilder("The following utilities are available: ");
            boolean first = true;
            for (DataSetUtility curUtility : utilities) {
                if (!first) {
                    sb.append(", ");
                }
                first = false;
                sb.append(curUtility.getName());
            }
            logger.log(Level.INFO, sb.toString());
            return;
        }

        // 2. Select datasets for processing.
        List<DataSetInfo> dataSets = Helper.selectDataSets(dataSetSelector, startDataSet, endDataSet, logger);

        if (dataSets == null) {
            return;
        }

        // 3. Parse arguments
        List<String> utilityArgs = new ArrayList<String>();
        Map<String, String> utilityOptions = new HashMap<String, String>();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.charAt(0) != '-') {
                utilityArgs.add(arg);
            } else {
                if (i == args.length - 1) {
                    logger.log(Level.INFO, "The option {0} does not specify its value", arg);
                    return;
                }
                utilityOptions.put(arg, args[i + 1]);
                i++;
            }
        }

        // 4. Run the utility
        utility.run(dataSets, utilityArgs, utilityOptions);
        LoggingWrapperUtility.logExit(logger, signature, null);
    }
}
