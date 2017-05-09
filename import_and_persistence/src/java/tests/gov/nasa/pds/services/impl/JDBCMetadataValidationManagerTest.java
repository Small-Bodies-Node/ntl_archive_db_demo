/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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
import gov.nasa.pds.services.DataDictionaryImportPersistence;
import gov.nasa.pds.services.MetadataValidationManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.JUnit4TestAdapter;

/**
 * <p>
 * The unit test of {@link JDBCMetadataValidationManager} class.
 * </p>
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
public class JDBCMetadataValidationManagerTest {
    /**
     * The sql connection for test.
     */
    private Connection conn;

    /**
     * The application context for test.
     */
    private ApplicationContext context;

    /**
     * The metadata validation manager for test.
     */
    private MetadataValidationManager metadataValidationManager;

    /**
     * The data dictionary import persistence for test.
     */
    private DataDictionaryImportPersistence dataDictionaryImportPersistence;

    /**
     * Adapter for JUnit 3.
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(JDBCMetadataValidationManagerTest.class);
    }

    /**
     * Sets up the unit tests.
     *
     * @throws Exception to JUnit.
     */
    @Before
    public void setUp() throws Exception {
        conn = TestHelper.getConnection();
        context = new ClassPathXmlApplicationContext("beans.xml");
        metadataValidationManager = (MetadataValidationManager) context.getBean("metadataValidationManager");
        dataDictionaryImportPersistence = (DataDictionaryImportPersistence) context.getBean(
                "dataDictionaryImportPersistence");
    }

    /**
     * Tears down the test environment.
     *
     * @throws Exception to JUnit.
     */
    @After
    public void tearDown() throws Exception {
        try {
            TestHelper.deleteData(conn);
        } finally {
            TestHelper.closeConnection(conn);
        }
    }

    /**
     * Test method <code>load()</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testLoad() throws Exception {
        insertValidationRules();
        dataDictionaryImportPersistence.insertElementAliases(createElementAliases());
        dataDictionaryImportPersistence.insertObjectAliases(createObjectAliases());

        metadataValidationManager.load();

        List<ObjectValidation> objectValidations = TestHelper.getObjectProperty(
                metadataValidationManager, "objectValidations");
        assertEquals("size should be correct", 5, objectValidations.size());
        int j = 0;
        for (ObjectValidation objectValidation : objectValidations) {
            // {"id":,"optionalObjects":["optionalObject1"],
            // "requiredObjects":["requiredObject1","necessaryA"],
            // "requiredElements":["requiredElement1","mustB"],"name":"object1",
            // "globallyAllowableElements":false,"optionalElements":["optionalElement1"]}

            // {"id":,"optionalObjects":[],"requiredObjects":[],"requiredElements":[],
            // "name":"emptyObject","globallyAllowableElements":false,"optionalElements":[]}

            // {"id":,"optionalObjects":[],"requiredObjects":[],"requiredElements":[],
            // "name":"requiredObject1","globallyAllowableElements":false,"optionalElements":[]}

            // {"id":,"optionalObjects":[],"requiredObjects":[],"requiredElements":[],
            // "name":"necessaryA","globallyAllowableElements":false,"optionalElements":[]}

            // {"id":,"optionalObjects":[],"requiredObjects":[],"requiredElements":[],
            // "name":"optionalObject1","globallyAllowableElements":false,"optionalElements":[]}
            String[] names = new String[] {
                "object1", "emptyObject", "requiredObject1", "necessaryA", "optionalObject1"};
            String[] optionalObjects = new String[] {
                "optionalObject1", null, null, null, null};
            String[] requiredObjects = new String[] {
                "requiredObject1,necessaryA", null, null, null, null};
            String[] requiredElements = new String[] {
                "requiredElement1,mustB", null, null, null, null};
            String[] optionalElements = new String[] {
                "optionalElement1", null, null, null, null};
            Boolean[] globallyAllowableElements = new Boolean[] {false, false, false, false, false};
            assertEquals("'name' should be correct", names[j], objectValidation.getName());
            checkStringList("optionalObjects", optionalObjects[j], objectValidation.getOptionalObjects());
            checkStringList("requiredObjects", requiredObjects[j], objectValidation.getRequiredObjects());
            checkStringList("requiredElements", requiredElements[j], objectValidation.getRequiredElements());
            checkStringList("optionalElements", optionalElements[j], objectValidation.getOptionalElements());
            assertEquals("'globallyAllowableElements' should be correct", globallyAllowableElements[j],
                    objectValidation.isGloballyAllowableElements());
            j++;
        }
        List<ElementValidation> elementValidations =  TestHelper.getObjectProperty(
                metadataValidationManager, "elementValidations");
        assertEquals("size should be correct", 3, elementValidations.size());
        j = 0;
        for (ElementValidation elementValidation : elementValidations) {
            // {"id":,"dataType":null,"minimum":null,"maximum":null,"name":"requiredElement1",
            // "minimumLength":2,"allowedValues":["allowed1","allowed2","allowed3"],"maximumLength":10}

            // {"id":,"dataType":null,"minimum":1.11000001430511474609375,
            // "maximum":100.1100006103515625,"name":"mustB","minimumLength":null,
            // "allowedValues":null,"maximumLength":null

            // {"id":,"dataType":null,"minimum":null,"maximum":null,"name":"optionalElement1",
            // "minimumLength":null,"allowedValues":null,"maximumLength":null}]
            String[] names = new String[] {
                "requiredElement1", "mustB", "optionalElement1"};
            assertEquals("'name' should be correct", names[j], elementValidation.getName());
            if (j == 0) {
                List<String> expectedAllowedValues = new ArrayList<String>();
                expectedAllowedValues.add("allowed1");
                expectedAllowedValues.add("allowed2");
                expectedAllowedValues.add("allowed3");
                assertTrue("'allowedValues' should be correct",
                        elementValidation.getAllowedValues().containsAll(expectedAllowedValues));
                assertNull("'dataType' should be correct", elementValidation.getDataType());
                assertNull("'maximum' should be correct", elementValidation.getMaximum());
                assertEquals("'maximumLength' should be correct", (Integer) 10, elementValidation.getMaximumLength());
                assertNull("'minimum' should be correct", elementValidation.getMinimum());
                assertEquals("'minimumLength' should be correct", (Integer) 2, elementValidation.getMinimumLength());
            } else if (j == 1) {
                assertNull("'allowedValues' should be correct", elementValidation.getAllowedValues());
                assertNull("'dataType' should be correct", elementValidation.getDataType());
                assertEquals("'maximum' should be correct", (Float) 100.11f, elementValidation.getMaximum());
                assertNull("'maximumLength' should be correct", elementValidation.getMaximumLength());
                assertEquals("'minimum' should be correct", (Float) 1.11f, elementValidation.getMinimum());
                assertNull("'minimumLength' should be correct", elementValidation.getMinimumLength());
            } else if (j == 2) {
                assertNull("'allowedValues' should be correct", elementValidation.getAllowedValues());
                assertNull("'dataType' should be correct", elementValidation.getDataType());
                assertNull("'maximum' should be correct", elementValidation.getMaximum());
                assertNull("'maximumLength' should be correct", elementValidation.getMaximumLength());
                assertNull("'minimum' should be correct", elementValidation.getMinimum());
                assertNull("'minimumLength' should be correct", elementValidation.getMinimumLength());
            }
            j++;
        }
        List<ElementAlias> elementAliases = TestHelper.getObjectProperty(
                metadataValidationManager, "elementAliases");
        assertEquals("size should be correct", 3, elementAliases.size());
        int i = 0;
        for (ElementAlias elementAlias : elementAliases) {
            i++;
            assertEquals("'alias' should be correct", "alias" + i, elementAlias.getAlias());
            assertEquals("'anotherName' should be correct", "anotherName" + i, elementAlias.getAnotherName());
            assertEquals("'fullName' should be correct", "fullName" + i, elementAlias.getFullName());
        }
        List<ObjectAlias> objectAliases =  TestHelper.getObjectProperty(
                metadataValidationManager, "objectAliases");
        assertEquals("size should be correct", 3, objectAliases.size());
        i = 0;
        for (ObjectAlias objectAlias : objectAliases) {
            i++;
            assertEquals("'alias' should be correct", "alias" + i, objectAlias.getAlias());
            assertEquals("'fullName' should be correct", "fullName" + i, objectAlias.getFullName());
        }
    }

    /**
     * Test method <code>validateMetadata(MetadataObject metaDataObject)</code>.
     * Tests with objectValidation not found.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testValidateMetadataWithObjectValidationNotFound() throws Exception {
        insertValidationRules();
        dataDictionaryImportPersistence.insertElementAliases(createElementAliases());
        dataDictionaryImportPersistence.insertObjectAliases(createObjectAliases());

        metadataValidationManager.load();
        MetadataObject object = new MetadataObject();
        object.setName("notFound");
        ValidationReport report = metadataValidationManager.validateMetadata(object);
        assertTrue("'valid should be correct", report.isValid());
        assertTrue("'errors' should be correct", report.getErrors().isEmpty());
    }

    /**
     * Test method <code>validateMetadata(MetadataObject metaDataObject)</code>.
     * Tests with objectValidation found in objectAliases. The founded objectValidation is with no
     * validation.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testValidateMetadataObjectValidationFound() throws Exception {
        insertValidationRules();
        dataDictionaryImportPersistence.insertElementAliases(createElementAliases());
        dataDictionaryImportPersistence.insertObjectAliases(createObjectAliases());

        metadataValidationManager.load();
        MetadataObject object = new MetadataObject();
        object.setName("emptyObject");
        ValidationReport report = metadataValidationManager.validateMetadata(object);
        assertTrue("'valid should be correct", report.isValid());
        assertTrue("'errors' should be correct", report.getErrors().isEmpty());
    }

    /**
     * Test method <code>validateMetadata(MetadataObject metaDataObject)</code>.
     * Tests with object name found in objectAliases and it's full name found in objectValidations.
     * The founded objectValidation is with no validation.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testValidateMetadataFullNameFoundInObjectValidations() throws Exception {
        insertValidationRules();
        dataDictionaryImportPersistence.insertElementAliases(createElementAliases());
        List<ObjectAlias> oObjectAliases = createObjectAliases();
        ObjectAlias objectAlias = new ObjectAlias();
        objectAlias.setAlias("emptyObjectAlias");
        objectAlias.setFullName("emptyObject");
        oObjectAliases.add(objectAlias);
        dataDictionaryImportPersistence.insertObjectAliases(oObjectAliases);
        metadataValidationManager.load();
        MetadataObject object = new MetadataObject();
        object.setName("emptyObjectAlias");
        ValidationReport report = metadataValidationManager.validateMetadata(object);
        assertFalse("'valid should be correct", report.isValid());
        assertEquals("'errors' should be correct", 1, report.getErrors().size());
        checkErrorItem(ErrorType.ALIAS, "emptyObjectAlias", ItemType.OBJECT, report.getErrors().get(0));
    }

    /**
     * Test method <code>validateMetadata(MetadataObject metaDataObject)</code>.
     * Tests with object name found in objectAliases but it's full name not found in objectValidations.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testValidateMetadataFullNameNotFoundInObjectValidations() throws Exception {
        insertValidationRules();
        dataDictionaryImportPersistence.insertElementAliases(createElementAliases());
        List<ObjectAlias> objectAliases = createObjectAliases();
        ObjectAlias objectAlias = new ObjectAlias();
        objectAlias.setAlias("emptyObjectAlias");
        objectAlias.setFullName("notFound");
        objectAliases.add(objectAlias);
        dataDictionaryImportPersistence.insertObjectAliases(objectAliases);
        metadataValidationManager.load();
        MetadataObject object = new MetadataObject();
        object.setName("emptyObjectAlias");
        ValidationReport report = metadataValidationManager.validateMetadata(object);
        assertTrue("'valid should be correct", report.isValid());
        assertTrue("'errors' should be correct", report.getErrors().isEmpty());
    }

    /**
     * Test methods <code>validateMetadata(MetadataObject metaDataObject)</code> and
     * <code>correctMetadata(MetadataObject metaDataObject, ValidationReport validationReport)</code>.
     * Tests with object name found in objectValidations.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testValidateMetadataAndCorrect1() throws Exception {
        insertValidationRules();
        dataDictionaryImportPersistence.insertElementAliases(createElementAliases());
        List<ObjectAlias> objectAliases = createObjectAliases();
        ObjectAlias objectAlias = new ObjectAlias();
        objectAlias.setAlias("object1Alias");
        objectAlias.setFullName("object1");
        objectAliases.add(objectAlias);
        dataDictionaryImportPersistence.insertObjectAliases(objectAliases);
        metadataValidationManager.load();
        MetadataObject object = new MetadataObject();
        object.setName("object1Alias");
        object.setChildren(new ArrayList<MetadataObject>());
        MetadataObject child = new MetadataObject();
        child.setName("requiredObject1");
        object.getChildren().add(child);

        object.setProperties(new ArrayList<Property>());
        Property property = new Property();
        property.setName("requiredElement1");
        List<String> values = new ArrayList<String>();
        values.add("allowed2");
        property.setValues(values);
        object.getProperties().add(property);
        property = new Property();
        property.setName("mustB");
        object.getProperties().add(property);
        ValidationReport report = metadataValidationManager.validateMetadata(object);
        assertFalse("'valid should be correct", report.isValid());
        assertEquals("'errors' should be correct", 2, report.getErrors().size());
        checkErrorItem(ErrorType.ALIAS, "object1Alias", ItemType.OBJECT,
                report.getErrors().get(0));
        checkErrorItem(ErrorType.MISSING_REQUIRED, "necessaryA", ItemType.OBJECT,
                report.getErrors().get(1));

        child = new MetadataObject();
        child.setName("necessaryA");
        object.getChildren().add(child);
        for (ErrorItem child2 : report.getErrors()) {
            child2.getItemName();
        }
        metadataValidationManager.correctMetadata(object, report);
        report = metadataValidationManager.validateMetadata(object);
        assertEquals("'errors' should be correct", 0, report.getErrors().size());
    }

    /**
     * Test methods <code>validateMetadata(MetadataObject metaDataObject)</code> and
     * <code>correctMetadata(MetadataObject metaDataObject, ValidationReport validationReport)</code>.
     * Tests with object name found in objectValidations.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testValidateMetadataAndCorrect2() throws Exception {
        insertValidationRules();

        List<ElementAlias> elementAliases = createElementAliases();
        ElementAlias elementAlias = new ElementAlias();
        elementAlias.setAlias("msB");
        elementAlias.setFullName("mustB");
        elementAlias.setAnotherName("anotherName");
        elementAliases.add(elementAlias);
        dataDictionaryImportPersistence.insertElementAliases(elementAliases);

        List<ObjectAlias> objectAliases = createObjectAliases();
        ObjectAlias objectAlias = new ObjectAlias();
        objectAlias.setAlias("ncsrA");
        objectAlias.setFullName("necessaryA");
        objectAliases.add(objectAlias);
        dataDictionaryImportPersistence.insertObjectAliases(objectAliases);

        metadataValidationManager.load();
        MetadataObject object = new MetadataObject();
        object.setName("object1");
        object.setChildren(new ArrayList<MetadataObject>());
        MetadataObject child = new MetadataObject();
        child.setName("requiredPbject1");
        object.getChildren().add(child);
        child = new MetadataObject();
        child.setName("ncsrA");
        object.getChildren().add(child);
        child = new MetadataObject();
        child.setName("optionalObject1");
        object.getChildren().add(child);

        object.setProperties(new ArrayList<Property>());
        Property property = new Property();
        property.setName("requiredWlement1");
        List<String> values = new ArrayList<String>();
        values.add("allowed2");
        values.add("notAllowed");
        property.setValues(values);
        object.getProperties().add(property);
        property = new Property();
        property.setName("msB");
        object.getProperties().add(property);
        property = new Property();
        property.setName("optionalElement1");
        object.getProperties().add(property);
        ValidationReport report = metadataValidationManager.validateMetadata(object);
        assertFalse("'valid should be correct", report.isValid());
        assertEquals("'errors' should be correct", 5, report.getErrors().size());
        checkErrorItem(ErrorType.ALIAS, "ncsrA", ItemType.OBJECT,
                report.getErrors().get(0));
        checkErrorItem(ErrorType.ALIAS, "msB", ItemType.PROPERTY,
                report.getErrors().get(1));
        checkErrorItem(ErrorType.MISSPELLED, "requiredPbject1", ItemType.OBJECT,
                report.getErrors().get(2));
        checkErrorItem(ErrorType.MISSPELLED, "requiredWlement1", ItemType.PROPERTY,
                report.getErrors().get(3));
        checkErrorItem(ErrorType.INVALID, "requiredWlement1", ItemType.PROPERTY,
                report.getErrors().get(4));

        metadataValidationManager.correctMetadata(object, report);
        report = metadataValidationManager.validateMetadata(object);
        assertEquals("'errors' should be correct", 1, report.getErrors().size());
        checkErrorItem(ErrorType.INVALID, "requiredElement1", ItemType.PROPERTY,
                report.getErrors().get(0));

        object.getProperties().get(0).getValues().remove(1);
        report = metadataValidationManager.validateMetadata(object);
        assertEquals("'errors' should be correct", 0, report.getErrors().size());
    }

    /**
     * Test methods <code>validateMetadata(MetadataObject metaDataObject)</code> and
     * <code>correctMetadata(MetadataObject metaDataObject, ValidationReport validationReport)</code>.
     * Tests with object name found in objectValidations, with empty object.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testValidateMetadataAndCorrect3() throws Exception {
        insertValidationRules();
        dataDictionaryImportPersistence.insertElementAliases(createElementAliases());
        dataDictionaryImportPersistence.insertObjectAliases(createObjectAliases());

        metadataValidationManager.load();
        MetadataObject object = new MetadataObject();
        object.setName("object1");
        ValidationReport report = metadataValidationManager.validateMetadata(object);
        assertFalse("'valid should be correct", report.isValid());
        assertEquals("'errors' should be correct", 4, report.getErrors().size());
        checkErrorItem(ErrorType.MISSING_REQUIRED, "requiredObject1", ItemType.OBJECT,
                report.getErrors().get(0));
        checkErrorItem(ErrorType.MISSING_REQUIRED, "necessaryA", ItemType.OBJECT,
                report.getErrors().get(1));
        checkErrorItem(ErrorType.MISSING_REQUIRED, "requiredElement1", ItemType.PROPERTY,
                report.getErrors().get(2));
        checkErrorItem(ErrorType.MISSING_REQUIRED, "mustB", ItemType.PROPERTY,
                report.getErrors().get(3));

        metadataValidationManager.correctMetadata(object, report);
        report = metadataValidationManager.validateMetadata(object);
        assertEquals("'errors' should be correct", 4, report.getErrors().size());
        checkErrorItem(ErrorType.MISSING_REQUIRED, "requiredObject1", ItemType.OBJECT,
                report.getErrors().get(0));
        checkErrorItem(ErrorType.MISSING_REQUIRED, "necessaryA", ItemType.OBJECT,
                report.getErrors().get(1));
        checkErrorItem(ErrorType.MISSING_REQUIRED, "requiredElement1", ItemType.PROPERTY,
                report.getErrors().get(2));
        checkErrorItem(ErrorType.MISSING_REQUIRED, "mustB", ItemType.PROPERTY,
                report.getErrors().get(3));

        object.setChildren(new ArrayList<MetadataObject>());
        MetadataObject child = new MetadataObject();
        child.setName("requiredObject1");
        object.getChildren().add(child);
        child = new MetadataObject();
        child.setName("necessaryA");
        object.getChildren().add(child);

        object.setProperties(new ArrayList<Property>());
        Property property = new Property();
        property.setName("requiredElement1");
        object.getProperties().add(property);
        property = new Property();
        property.setName("mustB");
        object.getProperties().add(property);
        report = metadataValidationManager.validateMetadata(object);
        assertEquals("'errors' should be correct", 0, report.getErrors().size());
    }

    /**
     * Test methods <code>validateMetadata(MetadataObject metaDataObject)</code> and
     * <code>correctMetadata(MetadataObject metaDataObject, ValidationReport validationReport)</code>.
     * Tests with object name found in objectValidations, and the object validation has globally allowed
     * values.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testValidateMetadataAndCorrect4() throws Exception {
        insertValidationRules2();
        dataDictionaryImportPersistence.insertElementAliases(createElementAliases());
        dataDictionaryImportPersistence.insertObjectAliases(createObjectAliases());

        metadataValidationManager.load();
        MetadataObject object = new MetadataObject();
        object.setName("object1");
        object.setChildren(new ArrayList<MetadataObject>());
        MetadataObject child = new MetadataObject();
        child.setName("requiredObject1");
        object.getChildren().add(child);
        child = new MetadataObject();
        child.setName("necessaryA");
        object.getChildren().add(child);
        child = new MetadataObject();
        child.setName("optionalAbject1");
        object.getChildren().add(child);

        object.setProperties(new ArrayList<Property>());
        Property property = new Property();
        property.setName("requiredElement1");
        List<String> values = new ArrayList<String>();
        values.add("allowed2");
        values.add("allowed3");
        property.setValues(values);
        object.getProperties().add(property);
        property = new Property();
        property.setName("must");
        object.getProperties().add(property);
        property = new Property();
        property.setName("alias1");
        object.getProperties().add(property);
        property = new Property();
        property.setName("optionalElement1");
        values = new ArrayList<String>();
        values.add("allowed2");
        values.add("allowed3");
        values.add("notAllowedLengthNotAllowed");
        property.setValues(values);
        object.getProperties().add(property);

        ValidationReport report = metadataValidationManager.validateMetadata(object);
        assertFalse("'valid should be correct", report.isValid());
        assertEquals("'errors' should be correct", 3, report.getErrors().size());
        checkErrorItem(ErrorType.MISSPELLED, "optionalAbject1", ItemType.OBJECT,
                report.getErrors().get(0));
        checkErrorItem(ErrorType.MISSPELLED, "must", ItemType.PROPERTY,
                report.getErrors().get(1));
        checkErrorItem(ErrorType.INVALID, "optionalElement1", ItemType.PROPERTY,
                report.getErrors().get(2));

        metadataValidationManager.correctMetadata(object, report);
        report = metadataValidationManager.validateMetadata(object);
        assertEquals("'errors' should be correct", 1, report.getErrors().size());
        checkErrorItem(ErrorType.INVALID, "optionalElement1", ItemType.PROPERTY,
                report.getErrors().get(0));

        object.getProperties().get(3).getValues().remove(2);
        report = metadataValidationManager.validateMetadata(object);
        assertEquals("'errors' should be correct", 0, report.getErrors().size());
    }

    /**
     * Test methods <code>validateMetadata(MetadataObject metaDataObject)</code> and
     * <code>correctMetadata(MetadataObject metaDataObject, ValidationReport validationReport)</code>.
     * Tests with object name found in objectValidations, and the object validation has globally allowed
     * values.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testValidateMetadataAndCorrect5() throws Exception {
        insertValidationRules2();
        dataDictionaryImportPersistence.insertElementAliases(createElementAliases());
        dataDictionaryImportPersistence.insertObjectAliases(createObjectAliases());

        metadataValidationManager.load();
        MetadataObject object = new MetadataObject();
        object.setName("object1");
        object.setChildren(new ArrayList<MetadataObject>());
        MetadataObject child = new MetadataObject();
        child.setName("requiredObject1");
        object.getChildren().add(child);
        child = new MetadataObject();
        child.setName("necessaryA");
        object.getChildren().add(child);

        object.setProperties(new ArrayList<Property>());
        Property property = new Property();
        property.setName("requiredElement1");
        List<String> values = new ArrayList<String>();
        values.add("allowed2");
        values.add("allowed3");
        property.setValues(values);
        object.getProperties().add(property);
        property = new Property();
        property.setName("mustB");
        object.getProperties().add(property);
        property = new Property();
        property.setName("optionalElement2");
        object.getProperties().add(property);
        property = new Property();
        property.setName("optionalElement1");
        values = new ArrayList<String>();
        values.add("n");
        property.setValues(values);
        object.getProperties().add(property);
        ValidationReport report = metadataValidationManager.validateMetadata(object);
        assertFalse("'valid should be correct", report.isValid());
        assertEquals("'errors' should be correct", 1, report.getErrors().size());
        checkErrorItem(ErrorType.INVALID, "optionalElement1", ItemType.PROPERTY,
                report.getErrors().get(0));

        metadataValidationManager.correctMetadata(object, report);
        report = metadataValidationManager.validateMetadata(object);
        assertEquals("'errors' should be correct", 1, report.getErrors().size());
        checkErrorItem(ErrorType.INVALID, "optionalElement1", ItemType.PROPERTY,
                report.getErrors().get(0));

        object.getProperties().get(3).getValues().remove(0);
        report = metadataValidationManager.validateMetadata(object);
        assertEquals("'errors' should be correct", 0, report.getErrors().size());
    }

    /**
     * Test methods <code>validateMetadata(MetadataObject metaDataObject)</code> and
     * <code>correctMetadata(MetadataObject metaDataObject, ValidationReport validationReport)</code>.
     * Tests with object name found in objectValidations, and the object validation has globally allowed
     * values, and misspelled conflict.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testValidateMetadataAndCorrect6() throws Exception {
        insertValidationRules2();
        dataDictionaryImportPersistence.insertElementAliases(createElementAliases());
        dataDictionaryImportPersistence.insertObjectAliases(createObjectAliases());

        metadataValidationManager.load();
        MetadataObject object = new MetadataObject();
        object.setName("object1");
        object.setChildren(new ArrayList<MetadataObject>());
        MetadataObject child = new MetadataObject();
        child.setName("requiredObject1");
        object.getChildren().add(child);
        child = new MetadataObject();
        child.setName("necessaryA");
        object.getChildren().add(child);
        child = new MetadataObject();
        child.setName("notAllowedObject");
        object.getChildren().add(child);

        object.setProperties(new ArrayList<Property>());
        Property property = new Property();
        property.setName("requiredElement1");
        List<String> values = new ArrayList<String>();
        values.add("allowed2");
        values.add("allowed3");
        property.setValues(values);
        object.getProperties().add(property);
        property = new Property();
        property.setName("mustB");
        values = new ArrayList<String>();
        values.add("1000.11");
        property.setValues(values);
        object.getProperties().add(property);
        property = new Property();
        property.setName("similar3");
        object.getProperties().add(property);
        ValidationReport report = metadataValidationManager.validateMetadata(object);
        assertFalse("'valid should be correct", report.isValid());
        assertEquals("'errors' should be correct", 3, report.getErrors().size());
        checkErrorItem(ErrorType.NOT_ALLOWED, "notAllowedObject", ItemType.OBJECT,
                report.getErrors().get(0));
        checkErrorItem(ErrorType.NOT_ALLOWED, "similar3", ItemType.PROPERTY,
                report.getErrors().get(1));
        checkErrorItem(ErrorType.INVALID, "mustB", ItemType.PROPERTY,
                report.getErrors().get(2));

        metadataValidationManager.correctMetadata(object, report);
        report = metadataValidationManager.validateMetadata(object);
        assertEquals("'errors' should be correct", 3, report.getErrors().size());
        checkErrorItem(ErrorType.NOT_ALLOWED, "notAllowedObject", ItemType.OBJECT,
                report.getErrors().get(0));
        checkErrorItem(ErrorType.NOT_ALLOWED, "similar3", ItemType.PROPERTY,
                report.getErrors().get(1));
        checkErrorItem(ErrorType.INVALID, "mustB", ItemType.PROPERTY,
                report.getErrors().get(2));

        object.getProperties().remove(2);
        object.getChildren().remove(2);
        object.getProperties().get(1).getValues().clear();
        report = metadataValidationManager.validateMetadata(object);
        assertEquals("'errors' should be correct", 0, report.getErrors().size());
    }

    /**
     * Inserts validation rules.
     *
     * @throws Exception to JUnit.
     */
    private void insertValidationRules() throws Exception {
        final String metadataObjectName_objectDefinition =  "GENERIC_OBJECT_DEFINITION";
        final String metadataObjectName_elementDefinition = "ELEMENT_DEFINITION";

        List<MetadataObject> objectDefinitions = new ArrayList<MetadataObject>();

        MetadataObject objectDefinition = new MetadataObject(metadataObjectName_objectDefinition);
        objectDefinition.setProperties(new ArrayList<Property>());
        objectDefinition.getProperties().add(TestHelper.createProperty("NAME", "object1"));
        objectDefinition.getProperties().add(TestHelper.createProperty(
                "REQUIRED_OBJECT_SET", "requiredObject1", "necessaryA"));
        objectDefinition.getProperties().add(TestHelper.createProperty(
                "REQUIRED_ELEMENT_SET", "requiredElement1", "mustB"));
        objectDefinition.getProperties().add(TestHelper.createProperty(
                "OPTIONAL_OBJECT_SET", "optionalObject1"));
        objectDefinition.getProperties().add(TestHelper.createProperty(
                "OPTIONAL_ELEMENT_SET", "optionalElement1"));
        objectDefinitions.add(objectDefinition);

        objectDefinition = new MetadataObject(metadataObjectName_objectDefinition);
        objectDefinition.setProperties(new ArrayList<Property>());
        objectDefinition.getProperties().add(TestHelper.createProperty("NAME", "emptyObject"));
        objectDefinitions.add(objectDefinition);

        objectDefinition = new MetadataObject(metadataObjectName_objectDefinition);
        objectDefinition.setProperties(new ArrayList<Property>());
        objectDefinition.getProperties().add(TestHelper.createProperty("NAME", "requiredObject1"));
        objectDefinitions.add(objectDefinition);

        objectDefinition = new MetadataObject(metadataObjectName_objectDefinition);
        objectDefinition.setProperties(new ArrayList<Property>());
        objectDefinition.getProperties().add(TestHelper.createProperty("NAME", "necessaryA"));
        objectDefinitions.add(objectDefinition);
        objectDefinition = new MetadataObject(metadataObjectName_objectDefinition);
        objectDefinition.setProperties(new ArrayList<Property>());
        objectDefinition.getProperties().add(TestHelper.createProperty("NAME", "optionalObject1"));
        objectDefinitions.add(objectDefinition);

        List<MetadataObject> elementDefinitions = new ArrayList<MetadataObject>();

        MetadataObject elementDefinition = new MetadataObject(metadataObjectName_elementDefinition);
        elementDefinition.setProperties(new ArrayList<Property>());
        elementDefinition.getProperties().add(createProperty("NAME", "requiredElement1"));
        elementDefinition.getProperties().add(createProperty("MAXIMUM_LENGTH", "10"));
        elementDefinition.getProperties().add(createProperty("MINIMUM_LENGTH", "2"));
        Property property = new Property();
        property.setName("STANDARD_VALUE_SET");
        List<String> values = new ArrayList<String>();
        values.add("allowed1");
        values.add("allowed2");
        values.add("allowed3");
        property.setValues(values);
        elementDefinition.getProperties().add(property);
        elementDefinitions.add(elementDefinition);

        elementDefinition = new MetadataObject(metadataObjectName_elementDefinition);
        elementDefinition.setProperties(new ArrayList<Property>());
        elementDefinition.getProperties().add(createProperty("NAME", "mustB"));
        elementDefinition.getProperties().add(createProperty("MAXIMUM", "100.11"));
        elementDefinition.getProperties().add(createProperty("MINIMUM", "1.11"));
        property = new Property();
        property.setName("STANDARD_VALUE_SET");
        elementDefinitions.add(elementDefinition);

        elementDefinition = new MetadataObject(metadataObjectName_elementDefinition);
        elementDefinition.setProperties(new ArrayList<Property>());
        elementDefinition.getProperties().add(createProperty("NAME", "optionalElement1"));
        elementDefinitions.add(elementDefinition);

        dataDictionaryImportPersistence.insertObjectDefinitions(objectDefinitions);
        dataDictionaryImportPersistence.insertElementDefinitions(elementDefinitions);
        dataDictionaryImportPersistence.insertValidationRules(objectDefinitions, elementDefinitions);
    }

    /**
     * Inserts validation rules, the first object validation has globally allowed values.
     *
     * @throws Exception to JUnit.
     */
    private void insertValidationRules2() throws Exception {
        final String metadataObjectName_objectDefinition =  "SPECIFIC_OBJECT_DEFINITION";
        final String metadataObjectName_elementDefinition = "ELEMENT_DEFINITION";

        List<MetadataObject> objectDefinitions = new ArrayList<MetadataObject>();

        MetadataObject objectDefinition = new MetadataObject(metadataObjectName_objectDefinition);
        objectDefinition.setProperties(new ArrayList<Property>());
        objectDefinition.getProperties().add(createProperty("NAME", "object1"));
        objectDefinition.getProperties().add(TestHelper.createProperty(
                "REQUIRED_OBJECT_SET", "requiredObject1", "necessaryA"));
        objectDefinition.getProperties().add(TestHelper.createProperty(
                "REQUIRED_ELEMENT_SET", "requiredElement1", "mustB"));
        objectDefinition.getProperties().add(TestHelper.createProperty(
                "OPTIONAL_OBJECT_SET", "optionalObject1"));
        objectDefinition.getProperties().add(TestHelper.createProperty(
                "OPTIONAL_ELEMENT_SET", "optionalElement1", "PSDD"));
        objectDefinitions.add(objectDefinition);

        objectDefinition = new MetadataObject(metadataObjectName_objectDefinition);
        objectDefinition.setProperties(new ArrayList<Property>());
        objectDefinition.getProperties().add(createProperty("NAME", "emptyObject"));
        objectDefinitions.add(objectDefinition);

        objectDefinition = new MetadataObject(metadataObjectName_objectDefinition);
        objectDefinition.setProperties(new ArrayList<Property>());
        objectDefinition.getProperties().add(createProperty("NAME", "requiredObject1"));
        objectDefinitions.add(objectDefinition);

        objectDefinition = new MetadataObject(metadataObjectName_objectDefinition);
        objectDefinition.setProperties(new ArrayList<Property>());
        objectDefinition.getProperties().add(createProperty("NAME", "necessaryA"));
        objectDefinitions.add(objectDefinition);
        objectDefinition = new MetadataObject(metadataObjectName_objectDefinition);
        objectDefinition.setProperties(new ArrayList<Property>());
        objectDefinition.getProperties().add(createProperty("NAME", "optionalObject1"));
        objectDefinitions.add(objectDefinition);

        List<MetadataObject> elementDefinitions = new ArrayList<MetadataObject>();

        MetadataObject elementDefinition = new MetadataObject(metadataObjectName_elementDefinition);
        elementDefinition.setProperties(new ArrayList<Property>());
        elementDefinition.getProperties().add(createProperty("NAME", "requiredElement1"));
        elementDefinition.getProperties().add(createProperty("MAXIMUM_LENGTH", "10"));
        elementDefinition.getProperties().add(createProperty("MINIMUM_LENGTH", "2"));
        Property property = new Property();
        property.setName("STANDARD_VALUE_SET");
        List<String> values = new ArrayList<String>();
        values.add("allowed1");
        values.add("allowed2");
        values.add("allowed3");
        property.setValues(values);
        elementDefinition.getProperties().add(property);
        elementDefinitions.add(elementDefinition);

        elementDefinition = new MetadataObject(metadataObjectName_elementDefinition);
        elementDefinition.setProperties(new ArrayList<Property>());
        elementDefinition.getProperties().add(createProperty("NAME", "mustB"));
        elementDefinition.getProperties().add(createProperty("GENERAL_DATA_TYPE", "DOUBLE"));
        elementDefinition.getProperties().add(createProperty("MAXIMUM", "100.11"));
        elementDefinition.getProperties().add(createProperty("MINIMUM", "1.11"));
        elementDefinitions.add(elementDefinition);

        elementDefinition = new MetadataObject(metadataObjectName_elementDefinition);
        elementDefinition.setProperties(new ArrayList<Property>());
        elementDefinition.getProperties().add(createProperty("NAME", "optionalElement1"));
        elementDefinition.getProperties().add(createProperty("MAXIMUM_LENGTH", "10"));
        elementDefinition.getProperties().add(createProperty("MINIMUM_LENGTH", "2"));
        elementDefinitions.add(elementDefinition);

        elementDefinition = new MetadataObject(metadataObjectName_elementDefinition);
        elementDefinition.setProperties(new ArrayList<Property>());
        elementDefinition.getProperties().add(createProperty("NAME", "optionalElement2"));
        elementDefinitions.add(elementDefinition);

        elementDefinition = new MetadataObject(metadataObjectName_elementDefinition);
        elementDefinition.setProperties(new ArrayList<Property>());
        elementDefinition.getProperties().add(createProperty("NAME", "similar1"));
        elementDefinitions.add(elementDefinition);

        elementDefinition = new MetadataObject(metadataObjectName_elementDefinition);
        elementDefinition.setProperties(new ArrayList<Property>());
        elementDefinition.getProperties().add(createProperty("NAME", "similar2"));
        elementDefinitions.add(elementDefinition);

        dataDictionaryImportPersistence.insertObjectDefinitions(objectDefinitions);
        dataDictionaryImportPersistence.insertElementDefinitions(elementDefinitions);
        dataDictionaryImportPersistence.insertValidationRules(objectDefinitions, elementDefinitions);
    }

    /**
     * Creates element aliases.
     *
     * @return a list of element aliases
     */
    private List<ElementAlias> createElementAliases() {
        List<ElementAlias> elementAliases = new ArrayList<ElementAlias>();
        ElementAlias elementAlias;
        for (int i = 1; i < 4; i++) {
            elementAlias = new ElementAlias();
            elementAlias.setAlias("alias" + i);
            elementAlias.setAnotherName("anotherName" + i);
            elementAlias.setFullName("fullName" + i);
            elementAliases.add(elementAlias);
        }
        return elementAliases;
    }

    /**
     * Creates object aliases.
     *
     * @return a list of object aliases
     */
    private List<ObjectAlias> createObjectAliases() {
        List<ObjectAlias> objectAliases = new ArrayList<ObjectAlias>();
        ObjectAlias objectAlias;
        for (int i = 1; i < 4; i++) {
            objectAlias = new ObjectAlias();
            objectAlias.setAlias("alias" + i);
            objectAlias.setFullName("fullName" + i);
            objectAliases.add(objectAlias);
        }
        return objectAliases;
    }

    /**
     * Creates a new property.
     *
     * @param name the name of the property
     * @param value the value of the values of the property
     * @return the created property
     */
    private Property createProperty(String name, String value) {
        Property property = new Property();
        property.setName(name);
        List<String> values = new ArrayList<String>();
        values.add(value);
        property.setValues(values);
        return property;
    }

    /**
     * Checks if the error item is correct.
     *
     * @param errorType the error type
     * @param itemName the item name
     * @param itemType the item type
     * @param errorItem the error item to be check
     */
    private void checkErrorItem(ErrorType errorType, String itemName, ItemType itemType, ErrorItem errorItem) {
        assertEquals("'errorType' should be correct", errorType, errorItem.getErrorType());
        assertEquals("'itemName' should be correct", itemName, errorItem.getItemName());
        assertEquals("'itemType' should be correct", itemType, errorItem.getItemType());
    }

    /**
     * Checks if the list value is correct.
     *
     * @param name the type name
     * @param value the expected value
     * @param lst the list to be check
     */
    private void checkStringList(String name, String value, List<String> lst) {
        if (value == null) {
            assertTrue("'" + name + "' should be correct", lst.isEmpty());
        } else {
            String[] split = value.split(",");
            List<String> all = new ArrayList<String>();
            for (String each : split) {
                all.add(each);
            }
            assertTrue("'" + name + "' should be correct",
                    lst.containsAll(all));
        }
    }
}
