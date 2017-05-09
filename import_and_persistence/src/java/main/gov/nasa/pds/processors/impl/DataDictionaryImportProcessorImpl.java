/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl;

import gov.nasa.pds.entities.ElementAlias;
import gov.nasa.pds.entities.MetadataFile;
import gov.nasa.pds.entities.MetadataObject;
import gov.nasa.pds.entities.ObjectAlias;
import gov.nasa.pds.entities.Property;
import gov.nasa.pds.entities.SequenceValueProperty;
import gov.nasa.pds.entities.TargetType;
import gov.nasa.pds.entities.UnitAlias;
import gov.nasa.pds.processors.DataDictionaryImportProcessor;
import gov.nasa.pds.services.DataDictionaryImportPersistence;
import gov.nasa.pds.services.DataSetProcessingConfigurationException;
import gov.nasa.pds.services.DataSetProcessingException;
import gov.nasa.pds.services.MetadataFileReader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;

import com.topcoder.commons.utils.LoggingWrapperUtility;
import com.topcoder.commons.utils.ValidationUtility;
import com.topcoder.util.log.Level;
import com.topcoder.util.log.Log;

/**
 * <p>
 * The <code>DataDictionaryImportProcessorImpl</code> class implements <code>DataDictionaryImportProcessor</code>
 * interface by providing implementation of <code>importDataDictionary()</code> method.
 * </p>
 *
 * <strong>Thread Safety:</strong> This class is mutable since it provides public setters for its properties. But it
 * doesn't change its state and is thread safe when the following conditions are met: this class is initialized by
 * Spring right after construction and its parameters are never changed after that, all entities passed to this class
 * are used by the caller in thread safe manner (accessed from a single thread only).
 *
 * @author KennyAlive
 * @version 1.0
 */
public class DataDictionaryImportProcessorImpl implements DataDictionaryImportProcessor, InitializingBean {
    /**
     * Constant for the class name of this class. Used for logging.
     */
    private static final String CLASS_NAME = DataDictionaryImportProcessorImpl.class.getName();

    // --------- Object names constants --------------
    /**
     * The constant for ALIAS_LIST object name
     */
    private static final String ALIAS_LIST_OBJECT_NAME = "ALIAS_LIST";

    /**
     * The constant for UNIT_LIST object name
     */
    private static final String UNIT_LIST_OBJECT_NAME = "UNIT_LIST";

    /**
     * The constant for GENERIC_OBJECT_DEFINITION object name
     */
    private static final String GENERIC_OBJECT_DEFINITION_OBJECT_NAME = "GENERIC_OBJECT_DEFINITION";

    /**
     * The constant for SPECIFIC_OBJECT_DEFINITION object name
     */
    private static final String SPECIFIC_OBJECT_DEFINITION_OBJECT_NAME = "SPECIFIC_OBJECT_DEFINITION";

    /**
     * The constant for ELEMENT_DEFINITION object name
     */
    private static final String ELEMENT_DEFINITION_OBJECT_NAME = "ELEMENT_DEFINITION";

    /**
     * The constant for TARGET_TYPE value
     */
    private static final String TARGET_TYPE_VALUE = "TARGET_TYPE";

    // ----------- Comparison constants ---------------
    /**
     * The constant for OBJECT_ALIAS_SEQUENCE sequence property
     */
    private static final SequenceValueProperty OBJECT_ALIAS_SEQUENCE_PROPERTY_TYPE =
            new SequenceValueProperty("OBJECT_ALIAS_SEQUENCE");

    /**
     * The constant for ELEMENT_ALIAS_SEQUENCE sequence property
     */
    private static final SequenceValueProperty ELEMENT_ALIAS_SEQUENCE_PROPERTY_TYPE =
            new SequenceValueProperty("ELEMENT_ALIAS_SEQUENCE");

    /**
     * The constant for UNIT_SEQUENCE sequence property
     */
    private static final SequenceValueProperty UNIT_ALIAS_SEQUENCE_PROPERTY_TYPE =
            new SequenceValueProperty("UNIT_SEQUENCE");

    /**
     * The constant for NAME property
     */
    private static final Property NAME_PROPERTY_TYPE = new Property("NAME");

