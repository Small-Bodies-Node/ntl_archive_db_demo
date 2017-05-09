/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl.profile.cassini;

import java.util.List;

/**
 * The {@code CassiniObservation} class represents an observation information for a single product. It stores the list
 * of targets inside the instrument field of view for the given product.
 * 
 * @author KennyAlive
 * @version 1.0
 */
public class CassiniObservation {
    /**
     * The product id (in the supplemental tables it's denoted by the label filepath).
     */
    private long productId;

    /**
     * The ring observation id.
     */
    private String ringObservationId;

    /**
     * The list of targets.
     */
    private List<String> targets;

    /**
     * Gets the productId.
     * 
     * @return the productId
     */
    public long getProductId() {
        return productId;
    }

    /**
     * Sets the productId.
     * 
     * @param productId
     *            the productId to set
     */
    public void setProductId(long productId) {
        this.productId = productId;
    }

    /**
     * Gets the ringObservationId.
     * 
     * @return the ringObservationId
     */
    public String getRingObservationId() {
        return ringObservationId;
    }

    /**
     * Sets the ringObservationId.
     * 
     * @param ringObservationId
     *            the ringObservationId to set
     */
    public void setRingObservationId(String ringObservationId) {
        this.ringObservationId = ringObservationId;
    }

    /**
     * Gets the targets.
     * 
     * @return the targets
     */
    public List<String> getTargets() {
        return targets;
    }

    /**
     * Sets the targets.
     * 
     * @param targets
     *            the targets to set
     */
    public void setTargets(List<String> targets) {
        this.targets = targets;
    }
}
