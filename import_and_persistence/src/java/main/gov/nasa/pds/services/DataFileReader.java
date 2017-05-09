/*
 * Copyright (C) 2011-2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services;

import gov.nasa.pds.entities.Table;

/**
 * Reads data from a file in a Table structure.
 * 
 * Thread Safety: The implementations should be effectively thread-safe.
 * 
 * <p>
 * Version 1.1 changes [PDS - Import and Persistence Update - Assembly Contest]:
 * <ol>
 * <li>Removed unneeded product parameter in {@link #readData(String, Table, int) method. 
 * All information to read table data is provided by the table itself.</li>
 * </ol>
 * </p>
 * 
 * @author KennyAlive
 * @version 1.1
 */
public interface DataFileReader {
    /**
     * Opens the file and puts it in the table structure.
     * 
     * @param filename
     *            - the file to open and read
     * @param table
     *            - the Table structure into which to write the data
     * @param offset
     *            - the offset value
     * 
     * @throws DataSetProcessingException
     *             - if there is an error while doing the copy.
     */
    void readData(String filename, Table table, int offset) throws DataSetProcessingException;
}
