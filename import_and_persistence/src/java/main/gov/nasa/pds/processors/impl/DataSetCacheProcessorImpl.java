/*
 * Copyright (C) 2012-2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl;

import gov.nasa.pds.entities.Column;
import gov.nasa.pds.entities.MetadataFile;
import gov.nasa.pds.entities.MetadataObject;
import gov.nasa.pds.entities.Product;
import gov.nasa.pds.entities.Property;
import gov.nasa.pds.entities.Table;
import gov.nasa.pds.processors.DataSetProcessor;
import gov.nasa.pds.processors.impl.profile.ProfileProvider;
import gov.nasa.pds.processors.impl.profile.cassini.CassiniProfile;
import gov.nasa.pds.services.DataSetFiles;
import gov.nasa.pds.services.DataSetProcessingConfigurationException;
import gov.nasa.pds.services.DataSetProcessingException;
import gov.nasa.pds.services.MetadataFileReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.topcoder.commons.utils.LoggingWrapperUtility;
import com.topcoder.commons.utils.ValidationUtility;
import com.topcoder.util.log.Level;
import com.topcoder.util.log.Log;

/**
 * <p>
 * The {@code DataSetCacheProcessorImpl} class implements {@code DataSetProcessor} interface and represents a
 * processor for caching some dataset information.
 * </p>
 * 
 * <strong>Thread Safety:</strong> This class is not thread safe.
 * 
 * <p>
 * Version 1.1 changes [PDS - Import and Persistence Update - Assembly Contest]:
 * <ol>
 * <li>Fixed label filepaths for Cassini profile.</li>
 * </ol>
 * </p>
 * 
 * @author KennyAlive
 * @version 1.1
 */
public class DataSetCacheProcessorImpl implements DataSetProcessor, InitializingBean {
    /**
     * Constant for the class name of this class. Used for logging.
     */
    private static final String CLASS_NAME = DataSetCacheProcessorImpl.class.getName();

    /**
     * Constant for tables cache file name.
     */
    private static final String TABLES_CACHE_FILENAME = "tables";
    /**
     * Constant for labels cache filename.
     */
    private static final String LABELS_CACHE_FILENAME = "labels.zip";
    /**
     * Constant for files cache file name.
     */
    private static final String FILES_CACHE_FILENAME = "files";
    /**
     * Constant for cache.info file name
     */
    private static final String CACHE_INFO_FILENAME ="cache.info";

    /**
     * The {@code Log} instance used for logging. It is initialized with Spring setter dependency injection. Cannot be
     * null after initialization. Has a setter.
     */
    private Log logger;

    /**
     * The directory where all dataset caches should be stored. It is initialized with Spring setter dependency
     * injection. Cannot be null after initialization. Has a setter.
     */
    private String cacheDirectory;

    /**
     * The {@code MetadataFileReader} instance. It is initialized with Spring setter dependency injection. Cannot be
     * null after initialization. Has a setter.
     */
    private MetadataFileReader metadataFileReader;

    /**
     * <p>
     * The file types that should be ignored when processing dataset tables.
     * </p>
     * 
     * <p>
     * It is initialized with Spring setter dependency injection. Cannot be <code>null</code> after initialization,
     * assuming that property is initialized via Spring setter-based dependency injection and is never changed after
     * that. Has a setter.
     * </p>
     */
    private Set<String> ignoredTableFileTypes;

    /**
     * The dataset path in the filesystem. Initialized in {@code processDataSet(String dataSetPath)} method. Cannot be
     * null after initialization.
     */
    private String dataSetPath;

    /**
     * The directory of the specific dataset cache. It is a nested directory of {@code cacheDirectory} directory.
     * Initialized in {@code processDataSet(String dataSetPath)} method. Can not be null after initialization.
     */
    private File dataSetCacheDirectory;

    /**
     * The DataSetTemplate instance used to access the dataset resources. Initialized in
     * {@code processDataSet(String dataSetPath)} method. Can not be null after initialization.
     */
    private DataSetTemplate dataSetTemplate;

