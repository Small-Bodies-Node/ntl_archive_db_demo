/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import java.util.List;
import java.util.Map;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents a volume.
 *
 * Thread Safety: This class is mutable and not thread safe.
 */
public class Volume extends NamedEntity implements MetaDataObjectConsumer {
    /**
     * Constant for "VOLUME_NAME" String.
     */
    private static final String VOLUME_NAME = "VOLUME_NAME";
    /**
     * Constant for "DESCRIPTION" String.
     */
    private static final String DESCRIPTION = "DESCRIPTION";
    /**
     * Constant for "VOLUME_ID" String.
     */
    private static final String VOLUME_ID = "VOLUME_ID";
    /**
     * Constant for "VOLUME_SET_ID" String.
     */
    private static final String VOLUME_SET_ID = "VOLUME_SET_ID";
    /**
     * Constant for "VOLUME_SET_NAME" String.
     */
    private static final String VOLUME_SET_NAME = "VOLUME_SET_NAME";
    /**
     * Constant for "VOLUME_SERIES_NAME" String.
     */
    private static final String VOLUME_SERIES_NAME = "VOLUME_SERIES_NAME";

    /**
     * Represents the description. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private String description;
    /**
     * Represents the primary ID defined in the original file. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     */
    private String textId;
    /**
     * Represents the setTextId. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private String setTextId;
    /**
     * Represents the setName. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private String setName;
    /**
     * Represents the seriesName. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private String seriesName;

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
     * Empty constructor.
     */
    public Volume() {
    }

    /**
     * Constructor. Create the instance with name.
     *
     * @param name
     *            - the given name to set.
     */
    public Volume(String name) {
        super(name);
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
     * Gets the setTextId value.
     *
     * @return - the setTextId value.
     */
    public String getSetTextId() {
        return setTextId;
    }

    /**
     * Sets the given value to setTextId.
     *
     * @param setTextId
     *            - the given value to set.
     */
    public void setSetTextId(String setTextId) {
        this.setTextId = setTextId;
    }

    /**
     * Gets the setName value.
     *
     * @return - the setName value.
     */
    public String getSetName() {
        return setName;
    }

    /**
     * Sets the given value to setName.
     *
     * @param setName
     *            - the given value to set.
     */
    public void setSetName(String setName) {
        this.setName = setName;
    }

    /**
     * Gets the seriesName value.
     *
     * @return - the seriesName value.
     */
    public String getSeriesName() {
        return seriesName;
    }

    /**
     * Sets the given value to seriesName.
     *
     * @param seriesName
     *            - the given value to set.
     */
    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
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
        Map<String, Property> map = EntityHelper.getProperties(properties, new String[] { VOLUME_NAME, DESCRIPTION,
                VOLUME_ID, VOLUME_SET_ID, VOLUME_SET_NAME, VOLUME_SERIES_NAME });

        setName(EntityHelper.getPropertyStringValue(map.get(VOLUME_NAME)));
        this.description = EntityHelper.getPropertyStringValue(map.get(DESCRIPTION));
        this.textId = EntityHelper.getPropertyStringValue(map.get(VOLUME_ID));
        this.setTextId = EntityHelper.getPropertyStringValue(map.get(VOLUME_SET_ID));
        this.setName = EntityHelper.getPropertyStringValue(map.get(VOLUME_SET_NAME));
        this.seriesName = EntityHelper.getPropertyStringValue(map.get(VOLUME_SERIES_NAME));

        this.otherProperties = EntityHelper.removeUsedProperties(properties, map);

        this.otherChildren = metaDataObject.getChildren();
    }

    /**
     * Gets the JSONObject instance.
     *
     * @return - the JSONObject for this instance.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = super.toJSONObject();
        EntityHelper.setString(object, "description", description);
        EntityHelper.setString(object, "textId", textId);
        EntityHelper.setString(object, "setTextId", setTextId);
        EntityHelper.setString(object, "setName", setName);
        EntityHelper.setString(object, "seriesName", seriesName);

        EntityHelper.setArray(object, "otherProperties", otherProperties);

        EntityHelper.setArray(object, "otherChildren", otherChildren);
        return object;
    }
}
