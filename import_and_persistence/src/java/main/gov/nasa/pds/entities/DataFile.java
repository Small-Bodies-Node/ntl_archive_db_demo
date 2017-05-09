/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents a representation of a file in the system. Only one of the path or content will be provided.
 *
 * Thread Safety: This class is mutable and not thread safe.
 */
public class DataFile extends NamedEntity {
    /**
     * Represents the path to the file in the file system. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     */
    private String path;
    /**
     * Represents the content of the file. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     */
    private String content;

    /**
     * Empty constructor
     */
    public DataFile() {
    }

    /**
     * Constructor. Create the instance with name.
     *
     * @param name
     *            - the given name to set.
     */
    public DataFile(String name) {
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
        this.path = (path == null) ? null : path.replace('\\', '/');
    }

    /**
     * Gets the content value.
     *
     * @return - the content value.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the given value to content.
     *
     * @param content
     *            - the given value to set.
     */
    public void setContent(String content) {
        this.content = content;
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
        EntityHelper.setString(object, "content", content);
        return object;
    }
}
