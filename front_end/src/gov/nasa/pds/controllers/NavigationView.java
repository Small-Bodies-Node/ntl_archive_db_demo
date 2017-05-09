package gov.nasa.pds.controllers;

import java.util.List;

import gov.nasa.pds.services.EntitiesStats;
import gov.nasa.pds.entities.EntityInfo;

public class NavigationView {
    /**
     * View's primary entities. For example for targets view it's the list of target entities,
     * for mission view it's the list of mission entities and so on.
     */
    private List<EntityInfo> entities;

    /**
     * Info about entity's dependent entities. For example, for dataset dependent entity is document,
     * for mission dependent entities are instrument, dataset and document. The size of this list is the same as
     * of 'entities' list.
     */
    private List<EntitiesStats> childrenStats;

    /**
     * The total number of entities. The current view shows only subset from all available entities.
     */
    private long totalEntities;

    /**
     * Creates NavigationView instance.
     */
    public NavigationView() {
        // Empty
    }

    // entities property
    public List<EntityInfo> getEntities() {
        return entities;
    }
    public void setEntities(List<EntityInfo> entities) {
        this.entities = entities;
    }

    // childrenStats property
    public List<EntitiesStats> getChildrenStats() {
        return childrenStats;
    }
    public void setChildrenStats(List<EntitiesStats> childrenStats) {
        this.childrenStats = childrenStats;
    }

    // totalEntities property
    public long getTotalEntities() {
        return totalEntities;
    }
    public void setTotalEntities(long totalEntities) {
        this.totalEntities = totalEntities;
    }
}
