package gov.nasa.pds.controllers;

public class NavigationState {
    private long targetTypeId;
    private long targetId;
    private long missionId;
    private long instrumentId;
    private long dataSetId;
    private long documentId;

    private int pageNumber;
    private int itemsPerPage;

    public NavigationState() {
        // Empty
    }

    // targetTypeId property
    public long getTargetTypeId() {
        return targetTypeId;
    }
    public void setTargetTypeId(long targetTypeId) {
        this.targetTypeId = targetTypeId;
    }

    // targetId property
    public long getTargetId() {
        return targetId;
    }
    public void setTargetId(long targetId) {
        this.targetId = targetId;
    }

    // missionId property
    public long getMissionId() {
        return missionId;
    }
    public void setMissionId(long missionId) {
        this.missionId = missionId;
    }

    // instrumentId property
    public long getInstrumentId() {
        return instrumentId;
    }
    public void setInstrumentId(long instrumentId) {
        this.instrumentId = instrumentId;
    }

    // dataSetId property
    public long getDataSetId() {
        return dataSetId;
    }
    public void setDataSetId(long dataSetId) {
        this.dataSetId = dataSetId;
    }

    // documentId property
    public long getDocumentId() {
        return documentId;
    }
    public void setDocumentId(long documentId) {
        this.documentId = documentId;
    }

    // pageNumber property
    public int getPageNumber() {
        return pageNumber;
    }
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    // itemsPerPage property
    public int getItemsPerPage() {
        return itemsPerPage;
    }
    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }
}
