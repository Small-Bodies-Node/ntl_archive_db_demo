/*
 * Copyright (C) 2011-2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services;

import gov.nasa.pds.entities.DataFile;
import gov.nasa.pds.entities.DataSet;
import gov.nasa.pds.entities.Instrument;
import gov.nasa.pds.entities.InstrumentHost;
import gov.nasa.pds.entities.Mission;
import gov.nasa.pds.entities.Product;
import gov.nasa.pds.entities.Reference;
import gov.nasa.pds.entities.Table;
import gov.nasa.pds.entities.Target;
import gov.nasa.pds.entities.Volume;
import gov.nasa.pds.processors.impl.profile.cassini.CassiniObservationInfo;

import java.util.List;

/**
 * The persistence service used during the conversion process. Used to insert converted data.
 * 
 * Thread Safety: The implementations should be effectively thread-safe.
 * 
 * <p>
 * Version 1.1 changes [PDS - Import and Persistence Update - Assembly Contest]:
 * <ol>
 * <li>Added {@link #insertCassiniObservationInfo(CassiniObservationInfo)} method.</li>
 * </ol>
 * </p>
 * 
 * @author TCSASSEMBLER
 * @version 1.1
 */
public interface ConversionPersistence {
    /**
     * Information about newly created table.
     */
    public static class TableInfo {
        /**
         * The SQL table name.
         */
        private final String sqlTableName;
        /**
         * The database table identifier.
         */
        private final long tableId;
        /**
         * Creates an instance of {@code TableInfo}.
         */
        public TableInfo(String sqlTableName, long tableId) {
            this.sqlTableName = sqlTableName;
            this.tableId = tableId;
        }
        /**
         * Gets the SQL table name.
         */
        public String getSQLTableName() {
            return sqlTableName;
        }
        /**
         * Gets the database table identifier.
         */
        public long getTableId() {
            return tableId;
        }
    }

    /**
     * Writes cached data to DB from insert caches. Clears all the caches (insert caches and caches used to speed up
     * DB operations).
     */
    void clearCaches();

    /**
     * Creates the table with the given columns. All columns will be of 'varchar' type of size that is determined by
     * the columnSizes parameter.
     * 
     * @param columnNames
     *            the column names
     * @param columnSizes
     *            the column sizes
     * 
     * @return the information about newly created table
     * 
     * @throws DataSetProcessingException
     *             if failed to create the table
     */
    TableInfo createTable(List<String> columnNames, List<Integer> columnSizes) throws DataSetProcessingException;

    /**
     * Drops a table with a given name.
     *
     * @param tableName
     *            the name of the table to drop
     *
     * @throws DataSetProcessingException
     *             if failed to drop the table
     */
    void dropTable(String tableName) throws DataSetProcessingException;

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
     *             if there is an error while persisting the data
     */
    long insertDataIntoTable(long tableId, Table table) throws DataSetProcessingException;

    /**
     * Inserts data into the database table specified by table#sqlTableName property.
     * 
     * @param table
     *            the table which contains data to insert into the database
     * @throws DataSetProcessingException
     *             if error occurs while persisting the data
     */
    void insertDataIntoRegularTable(Table table) throws DataSetProcessingException;

    /**
     * Inserts the given object into persistence.
     *
     * @param volume
     *          - the given volume instance.
     *
     * @return - the id of volume.
     *
     * @throws DataSetProcessingException
     *         - if there is an error while persisting the data
     */
    long insertVolume(Volume volume) throws DataSetProcessingException;

    /**
     * Inserts the given object into persistence.
     *
     * @param reference
     *            - the given reference instance.
     * @throws DataSetProcessingException
     *             - if there is an error while persisting the data
     * @return - the id of reference.
     */
    long insertReference(Reference reference) throws DataSetProcessingException;

    /**
     * Inserts the given object into persistence.
     *
     * @param instrumentHost
     *            - the given instrumentHost instance.
     * @throws DataSetProcessingException
     *             - if there is an error while persisting the data
     * @return - the id of instrumentHost
     */
    long insertInstrumentHost(InstrumentHost instrumentHost) throws DataSetProcessingException;

    /**
     * Inserts the given object into persistence.
     *
     * @param instrument
     *            - the given instrument instance.
     * @throws DataSetProcessingException
     *             - if there is an error while persisting the data
     * @return - the id of instrument.
     */
    long insertInstrument(Instrument instrument) throws DataSetProcessingException;

    /**
     * Inserts the given object into persistence.
     *
     * @param mission
     *            - the given mission instance.
     * @throws DataSetProcessingException
     *             - if there is an error while persisting the data
     * @return - the id of mission
     */
    long insertMission(Mission mission) throws DataSetProcessingException;

    /**
     * Inserts the given object into persistence.
     *
     * @param target
     *            - the given target instance.
     * @throws DataSetProcessingException
     *             - if there is an error while persisting the data
     * @return - the id of target
     */
    long insertTarget(Target target) throws DataSetProcessingException;

    /**
     * Inserts the given object into persistence.
     *
     * @param dataSet
     *            - the given dataSet instance.
     * @throws DataSetProcessingException
     *             - if there is an error while persisting the data
     * @return - the id of dataSet.
     */
    long insertDataSet(DataSet dataSet) throws DataSetProcessingException;

    /**
     * Inserts the given object into persistence.
     *
     * @param product
     *            - the given product instance.
     * @param dataSetId
     *            - the given dataSetId value.
     * @throws DataSetProcessingException
     *             - if there is an error while persisting the data.
     * @return - the id of product.
     */
    long insertProduct(long dataSetId, Product product) throws DataSetProcessingException;

    /**
     * Inserts the given object into persistence.
     *
     * @param dataFile
     *            - the given dataFile instance.
     * @param productId
     *            - the given productId value.
     * @throws DataSetProcessingException
     *             - if there is an error while persisting the data.
     */
    void insertProductDocument(long productId, DataFile dataFile) throws DataSetProcessingException;

    /**
     * Inserts the given object into persistence.
     *
     * @param dataSetId
     *            - the given dataSetId value.
     * @param dataFile
     *            - the given dataFile instance.
     * @throws DataSetProcessingException
     *             - if there is an error while persisting the data.
     * @return - the id of dataFile
     */
    long insertDataSetDocument(long dataSetId, DataFile dataFile) throws DataSetProcessingException;

    /**
     * Inserts Cassini observation information.
     * 
     * @param observationInfo
     *            the observation data
     * 
     * @throws DataSetProcessingException
     *             if there is an error while persisting the data
     * 
     * @since 1.2
     */
    void insertCassiniObservationInfo(CassiniObservationInfo observationInfo) throws DataSetProcessingException;

    /**
     * Associates the table with the given id to the product with the given id.
     * 
     * @param productId
     *            - the given productId value.
     * @param dataTableId
     *            the table id
     * @throws DataSetProcessingException
     *             - if there is an error while persisting the data.
     */
    void associateTableToProduct(long productId, long dataTableId) throws DataSetProcessingException;

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
    void associateTableToDataSet(long dataSetId, long dataTableId) throws DataSetProcessingException;
}
