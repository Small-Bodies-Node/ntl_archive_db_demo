/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import java.util.List;
import java.util.Map;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents a person.
 * 
 * Thread Safety: This class is mutable and not thread safe.
 */
public class Personnel extends IdentifiableEntity implements MetaDataObjectConsumer {
    /**
     * Constant for "PDS_USER_ID" String.
     */
    private static final String PDS_USER_ID = "PDS_USER_ID";
    /**
     * Constant for "PERSONNEL_INFORMATION" String.
     */
    private static final String PERSONNEL_INFORMATION = "PERSONNEL_INFORMATION";
    /**
     * Constant for "FULL_NAME" String.
     */
    private static final String FULL_NAME = "FULL_NAME";
    /**
     * Represents the userId. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private String userId;
    /**
     * Represents the fullName. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private String fullName;
    /**
     * Represents the structure of other objects of this entity. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     */
    private List<MetadataObject> otherChildren;

    /**
     * Empty constructor
     */
    public Personnel() {
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
        Map<String, Property> map = EntityHelper.getProperties(properties, new String[] { PDS_USER_ID });

        this.userId = EntityHelper.getPropertyStringValue(map.get(PDS_USER_ID));

        MetadataObject personnelInfo = EntityHelper.findMetadataObject(PERSONNEL_INFORMATION, children);
        if (personnelInfo != null) {
            Map<String, Property> personnelInfoMap = EntityHelper.getProperties(personnelInfo.getProperties(),
                    new String[] { FULL_NAME });
            this.fullName = EntityHelper.getPropertyStringValue(personnelInfoMap.get(FULL_NAME));
        }
        this.otherChildren = children;
    }

    /**
     * Gets the userId value.
     * 
     * @return - the userId value.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the given value to userId.
     * 
     * @param userId
     *            - the given value to set.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the fullName value.
     * 
     * @return - the fullName value.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the given value to fullName.
     * 
     * @param fullName
     *            - the given value to set.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
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
        EntityHelper.setString(object, "userId", userId);
        EntityHelper.setString(object, "fullName", fullName);

        EntityHelper.setArray(object, "otherChildren", otherChildren);
        return object;
    }
}
