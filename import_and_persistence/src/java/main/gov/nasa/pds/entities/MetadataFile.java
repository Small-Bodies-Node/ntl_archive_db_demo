/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

/**
 * This class represents an object representation of all metadata in a file.
 * 
 * Thread Safety: This class is mutable and not thread safe.
 */
public class MetadataFile extends MetadataObject {
    /**
     * Empty constructor
     */
    public MetadataFile() {
    }

    /**
     * Constructor. Create the instance with name.
     * 
     * @param name
     *            - the given name to set.
     */
    public MetadataFile(String name) {
        super(name);
    }
}
