/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents a cell of data.
 * 
 * Thread Safety: This class is mutable and not thread safe.
 */
public class Cell extends IdentifiableEntity {
    /**
     * Represents the column. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private Column column;
    /**
     * Represents the value. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private String value;
    
    /**
     * 
     */
    private byte[] binaryValue;

    /**
     * Empty constructor.
     */
    public Cell() {
        super();
    }

    /**
     * Gets the column value.
     * 
     * @return - the column value.
     */
    public Column getColumn() {
        return column;
    }

    /**
     * Sets the given value to column.
     * 
     * @param column
     *            - the given value to set.
     */
    public void setColumn(Column column) {
        this.column = column;
    }

    /**
     * Gets the value.
     * 
     * @return - the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the given value.
     * 
     * @param value
     *            - the given value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the binary value.
     * 
     * @return - the binary value.
     */
    public byte[] getBinaryValue() {
        return binaryValue;
    }

    /**
     * Sets the given binary value.
     * 
     * @param binaryValue
     *            - the given binary value to set.
     */
    public void setBinaryValue(byte[] binaryValue) {
        this.binaryValue = binaryValue;
    }

    /**
     * Gets the JSONObject instance.
     * 
     * @return - the JSONObject for this instance.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = super.toJSONObject();
        EntityHelper.setNestedObject(object, "column", column);
        EntityHelper.setString(object, "value", value);
        return object;
    }
}
