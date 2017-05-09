/*
 * Copyright (C) 2011-2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services.impl;

import gov.nasa.pds.entities.Cell;
import gov.nasa.pds.entities.Column;
import gov.nasa.pds.entities.DataFile;
import gov.nasa.pds.entities.DataSet;
import gov.nasa.pds.entities.Instrument;
import gov.nasa.pds.entities.InstrumentHost;
import gov.nasa.pds.entities.MetadataObject;
import gov.nasa.pds.entities.Mission;
import gov.nasa.pds.entities.Product;
import gov.nasa.pds.entities.Property;
import gov.nasa.pds.entities.Reference;
import gov.nasa.pds.entities.Row;
import gov.nasa.pds.entities.Table;
import gov.nasa.pds.entities.Target;
import gov.nasa.pds.entities.TargetType;
import gov.nasa.pds.entities.Volume;
import gov.nasa.pds.processors.impl.profile.cassini.CassiniObservation;
import gov.nasa.pds.processors.impl.profile.cassini.CassiniObservationInfo;
import gov.nasa.pds.services.ConversionPersistence;
import gov.nasa.pds.services.DataSetProcessingConfigurationException;
import gov.nasa.pds.services.DataSetProcessingException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.topcoder.commons.utils.LoggingWrapperUtility;
import com.topcoder.commons.utils.ParameterCheckUtility;
import com.topcoder.commons.utils.ValidationUtility;
import com.topcoder.util.log.Level;
import com.topcoder.util.log.Log;

/**
 * <p>
 * The persistence service implementation used during the conversion process. Used to insert converted data.
 * </p>
 * 
 * <p>
 * <b>Thread Safety:</b> The implementations are effectively thread-safe.
 * </p>
 * 
 * <p>
 * Version 1.2 changes [PDS - Import and Persistence Update - Assembly Contest]:
 * <ol>
 * <li>Added {@link #insertCassiniObservationInfo(CassiniObservationInfo)} method.</li>
 * </ol>
 * </p>
 * 
 * @author argolite, KennyAlive
 * @version 1.2
 */
public class JDBCConversionPersistence implements ConversionPersistence, InitializingBean {
    /**
     * The name of this class used for logging.
     */
    private static final String CLASS_NAME = JDBCConversionPersistence.class.getName();

    /**
     * The number of records that is cached before insertion into lookup_value_xref table.
     */
    private static final int LOOKUP_VALUE_XREF_INSERT_CACHE_SIZE = 1024;

    /**
     * The number of records that is cached before insertion into product_metadata table.
     */
    private static final int PRODUCT_METADATA_INSERT_CACHE_SIZE = 1024;

    /**
     * The number of records that is cached before insertion into product_file table.
     */
    private static final int PRODUCT_FILE_INSERT_CACHE_SIZE = 1024;

    /**
     * The log instance used for logging.
     */
    private Log logger;

    /**
     * The JdbcTemplate instance used for all DB interaction.
     */
    private JdbcTemplate jdbcTemplate;

    /**
     * The default target type that is used when dataset does not define target type for the particular target.
     */
    private String defaultTargetType;

    /**
     * The directory for storing temporary files. Initialized by the spring setter dependency injection. Can be null
     * (optional parameter).
     */
    private String tempFileDirectory;

    /**
     * This class represents a lookup value cache item.
     */
    private class LookupValueCacheItem {
        public long keywordId;
        public long lookupValueId;

        public LookupValueCacheItem(long keywordId, long lookupValueId) {
            this.keywordId = keywordId;
            this.lookupValueId = lookupValueId;
        }
    }

    /**
     * This class represents a record from lookup_value table.
     */
    private class LookupValueRecord {
        public long keywordId;
        public String value;

        public LookupValueRecord(long keywordId, String value) {
            this.keywordId = keywordId;
            this.value = value;
        }
    }

    /**
     * This class represents a record from lookup_value_xref table.
     */
    private class LookupValueXrefRecord {
        public long parentId;
        public long childId;

        public LookupValueXrefRecord(long parentId, long childId) {
            this.parentId = parentId;
            this.childId = childId;
        }
    }

    /**
     * This class represents a record from product_metadata table.
     */
    private class ProductMetadataRecord {
        public long productId;
        public long lookupValueId;

        public ProductMetadataRecord(long productId, long lookupValueId) {
            this.productId = productId;
            this.lookupValueId = lookupValueId;
        }
    }

    /**
     * This class represents a record from product_file table.
     */
    private class ProductFileRecord {
        public long productId;
        public long dataFileId;

        public ProductFileRecord(long productId, long dataFileId) {
            this.productId = productId;
            this.dataFileId = dataFileId;
        }
    }

    // Caches to speed up database access. They do not cache all the value from the corresponding tables but only the
    // values that were added during current dataset's processing.
    private Map<String, List<LookupValueCacheItem>> lookupValueCache; // lookup_value table
    private Map<Long, List<Long>> lookupValueXrefCache; // lookup_value_xref table
    private Map<String, Long> keywordCache; // keyword table

    // Caches to collect many records before insertion.
    private List<LookupValueXrefRecord> lookupValueXrefInsertCache;
    private List<ProductMetadataRecord> productMetadataInsertCache;
    private List<ProductFileRecord> productFileInsertCache;

    // Other caches
    private Map<Integer, String> parameterizedSqlWith2nValues;

    /**
     * Creates new JDBCConversionPersistence instance.
     */
    public JDBCConversionPersistence() {
        lookupValueCache = new HashMap<String, List<LookupValueCacheItem>>();
        lookupValueXrefCache = new HashMap<Long, List<Long>>();
        keywordCache = new HashMap<String, Long>();
        lookupValueXrefInsertCache = new ArrayList<LookupValueXrefRecord>();
        productMetadataInsertCache = new ArrayList<ProductMetadataRecord>();
        productFileInsertCache = new ArrayList<ProductFileRecord>();
        parameterizedSqlWith2nValues = new HashMap<Integer, String>();
    }

    /**
     * Sets the log instance used for logging.
     * @param logger the log instance used for logging
     */
    public void setLogger(Log logger) {
        this.logger = logger;
    }

    /**
     * Sets the JdbcTemplate instance used for all DB interaction.
     * @param jdbcTemplate the JdbcTemplate instance used for all DB interaction
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Sets the default target type.
     *
     * @param defaultTargetType
     *              the default target type to set.
     */
    public void setDefaultTargetType(String defaultTargetType) {
        if (defaultTargetType != null && defaultTargetType.trim().isEmpty()) {
            this.defaultTargetType = null;
        } else {
            this.defaultTargetType = defaultTargetType;
        }
    }

    /**
     * Sets the tempFileDirectory.
     * 
     * @param tempFileDirectory
     *            the tempFileDirectory to set
     */
    public void setTempFileDirectory(String tempFileDirectory) {
        this.tempFileDirectory = tempFileDirectory;
    }

