/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services.impl;

import gov.nasa.pds.entities.MetadataObject;
import gov.nasa.pds.entities.ValidationReport;
import gov.nasa.pds.services.DataSetProcessingException;
import gov.nasa.pds.services.MetadataValidationManager;

/**
 * <p>
 * The <code>MockMetadataValidationManager</code> class is a mock implementation of
 * <code>MetadataValidationManager</code> interface and is used for testing.
 * </p>
 *
 * <strong>Thread Safety:</strong> This class is immutable and thread safe.
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
public class MockMetadataValidationManager implements MetadataValidationManager {
    @Override
    public void load() throws DataSetProcessingException {
        // Empty
    }

    @Override
    public boolean isLoaded() {
        return true;
    }

    @Override
    public ValidationReport validateMetadata(MetadataObject metaDataObject) throws DataSetProcessingException {
        ValidationReport report = new ValidationReport();
        report.setValid(true); // mock implementation always says that metadata is valid
        return report;
    }

    @Override
    public void correctMetadata(MetadataObject metaDataObject, ValidationReport validationReport)
        throws DataSetProcessingException {
    }
}