    /**
     * The constant for STANDARD_VALUE_SET property
     */
    private static final Property STANDARD_VALUE_SETNAME_PROPERTY_TYPE = new Property("STANDARD_VALUE_SET");


    /**
     * <p>
     * The <code>Log</code> instance used for logging.
     * </p>
     *
     * <p>
     * It is initialized with Spring setter dependency injection. Cannot be <code>null</code> after initialization,
     * assuming that property is initialized via Spring setter-based dependency injection and is never changed
     * after that. Has a setter.
     * </p>
     */
    private Log logger;

    /**
     * <p>
     * The file that contains the data dictionary.
     * </p>
     *
     * <p>
     * It is initialized with Spring setter dependency injection. Cannot be <code>null</code> after initialization,
     * assuming that property is initialized via Spring setter-based dependency injection and is never changed
     * after that. Has a setter.
     * </p>
     */
    private String importFile;

    /**
     * <p>
     * The <code>MetadataFileReader</code>instance.
     * </p>
     *
     * <p>
     * It is initialized with Spring setter dependency injection. Cannot be <code>null</code> after initialization,
     * assuming that property is initialized via Spring setter-based dependency injection and is never changed
     * after that. Has a setter.
     * </p>
     */
    private MetadataFileReader metadataFileReader;

    /**
     * <p>
     * The <code>DataDictionaryImportPersistence</code> instance.
     * </p>
     *
     * <p>
     * It is initialized with Spring setter dependency injection. Cannot be <code>null</code> after initialization,
     * assuming that property is initialized via Spring setter-based dependency injection and is never changed
     * after that. Has a setter.
     * </p>
     */
    private DataDictionaryImportPersistence dataDictionaryImportPersistence;

    /**
     * Creates an instance of <code>DataDictionaryImportProcessor</code>
     */
    public DataDictionaryImportProcessorImpl() {
        // Empty
    }

    /**
     * Sets the logger.
     *
     * @param logger
     *              the logger to set
     */
    public void setLogger(Log logger) {
        this.logger = logger;
    }

    /**
     * Sets the import file.
     *
     * @param importFile
     *              the import file to set
     */
    public void setImportFile(String importFile) {
        this.importFile = importFile;
    }

    /**
     * Sets the <code>MetadataFileReader</code> instance.
     *
     * @param metadataFileReader
     *              the metadata file reader to set
     */
    public void setMetadataFileReader(MetadataFileReader metadataFileReader) {
        this.metadataFileReader = metadataFileReader;
    }

    /**
     * Sets the <code>DataDictionaryImportPersistence</code> instance.
     *
     * @param dataDictionaryImportPersistence
     *              the <code>DataDictionaryImportPersistence</code> instance to set
     */
    public void setDataDictionaryImportPersistence(DataDictionaryImportPersistence dataDictionaryImportPersistence) {
        this.dataDictionaryImportPersistence = dataDictionaryImportPersistence;
    }

    /**
     * Checks whether this class was initialized by Spring properly.
     *
     * Required parameters:
     * <ul>
     * <li>logger</li>
     * <li>importFile</li>
     * <li>metadataFileReader</li>
     * <li>dataDictionaryImportPersistence</li>
     * </ul>
     *
     * @throws DataSetProcessingConfigurationException
     *            if the class was not initialized properly
     */
    @Override
    public void afterPropertiesSet() {
        ValidationUtility.checkNotNull(logger, "logger",
                DataSetProcessingConfigurationException.class);
        ValidationUtility.checkNotNullNorEmptyAfterTrimming(importFile, "importFile",
                DataSetProcessingConfigurationException.class);
        ValidationUtility.checkNotNull(metadataFileReader, "metadataFileReader",
                DataSetProcessingConfigurationException.class);
        ValidationUtility.checkNotNull(dataDictionaryImportPersistence, "dataDictionaryImportPersistence",
                DataSetProcessingConfigurationException.class);
    }

