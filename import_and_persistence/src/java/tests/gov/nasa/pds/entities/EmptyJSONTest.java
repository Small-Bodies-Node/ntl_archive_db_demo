/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import gov.nasa.pds.entities.Column;
import gov.nasa.pds.entities.DataFile;
import gov.nasa.pds.entities.DataSet;
import gov.nasa.pds.entities.SearchCriteria;
import gov.nasa.pds.entities.ElementAlias;
import gov.nasa.pds.entities.ElementValidation;
import gov.nasa.pds.entities.ErrorItem;
import gov.nasa.pds.entities.HelpFile;
import gov.nasa.pds.entities.Instrument;
import gov.nasa.pds.entities.InstrumentHost;
import gov.nasa.pds.entities.Loggable;
import gov.nasa.pds.entities.MetadataFile;
import gov.nasa.pds.entities.MetadataObject;
import gov.nasa.pds.entities.Mission;
import gov.nasa.pds.entities.ObjectAlias;
import gov.nasa.pds.entities.ObjectValidation;
import gov.nasa.pds.entities.PagedResults;
import gov.nasa.pds.entities.Personnel;
import gov.nasa.pds.entities.Product;
import gov.nasa.pds.entities.Property;
import gov.nasa.pds.entities.Reference;
import gov.nasa.pds.entities.Row;
import gov.nasa.pds.entities.SequenceValueProperty;
import gov.nasa.pds.entities.Table;
import gov.nasa.pds.entities.Target;
import gov.nasa.pds.entities.TargetType;
import gov.nasa.pds.entities.UnitAlias;
import gov.nasa.pds.entities.ValidationReport;
import gov.nasa.pds.entities.Volume;
import junit.framework.JUnit4TestAdapter;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Test cases for toJSONString() method.
 */
