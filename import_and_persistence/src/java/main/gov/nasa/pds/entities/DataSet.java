/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents the data set.
 *
 * Thread Safety: This class is mutable and not thread safe.
 */
public class DataSet extends NamedEntity implements MetaDataObjectConsumer {
    /**
     * Constant for "DATA_SET_ID" String.
     */
    private static final String DATA_SET_ID = "DATA_SET_ID";
    /**
     * Constant for "DATA_SET_TARGET" String.
     */
    private static final String DATA_SET_TARGET = "DATA_SET_TARGET";
    /**
     * Constant for "DATA_SET_MISSION" String.
     */
    private static final String DATA_SET_MISSION = "DATA_SET_MISSION";
    /**
     * Constant for "DATA_SET_REFERENCE_INFORMATION" String.
     */
    private static final String DATA_SET_REFERENCE_INFORMATION = "DATA_SET_REFERENCE_INFORMATION";
    /**
     * Constant for "DATA_SET_INFORMATION" String.
     */
    private static final String DATA_SET_INFORMATION = "DATA_SET_INFORMATION";
    /**
     * Constant for "DATA_SET_NAME" String.
     */
    private static final String DATA_SET_NAME = "DATA_SET_NAME";
    /**
     * Constant for "DATA_SET_DESC" String.
     */
    private static final String DATA_SET_DESC = "DATA_SET_DESC";
    /**
     * Constant for "DATA_SET_HOST" String.
     */
    private static final String DATA_SET_HOST = "DATA_SET_HOST";
    /**
     * Constant for "START_TIME" String.
     */
    private static final String START_TIME = "START_TIME";
    /**
     * Constant for "STOP_TIME" String.
     */
    private static final String STOP_TIME = "STOP_TIME";

    /**
     * Represents the primary ID defined in the original file. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     */
    private String textId;
    /**
     * Represents the start date. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private Date startDate;
    /**
     * Represents the stop date. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private Date stopDate;
    /**
     * Represents the description. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private String description;

    /**
     * Represents the rating number to be assigned to this data set in regards to its coverage of the target. It is
     * managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private Float rating;

    /**
     * Represents the volumes associated with this data set. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     */
    private List<Volume> volumes;
    /**
     * Represents the targets. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private List<Target> targets;
    /**
     * Represents the missions. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private List<Mission> missions;
    /**
     * Represents the instruments associated with this data set. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     */
    private List<Instrument> instruments;
    /**
     * Represents the references. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private List<Reference> references;

    /**
     * Represents the structure of other objects of this entity. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     */
    private List<MetadataObject> otherChildren;

    /**
     * Empty constructor.
     */
    public DataSet() {
    }

    /**
     * Constructor. Create the instance with name.
     *
     * @param name
     *            - the given name to set.
     */
    public DataSet(String name) {
        super(name);
    }

    /**
     * Populates the instance with given metaDataObject.
     *
     * @param metaDataObject
     *            - the given object to handle.
     */
    @Override
    public void fromMetadata(MetadataObject metaDataObject) {
        if (metaDataObject == null) {
            return;
        }
        List<Property> properties = metaDataObject.getProperties();
        List<MetadataObject> children = metaDataObject.getChildren();
        Map<String, Property> map = EntityHelper.getProperties(properties, new String[] { DATA_SET_ID });

        this.textId = EntityHelper.getPropertyStringValue(map.get(DATA_SET_ID));

        targets = EntityHelper.createTargetsList(DATA_SET_TARGET, children);

        missions = EntityHelper.createMissionsList(DATA_SET_MISSION, children);

        references = EntityHelper.createReferencesList(DATA_SET_REFERENCE_INFORMATION, children);

        instruments = EntityHelper.createInstrumentsList(DATA_SET_HOST, children);

        // read the data in information set
        MetadataObject dataSetInfo = EntityHelper.findMetadataObject(DATA_SET_INFORMATION, children);
        if (dataSetInfo != null) {
            Map<String, Property> dataSetInfoMap = EntityHelper.getProperties(dataSetInfo.getProperties(),
                    new String[] { DATA_SET_NAME, DATA_SET_DESC, START_TIME, STOP_TIME });
            setName(EntityHelper.getPropertyStringValue(dataSetInfoMap.get(DATA_SET_NAME)));
            this.description = EntityHelper.getPropertyStringValue(dataSetInfoMap.get(DATA_SET_DESC));
            this.startDate = EntityHelper.getPropertyDateValue(dataSetInfoMap.get(START_TIME));
            this.stopDate = EntityHelper.getPropertyDateValue(dataSetInfoMap.get(STOP_TIME));
        }

        this.otherChildren = children;
    }

