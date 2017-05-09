/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

/**
 * This enumeration represents the enumeration of possible types of errors encountered during validation.
 * 
 * Thread Safety: This class is immutable and thread safe.
 */
public enum ErrorType {
    /**
     * Represents an error type of an item being missing when it is required.
     */
    MISSING_REQUIRED,
    /**
     * Represents an error type of an item being available when it is invalid.
     */
    INVALID,
    /**
     * Represents an error type of an item being available when it is not allowed.
     */
    NOT_ALLOWED,
    /**
     * Represents an error type of an item being called by soe type of its alias or alternate name.
     */
    ALIAS,
    /**
     * Represents an error type of an item being misspelled. Criteria for reaching this level will be up to the
     * validator implementations.
     */
    MISSPELLED;
}
