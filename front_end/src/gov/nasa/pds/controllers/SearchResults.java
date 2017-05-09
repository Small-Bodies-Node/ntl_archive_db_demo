package gov.nasa.pds.controllers;

public class SearchResults {
    private NavigationView targetTypes;
    private NavigationView targets;
    private NavigationView missions;
    private NavigationView instruments;
    private NavigationView dataSets;
    private NavigationView documents;

    /**
     * Creates SearchResults instance.
     */
    public SearchResults() {
        // Empty
    }

    // targetTypes property
    public NavigationView getTargetTypes() {
        return targetTypes;
    }
    public void setTargetTypes(NavigationView targetTypes) {
        this.targetTypes = targetTypes;
    }

    // targets property
    public NavigationView getTargets() {
        return targets;
    }
    public void setTargets(NavigationView targets) {
        this.targets = targets;
    }

    // missions property
    public NavigationView getMissions() {
        return missions;
    }
    public void setMissions(NavigationView missions) {
        this.missions = missions;
    }

    // instruments property
    public NavigationView getInstruments() {
        return instruments;
    }
    public void setInstruments(NavigationView instruments) {
        this.instruments = instruments;
    }

    // dataSets property
    public NavigationView getDataSets() {
        return dataSets;
    }
    public void setDataSets(NavigationView dataSets) {
        this.dataSets = dataSets;
    }

    // documents property
    public NavigationView getDocuments() {
        return documents;
    }
    public void setDocuments(NavigationView documents) {
        this.documents = documents;
    }
}
