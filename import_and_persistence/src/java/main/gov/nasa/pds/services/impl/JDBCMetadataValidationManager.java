/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services.impl;

import gov.nasa.pds.entities.ElementAlias;
import gov.nasa.pds.entities.ElementValidation;
import gov.nasa.pds.entities.ErrorItem;
import gov.nasa.pds.entities.ErrorType;
import gov.nasa.pds.entities.ItemType;
import gov.nasa.pds.entities.MetadataObject;
import gov.nasa.pds.entities.ObjectAlias;
import gov.nasa.pds.entities.ObjectValidation;
import gov.nasa.pds.entities.Property;
import gov.nasa.pds.entities.ValidationReport;
import gov.nasa.pds.services.DataSetProcessingConfigurationException;
import gov.nasa.pds.services.DataSetProcessingException;
import gov.nasa.pds.services.MetadataValidationManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.topcoder.commons.utils.LoggingWrapperUtility;
import com.topcoder.commons.utils.ValidationUtility;
import com.topcoder.util.log.Level;
import com.topcoder.util.log.Log;

/**
 * <p>
 * Performs validations of the metadata as well as corrections.
 * </p>
 *
 * <p>
 * <b>Thread Safety:</b> The implementations are effectively thread-safe.
 * </p>
 *
 * @author argolite, TCSASSEMBLER
 * @version 1.0
 */
public class JDBCMetadataValidationManager implements MetadataValidationManager, InitializingBean {
    /**
     * Represents the class name.
     */
    private static final String CLASS_NAME = JDBCMetadataValidationManager.class.getName();

    /**
     * Represents the JdbcTemplate instance used for all DB interaction.
     */
    private JdbcTemplate jdbcTemplate;

    /**
     * Represents the log instance used for logging.
     */
    private Log logger;

    /**
     * Represents the percentage that a string is matched for conflict checking.
     */
    private double misspelledConflictPercentage;

    /**
     * Represents the loaded object validation rules.
     */
    private List<ObjectValidation> objectValidations;

    /**
     * Represents the loaded element validation rules.
     */
    private List<ElementValidation> elementValidations;

    /**
     * Represents the loaded element aliases.
     */
    private List<ElementAlias> elementAliases;

    /**
     * Represents the loaded object aliases.
     */
    private List<ObjectAlias> objectAliases;

    /**
     * True, if the validation rules are loaded, otherwise false.
     */
    private boolean loaded;

