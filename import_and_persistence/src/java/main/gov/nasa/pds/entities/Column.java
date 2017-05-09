/*
 * Copyright (C) 2011-2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import java.util.List;
import java.util.Map;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents columns in data set table.
 * 
 * NOTE: per-constant javadocs are not provided, each constant is named just after corresponding string value.
 * 
 * Thread Safety: This class is mutable and not thread safe
 * 
 * <p>
 * Version 1.1 changes [PDS - Import and Persistence Update - Assembly Contest]:
 * <ol>
 * <li>Added the following elements: ITEMS, ITEM_BYTES, ITEM_OFFSET.</li>
 * <li>Removed BIT_COLUMN_NAME property which never used - datasets use just NAME property for BIT_COLUMNs.</li>
 * </ol>
 * </p>
 * 
 * @author TCSASSEMBLER
 * @version 1.1
 */
public class Column extends NamedEntity implements MetaDataObjectConsumer {
    // The elements of the COLUMN object.
    private static final String NAME = "NAME";
    private static final String START_BYTE = "START_BYTE";
    private static final String BYTES = "BYTES";
    private static final String DATA_TYPE = "DATA_TYPE";
    private static final String FORMAT = "FORMAT";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String SCALING_FACTOR = "SCALING_FACTOR";
    private static final String OFFSET = "OFFSET";
    private static final String ITEMS = "ITEMS";
    private static final String ITEM_BYTES = "ITEM_BYTES";
    private static final String ITEM_OFFSET = "ITEM_OFFSET";
    private static final String BIT_COLUMN = "BIT_COLUMN";

    /**
     * Represents the start position. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     */
    private int startPosition;
    /**
     * Represents the size. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private Integer size;
    /**
     * Represents the data type. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private String dataType;
    /**
     * Represents the format. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private String format;
    /**
     * Represents the description. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private String description;
    /**
     * Represents the scaling factor. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     */
    private Float scalingFactor;
    /**
     * Represents the offset. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private Float offset;

    /**
     * Represents the bitColumn. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private boolean bitColumn;

    /**
     * The number of occurrences of the field (i.e. elements in the vector).
     */
    private Integer items;

    /**
     * The number of bytes that one item occupies.
     */
    private Integer itemBytes;

    /**
     * The number of bytes from the beginning of one item to the beginning of the next.
     */
    private Integer itemOffset;

    /**
     * Represents the otherProperties. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     */
    private List<Property> otherProperties;
    /**
     * Represents the otherChildren. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private List<MetadataObject> otherChildren;

    /**
     * Empty constructor.
     */
    public Column() {
    }

    /**
     * Constructor. Create the instance with name.
     * 
     * @param name
     *            - the given name to set.
     */
    public Column(String name) {
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
        List<MetadataObject> children = metaDataObject.getChildren();
        List<Property> properties = metaDataObject.getProperties();
        Map<String, Property> map = EntityHelper.getProperties(properties, new String[] { NAME, START_BYTE, BYTES,
                DATA_TYPE, FORMAT, DESCRIPTION, SCALING_FACTOR, OFFSET, ITEMS, ITEM_BYTES, ITEM_OFFSET });

        setName(EntityHelper.getPropertyStringValue(map.get(NAME)));
        this.startPosition = EntityHelper.getPropertyIntValue(map.get(START_BYTE));
        this.size = EntityHelper.getPropertyIntegerValue(map.get(BYTES));
        this.dataType = EntityHelper.getPropertyStringValue(map.get(DATA_TYPE));
        this.format = EntityHelper.getPropertyStringValue(map.get(FORMAT));
        this.description = EntityHelper.getPropertyStringValue(map.get(DESCRIPTION));
        this.scalingFactor = EntityHelper.getPropertyFloatValue(map.get(SCALING_FACTOR));
        this.offset = EntityHelper.getPropertyFloatValue(map.get(OFFSET));
        this.items = EntityHelper.getPropertyIntegerValue(map.get(ITEMS));
        this.itemBytes = EntityHelper.getPropertyIntegerValue(map.get(ITEM_BYTES));
        this.itemOffset = EntityHelper.getPropertyIntegerValue(map.get(ITEM_OFFSET));

        // read the data in bit column set
        MetadataObject bitColumnInfo = EntityHelper.findMetadataObject(BIT_COLUMN, children);
        if (bitColumnInfo != null) {
            this.bitColumn = true;
        }

        this.otherProperties = EntityHelper.removeUsedProperties(properties, map);
        this.otherChildren = children;
    }

