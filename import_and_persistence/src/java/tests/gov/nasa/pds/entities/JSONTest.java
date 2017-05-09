/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Test cases for toJSONString() method.
 */
public class JSONTest extends TestCase {
    /**
     * Aggregates all tests in this class.
     *
     * @return Test suite aggregating all tests.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(JSONTest.class);
    }

    /**
     * Test {@link Column#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledColumn() {
        Column column = createColumns().get(0);
        System.out.println("column.toJSONString():");
        System.out.println(column.toJSONString());
    }

    /**
     * Test {@link DataFile#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledDataFile() {
        DataFile dataFile = new DataFile();
        dataFile.setContent("content 1");
        dataFile.setId(2);
        dataFile.setName("name 1");
        dataFile.setPath("path 1");
        System.out.println("dataFile.toJSONString():");
        System.out.println(dataFile.toJSONString());
    }

    /**
     * Test {@link DataSet#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledDataSet() {
        DataSet dataSet = new DataSet();
        dataSet.setDescription("data set description");
        dataSet.setId(1);
        dataSet.setInstruments(createInstruments());
        dataSet.setMissions(createMissions());
        dataSet.setName("data set name");
        dataSet.setOtherChildren(createMetadataObjects());
        dataSet.setRating(1F);
        dataSet.setReferences(createReferences());
        dataSet.setStartDate(createDate());
        dataSet.setStopDate(createDate());
        dataSet.setTargets(createTargets());
        dataSet.setTextId("data set text id");
        dataSet.setVolumes(createVolumes());
        System.out.println("dataSet.toJSONString():");
        System.out.println(dataSet.toJSONString());
    }

    /**
     * Create volumns instance.
     *
     * @return created instance.
     */
    private List<Volume> createVolumes() {
        List<Volume> volumns = new ArrayList<Volume>();
        Volume v = new Volume();
        v.setDescription("description");
        v.setId(1);
        v.setName("volumn name");
        v.setOtherChildren(createMetadataObjects());
        v.setOtherProperties(createProperties());
        v.setSeriesName("seriesName");
        v.setSetName("setName");
        v.setSetTextId("setTextId");
        v.setTextId("textId");
        volumns.add(v);
        return volumns;
    }

    /**
     * Create references instance.
     *
     * @return created instance.
     */
    private List<Reference> createReferences() {
        List<Reference> references = new ArrayList<Reference>();
        Reference r = new Reference();
        r.setDescription("reference description");
        r.setId(1);
        r.setKeyTextId("text id");
        references.add(r);
        return references;
    }

    /**
     * Create target instance.
     *
     * @return created instance.
     */
    private Target createTarget() {
        Target target = new Target();
        target.setId(1);
        target.setName("target name");
        target.setTypes(createTargetTypes());

        return target;
    }

    /**
     * Create target List instance.
     *
     * @return created instance.
     */
    private List<Target> createTargets() {
        List<Target> targets = new ArrayList<Target>();
        targets.add(createTarget());

        return targets;
    }

    /**
     * Create TargetType instance.
     *
     * @return created instance.
     */
    private TargetType createTargetType() {
        TargetType type = new TargetType();
        type.setId(1);
        type.setName("type name");
        return type;
    }

    /**
     * Create TargetType List instance.
     *
     * @return created instance.
     */
    private List<TargetType> createTargetTypes() {
        List<TargetType> types = new ArrayList<TargetType>();
        types.add(createTargetType());

        return types;
    }

    /**
     * Create personnel instance.
     *
     * @return created instance.
     */
    private List<Personnel> createPersonnel() {
        List<Personnel> list = new ArrayList<Personnel>();
        Personnel personnel = new Personnel();
        personnel.setFullName("full name");
        personnel.setId(3);
        personnel.setUserId("user id");
        personnel.setOtherChildren(createMetadataObjects());
        list.add(personnel);
        return list;
    }

    /**
     * Create Date instance.
     *
     * @return created instance.
     */
    private Date createDate() {
        Date result = null;
        try {
            DateFormat format = new SimpleDateFormat(EntityHelper.DATE_FORMATS.get(0));
            result = format.parse("1993-08-01T00:00:00.000");
        } catch (ParseException e) {
        }
        return result;
    }

