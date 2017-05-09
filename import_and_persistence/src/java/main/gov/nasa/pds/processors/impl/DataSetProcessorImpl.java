/*
 * Copyright (C) 2011-2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl;

import gov.nasa.pds.entities.Column;
import gov.nasa.pds.entities.DataFile;
import gov.nasa.pds.entities.DataSet;
import gov.nasa.pds.entities.Instrument;
import gov.nasa.pds.entities.InstrumentHost;
import gov.nasa.pds.entities.MetadataFile;
import gov.nasa.pds.entities.MetadataObject;
import gov.nasa.pds.entities.Mission;
import gov.nasa.pds.entities.Product;
import gov.nasa.pds.entities.Property;
import gov.nasa.pds.entities.Reference;
import gov.nasa.pds.entities.Table;
import gov.nasa.pds.entities.Target;
import gov.nasa.pds.entities.ValidationReport;
import gov.nasa.pds.entities.Volume;
import gov.nasa.pds.processors.DataSetProcessor;
import gov.nasa.pds.processors.impl.profile.ProfileProvider;
import gov.nasa.pds.processors.impl.profile.cassini.CassiniProfile;
import gov.nasa.pds.services.ConversionPersistence;
import gov.nasa.pds.services.DataFileReader;
import gov.nasa.pds.services.DataSetFiles;
import gov.nasa.pds.services.DataSetProcessingConfigurationException;
import gov.nasa.pds.services.DataSetProcessingException;
import gov.nasa.pds.services.MetadataFileReader;
import gov.nasa.pds.services.MetadataValidationManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.topcoder.commons.utils.LoggingWrapperUtility;
import com.topcoder.commons.utils.ParameterCheckUtility;
import com.topcoder.commons.utils.ValidationUtility;
import com.topcoder.util.log.Level;
import com.topcoder.util.log.Log;

/**
 * <p>
 * The <code>DataSetProcessorImpl</code> class implements <code>DataSetProcessor</code> interface by providing
 * implementation of <code>processDataSet()</code> method.
 * </p>
 * 
 * <strong>Thread Safety:</strong> This class is not thread safe.
 * 
 * <p>
 * Version 1.1 changes [PDS - Import and Persistence Update - Assembly Contest]:
 * <ol>
 * <li>Added code that fixes label filepaths for Cassini profile.</li>
 * <li>Added steps to process supplemental tables for Cassini profile.</li>
 * </ol>
 * </p>
 * 
 * @author KennyAlive
 * @version 1.1
 */
public class DataSetProcessorImpl implements DataSetProcessor, InitializingBean {
    /**
     * Constant for the class name of this class. Used for logging.
     */
    private static final String CLASS_NAME = DataSetProcessorImpl.class.getName();

    /**
     * The filename of the file with metadata for data set volume.
     */
    private static final String VOLUME_DESC_FILENAME = "voldesc.cat";

    /**
     * The name of the directory where catalog files are stored.
     */
    private static final String CATALOG_DIRECTORY_NAME = "catalog";

    /**
     * Constant for aareadme.txt file name
     */
    private static final String AAREADME_TXT_FILENAME = "aareadme.txt";

    /**
     * Constant for errata.txt file name
     */
    private static final String ERRATA_TXT_FILENAME = "errata.txt";

    //--------- Begin of folders that need to be processed ---------
    /**
     * The document folder name
     */
    private static final String DOCUMENT_DIRECTORY = "document";
    /**
     * The calib folder name
     */
    private static final String CALIB_DIRECTORY = "calib";
    /**
     * The gazetter folder name
     */
    private static final String GAZETTER_DIRECTORY = "gazetter";
    /**
     * The software folder name
     */
    private static final String SOFTWARE_DIRECTORY = "software";

    //----------- Begin of object names -------------
    /**
     * The name of the volume metadata object - the toplevel object from the volume description file
     */
    private static final String VOLUME_OBJECT_NAME = "VOLUME";
    /**
     * The name of the catalog object that is defined within a volume object.
     */
    private static final String CATALOG_OBJECT_NAME = "CATALOG";
    /**
     * The name of the reference object.
     */
    private static final String REFERENCE_OBJECT_NAME = "REFERENCE";
    /**
     * The name of the instrument host object.
     */
    private static final String INSTRUMENT_HOST_OBJECT_NAME = "INSTRUMENT_HOST";
    /**
     * The name of the instrument object.
     */
    private static final String INSTRUMENT_OBJECT_NAME = "INSTRUMENT";
    /**
     * The name of the mission object.
     */
    private static final String MISSION_OBJECT_NAME = "MISSION";
    /**
     * The name of the target object.
     */
    private static final String TARGET_OBJECT_NAME = "TARGET";
    /**
     * The name of the data set object.
     */
    private static final String DATA_SET_OBJECT_NAME = "DATA_SET";
    /**
     * The name of the image object.
     */
    private static final String IMAGE_OBJECT_NAME = "IMAGE";
    /**
     * The name of the product object.
     */
    private static final String PRODUCT_OBJECT_NAME = "PRODUCT";


