/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import gov.nasa.pds.entities.Column;
import gov.nasa.pds.entities.DataSet;
import gov.nasa.pds.entities.Instrument;
import gov.nasa.pds.entities.InstrumentHost;
import gov.nasa.pds.entities.MetadataObject;
import gov.nasa.pds.entities.Mission;
import gov.nasa.pds.entities.Personnel;
import gov.nasa.pds.entities.Product;
import gov.nasa.pds.entities.Reference;
import gov.nasa.pds.entities.Table;
import gov.nasa.pds.entities.Target;
import gov.nasa.pds.entities.TargetType;
import gov.nasa.pds.entities.Volume;
import junit.framework.JUnit4TestAdapter;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Test cases for fromMetadata() methods with empty instance.
 */
public class EmptyMetadataObjectTest extends TestCase {
    /**
     * Aggregates all tests in this class.
     * 
     * @return Test suite aggregating all tests.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(EmptyMetadataObjectTest.class);
    }

    /**
     * The metaDataObject instance for test.
     */
    private MetadataObject metaDataObject;

    /**
     * Set up method.
     */
    public void setUp() {
        metaDataObject = new MetadataObject();
    }

    /**
     * Tear down method.
     */
    public void tearDown() {

    }

    /**
     * Test {@link Column#fromMetadata(MetadataObject)} with empty instance.
     */
    @Test
    public void testEmptyColumn() {
        Column column = new Column();
        column.fromMetadata(metaDataObject);
    }

    /**
     * Test {@link DataSet#fromMetadata(MetadataObject)} with empty instance.
     */
    @Test
    public void testEmptyDataSet() {
        DataSet dataSet = new DataSet();
        dataSet.fromMetadata(metaDataObject);
    }

    /**
     * Test {@link Instrument#fromMetadata(MetadataObject)} with empty instance.
     */
    @Test
    public void testEmptyInstrument() {
        Instrument instrument = new Instrument();
        instrument.fromMetadata(metaDataObject);
    }

    /**
     * Test {@link InstrumentHost#fromMetadata(MetadataObject)} with empty instance.
     */
    @Test
    public void testEmptyInstrumentHost() {
        InstrumentHost instrumentHost = new InstrumentHost();
        instrumentHost.fromMetadata(metaDataObject);
    }

    /**
     * Test {@link Mission#fromMetadata(MetadataObject)} with empty instance.
     */
    @Test
    public void testEmptyMission() {
        Mission mission = new Mission();
        mission.fromMetadata(metaDataObject);
    }

    /**
     * Test {@link Personnel#fromMetadata(MetadataObject)} with empty instance.
     */
    @Test
    public void testEmptyPersonnel() {
        Personnel personnel = new Personnel();
        personnel.fromMetadata(metaDataObject);
    }

    /**
     * Test {@link Product#fromMetadata(MetadataObject)} with empty instance.
     */
    @Test
    public void testEmptyProduct() {
        Product product = new Product();
        product.fromMetadata(metaDataObject);
    }

    /**
     * Test aaa with empty instance.
     */
    @Test
    public void testEmptyReference() {
        Reference reference = new Reference();
        reference.fromMetadata(metaDataObject);
    }

    /**
     * Test {@link Table#fromMetadata(MetadataObject)} with empty instance.
     */
    @Test
    public void testEmptyTable() {
        Table table = new Table();
        table.fromMetadata(metaDataObject);
    }

    /**
     * Test {@link Target#fromMetadata(MetadataObject)} with empty instance.
     */
    @Test
    public void testEmptyTarget() {
        Target target = new Target();
        target.fromMetadata(metaDataObject);
    }

    /**
     * Test {@link TargetType#fromMetadata(MetadataObject)} with empty instance.
     */
    @Test
    public void testEmptyTargetType() {
        TargetType targetType = new TargetType();
        targetType.fromMetadata(metaDataObject);
    }

    /**
     * Test {@link Volume#fromMetadata(MetadataObject)} with empty instance.
     */
    @Test
    public void testEmptyVolume() {
        Volume volume = new Volume();
        volume.fromMetadata(metaDataObject);
    }
}
