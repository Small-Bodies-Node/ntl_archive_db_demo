/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl.profile.cassini;

import java.util.List;

/**
 * <p>
 * The {@code CassiniInstrument} class provides information associated with the Cassini instrument.
 * </p>
 * 
 * <p>
 * NOTE: at the moment this class provides supplementary data tables associated with the instrument. But any other
 * instrument related information could be added here if it is required by {@code CassiniProfile} implementation.
 * </p>
 * 
 * @author KennyAlive
 * @version 1.0
 */
class CassiniInstrument {
    /**
     * Name of this instrument as specified in instruments list file.
     */
    private String name;

    /**
     * The table types configured for this instrument.
     */
    private List<CassiniTableType> tableTypes;

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     * 
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the tableTypes.
     * 
     * @return the tableTypes
     */
    public List<CassiniTableType> getTableTypes() {
        return tableTypes;
    }

    /**
     * Sets the tableTypes.
     * 
     * @param tableTypes
     *            the tableTypes to set
     */
    public void setTableTypes(List<CassiniTableType> tableTypes) {
        this.tableTypes = tableTypes;
    }
}