    //-------- Begin of catalog properties names --------
    /**
     * Reference property name of the catalog object.
     */
    private static final String CATALOG_REFERENCE_PROPERTY_NAME = "^REFERENCE_CATALOG";
    /**
     * Instrument host property name of the catalog object.
     */
    private static final String CATALOG_INSTRUMENT_HOST_PROPERTY_NAME = "^INSTRUMENT_HOST_CATALOG";
    /**
     * Instrument property name of the catalog object.
     */
    private static final String CATALOG_INSTRUMENT_PROPERTY_NAME = "^INSTRUMENT_CATALOG";
    /**
     * Mission property name of the catalog object.
     */
    private static final String CATALOG_MISSION_PROPERTY_NAME = "^MISSION_CATALOG";
    /**
     * Target property name of the catalog object.
     */
    private static final String CATALOG_TARGET_PROPERTY_NAME = "^TARGET_CATALOG";
    /**
     * Data set property name of the catalog object.
     */
    private static final String CATALOG_DATA_SET_PROPERTY_NAME = "^DATA_SET_CATALOG";
    private static final String CATALOG_DATA_SET_PROPERTY_NAME2 = "^DATA_SET";
    /**
     * Personnel property name of the catalog object.
     */
    private static final String CATALOG_PERSONNEL_PROPERTY_NAME = "^PERSONNEL_CATALOG";
    /**
     * Software property name of the catalog object.
     */
    private static final String CATALOG_SOFTWARE_PROPERTY_NAME = "^SOFTWARE_CATALOG";
    /**
     * DataSet collection property name of the catalog object.
     */
    private static final String CATALOG_DATA_SET_COLLECTION_PROPERTY_NAME = "^DATA_SET_COLLECTION_CATALOG";

    /**
     * This set contains predefined properties names of the CATALOG metadata object.
     */
    private static final Set<String> CATALOG_PROPERTIES_NAMES;

    /**
     * Maps catalog property name to corresponding object name
     */
    private static final Map<String, String> CATALOG_PROPERTY_NAME_2_OBJECT_NAME;

    /**
     * Initializes <code>CATALOG_PROPERTIES_PATTERNS</code> with predefined patterns.
     * Initializes <code>CATALOG_PROPERTY_NAME_2_OBJECT_NAME</code>.
     */
    static {
        Set<String> set = new HashSet<String>();
        set.add(CATALOG_REFERENCE_PROPERTY_NAME);
        set.add(CATALOG_INSTRUMENT_HOST_PROPERTY_NAME);
        set.add(CATALOG_INSTRUMENT_PROPERTY_NAME);
        set.add(CATALOG_MISSION_PROPERTY_NAME);
        set.add(CATALOG_TARGET_PROPERTY_NAME);
        set.add(CATALOG_DATA_SET_PROPERTY_NAME);
        set.add(CATALOG_DATA_SET_PROPERTY_NAME2);
        CATALOG_PROPERTIES_NAMES = Collections.unmodifiableSet(set);

        Map<String, String> map = new HashMap<String, String>();
        map.put(CATALOG_REFERENCE_PROPERTY_NAME, REFERENCE_OBJECT_NAME);
        map.put(CATALOG_INSTRUMENT_HOST_PROPERTY_NAME, INSTRUMENT_HOST_OBJECT_NAME);
        map.put(CATALOG_INSTRUMENT_PROPERTY_NAME, INSTRUMENT_OBJECT_NAME);
        map.put(CATALOG_MISSION_PROPERTY_NAME, MISSION_OBJECT_NAME);
        map.put(CATALOG_TARGET_PROPERTY_NAME, TARGET_OBJECT_NAME);
        map.put(CATALOG_DATA_SET_PROPERTY_NAME, DATA_SET_OBJECT_NAME);
        map.put(CATALOG_DATA_SET_PROPERTY_NAME2, DATA_SET_OBJECT_NAME);
        CATALOG_PROPERTY_NAME_2_OBJECT_NAME = Collections.unmodifiableMap(map);
    }

    /**
     * <p>
     * This enumeration provides product pointers types.
     * </p>
     *
     * NOTE: the implementation uses the following convention: enumerator names match the corresponding
     * pointer names without leading ^ symbol and optional prefix.
     *
     * <strong>Thread Safety:</strong> This enum is immutable and thread safe.
     *
     * @author TCSASSEMBLER
     * @version 1.0
     */
    private enum ProductPointer {
        TABLE,
        SERIES,
        LABEL,
        TEXT,
        HEADER,
        IMAGE
    }

    /**
     * <p>
     * The <code>Log</code> instance used for logging.
     * </p>
     *
     * <p>
     * It is initialized with Spring setter dependency injection. Cannot be <code>null</code> after initialization,
     * assuming that property is initialized via Spring setter-based dependency injection and is never changed
     * after that. Has a setter.
     * </p>
     */
    private Log logger;

    /**
     * <p>
     * The <code>MetadataFileReader</code> instance.
     * </p>
     *
     * <p>
     * It is initialized with Spring setter dependency injection. Cannot be <code>null</code> after initialization,
     * assuming that property is initialized via Spring setter-based dependency injection and is never changed
     * after that. Has a setter.
     * </p>
     */
    private MetadataFileReader metadataFileReader;

    /**
     * <p>
     * The <code>MetadataValidationManager</code> instance.
     * </p>
     *
     * <p>
     * It is initialized with Spring setter dependency injection. Cannot be <code>null</code> after initialization,
     * assuming that property is initialized via Spring setter-based dependency injection and is never changed
     * after that. Has a setter.
     * </p>
     */
    private MetadataValidationManager metadataValidationManager;

    /**
     * <p>
     * The <code>DataFileReader</code> instance.
     * </p>
     *
     * <p>
     * It is initialized with Spring setter dependency injection. Cannot be <code>null</code> after initialization,
     * assuming that property is initialized via Spring setter-based dependency injection and is never changed
     * after that. Has a setter.
     * </p>
     */
    private DataFileReader dataFileReader;

    /**
     * <p>
     * The <code>ConversionPersistence</code> instance.
     * </p>
     *
     * <p>
     * It is initialized with Spring setter dependency injection. Cannot be <code>null</code> after initialization,
     * assuming that property is initialized via Spring setter-based dependency injection and is never changed
     * after that. Has a setter.
     * </p>
     */
    private ConversionPersistence conversionPersistence;

    /**
     * <p>
     * The file types that should have their content read. Anything else should be copied as-is to a
     * destination folder.
     * </p>
     *
     * <p>
     * It is initialized with Spring setter dependency injection. Cannot be <code>null</code> after initialization,
     * assuming that property is initialized via Spring setter-based dependency injection and is never changed
     * after that. Has a setter.
     * </p>
     */
    private Set<String> asciiFileTypes;

