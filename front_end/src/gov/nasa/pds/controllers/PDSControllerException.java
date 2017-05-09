/*
 * Copyright (C) 2012 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.controllers;

/**
 * <p>
 * This is a base class for all custom exceptions to be thrown by all Spring controllers from the module.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is not thread safe because its base class is not thread safe.
 * </p>
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PDSControllerException extends Exception {
    /**
     * Creates a new instance of this exception with the given message.
     *
     * @param message
     *            the detailed error message of this exception.
     */
    public PDSControllerException(String message) {
        super(message);
    }

    /**
     * Creates a new instance of this exception with the given message and cause.
     *
     * @param message
     *            the detailed error message of this exception.
     * @param cause
     *            the inner cause of this exception.
     */
    public PDSControllerException(String message, Throwable cause) {
        super(message, cause);
    }
}
