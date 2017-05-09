/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services;

import gov.nasa.pds.entities.ElementAlias;
import gov.nasa.pds.entities.MetadataObject;
import gov.nasa.pds.entities.ObjectAlias;
import gov.nasa.pds.entities.TargetType;
import gov.nasa.pds.entities.UnitAlias;

import java.util.List;

/**
 * This interface defines the contract for inserting dictionary data into the persistence.
 * 
 * Thread Safety: The implementations should be effectively thread-safe.
 */
public interface DataDictionaryImportPersistence {
    /**
     * Initializes tables counter.
     * 
     * @throws DataSetProcessingException
     *             - if there is an error while persisting the data.
     */
    void initializeTableCounter() throws DataSetProcessingException;

    /**
     * Inserts the given object aliases into persistence.
     * 
     * @param objectAliases
     *            - the object aliases to insert.
     * @throws DataSetProcessingException
     *             - if there is an error while persisting the data.
     */
    void insertObjectAliases(List<ObjectAlias> objectAliases) throws DataSetProcessingException;

    /**
     * Inserts the given element aliases into persistence.
     * 
     * @param elementAliases
     *            - the element aliases to insert.
     * @throws DataSetProcessingException
     *             - if there is an error while persisting the data.
     */
    void insertElementAliases(List<ElementAlias> elementAliases) throws DataSetProcessingException;

    /**
     * Inserts the given unit aliases into persistence.
     * 
     * @param unitAliases
     *            - the unit aliases to insert.
     * @throws DataSetProcessingException
     *             - if there is an error while persisting the data.
     */
    void insertUnitAliases(List<UnitAlias> unitAliases) throws DataSetProcessingException;

    /**
     * Inserts the given target types into persistence.
     * 
     * @param targetTypes
     *            - the target types to insert.
     * @throws DataSetProcessingException
     *             - if there is an error while persisting the data.
     */
    void insertTargetTypes(List<TargetType> targetTypes) throws DataSetProcessingException;

    /**
     * Inserts the given element definitions into persistence.
     * 
     * @param elementDefinitions
     *            - the element definitions to insert.
     * @throws DataSetProcessingException
     *             - if there is an error while persisting the data.
     */
    void insertElementDefinitions(List<MetadataObject> elementDefinitions) throws DataSetProcessingException;

    /**
     * Inserts the given object definitions into persistence.
     * 
     * @param objectDefinitions
     *            - the object definitions to insert.
     * @throws DataSetProcessingException
     *             - if there is an error while persisting the data.
     */
    void insertObjectDefinitions(List<MetadataObject> objectDefinitions) throws DataSetProcessingException;

    /**
     * Uses the given object and element definitions to add the object validation rules to persistence.
     * 
     * It assumes that these definitions already exist in persistence and thus contain their primary IDs.
     * 
     * @param elementDefinitions
     *            - the element definitions to use.
     * @param objectDefinitions
     *            - the object definitions to use.
     * @throws DataSetProcessingException
     *             - if there is an error while persisting the data.
     */
    void insertValidationRules(List<MetadataObject> objectDefinitions, List<MetadataObject> elementDefinitions)
            throws DataSetProcessingException;
}
