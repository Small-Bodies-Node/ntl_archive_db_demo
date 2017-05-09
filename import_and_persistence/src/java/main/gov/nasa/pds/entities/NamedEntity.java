/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import com.topcoder.json.object.JSONObject;

/**
 * This is the base class for all entities that have a name.
 * 
 * Thread Safety: This class is mutable and not thread safe.
 */
public abstract class NamedEntity extends IdentifiableEntity {
    /**
     * Represents the name assigned to this entity. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     */
    private String name;

    /**
     * Empty constructor.
     */
    protected NamedEntity() {
    }

    /**
     * Constructor. Create the instance with name.
     * 
     * @param name
     *            - the given name to set.
     */
    protected NamedEntity(String name) {
        this.name = name;
    }

    /**
     * Determines whether the specified Object is equal to the current Object.
     * 
     * @param obj
     *            - The Object to compare with the current Object
     * @return - the compared result
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        NamedEntity other = (NamedEntity) obj;
        return this.name.equals(other.name);
    }

    /**
     * Serves as a hash function for a particular type.
     * 
     * @return- hash code for the current Object.
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Gets the name value.
     * 
     * @return - the name value.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the given value to name.
     * 
     * @param name
     *            - the given value to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the JSONObject instance.
     * 
     * @return - the JSONObject for this instance.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = super.toJSONObject();
        EntityHelper.setString(object, "name", name);
        return object;
    }
}
