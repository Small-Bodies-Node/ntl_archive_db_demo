/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services.impl;

import gov.nasa.pds.entities.ElementAlias;
import gov.nasa.pds.entities.MetadataObject;
import gov.nasa.pds.entities.ObjectAlias;
import gov.nasa.pds.entities.TargetType;
import gov.nasa.pds.entities.UnitAlias;
import gov.nasa.pds.services.DataDictionaryImportPersistence;
import gov.nasa.pds.services.DataSetProcessingException;

import java.util.List;

/**
 * <p>
 * The <code>MockDataDictionaryImportPersistence</code> class is a mock implementation of
 * <code>DataDictionaryImportPersistence</code> interface and is used for testing.
 * </p>
 *
 * <strong>Thread Safety:</strong> This class is immutable and thread safe.
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
public class MockDataDictionaryImportPersistence implements DataDictionaryImportPersistence {

    @Override
    public void insertObjectAliases(List<ObjectAlias> objectAliases) throws DataSetProcessingException {
    }

    @Override
    public void insertElementAliases(List<ElementAlias> elementAliases) throws DataSetProcessingException {
    }

    @Override
    public void insertUnitAliases(List<UnitAlias> unitAliases) throws DataSetProcessingException {
    }

    @Override
    public void insertTargetTypes(List<TargetType> targetTypes) throws DataSetProcessingException {
    }

    @Override
    public void insertElementDefinitions(List<MetadataObject> elementDefinitions) throws DataSetProcessingException {
    }

    @Override
    public void insertObjectDefinitions(List<MetadataObject> objectDefinitions) throws DataSetProcessingException {
    }

    @Override
    public void insertValidationRules(List<MetadataObject> objectDefinitions, List<MetadataObject> elementDefinitions)
            throws DataSetProcessingException {
    }

    @Override
    public void initializeTableCounter() throws DataSetProcessingException {
    }
}
