/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl.profile.cassini;

import gov.nasa.pds.entities.Column;
import gov.nasa.pds.entities.EntityHelper;
import gov.nasa.pds.entities.MetadataFile;
import gov.nasa.pds.entities.MetadataObject;
import gov.nasa.pds.entities.Product;
import gov.nasa.pds.entities.Property;
import gov.nasa.pds.entities.Row;
import gov.nasa.pds.entities.Table;
import gov.nasa.pds.processors.impl.Helper;
import gov.nasa.pds.processors.impl.profile.BaseProfile;
import gov.nasa.pds.services.ConversionPersistence;
import gov.nasa.pds.services.DataFileReader;
import gov.nasa.pds.services.DataSetProcessingConfigurationException;
import gov.nasa.pds.services.DataSetProcessingException;
import gov.nasa.pds.services.MetadataFileReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.topcoder.commons.utils.LoggingWrapperUtility;
import com.topcoder.util.log.Level;
import com.topcoder.util.log.Log;

/**
 * <p>
 * The {@code CassiniProfile} class provides configuration and functionality specific to Cassini datasets. It does not
 * implement some specific interface, anything that could be considered as Cassini specific should be exposed via this
 * class. This class extends {@code BaseProfile} which provides basic initialization services.
 * </p>
 * 
 * <strong>Thread Safety:</strong> This class is not required to be thread-safe.
 * 
 * @author KennyAlive
 * @version 1.0
 */
public class CassiniProfile extends BaseProfile {
    /**
     * Constant for the class name of this class. Used for logging.
     */
    private static final String CLASS_NAME = CassiniProfile.class.getName();

    /**
     * The table types configuration file on the classpath.
     */
    private static final String TABLE_TYPES_CONFIG_FILE = "cassini-table-types.list";

    /**
     * The instruments configuration file on the classpath.
     */
    private static final String INSTRUMENTS_CONFIG_FILE = "cassini-instruments.list";

    /**
     * The key for property that defines supplemental tables root directory (where subdirectories for each dataset are
     * located).
     */
    private static final String SUPPLEMENTAL_TABLES_DIRECTORY_KEY = "profile.supplementalTablesDirectory";

    /**
     * Table semantics to denote regular table (like moon, ring or saturn tables).
     */
    private static final String REGULAR_TABLE_SEMANTICS = "REGULAR_TABLE";

    /**
     * Table semantics to denote inventory table.
     */
    private static final String INVENTORY_TABLE_SEMANTICS = "INVENTORY_TABLE";

    /**
     * The metadata reader. Initialized in constructor.
     */
    private final MetadataFileReader metadataFileReader;

    /**
     * The table data reader. Initialized in constructor.
     */
    private final DataFileReader dataFileReader;

    /**
     * The persistence service. Initialized in constructor.
     */
    private final ConversionPersistence conversionPersistence;

    /**
     * The list of registered table types.
     */
    private final List<CassiniTableType> cassiniTableTypes = new ArrayList<CassiniTableType>();

    /**
     * The list of instruments specified in profile configuration.
     */
    private final List<CassiniInstrument> cassiniInstruments = new ArrayList<CassiniInstrument>();

    /**
     * The supplemental tables directory as specified in configuration.
     */
    private final String supplementalTablesDirectory;

    /**
     * Mapping from label path (relative to dataset directory) to product id that is described by this label.
     */
    private final Map<String, Long> labelPath2ProductId = new HashMap<String, Long>();

    /**
     * Specifies the supplemental tables that should be processed.
     */
    private final List<TableInfo> tables = new ArrayList<TableInfo>();

    /**
     * The name of the current dataset.
     */
    private String dataSetName;

    /**
     * Creates {@code CassiniProfile} instance.
     * 
     * @param logger
     *            the logger instance
     * @param configurationFile
     *            the configuration file on the classpath
     * @param metadataFileReader
     *            the metadata reader instance
     * @param dataFileReader
     *            the table data reader
     * @param conversionPersistence
     *            the persistence service instance
     * 
     * @throws DataSetProcessingConfigurationException
     *             if failed to configure this instance
     */
    public CassiniProfile(Log logger,
            String configurationFile,
            MetadataFileReader metadataFileReader,
            DataFileReader dataFileReader,
            ConversionPersistence conversionPersistence) {

        super(logger, configurationFile);

        final String signature = CLASS_NAME
                + ".CassiniProfile(Log, String, MetadataFileReader, DataFileReader, ConversionPersistence)";

        try {
            this.metadataFileReader = metadataFileReader;
            this.dataFileReader = dataFileReader;
            this.conversionPersistence = conversionPersistence;

            supplementalTablesDirectory = configuration.getProperty(SUPPLEMENTAL_TABLES_DIRECTORY_KEY);
            if (supplementalTablesDirectory == null) {
                throw new DataSetProcessingConfigurationException(SUPPLEMENTAL_TABLES_DIRECTORY_KEY
                        + " property is not defined in profile configuration");
            }
            initializeTableTypes();
            initializeInstrumentsAndTables();
        } catch (DataSetProcessingConfigurationException e) {
            throw LoggingWrapperUtility.logException(logger, signature, e);
        }
    }

