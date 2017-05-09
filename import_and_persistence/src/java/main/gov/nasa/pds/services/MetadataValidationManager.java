/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services;

import gov.nasa.pds.entities.MetadataObject;
import gov.nasa.pds.entities.ValidationReport;

/**
 * Does validations of the metadata as well as corrections.
 *
 * Thread Safety: The implementations should be effectively thread-safe.
 */
public interface MetadataValidationManager {
    /**
     * Loads all validation rules for faster access.
     *
     * @throws DataSetProcessingException
     *             - if there is an error while doing the validation
     */
    void load() throws DataSetProcessingException;

    /**
     * Checks whether the validation rules are already loaded.
     *
     * @return true, if the validation rules are loaded, otherwise false
     */
    boolean isLoaded();

    /**
     * Validates the metadata object.
     *
     * @param metaDataObject
     *            - the metadata object to validate
     * @throws DataSetProcessingException
     *             - if there is an error while doing the validation
     * @return - the validation report, indicating any rule violations
     */
    ValidationReport validateMetadata(MetadataObject metaDataObject) throws DataSetProcessingException;

    /**
     * Corrects the metadata object.
     *
     * @param validationReport
     *            - the report with the violations
     * @param metaDataObject
     *            - the metadata object to correct
     * @throws DataSetProcessingException
     *             - if there is an error while doing the validation
     */
    void correctMetadata(MetadataObject metaDataObject, ValidationReport validationReport)
            throws DataSetProcessingException;
}
