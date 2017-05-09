/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl;

import gov.nasa.pds.entities.Column;
import gov.nasa.pds.entities.MetadataFile;
import gov.nasa.pds.entities.MetadataObject;
import gov.nasa.pds.entities.Product;
import gov.nasa.pds.entities.Property;
import gov.nasa.pds.entities.Table;
import gov.nasa.pds.services.DataSetProcessingException;
import gov.nasa.pds.services.MetadataFileReader;
import gov.nasa.pds.services.impl.MetadataFileReaderFastImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.topcoder.util.log.Level;

/**
 * <p>
 * The {@code UtilityTableStructure} class represents a utility that:
 * <ul>
 * <li>generates database 'CREATE TABLE' statement for the table specified by the given label file</li>
 * <li>java code with table information that is used during runtime</li>
 * </ul>
 * </p>
 * 
 * <p>
 * <strong>Thread Safety:</strong> This class is effectively thread safe
 * </p>
 * 
 * @author KennyAlive
 * @version 1.0
 */
public class UtilityTableStructure extends AbstractDataSetUtility {
    /**
     * The metadata reader. Initialized in constructor.
     */
    private final MetadataFileReader metadataFileReader;

    /**
     * Constructs new {@code UtilityTableStructure} instance.
     */
    public UtilityTableStructure() {
        metadataFileReader = new MetadataFileReaderFastImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "tableStructure";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run(List<DataSetInfo> dataSets, List<String> args, Map<String, String> options) {
        if (dataSets.size() > 1) {
            getLogger().log(Level.WARN, "Only the first dataset {0} is used to analize table structure",
                    dataSets.get(0).getDirectoryName());
        }

        // retrieve arguments
        String labelFile;
        if (args.size() > 0) {
            labelFile = args.get(0);
        } else {
            getLogger().log(Level.WARN, "Missing required argument: label file path relative to dataset directory");
            logUsage();
            return;
        }
        String tableName = "myTable";
        if (args.size() > 1) {
            tableName = args.get(1);
        }

        ((MetadataFileReaderFastImpl) metadataFileReader).setLogger(getLogger());

        // parse label file
        MetadataFile labelMetadata;
        String path = new File(dataSets.get(0).getPath(), labelFile).getPath();
        try {
            labelMetadata = metadataFileReader.readMetadataInfo(path, null);
        } catch (DataSetProcessingException e) {
            getLogger().log(Level.ERROR, "Failed to parse label file {0]. Error message: {1}", path, e.getMessage());
            return;
        }

        // get table instance
        Table table = getTableFromLabelMetadata(labelMetadata);
        if (table == null) {
            return;
        }

        writeTableSQLFile(tableName, table.getColumns());
    }

    /**
     * Creates a table object using provided metadata.
     * 
     * @param labelMetadata
     *            the metadata to read table information from
     * @return the table instance or null (with log message) if failed to create table instance
     */
    private Table getTableFromLabelMetadata(MetadataObject labelMetadata) {
        final String PRODUCT_OBJECT = "PRODUCT";

        // at first create product object
        Product product = new Product(PRODUCT_OBJECT);
        product.fromMetadata(labelMetadata);

        // get table pointer name
        String tablePointerName = null;
        for (Property property : product.getOtherProperties()) {
            if (Helper.isTablePointer(property.getName())) {
                tablePointerName = property.getName().substring(1); // skip "^" symbol
                break;
            }
        }
        if (tablePointerName == null) {
            getLogger().log(Level.ERROR, "The provided label file does not specify a table pointer " +
                    "(i.e. does not describe a table)");
            return null;
        }

        // get table metadata and create table instance
        MetadataObject tableMetadata = Helper.getObjectFromProduct(product, tablePointerName);
        if (tableMetadata == null) {
            getLogger().log(Level.ERROR, "Failed to find OBJECT definition for {0} pointer", tablePointerName);
            return null;
        }
        Table table = new Table();
        table.fromMetadata(tableMetadata);
        return table;
    }

    /**
     * Creates CREATE TABLE statement for the table with the given structure and writes it to table.sql file.
     * 
     * @param tableName
     *            the table name
     * @param columns
     *            the list of columns
     */
    private void writeTableSQLFile(String tableName, List<Column> columns) {
        List<String> columnNames = getColumnNames(columns);
        List<String> types = getSQLColumnTypes(columns);

        if (types == null) {
            return;
        }

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File("table.sql"));
            writer.println("-- -----------------------------------------------------");
            writer.println("-- Table `" + tableName + "`");
            writer.println("-- -----------------------------------------------------");
            writer.println("CREATE TABLE IF NOT EXISTS `" + tableName + "` (");
            writer.println("  `id` BIGINT NOT NULL AUTO_INCREMENT ,");

            for (int i = 0; i < columns.size(); i++) {
                String name = columnNames.get(i);
                String type = types.get(i);

                String nullFlag = " NOT NULL";
                for (Property property : columns.get(i).getOtherProperties()) {
                    if (property.getName().equalsIgnoreCase("NULL_CONSTANT")) {
                        nullFlag = "";
                        break;
                    }
                }

                writer.println("  `" + name + "` " + type + nullFlag + " ,");
            }
            writer.println("  PRIMARY KEY (`id`) )");
            writer.println("ENGINE = InnoDB;");
        } catch (FileNotFoundException e) {
            getLogger().log(Level.ERROR, "Failed to create table.sql file");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * Gets the SQL names of the columns.
     * 
     * @param columns
     *            the columns list
     * @return the SQL column names
     */
    private List<String> getColumnNames(List<Column> columns) {
        List<String> columnNames = new ArrayList<String>();
        for (Column column : columns) {
            columnNames.add(Helper.getSqlIdentifier(column.getName()));
        }
        return columnNames;
    }

    /**
     * Gets the SQL types of the columns.
     * 
     * @param columns
     *            the columns list
     * @return the SQL column types
     */
    private List<String> getSQLColumnTypes(List<Column> columns) {
        List<String> types = new ArrayList<String>();
        for (Column column : columns) {
            if (column.getDataType().equalsIgnoreCase("character")) {
                types.add("VARCHAR(" + column.getSize() + ")");
            } else if (column.getDataType().equalsIgnoreCase("ascii_real")) {
                types.add("DOUBLE");
            } else {
                getLogger().log(Level.ERROR, "Unknown column data type: {0}", column.getDataType());
                return null;
            }
        }
        return types;
    }

    /**
     * Logs the arguments usage of this utility.
     */
    private void logUsage() {
        getLogger().log(Level.INFO, "tableStructure utility arguments: [label_file_path]");
    }
}
