/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services;

import com.topcoder.util.errorhandling.BaseCriticalException;
import com.topcoder.util.errorhandling.ExceptionData;

/**
 * This exception is the top-level application exception in this application. All other application exceptions in that
 * class will extend it. extends BaseCriticalException.
 * 
 * Thread Safety: The class is not thread safe because the base class is not thread safe.
 */
public class DataSetProcessingException extends BaseCriticalException {
    /**
     * The generated value.
     */
    private static final long serialVersionUID = 3827168732214639644L;

    /**
     * Creates a new exception instance with this error message.
     * 
     * @param message
     *            - error message
     */
    public DataSetProcessingException(String message) {
        super(message);
    }

    /**
     * Creates a new exception instance with this error message and cause of error.
     * 
     * @param message
     *            - error message
     * @param cause
     *            - cause of error
     */
    public DataSetProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new exception instance with this error message and any additional data to attach to the exception.
     * 
     * @param message
     *            - error message
     * @param data
     *            - additional data to attach to the exception
     */
    public DataSetProcessingException(String message, ExceptionData data) {
        super(message, data);
    }

    /**
     * Creates a new exception instance with this error message, cause of error, and any additional data to attach to
     * the exception.
     * 
     * @param message
     *            - error message
     * @param cause
     *            - cause of error
     * @param data
     *            - additional data to attach to the exception
     */
    public DataSetProcessingException(String message, Throwable cause, ExceptionData data) {
        super(message, cause, data);
    }
}