    /**
     * Checks whether this class was initialized by Spring properly.
     *
     * Required parameters:
     * <ul>
     * <li>logger</li>
     * <li>jdbcTemplate</li>
     * </ul>
     *
     * @throws DataSetProcessingConfigurationException
     *             if logger or jdbcTemplate are null
     */
    @Override
    public void afterPropertiesSet() {
        ValidationUtility.checkNotNull(logger, "logger",
                DataSetProcessingConfigurationException.class);
        ValidationUtility.checkNotNull(jdbcTemplate, "jdbcTemplate",
                DataSetProcessingConfigurationException.class);
    }

    /**
     * Writes cached data to DB from insert caches. Clears all the caches (insert caches and caches used to speed up
     * DB operations).
     */
    @Override
    public void clearCaches() {
        flushLookupValueXrefCache();
        flushProductMetadataCache();
        flushProductFileCache();

        lookupValueCache.clear();
        lookupValueXrefCache.clear();
        keywordCache.clear();
        parameterizedSqlWith2nValues.clear();
    }

    /**
     * Creates the table with a given name and with the given columns. All columns will be of 'varchar' type of size
     * that is determined by the columnSizes parameter.
     * 
     * @param tableFileName
     *            the table file name
     * @param tableLabelPath
     *            the table label file path related to dataset directory
     * @param columnNames
     *            the column names
     * @param columnSizes
     *            the column sizes
     * 
     * @return the name of the created table
     * 
     * @throws DataSetProcessingException
     *             if failed to create the table
     */
    @Override
    public TableInfo createTable(List<String> columnNames, List<Integer> columnSizes)
            throws DataSetProcessingException {
        String signature = CLASS_NAME + ".createTable(List<String> columnNames, List<Integer> columnSizes)";

        try {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "columnNames", "columnSizes" },
                    new Object[] { columnNames, columnSizes });

            // validate parameters
            ParameterCheckUtility.checkNotNull(columnNames, "columnNames");
            ParameterCheckUtility.checkNotNull(columnSizes, "columnSizes");
            if (columnNames.size() != columnSizes.size() || columnNames.isEmpty()) {
                throw new IllegalArgumentException("columnNames and columnSizes should have the same length > 0");
            }

            String tableName = getNextTableName();

            // create DDL statement
            StringBuilder sql = new StringBuilder("create table `");
            sql.append(tableName).append("` (");

            sql.append("`data_table_id` BIGINT, ");
            sql.append("`row_index` INT, ");

            for (int i = 0; i < columnNames.size(); i++) {
                if (i != 0) {
                    sql.append(", ");
                }
                sql.append("`").append(columnNames.get(i)).append("` ");
                sql.append("varchar").append("(").append(columnSizes.get(i)).append(")");
            }
            sql.append(")");

