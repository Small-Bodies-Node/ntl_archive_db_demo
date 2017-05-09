/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents the element alias.
 * 
 * Thread Safety: This class is mutable and not thread safe.
 */
public class ElementAlias extends ObjectAlias {
    /**
     * Represents the alternate name of the element. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     */
    private String anotherName;

    /**
     * Empty constructor
     */
    public ElementAlias() {
    }

    /**
     * Gets the anotherName value.
     * 
     * @return - the anotherName value.
     */
    public String getAnotherName() {
        return anotherName;
    }

    /**
     * Sets the given value to anotherName.
     * 
     * @param anotherName
     *            - the given value to set.
     */
    public void setAnotherName(String anotherName) {
        this.anotherName = anotherName;
    }

    /**
     * Gets the JSONObject instance.
     * 
     * @return - the JSONObject for this instance.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = super.toJSONObject();
        EntityHelper.setString(object, "anotherName", anotherName);
        return object;
    }
}
