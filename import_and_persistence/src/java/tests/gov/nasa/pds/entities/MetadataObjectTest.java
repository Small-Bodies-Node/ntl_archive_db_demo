/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Test cases for fromMetadata() methods with populated values.
 */
public class MetadataObjectTest extends TestCase {
    /**
     * Aggregates all tests in this class.
     *
     * @return Test suite aggregating all tests.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(MetadataObjectTest.class);
    }

    /**
     * The metaDataObject instance for test.
     */
    private MetadataObject metaDataObject;

    /**
     * Set up method.
     */
    @Override
    public void setUp() {
        metaDataObject = new MetadataObject();
    }

    /**
     * Tear down method.
     */
    @Override
    public void tearDown() {
        metaDataObject = null;
    }

    /**
     * Test {@link Column#fromMetadata(MetadataObject)} with populated values.
     */
    @Test
    public void testColumn() {
        Column column = new Column();
        metaDataObject.setProperties(createProperties(new String[][] { { "NAME", "DETECTOR TEMPERATURE" },
                { "START_BYTE", "27" }, { "BYTES", "5" }, { "DATA_TYPE", "ASCII_REAL" }, { "FORMAT", "F5.1" },
                { "DESCRIPTION", "These bits identify Version 1." }, { "SCALING_FACTOR", "1" }, { "OFFSET", "1" } }));

        column.fromMetadata(metaDataObject);
        assertEquals(column.getName(), "DETECTOR TEMPERATURE");
        assertEquals(column.getStartPosition(), 27);
        assertEquals(column.getSize(), new Integer(5));
        assertEquals(column.getDataType(), "ASCII_REAL");
        assertEquals(column.getFormat(), "F5.1");
        assertEquals(column.getDescription(), "These bits identify Version 1.");
        assertEquals(column.getScalingFactor(), 1.0F);
        assertEquals(column.getOffset(), 1.0F);
    }

    /**
     * Creates the Property instance with given name and value.
     *
     * @param name
     *            - the given name.
     * @param value
     *            - the given value.
     * @return - the created Property instance.
     */
    private Property createProperty(String name, String value) {
        Property property = new Property(name);
        List<String> list = new ArrayList<String>();
        list.add(value);
        property.setValues(list);
        return property;
    }

    /**
     * Creates the properties list.
     *
     * @param values
     *            - the given key and name pairs
     * @return - the created properties list.
     */
    private List<Property> createProperties(String[][] values) {
        List<Property> properties = new ArrayList<Property>();
        for (String[] array : values) {
            properties.add(createProperty(array[0], array[1]));
        }

        return properties;
    }

    /**
     * Test {@link DataSet#fromMetadata(MetadataObject)} with populated values.
     */
    @Test
    public void testDataSet() {
        DataSet dataSet = new DataSet();
        List<MetadataObject> children = new ArrayList<MetadataObject>();

        metaDataObject.setProperties(createProperties(new String[][] { { "DATA_SET_ID", "MGN-V-RDRS-5-GVDR-V1.0" } }));

        MetadataObject target = new MetadataObject("DATA_SET_TARGET");
        target.setProperties(createProperties(new String[][] { { "TARGET_NAME", "VENUS" } }));

        MetadataObject mission = new MetadataObject("DATA_SET_MISSION");
        mission.setProperties(createProperties(new String[][] { { "MISSION_NAME", "MAGELLAN" } }));

        MetadataObject references1 = new MetadataObject("DATA_SET_REFERENCE_INFORMATION");
        references1.setProperties(createProperties(new String[][] { { "REFERENCE_KEY_ID", "CUEVAS1989" } }));

        MetadataObject references2 = new MetadataObject("DATA_SET_REFERENCE_INFORMATION");
        references2.setProperties(createProperties(new String[][] { { "REFERENCE_KEY_ID", "DAVIESETAL1989" } }));

        MetadataObject host = new MetadataObject("DATA_SET_HOST");
        host.setProperties(createProperties(new String[][] { { "INSTRUMENT_HOST_ID", "MGN" },
                { "INSTRUMENT_ID", "RDRS" } }));

        MetadataObject info = new MetadataObject("DATA_SET_INFORMATION");
        info.setProperties(createProperties(new String[][] {
                { "DATA_SET_NAME", "MGN V RDRS DERIVED GLOBAL VECTOR DATA RECORD V1.0" }, { "DATA_SET_DESC", "DESC" },
                { "START_TIME", "1990-08-01T00:00:00.000" }, { "STOP_TIME", "1993-08-01T00:00:00.000" } }));

        children.add(target);
        children.add(mission);
        children.add(references1);
        children.add(references2);
        children.add(host);
        children.add(info);

        metaDataObject.setChildren(children);

        dataSet.fromMetadata(metaDataObject);

        assertEquals(dataSet.getName(), "MGN V RDRS DERIVED GLOBAL VECTOR DATA RECORD V1.0");
        assertEquals(dataSet.getDescription(), "DESC");
        assertEquals(formatDate(dataSet.getStartDate()), "1990-08-01T00:00:00.000");
        assertEquals(formatDate(dataSet.getStopDate()), "1993-08-01T00:00:00.000");
        assertEquals(dataSet.getOtherChildren().size(), 6);
        assertEquals(dataSet.getTextId(), "MGN-V-RDRS-5-GVDR-V1.0");
        assertEquals(dataSet.getTargets().get(0).getName(), "VENUS");
        assertEquals(dataSet.getMissions().get(0).getName(), "MAGELLAN");
        assertEquals(dataSet.getInstruments().size(), 1);
        assertEquals(dataSet.getReferences().size(), 2);
    }

