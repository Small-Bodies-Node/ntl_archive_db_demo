/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import java.util.List;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents the result of the validation request.
 * 
 * Thread Safety: This class is mutable and not thread safe.
 */
public class ValidationReport implements Loggable {
    /**
     * Represents the flag whether the validation was successful or not. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     */
    private boolean valid;
    /**
     * Represents the list of errors encountered during the validation. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     */
    private List<ErrorItem> errors;

    /**
     * Empty constructor.
     */
    public ValidationReport() {
    }

    /**
     * Gets the valid value.
     * 
     * @return - the valid value.
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Sets the given value to valid.
     * 
     * @param valid
     *            - the given value to set.
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    /**
     * Gets the errors value.
     * 
     * @return - the errors value.
     */
    public List<ErrorItem> getErrors() {
        return errors;
    }

    /**
     * Sets the given value to errors.
     * 
     * @param errors
     *            - the given value to set.
     */
    public void setErrors(List<ErrorItem> errors) {
        this.errors = errors;
    }

    /**
     * Gets the JSONObject instance.
     * 
     * @return - the JSONObject for this instance.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        EntityHelper.setBoolean(object, "valid", valid);

        EntityHelper.setArray(object, "errors", errors);
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
