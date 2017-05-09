/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl.profile.sbn;

import gov.nasa.pds.processors.impl.profile.BaseProfile;
import gov.nasa.pds.services.DataSetProcessingConfigurationException;

import com.topcoder.util.log.Log;

/**
 * <p>
 * The {@code SBNProfile} class provides configuration and functionality specific to Small Bodies Node (SBN) datasets.
 * It does not implement some specific interface, anything that could be considered as SBN specific should be exposed
 * via this class. This class extends {@code BaseProfile} which provides basic initialization services.
 * </p>
 * 
 * <strong>Thread Safety:</strong> This class is not required to be thread-safe.
 * 
 * @author KennyAlive
 * @version 1.0
 */
public class SBNProfile extends BaseProfile {
    /**
     * Creates {@code SBNProfile} instance.
     * 
     * @param logger
     *            the logger instance
     * @param configurationFilepath
     *            the configuration file on the classpath
     * 
     * @throws DataSetProcessingConfigurationException
     *             if failed to configure this instance
     */
    public SBNProfile(Log logger, String configurationFilepath) {
        super(logger, configurationFilepath);
    }
}