    /**
     * Formats the date to String.
     *
     * @param date
     *            - the given date to format
     * @return - the String value of date.
     */
    private static String formatDate(Date date) {
        DateFormat format = new SimpleDateFormat(EntityHelper.DATE_FORMATS.get(0));
        return format.format(date);
    }

    /**
     * Test {@link Instrument#fromMetadata(MetadataObject)} with populated values.
     */
    @Test
    public void testInstrument() {
        Instrument instrument = new Instrument();
        List<MetadataObject> children = new ArrayList<MetadataObject>();
        metaDataObject.setProperties(createProperties(new String[][] { { "INSTRUMENT_ID", "RDRS" }}));

        MetadataObject references1 = new MetadataObject("INSTRUMENT_REFERENCE_INFO");
        references1.setProperties(createProperties(new String[][] { { "REFERENCE_KEY_ID", "CUEVAS1989" } }));

        MetadataObject references2 = new MetadataObject("INSTRUMENT_REFERENCE_INFO");
        references2.setProperties(createProperties(new String[][] { { "REFERENCE_KEY_ID", "JOHNSON1990" } }));

        MetadataObject info = new MetadataObject("INSTRUMENT_INFORMATION");
        info.setProperties(createProperties(new String[][] { { "INSTRUMENT_NAME", "RADAR SYSTEM" },
                { "INSTRUMENT_TYPE", "RADAR" }, { "INSTRUMENT_DESC", "DESC" } }));

        children.add(references1);
        children.add(references2);
        children.add(info);

        metaDataObject.setChildren(children);

        instrument.fromMetadata(metaDataObject);
        assertEquals(instrument.getName(), "RADAR SYSTEM");
        assertEquals(instrument.getTextId(), "RDRS");
        assertEquals(instrument.getType(), "RADAR");
        assertEquals(instrument.getDescription(), "DESC");
        assertEquals(instrument.getReferences().size(), 2);
        assertEquals(instrument.getOtherChildren().size(), 3);
    }

    /**
     * Test {@link InstrumentHost#fromMetadata(MetadataObject)} with populated values.
     */
    @Test
    public void testInstrumentHost() {
        InstrumentHost instrumentHost = new InstrumentHost();
        List<MetadataObject> children = new ArrayList<MetadataObject>();
        metaDataObject.setProperties(createProperties(new String[][] { { "INSTRUMENT_HOST_ID", "MGN" } }));

        MetadataObject references1 = new MetadataObject("INSTRUMENT_HOST_REFERENCE_INFO");
        references1.setProperties(createProperties(new String[][] { { "REFERENCE_KEY_ID", "SAUNDERSETAL1990" } }));

        MetadataObject references2 = new MetadataObject("INSTRUMENT_HOST_REFERENCE_INFO");
        references2.setProperties(createProperties(new String[][] { { "REFERENCE_KEY_ID", "SAUNDERSETAL1992" } }));

        MetadataObject info = new MetadataObject("INSTRUMENT_HOST_INFORMATION");
        info.setProperties(createProperties(new String[][] { { "INSTRUMENT_HOST_NAME", "MAGELLAN" } }));

        children.add(references1);
        children.add(references2);
        children.add(info);

        metaDataObject.setChildren(children);
        instrumentHost.fromMetadata(metaDataObject);

        assertEquals(instrumentHost.getName(), "MAGELLAN");
        assertEquals(instrumentHost.getTextId(), "MGN");
        assertEquals(instrumentHost.getReferences().size(), 2);
        assertEquals(instrumentHost.getOtherChildren().size(), 3);
    }

