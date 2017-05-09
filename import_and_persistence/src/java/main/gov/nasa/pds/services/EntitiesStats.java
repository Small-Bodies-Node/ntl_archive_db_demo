/*
 * Copyright (C) 2012 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services;

public class EntitiesStats {
    private int targetsCount;
    private int missionsCount;
    private int instrumentsCount;
    private int dataSetsCount;
    private long documentsCount;

    public EntitiesStats() {
        // Empty
    }

    // targetsCount property
    public int getTargetsCount() {
        return targetsCount;
    }
    public void setTargetsCount(int targetsCount) {
        this.targetsCount = targetsCount;
    }

    // missionsCount property
    public int getMissionsCount() {
        return missionsCount;
    }
    public void setMissionsCount(int missionsCount) {
        this.missionsCount = missionsCount;
    }

    // instrumentsCount property
    public int getInstrumentsCount() {
        return instrumentsCount;
    }
    public void setInstrumentsCount(int instrumentsCount) {
        this.instrumentsCount = instrumentsCount;
    }

    // dataSetsCount property
    public int getDataSetsCount() {
        return dataSetsCount;
    }
    public void setDataSetsCount(int dataSetsCount) {
        this.dataSetsCount = dataSetsCount;
    }

    // documentsCount property
    public long getDocumentsCount() {
        return documentsCount;
    }
    public void setDocumentsCount(long documentsCount) {
        this.documentsCount = documentsCount;
    }
}
