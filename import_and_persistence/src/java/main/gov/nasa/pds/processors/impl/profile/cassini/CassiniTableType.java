/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl.profile.cassini;

import java.util.List;

/**
 * <p>
 * The {@code CassiniTableType} class specifies the type of the Cassini supplemental data table, like inventory table,
 * moon table or some other table type that can be added in the future.
 * </p>
 * 
 * <p>
 * Each table type can have the associated list of semantics values. This allows to associate some semantics with the
 * table and to categorize tables. The semantics values could be either a general string values with no special
 * meaning for the code or the code can check for some predefined values if the table with particular semantics
 * requires additional processing.
 * </p>
 * 
 * @author KennyAlive
 * @version 1.0
 */
class CassiniTableType {
    /**
     * The table type name (example: moonTable).
     */
    private String name;

    /**
     * The list of semantics values associated with the table of the given type.
     */
    private List<String> semantics;

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
     * Gets the semantics.
     * 
     * @return the semantics
     */
    public List<String> getSemantics() {
        return semantics;
    }

    /**
     * Sets the semantics.
     * 
     * @param semantics
     *            the semantics to set
     */
    public void setSemantics(List<String> semantics) {
        this.semantics = semantics;
    }
}
