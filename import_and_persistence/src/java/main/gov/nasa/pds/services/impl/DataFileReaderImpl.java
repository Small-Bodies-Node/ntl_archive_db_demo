/*
 * Copyright (C) 2011-2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services.impl;

import gov.nasa.pds.entities.Cell;
import gov.nasa.pds.entities.Column;
import gov.nasa.pds.entities.Row;
import gov.nasa.pds.entities.Table;
import gov.nasa.pds.services.DataFileReader;
import gov.nasa.pds.services.DataSetProcessingConfigurationException;
import gov.nasa.pds.services.DataSetProcessingException;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;

import com.topcoder.commons.utils.LoggingWrapperUtility;
import com.topcoder.commons.utils.ValidationUtility;
import com.topcoder.util.log.Log;

/**
 * <p>
 * The <code>DataFileReaderImpl</code> class implements <code>DataFileReader</code> interface by providing
 * implementation of <code>readData</code> method.
 * </p>
 *
 * <strong>Thread Safety:</strong> This class is mutable since it provides public setter for the logger. But it
 * doesn't change its state and is thread safe when the following conditions are met: this class is initialized by
 * Spring right after construction and its parameters are never changed after that, all entities passed to this class
 * are used by the caller in thread safe manner (accessed from a single thread only).
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
public class DataFileReaderImpl implements DataFileReader, InitializingBean {
    /**
     * Constant for the class name of this class. Used for logging.
     */
    private static final String CLASS_NAME = DataFileReaderImpl.class.getName();

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
     * Creates an instance of <code>DataFileReaderImpl</code>
     */
    public DataFileReaderImpl() {
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
     * Checks whether this class was initialized by Spring properly.
     *
     * Required parameters:
     * <ul>
     * <li>logger</li>
     * </ul>
     *
     * @throws DataSetProcessingConfigurationException
     *            if the class was not initialized properly
     */
    @Override
    public void afterPropertiesSet() {
        ValidationUtility.checkNotNull(logger, "logger", DataSetProcessingConfigurationException.class);
    }

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
     *              if there is an error while doing the copy
     */
    @Override
    public void readData(String filename, Table table, int offset) throws DataSetProcessingException {
        String signature = CLASS_NAME + ".readData(Product product, String filename, Table table, int offset)";
        LoggingWrapperUtility.logEntrance(logger, signature,
                new String[] { "filename", "table", "offset" },
                new Object[] { filename, table.toJSONString(), offset });

        try {
            // do no make difference between text and binary tables since every table (both ASCII and BINARY) gives
            // enough information about binary table structure (record/row sizes, cell offset/size in bytes),
            // so we can do processing in a unified way.
            InputStream stream = new BufferedInputStream(new FileInputStream(filename));

            try {
                // if offset is specified then skip it
                if (offset > 0) {
                    if (stream.skip(offset) != offset) {
                        String message = String.format("Failed to set start offset (%d) in '%s'", offset, filename);
                        throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                                message));
                    }
                }

                // read all table rows
                int rowByteSize = table.getRowByteSize();
                byte[] buffer = new byte[rowByteSize];
                for (int i = 0; i < table.getRowCount(); i++) {
                    // read next row
                    int n = stream.read(buffer, 0, rowByteSize);
                    if (n != rowByteSize) {
                        stream.close();
                        String message = String.format("Failed to read row %d from '%s'", i + 1, filename);
                        throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                                message));
                    }

                    // get row data
                    List<Cell> cells = getRowData(buffer, table.getColumns());

                    // initialize row with new data.
                    // if out of existing rows, create a new one and insert back into the table
                    if (table.getRows().size() < i + 1) {
                        table.getRows().add(new Row());
                    }
                    Row row = table.getRows().get(i);
                    row.setCells(cells);
                }
            } finally {
                stream.close();
            }
        } catch (IOException e) {
            String message = String.format("Failed to read table data from '%s'", filename);
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(message, e));
        }
        LoggingWrapperUtility.logExit(logger, signature, null);
    }

    /**
     * Retrieves the cells data from the given table row.
     *
     * @param rowBytes
     *          the bytes of the table row
     * @param columns
     *          the columns definitions
     *
     * @return the list of Cell objects initialized with extracted data
     */
    private List<Cell> getRowData(byte[] rowBytes, List<Column> columns) {
        List<Cell> cells = new ArrayList<Cell>();
        for (Column column : columns) {
            // extract bytes for current column
            int from = column.getStartPosition() - 1; // startPosition is 1-bases
            int to = from + column.getSize();
            byte[] binaryValue = Arrays.copyOfRange(rowBytes, from, to);
            // create and initialize new cell
            Cell cell = new Cell();
            cell.setColumn(column);
            if (column.isBitColumn()) {
                cell.setBinaryValue(binaryValue);
            } else {
                // get string from ascii bytes and trim it
                String value = new String(binaryValue).trim();
                if (!value.isEmpty()) { // if there are quotes remove them and again trim the result
                    int beginIndex = 0;
                    if (value.charAt(0) == '"') {
                        beginIndex = 1;
                    }
                    int endIndex = value.length();
                    if (value.charAt(endIndex - 1) == '"') {
                        endIndex--;
                    }
                    if ((beginIndex != 0 || endIndex != value.length()) && endIndex > beginIndex) {
                        value = value.substring(beginIndex, endIndex).trim();
                    }
                }
                cell.setValue(value);
            }
            cells.add(cell);
        }
        return cells;
    }
}
