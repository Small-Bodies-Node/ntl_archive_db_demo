/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import com.topcoder.json.object.JSONObject;

/**
 * This is the base class for all entities that have an identification number.
 * 
 * Thread Safety: This class is mutable and not thread safe.
 */
public abstract class IdentifiableEntity implements Loggable {
    /**
     * Represents the primary identifier of the entity. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     */
    private long id;

    /**
     * Empty constructor.
     */
    protected IdentifiableEntity() {
    }

    /**
     * Determines whether the specified Object is equal to the current Object.
     * 
     * @param obj
     *            - The Object to compare with the current Object return - true if the specified Object is equal to the
     *            current Object; otherwise, false.
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
        IdentifiableEntity other = (IdentifiableEntity) obj;
        return this.id == other.id;
    }

    /**
     * Serves as a hash function for a particular type.
     * 
     * @return - hash code for the current Object.
     */
    @Override
    public int hashCode() {
        return (int) id;
    }

    /**
     * Gets the id value.
     * 
     * @return - the id value.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the given value to id.
     * 
     * @param id
     *            - the given value to set.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Provides a visible version of the entity.
     * 
     * @return - a visible version of the entity
     */

    @Override
    public String toJSONString() {
        return toJSONObject().toJSONString();
    }

    /**
     * Gets the JSONObject instance.
     * 
     * @return - the JSONObject for this instance.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        EntityHelper.setLong(object, "id", id);
        return object;
    }
}