    /**
     * Imports the data dictionary into the application.
     *
     * @throws DataSetProcessingException
     *                  if there is an error while extracting the files
     */
    @Override
    public void importDataDictionary() throws DataSetProcessingException {
        String signature = CLASS_NAME + ".importDataDictionary()";
        LoggingWrapperUtility.logEntrance(logger, signature, null, null);

        // read data dictionary
        MetadataFile metadataFile = metadataFileReader.readMetadataInfo(importFile, null);

        // create lists
        List<ObjectAlias> objectAliases = new ArrayList<ObjectAlias>();
        List<ElementAlias> elementAliases = new ArrayList<ElementAlias>();
        List<UnitAlias> unitAliases = new ArrayList<UnitAlias>();
        List<MetadataObject> objectDefinitions = new ArrayList<MetadataObject>();
        List<MetadataObject> elementDefinitions = new ArrayList<MetadataObject>();
        List<TargetType> targetTypes = new ArrayList<TargetType>();

        // create entities out of the metadata
        for (MetadataObject metadataObject : metadataFile.getChildren()) {
            if (metadataObject.getName().equals(ALIAS_LIST_OBJECT_NAME)) {
                processObjectAliases(metadataObject, objectAliases);
                processElementAliases(metadataObject, elementAliases);
            } else if (metadataObject.getName().equals(UNIT_LIST_OBJECT_NAME)) {
                processUnitAliases(metadataObject, unitAliases);
            } else if (metadataObject.getName().equals(GENERIC_OBJECT_DEFINITION_OBJECT_NAME)
                    || metadataObject.getName().equals(SPECIFIC_OBJECT_DEFINITION_OBJECT_NAME)) {
                objectDefinitions.add(metadataObject);
            } else if (metadataObject.getName().equals(ELEMENT_DEFINITION_OBJECT_NAME)) {
                processElementDefinition(metadataObject, elementDefinitions, targetTypes);
            }
        }

        dataDictionaryImportPersistence.initializeTableCounter();

        // persist objects
        dataDictionaryImportPersistence.insertObjectAliases(objectAliases);
        dataDictionaryImportPersistence.insertElementAliases(elementAliases);
        dataDictionaryImportPersistence.insertUnitAliases(unitAliases);
        dataDictionaryImportPersistence.insertTargetTypes(targetTypes);
        dataDictionaryImportPersistence.insertElementDefinitions(elementDefinitions);
        dataDictionaryImportPersistence.insertObjectDefinitions(objectDefinitions);
        dataDictionaryImportPersistence.insertValidationRules(objectDefinitions, elementDefinitions);

        LoggingWrapperUtility.logExit(logger, signature, null);
    }

    /**
     * Processes OBJECT_ALIAS_SEQUENCE property:
     * creates <code>ObjectAlias</code> instances based on the property values.
     *
     * @param metadataObject
     *              the metadata object that holds OBJECT_ALIAS_SEQUENCE property
     * @param objectAliases
     *              the list to store created objects
     *
     * @throws DataSetProcessingException
     *                  if the given object doesn't have the OBJECT_ALIAS_SEQUENCE property
     */
    private void processObjectAliases(MetadataObject metadataObject, List<ObjectAlias> objectAliases)
            throws DataSetProcessingException {
        final String signature = CLASS_NAME +
                ".processObjectAliases(MetadataObject metadataObject, List<ObjectAlias> objectAliases)";

        int index = metadataObject.getProperties().indexOf(OBJECT_ALIAS_SEQUENCE_PROPERTY_TYPE);
        if (index == -1) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Failed to find OBJECT_ALIAS_SEQUENCE property"));
        }

