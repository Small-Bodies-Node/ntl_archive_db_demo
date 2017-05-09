/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import com.topcoder.json.object.JSONObject;

/**
 * Provides a visible version of the entity
 * 
 * Thread Safety: The implementation is not required to thread safe.
 */
public interface Loggable {
    /**
     * Provides a visible version of the entity.
     * 
     * @return - a visible version of the entity
     */
    public String toJSONString();

    /**
     * Gets the JSONObject instance.
     * 
     * @return - the JSONObject for this instance.
     */
    public JSONObject toJSONObject();
}
