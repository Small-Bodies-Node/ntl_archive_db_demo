/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents a reference to a help file in the file system.
 * 
 * Thread Safety: This class is mutable and not thread safe.
 */
public class HelpFile extends NamedEntity {
    /**
     * Represents the path. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private String path;

    /**
     * Empty constructor
     */
    public HelpFile() {
    }

    /**
     * Constructor. Create the instance with name.
     * 
     * @param name
     *            - the given name to set.
     */
    public HelpFile(String name) {
        super(name);
    }

    /**
     * Gets the path value.
     * 
     * @return - the path value.
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the given value to path.
     * 
     * @param path
     *            - the given value to set.
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Gets the JSONObject instance.
     * 
     * @return - the JSONObject for this instance.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = super.toJSONObject();
        EntityHelper.setString(object, "path", path);
        return object;
    }
}
