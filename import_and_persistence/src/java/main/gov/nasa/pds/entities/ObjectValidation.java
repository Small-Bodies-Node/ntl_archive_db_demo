/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import java.util.List;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents an object's validation rules.
 * 
 * Thread Safety: This class is mutable and not thread safe.
 */
public class ObjectValidation extends NamedEntity {
    /**
     * Represents the names of objects that are required to be in this object. It is managed with a getter and setter.
     * It may have any value. It is fully mutable.
     */
    private List<String> requiredObjects;
    /**
     * Represents the names of objects that are optional to be in this object. It is managed with a getter and setter.
     * It may have any value. It is fully mutable.
     */
    private List<String> optionalObjects;
    /**
     * Represents the names of properties that are required to be in this object. It is managed with a getter and
     * setter. It may have any value. It is fully mutable.
     */
    private List<String> requiredElements;
    /**
     * Represents the names of properties that are accepted to be in this object. It is managed with a getter and
     * setter. It may have any value. It is fully mutable.
     */
    private List<String> optionalElements;
    /**
     * Represents the flag of globallyAllowableElements. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     */
    private boolean globallyAllowableElements;

    /**
     * Empty constructor.
     */
    public ObjectValidation() {
    }

    /**
     * Constructor. Create the instance with name.
     * 
     * @param name
     *            - the given name to set.
     */
    public ObjectValidation(String name) {
        super(name);
    }

    /**
     * Gets the requiredObjects value.
     * 
     * @return - the requiredObjects value.
     */
    public List<String> getRequiredObjects() {
        return requiredObjects;
    }

    /**
     * Sets the given value to requiredObjects.
     * 
     * @param requiredObjects
     *            - the given value to set.
     */
    public void setRequiredObjects(List<String> requiredObjects) {
        this.requiredObjects = requiredObjects;
    }

    /**
     * Gets the optionalObjects value.
     * 
     * @return - the optionalObjects value.
     */
    public List<String> getOptionalObjects() {
        return optionalObjects;
    }

    /**
     * Sets the given value to optionalObjects.
     * 
     * @param optionalObjects
     *            - the given value to set.
     */
    public void setOptionalObjects(List<String> optionalObjects) {
        this.optionalObjects = optionalObjects;
    }

    /**
     * Gets the requiredElements value.
     * 
     * @return - the requiredElements value.
     */
    public List<String> getRequiredElements() {
        return requiredElements;
    }

    /**
     * Sets the given value to requiredElements.
     * 
     * @param requiredElements
     *            - the given value to set.
     */
    public void setRequiredElements(List<String> requiredElements) {
        this.requiredElements = requiredElements;
    }

    /**
     * Gets the optionalElements value.
     * 
     * @return - the optionalElements value.
     */
    public List<String> getOptionalElements() {
        return optionalElements;
    }

    /**
     * Sets the given value to optionalElements.
     * 
     * @param optionalElements
     *            - the given value to set.
     */
    public void setOptionalElements(List<String> optionalElements) {
        this.optionalElements = optionalElements;
    }

    /**
     * Gets the globallyAllowableElements value.
     * 
     * @return - the globallyAllowableElements value.
     */
    public boolean isGloballyAllowableElements() {
        return globallyAllowableElements;
    }

    /**
     * Sets the given value to globallyAllowableElements.
     * 
     * @param globallyAllowableElements
     *            - the given value to set.
     */
    public void setGloballyAllowableElements(boolean globallyAllowableElements) {
        this.globallyAllowableElements = globallyAllowableElements;
    }

    /**
     * Gets the JSONObject instance.
     * 
     * @return - the JSONObject for this instance.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = super.toJSONObject();

        EntityHelper.setArray(object, "requiredObjects", requiredObjects);
        EntityHelper.setArray(object, "optionalObjects", optionalObjects);
        EntityHelper.setArray(object, "requiredElements", requiredElements);
        EntityHelper.setArray(object, "optionalElements", optionalElements);

        EntityHelper.setBoolean(object, "globallyAllowableElements", globallyAllowableElements);
        return object;
    }
}
