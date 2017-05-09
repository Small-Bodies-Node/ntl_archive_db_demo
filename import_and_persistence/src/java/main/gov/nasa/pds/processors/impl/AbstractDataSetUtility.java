/*
 * Copyright (C) 2012 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl;

import com.topcoder.util.log.Log;

/**
 * <p>
 * The abstract {@code AbstractDataSetUtility} class is intended to be the base class for dataset utilities
 * implementations. It provides default implementations of the setter methods.
 * </p>
 *
 * <p>
 * <strong>Thread Safety:</strong> This class is not thread safe.
 * </p>
 *
 * @author KennyAlive
 * @version 1.0
 */
public abstract class AbstractDataSetUtility implements DataSetUtility {
    /**
     * The logger instance. Can not be null. Should be initialized using corresponding setter before running the
     * utility.
     */
    private Log logger;

    /**
     * Constructs new {@code AbstractDataSetUtility} instance.
     */
    protected AbstractDataSetUtility() {
        // Empty
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLogger(Log logger) {
        this.logger = logger;
    }

    /**
     * Gets the logger.
     * 
     * @return the logger instance
     */
    protected Log getLogger() {
        return logger;
    }
}
