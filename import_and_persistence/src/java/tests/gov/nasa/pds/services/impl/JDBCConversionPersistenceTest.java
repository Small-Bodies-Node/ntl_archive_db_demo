/*
 * Copyright (C) 2011-2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import gov.nasa.pds.entities.DataFile;
import gov.nasa.pds.entities.DataSet;
import gov.nasa.pds.entities.Instrument;
import gov.nasa.pds.entities.InstrumentHost;
import gov.nasa.pds.entities.MetadataObject;
import gov.nasa.pds.entities.Mission;
import gov.nasa.pds.entities.Product;
import gov.nasa.pds.entities.Property;
import gov.nasa.pds.entities.RecordType;
import gov.nasa.pds.entities.Reference;
import gov.nasa.pds.entities.Target;
import gov.nasa.pds.entities.TargetType;
import gov.nasa.pds.entities.Volume;
import gov.nasa.pds.processors.impl.profile.cassini.CassiniObservation;
import gov.nasa.pds.processors.impl.profile.cassini.CassiniObservationInfo;
import gov.nasa.pds.services.ConversionPersistence;
import gov.nasa.pds.services.ConversionPersistence.TableInfo;
import gov.nasa.pds.services.DataDictionaryImportPersistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * The unit test of {@link JDBCConversionPersistence} class.
 * </p>
 * 
 * <p>
 * Version 1.1 changes [PDS - Import and Persistence Update - Assembly Contest]:
 * <ol>
 * <li>Fixed old code for new schema.</li>
 * <li>Added testInsertObservationInfo test.</li>
 * </ol>
 * </p>
 * 
 * @author KennyAlive
 * @version 1.1
 */
public class JDBCConversionPersistenceTest {
    /**
     * The sql connection for test.
     */
    private Connection conn;

    /**
     * The application context for test.
     */
    private ApplicationContext context;

    /**
     * The conversion persistence for test.
     */
    private ConversionPersistence conversionPersistence;

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
        return new JUnit4TestAdapter(JDBCConversionPersistenceTest.class);
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
        conversionPersistence = (ConversionPersistence) context.getBean("conversionPersistence");
        dataDictionaryImportPersistence = (DataDictionaryImportPersistence) context.getBean(
                "dataDictionaryImportPersistence");

