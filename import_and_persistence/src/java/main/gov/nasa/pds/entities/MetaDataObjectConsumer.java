/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

/**
 * Populates the consumer with the data in the object.
 * 
 * Thread Safety: The implementation is not required to thread safe.
 */
public interface MetaDataObjectConsumer {
    /**
     * Populates the consumer with the data in the object.
     * 
     * @param metaDataObject
     *            - the metadata object to consume
     */
    public void fromMetadata(MetadataObject metaDataObject);
}