    /**
     * The default do nothing constructor.
     */
    public JDBCMetadataValidationManager() {
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
     * Sets the percentage that a string is matched for conflict checking.
     * @param misspelledConflictPercentage the percentage that a string is matched for conflict checking
     */
    public void setMisspelledConflictPercentage(double misspelledConflictPercentage) {
        this.misspelledConflictPercentage = misspelledConflictPercentage;
    }

    /**
     * Checks whether this class was initialized by Spring properly.
     * 
     * Required parameters:
     * <ul>
     * <li>logger</li>
     * <li>jdbcTemplate</li>
     * <li>misspelledConflictPercentage</li>
     * </ul>
     * 
     * @throws DataSetProcessingConfigurationException
     *             if logger, jdbcTemplate or misspelledConflictPercentage are null
     */
    @Override
    public void afterPropertiesSet() {
        ValidationUtility.checkNotNull(logger, "logger",
                DataSetProcessingConfigurationException.class);
        ValidationUtility.checkNotNull(jdbcTemplate, "jdbcTemplate",
                DataSetProcessingConfigurationException.class);
        ValidationUtility.checkNotNull(misspelledConflictPercentage, "misspelledConflictPercentage",
                DataSetProcessingConfigurationException.class);
    }

    /**
     * Loads all validation rules for faster access.
     *
     * @throws DataSetProcessingException if there is an error while perfroming the validation
     */
    @Override
    public void load() throws DataSetProcessingException {
        String signature = CLASS_NAME + ".load()";

        LoggingWrapperUtility.logEntrance(logger, signature, null, null);

        try {
            // Prepare list of object validations
            objectValidations = new ArrayList<ObjectValidation>();
            // Prepare list of element validations
            elementValidations  = new ArrayList<ElementValidation>();

            // Read object validation rules

            // Select all rows in object_definition
            List<Map<String, Object>> objectDefinitionMaps = jdbcTemplate.queryForList(
                    "select id, name, globally_allowed_elements from object_definition");

            for (Map<String, Object> objectDefinitionMap : objectDefinitionMaps) {
                // Create a new ObjectValidation with object_definition.name, id and globally_allowed_elements value
                ObjectValidation objectValidation = new ObjectValidation(objectDefinitionMap.get("name").toString());
                objectValidation.setId(Long.parseLong(objectDefinitionMap.get("id").toString()));
                objectValidation.setGloballyAllowableElements(Boolean.parseBoolean(
                        objectDefinitionMap.get("globally_allowed_elements").toString()));

                // Sets the required/optional objects/properties.
                setRequiredAndOptional(objectValidation);

                // Add objectValidation into objectValidations
                objectValidations.add(objectValidation);
            }

            // Load all element_aliases into a List of ElementAlias
            elementAliases = jdbcTemplate.query(
                 "select id, full_name, alias, another_name from element_alias",
                 new RowMapper<ElementAlias>() {
                    @Override
                    public ElementAlias mapRow(ResultSet rs, int rowNum) throws SQLException {
                        ElementAlias elementAlias = new ElementAlias();
                        elementAlias.setId(rs.getLong("id"));
                        elementAlias.setFullName(rs.getString("full_name"));
                        elementAlias.setAlias(rs.getString("alias"));
                        elementAlias.setAnotherName(rs.getString("another_name"));
                        return elementAlias;
                    }
                });

            // Load all object_aliases into a List of ObjectAlias
            objectAliases = jdbcTemplate.query(
                "select id, alias, full_name from object_alias",
                new RowMapper<ObjectAlias>() {
                    @Override
                    public ObjectAlias mapRow(ResultSet rs, int rowNum) throws SQLException {
                        ObjectAlias objectAlias = new ObjectAlias();
                        objectAlias.setId(rs.getLong("id"));
                        objectAlias.setAlias(rs.getString("alias"));
                        objectAlias.setFullName(rs.getString("full_name"));
                        return objectAlias;
                    }
                });

             // Read element validation rules
             // Select all rows in element_definition
            List<Map<String, Object>> elementDefinitionMaps = jdbcTemplate.queryForList(
                     "select id, name from element_definition");
            // For each row
            for (Map<String, Object> elementDefinitionMap : elementDefinitionMaps) {
                // Create a new ElementValidation with the id and name of the element_definition
                ElementValidation elementValidation = new ElementValidation(
                        elementDefinitionMap.get("name").toString());
                elementValidation.setId(Long.parseLong(elementDefinitionMap.get("id").toString()));

                setElementValidationRules(elementValidation); // Set rules

                elementValidations.add(elementValidation); // Add elementValidation into elementValidations
            }
            loaded = true;
            LoggingWrapperUtility.logExit(logger, signature, null);
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Data access error occurs", e));
        }
    }

    /**
     * Checks whether the validation rules are already loaded.
     *
     * @return true, if the validation rules are loaded, otherwise false
     */
    @Override
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * Validates the metadata object.
     *
     * @param metaDataObject the metadata object to validate
     * @return the validation report, indicating any rule violations
     * @throws DataSetProcessingException if there is an error while performing the validation
     */
    @Override
    public ValidationReport validateMetadata(MetadataObject metaDataObject) throws DataSetProcessingException {
        String signature = CLASS_NAME + ".validateMetadata(MetadataObject metaDataObject)";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "metaDataObject" }, new Object[] { metaDataObject });
        }
        try {
            // Prepare report object
            ValidationReport report = new ValidationReport();
            report.setErrors(new ArrayList<ErrorItem>());

            ObjectValidation foundObjectValidation = findObjectValidation(metaDataObject.getName(), report);

            // If no entry in objectValidations, skip object and ignore the error created above
            if (foundObjectValidation == null) {
                report.getErrors().clear();
                report.setValid(true);

                if (logger.isEnabled(Level.DEBUG)) {
                    LoggingWrapperUtility.logExit(logger, signature, new Object[] { report.toJSONString() });
                }
                return report;
            }

            // Check that object has all required objects and properties, as per objectValidation
            // If a required object/property is not provided, or not found in the object/element aliases,
            // add the error items
            findRequiredObjects(metaDataObject.getChildren(), report, foundObjectValidation);
            findRequireProperties(metaDataObject.getProperties(), report, foundObjectValidation);

            // Check that object has only required or optional objects and properties, as per objectValidation.
            // If it has globally allowed values, then ensure it exists in the elementValidations or
            // elementAliases lists
            if (metaDataObject.getChildren() != null) {
                checkObjectsNotAllow(metaDataObject.getChildren(), report, foundObjectValidation);
            }
            if (metaDataObject.getProperties() != null) {
                checkPropertiesNotAllow(metaDataObject.getProperties(), report, foundObjectValidation);
            }

            // Check misspelled objects
            if (metaDataObject.getChildren() != null) {
                checkMisspelledObjects(report);
            }
            if (metaDataObject.getProperties() != null) {
                List<Map<String, Object>> misspelledProperties = checkMisspelledProperties(report);
                // Checks if the property values are valid, if not, adds an error item
                checkPropertyValues(metaDataObject.getProperties(), misspelledProperties, report);
            }

            // If there were errors, then report.valid = false; else it is true
            if (!report.getErrors().isEmpty()) {
                report.setValid(false);
            } else {
                report.setValid(true);
            }

            if (logger.isEnabled(Level.DEBUG)) {
                LoggingWrapperUtility.logExit(logger, signature, new Object[] { report.toJSONString() });
            }
            return report;
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Data access error occurs", e));
        }
    }

    /**
     * Corrects the metadata object.
     *
     * @param metaDataObject the metadata object to correct
     * @param validationReport the report with the violations
     * @throws DataSetProcessingException if there is an error while performing the validation
     */
    @Override
    public void correctMetadata(MetadataObject metaDataObject, ValidationReport validationReport)
            throws DataSetProcessingException {
        String signature = CLASS_NAME
            + ".correctMetadata(MetadataObject metaDataObject, ValidationReport validationReport)";

        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logEntrance(logger, signature,
                    new String[] { "metaDataObject", "validationReport" },
                    new Object[] { metaDataObject, validationReport });
        }
        try {
            // For each error:ErrorItem in validationReport.errors
            for (ErrorItem error : validationReport.getErrors()) {
                // If error.errorType is ALIAS or MISSPELLED, then locate the offending child or property
                if (error.getErrorType() == ErrorType.ALIAS && error.getItemType() == ItemType.OBJECT) {
                    // If it is an alias for an object, then locate the ObjectAlias with this alias,
                    // then put the fullName into the name of the child
                    String fullName = null;
                    for (ObjectAlias objectAlias : objectAliases) {
                        if (objectAlias.getAlias().equals(error.getItemName())) {
                            fullName = objectAlias.getFullName();
                            break;
                        }
                    }
                    if (metaDataObject.getName().equals(error.getItemName())) { // check object name first
                        metaDataObject.setName(fullName);
                    } else {
                        for (MetadataObject child : metaDataObject.getChildren()) { // check children name
                            if (child.getName().equals(error.getItemName())) {
                                child.setName(fullName); // fullName never be null here
                            }
                        }
                    }
                }
                if (error.getErrorType() == ErrorType.ALIAS && error.getItemType() == ItemType.PROPERTY) {
                    // If it is an alias for a property, then locate the ElementAlias with this alias
                    // or alternateName, then put the fullName into the name of the property
                    String fullName = null;
                    for (ElementAlias elementAlias : elementAliases) {
                        if (elementAlias.getAlias().equals(error.getItemName())) {
                            fullName = elementAlias.getFullName();
                            break;
                        }
                    }
                    for (Property property : metaDataObject.getProperties()) {
                        if (property.getName().equals(error.getItemName())) {
                            property.setName(fullName);
                        }
                    }
                }
                if (error.getErrorType() == ErrorType.MISSPELLED && error.getItemType() == ItemType.OBJECT) {
                    // If it is a misspelled object, then locate the nearest objectValidation in
                    // objectValidations and replace the name of the correct one
                    for (MetadataObject child : metaDataObject.getChildren()) {
                        if (child.getName().equals(error.getItemName())) {
                            Map<String, Object> result = checkObjectMisspelled(child.getName());
                            child.setName(result.get("compare").toString());
                        }
                    }
                }
                if (error.getErrorType() == ErrorType.MISSPELLED && error.getItemType() == ItemType.PROPERTY) {
                    // If it is a misspelled property, then locate the nearest elementValidation in
                    // elementValidations and replace the name of the correct one
                    for (Property property : metaDataObject.getProperties()) {
                        if (property.getName().equals(error.getItemName())) {
                            Map<String, Object> result = checkPropertyMisspelled(property.getName());
                            property.setName(result.get("compare").toString());
                        }
                    }
                }
            }
            LoggingWrapperUtility.logExit(logger, signature, null);
        } catch (DataAccessException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Data access error occurs", e));
        }
    }

    /**
     * Set the required/optional objects/property of the object validation.
     *
     * @param objectValidation the object validation to set
     * @throws DataAccessException if there is data access error
     */
    private void setRequiredAndOptional(ObjectValidation objectValidation) {
     // Prepare list
        objectValidation.setRequiredObjects(new ArrayList<String>());
        objectValidation.setOptionalObjects(new ArrayList<String>());
        objectValidation.setRequiredElements(new ArrayList<String>());
        objectValidation.setOptionalElements(new ArrayList<String>());

        // Select all rows in object_validation
        List<Map<String, Object>> objectValidationMaps = jdbcTemplate.queryForList(
                "select name, required from object_validation inner join object_definition"
                + " on object_definition.id = object_validation.inner_object_id where object_id = ?",
                new Object[] {objectValidation.getId()});
        // Add the name of the inner object name into requireObjects list of ObjectValidation if
        // required is true, else put into the optionalObjects list
        for (Map<String, Object> objectValidationMap : objectValidationMaps) {
            boolean required = Boolean.parseBoolean(objectValidationMap.get("required").toString());
            if (required) {
                objectValidation.getRequiredObjects().add(objectValidationMap.get("name").toString());
            } else {
                objectValidation.getOptionalObjects().add(objectValidationMap.get("name").toString());
            }
        }
        // Select all rows in element_validation
        List<Map<String, Object>> elementValidationMaps = jdbcTemplate.queryForList(
                "select name, required from element_validation inner join element_definition"
                + " on element_definition.id = element_validation.inner_element_id where object_id = ?",
                new Object[] {objectValidation.getId()});
        // Add the name of the inner element name into requiredElements list of ObjectValidation if
        // required is true, else put into the optionalElements list
        for (Map<String, Object> elementValidationMap : elementValidationMaps) {
            boolean required = Boolean.parseBoolean(elementValidationMap.get("required").toString());
            if (required) {
                objectValidation.getRequiredElements().add(elementValidationMap.get("name").toString());
            } else {
                objectValidation.getOptionalElements().add(elementValidationMap.get("name").toString());
            }
        }
    }

    /**
     * Set the rules of the element validation.
     *
     * @param elementValidation the element validation to set
     * @throws DataAccessException if there is data access error
     */
    private void setElementValidationRules(ElementValidation elementValidation) {
        // Extract rows in element_definition_lookup joined with lookup_value joined with keyword where
        // elemnt_definition_lookup.element_Id is ElementValidation.id
        List<Map<String, Object>> elementValidationMaps = jdbcTemplate.queryForList(
                "select name, value from element_definition_lookup inner join lookup_value"
                + " on element_definition_lookup.lookup_id = lookup_value.id inner join keyword"
                + " on lookup_value.keyword_id = keyword.id where element_definition_lookup.element_Id = ?",
                new Object[] {elementValidation.getId()});
        for (Map<String, Object> elementValidationMap : elementValidationMaps) {
            // For rows with keywords "MAXIMUM_LENGTH", "MINIMUM_LENGTH", "MAXIMUM", "MINIMUM",
            // set to equivalent ElementValidation fields
            if (elementValidationMap.get("name").toString().equals("MAXIMUM_LENGTH")) {
                if (!elementValidationMap.get("value").toString().equals("NULL")) { // xiufei - add
                    elementValidation.setMaximumLength(Integer.parseInt(
                            elementValidationMap.get("value").toString()));
                }
            }
            if (elementValidationMap.get("name").toString().equals("MINIMUM_LENGTH")) {
                elementValidation.setMinimumLength(Integer.parseInt(
                        elementValidationMap.get("value").toString())); // xiufei - may need to check NULL, same for others
            }
            if (elementValidationMap.get("name").toString().equals("MAXIMUM")) {
                if (!elementValidationMap.get("value").toString().equals("NULL")) { // xiufei - add
                    elementValidation.setMaximum(Float.parseFloat(elementValidationMap.get("value").toString()));
                }
            }
            if (elementValidationMap.get("name").toString().equals("MINIMUM")) {
                if (!elementValidationMap.get("value").toString().equals("NULL")) { // xiufei - add
                    elementValidation.setMinimum(Float.parseFloat(elementValidationMap.get("value").toString()));
                }
            }
            // For rows with keyword "STANDARD_VALUE_SET", set to ElementValidation.allowed values
            if (elementValidationMap.get("name").toString().equals("STANDARD_VALUE_SET")) {
                if (elementValidation.getAllowedValues() == null) {
                    elementValidation.setAllowedValues(new ArrayList<String>());
                }
                elementValidation.getAllowedValues().add(elementValidationMap.get("value").toString());
            }
            if (elementValidationMap.get("name").toString().equals("GENERAL_DATA_TYPE")) {
                elementValidation.setDataType(elementValidationMap.get("value").toString());
            }
        }
    }

    /**
     * Finds the object validation.
     *
     * @param objectName the object name
     * @param report the validation report to add errors
     * @return the found object validation, null if not found
     */
    private ObjectValidation findObjectValidation(String objectName, ValidationReport report) {
        ObjectValidation foundObjectValidation = null;
        // Determine if there is an entry for the metadataObject.name in objectValidations, and if not
        // there, then in objectAliases
        for (ObjectValidation objectValidation : objectValidations) {
            if (objectValidation.getName().equals(objectName)) {
                foundObjectValidation = objectValidation;
                break;
            }
        }
        ObjectAlias foundObjectAlias = null;
        for (ObjectAlias objectAlias : objectAliases) {
            if (objectAlias.getAlias().equals(objectName)) {
                foundObjectAlias = objectAlias;
                break;
            }
        }

        // If objectValidations has an entry, proceed to step 3
        if (foundObjectValidation == null && foundObjectAlias != null) {
            // If objectAliases has an entry, then create error item with these values
            report.getErrors().add(createErrorItem(objectName,
                    ItemType.OBJECT, ErrorType.ALIAS));
            // then use alias to get full name and then get the entry from objectValidations
            for (ObjectValidation objectValidation : objectValidations) {
                if (objectValidation.getName().equals(foundObjectAlias.getFullName())) {
                    foundObjectValidation = objectValidation;
                    break;
                }
            }
        }

        return foundObjectValidation;
    }

    /**
     * Check that object has all required objects, as per objectValidation.
     * If a required object is not provided, or not found in the object aliases,
     * creates the error item.
     *
     * @param children the child objects to find
     * @param report the report to add errors
     * @param foundObjectValidation the object validation
     */
    private void findRequiredObjects(List<MetadataObject> children,
            ValidationReport report, ObjectValidation foundObjectValidation) {
        for (String requiredObject : foundObjectValidation.getRequiredObjects()) {
            Object[] result = findRequiredObject(children, requiredObject);
            if (!(Boolean) result[0]) {
                report.getErrors().add(createErrorItem(requiredObject,
                        ItemType.OBJECT, ErrorType.MISSING_REQUIRED));
            } else if ((Boolean) result[1]) {
                report.getErrors().add(createErrorItem(result[2].toString(),
                        ItemType.OBJECT, ErrorType.ALIAS));
            }
        }
    }

    /**
     * Check that object has all required properties, as per objectValidation
     * If a required property is not provided, or not found in the element aliases,
     * create the error item.
     *
     * @param properties the properties to find
     * @param report the report to add errors
     * @param foundObjectValidation the object validation
     */
    private void findRequireProperties(List<Property> properties,
            ValidationReport report, ObjectValidation foundObjectValidation) {
        for (String requiredElement : foundObjectValidation.getRequiredElements()) {
            Object[] result = findRequireProperty(properties, requiredElement);
            if (!(Boolean) result[0]) {
                report.getErrors().add(createErrorItem(requiredElement,
                        ItemType.PROPERTY, ErrorType.MISSING_REQUIRED));
            } else if ((Boolean) result[1]) {
                report.getErrors().add(createErrorItem(result[2].toString(),
                        ItemType.PROPERTY, ErrorType.ALIAS));
            }
        }
    }

    /**
     * Finds the required object.
     *
     * @param children the children to find
     * @param requiredObject the required object
     * @return an boolean array of two elements, the first one represents if the required property found,
     *         the second one represents if the found required is provided as alias.
     */
    private Object[] findRequiredObject(List<MetadataObject> children,
            String requiredObject) {
        Object[] result = new Object[] {false, false, null};
        if (children != null) {
            for (MetadataObject child : children) {
                if (child.getName().equals(requiredObject)) {
                    result[0] = true;
                }

                for (ObjectAlias objectAlias : objectAliases) {
                    if (objectAlias.getAlias().equals(child.getName())) {
                        if (objectAlias.getFullName().equals(requiredObject)) {
                            result[0] = true;
                            result[1] = true;
                            result[2] = child.getName();
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * Finds the required property.
     *
     * @param properties the properties to find
     * @param requiredElement the required element
     * @return an boolean array of two elements, the first one represents if the required property found,
     *         the second one represents if the found required is provided as alias.
     */
    private Object[] findRequireProperty(List<Property> properties,
            String requiredElement) {
        Object[] result = new Object[] {false, false, null};
        if (properties != null) {
            for (Property property : properties) {
                if (property.getName().equals(requiredElement)) {
                    result[0] = true;
                }
                for (ElementAlias elementAlias : elementAliases) {
                    if (elementAlias.getAlias().equals(property.getName())) {
                        if (elementAlias.getFullName().equals(requiredElement)) {
                            result[0] = true;
                            result[1] = true;
                            result[2] = property.getName();
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * Checks if the objects are not allowed.
     *
     * @param children the children to validate
     * @param report the report to set error item
     * @param foundObjectValidation the validation object
     */
    private void checkObjectsNotAllow(List<MetadataObject> children,
            ValidationReport report, ObjectValidation foundObjectValidation) {
        ErrorItem errorItem;
        for (MetadataObject child : children) {
            boolean found = false;
            for (String requiredObject : foundObjectValidation.getRequiredObjects()) {
                if (requiredObject.equals(child.getName())) {
                    found = true;
                }
                // check if there is a alias
                if (!found) {
                    for (ObjectAlias objectAlias : objectAliases) {
                        if (child.getName().equals(objectAlias.getAlias())) {
                            if (requiredObject.equals(objectAlias.getFullName())) {
                                found = true;
                            }
                        }
                    }
                }
            }
            if (!found) {
                for (String optionalObject : foundObjectValidation.getOptionalObjects()) {
                    if (optionalObject.equals(child.getName())) {
                        found = true;
                    }
                }
            }
            if (!found) {
                // If a object/property is not allowed, create error item with these values
                errorItem = new ErrorItem();
                errorItem.setItemName(child.getName());
                errorItem.setItemType(ItemType.OBJECT);
                errorItem.setErrorType(ErrorType.NOT_ALLOWED);
                report.getErrors().add(errorItem);
            }
        }
    }

    /**
     * Checks if the properties are not allowed.
     *
     * @param properties the properties to validate
     * @param report the report to set error item
     * @param foundObjectValidation the validation object
     */
    private void checkPropertiesNotAllow(List<Property> properties,
            ValidationReport report, ObjectValidation foundObjectValidation) {
        for (Property property : properties) {
            boolean found = false;
            for (String requiredElement : foundObjectValidation.getRequiredElements()) {
                if (property.getName().equals(requiredElement)) {
                    found = true;
                }
                // check if there is a alias
                if (!found) {
                    for (ElementAlias elementAlias : elementAliases) {
                        if (property.getName().equals(elementAlias.getAlias())) {
                            if (requiredElement.equals(elementAlias.getFullName())) {
                                found = true;
                            }
                        }
                    }
                }
            }
            if (!found) {
                for (String requiredObject : foundObjectValidation.getOptionalElements()) {
                    if (property.getName().equals(requiredObject)) {
                        found = true;
                    }
                }
            }

            if (!found) {
                // check if it has globally allowed values
                if (foundObjectValidation.isGloballyAllowableElements()) {
                    found = false;
                    for (ElementValidation elementValidation : elementValidations) {
                        if (elementValidation.getName().equals(property.getName())) {
                            found = true;
                        }
                    }
                    if (!found) {
                        for (ElementAlias elementAlias : elementAliases) {
                            if (elementAlias.getAlias().equals(property.getName())) {
                                found = true;
                            }
                        }
                    }
                }
            }
            if (!found) {
                // If a object/property is not allowed, create error item with these values
                report.getErrors().add(createErrorItem(property.getName(),
                        ItemType.PROPERTY, ErrorType.NOT_ALLOWED));
            }
        }
    }

    /**
     * Check if the object's objects is misspelled for not allowed errors, also remove the missing
     * required errors if needed.
     *
     * @param report the validation report which contains not allowed and other errors, and it's errors
     *               need to be replaced or removed after checking misspelled
     */
    private void checkMisspelledObjects(ValidationReport report) {
        List<Map<String, Object>> misspelledObjects = new ArrayList<Map<String, Object>>();
        // If the object is missing due to misspelled, the misspelled one also be checked in
        // not allowed errors
        for (ErrorItem error : report.getErrors()) {
            if (error.getErrorType() == ErrorType.NOT_ALLOWED
                    && error.getItemType() == ItemType.OBJECT) {
                Map<String, Object> result = checkObjectMisspelled(error.getItemName());
                // Replace
                if (result != null) {
                    error.setErrorType(ErrorType.MISSPELLED);
                    misspelledObjects.add(result);
                }
            }
        }
        // Remove MISSING_REQUIRED error
        for (Map<String, Object> misspelledObject : misspelledObjects) {
            String perhapsRequired = misspelledObject.get("compare").toString();
            int removeIndex = -1;
            for (int i = 0; i < report.getErrors().size(); i++) {
                if (report.getErrors().get(i).getErrorType() == ErrorType.MISSING_REQUIRED
                        && report.getErrors().get(i).getItemType() == ItemType.OBJECT
                        && report.getErrors().get(i).getItemName().equals(perhapsRequired)) {
                    removeIndex = i;
                }
            }
            if (removeIndex != -1) {
                report.getErrors().remove(removeIndex);
            }
        }
    }

    /**
     * Check if the object's properties is misspelled for not allowed errors, also remove the missing
     * required errors if needed.
     *
     * @param report the validation report which contains not allowed and other errors, and it's errors
     *               need to be replaced or removed after checking misspelled
     *
     * @return a list contains the misspelled property names and the correct ones
     */
    private List<Map<String, Object>> checkMisspelledProperties(ValidationReport report) {
        List<Map<String, Object>> misspelledProperties = new ArrayList<Map<String, Object>>();
        // If the property is missing due to misspelled, the misspelled one also be checked in
        // not allowed errors
        for (ErrorItem error : report.getErrors()) {
            if (error.getErrorType() == ErrorType.NOT_ALLOWED
                    && error.getItemType() == ItemType.PROPERTY) {
                Map<String, Object> result = checkPropertyMisspelled(error.getItemName());
                // Replace
                if (result != null) {
                    error.setErrorType(ErrorType.MISSPELLED);
                    misspelledProperties.add(result);
                }
            }
        }
        // Remove MISSING_REQUIRED error
        for (Map<String, Object> misspelledProperty : misspelledProperties) {
            String perhapsRequired = misspelledProperty.get("compare").toString();
            int removeIndex = -1;
            for (int i = 0; i < report.getErrors().size(); i++) {
                if (report.getErrors().get(i).getErrorType() == ErrorType.MISSING_REQUIRED
                        && report.getErrors().get(i).getItemType() == ItemType.PROPERTY
                        && report.getErrors().get(i).getItemName().equals(perhapsRequired)) {
                    removeIndex = i;
                }
            }
            if (removeIndex != -1) {
                report.getErrors().remove(removeIndex);
            }
        }
        return misspelledProperties;
    }

    /**
     * Checks if the object name is misspelled.
     *
     * @param name the name to be checked if misspelled
     * @return a hash map that can be null, or contains the misspelled name, correct name,
     *         different words, or similar percentage
     */
    private Map<String, Object> checkObjectMisspelled(String name) {
        // If all-but one character or 90% of the object name is within an objectValidation
        // in objectValidations, then create a replacing error.
        // If also detected an alternate object that is at least 50% of the name as well,
        // then there is a conflict and the object is not misspelled
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        double similar = 0.00;
        for (ObjectValidation objectValidation : objectValidations) {
            Map<String, Object> result;
            for (String optionalObject : objectValidation.getOptionalObjects()) {
                result = checkMisspelled(name, optionalObject);
                similar = (Double) result.get("similar");
                if (similar >= misspelledConflictPercentage) {
                    results.add(result);
                }
            }
            for (String optionalObject : objectValidation.getRequiredObjects()) {
                result = checkMisspelled(name, optionalObject);
                similar = (Double) result.get("similar");
                if (similar >= misspelledConflictPercentage) {
                    results.add(result);
                }
            }
        }

        if (results.size() != 1) {
            return null;
        } else {
            return results.get(0); // Including one character different or on/above 0.9
        }
    }

    /**
     * Checks if the property name is misspelled.
     *
     * @param name the name to be checked if misspelled
     * @return a hash map that can be null, or contains the misspelled name, correct name,
     *         different words, or similar percentage
     */
    private Map<String, Object> checkPropertyMisspelled(String name) {
        // If all-but one character or 90% of the property name is within an elementValidation in
        // elementValidations, then create a replacing error
        // If either of the steps above also detected an alternate property that is at least
        // 50% of the name as well, then there is a conflict and the property is not misspelled.
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        double similar = 0.00;
        for (ElementValidation elementValidation : elementValidations) {
            Map<String, Object> result = checkMisspelled(name, elementValidation.getName());
            similar = (Double) result.get("similar");
            if (similar >= misspelledConflictPercentage) {
                results.add(result);
            }
        }
        if (results.size() != 1) {
            return null;
        } else {
            return results.get(0); // Including one character different or on/above 0.9
        }
    }

    /**
     * Checks if the value is misspelled.
     *
     * @param check the value to validate
     * @param compare the value to compare
     * @return a list of data
     */
    private Map<String, Object> checkMisspelled(String check, String compare) {
        Map<String, Object> result = new HashMap<String, Object>();
        int differentWord = 0;

        // Get the length of shorter one
        int shorterLength;
        if (check.length() > compare.length()) {
            shorterLength = compare.length();
        } else {
            shorterLength = check.length();
        }
        for (int i = 0; i < shorterLength; i++) {
            if (!check.substring(i, i + 1).equals(compare.substring(i, i + 1))) {
                differentWord++;
            }
        }
        double similar = (1.0 * (shorterLength - differentWord)) / shorterLength;
        result.put("differentWord", differentWord);
        result.put("similar", similar);
        result.put("check", check);
        result.put("compare", compare);
        return result;
    }

    /**
     * Checks if the property values are valid, if not, adds an error item.
     *
     * @param properties the properties to check
     * @param misspelledProperties contains the misspelled property names and the correct ones
     * @param report the validation report to add errors
     */
    private void checkPropertyValues(List<Property> properties,
            List<Map<String, Object>> misspelledProperties, ValidationReport report) {
        // For each property in the object, find the named ElementValidation (or one that is closest
        // in misspelling above), and apply all rules that are not null/empty (min max are only if
        // the value is a number)
        for (Property property : properties) {
            String realPropertyName = null;
            for (Map<String, Object> result : misspelledProperties) {
                if (result.get("check").toString().equals(property.getName())) {
                    realPropertyName = result.get("compare").toString();
                    break;
                }
            }
            boolean valid = true;
            for (ElementValidation elementValidation : elementValidations) {
                if (elementValidation.getName().equals(property.getName())
                        || elementValidation.getName().equals(realPropertyName)) {
                    if (property.getValues() != null) {
                        for (String value : property.getValues()) {
                            if (elementValidation.getAllowedValues() != null
                                    && !elementValidation.getAllowedValues().contains(value)) {
                                valid = false;
                                break;
                            }
                            if (elementValidation.getDataType() != null
                                    && (elementValidation.getDataType().toLowerCase().equals("integer")
                                    || elementValidation.getDataType().toLowerCase().equals("double")
                                    || elementValidation.getDataType().toLowerCase().equals("decimal")
                                    || elementValidation.getDataType().toLowerCase().equals("float"))) {
                                if (elementValidation.getMaximum() != null) { // xiufei - add
                                    if (Float.parseFloat(value) > elementValidation.getMaximum()
                                            || Float.parseFloat(value) < elementValidation.getMinimum()) {
                                        valid = false;
                                        break;
                                    }
                                }
                            } else {
                                if (elementValidation.getMaximumLength() != null
                                        && value.length() > elementValidation.getMaximumLength()) {
                                    valid = false;
                                    break;
                                }
                                if (elementValidation.getMinimumLength() != null
                                        && value.length() < elementValidation.getMinimumLength()) {
                                    valid = false;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            // If any rule is violated, then create an error
            if (!valid) {
                report.getErrors().add(createErrorItem(property.getName(),
                        ItemType.PROPERTY, ErrorType.INVALID));
            }
        }
    }

    /**
     * Creates the error item.
     *
     * @param itemName the item name
     * @param itemType the item type
     * @param errorType the error type
     * @return the created error item instance
     */
    private ErrorItem createErrorItem(String itemName, ItemType itemType, ErrorType errorType) {
        ErrorItem errorItem;
        errorItem = new ErrorItem();
        errorItem.setItemName(itemName);
        errorItem.setItemType(itemType);
        errorItem.setErrorType(errorType);
        return errorItem;
    }
}