    /**
     * Test {@link Mission#fromMetadata(MetadataObject)} with populated values.
     */
    @Test
    public void testMission() {
        Mission mission = new Mission();
        List<MetadataObject> children = new ArrayList<MetadataObject>();
        metaDataObject.setProperties(createProperties(new String[][] { { "MISSION_NAME", "MAGELLAN" } }));

        MetadataObject references1 = new MetadataObject("MISSION_REFERENCE_INFORMATION");
        references1.setProperties(createProperties(new String[][] { { "REFERENCE_KEY_ID", "CAMPBELLETAL1992" } }));

        MetadataObject references2 = new MetadataObject("MISSION_REFERENCE_INFORMATION");
        references2.setProperties(createProperties(new String[][] { { "REFERENCE_KEY_ID", "ARVIDSON1991" } }));

        MetadataObject info = new MetadataObject("MISSION_INFORMATION");
        info.setProperties(createProperties(new String[][] { { "MISSION_START_DATE", "1990-08-01T00:00:00.000" },
                { "MISSION_STOP_DATE", "1993-08-01T00:00:00.000" }, { "MISSION_DESC", "DESC" } }));

        children.add(references1);
        children.add(references2);
        children.add(info);

        metaDataObject.setChildren(children);
        mission.fromMetadata(metaDataObject);

        assertEquals(mission.getName(), "MAGELLAN");
        assertEquals(mission.getDescription(), "DESC");
        assertEquals(formatDate(mission.getStartDate()), "1990-08-01T00:00:00.000");
        assertEquals(formatDate(mission.getEndDate()), "1993-08-01T00:00:00.000");
        assertEquals(mission.getReferences().size(), 2);
        assertEquals(mission.getOtherChildren().size(), 3);
    }

    /**
     * Test {@link Personnel#fromMetadata(MetadataObject)} with populated values.
     */
    @Test
    public void testPersonnel() {
        Personnel personnel = new Personnel();
        List<MetadataObject> children = new ArrayList<MetadataObject>();
        metaDataObject.setProperties(createProperties(new String[][] { { "PDS_USER_ID", "PFORD" } }));

        MetadataObject info = new MetadataObject("PERSONNEL_INFORMATION");
        info.setProperties(createProperties(new String[][] { { "FULL_NAME", "PETER G. FORD" } }));

        children.add(info);

        metaDataObject.setChildren(children);
        personnel.fromMetadata(metaDataObject);

        assertEquals(personnel.getFullName(), "PETER G. FORD");
        assertEquals(personnel.getUserId(), "PFORD");
        assertEquals(personnel.getOtherChildren().size(), 1);
    }

    /**
     * Test {@link Product#fromMetadata(MetadataObject)} with populated values.
     */
    @Test
    public void testProduct() {
        Product product = new Product();
        List<MetadataObject> children = new ArrayList<MetadataObject>();
        metaDataObject.setProperties(createProperties(new String[][] { { "PRODUCT_NAME", "PRODUCT NAME" },
                { "PRODUCT_ID", "PRODUCD ID" }, { "START_TIME", "1990-08-01T00:00:00.000" },
                { "STOP_TIME", "1993-08-01T00:00:00.000" } }));

        metaDataObject.setChildren(children);
        product.fromMetadata(metaDataObject);
        assertEquals(product.getName(), "PRODUCT NAME");
        assertEquals(product.getTextId(), "PRODUCD ID");
        assertEquals(formatDate(product.getStartTime()), "1990-08-01T00:00:00.000");
        assertEquals(formatDate(product.getStopTime()), "1993-08-01T00:00:00.000");
        assertEquals(product.getOtherProperties().size(), 0);
        assertEquals(product.getOtherChildren().size(), 0);
    }