    /**
     * Gets the textId value.
     *
     * @return - the textId value.
     */
    public String getTextId() {
        return textId;
    }

    /**
     * Sets the given value to textId.
     *
     * @param textId
     *            - the given value to set.
     */
    public void setTextId(String textId) {
        this.textId = textId;
    }

    /**
     * Gets the startDate value.
     *
     * @return - the startDate value.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the given value to startDate.
     *
     * @param startDate
     *            - the given value to set.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the stopDate value.
     *
     * @return - the stopDate value.
     */
    public Date getStopDate() {
        return stopDate;
    }

    /**
     * Sets the given value to stopDate.
     *
     * @param stopDate
     *            - the given value to set.
     */
    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    /**
     * Gets the targets value.
     *
     * @return - the targets value.
     */
    public List<Target> getTargets() {
        return targets;
    }

    /**
     * Sets the given value to targets.
     *
     * @param targets
     *            - the given value to set.
     */
    public void setTargets(List<Target> targets) {
        this.targets = targets;
    }

    /**
     * Gets the missions value.
     *
     * @return - the missions value.
     */
    public List<Mission> getMissions() {
        return missions;
    }

    /**
     * Sets the given value to missions.
     *
     * @param missions
     *            - the given value to set.
     */
    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    /**
     * Gets the description value.
     *
     * @return - the description value.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the given value to description.
     *
     * @param description
     *            - the given value to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the references value.
     *
     * @return - the references value.
     */
    public List<Reference> getReferences() {
        return references;
    }

    /**
     * Sets the given value to references.
     *
     * @param references
     *            - the given value to set.
     */
    public void setReferences(List<Reference> references) {
        this.references = references;
    }

    /**
     * Gets the otherChildren value.
     *
     * @return - the otherChildren value.
     */
    public List<MetadataObject> getOtherChildren() {
        return otherChildren;
    }

    /**
     * Sets the given value to otherChildren.
     *
     * @param otherChildren
     *            - the given value to set.
     */
    public void setOtherChildren(List<MetadataObject> otherChildren) {
        this.otherChildren = otherChildren;
    }

    /**
     * Gets the volumes value.
     *
     * @return - the volumes value.
     */
    public List<Volume> getVolumes() {
        return volumes;
    }

    /**
     * Sets the given value to volumes.
     *
     * @param volumes
     *            - the given value to set.
     */
    public void setVolumes(List<Volume> volumes) {
        this.volumes = volumes;
    }

    /**
     * Gets the instruments value.
     *
     * @return - the instruments value.
     */
    public List<Instrument> getInstruments() {
        return instruments;
    }

    /**
     * Sets the given value to instruments.
     *
     * @param instruments
     *            - the given value to set.
     */
    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }

    /**
     * Gets the rating value.
     *
     * @return - the rating value.
     */
    public Float getRating() {
        return rating;
    }

    /**
     * Sets the given value to rating.
     *
     * @param rating
     *            - the given value to set.
     */
    public void setRating(Float rating) {
        this.rating = rating;
    }

    /**
     * Gets the JSONObject instance.
     *
     * @return - the JSONObject for this instance.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = super.toJSONObject();
        EntityHelper.setString(object, "textId", textId);
        EntityHelper.setString(object, "startDate", EntityHelper.convertDateToString(startDate));
        EntityHelper.setString(object, "stopDate", EntityHelper.convertDateToString(stopDate));

        EntityHelper.setArray(object, "target", targets);
        EntityHelper.setArray(object, "mission", missions);

        EntityHelper.setString(object, "description", description);

        EntityHelper.setArray(object, "references", references);

        EntityHelper.setArray(object, "otherChildren", otherChildren);

        EntityHelper.setArray(object, "volumes", volumes);

        EntityHelper.setArray(object, "instruments", instruments);

        EntityHelper.setFloat(object, "rating", rating);
        return object;
    }
}
