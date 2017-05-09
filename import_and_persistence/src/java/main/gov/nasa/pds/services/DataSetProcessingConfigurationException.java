/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services;

import com.topcoder.util.errorhandling.BaseRuntimeException;
import com.topcoder.util.errorhandling.ExceptionData;

/**
 * This exception signals an issue if the configuration of any class in this application fails for any reason. It
 * extends BaseRuntimeException.
 * 
 * Thread Safety: The class is not thread safe because the base class is not thread safe.
 */
public class DataSetProcessingConfigurationException extends BaseRuntimeException {
    /**
     * The generated value.
     */
    private static final long serialVersionUID = 7836345530737472182L;

    /**
     * Creates a new exception instance with this error message.
     * 
     * @param message
     *            - error message
     */
    public DataSetProcessingConfigurationException(String message) {
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
    public DataSetProcessingConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new exception instance with this error message and any additional data to attach to the exception.
     * 
     * @param message
     *            - error message data
     * @param data
     *            - additional data to attach to the exception
     */
    public DataSetProcessingConfigurationException(String message, ExceptionData data) {
        super(message, data);
    }

    /**
     * Creates a new exception instance with this error message, cause of error, and any additional data to attach to
     * the exception.
     * 
     * @param message
     *            - error message cause
     * @param cause
     *            - cause of error data
     * @param data
     *            - additional data to attach to the exception
     */
    public DataSetProcessingConfigurationException(String message, Throwable cause, ExceptionData data) {
        super(message, cause, data);
    }
}
