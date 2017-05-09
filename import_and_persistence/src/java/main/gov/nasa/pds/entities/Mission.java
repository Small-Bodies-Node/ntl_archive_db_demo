/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents a mission.
 *
 * Thread Safety: This class is mutable and not thread safe.
 */
public class Mission extends NamedEntity implements MetaDataObjectConsumer {
    /**
     * Constant for "MISSION_NAME" String.
     */
    private static final String MISSION_NAME = "MISSION_NAME";
    /**
     * Constant for "MISSION_INFORMATION" String.
     */
    private static final String MISSION_INFORMATION = "MISSION_INFORMATION";
    /**
     * Constant for "MISSION_START_DATE" String.
     */
    private static final String MISSION_START_DATE = "MISSION_START_DATE";
    /**
     * Constant for "MISSION_STOP_DATE" String.
     */
    private static final String MISSION_STOP_DATE = "MISSION_STOP_DATE";
    /**
     * Constant for "MISSION_DESC" String.
     */
    private static final String MISSION_DESC = "MISSION_DESC";
    /**
     * Constant for "MISSION_REFERENCE_INFORMATION" String.
     */
    private static final String MISSION_REFERENCE_INFORMATION = "MISSION_REFERENCE_INFORMATION";

    /**
     * Represents the startDate. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private Date startDate;
    /**
     * Represents the endDate. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private Date endDate;
    /**
     * Represents the description. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private String description;
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
    public Mission() {
    }

    /**
     * Constructor. Create the instance with name.
     *
     * @param name
     *            - the given name to set.
     */
    public Mission(String name) {
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
        Map<String, Property> map = EntityHelper.getProperties(properties, new String[] { MISSION_NAME });

        setName(EntityHelper.getPropertyStringValue(map.get(MISSION_NAME)));

        references = EntityHelper.createReferencesList(MISSION_REFERENCE_INFORMATION, children);

        MetadataObject missionInfo = EntityHelper.findMetadataObject(MISSION_INFORMATION, children);
        if (missionInfo != null) {
            Map<String, Property> missionInfoMap = EntityHelper.getProperties(missionInfo.getProperties(),
                    new String[] { MISSION_START_DATE, MISSION_STOP_DATE, MISSION_DESC });
            this.startDate = EntityHelper.getPropertyDateValue(missionInfoMap.get(MISSION_START_DATE), "yyyy-MM-dd");
            if (this.startDate == null) {
                this.startDate = EntityHelper.getPropertyDateValue(missionInfoMap.get(MISSION_START_DATE), "yyyy-MM");
            }
            this.endDate = EntityHelper.getPropertyDateValue(missionInfoMap.get(MISSION_STOP_DATE), "yyyy-MM-dd");
            if (this.endDate == null) {
                this.endDate = EntityHelper.getPropertyDateValue(missionInfoMap.get(MISSION_STOP_DATE), "yyyy-MM");
            }
            this.description = EntityHelper.getPropertyStringValue(missionInfoMap.get(MISSION_DESC));
        }
        this.otherChildren = children;

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
     * Gets the endDate value.
     *
     * @return - the endDate value.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the given value to endDate.
     *
     * @param endDate
     *            - the given value to set.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
     * Gets the JSONObject instance.
     *
     * @return - the JSONObject for this instance.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = super.toJSONObject();
        EntityHelper.setString(object, "startDate", EntityHelper.convertDateToString(startDate));
        EntityHelper.setString(object, "endDate", EntityHelper.convertDateToString(endDate));
        EntityHelper.setString(object, "description", description);

        EntityHelper.setArray(object, "references", references);

        EntityHelper.setArray(object, "otherChildren", otherChildren);
        return object;
    }
}
