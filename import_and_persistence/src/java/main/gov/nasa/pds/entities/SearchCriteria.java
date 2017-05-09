/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import java.util.Date;
import java.util.List;

import com.topcoder.json.object.JSONObject;

/**
 * This class represents the search criteria for locating data sets.
 *
 * Thread Safety: This class is mutable and not thread safe.
 */
public class SearchCriteria {
    /**
     * Set data set id.
     */
    private long dataSetId;
    /**
     * Represents the targetTypes. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private List<String> targetTypes;
    /**
     * Represents the targets. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private List<String> targets;
    /**
     * Represents the missions. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private List<String> missions;
    /**
     * Represents the It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private List<String> instruments;
    /**
     * Represents the instrumentHosts. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     */
    private List<String> instrumentHosts;
    /**
     * Represents the startDate. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private Date startDate;
    /**
     * Represents the stopDate. It is managed with a getter and setter. It may have any value. It is fully mutable.
     */
    private Date stopDate;


    /* NOTE: do not use this search parameters yet.
    private boolean whiteSpots;
    private Float lowerDataRating;
    private Float upperDataRating;
    */

    /**
     * Empty constructor
     */
    public SearchCriteria() {
    }

    // dataSetId property
    public long getDataSetId() {
        return dataSetId;
    }
    public void setDataSetId(long dataSetId) {
        this.dataSetId = dataSetId;
    }

    /**
     * Gets the targetTypes value.
     *
     * @return - the targetTypes value.
     */
    public List<String> getTargetTypes() {
        return targetTypes;
    }

    /**
     * Sets the given value to targetTypes.
     *
     * @param targetTypes
     *            - the given value to set.
     */
    public void setTargetTypes(List<String> targetTypes) {
        this.targetTypes = targetTypes;
    }

    /**
     * Gets the targets value.
     *
     * @return - the targets value.
     */
    public List<String> getTargets() {
        return targets;
    }

    /**
     * Sets the given value to targets.
     *
     * @param targets
     *            - the given value to set.
     */
    public void setTargets(List<String> targets) {
        this.targets = targets;
    }

    /**
     * Gets the missions value.
     *
     * @return - the missions value.
     */
    public List<String> getMissions() {
        return missions;
    }

    /**
     * Sets the given value to missions.
     *
     * @param missions
     *            - the given value to set.
     */
    public void setMissions(List<String> missions) {
        this.missions = missions;
    }

    /**
     * Gets the instruments value.
     *
     * @return - the instruments value.
     */
    public List<String> getInstruments() {
        return instruments;
    }

    /**
     * Sets the given value to instruments.
     *
     * @param instruments
     *            - the given value to set.
     */
    public void setInstruments(List<String> instruments) {
        this.instruments = instruments;
    }

    /**
     * Gets the startDate value.
     *
     * @return - the startDate value.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the given value to startDate.
     *
     * @param startDate
     *            - the given value to set.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the stopDate value.
     *
     * @return - the stopDate value.
     */
    public Date getStopDate() {
        return stopDate;
    }

    /**
     * Sets the given value to stopDate.
     *
     * @param stopDate
     *            - the given value to set.
     */
    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    /**
     * Gets the instrumentHosts value.
     *
     * @return - the instrumentHosts value.
     */
    public List<String> getInstrumentHosts() {
        return instrumentHosts;
    }

    /**
     * Sets the given value to instrumentHosts.
     *
     * @param instrumentHosts
     *            - the given value to set.
     */
    public void setInstrumentHosts(List<String> instrumentHosts) {
        this.instrumentHosts = instrumentHosts;
    }

/*
    public boolean isWhiteSpots() {
        return whiteSpots;
    }
    public void setWhiteSpots(boolean whiteSpots) {
        this.whiteSpots = whiteSpots;
    }

    public Float getLowerDataRating() {
        return lowerDataRating;
    }
    public void setLowerDataRating(Float lowerDataRating) {
        this.lowerDataRating = lowerDataRating;
    }

    public Float getUpperDataRating() {
        return upperDataRating;
    }
    public void setUpperDataRating(Float upperDataRating) {
        this.upperDataRating = upperDataRating;
    }
*/

    /**
     * Gets the JSONObject instance.
     *
     * @return - the JSONObject for this instance.
     */
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        EntityHelper.setLong(object, "dataSetId", dataSetId);
        EntityHelper.setArray(object, "targetTypes", targetTypes);
        EntityHelper.setArray(object, "targets", targets);
        EntityHelper.setArray(object, "missions", missions);
        EntityHelper.setArray(object, "instruments", instruments);
        EntityHelper.setArray(object, "instrumentHosts", instrumentHosts);

        EntityHelper.setString(object, "startDate", EntityHelper.convertDateToString(startDate));
        EntityHelper.setString(object, "stopDate", EntityHelper.convertDateToString(stopDate));

        /*
        EntityHelper.setBoolean(object, "whiteSpots", whiteSpots);
        EntityHelper.setFloat(object, "lowerDataRating", lowerDataRating);
        EntityHelper.setFloat(object, "upperDataRating", upperDataRating);
        */
        return object;
    }

    public String toJSONString() {
        return toJSONObject().toJSONString();
    }
}