    /**
     * Test {@link Reference#fromMetadata(MetadataObject)} with populated values.
     */
    @Test
    public void testReference() {
        Reference reference = new Reference();
        metaDataObject.setProperties(createProperties(new String[][] { { "REFERENCE_KEY_ID", "FIELDETAL1989B" },
                { "REFERENCE_DESC", "DESC" } }));

        reference.fromMetadata(metaDataObject);

        assertEquals(reference.getKeyTextId(), "FIELDETAL1989B");
        assertEquals(reference.getDescription(), "DESC");
    }

    /**
     * Test {@link Table#fromMetadata(MetadataObject)} with populated values.
     */
    @Test
    public void testTable() {
        Table table = new Table();
        List<MetadataObject> children = new ArrayList<MetadataObject>();

        metaDataObject.setProperties(createProperties(new String[][] { { "ROWS", "1000" } }));

        MetadataObject column = new MetadataObject("COLUMN");
        column.setProperties(createProperties(new String[][] { { "NAME", "DETECTOR TEMPERATURE" },
                { "START_BYTE", "27" }, { "BYTES", "5" }, { "DATA_TYPE", "ASCII_REAL" },
                { "DESCRIPTION", "These bits identify Version 1." } }));

        children.add(column);

        metaDataObject.setChildren(children);

        table.fromMetadata(metaDataObject);

        assertEquals(table.getRowCount(), 1000);
        assertEquals(table.getColumns().size(), 1);
        assertEquals(table.getColumns().get(0).getName(), "DETECTOR TEMPERATURE");
        assertEquals(table.getOtherProperties().size(), 0);

    }

    /**
     * Test {@link Target#fromMetadata(MetadataObject)} with populated values.
     */
    @Test
    public void testTarget() {
        Target target = new Target();
        List<MetadataObject> children = new ArrayList<MetadataObject>();

        metaDataObject.setProperties(createProperties(new String[][] { { "TARGET_NAME", "JUPITER" } }));

        MetadataObject info = new MetadataObject("TARGET_INFORMATION");
        info.setProperties(createProperties(new String[][] { { "TARGET_TYPE", "PLANET" } }));

        children.add(info);

        metaDataObject.setChildren(children);

        target.fromMetadata(metaDataObject);

        assertEquals(target.getName(), "JUPITER");
        assertEquals(target.getTypes().get(0).getName(), "PLANET");
    }

    /**
     * Test {@link TargetType#fromMetadata(MetadataObject)} with populated values.
     */
    @Test
    public void testTargetType() {
        TargetType targetType = new TargetType();

        metaDataObject.setProperties(createProperties(new String[][] { { "TARGET_TYPE", "Target Type" } }));

        targetType.fromMetadata(metaDataObject);
        assertEquals(targetType.getName(), "Target Type");
    }

    /**
     * Test {@link Volume#fromMetadata(MetadataObject)} with populated values.
     */
    @Test
    public void testVolume() {
        Volume volume = new Volume();
        List<MetadataObject> children = new ArrayList<MetadataObject>();
        metaDataObject.setProperties(createProperties(new String[][] {
                { "VOLUME_NAME", "MDIM/DTM VOLUME 7: GLOBAL COVERAGE" }, { "DESCRIPTION", "DESC" },
                { "VOLUME_ID", "VO_2007" }, { "VOLUME_SET_ID", "USA_NASA_PDS_VO_2001_TO_VO_2007" },
                { "VOLUME_SET_NAME", "MARS DIGITAL IMAGE MOSAIC AND DIGITAL TERRAIN MODEL" },
                { "VOLUME_SERIES_NAME", "MISSION TO MARS" } }));

        metaDataObject.setChildren(children);

        volume.fromMetadata(metaDataObject);
        assertEquals(volume.getName(), "MDIM/DTM VOLUME 7: GLOBAL COVERAGE");
        assertEquals(volume.getDescription(), "DESC");
        assertEquals(volume.getTextId(), "VO_2007");
        assertEquals(volume.getSetTextId(), "USA_NASA_PDS_VO_2001_TO_VO_2007");
        assertEquals(volume.getSetName(), "MARS DIGITAL IMAGE MOSAIC AND DIGITAL TERRAIN MODEL");
        assertEquals(volume.getSeriesName(), "MISSION TO MARS");

        assertEquals(volume.getOtherProperties().size(), 0);
        assertEquals(volume.getOtherChildren().size(), 0);
    }
}