    /**
     * <p>
     * The file types that should be ignored when processing the software folder.
     * </p>
     *
     * <p>
     * It is initialized with Spring setter dependency injection. Cannot be <code>null</code> after initialization,
     * assuming that property is initialized via Spring setter-based dependency injection and is never changed
     * after that. Has a setter.
     * </p>
     */
    private Set<String> ignoredSoftwareFileTypes;

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
     * <p>
     * The line separator symbols. It is an optional property. If it is not specified then line.separator system
     * property is used.
     * </p>
     *
     * <p>
     * It is initialized with Spring setter dependency injection. Cannot be <code>null</code> after initialization,
     * assuming that property is initialized via Spring setter-based dependency injection and is never changed
     * after that. Has a setter.
     * </p>
     */
    private String lineSeparator;

    /**
     * <p>
     * The transaction manager used to manage db transactions.
     * </p>
     *
     * <p>
     * It is initialized with Spring setter dependency injection. Cannot be {@code null} after initialization,
     * assuming that property is initialized via Spring setter-based dependency injection and is never changed after
     * that. Has a setter.
     * </p>
     */
    private PlatformTransactionManager transactionManager;

    /**
     * <p>
     * The directory where all dataset caches should be stored.
     * </p>
     *
     * <p>
     * It is initialized with Spring setter dependency injection. Cannot be {@code null} after initialization,
     * assuming that property is initialized via Spring setter-based dependency injection and is never changed after
     * that. Has a setter.
     * </p>
     */
    private String cacheDirectory;

    /**
     * The profile provider used by this class.
     */
    @Autowired
    private ProfileProvider profileProvider;

    /* ************ The fields that are mutable during import process ********************* */
    /**
     * The path to the dataset's root directory that is being imported.
     */
    private String dataSetDirectory; // Several datasets can be processed in one session

    /**
     * The DataSetTemplate instance used to access the dataset resources.
     */
    private DataSetTemplate dataSetTemplate;

    /**
     * The DataSetFiles instance. Initialized in {@link #initializeProcessing(String dataSetPath)} method. Can not be
     * null after initialization.
     */
    private DataSetFiles dataSetFiles;

    /**
     * The DataSetCache instance that represents a cache for the dataset being processed.
     */
    private DataSetCache dataSetCache;

    /**
     * Mapping from table key to table information object. Table key is created by
     * {@link #getTableKey(String, String, int)} method.
     */
    private Map<String, ConversionPersistence.TableInfo> tableKey2TableInfo;
    /* ********************************************************************************** */

    /**
     * Creates an instance of {@code DataSetProcessorImpl}.
     */
    public DataSetProcessorImpl() {
        // Empty
    }

    /**
     * Sets the logger.
     *
     * @param logger
     *              the logger to set
     */
    public void setLogger(Log logger) {
        this.logger = logger;
    }

    /**
     * Sets the metadata file reader.
     *
     * @param metadataFileReader
     *              the metadata file reader to set
     */
    public void setMetadataFileReader(MetadataFileReader metadataFileReader) {
        this.metadataFileReader = metadataFileReader;
    }

    /**
     * Sets the metadata validation manager.
     *
     * @param metadataValidationManager
     *              the metadata validation manager to set
     */
    public void setMetadataValidationManager(MetadataValidationManager metadataValidationManager) {
        this.metadataValidationManager = metadataValidationManager;
    }

    /**
     * Sets the data file reader.
     *
     * @param dataFileReader
     *              the data file reader to set
     */
    public void setDataFileReader(DataFileReader dataFileReader) {
        this.dataFileReader = dataFileReader;
    }

    /**
     * Sets the conversion persistence instance.
     *
     * @param conversionPersistence
     *              the conversion persistence instance to set
     */
    public void setConversionPersistence(ConversionPersistence conversionPersistence) {
        this.conversionPersistence = conversionPersistence;
    }

    /**
     * Sets the ASCII file types.
     *
     * @param asciiFileTypes
     *              the ASCII files types that should have their content read
     */
    public void setAsciiFileTypes(Set<String> asciiFileTypes) {
        this.asciiFileTypes = asciiFileTypes;
    }

