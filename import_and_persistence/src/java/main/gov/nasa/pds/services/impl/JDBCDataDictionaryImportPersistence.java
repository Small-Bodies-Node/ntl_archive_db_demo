/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services.impl;

import gov.nasa.pds.entities.ElementAlias;
import gov.nasa.pds.entities.MetadataObject;
import gov.nasa.pds.entities.ObjectAlias;
import gov.nasa.pds.entities.Property;
import gov.nasa.pds.entities.TargetType;
import gov.nasa.pds.entities.UnitAlias;
import gov.nasa.pds.services.DataDictionaryImportPersistence;
import gov.nasa.pds.services.DataSetProcessingConfigurationException;
import gov.nasa.pds.services.DataSetProcessingException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.topcoder.commons.utils.LoggingWrapperUtility;
import com.topcoder.commons.utils.ValidationUtility;
import com.topcoder.util.log.Level;
import com.topcoder.util.log.Log;

/**
 * <p>
 * The implementation for inserting dictionary data into the persistence
 * </p>
 *
 * <p>
 * <b>Thread Safety:</b> The implementations are effectively thread-safe.
 * </p>
 *
 * @author argolite, TCSASSEMBLER
 * @version 1.0
 */
public class JDBCDataDictionaryImportPersistence implements DataDictionaryImportPersistence, InitializingBean {
    /**
     * Represents the class name.
     */
    private static final String CLASS_NAME = JDBCDataDictionaryImportPersistence.class.getName();

    /**
     * Represents the names for required/optional element/object set.
     */
    private static final List<String> EXCEPT;

    /**
     * Initializes the except list.
     */
    static {
        EXCEPT = new ArrayList<String>();
        EXCEPT.add("REQUIRED_ELEMENT_SET");
        EXCEPT.add("OPTIONAL_ELEMENT_SET");
        EXCEPT.add("REQUIRED_OBJECT_SET");
        EXCEPT.add("OPTIONAL_OBJECT_SET");
    }

    /**
     * Represents the JdbcTemplate instance used for all DB interaction.
     */
    private JdbcTemplate jdbcTemplate;

    /**
     * Represents the log instance used for logging.
     */
    private Log logger;

    /**
     * The default do nothing constructor.
     */
    public JDBCDataDictionaryImportPersistence() {
    }

    /**
     * Sets the JdbcTemplate instance used for all DB interaction.
     * @param jdbcTemplate the JdbcTemplate instance used for all DB interaction
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Sets the log instance used for logging.
     * @param logger the log instance used for logging
     */
    public void setLogger(Log logger) {
        this.logger = logger;
    }

    /**
     * Checks whether this class was initialized by Spring properly.
     *
     * Required parameters:
     * <ul>
     * <li>logger</li>
     * <li>jdbcTemplate</li>
     * </ul>
     *
     * @throws DataSetProcessingConfigurationException
     *             if logger or jdbcTemplate are null
     */
    @Override
    public void afterPropertiesSet() {
        ValidationUtility.checkNotNull(logger, "logger",
                DataSetProcessingConfigurationException.class);
        ValidationUtility.checkNotNull(jdbcTemplate, "jdbcTemplate",
                DataSetProcessingConfigurationException.class);
    }

