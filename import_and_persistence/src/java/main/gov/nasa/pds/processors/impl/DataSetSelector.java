/*
 * Copyright (C) 2012-2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl;

import gov.nasa.pds.processors.impl.profile.ProfileProvider;
import gov.nasa.pds.services.DataSetProcessingConfigurationException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.topcoder.commons.utils.LoggingWrapperUtility;
import com.topcoder.commons.utils.ValidationUtility;
import com.topcoder.util.log.Level;
import com.topcoder.util.log.Log;

/**
 * <p>
 * The {@code DataSetSelector} class implements algorithm for selecting appropriate datasets for processing from the
 * provided list of dataset archives. It takes into account dataset status from dataset.html file and handles dataset
 * versions.
 * </p>
 * 
 * <strong>Thread Safety:</strong> This class is mutable since it provides public setters for its properties. But it
 * doesn't change its state and is thread safe when the following conditions are met: this class is initialized by
 * Spring right after construction and its parameters are never changed after that, all entities passed to this class
 * are used by the caller in thread safe manner (accessed from a single thread only).
 * 
 * <p>
 * Version 1.1 changes [PDS - Import and Persistence Update - Assembly Contest]:
 * <ol>
 * <li>Use ProfileProvider to check dataset archived status only for SBN datasets.</li>
 * </ol>
 * </p>
 * 
 * @author KennyAlive
 * @version 1.1
 */
public class DataSetSelector implements InitializingBean {
    /**
     * Constant for the class name of this class. Used for logging.
     */
    private static final String CLASS_NAME = DataSetSelector.class.getName();

    /**
     * The {@code Log} instance used for logging. It is initialized with Spring setter dependency injection. Cannot be
     * null after initialization. Has a setter.
     */
    private Log logger;

    /**
     * The list of directories that contain datasets for processing. It is initialized with Spring setter dependency
     * injection. Cannot be null after initialization. Has a setter.
     */
    private List<String> dataSetArchives;

    /**
     * The profile provider used by this class.
     */
    @Autowired
    private ProfileProvider profileProvider;