    /**
     * The DataSetFiles instance. Initialized in {@code processDataSet(String dataSetPath)} method. Can not be null
     * after initialization.
     */
    private DataSetFiles dataSetFiles;

    /**
     * The profile provider used by this class.
     */
    @Autowired
    private ProfileProvider profileProvider;

    /**
     * Creates an instance of {@code DataSetCacheProcessorImpl}.
     */
    public DataSetCacheProcessorImpl() {
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
     * Sets the cache directory.
     *
     * @param cacheDirectory
     *            the directory path to set
     */
    public void setCacheDirectory(String cacheDirectory) {
        this.cacheDirectory = cacheDirectory;
    }

    /**
     * Sets the metadata file reader.
     *
     * @param metadataFileReader
     *            the metadata file reader to set
     */
    public void setMetadataFileReader(MetadataFileReader metadataFileReader) {
        this.metadataFileReader = metadataFileReader;
    }

    /**
     * Sets the ignored table file types.
     * 
     * @param ignoredTableFileTypes
     *            the files types that should be ignored when processing the dataset tables
     */
    public void setIgnoredTableFileTypes(Set<String> ignoredTableFileTypes) {
        this.ignoredTableFileTypes = ignoredTableFileTypes;
    }

    /**
     * Checks whether this class was initialized by Spring properly.
     *
     * Required parameters:
     * <ul>
     * <li>logger</li>
     * <li>cacheDirectory</li>
     * <li>metadataFileReader</li>
     * </ul>
     *
     * @throws DataSetProcessingConfigurationException
     *             if the class was not initialized properly
     */
    @Override
    public void afterPropertiesSet() {
        ValidationUtility.checkNotNull(logger, "logger",
                DataSetProcessingConfigurationException.class);
        ValidationUtility.checkNotNull(cacheDirectory, "cacheDirectory",
                DataSetProcessingConfigurationException.class);
        ValidationUtility.checkNotNull(metadataFileReader, "metadataFileReader",
                DataSetProcessingConfigurationException.class);
    }

    /**
     * Creates the cache for the given dataset.
     *
     * @param dataSetPath
     *            the location of the dataset in the filesystem
     *
     * @throws DataSetProcessingException
     *             if there is any error while processing dataset
     */
    @Override
    public void processDataSet(String dataSetPath) throws DataSetProcessingException {
        this.dataSetPath = dataSetPath;

        // create cache directory
        dataSetCacheDirectory = prepareDirectories(dataSetPath);
        if (dataSetCacheDirectory == null) { // indicates that cache is already created
            return;
        }

        // create DataSetFiles instance to get information about dataset files
        dataSetFiles = new DataSetFilesImpl(dataSetPath);

        // create dataset template for working with dataset
        dataSetTemplate = new DataSetTemplate(dataSetPath, metadataFileReader, dataSetFiles, logger);

        // get the list of label files from dataset's index
        List<String> labelFilePaths = dataSetTemplate.readDataProductLabelsFromIndex();
        if (profileProvider.isCassiniProfile()) {
            CassiniProfile.correctLabelFilePaths(labelFilePaths);
        }
        for (int i = 0; i < labelFilePaths.size(); i++) {
            String filePath = dataSetFiles.getRealFilePath(labelFilePaths.get(i));
            if (filePath == null) {
                logger.log(Level.WARN, "The referenced label file {0} does not exist", labelFilePaths.get(i));
                labelFilePaths.remove(i--);
            } else {
                labelFilePaths.set(i, filePath);
            }
        }

        // create caches
        try {
            createTablesCache(labelFilePaths);
            createLabelsCache(labelFilePaths);
            createFilesCache(dataSetFiles.getFiles());
            createCacheInfoFile();
        } catch (DataSetProcessingException e) {
            File[] files = {
                    new File(dataSetCacheDirectory, TABLES_CACHE_FILENAME),
                    new File(dataSetCacheDirectory, LABELS_CACHE_FILENAME) ,
                    new File(dataSetCacheDirectory, FILES_CACHE_FILENAME),
                    new File(dataSetCacheDirectory, CACHE_INFO_FILENAME) };
            for (File file : files) {
                if (file.isFile()) {
                    file.delete();
                }
            }
            throw e;
        }
    }

    /**
     * Ensures that dataset cache directory exists.
     *
     * @param dataSetPath
     *            the dataset path
     *
     * @return the file instance for the dataset cache directory or null if dataset cache is already created
     *
     * @throws DataSetProcessingException
     *             if failed to create cache directory for the given dataset or the cache directory is not empty
     */
    private File prepareDirectories(String dataSetPath) throws DataSetProcessingException {
        // ensure that cacheDirectory exists
        File directory = new File(cacheDirectory);
        if (!directory.isDirectory() && !directory.mkdir()) {
            throw new DataSetProcessingException("Failed to create root cache directory: " + cacheDirectory);
        }

        // get dataset name
        String dataSetName = new File(dataSetPath).getName();
        if (dataSetName.isEmpty()) {
            throw new DataSetProcessingException("Dataset name is empty (dataset path = " + dataSetPath + ")");
        }

        // ensure that dataset cache directory exists. Throw an exception if we have not-empty dataset cache.
        File dataSetCacheDirectory = new File(cacheDirectory, dataSetName);
        if (dataSetCacheDirectory.isDirectory()) {
            String[] content = dataSetCacheDirectory.list();
            if (content == null || content.length > 0) {
                logger.log(Level.WARN,  "Dataset cache is already created: " + dataSetCacheDirectory.getAbsolutePath());
                return null;
            }
        } else if (!dataSetCacheDirectory.mkdir()) {
            throw new DataSetProcessingException("Failed to create cache directory for dataset " + dataSetName);
        }
        return dataSetCacheDirectory;
    }

    /**
     * Creates tables cache.
     *
     * @param labelFilePaths
     *            the filepaths of dataset label files
     *
     * @throws DataSetProcessingException
     *             if some IO error occurs when building tables cache
     */
    private void createTablesCache(List<String> labelFilePaths) throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".createTablesCache(List<String> labelFilePaths)";

        PrintWriter tableCacheWriter = null;
        Set<String> tableKeys = new HashSet<String>();

        try {
            for (String labelFilePath : labelFilePaths) {
                // parse label file
                String path = new File(dataSetPath, labelFilePath).getPath();
                MetadataFile labelMetadata = metadataFileReader.readMetadataInfo(path, dataSetFiles);

                // get product information
                Product product = new Product("PRODUCT");
                product.fromMetadata(labelMetadata);

                // search for table pointers and store table info to the file
                for (Property property : product.getOtherProperties()) {
                    if (Helper.isTablePointer(property.getName())) {
                        String pointerName = property.getName().substring(1); // skip "^" symbol
                        MetadataObject tableMetadata = Helper.getObjectFromProduct(product, pointerName);
                        if (tableMetadata == null) {
                            String message = String.format("Failed to find OBJECT definition for '%s' pointer (%s)",
                                    pointerName, labelFilePath);
                            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                                    message));
                        }

                        Table table = new Table();
                        table.fromMetadata(tableMetadata);

                        if (table.getColumns() == null) {
                            // this exception could indicate that parser should be extended to be more PDS-compliant
                            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                                    "table has no columns (probably parser doesn't support some PDS format feature"));
                        }

                        boolean attachedLabel = Helper.isDataAttached(property, product.getRecordByteSize());
                        String extension = Helper.getFileExtension(property.getValues().get(0).toLowerCase());

                        if (attachedLabel || !ignoredTableFileTypes.contains(extension)) {
                            StringBuilder sb = new StringBuilder();
                            for (Column column : table.getColumns()) {
                                sb.append(Helper.getSqlIdentifier(column.getName()));
                                sb.append(" ");
                                sb.append(column.getSize());
                                sb.append(" ");
                            }
                            String tableKey = sb.toString();
                            sb.append("\n");

                            if (!tableKeys.contains(tableKey)) {
                                tableKeys.add(tableKey);
                                if (tableCacheWriter == null) {
                                    tableCacheWriter = new PrintWriter(new File(dataSetCacheDirectory,
                                            TABLES_CACHE_FILENAME));
                                }
                                tableCacheWriter.print(sb.toString());
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Failed to create tables cache", e));
        } finally {
            if (tableCacheWriter != null) {
                tableCacheWriter.close();
            }
        }
    }

    /**
     * Creates labels cache.
     *
     * @param labelFilePaths
     *            the filepaths of dataset label files
     *
     * @throws DataSetProcessingException
     *             if some IO error occurs when building labels cache
     */
    private void createLabelsCache(List<String> labelFilePaths) throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".createLabelsCache(List<String> labelFilePaths)";

        if (labelFilePaths.isEmpty()) {
            return;
        }

        ZipOutputStream zipOutputStream = null;
        FileInputStream labelInputStream = null;
        byte[] buffer = new byte[1024 * 64];

        try {
            // add all labels to the cache (zip archive)
            for (String labelFilePath : labelFilePaths) {
                File labelFile = new File(dataSetPath, labelFilePath);
                if (labelFile.length() < 1024 * 100) { // do not cache large label files
                    // create labels cache on the first touch
                    if (zipOutputStream == null) {
                        OutputStream out = new FileOutputStream(
                                new File(dataSetCacheDirectory, LABELS_CACHE_FILENAME));
                        zipOutputStream = new ZipOutputStream(out);
                    }
                    // process label
                    ZipEntry ze = new ZipEntry(labelFilePath);
                    zipOutputStream.putNextEntry(ze);
                    labelInputStream = new FileInputStream(labelFile);
                    int len;
                    while ((len = labelInputStream.read(buffer)) > 0) {
                        zipOutputStream.write(buffer, 0, len);
                    }
                    labelInputStream.close();
                    labelInputStream = null;
                    zipOutputStream.closeEntry();
                }
            }
        } catch (FileNotFoundException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Failed to create labels cache", e));
        } catch (IOException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Failed to create labels cache", e));
        } finally {
            if (zipOutputStream != null) {
                try {
                    zipOutputStream.close();
                } catch (IOException e) {
                    // ignore
                }
            }
            if (labelInputStream != null) {
                try {
                    labelInputStream.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

    /**
     * Creates files cache.
     *
     * @param dataSetFiles
     *            all dataset files
     *
     * @throws DataSetProcessingException
     *             if some IO error occurs when building files cache
     */
    private void createFilesCache(List<String> dataSetFiles) throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".createFilesCache(List<String> dataSetFiles)";

        PrintWriter filesCacheWriter = null;
        try {
            filesCacheWriter = new PrintWriter(new File(dataSetCacheDirectory, FILES_CACHE_FILENAME));
            for (String dataSetFile : dataSetFiles) {
                filesCacheWriter.println(dataSetFile);
            }
        } catch (FileNotFoundException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Failed to create files cache", e));
        } finally {
            if (filesCacheWriter != null) {
                filesCacheWriter.close();
            }
        }
    }

    /**
     * Creates cache.info file.
     *
     * @throws DataSetProcessingException
     *             if some IO error occurs when creating the file
     */
    private void createCacheInfoFile() throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".createCacheInfoFile()";

        PrintWriter cacheInfoWriter = null;
        try {
            cacheInfoWriter = new PrintWriter(new File(dataSetCacheDirectory, CACHE_INFO_FILENAME));
            cacheInfoWriter.println(new Date());
        } catch (FileNotFoundException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Failed to create cache.info file", e));
        } finally {
            if (cacheInfoWriter != null) {
                cacheInfoWriter.close();
            }
        }
    }
}