    @Override
    public void initializeTableCounter() throws DataSetProcessingException {
        String signature = CLASS_NAME + ".initializeTableCounter()";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature, null, null);
        }

        try {
            Helper.insert(jdbcTemplate, "insert into table_counter (id, count) values (?, ?)", new Object[] { 1L, 0 });
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Data access error occurs", e));
        }

        LoggingWrapperUtility.logExit(logger, signature, null);
    }

    /**
     * Inserts the given object aliases into persistence.
     *
     * @param objectAliases the object aliases to insert
     * @throws DataSetProcessingException if there is an error while persisting the data
     */
    @Override
    public void insertObjectAliases(List<ObjectAlias> objectAliases) throws DataSetProcessingException {
        String signature = CLASS_NAME + ".insertObjectAliases(List<ObjectAlias> objectAliases)";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "objectAliases" }, new Object[] { objectAliases });
        }
        for (ObjectAlias objectAlias : objectAliases) {
            try {
                long objectAliasId = Helper.insert(jdbcTemplate,
                        "insert into object_alias (alias, full_name) values (?, ?)",
                        new Object[] {objectAlias.getAlias(), objectAlias.getFullName()});
                objectAlias.setId(objectAliasId);
            } catch (DataAccessException e) {
                throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                        "Data access error occurs", e));
            }
        }
        LoggingWrapperUtility.logExit(logger, signature, null);
    }

    /**
     * Inserts the given element aliases into persistence.
     *
     * @param elementAliases the element aliases to insert
     * @throws DataSetProcessingException if there is an error while persisting the data
     */
    @Override
    public void insertElementAliases(List<ElementAlias> elementAliases) throws DataSetProcessingException {
        String signature = CLASS_NAME + ".insertElementAliases(List<ElementAlias> elementAliases)";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "elementAliases" }, new Object[] { elementAliases });
        }
        for (ElementAlias elementAlias : elementAliases) {
            try {
                long elementAliasId = Helper.insert(jdbcTemplate,
                        "insert into element_alias (full_name, alias, another_name) values (?, ?, ?)",
                        new Object[] {elementAlias.getFullName(), elementAlias.getAlias(),
                        elementAlias.getAnotherName()});
                elementAlias.setId(elementAliasId);
            } catch (DataAccessException e) {
                throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                        "Data access error occurs", e));
            }
        }
        LoggingWrapperUtility.logExit(logger, signature, null);
    }

    /**
     * Inserts the given unit aliases into persistence.
     *
     * @param unitAliases the unit aliases to insert
     * @throws DataSetProcessingException if there is an error while persisting the data
     */
    @Override
    public void insertUnitAliases(List<UnitAlias> unitAliases) throws DataSetProcessingException {
        String signature = CLASS_NAME + ".insertUnitAliases(List<UnitAlias> unitAliases)";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "unitAliases" }, new Object[] { unitAliases });
        }
        for (UnitAlias unitAlias : unitAliases) {
            try {
                long unitAliasId = Helper.insert(jdbcTemplate,
                        "insert into unit_alias (alias, full_name) values (?, ?)",
                        new Object[] {unitAlias.getAlias(), unitAlias.getFullName()});
                unitAlias.setId(unitAliasId);
            } catch (DataAccessException e) {
                throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                        "Data access error occurs", e));
            }
        }
        LoggingWrapperUtility.logExit(logger, signature, null);
    }

    /**
     * Inserts the given target types into persistence.
     *
     * @param targetTypes the target types to insert
     * @throws DataSetProcessingException if there is an error while persisting the data
     */
    @Override
    public void insertTargetTypes(List<TargetType> targetTypes) throws DataSetProcessingException {
        String signature = CLASS_NAME + ".insertTargetTypes(List<TargetType> targetTypes)";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "targetTypes" }, new Object[] { targetTypes });
        }
        for (TargetType targetType : targetTypes) {
            try {
                long targetTypeId = Helper.insert(jdbcTemplate, "insert into target_type (name) values (?)",
                        new Object[] {targetType.getName()});
                targetType.setId(targetTypeId);
            } catch (DataAccessException e) {
                throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                        "Data access error occurs", e));
            }
        }
        LoggingWrapperUtility.logExit(logger, signature, null);
    }

    /**
     * Inserts the given element definitions into persistence.
     *
     * @param elementDefinitions the element definitions to insert
     * @throws DataSetProcessingException if there is an error while persisting the data
     */
    @Override
    public void insertElementDefinitions(List<MetadataObject> elementDefinitions)
            throws DataSetProcessingException {
        String signature = CLASS_NAME + ".insertElementDefinitions(List<MetadataObject> elementDefinitions)";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "elementDefinitions" }, new Object[] { elementDefinitions });
        }
        try {
            for (MetadataObject elementDefinition : elementDefinitions) {
                // xiufei - Find the property which name is 'NAME', it's value is the name of element definition
                String namePropertyValue = elementDefinition.getProperties().get(
                        elementDefinition.getProperties().indexOf(new Property("NAME"))).getValues().get(0);

                // Insert new record into the element_definition table with the name of the elementDefinition
                long elementDefinitionId = Helper.insert(jdbcTemplate,
                        "insert into element_definition (name) values (?)",
                        new Object[] {namePropertyValue}); // xiufei - change the name
                if (elementDefinition.getProperties() != null) {
                    for (Property property : elementDefinition.getProperties()) {
                        // xiufei - skip the property which name is 'NAME'
                        if (property.getName().equals("NAME")) {
                            continue;
                        }

                        // See if keyword exists in keyword table using property.name. If does not, insert it into
                        // that table
                        Long keywordId = Helper.getKeywordId(property.getName(), jdbcTemplate);

                        if (property.getValues() != null) {
                            for (String value : property.getValues()) {
                                // Insert property value into lookup_value table
                                long lookupId = Helper.insertLookupValue(jdbcTemplate, keywordId, value);

                                // Insert new row into element_definition_lookup table with the IDs of the
                                // new lookup_value and element_definition rows
                                jdbcTemplate.update(
                                        "insert into element_definition_lookup (element_id, lookup_id) values (?, ?)",
                                        new Object[] {elementDefinitionId, lookupId});
                            }
                        }
                    }
                }

                // Put the id of the element_definition row back into elementDefinition
                elementDefinition.setId(elementDefinitionId);
            }
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Data access error occurs", e));
        }
        LoggingWrapperUtility.logExit(logger, signature, null);
    }

    /**
     * Inserts the given object definitions into persistence.
     *
     * @param objectDefinitions the object definitions to insert
     * @throws DataSetProcessingException if there is an error while persisting the data
     */
    @Override
    public void insertObjectDefinitions(List<MetadataObject> objectDefinitions)
            throws DataSetProcessingException {
        String signature = CLASS_NAME + ".insertObjectDefinitions(List<MetadataObject> objectDefinitions)";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "objectDefinitions" }, new Object[] { objectDefinitions });
        }
        try {
            for (MetadataObject objectDefinition : objectDefinitions) {
                // xiufei - Find the property which name is 'NAME', it's value is the name of object definition
                String namePropertyValue = objectDefinition.getProperties().get(
                        objectDefinition.getProperties().indexOf(new Property("NAME"))).getValues().get(0);

                // Insert new record into the object_definition table with the name of the objectDefinition
                long objectDefinitionId = Helper.insert(jdbcTemplate,
                        "insert into object_definition (name) values (?)",
                        new Object[] {namePropertyValue}); // xiufei - change the name
                // For each property:Property in objectDefinition.properties except
                // REQUIRED_ELEMENT_SET, OPTIONAL_ELEMENT_SET, REQUIRED_OBJECT_SET, OPTIONAL_OBJECT_SET
                if (objectDefinition.getProperties() != null) {
                    for (Property property : objectDefinition.getProperties()) {
                        // xiufei - skip the property which name is 'NAME'
                        if (!EXCEPT.contains(property.getName()) && !property.getName().equals("NAME")) {
                            // See if keyword exists in keyword table using property.name. If does not,
                            // insert it into that table
                            Long keywordId = Helper.getKeywordId(property.getName(), jdbcTemplate);

                            for (String value : property.getValues()) {
                                // Insert property value into lookup_value table
                                long lookupId = Helper.insertLookupValue(jdbcTemplate, keywordId, value);

                                // Insert new row into object_definition_lookup table with the IDs of the new
                                // lookup_value and object_definition rows
                                jdbcTemplate.update(
                                        "insert into object_definition_lookup (object_id, lookup_id) values (?, ?)",
                                        new Object[] {objectDefinitionId, lookupId});
                            }
                        }
                    }
                }

                // Put the id of the object_definition row back into objectDefinition
                objectDefinition.setId(objectDefinitionId);
            }
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Data access error occurs", e));
        }
        LoggingWrapperUtility.logExit(logger, signature, null);
    }

    /**
     * Uses the given object and element definitions to add the object validation rules to persistence.<br>
     * It assumes that these definitions already exist in persistence and thus contain their primary IDs
     *
     * @param objectDefinitions the object definitions to use
     * @param elementDefinitions the element definitions to use
     * @throws DataSetProcessingException if there is an error while persisting the data
     */
    @Override
    public void insertValidationRules(List<MetadataObject> objectDefinitions, List<MetadataObject> elementDefinitions)
            throws DataSetProcessingException {
        String signature = CLASS_NAME
                + ".insertValidationRules(List<MetadataObject> objectDefinitions, List<MetadataObject> elementDefinitions)";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "objectDefinitions", "elementDefinitions" },
                    new Object[] { objectDefinitions, elementDefinitions });
        }
        // Create some comparison constants
        Property optionalObjectSetPropertyType = new Property("OPTIONAL_OBJECT_SET");
        Property requiredObjectSetPropertyType = new Property("REQUIRED_OBJECT_SET");
        Property optionalElementSetPropertyType = new Property("OPTIONAL_ELEMENT_SET");
        Property requiredElementSetPropertyType = new Property("REQUIRED_ELEMENT_SET");

        try {
            for (MetadataObject objectDefinition : objectDefinitions) {
                // Get the OPTIONAL_OBJECT_SET property
                Property optionalObjectSetProperty = null;
                if (objectDefinition.getProperties() != null
                        && objectDefinition.getProperties().indexOf(optionalObjectSetPropertyType) != -1) {
                    optionalObjectSetProperty = objectDefinition.getProperties().get(
                            objectDefinition.getProperties().indexOf(optionalObjectSetPropertyType));
                }

                // Get the REQUIRED_OBJECT_SET property
                Property requiredObjectSetProperty = null;
                if (objectDefinition.getProperties() != null
                        && objectDefinition.getProperties().indexOf(requiredObjectSetPropertyType) != -1) {
                    requiredObjectSetProperty = objectDefinition.getProperties().get(
                            objectDefinition.getProperties().indexOf(requiredObjectSetPropertyType));
                }

                // Get the OPTIONAL_ELEMENT_SET property
                Property optionalElementSetProperty = null;
                if (objectDefinition.getProperties() != null
                        && objectDefinition.getProperties().indexOf(optionalElementSetPropertyType) != -1) {
                    optionalElementSetProperty = objectDefinition.getProperties().get(
                            objectDefinition.getProperties().indexOf(optionalElementSetPropertyType));
                }

                // Get the REQUIRED_ELEMENT_SET property
                Property requiredElementSetProperty = null;
                if (objectDefinition.getProperties() != null
                        && objectDefinition.getProperties().indexOf(requiredElementSetPropertyType) != -1) {
                    requiredElementSetProperty = objectDefinition.getProperties().get(
                            objectDefinition.getProperties().indexOf(requiredElementSetPropertyType));
                }

                // Insert a new row in object_validation
                if (optionalObjectSetProperty != null) {
                    for (String value : optionalObjectSetProperty.getValues()) {

                        // xiufei - updated for name property
                        jdbcTemplate.update(
                                "insert into object_validation (object_id, inner_object_id, required) values (?, ?, ?)",
                                new Object[] {objectDefinition.getId(), findDefinitionId(objectDefinitions, value), 0});
                    }
                }

                // Insert a new row in object_validation
                if (requiredObjectSetProperty != null) {
                    for (String value : requiredObjectSetProperty.getValues()) {
                        // xiufei - updated for name property
                        jdbcTemplate.update(
                                "insert into object_validation (object_id, inner_object_id, required) values (?, ?, ?)",
                                new Object[] {objectDefinition.getId(), findDefinitionId(objectDefinitions, value), 1});
                    }
                }

                // Insert a new row in element_validation
                if (requiredElementSetProperty != null) {
                    for (String value : requiredElementSetProperty.getValues()) {
                        // xiufei - updated for name property
                        jdbcTemplate.update(
                                "insert into element_validation (object_id, inner_element_id, required) values (?, ?, ?)",
                                new Object[] {objectDefinition.getId(), findDefinitionId(elementDefinitions, value), 1});
                    }
                }

                if (optionalElementSetProperty != null) {
                    boolean containsPSDD = false;
                    for (String value : optionalElementSetProperty.getValues()) {
                        // If it is called "PSDD" then skip it, but update object_definition
                        if (value.equals("PSDD")) {
                            containsPSDD = true;
                            continue;
                        }
                        // Insert a new row in element_validation
                        // xiufei - updated for name property
                        jdbcTemplate.update(
                                "insert into element_validation (object_id, inner_element_id, required) values (?, ?, ?)",
                                new Object[] {objectDefinition.getId(), findDefinitionId(elementDefinitions, value), 0});
                    }

                    // If a "PSDD" was detected, update the entry in object_definition
                    if (containsPSDD) {
                        jdbcTemplate.update("update object_definition set globally_allowed_elements = 1 where id = ?",
                                new Object[] {objectDefinition.getId()});
                    }
                }
            }
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Data access error occurs", e));
        }

        LoggingWrapperUtility.logExit(logger, signature, null);
    }

    /**
     * Finds the id of definition which the value of 'NAME' property is equal to the given value.
     *
     * @param definitions the definitions
     * @param value the value to find
     * @return the definition id (or -1 is NAME is not found).
     */
    private long findDefinitionId(List<MetadataObject> definitions, String value) {
        for (int i = 0; i < definitions.size(); i++) {
            for (Property property : definitions.get(i).getProperties()) {
                if (property.getName().equals("NAME")) {
                    if (property.getValues().get(0).equals(value)) {
                        return definitions.get(i).getId();
                    }
                }
            }
        }
        return -1;
    }
}