    /**
     * Sets the ignored software file types.
     *
     * @param ignoredSoftwareFileTypes
     *              the files types that should be ignored when processing the software folder
     */
    public void setIgnoredSoftwareFileTypes(Set<String> ignoredSoftwareFileTypes) {
        this.ignoredSoftwareFileTypes = ignoredSoftwareFileTypes;
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
     * Sets the line separator that is used when creating a string that represents the content of the text file.
     * 
     * @param lineSeparator
     *            the line separator to set
     */
    public void setLineSeparator(String lineSeparator) {
        this.lineSeparator = lineSeparator;
    }

    /**
     * Sets the transaction manager.
     *
     * @param transactionManager
     *            the transaction manager to set
     */
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
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
     * Checks whether this class was initialized by Spring properly.
     * 
     * Required parameters:
     * <ul>
     * <li>logger</li>
     * <li>dataSetSelector</li>
     * <li>metadataFileReader</li>
     * <li>metadataValidationManager</li>
     * <li>dataFileReader</li>
     * <li>conversionPersistence</li>
     * <li>asciiFileTypes</li>
     * <li>cacheDirectory</li>
     * </ul>
     * 
     * Optional parameters:
     * <ul>
     * <li>ignoredSoftwareFileTypes</li>
     * <li>ignoredTableFileTypes</li>
     * </ul>
     * 
     * @throws DataSetProcessingConfigurationException
     *             if the class was not initialized properly
     */
    @Override
    public void afterPropertiesSet() {
        ValidationUtility.checkNotNull(logger, "logger",
                DataSetProcessingConfigurationException.class);
        ValidationUtility.checkNotNull(metadataFileReader, "metadataFileReader",
                DataSetProcessingConfigurationException.class);
        ValidationUtility.checkNotNull(metadataValidationManager, "metadataValidationManager",
                DataSetProcessingConfigurationException.class);
        ValidationUtility.checkNotNull(dataFileReader, "dataFileReader",
                DataSetProcessingConfigurationException.class);
        ValidationUtility.checkNotNull(conversionPersistence, "conversionPersistence",
                DataSetProcessingConfigurationException.class);
        ValidationUtility.checkNotNull(asciiFileTypes, "asciiFileTypes",
                DataSetProcessingConfigurationException.class);
        ValidationUtility.checkNotNull(cacheDirectory, "cacheDirectory",
                DataSetProcessingConfigurationException.class);
    }

    /**
     * Processes the dataset located on the given path.
     *
     * @param dataSetPath
     *            the location of the dataset in the filesystem
     *
     * @throws IllegalArgumentException
     *             if dataSetPath is null or an empty string
     * @throws DataSetProcessingException
     *             if any IO/parsing/persistence error occurs while processing the dataset
     */
    @Override
    public void processDataSet(String dataSetPath) throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".processDataSet(String dataSetPath)";

        ParameterCheckUtility.checkNotNullNorEmptyAfterTrimming(dataSetPath, "dataSetPath");

        LoggingWrapperUtility.logEntrance(logger, signature,
                new String[] { "dataSetPath" }, new Object[] { dataSetPath });

        // initialize resources and create db tables (if any)
        initializeProcessing(dataSetPath);
        List<String> createdTables = createTables();

        // process the dataset within the scope of a single transaction
        TransactionStatus status = beginTransaction();
        try {
            doProcessing();
        } catch (DataSetProcessingException e) {
            // rollback transaction and drop all the tables that were created for this dataset
            try {
                if (transactionManager != null) {
                    transactionManager.rollback(status);
                }
            } catch (TransactionException e2) {
                LoggingWrapperUtility.logException(logger, signature, e2);
            }
            dropTables(createdTables);
            throw e;
        }

        // commit transaction
        try {
            if (transactionManager != null) {
                transactionManager.commit(status);
            }
        } catch (TransactionException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Failed to commit transaction", e));
        }
        LoggingWrapperUtility.logExit(logger, signature, null);
    }

    /**
     * Initializes resources necessary for dataset processing.
     *
     * @param dataSetPath
     *            the dataset path
     *
     * @throws DataSetProcessingException
     *             if any error occurs
     */
    private void initializeProcessing(String dataSetPath) throws DataSetProcessingException {
        this.dataSetDirectory = dataSetPath;

        // initialize metadata validation manager
        if (!metadataValidationManager.isLoaded()) {
            Date start = new Date();
            logger.log(Level.INFO, "Initializing metadata validation manager...");
            metadataValidationManager.load();
            logger.log(Level.INFO, "Metadata  validation manager initialized in "
                    + Helper.getTimingString(start, new Date()));
        }

        // create dataset cache instance
        File dataSetCacheDirectory = new File(cacheDirectory, getDataSetName());
        dataSetCache = new DataSetCache(dataSetCacheDirectory, logger);

        // create DataSetFiles instance
        dataSetFiles = new DataSetFilesImpl(dataSetPath, dataSetCache.getDataSetFiles());

        // create DataSetTemplate instance
        dataSetTemplate = new DataSetTemplate(dataSetDirectory, metadataFileReader, dataSetFiles, logger);

        // create mapping to store table information.
        tableKey2TableInfo = new HashMap<String, ConversionPersistence.TableInfo>();

        // clear all caches used by persistence layer
        conversionPersistence.clearCaches();

        // prepare Cassini profile for new dataset processing
        if (profileProvider.isCassiniProfile()) {
            profileProvider.getCassiniProfile().initializeProcessing(getDataSetName());
        }
    }

    /**
     * Creates all dataset tables in the database (but does not fill them with data yet).
     * 
     * @return the list with names of the created tables
     * 
     * @throws DataSetProcessingException
     *             if any error occurs
     */
    private List<String> createTables() throws DataSetProcessingException {
        List<String> createdTables = new ArrayList<String>();
        try {
            List<DataSetCache.TableInfo> tablesInfo = dataSetCache.getTablesInfo();

            // Create tables.
            for (int i = 0; i < tablesInfo.size(); i++) {
                DataSetCache.TableInfo tableInfo = tablesInfo.get(i);

                ConversionPersistence.TableInfo table = conversionPersistence.createTable(
                        Helper.getUniqueNames(tableInfo.getColumnNames()),
                        tableInfo.getColumnSizes());

                createdTables.add(table.getSQLTableName());
                String tableKey = getTableKey(tableInfo.getColumnNames(), tableInfo.getColumnSizes());
                tableKey2TableInfo.put(tableKey, table);
            }
        } catch (DataSetProcessingException e) {
            dropTables(createdTables);
            throw e;
        }
        return createdTables;
    }

    /**
     * Drops the given tables.
     *
     * @param createdTables
     *            the list of tables to drop
     */
    private void dropTables(List<String> createdTables) {
        for (String tableName : createdTables) {
            try {
                conversionPersistence.dropTable(tableName);
            } catch (DataSetProcessingException e) {
                logger.log(Level.WARN, "Failed to drop table {0}", tableName);
            }
        }
    }

    /**
     * Begins the transaction for entire dataset processing
     *
     * @return the transaction status instance
     *
     * @throws DataSetProcessingException
     *             if failed to start transaction
     */
    private TransactionStatus beginTransaction() throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".beginTransaction()";

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("PDSProcessingTransaction");

