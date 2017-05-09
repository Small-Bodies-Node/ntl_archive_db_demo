/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import java.util.List;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents an element in a metadata object (or file).
 * 
 * Thread Safety: This class is mutable and not thread safe.
 */
public class Property extends NamedEntity {
    /**
     * Represents the value or values in a property. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     */
    private List<String> values;

    /**
     * Empty constructor.
     */
    public Property() {
    }

    /**
     * Constructor. Create the instance with name.
     * 
     * @param name
     *            - the given name to set.
     */
    public Property(String name) {
        super(name);
    }

    /**
     * Gets the values value.
     * 
     * @return - the values value.
     */
    public List<String> getValues() {
        return values;
    }

    /**
     * Sets the given value to values.
     * 
     * @param values
     *            - the given value to set.
     */
    public void setValues(List<String> values) {
        this.values = values;
    }

    /**
     * Gets the JSONObject instance.
     * 
     * @return - the JSONObject for this instance.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = super.toJSONObject();

        EntityHelper.setArray(object, "values", values);
        return object;
    }
}
