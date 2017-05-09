/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents object alias.
 * 
 * Thread Safety: This class is mutable and not thread safe.
 */
public class ObjectAlias extends IdentifiableEntity {
    /**
     * Represents the alias name. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private String alias;
    /**
     * Represents the full name of the alias. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     */
    private String fullName;

    /**
     * Empty constructor.
     */
    public ObjectAlias() {
    }

    /**
     * Gets the alias value.
     * 
     * @return - the alias value.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the given value to alias.
     * 
     * @param alias
     *            - the given value to set.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Gets the fullName value.
     * 
     * @return - the fullName value.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the given value to fullName.
     * 
     * @param fullName
     *            - the given value to set.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets the JSONObject instance.
     * 
     * @return - the JSONObject for this instance.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = super.toJSONObject();
        EntityHelper.setString(object, "alias", alias);
        EntityHelper.setString(object, "fullName", fullName);
        return object;
    }
}