        SequenceValueProperty objectAliasSequenceProperty =
                (SequenceValueProperty)metadataObject.getProperties().get(index);
        for (List<String> item : objectAliasSequenceProperty.getSequences()) {
            ObjectAlias objectAlias = new ObjectAlias();
            objectAlias.setAlias(item.get(0));
            objectAlias.setFullName(item.get(1));
            objectAliases.add(objectAlias);
        }
    }

    /**
     * Processes ELEMENT_ALIAS_SEQUENCE property:
     * creates <code>ElementAlias</code> instances based on the property values.
     *
     * @param metadataObject
     *              the metadata object that holds ELEMENT_ALIAS_SEQUENCE property
     * @param elementAliases
     *              the list to store created objects
     *
     * @throws DataSetProcessingException
     *                  if the given object doesn't have the ELEMENT_ALIAS_SEQUENCE property
     */
    private void processElementAliases(MetadataObject metadataObject, List<ElementAlias> elementAliases)
            throws DataSetProcessingException {
        final String signature = CLASS_NAME +
                ".processElementAliases(MetadataObject metadataObject, List<ElementAlias> elementAliases)";

        int index = metadataObject.getProperties().indexOf(ELEMENT_ALIAS_SEQUENCE_PROPERTY_TYPE);
        if (index == -1) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Failed to find ELEMENT_ALIAS_SEQUENCE property"));
        }

        SequenceValueProperty elementAliasSequenceProperty =
                (SequenceValueProperty)metadataObject.getProperties().get(index);
        for (List<String> item : elementAliasSequenceProperty.getSequences()) {
            ElementAlias elementAlias = new ElementAlias();
            elementAlias.setAlias(item.get(0));
            elementAlias.setFullName(item.get(1));
            elementAlias.setAnotherName(item.get(2));
            elementAliases.add(elementAlias);
        }
    }

    /**
     * Processes UNIT_ALIAS_SEQUENCE property:
     * creates <code>UnitAlias</code> instances based on the property values.
     *
     * @param metadataObject
     *              the metadata object that holds UNIT_SEQUENCE property
     * @param unitAliases
     *              the list to store created objects
     *
     * @throws DataSetProcessingException
     *                  if the given object doesn't have the UNIT_SEQUENCE property
     */
    private void processUnitAliases(MetadataObject metadataObject, List<UnitAlias> unitAliases)
            throws DataSetProcessingException {
        final String signature = CLASS_NAME +
                ".processUnitAliases(MetadataObject metadataObject, List<UnitAlias> unitAliases)";

        int index = metadataObject.getProperties().indexOf(UNIT_ALIAS_SEQUENCE_PROPERTY_TYPE);
        if (index == -1) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Failed to find UNIT_ALIAS_SEQUENCE property"));
        }

        SequenceValueProperty unitAliasSequenceProperty =
                (SequenceValueProperty)metadataObject.getProperties().get(index);
        for (List<String> item : unitAliasSequenceProperty.getSequences()) {
            UnitAlias unitAlias = new UnitAlias();
            unitAlias.setAlias(item.get(0));
            unitAlias.setFullName(item.get(1));
            unitAliases.add(unitAlias);
        }
    }

    /**
     * Processes NAME property:
     * stores the given <code>MetadataObject</code> object and creates <code>TargetType</code> instances based on
     * the property values.
     *
     * @param metadataObject
     *              the metadata object that holds NAME property
     * @param elementDefinitions
     *              the list to store <code>MetadataObject</code> objects
     * @param targetTypes
     *              the list to store created <code>TargetType</code> objects
     *
     * @throws DataSetProcessingException
     *                  if the given object doesn't have the NAME property or
     *                  when NAME == 'TARGET_TYPE' and the given object doesn't have the STANDARD_VALUE_SET property
     */
    private void processElementDefinition(MetadataObject metadataObject, List<MetadataObject> elementDefinitions,
            List<TargetType> targetTypes) throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".processElementDefinition(MetadataObject metadataObject, "
                +  "List<MetadataObject> elementDefinitions, List<TargetType> targetTypes)";

        elementDefinitions.add(metadataObject);
        int index = metadataObject.getProperties().indexOf(NAME_PROPERTY_TYPE);
        if (index == -1) {
            logger.log(Level.WARN, "Failed to find NAME property");
            return;
            //throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
            ///        "Failed to find NAME property"));
        }

        Property nameProperty = metadataObject.getProperties().get(index);
        if (nameProperty.getValues().get(0).equals(TARGET_TYPE_VALUE)) {
            index = metadataObject.getProperties().indexOf(STANDARD_VALUE_SETNAME_PROPERTY_TYPE);
            if (index == -1) {
                throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                        "Failed to find STANDARD_VALUE_SET property"));
            }

            Property standardValueSetnameProperty = metadataObject.getProperties().get(index);
            for (String value : standardValueSetnameProperty.getValues()) {
                TargetType targetType = new TargetType(value);
                targetTypes.add(targetType);
            }
        }
    }
}