        TestHelper.execute(conn, "DELETE FROM `table_counter`");
        TestHelper.execute(conn, "INSERT INTO `table_counter` VALUES (1,0)");
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
            TestHelper.execute(conn, "drop table IF EXISTS dummyTable");
        } finally {
            TestHelper.closeConnection(conn);
        }
    }

    /**
     * Test method <code>insertVolume(Volume volume)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testCreateTableStructure() throws Exception {
        TableInfo tableInfo = conversionPersistence.createTable(
                Arrays.asList(new String[] { "columnName1", "columnName2", "columnName3" }),
                Arrays.asList(new Integer[] { 20, 255, 2556 }));
        conversionPersistence.dropTable(tableInfo.getSQLTableName());
    }

    /**
     * Test method <code>insertVolume(Volume volume)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertVolume() throws Exception {
        Volume volume = new Volume();
        volume.setName("name");
        volume.setDescription("description");
        volume.setTextId("textId");
        volume.setSetTextId("setTextId");
        volume.setSetName("setName");
        volume.setSeriesName("seriesName");

        // other children
        volume.setOtherChildren(new ArrayList<MetadataObject>());
        volume.getOtherChildren().add(TestHelper.createMetadataObject("name1"));
        volume.getOtherChildren().get(0).getProperties().add(TestHelper.createProperty("name2", "value1", "value2"));
        volume.getOtherChildren().get(0).setChildren(new ArrayList<MetadataObject>());
        volume.getOtherChildren().get(0).getChildren().add(TestHelper.createMetadataObject("name3"));
        volume.getOtherChildren().get(0).getChildren().get(0).getProperties()
            .add(TestHelper.createProperty("name4", "value3", "value4"));
        volume.getOtherChildren().get(0).getChildren().get(0).getProperties()
            .add(TestHelper.createProperty("name5", "value5", "value6"));

        // other properties
        volume.setOtherProperties(new ArrayList<Property>());
        volume.getOtherProperties().add(TestHelper.createProperty("name6", "value7", "value8", "value9"));

        long volumeId = conversionPersistence.insertVolume(volume);
        conversionPersistence.clearCaches();
        ResultSet resultSet = TestHelper.query(conn,
                "Select * from volume where id = " + volumeId);
        int i = 0;
        while (resultSet.next()) {
            assertEquals("'name' should be correct", volume.getName(),
                    resultSet.getString("name"));
            assertEquals("'description' should be correct", volume.getDescription(),
                    resultSet.getString("description"));
            assertEquals("'volume_text_id' should be correct", volume.getTextId(),
                    resultSet.getString("volume_text_id"));
            assertEquals("'volume_text_id' should be correct", volume.getSetTextId(),
                    resultSet.getString("volume_set_text_id"));
            assertEquals("'volume_set_name' should be correct", volume.getSetName(),
                    resultSet.getString("volume_set_name"));
            assertEquals("'volume_series_name' should be correct", volume.getSeriesName(),
                    resultSet.getString("volume_series_name"));
            assertEquals("'id' should be correct", volume.getId(),
                    resultSet.getLong("id"));
            i++;
        }
        assertEquals("should be only one record", 1, i);

        checkMetadataAndXref(volumeId, "volume",
                new String[] {null, "value7", "value8", "value9"},
                new String[] {"name1", "name6", "name6", "name6"},
                4,
                new String[] {"value1", "value2", null, "value3", "value4", "value5", "value6"},
                new String[] {"name2", "name2", "name3", "name4", "name4", "name5", "name5"},
                new String[] {"name1", "name1", "name1", "name3", "name3", "name3", "name3"},
                7
        );
    }

    /**
     * Test method <code>insertVolume(Volume volume)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertVolumeNoLink() throws Exception {
        Volume volume = new Volume();
        volume.setName("name");
        volume.setDescription("description");
        volume.setTextId("textId");
        volume.setSetTextId("setTextId");
        volume.setSetName("setName");
        volume.setSeriesName("seriesName");

        long volumeId = conversionPersistence.insertVolume(volume);
        ResultSet resultSet = TestHelper.query(conn,
                "Select * from volume where id = " + volumeId);
        int i = 0;
        while (resultSet.next()) {
            assertEquals("'name' should be correct", volume.getName(),
                    resultSet.getString("name"));
            assertEquals("'description' should be correct", volume.getDescription(),
                    resultSet.getString("description"));
            assertEquals("'volume_text_id' should be correct", volume.getTextId(),
                    resultSet.getString("volume_text_id"));
            assertEquals("'volume_text_id' should be correct", volume.getSetTextId(),
                    resultSet.getString("volume_set_text_id"));
            assertEquals("'volume_set_name' should be correct", volume.getSetName(),
                    resultSet.getString("volume_set_name"));
            assertEquals("'volume_series_name' should be correct", volume.getSeriesName(),
                    resultSet.getString("volume_series_name"));
            assertEquals("'id' should be correct", volume.getId(),
                    resultSet.getLong("id"));
            i++;
        }
        assertEquals("should be only one record", 1, i);

        checkMetadataAndXref(volumeId, "volume",
                new String[] {},
                new String[] {},
                0,
                new String[] {},
                new String[] {},
                new String[] {},
                0
        );
    }

    /**
     * Test method <code>insertReference(Reference reference)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertReference() throws Exception {
        Reference reference = new Reference();
        reference.setDescription("description");
        reference.setKeyTextId("keyTextId");
        long referenceId = conversionPersistence.insertReference(reference);
        ResultSet resultSet = TestHelper.query(conn,
                "Select * from reference where id = " + referenceId);
        int i = 0;
        while (resultSet.next()) {
            assertEquals("'reference_key_text_id' should be correct", reference.getKeyTextId(),
                    resultSet.getString("reference_key_text_id"));
            assertEquals("'description' should be correct", reference.getDescription(),
                    resultSet.getString("description"));
            assertEquals("'id' should be correct", reference.getId(),
                    resultSet.getLong("id"));
            i++;
        }
        assertEquals("should be only one record", 1, i);
    }

    /**
     * Test method <code>insertInstrumentHost(InstrumentHost instrumentHost)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertInstrumentHost() throws Exception {
        InstrumentHost instrumentHost = new InstrumentHost();
        instrumentHost.setName("name");
        instrumentHost.setTextId("textId");

        //references
        instrumentHost.setReferences(new ArrayList<Reference>());
        Reference reference = new Reference();
        reference.setDescription("description1");
        reference.setKeyTextId("keyTextId1");
        long[] referenceIds = new long[2];
        referenceIds[0] = conversionPersistence.insertReference(reference);
        reference.setId(referenceIds[0]);
        instrumentHost.getReferences().add(reference);
        reference = new Reference();
        reference.setDescription("description2");
        reference.setKeyTextId("keyTextId2");
        referenceIds[1] = conversionPersistence.insertReference(reference);
        reference.setId(referenceIds[1]);
        instrumentHost.getReferences().add(reference);

        instrumentHost.setOtherChildren(new ArrayList<MetadataObject>());
        instrumentHost.setOtherChildren(new ArrayList<MetadataObject>());
        instrumentHost.getOtherChildren().add(TestHelper.createMetadataObject("name1"));
        instrumentHost.getOtherChildren().get(0).getProperties().add(
                TestHelper.createProperty("name2", "value1", "value2"));
        instrumentHost.getOtherChildren().get(0).setChildren(new ArrayList<MetadataObject>());
        instrumentHost.getOtherChildren().get(0).getChildren().add(TestHelper.createMetadataObject("name3"));
        instrumentHost.getOtherChildren().get(0).getChildren().get(0).getProperties()
            .add(TestHelper.createProperty("name4", "value3", "value4"));
        instrumentHost.getOtherChildren().get(0).getChildren().get(0).getProperties()
            .add(TestHelper.createProperty("name5", "value5", "value6"));

        long instrumentHostId = conversionPersistence.insertInstrumentHost(instrumentHost);
        conversionPersistence.clearCaches();
        ResultSet resultSet = TestHelper.query(conn,
                "Select * from instrument_host where id = " + instrumentHostId);
        int i = 0;
        while (resultSet.next()) {
            assertEquals("'name' should be correct", instrumentHost.getName(),
                    resultSet.getString("name"));
            assertEquals("'instrument_host_text_id' should be correct", instrumentHost.getTextId(),
                    resultSet.getString("instrument_host_text_id"));
            assertEquals("'id' should be correct", instrumentHost.getId(),
                    resultSet.getLong("id"));
            i++;
        }
        assertEquals("should be only one record", 1, i);

        // check instrument_host_reference table
        resultSet = TestHelper.query(conn,
                "Select * from instrument_host_reference where instrument_host_id = " + instrumentHostId);
        i = 0;
        while (resultSet.next()) {
            assertEquals("'reference_id' should be correct", instrumentHost.getReferences().get(i).getId(),
                    resultSet.getLong("reference_id"));
            i++;
        }
        assertEquals("should be two records", 2, i);

        checkMetadataAndXref(instrumentHostId, "instrument_host", new String[] {null},
                new String[] {"name1", "name6", "name6", "name6"},
                1,
                new String[] {"value1", "value2", null, "value3", "value4", "value5", "value6"},
                new String[] {"name2", "name2", "name3", "name4", "name4", "name5", "name5"},
                new String[] {"name1", "name1", "name1", "name3", "name3", "name3", "name3"},
                7
        );
    }

    /**
     * Test method <code>insertInstrumentHost(InstrumentHost instrumentHost)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertInstrumentHostNoLink() throws Exception {
        InstrumentHost instrumentHost = new InstrumentHost();
        instrumentHost.setName("name");
        instrumentHost.setTextId("textId");

        long instrumentHostId = conversionPersistence.insertInstrumentHost(instrumentHost);
        ResultSet resultSet = TestHelper.query(conn,
                "Select * from instrument_host where id = " + instrumentHostId);
        int i = 0;
        while (resultSet.next()) {
            assertEquals("'name' should be correct", instrumentHost.getName(),
                    resultSet.getString("name"));
            assertEquals("'instrument_host_text_id' should be correct", instrumentHost.getTextId(),
                    resultSet.getString("instrument_host_text_id"));
            assertEquals("'id' should be correct", instrumentHost.getId(),
                    resultSet.getLong("id"));
            i++;
        }
        assertEquals("should be only one record", 1, i);

        // check instrument_host_reference table
        resultSet = TestHelper.query(conn,
                "Select * from instrument_host_reference where instrument_host_id = " + instrumentHostId);
        i = 0;
        while (resultSet.next()) {
            assertEquals("'reference_id' should be correct", instrumentHost.getReferences().get(i).getId(),
                    resultSet.getLong("reference_id"));
            i++;
        }
        assertEquals("should be no records", 0, i);

        checkMetadataAndXref(instrumentHostId, "instrument_host", new String[] {},
                new String[] {},
                0,
                new String[] {},
                new String[] {},
                new String[] {},
                0
        );
    }

    /**
     * Test method <code>insertInstrument(Instrument instrument)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertInstrument() throws Exception {
        Instrument instrument = new Instrument();
        instrument.setDescription("description");
        instrument.setName("name");
        instrument.setTextId("textId");
        instrument.setType("type");

        //references
        instrument.setReferences(new ArrayList<Reference>());
        Reference reference = new Reference();
        reference.setDescription("description1");
        reference.setKeyTextId("keyTextId1");
        long[] referenceIds = new long[2];
        referenceIds[0] = conversionPersistence.insertReference(reference);
        reference.setId(referenceIds[0]);
        instrument.getReferences().add(reference);
        reference = new Reference();
        reference.setDescription("description2");
        reference.setKeyTextId("keyTextId2");
        referenceIds[1] = conversionPersistence.insertReference(reference);
        reference.setId(referenceIds[1]);
        instrument.getReferences().add(reference);

        instrument.setOtherChildren(new ArrayList<MetadataObject>());
        instrument.setOtherChildren(new ArrayList<MetadataObject>());
        instrument.getOtherChildren().add(TestHelper.createMetadataObject("name1"));
        instrument.getOtherChildren().get(0).getProperties().add(
                TestHelper.createProperty("name2", "value1", "value2"));
        instrument.getOtherChildren().get(0).setChildren(new ArrayList<MetadataObject>());
        instrument.getOtherChildren().get(0).getChildren().add(TestHelper.createMetadataObject("name3"));
        instrument.getOtherChildren().get(0).getChildren().get(0).getProperties()
            .add(TestHelper.createProperty("name4", "value3", "value4"));
        instrument.getOtherChildren().get(0).getChildren().get(0).getProperties()
            .add(TestHelper.createProperty("name5", "value5", "value6"));

        // instrument host
        // as insertInstrumentHost(InstrumentHost instrumentHost) is called for inserting instrument hosts,
        // so only check the records in instrument_host and instrument_host_instrument table
        instrument.setHosts(new ArrayList<InstrumentHost>());
        InstrumentHost instrumentHost = new InstrumentHost();
        instrumentHost.setName("name1");
        instrumentHost.setTextId("textId1");
        conversionPersistence.insertInstrumentHost(instrumentHost);
        instrument.getHosts().add(instrumentHost);
        instrumentHost = new InstrumentHost();
        instrumentHost.setName("name2");
        instrumentHost.setTextId("textId2");
        conversionPersistence.insertInstrumentHost(instrumentHost);
        instrument.getHosts().add(instrumentHost);

        long instrumentId = conversionPersistence.insertInstrument(instrument);
        conversionPersistence.clearCaches();
        ResultSet resultSet = TestHelper.query(conn,
                "Select * from instrument where id = " + instrumentId);
        int i = 0;
        while (resultSet.next()) {
            assertEquals("'description' should be correct", instrument.getDescription(),
                    resultSet.getString("description"));
            assertEquals("'name' should be correct", instrument.getName(),
                    resultSet.getString("name"));
            assertEquals("'instrument_text_id' should be correct", instrument.getTextId(),
                    resultSet.getString("instrument_text_id"));
            assertEquals("'type' should be correct", instrument.getType(),
                    resultSet.getString("type"));
            assertEquals("'id' should be correct", instrument.getId(),
                    resultSet.getLong("id"));
            i++;
        }
        assertEquals("should be only one record", 1, i);

        // check instrument_host_reference table
        resultSet = TestHelper.query(conn,
                "Select * from instrument_reference where instrument_id = " + instrumentId);
        i = 0;
        while (resultSet.next()) {
            assertEquals("'reference_id' should be correct", instrument.getReferences().get(i).getId(),
                    resultSet.getLong("reference_id"));
            i++;
        }
        assertEquals("should be two records", 2, i);

        checkMetadataAndXref(instrumentId, "instrument", new String[] {null},
                new String[] {"name1", "name6", "name6", "name6"},
                1,
                new String[] {"value1", "value2", null, "value3", "value4", "value5", "value6"},
                new String[] {"name2", "name2", "name3", "name4", "name4", "name5", "name5"},
                new String[] {"name1", "name1", "name1", "name3", "name3", "name3", "name3"},
                7
        );

        resultSet = TestHelper.query(conn,
                "Select * from instrument_host inner join instrument_host_instrument on"
                + " instrument_host_instrument.instrument_host_id = instrument_host.id inner join instrument"
                + " on instrument_host_instrument.instrument_id = instrument.id where instrument.id = "
                + instrumentId);
        i = 0;
        while (resultSet.next()) {
            assertEquals("'name' should be correct", instrument.getHosts().get(i).getName(),
                    resultSet.getString("name"));
            assertEquals("'instrument_host_text_id' should be correct", instrument.getHosts().get(i).getTextId(),
                    resultSet.getString("instrument_host_text_id"));
            assertEquals("'instrument_host.id' should be correct", instrument.getHosts().get(i).getId(),
                    resultSet.getLong("instrument_host.id"));
            i++;
        }
        assertEquals("should be two records", 2, i);
    }

    /**
     * Test method <code>insertInstrument(Instrument instrument)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertInstrumentNoLink() throws Exception {
        Instrument instrument = new Instrument();
        instrument.setDescription("description");
        instrument.setName("name");
        instrument.setTextId("textId");
        instrument.setType("type");

        long instrumentId = conversionPersistence.insertInstrument(instrument);
        ResultSet resultSet = TestHelper.query(conn,
                "Select * from instrument where id = " + instrumentId);
        int i = 0;
        while (resultSet.next()) {
            assertEquals("'description' should be correct", instrument.getDescription(),
                    resultSet.getString("description"));
            assertEquals("'name' should be correct", instrument.getName(),
                    resultSet.getString("name"));
            assertEquals("'instrument_text_id' should be correct", instrument.getTextId(),
                    resultSet.getString("instrument_text_id"));
            assertEquals("'type' should be correct", instrument.getType(),
                    resultSet.getString("type"));
            assertEquals("'id' should be correct", instrument.getId(),
                    resultSet.getLong("id"));
            i++;
        }
        assertEquals("should be only one record", 1, i);

        // check instrument_host_reference table
        resultSet = TestHelper.query(conn,
                "Select * from instrument_reference where instrument_id = " + instrumentId);
        i = 0;
        while (resultSet.next()) {
            assertEquals("'reference_id' should be correct", instrument.getReferences().get(i).getId(),
                    resultSet.getLong("reference_id"));
            i++;
        }
        assertEquals("should be no records", 0, i);

        checkMetadataAndXref(instrumentId, "instrument", new String[] {},
                new String[] {},
                0,
                new String[] {},
                new String[] {},
                new String[] {},
                0
        );

        resultSet = TestHelper.query(conn,
                "Select * from instrument_host inner join instrument_host_instrument on"
                + " instrument_host_instrument.instrument_host_id = instrument_host.id inner join instrument"
                + " on instrument_host_instrument.instrument_id = instrument.id where instrument.id = "
                + instrumentId);
        i = 0;
        while (resultSet.next()) {
            i++;
        }
        assertEquals("should be no records", 0, i);
    }

    /**
     * Test method <code>insertMission(Mission mission)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertMission() throws Exception {
        Mission mission = new Mission();
        mission.setDescription("description");
        mission.setEndDate(new Timestamp(System.currentTimeMillis()));
        mission.setName("name");
        mission.setStartDate(new Timestamp(System.currentTimeMillis()));

        //references
        mission.setReferences(new ArrayList<Reference>());
        Reference reference = new Reference();
        reference.setDescription("description1");
        reference.setKeyTextId("keyTextId1");
        long[] referenceIds = new long[2];
        referenceIds[0] = conversionPersistence.insertReference(reference);
        reference.setId(referenceIds[0]);
        mission.getReferences().add(reference);
        reference = new Reference();
        reference.setDescription("description2");
        reference.setKeyTextId("keyTextId2");
        referenceIds[1] = conversionPersistence.insertReference(reference);
        reference.setId(referenceIds[1]);
        mission.getReferences().add(reference);

        mission.setOtherChildren(new ArrayList<MetadataObject>());
        mission.setOtherChildren(new ArrayList<MetadataObject>());
        mission.getOtherChildren().add(TestHelper.createMetadataObject("name1"));
        mission.getOtherChildren().get(0).getProperties().add(TestHelper.createProperty("name2", "value1", "value2"));
        mission.getOtherChildren().get(0).setChildren(new ArrayList<MetadataObject>());
        mission.getOtherChildren().get(0).getChildren().add(TestHelper.createMetadataObject("name3"));
        mission.getOtherChildren().get(0).getChildren().get(0).getProperties()
            .add(TestHelper.createProperty("name4", "value3", "value4"));
        mission.getOtherChildren().get(0).getChildren().get(0).getProperties()
            .add(TestHelper.createProperty("name5", "value5", "value6"));

        long missionId = conversionPersistence.insertMission(mission);
        conversionPersistence.clearCaches();
        ResultSet resultSet = TestHelper.query(conn,
                "Select * from mission where id = " + missionId);
        int i = 0;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd : hh mm ss");
        while (resultSet.next()) {
            assertEquals("'name' should be correct", mission.getName(),
                    resultSet.getString("name"));
            assertEquals("'start_date' should be correct", formatter.format(mission.getStartDate()),
                    formatter.format(resultSet.getTimestamp("start_date")));
            assertEquals("'end_date' should be correct", formatter.format(mission.getEndDate()),
                    formatter.format(resultSet.getTimestamp("end_date")));
            assertEquals("'description' should be correct", mission.getDescription(),
                    resultSet.getString("description"));
            assertEquals("'id' should be correct", mission.getId(),
                    resultSet.getLong("id"));
            i++;
        }
        assertEquals("should be only one record", 1, i);

        // check instrument_host_reference table
        resultSet = TestHelper.query(conn,
                "Select * from mission_reference where mission_id = " + missionId);
        i = 0;
        while (resultSet.next()) {
            assertEquals("'reference_id' should be correct", mission.getReferences().get(i).getId(),
                    resultSet.getLong("reference_id"));
            i++;
        }
        assertEquals("should be two records", 2, i);

        checkMetadataAndXref(missionId, "mission", new String[] {null},
                new String[] {"name1", "name6", "name6", "name6"},
                1,
                new String[] {"value1", "value2", null, "value3", "value4", "value5", "value6"},
                new String[] {"name2", "name2", "name3", "name4", "name4", "name5", "name5"},
                new String[] {"name1", "name1", "name1", "name3", "name3", "name3", "name3"},
                7
        );
    }

    /**
     * Test method <code>insertMission(Mission mission)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertMissionNoLink() throws Exception {
        Mission mission = new Mission();
        mission.setDescription("description");
        mission.setEndDate(new Timestamp(System.currentTimeMillis()));
        mission.setName("name");
        mission.setStartDate(new Timestamp(System.currentTimeMillis()));

        long missionId = conversionPersistence.insertMission(mission);
        ResultSet resultSet = TestHelper.query(conn,
                "Select * from mission where id = " + missionId);
        int i = 0;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd : hh mm ss");
        while (resultSet.next()) {
            assertEquals("'name' should be correct", mission.getName(),
                    resultSet.getString("name"));
            assertEquals("'start_date' should be correct", formatter.format(mission.getStartDate()),
                    formatter.format(resultSet.getTimestamp("start_date")));
            assertEquals("'end_date' should be correct", formatter.format(mission.getEndDate()),
                    formatter.format(resultSet.getTimestamp("end_date")));
            assertEquals("'description' should be correct", mission.getDescription(),
                    resultSet.getString("description"));
            assertEquals("'id' should be correct", mission.getId(),
                    resultSet.getLong("id"));
            i++;
        }
        assertEquals("should be only one record", 1, i);

        // check instrument_host_reference table
        resultSet = TestHelper.query(conn,
                "Select * from mission_reference where mission_id = " + missionId);
        i = 0;
        while (resultSet.next()) {
            assertEquals("'reference_id' should be correct", mission.getReferences().get(i).getId(),
                    resultSet.getLong("reference_id"));
            i++;
        }
        assertEquals("should be no records", 0, i);

        checkMetadataAndXref(missionId, "mission", new String[] {},
                new String[] {},
                0,
                new String[] {},
                new String[] {},
                new String[] {},
                0
        );
    }

    /**
     * Test method <code>insertTarget(Target target)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertTarget() throws Exception {
        Target target = new Target();
        target.setName("name");

        //references
        target.setReferences(new ArrayList<Reference>());
        Reference reference = new Reference();
        reference.setDescription("description1");
        reference.setKeyTextId("keyTextId1");
        long[] referenceIds = new long[2];
        referenceIds[0] = conversionPersistence.insertReference(reference);
        reference.setId(referenceIds[0]);
        target.getReferences().add(reference);
        reference = new Reference();
        reference.setDescription("description2");
        reference.setKeyTextId("keyTextId2");
        referenceIds[1] = conversionPersistence.insertReference(reference);
        reference.setId(referenceIds[1]);
        target.getReferences().add(reference);

        // target type
        // as insertInstrumentHost(InstrumentHost instrumentHost) is called for inserting instrument hosts,
        // so only check the records in instrument_host and instrument_host_instrument table
        target.setTypes(new ArrayList<TargetType>());

        TargetType targetType = new TargetType();
        targetType.setName("targetTypeName1");
        target.getTypes().add(targetType);
        targetType = new TargetType();
        targetType.setName("targetTypeName2");
        target.getTypes().add(targetType);
        dataDictionaryImportPersistence.insertTargetTypes(target.getTypes());

        long targetId = conversionPersistence.insertTarget(target);
        ResultSet resultSet = TestHelper.query(conn,
                "Select * from target where id = " + targetId);
        int i = 0;
        while (resultSet.next()) {
            assertEquals("'name' should be correct", target.getName(),
                    resultSet.getString("name"));
            assertEquals("'id' should be correct", target.getId(),
                    resultSet.getLong("id"));
            i++;
        }
        assertEquals("should be only one record", 1, i);

        // check instrument_host_reference table
        resultSet = TestHelper.query(conn,
                "Select * from target_reference where target_id = " + targetId);
        i = 0;
        while (resultSet.next()) {
            assertEquals("'reference_id' should be correct", target.getReferences().get(i).getId(),
                    resultSet.getLong("reference_id"));
            i++;
        }
        assertEquals("should be two records", 2, i);

        resultSet = TestHelper.query(conn,
                "Select * from target_type inner join target_type_target on"
                + " target_type_target.target_type_id = target_type.id inner join target"
                + " on target_type_target.target_id = target.id where target.id = "
                + targetId);
        i = 0;
        while (resultSet.next()) {
            assertEquals("'name' should be correct", target.getTypes().get(i).getName(),
                    resultSet.getString("name"));
            assertEquals("'target_type.id' should be correct", target.getTypes().get(i).getId(),
                    resultSet.getLong("target_type.id"));
            i++;
        }
        assertEquals("should be two records", 2, i);
    }

    /**
     * Test method <code>insertTarget(Target target)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertTargetNoLink() throws Exception {
        Target target = new Target();
        target.setName("name");

        long targetId = conversionPersistence.insertTarget(target);
        ResultSet resultSet = TestHelper.query(conn,
                "Select * from target where id = " + targetId);
        int i = 0;
        while (resultSet.next()) {
            assertEquals("'name' should be correct", target.getName(),
                    resultSet.getString("name"));
            assertEquals("'id' should be correct", target.getId(),
                    resultSet.getLong("id"));
            i++;
        }
        assertEquals("should be only one record", 1, i);

        // check instrument_host_reference table
        resultSet = TestHelper.query(conn,
                "Select * from target_reference where target_id = " + targetId);
        i = 0;
        while (resultSet.next()) {
            i++;
        }
        assertEquals("should be no records", 0, i);

        resultSet = TestHelper.query(conn,
                "Select * from target_type inner join target_type_target on"
                + " target_type_target.target_type_id = target_type.id inner join target"
                + " on target_type_target.target_id = target.id where target.id = "
                + targetId);
        i = 0;
        while (resultSet.next()) {
            i++;
        }
        assertEquals("should be no records", 0, i);
    }

    /**
     * Test method <code>insertDataSet(DataSet dataSet)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertDataSet() throws Exception {
        DataSet dataSet = new DataSet();
        dataSet.setDescription("description");
        dataSet.setName("name");
        dataSet.setRating(1.1f);
        dataSet.setStartDate(new Timestamp(System.currentTimeMillis()));
        dataSet.setStopDate(new Timestamp(System.currentTimeMillis()));
        dataSet.setTextId("textId");

        // references
        dataSet.setReferences(new ArrayList<Reference>());
        Reference reference = new Reference();
        reference.setDescription("description1");
        reference.setKeyTextId("keyTextId1");
        long[] referenceIds = new long[2];
        referenceIds[0] = conversionPersistence.insertReference(reference);
        reference.setId(referenceIds[0]);
        dataSet.getReferences().add(reference);
        reference = new Reference();
        reference.setDescription("description2");
        reference.setKeyTextId("keyTextId2");
        referenceIds[1] = conversionPersistence.insertReference(reference);
        reference.setId(referenceIds[1]);
        dataSet.getReferences().add(reference);

        // other children
        dataSet.setOtherChildren(new ArrayList<MetadataObject>());
        dataSet.getOtherChildren().add(TestHelper.createMetadataObject("name1"));
        dataSet.getOtherChildren().get(0).getProperties().add(TestHelper.createProperty("name2", "value1", "value2"));
        dataSet.getOtherChildren().get(0).setChildren(new ArrayList<MetadataObject>());
        dataSet.getOtherChildren().get(0).getChildren().add(TestHelper.createMetadataObject("name3"));
        dataSet.getOtherChildren().get(0).getChildren().get(0).getProperties()
            .add(TestHelper.createProperty("name4", "value3", "value4"));
        dataSet.getOtherChildren().get(0).getChildren().get(0).getProperties()
            .add(TestHelper.createProperty("name5", "value5", "value6"));

        // as insertInstrument(Instrument instrument), insertMissions(Mission mission).
        // insertTarget(Target target) and  insertTarget(Target target) are called
        // so only check the records in instrument and dataset_instrument tables, ....
        dataSet.setInstruments(new ArrayList<Instrument>());
        Instrument instrument = new Instrument();
        instrument.setDescription("description1");
        instrument.setName("name1");
        instrument.setTextId("textId1");
        instrument.setType("type1");
        conversionPersistence.insertInstrument(instrument);
        dataSet.getInstruments().add(instrument);
        instrument = new Instrument();
        instrument.setDescription("description2");
        instrument.setName("name2");
        instrument.setTextId("textId2");
        instrument.setType("type2");
        conversionPersistence.insertInstrument(instrument);
        dataSet.getInstruments().add(instrument);

        dataSet.setMissions(new ArrayList<Mission>());
        Mission mission = new Mission();
        mission.setDescription("description1");
        mission.setEndDate(new Timestamp(System.currentTimeMillis()));
        mission.setName("name1");
        mission.setStartDate(new Timestamp(System.currentTimeMillis()));
        conversionPersistence.insertMission(mission);
        dataSet.getMissions().add(mission);
        mission = new Mission();
        mission.setDescription("description2");
        mission.setEndDate(new Timestamp(System.currentTimeMillis()));
        mission.setName("name2");
        mission.setStartDate(new Timestamp(System.currentTimeMillis()));
        conversionPersistence.insertMission(mission);
        dataSet.getMissions().add(mission);

        dataSet.setTargets(new ArrayList<Target>());
        Target target = new Target();
        target.setName("name1");
        conversionPersistence.insertTarget(target);
        dataSet.getTargets().add(target);
        target = new Target();
        target.setName("name2");
        conversionPersistence.insertTarget(target);
        dataSet.getTargets().add(target);

        dataSet.setVolumes(new ArrayList<Volume>());
        Volume volume = new Volume();
        volume.setName("name1");
        volume.setDescription("description1");
        volume.setTextId("textId1");
        volume.setSetTextId("setTextId1");
        volume.setSetName("setName1");
        volume.setSeriesName("seriesName1");
        conversionPersistence.insertVolume(volume);
        dataSet.getVolumes().add(volume);
        volume = new Volume();
        volume.setName("name2");
        volume.setDescription("description2");
        volume.setTextId("textId2");
        volume.setSetTextId("setTextId2");
        volume.setSetName("setName2");
        volume.setSeriesName("seriesName2");
        conversionPersistence.insertVolume(volume);
        dataSet.getVolumes().add(volume);

        long dataSetId = conversionPersistence.insertDataSet(dataSet);
        conversionPersistence.clearCaches();
        ResultSet resultSet = TestHelper.query(conn,
                "Select * from dataset where id = " + dataSetId);
        int i = 0;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd : hh mm ss");
        while (resultSet.next()) {
            assertEquals("'data_set_text_id' should be correct", dataSet.getTextId(),
                    resultSet.getString("data_set_text_id"));
            assertEquals("'name' should be correct", dataSet.getName(),
                    resultSet.getString("name"));
            assertEquals("'start_time' should be correct", formatter.format(dataSet.getStartDate()),
                    formatter.format(resultSet.getTimestamp("start_time")));
            assertEquals("'stop_time' should be correct", formatter.format(dataSet.getStopDate()),
                    formatter.format(resultSet.getTimestamp("stop_time")));
            assertEquals("'description' should be correct", dataSet.getDescription(),
                    resultSet.getString("description"));
            assertEquals("'id' should be correct", dataSet.getId(),
                    resultSet.getLong("id"));
            i++;
        }
        assertEquals("should be only one record", 1, i);

        resultSet = TestHelper.query(conn,
                "Select * from instrument inner join instrument_catalog on instrument_catalog.instrument_id"
                + " = instrument.id inner join dataset"
                + " on instrument_catalog.dataset_id = dataset.id where dataset.id = "
                + dataSetId + " order by instrument.id");
        i = 0;
        while (resultSet.next()) {
            assertEquals("'description' should be correct", dataSet.getInstruments().get(i).getDescription(),
                    resultSet.getString("description"));
            assertEquals("'name' should be correct", dataSet.getInstruments().get(i).getName(),
                    resultSet.getString("name"));
            assertEquals("'instrument_text_id' should be correct", dataSet.getInstruments().get(i).getTextId(),
                    resultSet.getString("instrument_text_id"));
            assertEquals("'type' should be correct", dataSet.getInstruments().get(i).getType(),
                    resultSet.getString("type"));
            assertEquals("'instrument.id' should be correct", dataSet.getInstruments().get(i).getId(),
                    resultSet.getLong("instrument.id"));
            i++;
        }
        assertEquals("should be two records", 2, i);

        resultSet = TestHelper.query(conn,
                "Select * from mission inner join dataset_mission on dataset_mission.mission_id"
                + " = mission.id inner join dataset on dataset_mission.dataset_id = dataset.id where dataset.id = "
                + dataSetId + " order by mission.id");
        i = 0;
        while (resultSet.next()) {
            assertEquals("'name' should be correct", dataSet.getMissions().get(i).getName(),
                    resultSet.getString("name"));
            assertEquals("'start_date' should be correct",
                    formatter.format(dataSet.getMissions().get(i).getStartDate()),
                    formatter.format(resultSet.getTimestamp("start_date")));
            assertEquals("'end_date' should be correct", formatter.format(dataSet.getMissions().get(i).getEndDate()),
                    formatter.format(resultSet.getTimestamp("end_date")));
            assertEquals("'description' should be correct", dataSet.getMissions().get(i).getDescription(),
                    resultSet.getString("description"));
            assertEquals("'mission.id' should be correct", dataSet.getMissions().get(i).getId(),
                    resultSet.getLong("mission.id"));
            i++;
        }
        assertEquals("should be two records", 2, i);

        resultSet = TestHelper.query(conn,
                "Select * from target inner join dataset_target on dataset_target.target_id"
                + " = target.id inner join dataset on dataset_target.dataset_id = dataset.id where dataset.id = "
                + dataSetId + " order by target.id");
        i = 0;
        while (resultSet.next()) {
            assertEquals("'name' should be correct", dataSet.getTargets().get(i).getName(),
                    resultSet.getString("name"));
            assertEquals("'target.id' should be correct", dataSet.getTargets().get(i).getId(),
                    resultSet.getLong("target.id"));
            i++;
        }
        assertEquals("should be two records", 2, i);

        resultSet = TestHelper.query(conn,
                "Select * from volume inner join dataset_volume on dataset_volume.volume_id"
                + " = volume.id inner join dataset on dataset_volume.dataset_id = dataset.id where dataset.id = "
                + dataSetId + " order by volume.id");
        i = 0;
        while (resultSet.next()) {
            assertEquals("'name' should be correct", dataSet.getVolumes().get(i).getName(),
                    resultSet.getString("name"));
            assertEquals("'volume.id' should be correct", dataSet.getVolumes().get(i).getId(),
                    resultSet.getLong("volume.id"));
            i++;
        }
        assertEquals("should be two records", 2, i);

        resultSet = TestHelper.query(conn,
                "Select * from dataset inner join dataset_rating on dataset_rating.dataset_id "
                + " = dataset.id where dataset.id = " + dataSetId);
        i = 0;
        while (resultSet.next()) {
            assertEquals("'name' should be correct", dataSet.getRating(),
                    resultSet.getFloat("rating"), 0.00);
            i++;
        }
        assertEquals("should be only one record", 1, i);

        // check reference_catalog table
        resultSet = TestHelper.query(conn,
                "Select * from reference_catalog where dataset_id = " + dataSetId + " order by reference_id");
        i = 0;
        while (resultSet.next()) {
            assertEquals("'reference_id' should be correct", dataSet.getReferences().get(i).getId(),
                    resultSet.getLong("reference_id"));
            i++;
        }
        assertEquals("should be two records", 2, i);

        // check other children
        checkMetadataAndXref(dataSetId, "dataset", new String[] {null},
                new String[] {"name1", "name6", "name6", "name6"},
                1,
                new String[] {"value1", "value2", null, "value3", "value4", "value5", "value6"},
                new String[] {"name2", "name2", "name3", "name4", "name4", "name5", "name5"},
                new String[] {"name1", "name1", "name1", "name3", "name3", "name3", "name3"},
                7
        );
    }

    /**
     * Test method <code>insertDataSet(DataSet dataSet)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertDataSetNoLink() throws Exception {
        DataSet dataSet = new DataSet();
        dataSet.setDescription("description");
        dataSet.setName("name");
        dataSet.setStartDate(new Timestamp(System.currentTimeMillis()));
        dataSet.setStopDate(new Timestamp(System.currentTimeMillis()));
        dataSet.setTextId("textId");

        long dataSetId = conversionPersistence.insertDataSet(dataSet);
        conversionPersistence.clearCaches();
        ResultSet resultSet = TestHelper.query(conn,
                "Select * from dataset where id = " + dataSetId);
        int i = 0;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd : hh mm ss");
        while (resultSet.next()) {
            assertEquals("'data_set_text_id' should be correct", dataSet.getTextId(),
                    resultSet.getString("data_set_text_id"));
            assertEquals("'name' should be correct", dataSet.getName(),
                    resultSet.getString("name"));
            assertEquals("'start_time' should be correct", formatter.format(dataSet.getStartDate()),
                    formatter.format(resultSet.getTimestamp("start_time")));
            assertEquals("'stop_time' should be correct", formatter.format(dataSet.getStopDate()),
                    formatter.format(resultSet.getTimestamp("stop_time")));
            assertEquals("'description' should be correct", dataSet.getDescription(),
                    resultSet.getString("description"));
            assertEquals("'id' should be correct", dataSet.getId(),
                    resultSet.getLong("id"));
            i++;
        }
        assertEquals("should be only one record", 1, i);

        resultSet = TestHelper.query(conn,
                "Select * from instrument inner join instrument_catalog on instrument_catalog.instrument_id"
                + " = instrument.id inner join dataset on instrument_catalog.dataset_id = dataset.id"
                + " where dataset.id = "
                + dataSetId + " order by instrument.id");
        i = 0;
        while (resultSet.next()) {
            i++;
        }
        assertEquals("should be no records", 0, i);

        resultSet = TestHelper.query(conn,
                "Select * from mission inner join dataset_mission on dataset_mission.mission_id"
                + " = mission.id inner join dataset on dataset_mission.dataset_id = dataset.id where dataset.id = "
                + dataSetId + " order by mission.id");
        i = 0;
        while (resultSet.next()) {
            i++;
        }
        assertEquals("should be no records", 0, i);

        resultSet = TestHelper.query(conn,
                "Select * from target inner join dataset_target on dataset_target.target_id"
                + " = target.id inner join dataset on dataset_target.dataset_id = dataset.id where dataset.id = "
                + dataSetId + " order by target.id");
        i = 0;
        while (resultSet.next()) {
            i++;
        }
        assertEquals("should be no records", 0, i);

        resultSet = TestHelper.query(conn,
                "Select * from volume inner join dataset_volume on dataset_volume.volume_id"
                + " = volume.id inner join dataset on dataset_volume.dataset_id = dataset.id where dataset.id = "
                + dataSetId + " order by volume.id");
        i = 0;
        while (resultSet.next()) {
            i++;
        }
        assertEquals("should be no records", 0, i);

        // check reference_catalog table
        resultSet = TestHelper.query(conn,
                "Select * from reference_catalog where dataset_id = " + dataSetId + " order by reference_id");
        i = 0;
        while (resultSet.next()) {
            i++;
        }
        assertEquals("should be no records", 0, i);

        // check other children
        checkMetadataAndXref(dataSetId, "dataset", new String[] {},
                new String[] {},
                0,
                new String[] {},
                new String[] {},
                new String[] {},
                0
        );
    }

    /**
     * Test method <code>insertProduct(long dataSetId, Product product)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertProduct() throws Exception {
        // Prepare dataset
        DataSet dataSet = new DataSet();
        dataSet.setDescription("description");
        dataSet.setName("name");
        dataSet.setRating(1.1f);
        dataSet.setStartDate(new Timestamp(System.currentTimeMillis()));
        dataSet.setStopDate(new Timestamp(System.currentTimeMillis()));
        dataSet.setTextId("textId");
        long dataSetId = conversionPersistence.insertDataSet(dataSet);
        conversionPersistence.clearCaches();

        Product product = new Product();

        product.setDescription("description");
        product.setName("name");
        product.setRecordByteSize(1);
        product.setRecordCount(2);
        product.setRecordType(RecordType.FIXED_LENGTH);
        product.setStartTime(new Timestamp(System.currentTimeMillis()));
        product.setStopTime(new Timestamp(System.currentTimeMillis()));
        product.setTextId("textId");

        // other children
        product.setOtherChildren(new ArrayList<MetadataObject>());
        product.getOtherChildren().add(TestHelper.createMetadataObject("name1"));
        product.getOtherChildren().get(0).getProperties().add(TestHelper.createProperty("name2", "value1", "value2"));
        product.getOtherChildren().get(0).setChildren(new ArrayList<MetadataObject>());
        product.getOtherChildren().get(0).getChildren().add(TestHelper.createMetadataObject("name3"));
        product.getOtherChildren().get(0).getChildren().get(0).getProperties()
            .add(TestHelper.createProperty("name4", "value3", "value4"));
        product.getOtherChildren().get(0).getChildren().get(0).getProperties()
            .add(TestHelper.createProperty("name5", "value5", "value6"));

        // other properties
        product.setOtherProperties(new ArrayList<Property>());
        product.getOtherProperties().add(TestHelper.createProperty("name6", "value7", "value8", "value9"));

        long productId = conversionPersistence.insertProduct(dataSetId, product);
        conversionPersistence.clearCaches();

        ResultSet resultSet = TestHelper.query(conn,
                "Select * from product where id = " + productId);
        int i = 0;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd : hh mm ss");
        while (resultSet.next()) {
            assertEquals("'name' should be correct", product.getName(),
                    resultSet.getString("name"));
            assertEquals("'product_text_id' should be correct", product.getTextId(),
                    resultSet.getString("product_text_id"));
            assertEquals("'start_time' should be correct", formatter.format(product.getStartTime()),
                    formatter.format(resultSet.getTimestamp("start_time")));
            assertEquals("'stop_time' should be correct", formatter.format(product.getStopTime()),
                    formatter.format(resultSet.getTimestamp("stop_time")));
            assertEquals("'description' should be correct", product.getDescription(),
                    resultSet.getString("description"));
            assertEquals("'record_type' should be correct", product.getRecordType().name(),
                    resultSet.getString("record_type"));
            assertEquals("'record_byte_size' should be correct", (int) product.getRecordByteSize(),
                    resultSet.getInt("record_byte_size"));
            assertEquals("'record_count' should be correct", (int) product.getRecordCount(),
                    resultSet.getInt("record_count"));
            assertEquals("'id' should be correct", product.getId(),
                    resultSet.getLong("id"));
            i++;
        }
        assertEquals("should be only one record", 1, i);

        // check product_index table
        resultSet = TestHelper.query(conn,
                "Select * from product_index where data_product_id = " + productId + " and dataset_id = "
                + dataSetId);
        i = 0;
        while (resultSet.next()) {
            i++;
        }
        assertEquals("should be only one record", 1, i);
    }

    /**
     * Test method <code>insertProduct(long dataSetId, Product product)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertProductNoLink() throws Exception {
        Product product = new Product();

        product.setDescription("description");
        product.setName("name");
        product.setRecordByteSize(1);
        product.setRecordCount(2);
        product.setRecordType(RecordType.FIXED_LENGTH);
        product.setStartTime(new Timestamp(System.currentTimeMillis()));
        product.setStopTime(new Timestamp(System.currentTimeMillis()));
        product.setTextId("textId");

        DataSet dataSet = new DataSet();
        dataSet.setDescription("description");
        dataSet.setName("name");
        dataSet.setRating(1.1f);
        dataSet.setStartDate(new Timestamp(System.currentTimeMillis()));
        dataSet.setStopDate(new Timestamp(System.currentTimeMillis()));
        dataSet.setTextId("textId");
        long dataSetId = conversionPersistence.insertDataSet(dataSet);
        long productId = conversionPersistence.insertProduct(dataSetId, product);

        ResultSet resultSet = TestHelper.query(conn,
                "Select * from product where id = " + productId);
        int i = 0;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd : hh mm ss");
        while (resultSet.next()) {
            assertEquals("'name' should be correct", product.getName(),
                    resultSet.getString("name"));
            assertEquals("'product_text_id' should be correct", product.getTextId(),
                    resultSet.getString("product_text_id"));
            assertEquals("'start_time' should be correct", formatter.format(product.getStartTime()),
                    formatter.format(resultSet.getTimestamp("start_time")));
            assertEquals("'stop_time' should be correct", formatter.format(product.getStopTime()),
                    formatter.format(resultSet.getTimestamp("stop_time")));
            assertEquals("'description' should be correct", product.getDescription(),
                    resultSet.getString("description"));
            assertEquals("'record_type' should be correct", product.getRecordType().name(),
                    resultSet.getString("record_type"));
            assertEquals("'record_byte_size' should be correct", (int) product.getRecordByteSize(),
                    resultSet.getInt("record_byte_size"));
            assertEquals("'record_count' should be correct", (int) product.getRecordCount(),
                    resultSet.getInt("record_count"));
            assertEquals("'id' should be correct", product.getId(),
                    resultSet.getLong("id"));
            i++;
        }
        assertEquals("should be only one record", 1, i);

        // check product_index table
        resultSet = TestHelper.query(conn,
                "Select * from product_index where data_product_id = " + productId + " and dataset_id = "
                + dataSetId);
        i = 0;
        while (resultSet.next()) {
            i++;
        }
        assertEquals("should be only one record", 1, i);

        resultSet = TestHelper.query(conn,
                "Select * from data_file inner join product_file on"
                + " product_file.data_file_id = data_file.id inner join product"
                + " on product_file.product_id = product.id where product.id = "
                + productId);
        i = 0;
        while (resultSet.next()) {
            i++;
        }
        assertEquals("should be no records", 0, i);

        resultSet = TestHelper.query(conn,
                "Select * from product_table where product_id = "
                + productId);
        i = 0;
        while (resultSet.next()) {
            i++;
        }
        assertEquals("should be no records", 0, i);
    }

    /**
     * Test method <code>insertProductDocument(long productId, DataFile dataFile)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertProductDocument() throws Exception {
        Product product = new Product();

        product.setDescription("description");
        product.setName("name");
        product.setRecordByteSize(1);
        product.setRecordCount(2);
        product.setRecordType(RecordType.FIXED_LENGTH);
        product.setStartTime(new Timestamp(System.currentTimeMillis()));
        product.setStopTime(new Timestamp(System.currentTimeMillis()));
        product.setTextId("textId");

        DataFile dataFile = new DataFile();
        dataFile.setContent("content");
        dataFile.setName("name1");

        DataSet dataSet = new DataSet();
        dataSet.setDescription("description");
        dataSet.setName("name");
        dataSet.setRating(1.1f);
        dataSet.setStartDate(new Timestamp(System.currentTimeMillis()));
        dataSet.setStopDate(new Timestamp(System.currentTimeMillis()));
        dataSet.setTextId("textId");
        long dataSetId = conversionPersistence.insertDataSet(dataSet);
        long productId = conversionPersistence.insertProduct(dataSetId, product);
        conversionPersistence.insertProductDocument(productId, dataFile);
        conversionPersistence.clearCaches();

        ResultSet resultSet = TestHelper.query(conn,
                "Select * from product_file inner join data_file on"
                + " data_file.id = product_file.data_file_id inner join product"
                + " on product.id = product_file.product_id where product.id = "
                + productId + " and data_file.id = " + dataFile.getId());
        int i = 0;
        while (resultSet.next()) {
            assertEquals("'content' should be correct", dataFile.getContent(),
                    resultSet.getString("content"));
            assertEquals("'name' should be correct", dataFile.getName(),
                    resultSet.getString("name"));
            assertEquals("'path' should be correct", dataFile.getPath(),
                    resultSet.getString("path"));
            i++;
        }
        assertEquals("should be only one record", 1, i);
    }

    /**
     * Test method <code>insertDataSetDocument(long dataSetId, DataFile dataFile)</code>.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testInsertDataSetDocument() throws Exception {
        DataFile dataFile = new DataFile();
        dataFile.setContent("content");
        dataFile.setName("name1");

        DataSet dataSet = new DataSet();
        dataSet.setDescription("description");
        dataSet.setName("name");
        dataSet.setRating(1.1f);
        dataSet.setStartDate(new Timestamp(System.currentTimeMillis()));
        dataSet.setStopDate(new Timestamp(System.currentTimeMillis()));
        dataSet.setTextId("textId");
        long dataSetId = conversionPersistence.insertDataSet(dataSet);
        long datasetFileId = conversionPersistence.insertDataSetDocument(dataSetId, dataFile);

        ResultSet resultSet = TestHelper.query(conn,
                "Select * from dataset_file inner join dataset on dataset.id = dataset_file.dataset_id"
                + " inner join data_file on data_file.id = dataset_file.data_file_id where dataset.id = "
                + dataSetId + " and data_file.id = " + dataFile.getId());
        int i = 0;
        while (resultSet.next()) {
            assertEquals("'content' should be correct", dataFile.getContent(),
                    resultSet.getString("content"));
            assertEquals("'data_file.name' should be correct", dataFile.getName(),
                    resultSet.getString("data_file.name"));
            assertEquals("'path' should be correct", dataFile.getPath(),
                    resultSet.getString("path"));
            assertEquals("'dataset_file.id' should be correct", datasetFileId,
                    resultSet.getLong("dataset_file.id"));
            i++;
        }
        assertEquals("should be only one record", 1, i);
    }

    /**
     * Test method {@code insertCassiniObservationInfo(CassiniObservationInfo info)}.
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testInsertObservationInfo() throws Exception {
        // test data
        final long dataSetId = 1L;
        final long productId = 1L;
        final long productId2 = 2L;
        final String cassiniInstrument = "iss";
        final String ringId = "ringId";
        final String ringId2 = "ringId2";
        final String target = "target";
        final String target2 = "target2";
        final String target3 = "target3";
        
        final Date creationDate = new Date();
        final String hostName = "hostName";
        final String hostId = "hostId";
        final String instrumentName = "instrumentName";
        final String instrumentId = "instrumentId";

        // insert test dataset and products
        TestHelper.execute(conn, "insert into dataset (id, name, data_set_text_id, description) values (" + dataSetId
                + " , 'myDataset', 'myDatasetTextId', 'myDatasetDesc')");
        TestHelper.execute(conn, "insert into product (id, record_type) values (" + productId + ", 'type1')");
        TestHelper.execute(conn, "insert into product (id, record_type) values (" + productId2 + ", 'type2')");

        // insert observation info
        CassiniObservationInfo info = new CassiniObservationInfo();
        info.setCassiniInstrumentName(cassiniInstrument);
        info.setObservations(new ArrayList<CassiniObservation>());
        info.setProductCreationTime(creationDate);
        info.setInstrumentHostName(hostName);
        info.setInstrumentHostId(hostId);
        info.setInstrumentName(instrumentName);
        info.setInstrumentId(instrumentId);

        CassiniObservation observation = new CassiniObservation();
        observation.setProductId(productId);
        observation.setRingObservationId(ringId);
        observation.setTargets(Arrays.asList(target, target2));
        info.getObservations().add(observation);

        CassiniObservation observation2 = new CassiniObservation();
        observation2.setProductId(productId2);
        observation2.setRingObservationId(ringId2);
        observation2.setTargets(Arrays.asList(target3));
        info.getObservations().add(observation2);

        conversionPersistence.insertCassiniObservationInfo(info);

        // test cassini instruments
        long cassiniInstrumentId = 0;
        ResultSet resultSet = TestHelper.query(conn, "select * from cassini_instrument");
        int i = 0;
        while (resultSet.next()) {
            assertEquals("cassini instrument name should be correct", "iss", resultSet.getString("name"));
            cassiniInstrumentId = resultSet.getLong("id");
            i++;
        }
        assertEquals("should be single instrument", 1, i);

        // test instrument observations
        resultSet = TestHelper.query(conn, "select * from cassini_instrument_observation");
        i = 0;
        while (resultSet.next()) {
            assertEquals("cassini_instrument_observation.cassini_instrument_id should be correct",
                    cassiniInstrumentId, resultSet.getLong("cassini_instrument_id"));
            i++;
        }
        assertEquals("should be 2 observations for instrument", 2, i);

        // test observations
        long observationId = 0, observationId2 = 0;
        resultSet = TestHelper.query(conn, "select * from cassini_observation");
        i = 0;
        while (resultSet.next()) {
            String id = resultSet.getString("ring_observation_id");
            assertTrue("Ring id should be correct", id.equals(ringId) || id.equals(ringId2));
            if (id.equals(ringId)) {
                observationId = resultSet.getLong("id");
            } else if (id.equals(ringId2)) {
                observationId2 = resultSet.getLong("id");
            }
            i++;
        }
        assertEquals("should be 2 observations", 2, i);

        // test observation info
        resultSet = TestHelper.query(conn, "select * from cassini_observation_info");
        i = 0;
        while (resultSet.next()) {
            Date date = resultSet.getTimestamp("product_creation_time");
            assertEquals("Date should be correct", creationDate.getTime() / 1000, date.getTime() / 1000);
            assertEquals("Host name should be correct", hostName, resultSet.getString("instrument_host_name"));
            assertEquals("Host id should be correct", hostId, resultSet.getString("instrument_host_id"));
            assertEquals("Instrument name should be correct", instrumentName, resultSet.getString("instrument_name"));
            assertEquals("Instrument id should be correct", instrumentId, resultSet.getString("instrument_id"));
            i++;
        }
        assertEquals("should be single observation info", 1, i);

        // test observation products
        resultSet = TestHelper.query(conn, "select * from cassini_observation_product");
        i = 0;
        while (resultSet.next()) {
            long id = resultSet.getLong("product_id");
            assertTrue("Product id should be correct", id == productId || id == productId2);
            i++;
        }
        assertEquals("should be 2 observation products", 2, i);
        
        // get target ids
        resultSet = TestHelper.query(conn, "select id from target where name = '" + target + "'");
        resultSet.next();
        long targetId = resultSet.getLong("id");
        resultSet = TestHelper.query(conn, "select id from target where name = '" + target2 + "'");
        resultSet.next();
        long targetId2 = resultSet.getLong("id");
        resultSet = TestHelper.query(conn, "select id from target where name = '" + target3 + "'");
        resultSet.next();
        long targetId3 = resultSet.getLong("id");

        // test targets
        resultSet = TestHelper.query(conn, "select * from cassini_observation_target");
        i = 0;
        while (resultSet.next()) {
            long id = resultSet.getLong("target_id");

            if (resultSet.getLong("cassini_observation_id") == observationId) {
                assertTrue("Target should be correct", id == targetId || id == targetId2);
            } else if (resultSet.getLong("cassini_observation_id") == observationId2) {
                assertTrue("Target should be correct", id == targetId3);
            }
            i++;
        }
        assertEquals("should be 3 observation targets", 3, i);
    }

    /**
     * checks the volume_metadata and lookup_value_xref table.
     *
     * @param id the id
     * @param type represents the related table name
     * @param values the lookup value for volume_metadata table
     * @param isProperties the is property value for volume_metadata table
     * @param names the keyword name of lookup value
     * @param count the record count
     * @param xrefValues the lookup value for lookup_value_xref table
     * @param xrefIsProperties the is property value for lookup_value_xref table
     * @param childNames the keyword name of child lookup value
     * @param parentNames the keyword name of parent lookup value
     * @param xrefCount the xref record count
     *
     * @throws SQLException if any sql error occurs
     */
    private void checkMetadataAndXref(long id, String type, String[] values,
            String[] names, int count, String[] xrefValues, String[] childNames,
            String[] parentNames, int xrefCount) throws SQLException {
        ResultSet resultSet;

        // check volume_metadata table
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ").append(type)
            .append("_metadata inner join lookup_value on lookup_value.id = ")
            .append(type).append("_metadata.lookup_value_id inner join keyword on keyword.id =")
            .append(" lookup_value.keyword_id inner join ").append(type).append(" on ").append(type)
            .append(".id = ").append(type).append("_metadata.").append(type).append("_id")
            .append(" where ").append(type).append("_metadata.").append(type).append("_id = ")
            .append(id).append(" order by ").append(type).append("_metadata.id");
        resultSet = TestHelper.query(conn, sql.toString());
        int i = 0;
        while (resultSet.next()) {
            assertEquals("'value' should be correct", values[i],
                    resultSet.getString("value"));
            assertEquals("'keyword.name' should be correct", names[i],
                    resultSet.getString("keyword.name"));
            i++;
        }
        assertEquals("should be " + count + " records", count, i);

        // check lookup_value_xref table
        sql = new StringBuilder();
        sql.append("SELECT * FROM lookup_value_xref inner join lookup_value parent on")
            .append(" lookup_value_xref.parent_id = parent.id inner join lookup_value child on")
            .append(" lookup_value_xref.child_id = child.id inner join keyword parentKeyword on")
            .append(" parentKeyword.id = parent.keyword_id inner join keyword childKeyword on")
            .append(" childKeyword.id = child.keyword_id order by lookup_value_xref.id");
        resultSet = TestHelper.query(conn, sql.toString());
        i = 0;
        while (resultSet.next()) {
            assertEquals("'child.value' should be correct", xrefValues[i],
                    resultSet.getString("child.value"));
            assertEquals("'parent.value' should be correct", null,
                    resultSet.getString("parent.value"));
            assertEquals("'childKeyword.name' should be correct", childNames[i],
                    resultSet.getString("childKeyword.name"));
            assertEquals("'parentKeyword.name' should be correct", parentNames[i],
                    resultSet.getString("parentKeyword.name"));
            i++;
        }
        assertEquals("should be " + xrefCount + " records", xrefCount, i);
    }
}
