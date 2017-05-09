/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import java.util.List;
import java.util.Map;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents a target.
 * 
 * Thread Safety: This class is mutable and not thread safe.
 */
public class Target extends NamedEntity implements MetaDataObjectConsumer {
    /**
     * Constant for "TARGET_NAME" String.
     */
    private static final String TARGET_NAME = "TARGET_NAME";
    /**
     * Constant for "TARGET_INFORMATION" String.
     */
    private static final String TARGET_INFORMATION = "TARGET_INFORMATION";
    /**
     * Constant for "TARGET_REFERENCE_INFORMATION" String.
     */
    private static final String TARGET_REFERENCE_INFORMATION = "TARGET_REFERENCE_INFORMATION";
    /**
     * Represents the types. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private List<TargetType> types;

    /**
     * Represents the references. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private List<Reference> references;

    /**
     * Empty constructor
     */
    public Target() {
    }

    /**
     * Constructor. Create the instance with name.
     * 
     * @param name
     *            - the given name to set.
     */
    public Target(String name) {
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
        Map<String, Property> map = EntityHelper.getProperties(properties, new String[] { TARGET_NAME });

        setName(EntityHelper.getPropertyStringValue(map.get(TARGET_NAME)));

        references = EntityHelper.createReferencesList(TARGET_REFERENCE_INFORMATION, children);

        types = EntityHelper.createTargetTypesList(TARGET_INFORMATION, children);
    }

    /**
     * Gets the types value.
     * 
     * @return - the types value.
     */
    public List<TargetType> getTypes() {
        return types;
    }

    /**
     * Sets the given value to types.
     * 
     * @param types
     *            - the given value to set.
     */
    public void setTypes(List<TargetType> types) {
        this.types = types;
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
     * Gets the JSONObject instance.
     * 
     * @return - the JSONObject for this instance.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = super.toJSONObject();
        EntityHelper.setArray(object, "types", types);
        EntityHelper.setArray(object, "references", references);
        return object;
    }
}
