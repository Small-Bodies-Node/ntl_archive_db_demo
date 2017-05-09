/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

/**
 * This enumeration represents the enumeration of possible types of RecordType.
 * 
 * Thread Safety: This class is immutable and thread safe.
 */
public enum RecordType {
    /**
     * Represents FIXED_LENGTH type.
     */
    FIXED_LENGTH, /**
     * Represents STREAM type.
     */
    STREAM, /**
     * Represents VARIABLE_LENGTH type.
     */
    VARIABLE_LENGTH
}
