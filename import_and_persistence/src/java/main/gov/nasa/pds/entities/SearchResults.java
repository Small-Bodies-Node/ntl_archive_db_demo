package gov.nasa.pds.entities;

public class SearchResults {
    private PagedResults<EntityInfo> targetTypes;
    private PagedResults<EntityInfo> targets;
    private PagedResults<EntityInfo> missions;
    private PagedResults<EntityInfo> instruments;
    private PagedResults<EntityInfo> datasets;
    private PagedResults<EntityInfo> dataFiles;

    /**
     * @return the targetTypes
     */
    public PagedResults<EntityInfo> getTargetTypes() {
        return targetTypes;
    }
    /**
     * @param targetTypes the targetTypes to set
     */
    public void setTargetTypes(PagedResults<EntityInfo> targetTypes) {
        this.targetTypes = targetTypes;
    }
    /**
     * @return the targets
     */
    public PagedResults<EntityInfo> getTargets() {
        return targets;
    }
    /**
     * @param targets the targets to set
     */
    public void setTargets(PagedResults<EntityInfo> targets) {
        this.targets = targets;
    }
    /**
     * @return the missions
     */
    public PagedResults<EntityInfo> getMissions() {
        return missions;
    }
    /**
     * @param missions the missions to set
     */
    public void setMissions(PagedResults<EntityInfo> missions) {
        this.missions = missions;
    }
    /**
     * @return the instruments
     */
    public PagedResults<EntityInfo> getInstruments() {
        return instruments;
    }
    /**
     * @param instruments the instruments to set
     */
    public void setInstruments(PagedResults<EntityInfo> instruments) {
        this.instruments = instruments;
    }
    /**
     * @return the datasets
     */
    public PagedResults<EntityInfo> getDatasets() {
        return datasets;
    }
    /**
     * @param datasets the datasets to set
     */
    public void setDatasets(PagedResults<EntityInfo> datasets) {
        this.datasets = datasets;
    }
    /**
     * @return the dataFiles
     */
    public PagedResults<EntityInfo> getDataFiles() {
        return dataFiles;
    }
    /**
     * @param dataFiles the dataFiles to set
     */
    public void setDataFiles(PagedResults<EntityInfo> dataFiles) {
        this.dataFiles = dataFiles;
    }
}