    /**
     * Prepares to process new dataset.
     * 
     * @param dataSetName
     *            the dataset name
     */
    public void initializeProcessing(String dataSetName) {
        labelPath2ProductId.clear();
        this.dataSetName = dataSetName;
    }

    /**
     * Registers new product.
     * 
     * @param labelPath
     *            the label relative path
     * @param productId
     *            the product identifier for the given label
     */
    public void registerProduct(String labelPath, long productId) {
        labelPath2ProductId.put(labelPath.toLowerCase(), productId);
    }

    /**
     * Processes supplemental tables of the given dataset. Processing involves storing table's data in the database
     * and persisting other meta-information related to supplementary tables.
     * 
     * @throws DataSetProcessingException
     *             if failed to process supplemental table
     */
    public void processSupplementalTables() throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".processSupplementalTables(long dataSetId)";
        try {
            for (TableInfo table : tables) {
                if (table.getTableType().getSemantics().contains(INVENTORY_TABLE_SEMANTICS)) {
                    processInventoryTable(table);
                }
                if (table.getTableType().getSemantics().contains(REGULAR_TABLE_SEMANTICS)) {
                    processRegularTable(table);
                }
            }
        } catch (DataSetProcessingException e) {
            throw LoggingWrapperUtility.logException(logger, signature, e);
        }
    }

    /**
     * Corrects paths obtained from index.tab in order to get LBL-files locations. The corrected values replace the
     * original ones.
     * 
     * @param labelFilePaths
     *            the list of filepaths retrieved from index.tab
     */
    public static void correctLabelFilePaths(List<String> labelFilePaths) {
        // NOTE: DataSet processor resolves case sensitivity by using files list cache, we shouldn't care about this.
        final String IMG_EXTENSION = ".img";
        final String LBL_EXTENSION = ".lbl";

        for (int i = 0; i < labelFilePaths.size(); i++) {
            String filePath = labelFilePaths.get(i);
            if (filePath.toLowerCase().endsWith(IMG_EXTENSION)) {
                filePath = filePath.substring(0, filePath.length() - IMG_EXTENSION.length()) + LBL_EXTENSION;
                labelFilePaths.set(i, filePath);
            }
        }
    }

    /**
     * Reads table types configuration.
     * 
     * @throws DataSetProcessingConfigurationException
     *             if failed to read configuration file
     */
    private void initializeTableTypes() {
        // read configuration file
        List<String> lines;
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(TABLE_TYPES_CONFIG_FILE);
        if (stream == null) {
            throw new DataSetProcessingConfigurationException("Can not find file on the classpath: "
                    + TABLE_TYPES_CONFIG_FILE);
        }
        try {
            lines = Helper.readFileLines(stream);
        } catch (IOException e) {
            throw new DataSetProcessingConfigurationException("Failed to read table types configuration", e);
        }
        // parse configuration
        for (String line : lines) {
            String[] values = line.trim().split("\\s+");
            if (values[0].length() > 0) { // always have at least 1 element, even for empty string
                CassiniTableType tableType = new CassiniTableType();
                tableType.setName(values[0]);
                tableType.setSemantics(new ArrayList<String>());
                for (int i = 1; i < values.length; i++) {
                    tableType.getSemantics().add(values[i]);
                }
                cassiniTableTypes.add(tableType);
            }
        }
    }

    /**
     * Reads instruments configuration and supplemental tables configuration for each instruments.
     * 
     * @throws DataSetProcessingConfigurationException
     *             if failed to read instruments configuration
     */
    private void initializeInstrumentsAndTables() {
        // read instruments list
        List<String> instrumentNames = new ArrayList<String>();
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(INSTRUMENTS_CONFIG_FILE);
        if (stream == null) {
            throw new DataSetProcessingConfigurationException("Can not find file on the classpath: "
                    + INSTRUMENTS_CONFIG_FILE);
        }
        try {
            List<String> lines = Helper.readFileLines(stream);
            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    instrumentNames.add(line.trim());
                }
            }
        } catch (IOException e) {
            throw new DataSetProcessingConfigurationException("Failed to read instruments list", e);
        }
        // initialize instruments and supplemental tables specified for each instrument
        for (String instrumentName : instrumentNames) {
            CassiniInstrument instrument = new CassiniInstrument();
            instrument.setName(instrumentName);
            instrument.setTableTypes(new ArrayList<CassiniTableType>());

            // check which table types are configured for the given instrument
            for (CassiniTableType tableType : cassiniTableTypes) {
                String propertyBaseName = instrumentName + "." + tableType.getName();

                String fileNamePattern = configuration.getProperty(propertyBaseName + ".label");

                // it means that instrument provides supplemental table of the given type
                if (fileNamePattern != null) {
                    String dbTableName = configuration.getProperty(propertyBaseName + ".dbName");

                    TableInfo tableContext = new TableInfo(
                            tableType, instrument, fileNamePattern, dbTableName);

                    tables.add(tableContext);
                    instrument.getTableTypes().add(tableType);
                }
            }
            cassiniInstruments.add(instrument);
        }
    }

    /**
     * Stores Cassini regular table data and meta-information into persistence.
     * 
     * @param tableInfo
     *            the {@code TableInfo} instance for regular table
     * 
     * @throws DataSetProcessingException
     *             if failed to read table data or perform some persistence operation
     */
    private void processRegularTable(TableInfo tableInfo) throws DataSetProcessingException {
        Table table = getTableInstanceForRegularTable(tableInfo);

        // if regular table does not exist continue with other tables
        if (table == null) {
            return;
        }

        // get table data file (which contains raw table data)
        File directory = new File(supplementalTablesDirectory, dataSetName);
        File tableFile = new File(directory, table.getName());
        String tableFilePath = tableFile.getPath();

        if (tableFile.isDirectory() || !tableFile.exists()) {
            throw new DataSetProcessingException("Table data file does not exist: " + tableFilePath);
        }

        // store table data
        dataFileReader.readData(tableFilePath, table, -1);
        conversionPersistence.insertDataIntoRegularTable(table);

        // added observation information
        CassiniObservationInfo observationInfo = getRegularTableObservationInfo(table, tableInfo);
        if (observationInfo != null) {
            conversionPersistence.insertCassiniObservationInfo(observationInfo);
        }
    }

    /**
     * Stores inventory table data and meta-information into persistence.
     * 
     * @param tableInfo
     *            the {@code TableInfo} instance for inventory table
     * 
     * @throws DataSetProcessingException
     *             if failed to read table data or perform some persistence operation
     */
    private void processInventoryTable(TableInfo tableInfo) throws DataSetProcessingException {
        String labelFilePath = getLabelFilePath(dataSetName, tableInfo.getFileNamePattern());

        // if inventory table does not exist continue with other tables
        if (labelFilePath == null) {
            return;
        }

        // parse label file
        MetadataFile labelMetadata = metadataFileReader.readMetadataInfo(labelFilePath, null);

        // get table name
        String tableName = null;
        for (Property property : labelMetadata.getProperties()) {
            if (property.getName().equals("^TEXT")) {
                tableName = property.getValues().get(0);
                break;
            }
        }
        if (tableName == null) {
            throw new DataSetProcessingException("Failed to find table name in the label file: " + labelFilePath);
        }

        // get table data file (which contains raw table data)
        File directory = new File(supplementalTablesDirectory, dataSetName);
        File tableFile = new File(directory, tableName);
        String tableFilePath = tableFile.getPath();

        if (tableFile.isDirectory() || !tableFile.exists()) {
            throw new DataSetProcessingException("Table data file does not exist: " + tableFilePath);
        }

        // get properties that should be persisted
        final String PRODUCT_CREATION_TIME = "PRODUCT_CREATION_TIME";
        final String INSTRUMENT_HOST_NAME = "INSTRUMENT_HOST_NAME";
        final String INSTRUMENT_HOST_ID = "INSTRUMENT_HOST_ID";
        final String INSTRUMENT_NAME = "INSTRUMENT_NAME";
        final String INSTRUMENT_ID = "INSTRUMENT_ID";

        Map<String, Property> map = EntityHelper.getProperties(labelMetadata.getProperties(), new String[] {
                PRODUCT_CREATION_TIME,
                INSTRUMENT_HOST_NAME,
                INSTRUMENT_HOST_ID,
                INSTRUMENT_NAME,
                INSTRUMENT_ID });

        CassiniObservationInfo observationInfo = new CassiniObservationInfo();
        observationInfo.setCassiniInstrumentName(tableInfo.getInstrument().getName());

        observationInfo.setProductCreationTime(EntityHelper.getPropertyDateValue(map.get(PRODUCT_CREATION_TIME)));
        observationInfo.setInstrumentHostName(EntityHelper.getPropertyStringValue(map.get(INSTRUMENT_HOST_NAME)));
        observationInfo.setInstrumentHostId(EntityHelper.getPropertyStringValue(map.get(INSTRUMENT_HOST_ID)));
        observationInfo.setInstrumentName(EntityHelper.getPropertyStringValue(map.get(INSTRUMENT_NAME)));
        observationInfo.setInstrumentId(EntityHelper.getPropertyStringValue(map.get(INSTRUMENT_ID)));

        // read table's data and persist table
        List<CassiniObservation> observations = loadInventoryTable(tableFilePath);
        observationInfo.setObservations(observations);
        conversionPersistence.insertCassiniObservationInfo(observationInfo);
    }

    /**
     * Creates Table instance for regular table specified by the given TableInfo instance.
     * 
     * @param tableInfo
     *            the {@code TableInfo} instance
     * 
     * @return the {@code Table} instance for the regular table specified by {@code TableInfo} or null if regular
     *         table's label file does not exist
     * 
     * @throws DataSetProcessingException
     *             if failed to create table
     */
    private Table getTableInstanceForRegularTable(TableInfo tableInfo) throws DataSetProcessingException {
        final String PRODUCT_OBJECT = "PRODUCT";

        String labelFilePath = getLabelFilePath(dataSetName, tableInfo.getFileNamePattern());

        // regular table does not exist so continue with other tables
        if (labelFilePath == null) {
            return null;
        }

        // parse label file
        MetadataFile labelMetadata = metadataFileReader.readMetadataInfo(labelFilePath, null);

        // get product information
        Product product = new Product(PRODUCT_OBJECT);
        product.fromMetadata(labelMetadata);
        product.setName(labelFilePath);

        // get table pointer name
        String tablePointerName = null;
        String tableName = null;
        for (Property property : product.getOtherProperties()) {
            if (Helper.isTablePointer(property.getName())) {
                tablePointerName = property.getName().substring(1); // skip "^" symbol
                tableName = property.getValues().get(0);
                break;
            }
        }
        if (tablePointerName == null) {
            throw new DataSetProcessingException("Failed to find table pointer in the label file: " + labelFilePath);
        }

        // get table metadata and create table instance
        MetadataObject tableMetadata = Helper.getObjectFromProduct(product, tablePointerName);
        if (tableMetadata == null) {
            throw new DataSetProcessingException(String.format("Failed to find OBJECT definition for %s pointer (%s)",
                    tablePointerName, labelFilePath));
        }
        Table table = new Table();
        table.fromMetadata(tableMetadata);
        table.setName(tableName);
        table.setSqlTableName(tableInfo.getDatabaseTableName());

        // set SQL column names
        List<String> columnNamesList = new ArrayList<String>();
        for (Column column : table.getColumns()) {
            columnNamesList.add(Helper.getSqlIdentifier(column.getName()));
        }
        table.setSqlColumnNames(Helper.getUniqueNames(columnNamesList));

        return table;
    }

    /**
     * Gets observation information for the given regular table.
     * 
     * @param table
     *            the table instance
     * @param tableInfo
     *            the table info
     * 
     * @return the {@code CassiniObservationInfo} instance or null if table does not provide observation information
     */
    private CassiniObservationInfo getRegularTableObservationInfo(Table table, TableInfo tableInfo) {
        CassiniObservationInfo observationInfo = null;

        // check if table provides information about observed targets,
        // only specific tables provide such information (which is denoted by corresponding semantics)
        final List<String> TABLES_WITH_OBSERVED_TARGETS = Arrays.asList("MOON", "SATURN");
        boolean hasTargets = false;
        for (String semantics : TABLES_WITH_OBSERVED_TARGETS) {
            if (tableInfo.getTableType().getSemantics().contains(semantics)) {
                hasTargets = true;
                break;
            }
        }
        if (hasTargets) {
            final String FILE_SPECIFICATION_NAME = "FILE_SPECIFICATION_NAME";
            final String RING_OBSERVATION_ID = "RING_OBSERVATION_ID";
            final String TARGET_NAME = "TARGET_NAME";

            int labelFileIndex = -1;
            int ringIdColumnIndex = -1;
            int targetNameColumnIndex = -1;

            // look for predefined columns with target information
            for (int i = 0; i < table.getColumns().size(); i++) {
                String columnName = table.getColumns().get(i).getName();
                if (columnName.equalsIgnoreCase(RING_OBSERVATION_ID)) {
                    ringIdColumnIndex = i;
                } else if (columnName.equalsIgnoreCase(TARGET_NAME)) {
                    targetNameColumnIndex = i;
                } else if (columnName.equalsIgnoreCase(FILE_SPECIFICATION_NAME)) {
                    labelFileIndex = i;
                }
            }
            // retrieve information in case all columns are present
            if (labelFileIndex > -1 && targetNameColumnIndex > -1 && ringIdColumnIndex > -1) {
                List<CassiniObservation> observations = new ArrayList<CassiniObservation>();
                for (Row row : table.getRows()) {
                    String ringId = row.getCells().get(ringIdColumnIndex).getValue();
                    String targetName = row.getCells().get(targetNameColumnIndex).getValue();
                    String labelFile = row.getCells().get(labelFileIndex).getValue();

                    Long rowProductId = labelPath2ProductId.get(labelFile.toLowerCase());
                    if (rowProductId != null) {
                        CassiniObservation observation = new CassiniObservation();
                        observation.setProductId(rowProductId);
                        observation.setRingObservationId(ringId);
                        // NOTE: regular table provides one target per row (in contrast to inventory table)
                        observation.setTargets(Arrays.asList(targetName));
                        observations.add(observation);
                    }
                }
                if (!observations.isEmpty()) {
                    observationInfo = new CassiniObservationInfo();
                    observationInfo.setCassiniInstrumentName(tableInfo.getInstrument().getName());
                    observationInfo.setObservations(observations);
                }
            }
        }
        return observationInfo;
    }

    /**
     * Loads inventory table data.
     * 
     * @param tableFilePath
     *            the absolute path to inventory table
     * @return the list of {@code InventoryTableRow} instances
     * 
     * @throws DataSetProcessingException
     *             if failed to load table data
     */
    private List<CassiniObservation> loadInventoryTable(String tableFilePath) throws DataSetProcessingException {
        List<CassiniObservation> rows = new ArrayList<CassiniObservation>();
        try {
            List<String> lines = Helper.readFileLines(new FileInputStream(tableFilePath));
            int rowCount = 0;
            for (String line : lines) {
                // parse row data
                List<String> values = Helper.parseTableRow(line, rowCount + 1);
                rowCount++;
                // there should be at least 3 columns. Target columns (N >= 3) are optional.
                if (values.size() < 3) {
                    throw new DataSetProcessingException("Invalid inventory table row: " + line
                            + ". At least 3 columns are required");
                }
                // initialize row instance
                CassiniObservation row = new CassiniObservation();
                String labelPath = values.get(1);
                Long productId = labelPath2ProductId.get(labelPath.toLowerCase());
                if (productId == null) {
                    throw new DataSetProcessingException("Can not find product for label: " + labelPath);
                }
                row.setProductId(productId);
                row.setRingObservationId(values.get(2));
                row.setTargets(new ArrayList<String>());
                for (int i = 3; i < values.size(); i++) {
                    row.getTargets().add(values.get(i));
                }
                rows.add(row);
            }
            return rows;
        } catch (IOException e) {
            throw new DataSetProcessingException("Failed to read inventory table data", e);
        }
    }

    /**
     * Helper method that gets absolute file path for the label file specified by the filename pattern. The filename
     * pattern is either a pattern string which contains predefined parameters (surrounded with {}) or just a real
     * file name of the label file.
     * 
     * @param dataSetName
     *            the dataset name
     * @param fileNamePattern
     *            the filename pattern or ordinary filename
     * @return the absolute file path of the label file or null if the given file does not exist
     */
    private String getLabelFilePath(String dataSetName, String fileNamePattern) {
        final String DATASET_PARAM = "{dataset}";
        String labelFileName = fileNamePattern.replace(DATASET_PARAM, dataSetName);

        File directory = new File(supplementalTablesDirectory, dataSetName);
        File labelFile = new File(directory, labelFileName);
        String labelFilePath = labelFile.getPath();

        if (labelFile.isDirectory() || !labelFile.exists()) {
            logger.log(Level.WARN, "Can not find table label file for pattern {0}.", fileNamePattern);
            return null;
        }
        return labelFilePath;
    }
}
