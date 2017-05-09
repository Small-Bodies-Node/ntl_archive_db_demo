/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import java.util.List;
import java.util.Map;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents host of the instrument.
 * 
 * Thread Safety: This class is mutable and not thread safe.
 */
public class InstrumentHost extends NamedEntity implements MetaDataObjectConsumer {
    /**
     * Constant for "INSTRUMENT_HOST_ID" String.
     */
    private static final String INSTRUMENT_HOST_ID = "INSTRUMENT_HOST_ID";
    /**
     * Constant for "INSTRUMENT_HOST_REFERENCE_INFO" String.
     */
    private static final String INSTRUMENT_HOST_REFERENCE_INFO = "INSTRUMENT_HOST_REFERENCE_INFO";
    /**
     * Constant for "INSTRUMENT_HOST_INFORMATION" String.
     */
    private static final String INSTRUMENT_HOST_INFORMATION = "INSTRUMENT_HOST_INFORMATION";
    /**
     * Constant for "INSTRUMENT_HOST_NAME" String.
     */
    private static final String INSTRUMENT_HOST_NAME = "INSTRUMENT_HOST_NAME";
    /**
     * Represents the primary ID defined in the original file. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     */
    private String textId;
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
    public InstrumentHost() {
    }

    /**
     * Constructor. Create the instance with name.
     * 
     * @param name
     *            - the given name to set.
     */
    public InstrumentHost(String name) {
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
        Map<String, Property> map = EntityHelper.getProperties(properties, new String[] { INSTRUMENT_HOST_ID });

        this.textId = EntityHelper.getPropertyStringValue(map.get(INSTRUMENT_HOST_ID));

        references = EntityHelper.createReferencesList(INSTRUMENT_HOST_REFERENCE_INFO, children);

        MetadataObject instrumentHostInfo = EntityHelper.findMetadataObject(INSTRUMENT_HOST_INFORMATION, children);
        if (instrumentHostInfo != null) {
            Map<String, Property> instrumentHostInfoMap = EntityHelper.getProperties(
                    instrumentHostInfo.getProperties(), new String[] { INSTRUMENT_HOST_NAME });
            setName(EntityHelper.getPropertyStringValue(instrumentHostInfoMap.get(INSTRUMENT_HOST_NAME)));
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
        EntityHelper.setString(object, "textId", textId);

        EntityHelper.setArray(object, "references", references);

        EntityHelper.setArray(object, "otherChildren", otherChildren);

        return object;
    }
}