            // execute DDL statement
            jdbcTemplate.execute(sql.toString());
            // register new table
            long tableId = registerTable(tableName);
            LoggingWrapperUtility.logExit(logger, signature, new Object[] { tableName });
            return new TableInfo(tableName, tableId);
        } catch (IllegalArgumentException e) {
            throw LoggingWrapperUtility.logException(logger, signature, e);
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Failed to create table", e));
        }
    }

    /**
     * Gets the name of the next table.
     * 
     * @return the table name
     */
    private String getNextTableName() {
        String selectSql = "select count from table_counter where id = 1";
        int index = jdbcTemplate.queryForInt(selectSql);
        String tableName = "x_table_" + (index + 1);

        index++;
        String updateSql = String.format("update table_counter set count = %d where id = 1", index);
        jdbcTemplate.update(updateSql);
        return tableName;
    }

    /**
     * Registers new dataset table.
     */
    private long registerTable(String tableName) {
        String tableSql = "insert into `table` (table_name) values (?)";
        long tableId = Helper.insert(jdbcTemplate, tableSql, new Object[] { tableName });
        return tableId;
    }

    /**
     * Drops a table with a given name.
     *
     * @param tableName
     *            the name of the table to drop
     *
     * @throws DataSetProcessingException
     *             if failed to drop the table
     */
    @Override
    public void dropTable(String tableName) throws DataSetProcessingException {
        String signature = CLASS_NAME + ".dropTable(String tableName)";
        try {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "tableName" }, new Object[] { tableName });

            ParameterCheckUtility.checkNotNullNorEmptyAfterTrimming(tableName, "tableName");

            jdbcTemplate.execute("drop table `" + tableName + "`");
            LoggingWrapperUtility.logExit(logger, signature, null);
        } catch (IllegalArgumentException e) {
            throw LoggingWrapperUtility.logException(logger, signature, e);
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Failed to drop table: " + tableName, e));
        }
    }

    /**
     * Inserts the given data into the given table into persistence.
     * 
     * @param tableId
     *            defines the table for data inserting
     * @param table
     *            the table instance populated with data to insert
     * 
     * @return the new data table id
     * 
     * @throws DataSetProcessingException
     *             - if there is an error while persisting the data
     */
    @Override
    public long insertDataIntoTable(long tableId, final Table table) throws DataSetProcessingException {
        String signature = CLASS_NAME + ".insertDataIntoTable(long tableId, final Table table)";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "table" }, new Object[] { table });
        }

        File tempFile = null;
        PrintWriter writer = null;
        try {
            long dataTableId = registerDataTable(tableId);

            // Create temp file.
            if (tempFileDirectory != null && !tempFileDirectory.isEmpty()) {
                tempFile = File.createTempFile("table-", null, new File(tempFileDirectory));
            } else {
                tempFile = File.createTempFile("table-", null);
            }

            // Write table data to the file in the format that can be read by 'LOAD DATA INFILE' MySQL statement.
            writer = new PrintWriter(tempFile);
            for (int i = 0; i < table.getRows().size(); i++) {
                Row row = table.getRows().get(i);
                StringBuilder sb = new StringBuilder();

                sb.append("\"");
                sb.append(dataTableId);
                sb.append("\",\"");
                sb.append(i);
                sb.append("\"");

                for (Cell cell : row.getCells()) {
                    sb.append(",");
                    sb.append("\"");
                    sb.append(cell.getValue());
                    sb.append("\"");
                }
                sb.append("\n");
                writer.print(sb.toString());
            }
            writer.close();

            // Store table to the database.
            String sql = String.format(
                    "LOAD DATA LOCAL INFILE '%s' INTO TABLE %s FIELDS TERMINATED BY ',' ENCLOSED BY '\"'",
                    tempFile.getPath().replace('\\', '/'), table.getSqlTableName());

            jdbcTemplate.update(sql);

            LoggingWrapperUtility.logExit(logger, signature, new Object[] { dataTableId });
            return dataTableId;
        } catch (IOException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "I/O error occured", e));
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Data access error occurs", e));
        } finally {
            if (tempFile != null && !tempFile.delete()) {
                tempFile.deleteOnExit();
            }
        }
    }

    /**
     * Inserts data into the database table specified by table#sqlTableName property.
     * 
     * @param table
     *            the table which contains data to insert into the database
     * @throws DataSetProcessingException
     *             if error occurs while persisting the data
     */
    @Override
    public void insertDataIntoRegularTable(final Table table) throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".insertDataIntoRegularTable(Table table)";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "table" }, new Object[] { table });
        }

        // Create sql insert statement for a single row.
        StringBuilder sql = new StringBuilder();
        sql.append("insert into `").append(table.getSqlTableName()).append("` (");

        for (int i = 0; i < table.getSqlColumnNames().size(); i++) {
            if (i != 0) {
                sql.append(",");
            }
            sql.append("`").append(table.getSqlColumnNames().get(i)).append("`");
        }
        sql.append(") values (");
        for (int i = 0; i < table.getColumns().size(); i++) {
            if (i != 0) {
                sql.append(",");
            }
            sql.append("?");
        }
        sql.append(")");

        // Determine null constant value for each column (can be null).
        final List<Double> nullConstants = new ArrayList<Double>();
        for (Column column : table.getColumns()) {
            Double nullConstant = null;
            List<Property> properties = column.getOtherProperties();
            for (Property property : properties) {
                if (property.getName().equalsIgnoreCase("NULL_CONSTANT")) {
                    try {
                        nullConstant = Double.valueOf(property.getValues().get(0));
                    } catch (NumberFormatException e) {
                        // ignore: do not use null constant for this column
                    }
                    break;
                }
            }
            nullConstants.add(nullConstant);
        }

        // Insert rows
        try {
            jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    List<Cell> cells = table.getRows().get(i).getCells();
                    for (int k = 0; k < cells.size(); k++) {
                        String strValue = cells.get(k).getValue();

                        Column column = table.getColumns().get(k);
                        if (column.getDataType().equalsIgnoreCase("character")) {
                            ps.setString(k + 1, strValue);
                        } else if (column.getDataType().equalsIgnoreCase("ascii_real")) {
                            try {
                                Double d = Double.parseDouble(strValue);
                                Double nullConstant = nullConstants.get(k);
                                if (nullConstant != null && (d - nullConstant) < 1e-5) {
                                    ps.setNull(k + 1, Types.DOUBLE);
                                } else {
                                    ps.setDouble(k + 1, d);
                                }
                            } catch (NumberFormatException e) {
                                String message = String.format("Failed to convert %s to double value. " +
                                       "Row %d,  column %d", strValue, i, k); 
                                throw LoggingWrapperUtility.logException(logger, signature,
                                        new SQLException(message, e));
                            }
                        } else {
                            throw LoggingWrapperUtility.logException(logger, signature, 
                                    new SQLException("Unknown column data type: " + column.getDataType()));
                        }
                    }
                }
                @Override
                public int getBatchSize() {
                    return table.getRows().size();
                }
            });
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Data access error occurs", e));
        }
        LoggingWrapperUtility.logExit(logger, signature, null);
    }

    /**
     * Registers data table.
     */
    private long registerDataTable(long tableId) {
        String dataTableSql = "insert into `data_table` (table_id) values (?)";
        long dataTableId = Helper.insert(jdbcTemplate, dataTableSql, new Object[] { tableId });
        return dataTableId;
    }

    /**
     * Inserts the given object into persistence.
     *
     * @param volume the given volume instance
     * @return the id of volume
     * @throws DataSetProcessingException if there is an error while persisting the data
     */
    @Override
    public long insertVolume(Volume volume) throws DataSetProcessingException {
        String signature = CLASS_NAME + ".insertVolume(Volume volume)";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "volume" }, new Object[] { volume });
        }

        try {
            long volumeId = Helper.insert(jdbcTemplate, "insert into volume (name, description, volume_text_id,"
                    + " volume_set_text_id, volume_set_name, volume_series_name) values (?, ?, ?, ?, ?, ?)",
                    new Object[] {volume.getName(), volume.getDescription(), volume.getTextId(),
                    volume.getSetTextId(), volume.getSetName(), volume.getSeriesName()});
            volume.setId(volumeId);
            insertOtherChildren(volume.getOtherChildren(), volumeId, "volume");
            insertImmediateProperties(volume.getOtherProperties(), volumeId, "volume");

            LoggingWrapperUtility.logExit(logger, signature, new Object[] { volumeId });
            return volumeId;
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Data access error occurs", e));
        }
    }

    /**
     * Inserts the given object into persistence.
     *
     * @param reference the given reference instance
     * @return the id of reference
     * @throws DataSetProcessingException if there is an error while persisting the data
     */
    @Override
    public long insertReference(Reference reference) throws DataSetProcessingException {
        String signature = CLASS_NAME + ".insertReference(Reference reference)";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "reference" }, new Object[] { reference });
        }

        try {
            long referenceId =  Helper.insert(jdbcTemplate, "insert into reference (reference_key_text_id, description)"
                    + " values (?, ?)",
                    new Object[] {reference.getKeyTextId(), reference.getDescription()});
            reference.setId(referenceId);

            LoggingWrapperUtility.logExit(logger, signature, new Object[] { referenceId });
            return referenceId;
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Data access error occurs", e));
        }
    }

    /**
     * Inserts the given object into persistence.
     *
     * @param instrumentHost the given instrumentHost instance
     * @return the id of instrumentHost
     * @throws DataSetProcessingException if there is an error while persisting the data
     */
    @Override
    public long insertInstrumentHost(InstrumentHost instrumentHost) throws DataSetProcessingException {
        String signature = CLASS_NAME + ".insertInstrumentHost(InstrumentHost instrumentHost)";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "instrumentHost" }, new Object[] { instrumentHost });
        }
        try {
            long instrumentHostId = Helper.insert(jdbcTemplate,
                    "insert into instrument_host (instrument_host_text_id, name)"
                            + " values (?, ?)",
                            new Object[] {instrumentHost.getTextId(), instrumentHost.getName()});
            instrumentHost.setId(instrumentHostId);
            insertReferences(instrumentHost.getReferences(), instrumentHostId, "instrument_host", signature);
            insertOtherChildren(instrumentHost.getOtherChildren(), instrumentHostId, "instrument_host");

            LoggingWrapperUtility.logExit(logger, signature, new Object[] { instrumentHostId });
            return instrumentHostId;
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Data access error occurs", e));
        }
    }

    /**
     * Inserts the given object into persistence.
     *
     * @param instrument the given instrument instance
     * @return the id of instrument
     * @throws DataSetProcessingException if there is an error while persisting the data
     */
    @Override
    public long insertInstrument(Instrument instrument) throws DataSetProcessingException {
        String signature = CLASS_NAME + ".insertInstrument(Instrument instrument)";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "instrument" }, new Object[] { instrument });
        }
        try {
            long instrumentId = Helper.insert(jdbcTemplate,
                    "insert into instrument (instrument_text_id, name, type, description)"
                            + " values (?, ?, ?, ?)",
                            new Object[] {instrument.getTextId(), instrument.getName(), instrument.getType(),
                    instrument.getDescription()});

            instrument.setId(instrumentId);
            insertReferences(instrument.getReferences(), instrumentId, "instrument", signature);
            insertOtherChildren(instrument.getOtherChildren(), instrumentId, "instrument");

            if (instrument.getHosts() != null) {
                for (InstrumentHost instrumentHost : instrument.getHosts()) {
                    long instrumentHostId = getInstrumentHostId(instrumentHost);
                    jdbcTemplate.update(
                            "insert into instrument_host_instrument (instrument_host_id, instrument_id) values (?, ?)",
                            new Object[] {instrumentHostId, instrumentId});
                }
            }

            LoggingWrapperUtility.logExit(logger, signature, new Object[] { instrumentId });
            return instrumentId;
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Data access error occurs", e));
        }
    }

    /**
     * Inserts the given object into persistence.
     *
     * @param mission the given mission instance
     * @return the id of mission
     * @throws DataSetProcessingException if there is an error while persisting the data
     */
    @Override
    public long insertMission(Mission mission) throws DataSetProcessingException {
        String signature = CLASS_NAME + ".insertMission(Mission mission)";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "mission" }, new Object[] { mission });
        }
        try {
            // check if mission with the given name already registered
            Long missionId = Helper.getObjectIdByName(mission.getName(), "mission", jdbcTemplate);
            if (missionId != null) {
                return missionId;
            }

            // register this mission
            missionId = Helper.insert(jdbcTemplate, "insert into mission (name, start_date, end_date, description)"
                    + " values (?, ?, ?, ?)",
                    new Object[] {mission.getName(), mission.getStartDate(), mission.getEndDate(),
                    mission.getDescription()});

            mission.setId(missionId);
            insertReferences(mission.getReferences(), missionId, "mission", signature);
            insertOtherChildren(mission.getOtherChildren(), missionId, "mission");

            LoggingWrapperUtility.logExit(logger, signature, new Object[] { missionId });
            return missionId;
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Data access error occurs", e));
        }
    }

    /**
     * Inserts the given object into persistence.
     *
     * @param target the given target instance
     * @return the id of target
     * @throws DataSetProcessingException if there is an error while persisting the data
     */
    @Override
    public long insertTarget(Target target) throws DataSetProcessingException {
        String signature = CLASS_NAME + ".insertTarget(Target target)";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "target" }, new Object[] { target });
        }
        try {
            // check if mission with the given name already registered
            Long targetId = Helper.getObjectIdByName(target.getName(), "target", jdbcTemplate);
            if (targetId != null) {
                return targetId;
            }

            // register this target
            targetId =  Helper.insert(jdbcTemplate, "insert into target (name) values (?)",
                    new Object[] {target.getName()});

            target.setId(targetId);
            insertReferences(target.getReferences(), targetId, "target", signature);

            List<TargetType> targetTypes = target.getTypes();

            if (targetTypes == null && defaultTargetType != null) {
                String sql = "select * from target_type where name = ?";
                List<TargetType > result = jdbcTemplate.query(sql, new RowMapper<TargetType>() {
                    @Override
                    public TargetType mapRow(ResultSet rs, int rowNum) throws SQLException {
                        TargetType targetType = new TargetType();
                        targetType.setId(rs.getLong("id"));
                        targetType.setName(rs.getString("name"));
                        return targetType;
                    }
                }, defaultTargetType);

                if (result != null && !result.isEmpty()) {
                    targetTypes = result;
                }
            }

            if (targetTypes != null) {
                for (TargetType targetType : targetTypes) {
                    long targetTypeId = Helper.getTargetTypeId(targetType.getName(), jdbcTemplate);
                    Helper.insert(jdbcTemplate,
                            "insert into target_type_target (target_type_id, target_id) values (?, ?)",
                            new Object[] {targetTypeId, targetId});
                }
            }

            LoggingWrapperUtility.logExit(logger, signature, new Object[] { targetId });
            return targetId;
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Data access error occurs", e));
        }
    }

    /**
     * Inserts the given object into persistence.
     *
     * @param dataSet
     *            the given dataSet instance
     * @return the id of dataSet
     * @throws DataSetProcessingException
     *             if there is an error while persisting the data
     */
    @Override
    public long insertDataSet(DataSet dataSet) throws DataSetProcessingException {
        String signature = CLASS_NAME + ".insertDataSet(DataSet dataSet)";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "dataSet" }, new Object[] { dataSet });
        }
        try {
            long dataSetId =  Helper.insert(jdbcTemplate,
                    "insert into dataset (data_set_text_id, name, start_time, stop_time,"
                            + " description) values (?, ?, ?, ?, ?)",
                            new Object[] {dataSet.getTextId(), dataSet.getName(), dataSet.getStartDate(),
                    dataSet.getStopDate(), dataSet.getDescription()});
            dataSet.setId(dataSetId);

            if (dataSet.getInstruments() != null) {
                for (Instrument instrument : dataSet.getInstruments()) {
                    long instrumentId = getInstrumentId(instrument);
                    Helper.insert(jdbcTemplate, "insert into instrument_catalog (dataset_id, instrument_id)"
                            + " values (?, ?)",
                            new Object[] {dataSetId, instrumentId});
                }
            }

            if (dataSet.getMissions() != null) {
                for (Mission mission : dataSet.getMissions()) {
                    long missionId = getMissionId(mission);
                    Helper.insert(jdbcTemplate, "insert into dataset_mission (dataset_id, mission_id)"
                            + " values (?, ?)",
                            new Object[] {dataSetId, missionId});
                }
            }

            insertOtherChildren(dataSet.getOtherChildren(), dataSetId, "dataset");

            if (dataSet.getRating() != null) {
                Helper.insert(jdbcTemplate, "insert into dataset_rating (rating, dataset_id) values (?, ?)",
                        new Object[] {dataSet.getRating(), dataSetId});
            }

            if (dataSet.getReferences() != null) {
                for (Reference reference : dataSet.getReferences()) {
                    // skip undefined references
                    if (reference.getKeyTextId().equals("N/A")) {
                        continue;
                    }
                    long referenceId = getReferenceId(reference);
                    jdbcTemplate.update(
                            "insert into reference_catalog (dataset_id, reference_id) values (?, ?)",
                            new Object[] {dataSetId, referenceId});
                }
            }

            if (dataSet.getTargets() != null) {
                for (Target target : dataSet.getTargets()) {
                    long targetId = getTargetId(target);
                    String sql = "insert into dataset_target (dataset_id, target_id) values (?, ?)";
                    Helper.insert(jdbcTemplate, sql, new Object[] {dataSetId, targetId});
                }
            }

            if (dataSet.getVolumes() != null) {
                for (Volume volume : dataSet.getVolumes()) {
                    Helper.insert(jdbcTemplate, "insert into dataset_volume (dataset_id, volume_id)"
                            + " values (?, ?)",
                            new Object[] {dataSetId, volume.getId()});
                }
            }

            LoggingWrapperUtility.logExit(logger, signature, new Object[] { dataSetId });
            return dataSetId;
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Data access error occurs", e));
        }
    }

    /**
     * Inserts the given object into persistence.
     *
     * @param product the given product instance
     * @param dataSetId the given dataSetId value
     * @return the id of product
     * @throws DataSetProcessingException if there is an error while persisting the data
     */
    @Override
    public long insertProduct(long dataSetId, Product product) throws DataSetProcessingException {
        String signature = CLASS_NAME + ".insertProduct(long dataSetId, Product product)";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "dataSetId", "product" }, new Object[] { dataSetId, product });
        }
        try {
            long productId = Helper.insert(jdbcTemplate,
                    "insert into product (name, product_text_id, start_time, stop_time,"
                            + " description, record_type, record_byte_size, record_count) "
                            + "values (?, ?, ?, ?, ?, ?, ?, ?)",
                            new Object[] {product.getName(), product.getTextId(), product.getStartTime(),
                    product.getStopTime(), product.getDescription(), product.getRecordType().name(),
                    product.getRecordByteSize(), product.getRecordCount()});
            product.setId(productId);

            Helper.insert(jdbcTemplate, "insert into product_index (dataset_id, data_product_id) values (?, ?)",
                    new Object[] {dataSetId, productId});

            insertOtherChildren(product.getOtherChildren(), productId, "product");
            insertProductImmediateProperties(product.getOtherProperties(), productId);

            LoggingWrapperUtility.logExit(logger, signature, new Object[] { productId });
            return productId;
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Data access error occurs", e));
        }
    }

    /**
     * Inserts the given object into persistence.
     *
     * @param dataFile the given dataFile instance
     * @param productId the given productId value
     * @throws DataSetProcessingException if there is an error while persisting the data
     */
    @Override
    public void insertProductDocument(long productId, DataFile dataFile) throws DataSetProcessingException {
        String signature = CLASS_NAME + ".insertProductDocument(long productId, DataFile dataFile)";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "productId", "dataFile" }, new Object[] { productId, dataFile });
        }
        try {
            long dataFileId = Helper.insert(jdbcTemplate,
                    "insert into data_file (name, path, content) values (?, ?, ?)",
                    new Object[] {dataFile.getName(), dataFile.getPath(), dataFile.getContent()});

            dataFile.setId(dataFileId);
            insertProductFile(productId, dataFileId);

            LoggingWrapperUtility.logExit(logger, signature, null);
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Data access error occurs", e));
        }
    }

    /**
     * Inserts the given object into persistence.
     *
     * @param dataSetId the given dataSetId value
     * @param dataFile the given dataFile instance
     * @return the id of dataset file
     * @throws DataSetProcessingException if there is an error while persisting the data
     */
    @Override
    public long insertDataSetDocument(long dataSetId, DataFile dataFile) throws DataSetProcessingException {
        String signature = CLASS_NAME + ".insertDataSetDocument(long dataSetId, DataFile dataFile)";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "dataSetId", "dataFile" }, new Object[] { dataSetId, dataFile });
        }
        try {
            long dataFileId = Helper.insert(jdbcTemplate,
                    "insert into data_file (name, path, content) values (?, ?, ?)",
                    new Object[] {dataFile.getName(), dataFile.getPath(), dataFile.getContent()});
            dataFile.setId(dataFileId);
            long datasetFileId = Helper.insert(jdbcTemplate,
                    "insert into dataset_file (dataset_id, data_file_id) values (?, ?)",
                    new Object[] {dataSetId, dataFileId});

            LoggingWrapperUtility.logExit(logger, signature, new Object[] { datasetFileId });
            return datasetFileId;
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Data access error occurs", e));
        }
    }

    /**
     * Inserts Cassini observation information.
     * 
     * @param observationInfo
     *            the observation data
     * 
     * @throws IllegalArgumentException
     *             if observationInfo argument is null or observationInfo#observations is null or targets list for
     *             observation is null
     * @throws DataSetProcessingException
     *             if there is an error while persisting the data
     * 
     * @since 1.2
     */
    @Override
    public void insertCassiniObservationInfo(CassiniObservationInfo observationInfo) throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".insertCassiniObservationInfo(CassiniObservationInfo observationInfo)";

        final String INSERT_INSTRUMENT =
                "insert into cassini_instrument (name) values (?)";
        final String INSERT_OBSERVATION_INFO =
                "insert into cassini_observation_info (product_creation_time, instrument_host_name, " +
                        "instrument_host_id, instrument_name, instrument_id) values (?, ?, ?, ?, ?)";
        final String INSERT_OBSERVATION =
                "insert into cassini_observation (ring_observation_id, cassini_observation_info_id) values (?, ?)";
        final String INSERT_OBSERVATION_PRODUCT =
                "insert into cassini_observation_product (cassini_observation_id, product_id) values (?, ?)";
        final String INSERT_INSTRUMENT_OBSERVATION =
                "insert into cassini_instrument_observation (cassini_instrument_id, cassini_observation_id) " +
                        "values (?, ?)";
        final String INSERT_OBSERVATION_TARGET =
                "insert into cassini_observation_target (cassini_observation_id, target_id) values (?, ?)";
        final String SELECT_OBSERVATION = 
                "select id from cassini_observation where ring_observation_id = ?";
        final String SELECT_EXISTING_TARGETS =
                "select target_id from cassini_observation_target where cassini_observation_id = ?";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "observationInfo" }, new Object[] { observationInfo });
        }
        try {
            ParameterCheckUtility.checkNotNull(observationInfo, "observationInfo");
            ParameterCheckUtility.checkNotNull(observationInfo.getObservations(), "observationInfo#observations");
            for (CassiniObservation observation : observationInfo.getObservations()) {
                ParameterCheckUtility.checkNotNull(observation.getTargets(), "observation#targets");
                ParameterCheckUtility.checkNotNull(observation.getRingObservationId(), "observation#ringObservationId");
            }

            // get Cassini instrument id
            Long instrumentId = Helper.getObjectIdByName(observationInfo.getCassiniInstrumentName(),
                    "cassini_instrument", jdbcTemplate);
            if (instrumentId == null) {
                instrumentId = Helper.insert(jdbcTemplate, INSERT_INSTRUMENT,
                        new Object[] { observationInfo.getCassiniInstrumentName() });
            }

            // insert observation properties
            boolean hasProperties = observationInfo.getProductCreationTime() != null
                    || observationInfo.getInstrumentHostName() != null
                    || observationInfo.getInstrumentHostId() != null
                    || observationInfo.getInstrumentName() != null
                    || observationInfo.getInstrumentId() != null;

            Long observationInfoId = null;
            if (hasProperties) {
                observationInfoId = Helper.insert(jdbcTemplate, INSERT_OBSERVATION_INFO, new Object[] {
                        observationInfo.getProductCreationTime(),
                        observationInfo.getInstrumentHostName(),
                        observationInfo.getInstrumentHostId(),
                        observationInfo.getInstrumentName(),
                        observationInfo.getInstrumentId()
                });
            }

            // insert observations
            for (CassiniObservation observation : observationInfo.getObservations()) {
                List<Long> observationTargets = null;

                // get observation id
                List<Map<String, Object>> objectsList = jdbcTemplate.queryForList(SELECT_OBSERVATION,
                        observation.getRingObservationId());
                Long observationId = objectsList.isEmpty() ? null : (Long) objectsList.get(0).get("id");

                // observation was not found, so register it
                if (observationId == null) {
                    observationId = Helper.insert(jdbcTemplate, INSERT_OBSERVATION,
                            new Object[] { observation.getRingObservationId(), observationInfoId });
                    // associate product with this observation
                    Helper.insert(jdbcTemplate, INSERT_OBSERVATION_PRODUCT,
                            new Object[] { observationId, observation.getProductId() });
                    // associate this observation with the instrument
                    Helper.insert(jdbcTemplate, INSERT_INSTRUMENT_OBSERVATION,
                            new Object[] { instrumentId, observationId });
                } else {
                    // get existing targets for the observation
                    observationTargets = jdbcTemplate.queryForList(SELECT_EXISTING_TARGETS, Long.class, observationId);
                }

                // add targets to this observation
                for (String targetName : observation.getTargets()) {
                    long targetId = getTargetId(new Target(targetName));
                    // add target only if it was not added previously
                    if (observationTargets == null || !observationTargets.contains(targetId)) {
                        Helper.insert(jdbcTemplate, INSERT_OBSERVATION_TARGET,
                                new Object[] { observationId, targetId });
                    }
                }
            }
            LoggingWrapperUtility.logExit(logger, signature, null);
        } catch (IllegalArgumentException e) {
            throw LoggingWrapperUtility.logException(logger, signature, e);
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Data access error occurs", e));
        }
    }

    /**
     * Associates the table with the given name to the product with the given ID.
     * 
     * @param productId
     *            the given productId value
     * @param dataTableId
     *            the table id
     * 
     * @throws DataSetProcessingException
     *             if there is an error while persisting the data
     */
    @Override
    public void associateTableToProduct(long productId, long dataTableId) throws DataSetProcessingException {
        String signature = CLASS_NAME + ".associateTableToProduct(long productId, long dataTableId)";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "productId", "dataTableId" }, new Object[] { productId, dataTableId });
        }
        try {
            jdbcTemplate.update("insert into product_table (product_id, data_table_id) values (?, ?)",
                    new Object[] { productId, dataTableId });
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Data access error occurs", e));
        }
        LoggingWrapperUtility.logExit(logger, signature, null);
    }

    /**
     * Associates the table with the given id to the dataset with the given id.
     * 
     * @param dataSetId
     *            - the given datasetId value
     * @param dataTableId
     *            the table id
     * @throws DataSetProcessingException
     *             - if there is an error while persisting the data.
     */
    @Override
    public void associateTableToDataSet(long dataSetId, long dataTableId) throws DataSetProcessingException {
        String signature = CLASS_NAME + ".associateTableToDataSet(long dataSetId, long dataTableId)";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "dataSetId", "dataTableId" }, new Object[] { dataSetId, dataTableId });
        }
        try {
            jdbcTemplate.update("insert into dataset_table (dataset_id, data_table_id) values (?, ?)",
                    new Object[] { dataSetId, dataTableId });
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Data access error occurs", e));
        }
        LoggingWrapperUtility.logExit(logger, signature, null);
    }

    /**
     * Inserts other children and their children and properties.
     *
     * @param objects the meta data object children
     * @param id the id
     * @param type represents the related table name
     */
    private void insertOtherChildren(List<MetadataObject> objects, long id, String type) {
        if (objects == null) {
            return;
        }
        for (MetadataObject object : objects) {
            insertMetadata(object, id, null, type);
        }
    }

    /**
     * Inserts each meta data object and their properties.
     *
     * @param object the meta data object
     * @param id the id
     * @param parentId the id of parent meta data object
     * @param type represents the related table name
     */
    private void insertMetadata(MetadataObject object, long id, Long parentId, String type) {
        Long keywordId = getKeywordId(object.getName());
        long lookupValueId = insertLookupValue(keywordId, null);

        if (parentId == null) {
            if (type.equals("product")) {
                insertProductMetadata(id, lookupValueId);
            } else {
                StringBuilder sql = new StringBuilder();
                sql.append("insert into ").append(type).append("_metadata (").append(type)
                .append("_id, lookup_value_id) values (?, ?)");
                Helper.insert(jdbcTemplate, sql.toString(), new Object[] { id, lookupValueId });
            }
        } else {
            insertLookupValueXref(parentId, lookupValueId);
        }

        insertProperties(object.getProperties(), lookupValueId, type);

        if (object.getChildren() != null) {
            for (MetadataObject child : object.getChildren()) {
                insertMetadata(child, id, lookupValueId, type);
            }
        }
    }

    /**
     * Inserts the properties which are from a meta data object.
     *
     * @param properties the properties
     * @param parentId the id of parent meta data object
     * @param type represents the related table name
     */
    private void insertProperties(List<Property> properties, Long parentId, String type) {
        if (properties == null) {
            return;
        }

        List<LookupValueRecord> lookupValueRecords = new ArrayList<LookupValueRecord>();
        for (Property property : properties) {
            Long keywordId = getKeywordId(property.getName());
            for (String value : property.getValues()) {
                lookupValueRecords.add(new LookupValueRecord(keywordId, value));
            }
        }
        List<Long> lookupValueIds = insertLookupValues(lookupValueRecords);

        int count = 0;
        for (Property property : properties) {
            property.setId(-1);
            for (int k = 0; k < property.getValues().size(); k++) {
                if (property.getId() == -1) {
                    property.setId(lookupValueIds.get(count));
                }
                count++;
            }
        }

        for (Long lookupValueId : lookupValueIds) {
            insertLookupValueXref(parentId, lookupValueId);
        }
    }

    /**
     * Inserts the immediate properties which are from the non meta data object, eg. volume.
     *
     * @param properties the properties
     * @param id the id of the non meta data object, eg. volume
     * @param type represents the related table name
     */
    private void insertImmediateProperties(List<Property> properties, long id, String type) {
        if (properties == null) {
            return;
        }
        long lookupValueId;
        for (Property property : properties) {
            Long keywordId = getKeywordId(property.getName());
            for (String value : property.getValues()) {
                lookupValueId = insertLookupValue(keywordId, value);
                property.setId(lookupValueId);
                StringBuilder sql = new StringBuilder();
                sql.append("insert into ").append(type).append("_metadata (").append(type)
                .append("_id, lookup_value_id) values (?, ?)");
                Helper.insert(jdbcTemplate, sql.toString(), new Object[] {id, lookupValueId});
            }
        }
    }

    /**
     * Inserts the immediate properties of the product object.
     *
     * @param properties
     *            the properties
     * @param productId
     *            the product id
     */
    private void insertProductImmediateProperties(List<Property> properties, long productId) {
        if (properties == null) {
            return;
        }

        List<LookupValueRecord> lookupValueRecords = new ArrayList<LookupValueRecord>();
        for (Property property : properties) {
            Long keywordId = getKeywordId(property.getName());
            for (String value : property.getValues()) {
                lookupValueRecords.add(new LookupValueRecord(keywordId, value));
            }
        }

        List<Long> lookupValueIds = insertLookupValues(lookupValueRecords);

        int count = 0;
        for (Property property : properties) {
            property.setId(-1);
            for (int k = 0; k < property.getValues().size(); k++) {
                if (property.getId() == -1) {
                    property.setId(lookupValueIds.get(count));
                }
                count++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Long lookupValueId : lookupValueIds) {
            sb.append(lookupValueId).append(" ");
        }

        String sql = "insert into product_properties (product_id, properties_ids) values (?, ?)";
        Helper.insert(jdbcTemplate, sql, productId, sb.toString());
    }

    /**
     * Inserts the references for specified type.
     *
     * @param references the references
     * @param id the id
     * @param type represents the related table name
     */
    private void insertReferences(List<Reference> references, long id, String type, String callerSignature)
            throws DataSetProcessingException {
        if (references == null) {
            return;
        }
        for (Reference reference : references) {
            // skip undefined references
            if (reference.getKeyTextId().equals("N/A")) {
                continue;
            }
            long referenceId = getReferenceId(reference);
            StringBuilder sql = new StringBuilder();
            sql.append("insert into ").append(type).append("_reference (").append(type)
            .append("_id, reference_id) values (?, ?)");
            jdbcTemplate.update(
                    sql.toString(),
                    new Object[] {id, referenceId});
        }
    }

    /**
     * Gets id of the target with the given name. If the target with requested name is not found in the database then
     * new target with this name is created.
     *
     * @param target
     *            the target to look for
     *
     * @return the target's id
     *
     * @throws DataSetProcessingException
     *             if an error occurred while accessing the database
     */
    private long getTargetId(Target target) throws DataSetProcessingException {
        Long id = Helper.getObjectIdByName(target.getName(), "target", jdbcTemplate);
        return (id != null) ? id : insertTarget(target);
    }

    /**
     * Gets id of the mission with the given name. If the mission with requested name is not found
     * in the database then new mission with this name is created.
     *
     * @param mission
     *              the mission to look for
     *
     * @return the mission id
     *
     * @throws DataSetProcessingException
     *              if an error occurred while accessing the database
     */
    private long getMissionId(Mission mission) throws DataSetProcessingException {
        Long id = Helper.getObjectIdByName(mission.getName(), "mission", jdbcTemplate);
        if (id != null) {
            return id;
        }
        // create new mission in the database
        if (mission.getStartDate() == null) {
            mission.setStartDate(new Date(0));
        }
        if (mission.getEndDate() == null) {
            mission.setEndDate(new Date(0));
        }
        if (mission.getDescription() == null) {
            mission.setDescription("");
        }
        return insertMission(mission);
    }

    /**
     * Gets id of the instrument host with the given text_id. If the instrument host with requested text_id
     * is not found in the database then new instrument host with this text_id is created.
     *
     * @param instrumentHost
     *              the instrument host to look for
     *
     * @return the instrument host's id
     *
     * @throws DataSetProcessingException
     *              if an error occurred while accessing the database
     */
    private long getInstrumentHostId(InstrumentHost instrumentHost) throws DataSetProcessingException {
        String sql = "select id from instrument_host where instrument_host_text_id = ?";
        List<Map<String, Object>> objectsList = jdbcTemplate.queryForList(sql, instrumentHost.getTextId());

        if (!objectsList.isEmpty()) {
            return (Long)objectsList.get(0).get("id");
        }

        // create new instrument host in the database
        if (instrumentHost.getName() == null) {
            instrumentHost.setName(instrumentHost.getTextId());
        }
        return insertInstrumentHost(instrumentHost);
    }

    /**
     * Gets id of the instrument with the given text_id. If the instrument with requested text_id
     * is not found in the database then new instrument with this text_id is created.
     *
     * @param instrument
     *              the instrument to look for
     *
     * @return the instrument's id
     *
     * @throws DataSetProcessingException
     *              if an error occurred while accessing the database
     */
    private long getInstrumentId(Instrument instrument) throws DataSetProcessingException {
        String sql = "select id from instrument where instrument_text_id = ?";
        List<Map<String, Object>> objectsList = jdbcTemplate.queryForList(sql, instrument.getTextId());

        if (!objectsList.isEmpty()) {
            return (Long)objectsList.get(0).get("id");
        }

        // create new instrument in the database
        if (instrument.getName() == null) {
            instrument.setName(instrument.getTextId());
        }
        if (instrument.getType() == null) {
            instrument.setType("");
        }
        if (instrument.getDescription() == null) {
            instrument.setDescription("");
        }
        return insertInstrument(instrument);
    }

    /**
     * Gets id of the reference with the given text_id. If the reference with requested text_id
     * is not found in the database then new reference with this text_id is created.
     *
     * @param reference
     *              the reference to look for
     *
     * @return the reference's id
     *
     * @throws DataSetProcessingException
     *              if an error occurred while accessing the database
     */
    private long getReferenceId(Reference reference) throws DataSetProcessingException {
        String sql = "select id from reference where reference_key_text_id = ?";
        List<Map<String, Object>> objectsList = jdbcTemplate.queryForList(sql, reference.getKeyTextId());

        if (!objectsList.isEmpty()) {
            return (Long)objectsList.get(0).get("id");
        }

        // create new reference in the database
        if (reference.getDescription() == null) {
            reference.setDescription("<dummy reference>");
        }
        return insertReference(reference);
    }

    /**
     * Inserts new lookup value into persistence. Uses memory cache to skip inserting duplicated values.
     *
     * @param keywordId
     *            the keyword id
     * @param value
     *            the value
     *
     * @return the id of the inserted lookup value
     */
    private long insertLookupValue(long keywordId, String value) {
        List<LookupValueCacheItem> results = lookupValueCache.get(value);
        if (results != null) {
            for (LookupValueCacheItem result : results) {
                if (result.keywordId == keywordId) {
                    return result.lookupValueId;
                }
            }
        }

        if (results == null) {
            results = new ArrayList<LookupValueCacheItem>();
            lookupValueCache.put(value, results);
        }

        long lookupValueId = Helper.insertLookupValue(jdbcTemplate, keywordId, value);
        results.add(new LookupValueCacheItem(keywordId, lookupValueId));
        return lookupValueId;
    }

    /**
     * Inserts multiple lookup values into the persistence. The values that are already stored in the cache are not
     * inserted into the persistence again (but the method returns theirs ids as well).
     *
     * @param lookupValueRecords
     *            the list of lookup values to insert
     *
     * @return the list of ids of the lookup values
     */
    private List<Long> insertLookupValues(List<LookupValueRecord> lookupValueRecords) {
        List<Long> result = Arrays.asList(new Long[lookupValueRecords.size()]);
        List<Integer> newRecordIndices = new ArrayList<Integer>();
        List<List<LookupValueCacheItem>> newRecordCacheItems = new ArrayList<List<LookupValueCacheItem>>();

        // select the records that are not persisted yet (check that they are not in cache)
        for (int i = 0; i < lookupValueRecords.size(); i++) {
            LookupValueRecord record = lookupValueRecords.get(i);
            boolean cached = false;

            List<LookupValueCacheItem> cacheItems = lookupValueCache.get(record.value);

            if (cacheItems != null) {
                for (LookupValueCacheItem cacheItem : cacheItems) {
                    if (cacheItem.keywordId == record.keywordId) {
                        result.set(i, cacheItem.lookupValueId);
                        cached = true;
                        break;
                    }
                }
            }
            if (!cached) {
                newRecordIndices.add(i);

                if (cacheItems == null) {
                    cacheItems = new ArrayList<LookupValueCacheItem>();
                    lookupValueCache.put(record.value, cacheItems);
                }
                newRecordCacheItems.add(cacheItems);
            }
        }

        // insert all new records
        if (!newRecordIndices.isEmpty()) {
            String sql = "insert into lookup_value (keyword_id, value) values "
                    + getParameterizedSqlWith2nValues(newRecordIndices.size());

            List<Object> args = new ArrayList<Object>();
            for (int i = 0; i < newRecordIndices.size(); i++) {
                int index = newRecordIndices.get(i);
                LookupValueRecord record = lookupValueRecords.get(index);
                args.add(Long.valueOf(record.keywordId));
                args.add(record.value);
            }

            List<Long> newRecordIds = Helper.insertMultipleRecords(jdbcTemplate, sql, args.toArray());

            for (int i = 0; i < newRecordIds.size(); i++) {
                int index = newRecordIndices.get(i);
                long lookupValueId = newRecordIds.get(i);
                result.set(index, lookupValueId);

                // update cache
                LookupValueRecord record = lookupValueRecords.get(index);
                List<LookupValueCacheItem> cacheItems = newRecordCacheItems.get(i);
                cacheItems.add(new LookupValueCacheItem(record.keywordId, lookupValueId));
            }
        }

        return result;
    }

    /**
     * Writes cached lookup_value_xref records and clears the cache.
     */
    private void flushLookupValueXrefCache() {
        if (!lookupValueXrefInsertCache.isEmpty()) {
            Object[] args = new Object[lookupValueXrefInsertCache.size() * 2];

            String sql = "insert into lookup_value_xref (parent_id, child_id) values "
                    + getParameterizedSqlWith2nValues(lookupValueXrefInsertCache.size());

            for (int i = 0; i < lookupValueXrefInsertCache.size(); i++) {
                LookupValueXrefRecord record = lookupValueXrefInsertCache.get(i);
                args[2 * i + 0] = Long.valueOf(record.parentId);
                args[2 * i + 1] = Long.valueOf(record.childId);
            }
            jdbcTemplate.update(sql, args);
            lookupValueXrefInsertCache.clear();
        }
    }

    /**
     * Adds new lookup_value_xref record (in most cases record won't be persisted immediately, it happens when insert
     * cache is large enough or when #clearCaches method is called).
     *
     * @param parentId
     *            the parent id
     * @param childId
     *            the child id
     */
    private void insertLookupValueXref(long parentId, long childId) {
        List<Long> cacheItems = lookupValueXrefCache.get(childId);
        if (cacheItems != null) {
            for (Long cachedParentId : cacheItems) {
                if (cachedParentId.longValue() == parentId) {
                    return;
                }
            }
        }
        if (cacheItems == null) {
            cacheItems = new ArrayList<Long>();
            lookupValueXrefCache.put(childId, cacheItems);
        }
        cacheItems.add(parentId);

        lookupValueXrefInsertCache.add(new LookupValueXrefRecord(parentId, childId));
        if (lookupValueXrefInsertCache.size() >= LOOKUP_VALUE_XREF_INSERT_CACHE_SIZE) {
            flushLookupValueXrefCache();
        }
    }

    private void flushProductMetadataCache() {
        if (!productMetadataInsertCache.isEmpty()) {
            Object[] args = new Object[productMetadataInsertCache.size() * 2];

            String sql = "insert into product_metadata (product_id, lookup_value_id) values "
                    + getParameterizedSqlWith2nValues(productMetadataInsertCache.size());

            for (int i = 0; i < productMetadataInsertCache.size(); i++) {
                ProductMetadataRecord record = productMetadataInsertCache.get(i);
                args[2 * i + 0] = Long.valueOf(record.productId);
                args[2 * i + 1] = Long.valueOf(record.lookupValueId);
            }
            jdbcTemplate.update(sql, args);
            productMetadataInsertCache.clear();
        }
    }

    private void insertProductMetadata(long productId, long lookupValueId) {
        productMetadataInsertCache.add(new ProductMetadataRecord(productId, lookupValueId));
        if (productMetadataInsertCache.size() >= PRODUCT_METADATA_INSERT_CACHE_SIZE) {
            flushProductMetadataCache();
        }
    }

    private void flushProductFileCache() {
        if (!productFileInsertCache.isEmpty()) {
            Object[] args = new Object[productFileInsertCache.size() * 2];

            String sql = "insert into product_file (product_id, data_file_id) values "
                    + getParameterizedSqlWith2nValues(productFileInsertCache.size());

            for (int i = 0; i < productFileInsertCache.size(); i++) {
                ProductFileRecord record = productFileInsertCache.get(i);
                args[2 * i + 0] = Long.valueOf(record.productId);
                args[2 * i + 1] = Long.valueOf(record.dataFileId);
            }
            jdbcTemplate.update(sql, args);
            productFileInsertCache.clear();
        }
    }

    private void insertProductFile(long productId, long dataFileId) {
        productFileInsertCache.add(new ProductFileRecord(productId, dataFileId));
        if (productFileInsertCache.size() >= PRODUCT_FILE_INSERT_CACHE_SIZE) {
            flushProductFileCache();
        }
    }

    /**
     * Get the keyword id by its name.
     *
     * @param keyword
     *            the keyword name
     *
     * @return the keyword id
     */
    private long getKeywordId(String keyword) {
        Long keywordId = keywordCache.get(keyword);
        if (keywordId != null) {
            return keywordId;
        }

        keywordId = Helper.getKeywordId(keyword, jdbcTemplate);
        keywordCache.put(keyword, keywordId);
        return keywordId;
    }

    /**
     * Creates parameterized sql pattern with '(?, ?)' sections.
     *
     * @param n
     *            the number of sections in the parameterized sql pattern
     *
     * @return the resulted parameterized sql pattern
     */
    private String getParameterizedSqlWith2nValues(int n) {
        String sql = parameterizedSqlWith2nValues.get(Integer.valueOf(n));
        if (sql == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (i != 0) {
                    sb.append(",");
                }
                sb.append("(?,?)");
            }
            sql = sb.toString();
            parameterizedSqlWith2nValues.put(n, sql);
        }
        return sql;
    }
}
