/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents the data set product (like a table of data, or image, etc...).
 *
 * Thread Safety: This class is mutable and not thread safe.
 */
public class Product extends NamedEntity implements MetaDataObjectConsumer {
    /**
     * Constant for "PRODUCT_NAME" String.
     */
    private static final String PRODUCT_NAME = "PRODUCT_NAME";
    /**
     * Constant for "PRODUCT_ID" String.
     */
    private static final String PRODUCT_ID = "PRODUCT_ID";
    /**
     * Constant for "START_TIME" String.
     */
    private static final String START_TIME = "START_TIME";
    /**
     * Constant for "STOP_TIME" String.
     */
    private static final String STOP_TIME = "STOP_TIME";

    /**
     * Constant for "DESCRIPTION" String.
     */
    private static final String DESCRIPTION = "DESCRIPTION";

    /**
     * Constant for "RECORD_TYPE" String.
     */
    private static final String RECORD_TYPE = "RECORD_TYPE";

    /**
     * Constant for "RECORD_BYTES" String.
     */
    private static final String RECORD_BYTES = "RECORD_BYTES";

    /**
     * Constant for "FILE_RECORDS" String.
     */
    private static final String FILE_RECORDS = "FILE_RECORDS";
    /**
     * Represents the primary ID defined in the original file. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     */
    private String textId;
    /**
     * Represents the startTime. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private Date startTime;
    /**
     * Represents the stopTime. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private Date stopTime;

    /**
     * Represents the structure of other properties of this entity. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     */
    private List<Property> otherProperties;

    /**
     * Represents the structure of other objects of this entity. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     */
    private List<MetadataObject> otherChildren;
    /**
     * Represents the description of this entity. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     */
    private String description;
    /**
     * Represents the recordType of this entity. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     */
    private RecordType recordType;
    /**
     * Represents recordByteSize of this entity. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     */
    private Integer recordByteSize;
    /**
     * Represents recordCount of this entity. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     */
    private Integer recordCount;

    /**
     * Empty constructor
     */
    public Product() {
    }

    /**
     * Constructor. Create the instance with name.
     *
     * @param name
     *            - the given name to set.
     */
    public Product(String name) {
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
        Map<String, Property> map = EntityHelper.getProperties(properties, new String[] { PRODUCT_NAME, PRODUCT_ID,
                START_TIME, STOP_TIME, DESCRIPTION, RECORD_TYPE, RECORD_BYTES, FILE_RECORDS });

        setName(EntityHelper.getPropertyStringValue(map.get(PRODUCT_NAME)));
        this.textId = EntityHelper.getPropertyStringValue(map.get(PRODUCT_ID));
        this.startTime = EntityHelper.getPropertyDateValue(map.get(START_TIME));
        this.stopTime = EntityHelper.getPropertyDateValue(map.get(STOP_TIME));

        this.description = EntityHelper.getPropertyStringValue(map.get(DESCRIPTION));
        this.recordType = EntityHelper.getPropertyRecordTypeValue(map.get(RECORD_TYPE));
        this.recordByteSize = EntityHelper.getPropertyIntValue(map.get(RECORD_BYTES));
        this.recordCount = EntityHelper.getPropertyIntValue(map.get(FILE_RECORDS));

        this.otherProperties = EntityHelper.removeUsedProperties(properties, map);

        this.otherChildren = metaDataObject.getChildren();
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
     * Gets the startTime value.
     *
     * @return - the startTime value.
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Sets the given value to startTime.
     *
     * @param startTime
     *            - the given value to set.
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the stopTime value.
     *
     * @return - the stopTime value.
     */
    public Date getStopTime() {
        return stopTime;
    }

    /**
     * Sets the given value to stopTime.
     *
     * @param stopTime
     *            - the given value to set.
     */
    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    /**
     * Gets the otherProperties value.
     *
     * @return - the otherProperties value.
     */
    public List<Property> getOtherProperties() {
        return otherProperties;
    }

    /**
     * Sets the given value to otherProperties.
     *
     * @param otherProperties
     *            - the given value to set.
     */
    public void setOtherProperties(List<Property> otherProperties) {
        this.otherProperties = otherProperties;
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
    }    /**
     * Gets the recordType value.
     *
     * @return - the recordType value.
     */
    public RecordType getRecordType() {
        return recordType;
    }
    /**
     * Sets the given value to recordType.
     *
     * @param recordType
     *            - the given value to set.
     */
    public void setRecordType(RecordType recordType) {
        this.recordType = recordType;
    }
    /**
     * Gets the recordByteSize value.
     *
     * @return - the recordByteSize value.
     */
    public Integer getRecordByteSize() {
        return recordByteSize;
    }
    /**
     * Sets the given value to recordByteSize.
     *
     * @param recordByteSize
     *            - the given value to set.
     */
    public void setRecordByteSize(Integer recordByteSize) {
        this.recordByteSize = recordByteSize;
    }
    /**
     * Gets the recordCount value.
     *
     * @return - the recordCount value.
     */
    public Integer getRecordCount() {
        return recordCount;
    }
    /**
     * Sets the given value to recordCount.
     *
     * @param recordCount
     *            - the given value to set.
     */
    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
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
        EntityHelper.setString(object, "startTime", EntityHelper.convertDateToString(startTime));
        EntityHelper.setString(object, "stopTime", EntityHelper.convertDateToString(stopTime));

        EntityHelper.setArray(object, "otherProperties", otherProperties);
        EntityHelper.setArray(object, "otherChildren", otherChildren);

        EntityHelper.setString(object, "description", description);
        EntityHelper.setString(object, "recordType", recordType != null ? recordType.toString(): "");
        EntityHelper.setInt(object, "recordByteSize", recordByteSize);
        EntityHelper.setInt(object, "recordCount", recordCount);

        return object;
    }
}
