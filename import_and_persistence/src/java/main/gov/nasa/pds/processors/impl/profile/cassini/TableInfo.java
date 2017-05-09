/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl.profile.cassini;

/**
 * The {@code TableInfo} class specifies parameters of single Cassini supplemental table.
 * 
 * @author KennyAlive
 * @version 1.0
 */
class TableInfo {
    /**
     * The table type associated with the table.
     */
    private final CassiniTableType tableType;

    /**
     * The instrument associated with the table.
     */
    private final CassiniInstrument instrument;

    /**
     * The filename or filename pattern of the label file of the supplemental table.
     */
    private final String fileNamePattern;

    /**
     * The database table that stores data of this table. Used by regular tables.
     */
    private final String databaseTableName;

    /**
     * Creates new {@code TableProcessingContext} instance.
     * 
     * @param tableType
     *            the table type of the associated table
     * @param instrument
     *            the associated instrument
     * @param fileNamePattern
     *            the filename pattern for the label file
     * @param databaseTableName
     *            the database table name that stores data of this table
     */
    public TableInfo(CassiniTableType tableType, CassiniInstrument instrument, String fileNamePattern,
            String databaseTableName) {
        this.tableType = tableType;
        this.instrument = instrument;
        this.fileNamePattern = fileNamePattern;
        this.databaseTableName = databaseTableName;
    }

    /**
     * Gets the tableType.
     * 
     * @return the tableType
     */
    public CassiniTableType getTableType() {
        return tableType;
    }

    /**
     * Gets the instrument.
     * 
     * @return the instrument
     */
    public CassiniInstrument getInstrument() {
        return instrument;
    }

    /**
     * Gets the fileNamePattern.
     * 
     * @return the fileNamePattern
     */
    public String getFileNamePattern() {
        return fileNamePattern;
    }

    /**
     * Gets the databaseTableName.
     * 
     * @return the databaseTableName
     */
    public String getDatabaseTableName() {
        return databaseTableName;
    }
}