    /**
     * Creates an instance of {@code DataSetSelector}
     */
    public DataSetSelector() {
        // Empty
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
     * Sets the dataset archives (list of directories that contain datasets for processing).
     *
     * @param dataSetArchives
     *            the dataset archives to set
     */
    public void setDataSetArchives(List<String> dataSetArchives) {
        this.dataSetArchives = dataSetArchives;
    }

    /**
     * Checks whether this class was initialized by Spring properly.
     * 
     * Required parameters:
     * <ul>
     * <li>logger</li>
     * <li>dataSetArchives</li>
     * <li>profileProvider</li>
     * </ul>
     * 
     * @throws DataSetProcessingConfigurationException
     *             if the class was not initialized properly
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        ValidationUtility.checkNotNull(logger, "logger",
                DataSetProcessingConfigurationException.class);
        ValidationUtility.checkNotNull(dataSetArchives, "dataSetArchives",
                DataSetProcessingConfigurationException.class);
        ValidationUtility.checkNotNull(profileProvider, "profileProvider",
                DataSetProcessingConfigurationException.class);
    }

    /**
     * Implements an algorithm that selects appropriate datasets for processing from the specified dataset archives.
     *
     * @return the list of DataSetInfo object for the selected datasets
     */
    public List<DataSetInfo> selectDataSetsForProcessing() {
        String signature = CLASS_NAME + ".selectDataSetsForProcessing()";
        LoggingWrapperUtility.logEntrance(logger, signature, null, null);

        logger.log(Level.INFO, "Scanning {0} dataset archive(s)", dataSetArchives.size());

        List<DataSetInfo> result = new ArrayList<DataSetInfo>();
        Map<String, Integer> baseDirectoryNameToIndex = new HashMap<String, Integer>();

        // 1. Select datasets for processing
        for (String archivePath : dataSetArchives) {
            File archiveDirectory = new File(archivePath);
            File[] directoryFiles = archiveDirectory.listFiles();

            for (File file : directoryFiles) {
                if (file.isDirectory()) {
                    File dataSetDirectory = new File(archivePath, file.getName());

                    if (profileProvider.isSBNProfile()) {
                        // Check dataset archived status for SBN profile.
                        if (!isArchivedDataSet(dataSetDirectory, signature)) {
                            continue;
                        }
                    }
                    DataSetInfo dataSetInfo = new DataSetInfo(archivePath, file.getName());
                    int major = dataSetInfo.getMajorVersion();
                    int minor = dataSetInfo.getMinorVersion();

                    Integer index = baseDirectoryNameToIndex.get(dataSetInfo.getBaseDirectoryName());
                    if (index != null) {
                        DataSetInfo dataSetInfo2 = result.get(index);
                        int major2 = dataSetInfo2.getMajorVersion();
                        int minor2 = dataSetInfo2.getMinorVersion();
                        if (major > major2 || (major == major2 && minor > minor2)) {
                            // replace an older dataset with a newer one
                            result.set(index, dataSetInfo);
                        }
                    } else {
                        result.add(dataSetInfo);
                        baseDirectoryNameToIndex.put(dataSetInfo.getBaseDirectoryName(),
                                result.size() - 1);
                    }
                }
            }
        }

        // 2. Sort selected datasets in lexicographical order.
        Collections.sort(result);

        return result;
    }

    /**
     * Checks if the specified dataset has ARCHIVED or LOCALLY ARCHIVED status.
     *
     * @param dataSetDirectory
     *            the dataset directory
     * @param signature
     *            the method signature for logging
     *
     * @return true, if the file has ARCHIVED or LOCALLY ARCHIVED status, otherwise false
     */
    private boolean isArchivedDataSet(File dataSetDirectory, String signature) {
        final String DATASET_HTLM_FILENAME = "dataset.html";
        final String DATASET_STATUS_PATTERN = "status:";
        final String DATASET_ARCHIVED_STATUS = "archived";
        final String DATASET_ARCHIVED_STATUS2 = "locally archived";

        // 1. Check if dataset.html exists.
        File dataSetHtml = new File(dataSetDirectory, DATASET_HTLM_FILENAME);
        if (!dataSetHtml.exists()) {
            logger.log(Level.WARN, "{0} file is not found for {1} dataset",
                    DATASET_HTLM_FILENAME, dataSetDirectory.getName());
            return false;
        }

        // 2. Read status value.
        String statusLine = null;
        String statusLine2 = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataSetHtml));
            try {
                String line = reader.readLine();
                while (line != null) {
                    line = line.toLowerCase();
                    if (line.contains(DATASET_STATUS_PATTERN)) {
                        statusLine = line;

                        // In some cases status value can be on a separate line
                        line = reader.readLine();
                        while (line != null) {
                            line = line.trim().toLowerCase();
                            if (!line.isEmpty()) {
                                statusLine2 = line;
                                break;
                            }
                            line = reader.readLine();
                        }
                        break;
                    }
                    line = reader.readLine();
                }
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            LoggingWrapperUtility.logException(logger, signature, e);
            logger.log(Level.ERROR, "Failed to read dataset status from {0}", dataSetHtml.getAbsolutePath());
            return false;
        }

        // 3. Check status value against predefined value.
        if (statusLine != null) {
            if (statusLine.contains(DATASET_ARCHIVED_STATUS) ||
                    statusLine.contains(DATASET_ARCHIVED_STATUS2)) {
                return true;
            }
        }
        if (statusLine2 != null) {
            if (statusLine2.contains(DATASET_ARCHIVED_STATUS) ||
                    statusLine2.contains(DATASET_ARCHIVED_STATUS2)) {
                return true;
            }
        }
        return false;
    }
}
