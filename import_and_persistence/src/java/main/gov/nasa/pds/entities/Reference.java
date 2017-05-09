/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import java.util.List;
import java.util.Map;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents Reference instance.
 * 
 * Thread Safety: This class is mutable and not thread safe.
 */
public class Reference extends IdentifiableEntity implements MetaDataObjectConsumer {
    /**
     * Constant for "REFERENCE_KEY_ID" String.
     */
    private static final String REFERENCE_KEY_ID = "REFERENCE_KEY_ID";
    /**
     * Constant for "REFERENCE_DESC" String.
     */
    private static final String REFERENCE_DESC = "REFERENCE_DESC";
    /**
     * Represents the keyTextId. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private String keyTextId;
    /**
     * Represents the description. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private String description;

    /**
     * Empty constructor
     */
    public Reference() {
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
        Map<String, Property> map = EntityHelper.getProperties(properties, new String[] { REFERENCE_KEY_ID,
                REFERENCE_DESC });

        this.keyTextId = EntityHelper.getPropertyStringValue(map.get(REFERENCE_KEY_ID));
        this.description = EntityHelper.getPropertyStringValue(map.get(REFERENCE_DESC));
    }

    /**
     * Gets the keyTextId value.
     * 
     * @return - the keyTextId value.
     */
    public String getKeyTextId() {
        return keyTextId;
    }

    /**
     * Sets the given value to keyTextId.
     * 
     * @param keyTextId
     *            - the given value to set.
     */
    public void setKeyTextId(String keyTextId) {
        this.keyTextId = keyTextId;
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
     * Gets the JSONObject instance.
     * 
     * @return - the JSONObject for this instance.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = super.toJSONObject();
        EntityHelper.setString(object, "keyTextId", keyTextId);
        EntityHelper.setString(object, "description", description);

        return object;
    }

}
