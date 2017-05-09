/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import java.util.List;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents a metadata object.
 *
 * Thread Safety: This class is mutable and not thread safe.
 */
public class MetadataObject extends NamedEntity {
    /**
     * Object's full name. It's either the same as object's name or <some_prefix> + object's name.
     */
    private String fullName;

    /**
     * Represents the immediate metadata objects contained in this object. It is managed with a getter and setter. It
     * may have any value. It is fully mutable.
     */
    private List<MetadataObject> children;

    /**
     * Represents the immediate properties contained in this object. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     */
    private List<Property> properties;

    /**
     * Empty constructor.
     */
    public MetadataObject() {
    }

    /**
     * Constructor. Create the instance with name.
     *
     * @param name
     *            - the given name to set.
     */
    public MetadataObject(String name) {
        super(name);
    }

    /**
     * Gets object's full name
     *
     * @return the full name of the object
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name.
     *
     * @param fullName
     *            the full name to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets the children value.
     *
     * @return - the children value.
     */
    public List<MetadataObject> getChildren() {
        return children;
    }

    /**
     * Sets the given value to children.
     *
     * @param children
     *            - the given value to set.
     */
    public void setChildren(List<MetadataObject> children) {
        this.children = children;
    }

    /**
     * Gets the properties value.
     *
     * @return - the properties value.
     */
    public List<Property> getProperties() {
        return properties;
    }

    /**
     * Sets the given value to properties.
     *
     * @param properties
     *            - the given value to set.
     */
    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    /**
     * Gets the JSONObject instance.
     *
     * @return the JSONObject for this instance
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = super.toJSONObject();

        EntityHelper.setString(object, "fullName", fullName);
        EntityHelper.setArray(object, "children", children);
        EntityHelper.setArray(object, "properties", properties);
        return object;
    }
}
