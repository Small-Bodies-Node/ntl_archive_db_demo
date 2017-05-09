/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl.profile;

import gov.nasa.pds.services.DataSetProcessingConfigurationException;

import java.io.IOException;
import java.util.Properties;

import com.topcoder.commons.utils.LoggingWrapperUtility;
import com.topcoder.util.log.Log;

/**
 * <p>
 * The {@code BaseProfile} provides basic initialization used by the profile classes. The profile class should extend
 * {@code BaseProfile} in order to use its functionality.
 * </p>
 * 
 * <strong>Thread Safety:</strong> This class is not required to be thread-safe.
 * 
 * @author KennyAlive
 * @version 1.0
 */
public abstract class BaseProfile {
    /**
     * Constant for the class name of this class. Used for logging.
     */
    private static final String CLASS_NAME = BaseProfile.class.getName();

    /**
     * The {@code Log} instance.
     */
    protected final Log logger;

    /**
     * The profile configuration.
     */
    protected final Properties configuration;

    /**
     * Creates {@code BaseProfile} instance. Loads profile's configuration file.
     * 
     * @param logger
     *            the logger instance
     * @param configurationFile
     *            the configuration file on the classpath
     * 
     * @throws DataSetProcessingConfigurationException
     *             if failed to load configuration file
     */
    protected BaseProfile(Log logger, String configurationFile) {
        final String signature = CLASS_NAME + ".BaseProfile(Log logger, String configurationFile)";

        this.logger = logger;
        configuration = new Properties();
        try {
            configuration.load(this.getClass().getClassLoader().getResourceAsStream(configurationFile));
        } catch (IOException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingConfigurationException(
                    "Failed to load profile configuration " + configurationFile, e));
        }
    }
}
