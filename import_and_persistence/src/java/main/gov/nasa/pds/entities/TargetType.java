/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import java.util.List;
import java.util.Map;

/**
 * This class represents the type of target.
 * 
 * Thread Safety: This class is mutable and not thread safe.
 */
public class TargetType extends NamedEntity implements MetaDataObjectConsumer {
    /**
     * Constant for "TARGET_TYPE_NAME" String.
     */
    private static final String TARGET_TYPE = "TARGET_TYPE";

    /**
     * Empty constructor.
     */
    public TargetType() {
    }

    /**
     * Constructor. Create the instance with name.
     * 
     * @param name
     *            - the given name to set.
     */
    public TargetType(String name) {
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
        Map<String, Property> map = EntityHelper.getProperties(properties, new String[] { TARGET_TYPE });

        setName(EntityHelper.getPropertyStringValue(map.get(TARGET_TYPE)));

    }
}
