/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents a table view of the data set data.
 *
 * Thread Safety: This class is mutable and not thread safe.
 */
public class Table extends NamedEntity implements MetaDataObjectConsumer {
    /**
     * Constant for "ROWS" String.
     */
    private static final String ROWS = "ROWS";
    /**
     * Constant for "COLUMN" String.
     */
    private static final String COLUMN = "COLUMN";

    /**
     * Constant for "NAME" String.
     */
    private static final String NAME = "NAME";

    /**
     * Constant for "INTERCHANGE_FORMAT" String.
     */
    private static final String INTERCHANGE_FORMAT = "INTERCHANGE_FORMAT";

    /**
     * Constant for "ROW_BYTES" String.
     */
    private static final String ROW_BYTES = "ROW_BYTES";

    /**
     * Constant for "TABLE_STORAGE_TYPE" String.
     */
    private static final String TABLE_STORAGE_TYPE = "TABLE_STORAGE_TYPE";
    /**
     * Represents the columns. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private List<Column> columns;
    /**
     * Represents the rows. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private List<Row> rows;
    /**
     * Represents the rowCount. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private int rowCount;
    /**
     * Represents the structure of other properties of this entity. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     */
    private List<Property> otherProperties;
    /**
     * Represents the rowByteSize of this entity. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     */
    private Integer rowByteSize;
    /**
     * Represents the tableType of this entity. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     */
    private TableType tableType;
    /**
     * Represents the format of this entity. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     */
    private InterchangeFormat format;

    /**
     * The name of this table in the database.
     */
    private String sqlTableName;

    /**
     * The name of table columns in the database.
     */
    private List<String> sqlColumnNames;

    /**
     * Empty constructor.
     */
    public Table() {
        rows = new ArrayList<Row>();
    }

    /**
     * An empty row.
     *
     * @return - a new row.
     */
    public Row getEmptyRow() {
        return new Row();
    }

    /**
     * Add the given row to list.
     *
     * @param row
     *            - the given row to add.
     */
    public void addRow(Row row) {
        if (rows == null) {
            rows = new ArrayList<Row>();
        }
        rows.add(row);
    }

    /**
     * Populates the instance with given metaDataObject.
     *
     * @param metaDataObject
     *            - the given object to handle.
     */
    @Override
    public void fromMetadata(MetadataObject metaDataObject) {
        if (metaDataObject == null) {
            return;
        }

        List<Property> properties = metaDataObject.getProperties();
        List<MetadataObject> children = metaDataObject.getChildren();
        Map<String, Property> map = EntityHelper.getProperties(properties, new String[] { ROWS, NAME,
                INTERCHANGE_FORMAT, ROW_BYTES, TABLE_STORAGE_TYPE });

        this.rowCount = EntityHelper.getPropertyIntValue(map.get(ROWS));

        this.columns = EntityHelper.createColumnsList(COLUMN, children);

        setName(EntityHelper.getPropertyStringValue(map.get(NAME)));
        rowByteSize = EntityHelper.getPropertyIntValue(map.get(ROW_BYTES));
        tableType = EntityHelper.getPropertyTableTypeValue(map.get(TABLE_STORAGE_TYPE));
        format = EntityHelper.getPropertyInterchangeFormatValue(map.get(INTERCHANGE_FORMAT));

        this.otherProperties = EntityHelper.removeUsedProperties(properties, map);
    }

    /**
     * Gets the columns value.
     *
     * @return - the columns value.
     */
    public List<Column> getColumns() {
        return columns;
    }

    /**
     * Sets the given value to columns.
     *
     * @param columns
     *            - the given value to set.
     */
    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    /**
     * Gets the rows value.
     *
     * @return - the rows value.
     */
    public List<Row> getRows() {
        return rows;
    }

    /**
     * Sets the given value to rows.
     *
     * @param rows
     *            - the given value to set.
     */
    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    /**
     * Gets the rowCount value.
     *
     * @return - the rowCount value.
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * Sets the given value to rowCount.
     *
     * @param rowCount
     *            - the given value to set.
     */
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    /**
     * Gets the otherProperties value.
     *
     * @return - the otherProperties value.
     */
    public List<Property> getOtherProperties() {
        return otherProperties;
    }

    /**
     * Sets the given value to otherProperties.
     *
     * @param otherProperties
     *            - the given value to set.
     */
    public void setOtherProperties(List<Property> otherProperties) {
        this.otherProperties = otherProperties;
    }

    /**
     * Gets the rowByteSize value.
     *
     * @return - the rowByteSize value.
     */
    public Integer getRowByteSize() {
        return rowByteSize;
    }

    /**
     * Sets the given value to rowByteSize.
     *
     * @param rowByteSize
     *            - the given value to set.
     */
    public void setRowByteSize(Integer rowByteSize) {
        this.rowByteSize = rowByteSize;
    }

    /**
     * Gets the tableType value.
     *
     * @return - the tableType value.
     */
    public TableType getTableType() {
        return tableType;
    }

    /**
     * Sets the given value to tableType.
     *
     * @param tableType
     *            - the given value to set.
     */
    public void setTableType(TableType tableType) {
        this.tableType = tableType;
    }

    /**
     * Gets the format value.
     *
     * @return - the format value.
     */
    public InterchangeFormat getFormat() {
        return format;
    }

    /**
     * Sets the given value to format.
     *
     * @param format
     *            - the given value to set.
     */
    public void setFormat(InterchangeFormat format) {
        this.format = format;
    }

    /**
     * Gets the sql table name.
     * 
     * @return the sql table name
     */
    public String getSqlTableName() {
        return sqlTableName;
    }

    /**
     * Sets the sql table name.
     * 
     * @param sqlTableName
     *            the sql table name to set
     */
    public void setSqlTableName(String sqlTableName) {
        this.sqlTableName = sqlTableName;
    }

    /**
     * Gets the sql column names.
     * 
     * @return the sqlColumnNames
     */
    public List<String> getSqlColumnNames() {
        return sqlColumnNames;
    }

    /**
     * Sets the sql column names.
     * 
     * @param sqlColumnNames
     *            the sqlColumnNames to set
     */
    public void setSqlColumnNames(List<String> sqlColumnNames) {
        this.sqlColumnNames = sqlColumnNames;
    }

    /**
     * Gets the JSONObject instance.
     *
     * @return - the JSONObject for this instance.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = super.toJSONObject();

        EntityHelper.setArray(object, "columns", columns);

        EntityHelper.setArray(object, "rows", rows);

        EntityHelper.setInt(object, "rowCount", rowCount);

        EntityHelper.setArray(object, "otherProperties", otherProperties);
        EntityHelper.setInt(object, "rowByteSize", rowByteSize);
        EntityHelper.setString(object, "tableType", tableType != null ? tableType.toString() : "");
        EntityHelper.setString(object, "format", format != null ? format.toString() : "");
        EntityHelper.setString(object, "sqlTableName", sqlTableName);

        return object;
    }
}
