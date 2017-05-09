/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl.profile;

import gov.nasa.pds.processors.impl.profile.cassini.CassiniProfile;
import gov.nasa.pds.processors.impl.profile.sbn.SBNProfile;
import gov.nasa.pds.services.ConversionPersistence;
import gov.nasa.pds.services.DataFileReader;
import gov.nasa.pds.services.DataSetProcessingConfigurationException;
import gov.nasa.pds.services.MetadataFileReader;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.topcoder.commons.utils.LoggingWrapperUtility;
import com.topcoder.commons.utils.ValidationUtility;
import com.topcoder.util.log.Log;

/**
 * <p>
 * The {@code ProfileProvider} class provides 'processing profile' related services. It allows to get profile instance
 * of the specific type and it also provides shortcut methods to check if the specific profile is active.
 * </p>
 * 
 * <strong>Thread Safety:</strong> This class is not required to be thread-safe.
 * 
 * @author KennyAlive
 * @version 1.0
 */
public class ProfileProvider implements InitializingBean {
    /**
     * The name of this class, used for logging.
     */
    private static final String CLASS_NAME = ProfileProvider.class.getName();

    /**
     * The name for SBN profile as it is specified in configuration.
     */
    private static final String SBN_PROFILE_TYPE = "sbn";

    /**
     * The name for Cassini profile as it is specified in configuration.
     */
    private static final String CASSINI_PROFILE_TYPE = "cassini";

    /**
     * The {@code Log} instance used by this class. Initialized by Spring. Can not be null.
     */
    @Autowired
    private Log logger;

    /**
     * The metadata reader. Initialized by Spring. Can be null.
     */
    @Autowired(required = false)
    private MetadataFileReader metadataFileReader;

    /**
     * The table data reader. Initialized by Spring. Can be null.
     */
    @Autowired(required = false)
    private DataFileReader dataFileReader;

    /**
     * The persistence service. Initialized by Spring. Can be null.
     */
    @Autowired(required = false)
    private ConversionPersistence conversionPersistence;

    /**
     * The type of the active profile. Initialized by Spring setter dependency injection. Can not be null/empty.
     */
    private String profileType;

    /**
     * The profile configuration file on the classpath. Initialized by Spring setter dependency injection. Can not be
     * null/empty.
     */
    private String profileConfigurationFilepath;

    /**
     * The {@code SBNProfile} instance. Can be null.
     */
    private SBNProfile sbnProfile;

    /**
     * The {@code CassiniProfile} instance. Can be null.
     */
    private CassiniProfile cassiniProfile;

    /**
     * Creates instance of this class.
     */
    public ProfileProvider() {
    }

    /**
     * Sets the profileType.
     * 
     * @param profileType
     *            the profileType to set
     */
    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }

    /**
     * Sets the profileConfigurationFilepath.
     * 
     * @param profileConfigurationFilepath
     *            the profileConfigurationFilepath to set
     */
    public void setProfileConfigurationFilepath(String profileConfigurationFilepath) {
        this.profileConfigurationFilepath = profileConfigurationFilepath;
    }

    /**
     * Checks whether this class was initialized by Spring properly. Initializes active profile based on the injected
     * parameters.
     * 
     * Required parameters:
     * <ul>
     * <li>logger</li>
     * <li>profileType</li>
     * <li>profileConfigurationFilepath</li>
     * </ul>
     * 
     * @throws DataSetProcessingConfigurationException
     *             if the class was not initialized properly
     */
    @Override
    public void afterPropertiesSet() {
        final String signature = CLASS_NAME + ".afterPropertiesSet()";

        ValidationUtility.checkNotNull(logger, "logger", DataSetProcessingConfigurationException.class);
        try {
            ValidationUtility.checkNotNullNorEmptyAfterTrimming(
                    profileType,
                    "profileType",
                    DataSetProcessingConfigurationException.class);

            ValidationUtility.checkNotNullNorEmptyAfterTrimming(
                    profileConfigurationFilepath,
                    "profileConfigurationFilepath",
                    DataSetProcessingConfigurationException.class);

            // create profile
            if (profileType.equalsIgnoreCase(SBN_PROFILE_TYPE)) {
                sbnProfile = new SBNProfile(
                        logger,
                        profileConfigurationFilepath);
            } else if (profileType.equalsIgnoreCase(CASSINI_PROFILE_TYPE)) {
                cassiniProfile = new CassiniProfile(
                        logger,
                        profileConfigurationFilepath,
                        metadataFileReader,
                        dataFileReader,
                        conversionPersistence);
            } else {
                throw new DataSetProcessingConfigurationException("Unknown profile type: " + profileType
                        + ". The following profile types are supported: sbn, cassini");
            }
        } catch (DataSetProcessingConfigurationException e) {
            throw LoggingWrapperUtility.logException(logger, signature, e);
        }
    }

    /**
     * Checks if the SBN profile is active. It's a shortcut method for (getSBNProfile() != null). It's a common
     * situation to make some decision based only on whether particular profile is active.
     * 
     * @return true if the SBN profile active, otherwise false
     */
    public boolean isSBNProfile() {
        return sbnProfile != null;
    }

    /**
     * Gets the SBN profile.
     * 
     * @return the SBN profile (can be null)
     */
    public SBNProfile getSBNProfile() {
        return sbnProfile;
    }

    /**
     * Checks if the Cassini profile is active. It's a shortcut method for (getCassiniProfile() != null). It's a
     * common situation to make some decision based only on whether particular profile is active.
     * 
     * @return true if the Cassini profile active, otherwise false
     */
    public boolean isCassiniProfile() {
        return cassiniProfile != null;
    }

    /**
     * Gets the Cassini profile.
     * 
     * @return the Cassini profile (can be null)
     */
    public CassiniProfile getCassiniProfile() {
        return cassiniProfile;
    }
}
