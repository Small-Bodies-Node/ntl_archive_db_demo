/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents error item found during validation.
 * 
 * Thread Safety: This class is mutable and not thread safe.
 */
public class ErrorItem implements Loggable {
    /**
     * Represents the name of the item that was faulty. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     */
    private String itemName;
    /**
     * Represents the type of the item. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     */
    private ItemType itemType;
    /**
     * Represents the type of the error encountered. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     */
    private ErrorType errorType;

    /**
     * Empty constructor.
     */
    public ErrorItem() {
    }

    /**
     * Gets the itemName value.
     * 
     * @return - the itemName value.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the given value to itemName.
     * 
     * @param itemName
     *            - the given value to set.
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Gets the itemType value.
     * 
     * @return - the itemType value.
     */
    public ItemType getItemType() {
        return itemType;
    }

    /**
     * Sets the given value to itemType.
     * 
     * @param itemType
     *            - the given value to set.
     */
    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    /**
     * Gets the errorType value.
     * 
     * @return - the errorType value.
     */
    public ErrorType getErrorType() {
        return errorType;
    }

    /**
     * Sets the given value to errorType.
     * 
     * @param errorType
     *            - the given value to set.
     */
    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    /**
     * Gets the JSONObject instance.
     * 
     * @return - the JSONObject for this instance.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        EntityHelper.setString(object, "itemName", itemName);
        EntityHelper.setString(object, "itemType", EntityHelper.convertObjectToString(itemType));
        EntityHelper.setString(object, "errorType", EntityHelper.convertObjectToString(errorType));
        return object;
    }

    /**
     * Provides a visible version of the entity.
     * 
     * @return - a visible version of the entity
     */
    @Override
    public String toJSONString() {
        return toJSONObject().toJSONString();
    }
}