    /**
     * Create mission instance.
     *
     * @return created instance.
     */
    private Mission createMission() {
        Mission mission = new Mission();
        mission.setDescription("mission description");
        mission.setEndDate(createDate());
        mission.setId(4);
        mission.setName("mission name");
        mission.setOtherChildren(createMetadataObjects());
        mission.setReferences(createReferences());
        mission.setStartDate(createDate());
        return mission;
    }

    /**
     * Create mission List instance.
     *
     * @return created instance.
     */
    private List<Mission> createMissions() {
        List<Mission> missions = new ArrayList<Mission>();
        missions.add(createMission());
        return missions;
    }

    /**
     * Create instrument instances.
     *
     * @return created instance.
     */
    private List<Instrument> createInstruments() {
        List<Instrument> list = new ArrayList<Instrument>();
        Instrument instrument = new Instrument();
        instrument.setDescription("instrument description");
        instrument.setHosts(createInstrumentHosts());
        instrument.setId(1);
        instrument.setName("instrument name");
        instrument.setOtherChildren(createMetadataObjects());
        instrument.setReferences(createReferences());
        instrument.setTextId("text 1");
        instrument.setType("type 1");
        list.add(instrument);
        return list;
    }

    /**
     * Create InstrumentHost instance.
     *
     * @return created instance.
     */
    private InstrumentHost createInstrumentHost() {
        InstrumentHost host = new InstrumentHost();
        host.setId(1);
        host.setName("host name");
        host.setOtherChildren(createMetadataObjects());
        host.setReferences(createReferences());
        host.setTextId("host text id");
        return host;
    }

    /**
     * Create InstrumentHost List instance.
     *
     * @return created instance.
     */
    private List<InstrumentHost> createInstrumentHosts() {
        List<InstrumentHost> hosts = new ArrayList<InstrumentHost>();
        hosts.add(createInstrumentHost());
        return hosts;
    }

    /**
     * Create MetadataObject instances.
     *
     * @return created instance.
     */
    private List<MetadataObject> createMetadataObjects() {
        List<MetadataObject> list = new ArrayList<MetadataObject>();
        list.add(createMetadataObject());
        return list;
    }

    /**
     * Create MetadataObject instance.
     *
     * @return created instance.
     */
    private MetadataObject createMetadataObject() {
        MetadataObject object = new MetadataObject();
        object.setId(1);
        object.setName("");
        object.setProperties(createProperties());

        MetadataObject child = new MetadataObject();
        child.setId(2);
        child.setName("");
        child.setProperties(new ArrayList<Property>());
        List<MetadataObject> list = new ArrayList<MetadataObject>();
        list.add(child);
        object.setChildren(list);

        return object;

    }

    /**
     * Create Property instances.
     *
     * @return created instance.
     */
    private List<Property> createProperties() {
        List<Property> list = new ArrayList<Property>();
        Property property = new Property();
        property.setId(1);
        property.setName("property name");
        List<String> values = new ArrayList<String>();
        values.add("value 1");
        values.add("value 2");
        property.setValues(values);

        list.add(property);

        return list;
    }

    /**
     * Create DataSetSearchCriteria instance.
     *
     * @return created instance.
     */
    private SearchCriteria createDataSetSearchCriteria() {
        SearchCriteria c = new SearchCriteria();
        c.setDataSetId(1);
        List<String> list = new ArrayList<String>();
        list.add("1");
        c.setInstrumentHosts(list);

        list = new ArrayList<String>();
        list.add("2");
        c.setInstruments(list);
        //c.setLowerDataRating(1F);

        list = new ArrayList<String>();
        list.add("3");
        c.setMissions(list);
        //c.setUpperDataRating(2F);

        return c;
    }

