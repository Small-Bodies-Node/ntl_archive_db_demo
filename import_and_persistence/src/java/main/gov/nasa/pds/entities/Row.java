/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import java.util.List;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents a row of data.
 * 
 * Thread Safety: This class is mutable and not thread safe.
 */
public class Row extends IdentifiableEntity {
    /**
     * Represents the cells. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private List<Cell> cells;

    /**
     * Empty constructor.
     */
    public Row() {
    }

    /**
     * Gets the cells.
     * 
     * @return - the cells.
     */
    public List<Cell> getCells() {
        return cells;
    }

    /**
     * Sets the given cells.
     * 
     * @param cells
     *            - the given cells to set.
     */
    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    /**
     * Gets the JSONObject instance.
     * 
     * @return - the JSONObject for this instance.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = super.toJSONObject();
        EntityHelper.setArray(object, "cells", cells);
        return object;
    }
}