    /**
     * Gets the startPosition value.
     * 
     * @return - the startPosition value.
     */
    public int getStartPosition() {
        return startPosition;
    }

    /**
     * Sets the given value to startPosition.
     * 
     * @param startPosition
     *            - the given value to set.
     */
    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    /**
     * Gets the dataType value.
     * 
     * @return - the dataType value.
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * Sets the given value to dataType.
     * 
     * @param dataType
     *            - the given value to set.
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * Gets the format value.
     * 
     * @return - the format value.
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the given value to format.
     * 
     * @param format
     *            - the given value to set.
     */
    public void setFormat(String format) {
        this.format = format;
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
     * Gets the scalingFactor value.
     * 
     * @return - the scalingFactor value.
     */
    public Float getScalingFactor() {
        return scalingFactor;
    }

    /**
     * Sets the given value to scalingFactor.
     * 
     * @param scalingFactor
     *            - the given value to set.
     */
    public void setScalingFactor(Float scalingFactor) {
        this.scalingFactor = scalingFactor;
    }

    /**
     * Gets the offset value.
     * 
     * @return - the offset value.
     */
    public Float getOffset() {
        return offset;
    }

    /**
     * Sets the given value to offset.
     * 
     * @param offset
     *            - the given value to set.
     */
    public void setOffset(Float offset) {
        this.offset = offset;
    }

    /**
     * Gets the size value.
     * 
     * @return - the size value.
     */
    public Integer getSize() {
        return size;
    }

    /**
     * Sets the given value to size.
     * 
     * @param size
     *            - the given value to set.
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * Gets the items.
     * 
     * @return the items
     */
    public Integer getItems() {
        return items;
    }

    /**
     * Sets the items.
     * 
     * @param items
     *            the items to set
     */
    public void setItems(Integer items) {
        this.items = items;
    }

    /**
     * Gets the itemBytes.
     * 
     * @return the itemBytes
     */
    public Integer getItemBytes() {
        return itemBytes;
    }

    /**
     * Sets the itemBytes.
     * 
     * @param itemBytes
     *            the itemBytes to set
     */
    public void setItemBytes(Integer itemBytes) {
        this.itemBytes = itemBytes;
    }

    /**
     * Gets the itemOffset.
     * 
     * @return the itemOffset
     */
    public Integer getItemOffset() {
        return itemOffset;
    }

    /**
     * Sets the itemOffset.
     * 
     * @param itemOffset
     *            the itemOffset to set
     */
    public void setItemOffset(Integer itemOffset) {
        this.itemOffset = itemOffset;
    }

    /**
     * Gets the bitColumn value.
     * 
     * @return - the bitColumn value.
     */
    public boolean isBitColumn() {
        return bitColumn;
    }

    /**
     * Sets the given value to bitColumn.
     * 
     * @param bitColumn
     *            - the given value to set.
     */
    public void setBitColumn(boolean bitColumn) {
        this.bitColumn = bitColumn;
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
     * Gets the JSONObject instance.
     * 
     * @return - the JSONObject for this instance.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = super.toJSONObject();
        EntityHelper.setInt(object, "startPosition", startPosition);
        EntityHelper.setInt(object, "size", size);
        EntityHelper.setString(object, "dataType", dataType);
        EntityHelper.setString(object, "format", format);
        EntityHelper.setString(object, "description", description);
        EntityHelper.setFloat(object, "scalingFactor", scalingFactor);
        EntityHelper.setFloat(object, "offset", offset);
        EntityHelper.setInt(object, "items", items);
        EntityHelper.setInt(object, "itemBytes", itemBytes);
        EntityHelper.setInt(object, "itemOffset", itemOffset);
        EntityHelper.setBoolean(object, "bitColumn", bitColumn);
        EntityHelper.setArray(object, "otherProperties", otherProperties);
        EntityHelper.setArray(object, "otherChildren", otherChildren);
        return object;
    }
}
