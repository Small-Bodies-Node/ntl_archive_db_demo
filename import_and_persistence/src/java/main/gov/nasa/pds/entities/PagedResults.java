/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import java.util.List;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents paged results.
 *
 * Thread Safety: This class is mutable and not thread safe.
 */
public class PagedResults<T> implements Loggable {
    /**
     * Represents the total count of items in all pages. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     */
    private long total;
    /**
     * Represents the list of results for the requested page. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     */
    private List<T> results;

    /**
     * Empty constructor.
     */
    public PagedResults() {
    }

    /**
     * Gets the total value.
     *
     * @return - the total value.
     */
    public long getTotal() {
        return total;
    }

    /**
     * Sets the given value to total.
     *
     * @param total
     *            - the given value to set.
     */
    public void setTotal(long total) {
        this.total = total;
    }

    /**
     * Gets the results value.
     *
     * @return - the results value.
     */
    public List<T> getResults() {
        return results;
    }

    /**
     * Sets the given value to results.
     *
     * @param results
     *            - the given value to set.
     */
    public void setResults(List<T> results) {
        this.results = results;
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

    /**
     * Gets the JSONObject instance.
     *
     * @return - the JSONObject for this instance.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        EntityHelper.setLong(object, "total", total);
        EntityHelper.setArray(object, "results", results);
        return object;
    }
}