    /**
     * Test {@link SearchCriteria#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledDataSetSearchCriteria() {
        SearchCriteria dataSetSearchCriteria = createDataSetSearchCriteria();
        System.out.println("dataSetSearchCriteria.toJSONString():");
        System.out.println(dataSetSearchCriteria.toJSONString());
    }

    /**
     * Test {@link ElementAlias#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledElementAlias() {
        ElementAlias elementAlias = new ElementAlias();

        elementAlias.setAlias("alias");
        elementAlias.setAnotherName("anotherName");
        elementAlias.setFullName("fullName");
        elementAlias.setId(1);
        System.out.println("elementAlias.toJSONString():");
        System.out.println(elementAlias.toJSONString());
    }

    /**
     * Test {@link ElementValidation#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledElementValidation() {
        ElementValidation elementValidation = new ElementValidation();
        List<String> list = new ArrayList<String>();
        list.add("1");
        elementValidation.setAllowedValues(list);
        elementValidation.setId(1);
        elementValidation.setMaximum(2F);
        elementValidation.setMaximumLength(2);
        elementValidation.setMinimum(1F);
        elementValidation.setMinimumLength(1);
        elementValidation.setName("name");
        System.out.println("elementValidation.toJSONString():");
        System.out.println(elementValidation.toJSONString());
    }

    /**
     * Create errorItem instance.
     *
     * @return created instance.
     */
    private ErrorItem createErrorItem() {
        ErrorItem errorItem = new ErrorItem();
        errorItem.setErrorType(ErrorType.ALIAS);
        errorItem.setItemName("itemName");
        errorItem.setItemType(ItemType.OBJECT);
        return errorItem;
    }

    /**
     * Test {@link ErrorItem#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledErrorItem() {
        ErrorItem errorItem = createErrorItem();
        System.out.println("errorItem.toJSONString():");
        System.out.println(errorItem.toJSONString());
    }

    /**
     * Test {@link HelpFile#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledHelpFile() {
        HelpFile helpFile = new HelpFile();
        helpFile.setId(1);
        helpFile.setName("name");
        helpFile.setPath("path");
        System.out.println("helpFile.toJSONString():");

        System.out.println(helpFile.toJSONString());
    }

    /**
     * Test {@link Instrument#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledInstrument() {
        Instrument instrument = createInstruments().get(0);
        System.out.println("instrument.toJSONString():");
        System.out.println(instrument.toJSONString());
    }

    /**
     * Test {@link InstrumentHost#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledInstrumentHost() {
        InstrumentHost instrumentHost = createInstrumentHost();
        System.out.println("instrumentHost.toJSONString():");
        System.out.println(instrumentHost.toJSONString());
    }

    /**
     * Test {@link MetadataFile#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledMetadataFile() {
        MetadataFile metadataFile = new MetadataFile();
        metadataFile.setChildren(createMetadataObjects());
        metadataFile.setId(1);
        metadataFile.setName("name");
        metadataFile.setProperties(createProperties());
        System.out.println("metadataFile.toJSONString():");
        System.out.println(metadataFile.toJSONString());
    }

    /**
     * Test {@link MetadataObject#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledMetadataObject() {
        MetadataObject metadataObject = createMetadataObject();
        System.out.println("metadataObject.toJSONString():");
        System.out.println(metadataObject.toJSONString());
    }

    /**
     * Test {@link Mission#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledMission() {
        Mission mission = createMission();
        System.out.println("mission.toJSONString():");
        System.out.println(mission.toJSONString());
    }

    /**
     * Test {@link ObjectAlias#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledObjectAlias() {
        ObjectAlias objectAlias = new ObjectAlias();
        objectAlias.setAlias("alias");
        objectAlias.setFullName("fullName");
        objectAlias.setId(1);
        System.out.println("objectAlias.toJSONString():");
        System.out.println(objectAlias.toJSONString());
    }

    /**
     * Test {@link ObjectValidation#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledObjectValidation() {
        ObjectValidation objectValidation = new ObjectValidation();
        objectValidation.setId(1);
        objectValidation.setName("name");
        List<String> list = new ArrayList<String>();
        list.add("1");
        objectValidation.setOptionalElements(list);
        list = new ArrayList<String>();
        list.add("2");
        objectValidation.setOptionalObjects(list);
        list = new ArrayList<String>();
        list.add("3");
        objectValidation.setRequiredElements(list);
        list = new ArrayList<String>();
        list.add("4");
        objectValidation.setRequiredObjects(list);
        System.out.println("objectValidation.toJSONString():");
        System.out.println(objectValidation.toJSONString());
    }

    /**
     * Test {@link PagedResults#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledPagedResults() {
        PagedResults<Loggable> pagedResults = new PagedResults<Loggable>();
        List<Loggable> list = new ArrayList<Loggable>();
        list.add(createMetadataObject());
        pagedResults.setResults(list);
        pagedResults.setTotal(1);
        System.out.println("pagedResults.toJSONString():");
        System.out.println(pagedResults.toJSONString());
    }

    /**
     * Test personnel method with populated instance.
     */
    @Test
    public void testFilledPersonnel() {
        Personnel personnel = createPersonnel().get(0);
        System.out.println("personnel.toJSONString():");
        System.out.println(personnel.toJSONString());
    }