public class EmptyJSONTest extends TestCase {
    /**
     * Aggregates all tests in this class.
     * 
     * @return Test suite aggregating all tests.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(EmptyJSONTest.class);
    }

    /**
     * Test {@link Column#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyColumn() {
        Column column = new Column();
        assertEquals("Should return the same JSON String.", column.toJSONString(),
                "{\"scalingFactor\":null,\"format\":null,\"size\":null,\"bitColumnName\":null,\"id\":0,"
                        + "\"dataType\":null,\"description\":null,\"name\":null,\"otherProperties\":null,"
                        + "\"offset\":null,\"otherChildren\":null,\"bitColumn\":false,\"startPosition\":0}");
    }

    /**
     * Test {@link DataFile#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyDataFile() {
        DataFile dataFile = new DataFile();
        assertEquals("Should return the same JSON String.", dataFile.toJSONString(),
                "{\"content\":null,\"id\":0,\"name\":null,\"path\":null}");
    }

    /**
     * Test {@link DataSet#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyDataSet() {
        DataSet dataSet = new DataSet();
        assertEquals("Should return the same JSON String.", dataSet.toJSONString(),
                "{\"startDate\":null,\"stopDate\":null,\"textId\":null,\"volumes\":null,\"id\":0,"
                        + "\"references\":null,\"mission\":null,\"description\":null,\"name\":null,\"target\":null,"
                        + "\"rating\":null,\"otherChildren\":null,\"instruments\":null}");
    }

    /**
     * Test {@link SearchCriteria#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyDataSetSearchCriteria() {
        SearchCriteria dataSetSearchCriteria = new SearchCriteria();
        assertEquals("Should return the same JSON String.", dataSetSearchCriteria.toJSONString(),
                "{\"id\":0,\"startDate\":null,\"lowerDataRating\":null,\"stopDate\":null,"
                        + "\"whiteSpots\":false,\"missions\":null,\"targetTypes\":null,\"opperDataRating\":null,"
                        + "\"instruments\":null,\"targets\":null,\"instrumentHosts\":null}");
    }

    /**
     * Test {@link ElementAlias#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyElementAlias() {
        ElementAlias elementAlias = new ElementAlias();
        assertEquals("Should return the same JSON String.", elementAlias.toJSONString(),
                "{\"id\":0,\"alias\":null,\"fullName\":null,\"anotherName\":null}");
    }

    /**
     * Test {@link ElementValidation#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyElementValidation() {

        ElementValidation elementValidation = new ElementValidation();
        assertEquals("Should return the same JSON String.", elementValidation.toJSONString(),
                "{\"id\":0,\"dataType\":null,\"minimum\":null,\"maximum\":null,\"name\":null,"
                        + "\"minimumLength\":null,\"allowedValues\":null,\"maximumLength\":null}");
    }

    /**
     * Test {@link ErrorItem#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyErrorItem() {
        ErrorItem errorItem = new ErrorItem();
        assertEquals("Should return the same JSON String.", errorItem.toJSONString(),
                "{\"itemName\":null,\"errorType\":null,\"itemType\":null}");
    }

    /**
     * Test {@link HelpFile#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyHelpFile() {
        HelpFile helpFile = new HelpFile();
        assertEquals("Should return the same JSON String.", helpFile.toJSONString(),
                "{\"id\":0,\"name\":null,\"path\":null}");
    }

    /**
     * Test {@link Instrument#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyInstrument() {
        Instrument instrument = new Instrument();
        assertEquals("Should return the same JSON String.", instrument.toJSONString(),
                "{\"id\":0,\"references\":null,\"description\":null,\"name\":null,\"hosts\":null,"
                        + "\"textId\":null,\"otherChildren\":null,\"type\":null}");
    }

    /**
     * Test {@link InstrumentHost#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyInstrumentHost() {
        InstrumentHost instrumentHost = new InstrumentHost();
        assertEquals("Should return the same JSON String.", instrumentHost.toJSONString(),
                "{\"id\":0,\"references\":null,\"name\":null,\"textId\":null,\"otherChildren\":null}");
    }

    /**
     * Test {@link MetadataFile#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyMetadataFile() {
        MetadataFile metadataFile = new MetadataFile();
        assertEquals("Should return the same JSON String.", metadataFile.toJSONString(),
                "{\"id\":0,\"name\":null,\"children\":null,\"properties\":null}");
    }

    /**
     * Test {@link MetadataObject#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyMetadataObject() {
        MetadataObject metadataObject = new MetadataObject();
        assertEquals("Should return the same JSON String.", metadataObject.toJSONString(),
                "{\"id\":0,\"name\":null,\"children\":null,\"properties\":null}");
    }

    /**
     * Test {@link Mission#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyMission() {
        Mission mission = new Mission();
        assertEquals("Should return the same JSON String.", mission.toJSONString(),
                "{\"id\":0,\"startDate\":null,\"references\":null,\"description\":null,\"name\":null,"
                        + "\"endDate\":null,\"otherChildren\":null}");
    }

    /**
     * Test {@link ObjectAlias#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyObjectAlias() {
        ObjectAlias objectAlias = new ObjectAlias();
        assertEquals("Should return the same JSON String.", objectAlias.toJSONString(),
                "{\"id\":0,\"alias\":null,\"fullName\":null}");
    }

    /**
     * Test {@link ObjectValidation#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyObjectValidation() {
        ObjectValidation objectValidation = new ObjectValidation();
        assertEquals("Should return the same JSON String.", objectValidation.toJSONString(),
                "{\"id\":0,\"optionalObjects\":null,\"requiredObjects\":null,\"requiredElements\":null,"
                        + "\"name\":null,\"globallyAllowableElements\":false,\"optionalElements\":null}");
    }

    /**
     * Test {@link PagedResults#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyPagedResults() {
        PagedResults<Loggable> pagedResults = new PagedResults<Loggable>();
        assertEquals("Should return the same JSON String.", pagedResults.toJSONString(),
                "{\"total\":0,\"results\":null}");
    }

    /**
     * Test {@link Personnel#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyPersonnel() {
        Personnel personnel = new Personnel();
        assertEquals("Should return the same JSON String.", personnel.toJSONString(),
                "{\"id\":0,\"userId\":null,\"fullName\":null,\"otherChildren\":null}");
    }

    /**
     * Test {@link Product#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyProduct() {
        Product product = new Product();
        assertEquals("Should return the same JSON String.", product.toJSONString(),
                "{\"recordCount\":null,\"textId\":null,\"recordByteSize\":null,"
                        + "\"associatedTableName\":null,\"associatedFiles\":null,\"stopTime\":null,\"id\":0,"
                        + "\"startTime\":null,\"description\":null,\"name\":null,\"otherProperties\":null,"
                        + "\"otherChildren\":null,\"recordType\":\"\"}");
    }

    /**
     * Test {@link Property#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyProperty() {
        Property property = new Property();
        assertEquals("Should return the same JSON String.", property.toJSONString(),
                "{\"id\":0,\"values\":null,\"name\":null}");
    }

    /**
     * Test {@link Reference#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyReference() {
        Reference reference = new Reference();
        assertEquals("Should return the same JSON String.", reference.toJSONString(),
                "{\"id\":0,\"description\":null,\"keyTextId\":null}");
    }

    /**
     * Test {@link Row#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyRow() {
        Row row = new Row();
        System.out.println(row.toJSONString());
        assertEquals("Should return the same JSON String.", row.toJSONString(), "{\"id\":0,\"cells\":null}");
    }

    /**
     * Test {@link SequenceValueProperty#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptySequenceValueProperty() {
        SequenceValueProperty sequenceValueProperty = new SequenceValueProperty();
        assertEquals("Should return the same JSON String.", sequenceValueProperty.toJSONString(),
                "{\"id\":0,\"values\":null,\"name\":null,\"sequences\":null}");
    }

    /**
     * Test {@link Table#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyTable() {
        Table table = new Table();
        assertEquals("Should return the same JSON String.", table.toJSONString(),
                "{\"id\":0,\"name\":null,\"rowByteSize\":null,\"columns\":null,\"tableType\":\"\","
                        + "\"otherProperties\":null,\"format\":\"\",\"rowCount\":0,\"rows\":[]}");
    }

    /**
     * Test {@link Target#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyTarget() {
        Target target = new Target();
        System.out.println(target.toJSONString());
        System.out.println(target.toJSONString());
        assertEquals("Should return the same JSON String.", target.toJSONString(),
                "{\"id\":0,\"references\":null,\"name\":null,\"types\":null}");
    }

    /**
     * Test {@link TargetType#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyTargetType() {
        TargetType targetType = new TargetType();
        assertEquals("Should return the same JSON String.", targetType.toJSONString(), "{\"id\":0,\"name\":null}");
    }

    /**
     * Test {@link UnitAlias#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyUnitAlias() {
        UnitAlias unitAlias = new UnitAlias();
        assertEquals("Should return the same JSON String.", unitAlias.toJSONString(),
                "{\"id\":0,\"alias\":null,\"fullName\":null}");
    }

    /**
     * Test {@link ValidationReport#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyValidationReport() {
        ValidationReport validationReport = new ValidationReport();
        assertEquals("Should return the same JSON String.", validationReport.toJSONString(),
                "{\"valid\":false,\"errors\":null}");
    }

    /**
     * Test {@link Volume#toJSONString()} method with empty instance.
     */
    @Test
    public void testEmptyVolume() {
        Volume volume = new Volume();
        assertEquals("Should return the same JSON String.", volume.toJSONString(),
                "{\"id\":0,\"setTextId\":null,\"seriesName\":null,\"description\":null,\"name\":null,"
                        + "\"textId\":null,\"otherProperties\":null,\"setName\":null,\"otherChildren\":null}");
    }

}
