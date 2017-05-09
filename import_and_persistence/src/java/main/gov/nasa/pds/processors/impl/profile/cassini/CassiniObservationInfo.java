/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl.profile.cassini;

import java.util.Date;
import java.util.List;

/**
 * The {@code CassiniObservationInfo} class represents the list of observations associated with the given Cassini
 * instrument. Each observation is associated with a single product (like image) and specifies that list of targets
 * that were observed.
 * 
 * @author KennyAlive
 * @version 1.0
 */
public class CassiniObservationInfo {
    /**
     * The Cassini instrument name (as specified in instruments list configuration).
     */
    private String cassiniInstrumentName;

    /**
     * The product creation time (as specified in inventory label).
     */
    private Date productCreationTime;

    /**
     * The instrument host name (as specified in inventory label).
     */
    private String instrumentHostName;

    /**
     * The instrument host id (as specified in inventory label).
     */
    private String instrumentHostId;

    /**
     * The instrument name (as specified in inventory label).
     */
    private String instrumentName;

    /**
     * The instrument id (as specified in inventory label).
     */
    private String instrumentId;

    /**
     * The list of observations.
     */
    private List<CassiniObservation> observations;

    /**
     * Gets the cassiniInstrumentName.
     * 
     * @return the cassiniInstrumentName
     */
    public String getCassiniInstrumentName() {
        return cassiniInstrumentName;
    }

    /**
     * Sets the cassiniInstrumentName.
     * 
     * @param cassiniInstrumentName
     *            the cassiniInstrumentName to set
     */
    public void setCassiniInstrumentName(String cassiniInstrumentName) {
        this.cassiniInstrumentName = cassiniInstrumentName;
    }

    /**
     * Gets the productCreationTime.
     * 
     * @return the productCreationTime
     */
    public Date getProductCreationTime() {
        return productCreationTime;
    }

    /**
     * Sets the productCreationTime.
     * 
     * @param productCreationTime
     *            the productCreationTime to set
     */
    public void setProductCreationTime(Date productCreationTime) {
        this.productCreationTime = productCreationTime;
    }

    /**
     * Gets the instrumentHostName.
     * 
     * @return the instrumentHostName
     */
    public String getInstrumentHostName() {
        return instrumentHostName;
    }

    /**
     * Sets the instrumentHostName.
     * 
     * @param instrumentHostName
     *            the instrumentHostName to set
     */
    public void setInstrumentHostName(String instrumentHostName) {
        this.instrumentHostName = instrumentHostName;
    }

    /**
     * Gets the instrumentHostId.
     * 
     * @return the instrumentHostId
     */
    public String getInstrumentHostId() {
        return instrumentHostId;
    }

    /**
     * Sets the instrumentHostId.
     * 
     * @param instrumentHostId
     *            the instrumentHostId to set
     */
    public void setInstrumentHostId(String instrumentHostId) {
        this.instrumentHostId = instrumentHostId;
    }

    /**
     * Gets the instrumentName.
     * 
     * @return the instrumentName
     */
    public String getInstrumentName() {
        return instrumentName;
    }

    /**
     * Sets the instrumentName.
     * 
     * @param instrumentName
     *            the instrumentName to set
     */
    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    /**
     * Gets the instrumentId.
     * 
     * @return the instrumentId
     */
    public String getInstrumentId() {
        return instrumentId;
    }

    /**
     * Sets the instrumentId.
     * 
     * @param instrumentId
     *            the instrumentId to set
     */
    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    /**
     * Gets the observations.
     * 
     * @return the observations
     */
    public List<CassiniObservation> getObservations() {
        return observations;
    }

    /**
     * Sets the observations.
     * 
     * @param observations
     *            the observations to set
     */
    public void setObservations(List<CassiniObservation> observations) {
        this.observations = observations;
    }
}
