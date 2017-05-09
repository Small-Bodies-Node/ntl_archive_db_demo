/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import java.util.List;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents an element's validation rules.
 * 
 * Thread Safety: This class is mutable and not thread safe.
 */
public class ElementValidation extends NamedEntity {
    /**
     * Represents the minimum length an element value can have, if set. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     */
    private Integer minimumLength;

    /**
     * Represents the maximum length an element value can have, if set. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     */
    private Integer maximumLength;
    /**
     * Represents the allowed values an element value can have, if set. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     */
    private List<String> allowedValues;
    /**
     * Represents the minimum an element value can have, if set. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     */
    private Float minimum;
    /**
     * Represents the maximum an element value can have, if set. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     */
    private Float maximum;
    /**
     * Represents the dataType value. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     */
    private String dataType;

    /**
     * Empty constructor
     */
    public ElementValidation() {
    }

    /**
     * Constructor. Create the instance with name.
     * 
     * @param name
     *            - the given name to set.
     */
    public ElementValidation(String name) {
        super(name);
    }

    /**
     * Gets the minimumLength value.
     * 
     * @return - the minimumLength value.
     */
    public Integer getMinimumLength() {
        return minimumLength;
    }

    /**
     * Sets the given value to minimumLength.
     * 
     * @param minimumLength
     *            - the given value to set.
     */
    public void setMinimumLength(Integer minimumLength) {
        this.minimumLength = minimumLength;
    }

    /**
     * Gets the maximumLength value.
     * 
     * @return - the maximumLength value.
     */
    public Integer getMaximumLength() {
        return maximumLength;
    }

    /**
     * Sets the given value to maximumLength.
     * 
     * @param maximumLength
     *            - the given value to set.
     */
    public void setMaximumLength(Integer maximumLength) {
        this.maximumLength = maximumLength;
    }

    /**
     * Gets the allowedValues value.
     * 
     * @return - the allowedValues value.
     */
    public List<String> getAllowedValues() {
        return allowedValues;
    }

    /**
     * Sets the given value to allowedValues.
     * 
     * @param allowedValues
     *            - the given value to set.
     */
    public void setAllowedValues(List<String> allowedValues) {
        this.allowedValues = allowedValues;
    }

    /**
     * Gets the minimum value.
     * 
     * @return - the minimum value.
     */
    public Float getMinimum() {
        return minimum;
    }

    /**
     * Sets the given value to minimum.
     * 
     * @param minimum
     *            - the given value to set.
     */
    public void setMinimum(Float minimum) {
        this.minimum = minimum;
    }

    /**
     * Gets the maximum value.
     * 
     * @return - the maximum value.
     */
    public Float getMaximum() {
        return maximum;
    }

    /**
     * Sets the given value to maximum.
     * 
     * @param maximum
     *            - the given value to set.
     */
    public void setMaximum(Float maximum) {
        this.maximum = maximum;
    }

    /**
     * Gets the dataType value.
     * 
     * @return - the dataType value.
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * Sets the given value to dataType.
     * 
     * @param dataType
     *            - the given value to set.
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * Gets the JSONObject instance.
     * 
     * @return - the JSONObject for this instance.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = super.toJSONObject();
        EntityHelper.setInt(object, "minimumLength", minimumLength);
        EntityHelper.setInt(object, "maximumLength", maximumLength);
        EntityHelper.setArray(object, "allowedValues", allowedValues);
        EntityHelper.setFloat(object, "minimum", minimum);
        EntityHelper.setFloat(object, "maximum", maximum);
        EntityHelper.setString(object, "dataType", dataType);
        return object;
    }
}