    /**
     * Test {@link Product#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledProduct() {
        Product product = new Product();
        product.setId(1);
        product.setName("name");
        System.out.println("product.toJSONString():");
        System.out.println(product.toJSONString());
    }

    /**
     * Test {@link Property#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledProperty() {
        Property property = createProperties().get(0);

        System.out.println("property.toJSONString():");
        System.out.println(property.toJSONString());
    }

    /**
     * Test {@link Reference#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledReference() {
        Reference reference = createReferences().get(0);
        System.out.println("reference.toJSONString():");
        System.out.println(reference.toJSONString());
    }

    /**
     * Test {@link Row#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledRow() {
        Row row = this.createRows().get(0);
        System.out.println("row.toJSONString():");
        System.out.println(row.toJSONString());
    }

    /**
     * Test {@link SequenceValueProperty#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledSequenceValueProperty() {
        SequenceValueProperty sequenceValueProperty = new SequenceValueProperty();
        sequenceValueProperty.setId(1);
        sequenceValueProperty.setName("name");
        List<String> list = new ArrayList<String>();
        list.add("1");
        sequenceValueProperty.setValues(list);
        List<List<String>> sequences = new ArrayList<List<String>>();
        list = new ArrayList<String>();
        list.add("2");
        sequences.add(list);
        sequenceValueProperty.setSequences(sequences);
        System.out.println("sequenceValueProperty.toJSONString():");
        System.out.println(sequenceValueProperty.toJSONString());
    }

    /**
     * Create Column instances.
     *
     * @return created instance.
     */
    private List<Column> createColumns() {
        List<Column> list = new ArrayList<Column>();
        Column column = new Column();
        column.setId(1);
        column.setName("name1");
        column.setDataType("date type 1");
        column.setDescription("description 1");
        column.setFormat("format 1");
        column.setOffset(1.0F);
        column.setScalingFactor(2.0F);
        column.setSize(3);
        column.setStartPosition(4);
        list.add(column);
        return list;
    }

    /**
     * Create Row instances.
     *
     * @return created instance.
     */
    private List<Row> createRows() {
        List<Row> list = new ArrayList<Row>();
        Row row = new Row();
        Cell cell = new Cell();
        cell.setColumn(createColumns().get(0));
        cell.setId(1);
        cell.setValue("value");
        List<Cell> cells = new ArrayList<Cell>();
        cells.add(cell);
        row.setCells(cells);
        list.add(row);
        return list;
    }

    /**
     * Test {@link Table#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledTable() {
        Table table = new Table();
        table.setId(1);
        table.setRowCount(1);
        table.setColumns(createColumns());
        table.setOtherProperties(createProperties());
        table.setRows(createRows());
        System.out.println("table.toJSONString():");
        System.out.println(table.toJSONString());
    }

    /**
     * Test {@link Target#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledTarget() {
        Target target = createTarget();
        System.out.println("target.toJSONString():");
        System.out.println(target.toJSONString());
    }

    /**
     * Test {@link TargetType#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledTargetType() {
        TargetType targetType = createTargetType();
        System.out.println("targetType.toJSONString():");
        System.out.println(targetType.toJSONString());
    }

    /**
     * Test {@link UnitAlias#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledUnitAlias() {
        UnitAlias unitAlias = new UnitAlias();
        unitAlias.setAlias("alias");
        unitAlias.setFullName("fullName");
        unitAlias.setId(1);
        System.out.println("unitAlias.toJSONString():");
        System.out.println(unitAlias.toJSONString());
    }

    /**
     * Test {@link ValidationReport#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledValidationReport() {
        ValidationReport validationReport = new ValidationReport();
        validationReport.setValid(true);
        List<ErrorItem> list = new ArrayList<ErrorItem>();
        list.add(createErrorItem());
        validationReport.setErrors(list);
        System.out.println("validationReport.toJSONString():");
        System.out.println(validationReport.toJSONString());
    }

    /**
     * Test {@link Volume#toJSONString()} method with populated instance.
     */
    @Test
    public void testFilledVolume() {
        Volume volume = createVolumes().get(0);
        System.out.println("volume.toJSONString():");
        System.out.println(volume.toJSONString());
    }

}
