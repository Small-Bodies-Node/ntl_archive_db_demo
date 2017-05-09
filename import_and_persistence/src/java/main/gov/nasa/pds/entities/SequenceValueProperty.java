/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import java.util.List;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents a property with a sequence of sets of values.
 * 
 * Thread Safety: This class is mutable and not thread safe.
 */
public class SequenceValueProperty extends Property {
    /**
     * Represents the sequence of sets of values of this property. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     */
    private List<List<String>> sequences;

    /**
     * Empty constructor.
     */
    public SequenceValueProperty() {
    }

    /**
     * Constructor. Create the instance with name.
     * 
     * @param name
     *            - the given name to set.
     */
    public SequenceValueProperty(String name) {
        super(name);

    }

    /**
     * Gets the sequences value.
     * 
     * @return - the sequences value.
     */
    public List<List<String>> getSequences() {
        return sequences;
    }

    /**
     * Sets the given value to sequences.
     * 
     * @param sequences
     *            - the given value to set.
     */
    public void setSequences(List<List<String>> sequences) {
        this.sequences = sequences;
    }

    /**
     * Gets the JSONObject instance.
     * 
     * @return - the JSONObject for this instance.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = super.toJSONObject();

        EntityHelper.setArray(object, "sequences", sequences);
        return object;
    }
}