        TransactionStatus status = null;
        try {
            if (transactionManager != null) {
                status = transactionManager.getTransaction(def);
            }
        } catch (TransactionException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Failed to start transaction", e));
        }
        return status;
    }

    /**
     * Does actual dataset processing.
     *
     * @throws DataSetProcessingException
     *             if any error occurs
     */
    private void doProcessing() throws DataSetProcessingException {
        try {
            // process volume metadata (VOLDESC.CAT)
            long dataSetId = processVolumeDescriptor();

            // process root directory textual files (AAREADME.TXT, ERRATA.TXT)
            processRootDirectoryTextFiles(dataSetId);

            // process dataset products
            processProducts(dataSetId);

            // process other files
            processDirectory(DOCUMENT_DIRECTORY, dataSetId, false);
            processDirectory(CALIB_DIRECTORY, dataSetId, false);
            processDirectory(GAZETTER_DIRECTORY, dataSetId, false);
            processDirectory(SOFTWARE_DIRECTORY, dataSetId, true);

            // process supplemental tables for Cassini profile
            if (profileProvider.isCassiniProfile()) {
                profileProvider.getCassiniProfile().processSupplementalTables();
            }
        } finally {
            // free resources
            conversionPersistence.clearCaches();
            dataSetCache.close();
            dataSetCache = null;
        }
    }

    /**
     * Process volume descriptor file (VOLDESC.CAT).
     *
     * @return the identifier of the data set object
     *
     * @throws DataSetProcessingException
     *              if there is any IO/parsing/persistence error while processing data set
     */
    private long processVolumeDescriptor() throws DataSetProcessingException {
        File volumeDescFile = new File(dataSetDirectory, VOLUME_DESC_FILENAME);

        MetadataFile metadataFile = metadataFileReader.readMetadataInfo(volumeDescFile.getPath(), dataSetFiles);

        MetadataObject volumeMetadata = Helper.getRequiredChildObject(metadataFile, VOLUME_OBJECT_NAME, logger);
        validateAndCorrectMetadata(Arrays.asList(volumeMetadata));

        Volume volume = new Volume(VOLUME_OBJECT_NAME);
        volume.fromMetadata(volumeMetadata);
        conversionPersistence.insertVolume(volume);

        return processVolumeCatalog(volumeMetadata);
    }

    /**
     * Processes CATALOG MetadataObject defined by the given VOLUME MetadataObject.
     *
     * @param volumeMetadata
     *            the volume metadata object that defines the catalog object
     *
     * @return the identifier of the data set object
     *
     * @throws DataSetProcessingException
     *             if there is any IO/parsing/persistence error while processing data set
     */
    private long processVolumeCatalog(MetadataObject volumeMetadata) throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".processVolumeCatalog(MetadataObject volumeMetadata)";

        long dataSetId = -1L;
        MetadataObject catalogMetadata = Helper.getRequiredChildObject(volumeMetadata, CATALOG_OBJECT_NAME, logger);
        List<Property> catalogProperties = sortCatalogProperties(catalogMetadata.getProperties());

        for (Property property : catalogProperties) {
            String propertyName = property.getName();
            for (String value : property.getValues()) {
                if (value.equals("N/A")) {
                    continue;
                }
                String relativePath = new File(CATALOG_DIRECTORY_NAME, value).getPath();
                String absPath = new File(dataSetDirectory, relativePath).getPath();

                if (dataSetFiles.getRealFilePath(relativePath) == null) {
                    logger.log(Level.WARN, "The referenced catalog file {0} does not exist", relativePath);
                    continue;
                }

                MetadataFile metadataFile = metadataFileReader.readMetadataInfo(absPath, dataSetFiles);

                String metadataObjectName = CATALOG_PROPERTY_NAME_2_OBJECT_NAME.get(propertyName);
                List<MetadataObject> metadataObjects = Helper.getChildrenByName(metadataFile, metadataObjectName);
                validateAndCorrectMetadata(metadataObjects);

                if (propertyName.equals(CATALOG_REFERENCE_PROPERTY_NAME)) {
                    for (MetadataObject metadataObject : metadataObjects) {
                        Reference reference = new Reference();
                        reference.fromMetadata(metadataObject);
                        conversionPersistence.insertReference(reference);
                    }
                } else if (propertyName.equals(CATALOG_INSTRUMENT_HOST_PROPERTY_NAME)) {
                    for (MetadataObject metadataObject : metadataObjects) {
                        InstrumentHost instrumentHost = new InstrumentHost(INSTRUMENT_HOST_OBJECT_NAME);
                        instrumentHost.fromMetadata(metadataObject);
                        conversionPersistence.insertInstrumentHost(instrumentHost);
                    }
                } else if (propertyName.equals(CATALOG_INSTRUMENT_PROPERTY_NAME)) {
                    for (MetadataObject metadataObject : metadataObjects) {
                        Instrument instrument = new Instrument();
                        instrument.fromMetadata(metadataObject);
                        conversionPersistence.insertInstrument(instrument);
                    }
                } else if (propertyName.equals(CATALOG_MISSION_PROPERTY_NAME)) {
                    for (MetadataObject metadataObject : metadataObjects) {
                        Mission mission = new Mission(MISSION_OBJECT_NAME);
                        mission.fromMetadata(metadataObject);
                        conversionPersistence.insertMission(mission);
                    }
                } else if (propertyName.equals(CATALOG_TARGET_PROPERTY_NAME)) {
                    for (MetadataObject metadataObject : metadataObjects) {
                        Target target = new Target(TARGET_OBJECT_NAME);
                        target.fromMetadata(metadataObject);
                        conversionPersistence.insertTarget(target);
                    }
                } else if (propertyName.equals(CATALOG_DATA_SET_PROPERTY_NAME)
                        || propertyName.equals(CATALOG_DATA_SET_PROPERTY_NAME2)) {
                    if (dataSetId != -1L || metadataObjects.size() > 1) {
                        throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                                "More than one data set objects are defined"));
                    } else if (metadataObjects.isEmpty()) {
                        throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                                "The data set object is not defined"));
                    }
                    DataSet dataSet = new DataSet(DATA_SET_OBJECT_NAME);
                    dataSet.fromMetadata(metadataObjects.get(0));
                    dataSetId = conversionPersistence.insertDataSet(dataSet);
                } else {
                    throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                            "Unexpected property name"));
                }
            }
        }

        // return dataset id
        if (dataSetId == -1L) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "The data set object is not defined"));
        }
        return dataSetId;
    }

    /**
     * Sorts properties in the following order (by name): reference -> instrument host -> instrument -> mission ->
     * target -> other objects names. This order guarantees that when some object is processed then all its
     * dependencies is already processed.
     *
     * Also filters out the properties that are not supported.
     *
     * @param catalogProperties
     *            the list of properties defined by the CATALOG object from the volume descriptor file.
     *
     * @return the list of sorted properties
     */
    private List<Property> sortCatalogProperties(List<Property> catalogProperties) {
        List<Property> referenceProperties = new ArrayList<Property>();
        List<Property> instrumentHostProperties = new ArrayList<Property>();
        List<Property> instrumentProperties = new ArrayList<Property>();
        List<Property> missionProperties = new ArrayList<Property>();
        List<Property> targetProperties = new ArrayList<Property>();
        List<Property> otherProperties = new ArrayList<Property>();

        for (Property property : catalogProperties) {
            if (property.getName().equals(CATALOG_REFERENCE_PROPERTY_NAME)) {
                referenceProperties.add(property);
            } else if (property.getName().equals(CATALOG_INSTRUMENT_HOST_PROPERTY_NAME)) {
                instrumentHostProperties.add(property);
            } else if (property.getName().equals(CATALOG_INSTRUMENT_PROPERTY_NAME)) {
                instrumentProperties.add(property);
            } else if (property.getName().equals(CATALOG_MISSION_PROPERTY_NAME)) {
                missionProperties.add(property);
            } else if (property.getName().equals(CATALOG_TARGET_PROPERTY_NAME)) {
                targetProperties.add(property);
            } else {
                // ignore obsolete catalog properties
                if (property.getName().equals(CATALOG_PERSONNEL_PROPERTY_NAME)
                        || property.getName().equals(CATALOG_DATA_SET_COLLECTION_PROPERTY_NAME)) {
                    continue;
                }
                // ignore software catalog property since we always use a predefined software folder
                if (property.getName().equals(CATALOG_SOFTWARE_PROPERTY_NAME)) {
                    logger.log(Level.WARN, "{0} catalog is specified but implementation always uses 'software' folder",
                            CATALOG_SOFTWARE_PROPERTY_NAME);
                    continue;
                }
                // ignore not supported catalog properties
                if (!CATALOG_PROPERTIES_NAMES.contains(property.getName())) {
                    logger.log(Level.WARN, "Unknown property ({0}) found in CATALOG object", property.getName());
                    continue;
                }
                otherProperties.add(property);
            }
        }

        List<Property> result = new ArrayList<Property>();
        result.addAll(referenceProperties);
        result.addAll(instrumentHostProperties);
        result.addAll(instrumentProperties);
        result.addAll(missionProperties);
        result.addAll(targetProperties);
        result.addAll(otherProperties);
        return result;
    }

    /**
     * Processes text files in the root directory of the data set.
     *
     * @param dataSetId
     *              the data set id
     *
     * @throws DataSetProcessingException
     *              if there is any persistence error while processing data set
     */
    private void processRootDirectoryTextFiles(long dataSetId) throws DataSetProcessingException {
        // process aareadme.txt: if it doesn't exist log a warning
        File file = new File(dataSetDirectory, AAREADME_TXT_FILENAME);
        if (file.exists()) {
            DataFile dataFile = createDataFileFromFile(file, file.getName());
            conversionPersistence.insertDataSetDocument(dataSetId, dataFile);
        } else {
            logger.log(Level.WARN, "Readme file {0} is not found in {1} directory",
                    AAREADME_TXT_FILENAME, dataSetDirectory);
        }
        // process errata.txt
        file = new File(dataSetDirectory, ERRATA_TXT_FILENAME);
        if (file.exists()) {
            DataFile dataFile = createDataFileFromFile(file, file.getName());
            conversionPersistence.insertDataSetDocument(dataSetId, dataFile);
        }
    }

    /**
     * Processes dataset products.
     *
     * @param dataSetId
     *            the dataset id
     *
     * @throws DataSetProcessingException
     *             if there is any IO/parsing/persistence error while processing data set
     */
    private void processProducts(long dataSetId) throws DataSetProcessingException {
        List<String> labelFilenames = dataSetTemplate.readDataProductLabelsFromIndex();
        if (profileProvider.isCassiniProfile()) {
            CassiniProfile.correctLabelFilePaths(labelFilenames);
        }

        for (String labelFilename : labelFilenames) {
            String labelPath = dataSetFiles.getRealFilePath(labelFilename);
            if (labelPath == null) {
                logger.log(Level.WARN, "The referenced label file {0} does not exist", labelFilename);
                continue;
            }

            // Parse label file.
            MetadataFile metadataFile;
            String labelAbsPath = new File(dataSetDirectory, labelPath).getPath();
            InputStream labelInputStream = dataSetCache.getLabelInputStream(labelFilename);

            if (labelInputStream != null) {
                long size = dataSetCache.getLabelSize(labelFilename);
                metadataFile = metadataFileReader.readMetadataInfo(labelInputStream, size, labelAbsPath, dataSetFiles);
            } else {
                metadataFile = metadataFileReader.readMetadataInfo(labelAbsPath, dataSetFiles);
            }

            // Process product.
            Product product = new Product(PRODUCT_OBJECT_NAME);
            product.fromMetadata(metadataFile);

            // RECORD_TYPE can be null when FILE object is used.
            // Do not support FILE objects at the moment.
            if (product.getRecordType() != null) {
                long productId = conversionPersistence.insertProduct(dataSetId, product);
                product.setId(productId);
                processProduct(dataSetId, product, labelPath);

                // Cassini profile needs to know mapping from label file to product id.
                if (profileProvider.isCassiniProfile()) {
                    profileProvider.getCassiniProfile().registerProduct(labelFilename, productId);
                }
            }
        }
    }

    /**
     * Processes the given product.
     * 
     * @param dataSetId
     *            the dataset id
     * @param product
     *            the product to process
     * @param labelPath
     *            the relative path of the label file
     * 
     * @throws DataSetProcessingException
     *             if there is any IO/parsing/persistence error while processing data set
     */
    private void processProduct(long dataSetId, Product product, String labelPath)
            throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".processProduct(long dataSetId, Product product, String labelPath)";

        // iterate through properties that are pointers
        for (Property property : product.getOtherProperties()) {
            String propertyName = property.getName();
            if (!Helper.isPointer(propertyName)) {
                continue;
            }
            String pointerShortName = Helper.getPointerShortName(propertyName);

            // if propertyName matches one of the ProductPointer enumerators then we have found a pointer
            for (ProductPointer pointer : ProductPointer.values()) {
                if (pointerShortName.equals(pointer.name())) {
                    String pointerValue = property.getValues().get(0).toLowerCase();

                    // Get data file path and optional data offset.
                    String dataFilePath = labelPath;
                    if (!Helper.isDataAttached(property, product.getRecordByteSize())) { // if label is detached
                        String parent = new File(labelPath).getParent();
                        dataFilePath = dataSetFiles.getRealFilePath(new File(parent, pointerValue).getPath());
                        if (dataFilePath == null) {
                            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                                    String.format("Dataset file %s does not exists", pointerValue)));
                        }
                    }
                    int dataOffset = Helper.getDataOffset(property, product.getRecordByteSize(), logger);

                    // Process pointer depending on its type.
                    if (pointer == ProductPointer.TABLE || pointer == ProductPointer.SERIES) {
                        String extension = Helper.getFileExtension(dataFilePath).toLowerCase();
                        if (!ignoredTableFileTypes.contains(extension)) {
                            processProductTable(dataSetId, product, dataFilePath, dataOffset, labelPath, property);
                        }
                    } else if (pointer == ProductPointer.LABEL || pointer == ProductPointer.TEXT) {
                        processProductTextDocument(product, pointer, dataFilePath);
                    } else if (pointer == ProductPointer.IMAGE) {
                        processProductImage(product, dataFilePath);
                    } else if (pointer == ProductPointer.HEADER) {
                        // Do not process HEADER pointer in this implementation.
                        // Assume that header is stored in the same file as the primary data object.
                        // It can be retrieved using HEADERS's metadata object that were stored
                        // as part of product's persistence.
                    }
                    break;
                }
            }
        }
    }

    /**
     * Processes a table associated with the given product.
     * 
     * @param dataSetId
     *            the dataset id
     * @param product
     *            the product that declares a table
     * @param dataFilePath
     *            the filepath of the table data file relative to dataset directory
     * @param dataOffset
     *            data offset in bytes relative to origin of data file
     * @param tableLabelPath
     *            the path of the table label file relative to dataset directory
     * @param property
     *            the property that represents a pointer to this table/series product
     * 
     * @throws DataSetProcessingException
     *             if there is any IO/parsing/persistence error while processing data set
     */
    private void processProductTable(long dataSetId, Product product, String dataFilePath, int dataOffset,
            String tableLabelPath, Property property) throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".processProductTablelong dataSetId, Product product, " +
                "String dataFilePath, int dataOffset, String tableLabelPath, Property property)";

        // Locate object that matches the pointer.
        String pointerName = property.getName().substring(1);
        MetadataObject tableMetadata = Helper.getObjectFromProduct(product, pointerName);
        if (tableMetadata == null) {
            String message = String.format("Failed to find metadata object for data file '%s'", dataFilePath);
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(message));
        }

        // Create new table with found metadata.
        Table table = new Table();
        table.fromMetadata(tableMetadata);

        if (Helper.isDataAttached(property, product.getRecordByteSize())) {
            table.setName(new File(tableLabelPath).getName());
        } else {
            table.setName(property.getValues().get(0).toLowerCase());
        }

        String tableKey = getTableKey(table.getColumns());
        ConversionPersistence.TableInfo tableInfo = tableKey2TableInfo.get(tableKey);
        if (tableInfo == null) {
            String message = String.format("Failed to get table info for key %s", tableKey);
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(message));
        }
        table.setSqlTableName(tableInfo.getSQLTableName());

        List<String> columnNamesList = new ArrayList<String>();
        for (Column column : table.getColumns()) {
            columnNamesList.add(Helper.getSqlIdentifier(column.getName()));
        }
        table.setSqlColumnNames(Helper.getUniqueNames(columnNamesList));

        // Fill in table with data and store it in the persistence.
        String absPath = new File(dataSetDirectory, dataFilePath).getPath();

        dataFileReader.readData(absPath, table, dataOffset);
        long dataTableId = conversionPersistence.insertDataIntoTable(tableInfo.getTableId(), table);
        conversionPersistence.associateTableToProduct(product.getId(), dataTableId);
        conversionPersistence.associateTableToDataSet(dataSetId, dataTableId);
    }

    /**
     * Processes text information associated with the given product.
     * 
     * @param product
     *            the product that declares a reference to the text file
     * @param pointer
     *            product pointer enumerator that defines object type: LABEL, TEXT or HEADER
     * @param dataFilePath
     *            the filepath of the text file relative to dataset directory
     * 
     * @throws DataSetProcessingException
     *             if there is any IO/parsing/persistence error while processing data set
     */
    private void processProductTextDocument(Product product, ProductPointer pointer, String dataFilePath)
            throws DataSetProcessingException {
        // Since for LABEL/TEXT/HEADER pointers there is no corresponding metadata object definition,
        // we use pointer name (without ^ and prefix) as the object name.
        String objectName = pointer.name();

        String dataFileName = generateNewObjectName(objectName, dataFilePath);
        File file = new File(dataSetDirectory, dataFilePath);
        DataFile dataFile = createDataFileFromFile(file, dataFileName);

        conversionPersistence.insertProductDocument(product.getId(), dataFile);
    }

    /**
     * Processes image associated with the given product.
     * 
     * @param product
     *            the product that declares a reference to the image file
     * @param dataFilePath
     *            the filepath of the image file relative to dataset directory
     * 
     * @throws DataSetProcessingException
     *             if there is any IO/parsing/persistence error while processing data set
     */
    private void processProductImage(Product product, String dataFilePath) throws DataSetProcessingException {
        String dataFileName = generateNewObjectName(IMAGE_OBJECT_NAME, dataFilePath);
        DataFile dataFile = new DataFile(dataFileName);
        dataFile.setPath(new File(dataSetDirectory, dataFilePath).getPath());

        conversionPersistence.insertProductDocument(product.getId(), dataFile);
    }

    /**
     * Processes the files from the given folder.
     *
     * @param directoryName
     *              specifies the name of the directory to process
     * @param dataSetId
     *              the data set id
     * @param softwareDirectory
     *              defines whether the given directory is a software directory
     *
     * @throws DataSetProcessingException
     *              if there is any IO/parsing/persistence error while processing data set
     */
    private void processDirectory(String directoryName, long dataSetId, boolean softwareDirectory)
            throws DataSetProcessingException {
        // check if directory exists
        File sourceDirectory = new File(dataSetDirectory, directoryName);
        if (!sourceDirectory.exists()) {
            return;
        }

        // get all not ".lbl" files from the source directory and all sub-directories
        List<File> files = new ArrayList<File>();
        Helper.listFiles(sourceDirectory, files);
        for (File file : files) {
            // skip label files
            if (file.getName().endsWith(".lbl")) {
                continue;
            }

            String extension = Helper.getFileExtension(file.getPath());

            // for software folder ignore file types defined by ignoredSoftwareFileTypes
            if (softwareDirectory && ignoredSoftwareFileTypes.contains(extension)) {
                continue;
            }

            if (asciiFileTypes.contains(extension) && Helper.isProbablyTextFile(file)) {
                DataFile dataFile = createDataFileFromFile(file, file.getName());
                conversionPersistence.insertDataSetDocument(dataSetId, dataFile);
            } else {
                DataFile dataFile = new DataFile(file.getName());
                dataFile.setPath(file.getPath());
                conversionPersistence.insertDataSetDocument(dataSetId, dataFile);
            }
        }
    }

    /**
     * Validates specified metadata objects and if necessary corrects them.
     *
     * @param metadataObjects
     *              the metadata objects to validate and correct
     *
     * @throws DataSetProcessingException
     *              if there is an error while doing validation
     */
    private void validateAndCorrectMetadata(List<MetadataObject> metadataObjects) throws DataSetProcessingException {
        for (MetadataObject metadataObject : metadataObjects) {
            ValidationReport report = metadataValidationManager.validateMetadata(metadataObject);
            if (!report.isValid()) {
                metadataValidationManager.correctMetadata(metadataObject, report);
            }
        }
    }

    /**
     * Creates a data file object and initializes it with the contents of the given file.
     *
     * @param file
     *              the file to read from
     * @param dataFileName
     *              specifies the name of the data file object
     *
     * @return the data file object with content initialized from the given file
     *
     * @throws DataSetProcessingException
     *              if there is an error while reading the file
     */
    private DataFile createDataFileFromFile(File file, String dataFileName) throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".createDataFileFromFile(File file, String dataFileName)";

        // if the lineSeparator optional spring-injected property is not defined then
        // use line.separator system property
        if (lineSeparator == null) {
            lineSeparator = System.getProperty("line.separator");
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();

            String line = reader.readLine();
            while (line != null) {
                sb.append(line).append(lineSeparator);
                line = reader.readLine();
            }

            DataFile dataFile = new DataFile(dataFileName);
            dataFile.setContent(sb.toString());
            return dataFile;
        } catch (IOException e) {
            String message = String.format("Failed to read '%s' file", file.getPath());
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(message, e));
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                String message = String.format("Failed to close stream associated with '%s' file", file.getPath());
                throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                        message, e));
            }
        }
    }

    /**
     * Generates new object name using the following pattern: <data_filename>_<object_name>
     *
     * @param objectName
     *              the object name
     * @param masterDataFilepath
     *              the filepath of the master data file
     *
     * @return the new object name
     */
    private String generateNewObjectName(String objectName, String masterDataFilepath) {
        return Helper.getNameWithoutExtension(masterDataFilepath) + "_" + objectName;
    }

    /**
     * Returns the dataset name.
     *
     * @return the dataset name
     */
    private String getDataSetName() {
        return (new File(dataSetDirectory)).getName();
    }

    /**
     * Get the key for the database table with the given structure.
     * 
     * @param columnNames
     *            the table column names
     * @param columnSizes
     *            the table column sizes
     * 
     * @return the table key
     */
    private String getTableKey(List<String> columnNames, List<Integer> columnSizes) {
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < columnNames.size(); i++) {
            key.append(columnNames.get(i));
            key.append(" ");
            key.append(columnSizes.get(i));
            key.append(" ");
        }
        return key.toString();
    }

    /**
     * Get the key for the database table with the given structure.
     * 
     * @param columnNames
     *            the table column names
     * @param columnSizes
     *            the table column sizes
     * 
     * @return the table key
     */
    private String getTableKey(List<Column> columns) {
        List<String> columnNames = new ArrayList<String>();
        List<Integer> columnSizes = new ArrayList<Integer>();
        for (Column column : columns) {
            columnNames.add(Helper.getSqlIdentifier(column.getName()));
            columnSizes.add(column.getSize());
        }
        return getTableKey(columnNames, columnSizes);
    }
}
