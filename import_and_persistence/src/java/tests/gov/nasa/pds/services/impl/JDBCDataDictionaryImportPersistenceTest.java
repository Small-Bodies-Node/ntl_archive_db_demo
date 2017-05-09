/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import gov.nasa.pds.entities.ElementAlias;
import gov.nasa.pds.entities.MetadataObject;
import gov.nasa.pds.entities.ObjectAlias;
import gov.nasa.pds.entities.Property;
import gov.nasa.pds.entities.TargetType;
import gov.nasa.pds.entities.UnitAlias;
import gov.nasa.pds.services.DataDictionaryImportPersistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.JUnit4TestAdapter;

/**
 * <p>
 * The unit test of {@link JDBCDataDictionaryImportPersistence} class.
 * </p>
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
public class JDBCDataDictionaryImportPersistenceTest {
    /**
     * The sql connection for test.
     */
    private Connection conn;

    /**
     * The application context for test.
     */
    private ApplicationContext context;

    /**
     * The data dictionary import persistence for test.
     */
    private DataDictionaryImportPersistence dataDictionaryImportPersistence;

    /**
     * Adapter for JUnit 3.
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(JDBCDataDictionaryImportPersistenceTest.class);
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
     * Test method <code>insertObjectAliases(List&lt;ObjectAlias&gt; objectAliases)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertObjectAliases() throws Exception {
        List<ObjectAlias> objectAliases = new ArrayList<ObjectAlias>();
        ObjectAlias objectAlias = new ObjectAlias();
        objectAlias.setAlias("alias1");
        objectAlias.setFullName("fullName1");
        objectAliases.add(objectAlias);
        objectAlias = new ObjectAlias();
        objectAlias.setAlias("alias2");
        objectAlias.setFullName("fullName2");
        objectAliases.add(objectAlias);
        objectAlias = new ObjectAlias();
        objectAlias.setAlias("alias3");
        objectAlias.setFullName("fullName3");
        objectAliases.add(objectAlias);

        dataDictionaryImportPersistence.insertObjectAliases(objectAliases);
        ResultSet resultSet = TestHelper.query(conn, "Select * from object_alias order by id");
        int i = 0;
        while (resultSet.next()) {
            assertEquals("'alias' should be correct", objectAliases.get(i).getAlias(),
                    resultSet.getString("alias"));
            assertEquals("'full_name' should be correct", objectAliases.get(i).getFullName(),
                    resultSet.getString("full_name"));
            assertEquals("'id' should be correct", objectAliases.get(i).getId(),
                    resultSet.getLong("id"));
            i++;
        }
        assertEquals("should be three records", 3, i);
    }

    /**
     * Test method <code>insertElementAliases(List&lt;ElementAlias&gt; elementAliases)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertElementAliases() throws Exception {
        List<ElementAlias> elementAliases = new ArrayList<ElementAlias>();
        ElementAlias elementAlias = new ElementAlias();
        elementAlias.setAlias("alias1");
        elementAlias.setAnotherName("anotherName1");
        elementAlias.setFullName("fullName1");
        elementAliases.add(elementAlias);
        elementAlias = new ElementAlias();
        elementAlias.setAlias("alias2");
        elementAlias.setAnotherName("anotherName2");
        elementAlias.setFullName("fullName2");
        elementAliases.add(elementAlias);
        elementAlias = new ElementAlias();
        elementAlias.setAlias("alias3");
        elementAlias.setAnotherName("anotherName3");
        elementAlias.setFullName("fullName3");
        elementAliases.add(elementAlias);

        dataDictionaryImportPersistence.insertElementAliases(elementAliases);
        ResultSet resultSet = TestHelper.query(conn, "Select * from element_alias order by id");
        int i = 0;
        while (resultSet.next()) {
            assertEquals("'alias' should be correct", elementAliases.get(i).getAlias(),
                    resultSet.getString("alias"));
            assertEquals("'another_name' should be correct", elementAliases.get(i).getAnotherName(),
                    resultSet.getString("another_name"));
            assertEquals("'full_name' should be correct", elementAliases.get(i).getFullName(),
                    resultSet.getString("full_name"));
            assertEquals("'id' should be correct", elementAliases.get(i).getId(),
                    resultSet.getLong("id"));
            i++;
        }
        assertEquals("should be three records", 3, i);
    }

    /**
     * Test method <code>insertUnitAliases(List&lt;UnitAlias&gt; unitAliases)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertUnitAliases() throws Exception {
        List<UnitAlias> unitAliases = new ArrayList<UnitAlias>();
        UnitAlias unitAlias = new UnitAlias();
        unitAlias.setAlias("alias1");
        unitAlias.setFullName("fullName1");
        unitAliases.add(unitAlias);
        unitAlias = new UnitAlias();
        unitAlias.setAlias("alias2");
        unitAlias.setFullName("fullName2");
        unitAliases.add(unitAlias);
        unitAlias = new UnitAlias();
        unitAlias.setAlias("alias3");
        unitAlias.setFullName("fullName3");
        unitAliases.add(unitAlias);

        dataDictionaryImportPersistence.insertUnitAliases(unitAliases);
        ResultSet resultSet = TestHelper.query(conn, "Select * from unit_alias order by id");
        int i = 0;
        while (resultSet.next()) {
            assertEquals("'alias' should be correct", unitAliases.get(i).getAlias(),
                    resultSet.getString("alias"));
            assertEquals("'full_name' should be correct", unitAliases.get(i).getFullName(),
                    resultSet.getString("full_name"));
            assertEquals("'id' should be correct", unitAliases.get(i).getId(),
                    resultSet.getLong("id"));
            i++;
        }
        assertEquals("should be three records", 3, i);
    }

    /**
     * Test method <code>insertTargetTypes(List&lt;TargetType&gt; targetTypes)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertTargetTypes() throws Exception {
        List<TargetType> targetTypes = new ArrayList<TargetType>();
        TargetType targetType = new TargetType();
        targetType.setName("name1");
        targetTypes.add(targetType);
        targetType = new TargetType();
        targetType.setName("name2");
        targetTypes.add(targetType);
        targetType = new TargetType();
        targetType.setName("name3");
        targetTypes.add(targetType);

        dataDictionaryImportPersistence.insertTargetTypes(targetTypes);
        ResultSet resultSet = TestHelper.query(conn, "Select * from target_type order by id");
        int i = 0;
        while (resultSet.next()) {
            assertEquals("'name' should be correct", targetTypes.get(i).getName(),
                    resultSet.getString("name"));
            assertEquals("'id' should be correct", targetTypes.get(i).getId(),
                    resultSet.getLong("id"));
            i++;
        }
        assertEquals("should be three records", 3, i);
    }

    /**
     * Test method <code>insertElementDefinitions(List&lt;MetadataObject&gt; elementDefinitions)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertElementDefinitions() throws Exception {
        final String metadataObjectName = "ELEMENT_DEFINITION";

        List<MetadataObject> elementDefinitions = new ArrayList<MetadataObject>();

        // Each element definition object should have 'NAME' property defined. It's a property that
        // get stored into the database. There is a difference between 'NAME' property of the element
        // definition object (it can be any string value) and the name of the object itself, which is
        // always 'ELEMENT_DEFINITION' for element definition objects.

        List<String> names = Arrays.asList(
                "MRO:MS_TRUSS_LEG_240_A_TEMPERATURE",
                "CENTRAL_BODY_DISTANCE",
                "CH1:STATISTICAL_POLISHER_FILE_NAME");

        MetadataObject elementDefinition = new MetadataObject(metadataObjectName);
        elementDefinition.setProperties(new ArrayList<Property>());
        elementDefinition.getProperties().add(TestHelper.createProperty("NAME", names.get(0)));
        elementDefinition.getProperties().add(
                TestHelper.createProperty("propertyName1", "value1", "value2", "value3"));
        elementDefinition.getProperties().add(TestHelper.createProperty("propertyName2", "value4"));
        elementDefinition.getProperties().add(TestHelper.createProperty("propertyName3", "value5"));
        elementDefinitions.add(elementDefinition);

        elementDefinition = new MetadataObject(metadataObjectName);
        elementDefinition.setProperties(new ArrayList<Property>());
        elementDefinition.getProperties().add(TestHelper.createProperty("NAME", names.get(1)));
        elementDefinition.getProperties().add(TestHelper.createProperty("propertyName4", "value6"));
        elementDefinitions.add(elementDefinition);

        elementDefinition = new MetadataObject(metadataObjectName);
        elementDefinition.setProperties(new ArrayList<Property>());
        elementDefinition.getProperties().add(TestHelper.createProperty("NAME", names.get(2)));
        elementDefinitions.add(elementDefinition);

        dataDictionaryImportPersistence.insertElementDefinitions(elementDefinitions);

        ResultSet resultSet = TestHelper.query(conn, "Select * from element_definition order by id");
        int i = 0;
        while (resultSet.next()) {
            assertEquals("'name' should be correct", names.get(i), resultSet.getString("name"));
            assertEquals("'id' should be correct", elementDefinitions.get(i).getId(), resultSet.getLong("id"));
            i++;
        }
        assertEquals("should be three records", 3, i);

        resultSet = TestHelper.query(conn, "Select * from element_definition_lookup inner join"
            + " element_definition on element_definition.id = element_definition_lookup.element_id inner"
            + " join lookup_value on lookup_value.id = element_definition_lookup.lookup_id inner join"
            + " keyword on keyword.id = lookup_value.keyword_id order by element_definition_lookup.id");
        i = 0;
        String[] keywordNames = new String[] {"propertyName1", "propertyName1", "propertyName1",
            "propertyName2", "propertyName3", "propertyName4"};
        String[] values = new String[] {"value1", "value2", "value3", "value4", "value5", "value6"};
        String[] elementDefinitionName = new String[] {names.get(0), names.get(0), names.get(0), names.get(0),
                names.get(0), names.get(1)};
        while (resultSet.next()) {
            assertEquals("'keyword.name' should be correct", keywordNames[i],
                    resultSet.getString("keyword.name"));
            assertEquals("'value' should be correct", values[i], resultSet.getString("value"));
            assertEquals("'element_definition.name' should be correct", elementDefinitionName[i],
                    resultSet.getString("element_definition.name"));
            i++;
        }
        assertEquals("should be five records", 6, i);
    }

    /**
     * Test method <code>insertObjectDefinitions(List&lt;MetadataObject&gt; objectDefinitions)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertObjectDefinitions() throws Exception {
        // Metadata objects that represent object definitions can have two possible names
        final String metadataObjectName1 = "GENERIC_OBJECT_DEFINITION";
        final String metadataObjectName2 = "SPECIFIC_OBJECT_DEFINITION";

        List<MetadataObject> objectDefinitions = new ArrayList<MetadataObject>();

        // Each object definition object should have 'NAME' property defined. It's a property that
        // get stored into the database. There is a difference between 'NAME' property of the element
        // definition object (it can be any string value) and the name of the object itself, which is
        // always either 'GENERIC_OBJECT_DEFINITION' or 'SPECIFIC_OBJECT_DEFINITION' for element definition objects.

        List<String> names = Arrays.asList(
                "DATA_SET_HOUSEKEEPING",
                "DATA_SET_COLLECTION_INFO",
                "DATA_SET_HOST");

        MetadataObject objectDefinition = new MetadataObject(metadataObjectName1);
        objectDefinition.setProperties(new ArrayList<Property>());
        objectDefinition.getProperties().add(TestHelper.createProperty("NAME", names.get(0)));
        objectDefinition.getProperties().add(
                TestHelper.createProperty("propertyName1", "value1", "value2", "value3"));
        objectDefinition.getProperties().add(TestHelper.createProperty("propertyName2", "value4"));
        objectDefinition.getProperties().add(TestHelper.createProperty("propertyName3", "value5"));
        objectDefinition.getProperties().add(TestHelper.createProperty("REQUIRED_ELEMENT_SET", "value6"));
        objectDefinitions.add(objectDefinition);

        objectDefinition = new MetadataObject(metadataObjectName2);
        objectDefinition.setProperties(new ArrayList<Property>());
        objectDefinition.getProperties().add(TestHelper.createProperty("NAME", names.get(1)));
        objectDefinition.getProperties().add(TestHelper.createProperty("propertyName4", "value7"));
        objectDefinition.getProperties().add(TestHelper.createProperty("OPTIONAL_ELEMENT_SET", "value8"));
        objectDefinitions.add(objectDefinition);

        objectDefinition = new MetadataObject(metadataObjectName2);
        objectDefinition.setProperties(new ArrayList<Property>());
        objectDefinition.getProperties().add(TestHelper.createProperty("NAME", names.get(2)));
        objectDefinition.getProperties().add(TestHelper.createProperty("REQUIRED_OBJECT_SET", "value9"));
        objectDefinition.getProperties().add(TestHelper.createProperty("OPTIONAL_OBJECT_SET", "value10"));
        objectDefinitions.add(objectDefinition);

        dataDictionaryImportPersistence.insertObjectDefinitions(objectDefinitions);

        ResultSet resultSet = TestHelper.query(conn, "Select * from object_definition order by id");
        int i = 0;
        while (resultSet.next()) {
            assertEquals("'name' should be correct", names.get(i), resultSet.getString("name"));
            assertEquals("'id' should be correct", objectDefinitions.get(i).getId(), resultSet.getLong("id"));
            i++;
        }
        assertEquals("should be three records", 3, i);

        resultSet = TestHelper.query(conn, "Select * from object_definition_lookup inner join"
            + " object_definition on object_definition.id = object_definition_lookup.object_id inner"
            + " join lookup_value on lookup_value.id = object_definition_lookup.lookup_id inner join"
            + " keyword on keyword.id = lookup_value.keyword_id order by object_definition_lookup.id");
        i = 0;
        String[] keywordNames = new String[] {"propertyName1", "propertyName1", "propertyName1",
            "propertyName2", "propertyName3", "propertyName4"};
        String[] values = new String[] {"value1", "value2", "value3", "value4", "value5", "value7"};
        String[] objectDefinitionName = new String[] {names.get(0), names.get(0), names.get(0), names.get(0),
                names.get(0), names.get(1)};
        while (resultSet.next()) {
            assertEquals("'keyword.name' should be correct", keywordNames[i],
                    resultSet.getString("keyword.name"));
            assertEquals("'value' should be correct", values[i], resultSet.getString("value"));
            assertEquals("'object_definition.name' should be correct", objectDefinitionName[i],
                    resultSet.getString("object_definition.name"));
            i++;
        }
        assertEquals("should be five records", 6, i);
    }

    /**
     * Test method <code>insertValidationRules(List&lt;MetadataObject&gt; objectDefinitions,
     * List&lt;MetadataObject&gt; elementDefinitions)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertValidationRules() throws Exception {
        final String metadataObjectName_objectDefinition =  "GENERIC_OBJECT_DEFINITION";
        final String metadataObjectName_elementDefinition = "ELEMENT_DEFINITION";

        List<MetadataObject> objectDefinitions = new ArrayList<MetadataObject>();

        // 1. Create objects that have REQUIRED_... OR OPTIONAL_... properties
        MetadataObject objectDefinition = new MetadataObject(metadataObjectName_objectDefinition);
        objectDefinition.setProperties(new ArrayList<Property>());
        objectDefinition.getProperties().add(TestHelper.createProperty("NAME", "some_object"));
        objectDefinition.getProperties().add(
                TestHelper.createProperty("propertyName1", "value1", "value2", "value3"));
        objectDefinition.getProperties().add(TestHelper.createProperty("propertyName2", "value4"));
        objectDefinition.getProperties().add(TestHelper.createProperty("OPTIONAL_ELEMENT_SET", "PSDD2"));
        objectDefinition.getProperties().add(TestHelper.createProperty("REQUIRED_ELEMENT_SET", "value5"));
        objectDefinitions.add(objectDefinition);

        objectDefinition = new MetadataObject(metadataObjectName_objectDefinition);
        objectDefinition.setProperties(new ArrayList<Property>());
        objectDefinition.getProperties().add(TestHelper.createProperty("NAME", "another_object"));
        objectDefinition.getProperties().add(TestHelper.createProperty("propertyName4", "value6"));
        objectDefinition.getProperties().add(TestHelper.createProperty("OPTIONAL_ELEMENT_SET", "PSDD"));
        objectDefinition.getProperties().add(TestHelper.createProperty("REQUIRED_OBJECT_SET", "value7", "value8"));
        objectDefinition.getProperties().add(TestHelper.createProperty("REQUIRED_ELEMENT_SET", "value9"));
        objectDefinitions.add(objectDefinition);

        objectDefinition = new MetadataObject(metadataObjectName_objectDefinition);
        objectDefinition.setProperties(new ArrayList<Property>());
        objectDefinition.getProperties().add(TestHelper.createProperty("NAME", "third_object"));
        objectDefinition.getProperties().add(TestHelper.createProperty("OPTIONAL_ELEMENT_SET", "value10"));
        objectDefinition.getProperties().add(TestHelper.createProperty("OPTIONAL_OBJECT_SET", "value11"));
        objectDefinitions.add(objectDefinition);

        // 2a. Create objects that represent required or optional objects
        objectDefinition = new MetadataObject(metadataObjectName_objectDefinition);
        objectDefinition.setProperties(new ArrayList<Property>());
        objectDefinition.getProperties().add(TestHelper.createProperty("NAME", "value11"));
        objectDefinitions.add(objectDefinition);

        objectDefinition = new MetadataObject(metadataObjectName_objectDefinition);
        objectDefinition.setProperties(new ArrayList<Property>());
        objectDefinition.getProperties().add(TestHelper.createProperty("NAME", "value7"));
        objectDefinitions.add(objectDefinition);

        objectDefinition = new MetadataObject(metadataObjectName_objectDefinition);
        objectDefinition.setProperties(new ArrayList<Property>());
        objectDefinition.getProperties().add(TestHelper.createProperty("NAME", "value8"));
        objectDefinitions.add(objectDefinition);

        // 2b. Create elements that represent required or optional elements
        List<MetadataObject> elementDefinitions = new ArrayList<MetadataObject>();

        MetadataObject elementDefinition = new MetadataObject(metadataObjectName_elementDefinition);
        elementDefinition.setProperties(new ArrayList<Property>());
        elementDefinition.getProperties().add(TestHelper.createProperty("NAME", "value5"));
        elementDefinitions.add(elementDefinition);

        elementDefinition = new MetadataObject(metadataObjectName_elementDefinition);
        elementDefinition.setProperties(new ArrayList<Property>());
        elementDefinition.getProperties().add(TestHelper.createProperty("NAME", "PSDD2"));
        elementDefinitions.add(elementDefinition);

        elementDefinition = new MetadataObject(metadataObjectName_elementDefinition);
        elementDefinition.setProperties(new ArrayList<Property>());
        elementDefinition.getProperties().add(TestHelper.createProperty("NAME", "otherValue"));
        elementDefinitions.add(elementDefinition);

        elementDefinition = new MetadataObject(metadataObjectName_elementDefinition);
        elementDefinition.setProperties(new ArrayList<Property>());
        elementDefinition.getProperties().add(TestHelper.createProperty("NAME", "value9"));
        elementDefinitions.add(elementDefinition);

        elementDefinition = new MetadataObject(metadataObjectName_elementDefinition);
        elementDefinition.setProperties(new ArrayList<Property>());
        elementDefinition.getProperties().add(TestHelper.createProperty("NAME", "value10"));
        elementDefinitions.add(elementDefinition);

        dataDictionaryImportPersistence.insertObjectDefinitions(objectDefinitions);
        dataDictionaryImportPersistence.insertElementDefinitions(elementDefinitions);
        dataDictionaryImportPersistence.insertValidationRules(objectDefinitions, elementDefinitions);

        ResultSet resultSet = TestHelper.query(conn, "Select * from object_validation order by id");
        int i = 0;
        long[] objectIds = new long[] {objectDefinitions.get(1).getId(), objectDefinitions.get(1).getId(),
                objectDefinitions.get(2).getId()};
        long[] innerObjectIds = new long[] {objectDefinitions.get(4).getId(),
                objectDefinitions.get(5).getId(), objectDefinitions.get(3).getId()};
        boolean[] requireds = new boolean[] {true, true, false};
        while (resultSet.next()) {
            assertEquals("'object_id' should be correct", objectIds[i],
                    resultSet.getLong("object_id"));
            assertEquals("'inner_object_id' should be correct", innerObjectIds[i],
                    resultSet.getLong("inner_object_id"));
            assertEquals("'required' should be correct", requireds[i],
                    resultSet.getBoolean("required"));
            i++;
        }
        assertEquals("should be two records", 3, i);

        resultSet = TestHelper.query(conn, "Select * from element_validation order by id");
        i = 0;
        objectIds = new long[] {objectDefinitions.get(0).getId(), objectDefinitions.get(0).getId(),
                objectDefinitions.get(1).getId(), objectDefinitions.get(2).getId()};
        innerObjectIds = new long[] {elementDefinitions.get(0).getId(), elementDefinitions.get(1).getId(),
                elementDefinitions.get(3).getId(), elementDefinitions.get(4).getId()};
        requireds = new boolean[] {true, false, true, false};
        while (resultSet.next()) {
            assertEquals("'object_id' should be correct" + i, objectIds[i],
                    resultSet.getLong("object_id"));
            assertEquals("'inner_element_id' should be correct" + i, innerObjectIds[i],
                    resultSet.getLong("inner_element_id"));
            assertEquals("'required' should be correct" + i, requireds[i],
                    resultSet.getBoolean("required"));
            i++;
        }
        assertEquals("should be two records", 4, i);

        resultSet = TestHelper.query(conn,
                "Select * from object_definition where id = " + objectDefinitions.get(1).getId());
        i = 0;
        while (resultSet.next()) {
            assertTrue("'globally_allowed_elements' should be correct",
                    resultSet.getBoolean("globally_allowed_elements"));
            i++;
        }
        assertEquals("should be only one record", 1, i);
    }
}
