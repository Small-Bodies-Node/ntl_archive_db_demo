/*
 * Copyright (C) 2012 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl;

import gov.nasa.pds.services.DataSetProcessingException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.topcoder.commons.utils.LoggingWrapperUtility;
import com.topcoder.commons.utils.ParameterCheckUtility;
import com.topcoder.util.log.Log;

/**
 * <p>
 * The {@code DataSetCache} class provides a way to get information from a dataset cache created by the dataset cache
 * processor.
 * </p>
 *
 * <p>
 * <strong>Thread Safety:</strong> This class is not thread safe.
 * </p>
 *
 * @author KennyAlive
 * @version 1.0
 */
class DataSetCache {
    /**
     * Constant for the class name of this class. Used for logging.
     */
    private static final String CLASS_NAME = DataSetCache.class.getName();
    /**
     * The cache.info filename.
     */
    private static final String CACHE_INFO_FILENAME = "cache.info";
    /**
     * The tables filename.
     */
    private static final String TABLES_CACHE_FILENAME = "tables";
    /**
     * Constant for files cache file name.
     */
    private static final String FILES_CACHE_FILENAME = "files";
    /**
     * The labels cache filename
     */
    private static final String LABELS_CACHE_FILENAME = "labels.zip";

    /**
     * The {@code Log} instance used for logging. Initialized in constructor. Can not be null.
     */
    private final Log logger;

    /**
     * The list of information about the dataset's tables. Initialized in constructor. Can not be null, can be empty
     * (if dataset has no tables). Has a getter.
     */
    private List<TableInfo> tablesInfo;

    /**
     * The list of all dataset files. Initialized in constructor. Can not be null. Has a getter.
     */
    private List<String> dataSetFiles;

    /**
     * Zip file instance that represents a labels cache. Initialized in constructor. Can be null.
     */
    private ZipFile labelsCacheZipFile;

    /**
     * <p>
     * The {@code TableInfo} class provides information about a single dataset table.
     * </p>
     *
     * <p>
     * <strong>Thread Safety:</strong> This class is thread safe.
     * </p>
     *
     * @author KennyAlive
     * @version 1.0
     */
    public static class TableInfo {
        /**
         * The table's columns names. Initialized in constructor. Can be any value, has a getter.
         */
        private List<String> columnNames;
        /**
         * The size of each table column Initialized in constructor. Can be any value, has a getter.
         */
        private List<Integer> columnSizes;

        /**
         * Creates new {@code TableInfo} instance.
         * 
         * @param columnNames
         *            the column names
         * @param columnSizes
         *            the column sizes in varchar symbols
         */
        public TableInfo(List<String> columnNames, List<Integer> columnSizes) {
            this.columnNames = columnNames;
            this.columnSizes = columnSizes;
        }

        /**
         * Gets the columns names.
         * 
         * @return the column names
         */
        public List<String> getColumnNames() {
            return columnNames;
        }

        /**
         * Gets the columns sizes.
         *
         * @return the columns sizes
         */
        public List<Integer> getColumnSizes() {
            return columnSizes;
        }
    }

    /**
     * Creates new {@code DataSetCache} instance.
     *
     * @param dataSetCache
     *            the dataset cache directory
     * @param logger
     *            the Log instance used for logging
     *
     * @throws IllegalArgumentException
     *             if dataSetCache parameter is null
     * @throws DataSetProcessingException
     *             if dataset cache on the disk is broken or some IO error occurs
     */
    public DataSetCache(File dataSetCache, Log logger) throws DataSetProcessingException {
        final String signature = "DataSetCache(File dataSetCache, Log logger)";

        ParameterCheckUtility.checkNotNull(dataSetCache, "dataSetCache");
        ParameterCheckUtility.checkNotNull(logger, "logger");

        this.logger = logger;

        // validate dataset cache
        if (!dataSetCache.isDirectory()) {
            throw LoggingWrapperUtility.logException(this.logger, signature, new DataSetProcessingException(
                    "dataset cache does not exist: " + dataSetCache.getAbsolutePath()));
        }
        File cacheInfoFile = new File(dataSetCache, CACHE_INFO_FILENAME);
        if (!cacheInfoFile.isFile()) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "dataset cache is not valid (cache.info is missing): " + dataSetCache.getAbsolutePath()));
        }

        // read information about dataset's tables
        tablesInfo = new ArrayList<TableInfo>();
        File tablesFile = new File(dataSetCache, TABLES_CACHE_FILENAME);
        if (tablesFile.isFile()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(tablesFile));
                try {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        Scanner scanner = new Scanner(line);
                        List<String> columnNames = new ArrayList<String>();
                        List<Integer> columnSizes = new ArrayList<Integer>();
                        while (scanner.hasNext()) {
                            columnNames.add(scanner.next());
                            columnSizes.add(scanner.nextInt());
                        }
                        scanner.close();
                        tablesInfo.add(new TableInfo(columnNames, columnSizes));
                    }
                } finally {
                    reader.close();
                }
            } catch (IOException e) {
                throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                        "Failed to read/close tables file", e));
            } catch (NoSuchElementException e) {
                throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                        "The 'tables' file is malformed", e));
            }
        }

        // read files cache
        dataSetFiles = new ArrayList<String>();
        File filesFile = new File(dataSetCache, FILES_CACHE_FILENAME);
        if (filesFile.isFile()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filesFile));
                try {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (!line.trim().isEmpty()) {
                            dataSetFiles.add(line);
                        }
                    }
                } finally {
                    reader.close();
                }
            } catch (IOException e) {
                throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                        "Failed to open/read/close files file", e));
            }
        }

        // initialize labels cache
        File labelsCacheFile = new File(dataSetCache, LABELS_CACHE_FILENAME);
        if (labelsCacheFile.isFile()) {
            try {
                labelsCacheZipFile = new ZipFile(labelsCacheFile);
            } catch (IOException e) {
                throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                        "Failed to initialize labels cache", e));
            }
        }
    }

    /**
     * Frees the resources.
     */
    public void close() {
        if (labelsCacheZipFile != null) {
            try {
                labelsCacheZipFile.close();
                labelsCacheZipFile = null;
            } catch (IOException e) {
                // ignore
            }
        }
    }

    /**
     * Gets the list of information about the dataset's tables.
     *
     * @return the list of information about the dataset's tables
     */
    public List<TableInfo> getTablesInfo() {
        return tablesInfo;
    }

    /**
     * Gets list of all dataset files.
     *
     * @return the list of all dataset files
     */
    public List<String> getDataSetFiles() {
        return dataSetFiles;
    }

    /**
     * Gets the input stream for the given label file or null if label file is not cached.
     *
     * @param labelFilename
     *            the label filename as defined in the dataset index
     *
     * @return the input stream for the label content or null if label file is not cached
     *
     * @throws DataSetProcessingException
     *             if any IO error occurs
     */
    public InputStream getLabelInputStream(String labelFilename) throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".getLabelInputStream(String labelFilename)";

        if (labelsCacheZipFile == null) {
            return null;
        }

        ZipEntry ze = new ZipEntry(labelFilename);
        try {
            return labelsCacheZipFile.getInputStream(ze);
        } catch (IOException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Failed to get input stream from labels cache for label:" + labelFilename, e));
        }
    }

    /**
     * Gets size of the given label file.
     * 
     * @param labelFilename
     *            the label filename
     * @return the size of the given label file
     */
    public long getLabelSize(String labelFilename) {
        if (labelsCacheZipFile == null) {
            return 0;
        }

        ZipEntry ze = labelsCacheZipFile.getEntry(labelFilename);
        return (ze == null) ? 0 : ze.getSize();
    }
}
