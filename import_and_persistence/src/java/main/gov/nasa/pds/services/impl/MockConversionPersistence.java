/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services.impl;

import gov.nasa.pds.entities.DataFile;
import gov.nasa.pds.entities.DataSet;
import gov.nasa.pds.entities.Instrument;
import gov.nasa.pds.entities.InstrumentHost;
import gov.nasa.pds.entities.Mission;
import gov.nasa.pds.entities.Product;
import gov.nasa.pds.entities.Reference;
import gov.nasa.pds.entities.Table;
import gov.nasa.pds.entities.Target;
import gov.nasa.pds.entities.Volume;
import gov.nasa.pds.processors.impl.profile.cassini.CassiniObservationInfo;
import gov.nasa.pds.services.ConversionPersistence;
import gov.nasa.pds.services.DataSetProcessingException;

import java.util.List;

/**
 * <p>
 * The <code>MockConversionPersistence</code> class is a mock implementation of
 * <code>ConversionPersistence</code> interface and is used for testing.
 * </p>
 *
 * <strong>Thread Safety:</strong> This class is immutable and thread safe.
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
public class MockConversionPersistence implements ConversionPersistence {
    @Override
    public void clearCaches() {
    }

    @Override
    public ConversionPersistence.TableInfo createTable(List<String> columnNames, List<Integer> columnSizes)
            throws DataSetProcessingException {
        return new ConversionPersistence.TableInfo("Test_table", 1L);
    }

    @Override
    public void dropTable(String tableName) throws DataSetProcessingException {
    }

    @Override
    public long insertDataIntoTable(long dataTableId, Table table) throws DataSetProcessingException {
        return 0;
    }

    @Override
    public void insertDataIntoRegularTable(Table table) throws DataSetProcessingException {
    }

    @Override
    public long insertVolume(Volume volume) throws DataSetProcessingException {
        return 0;
    }

    @Override
    public long insertReference(Reference reference) throws DataSetProcessingException {
        return 0;
    }

    @Override
    public long insertInstrumentHost(InstrumentHost instrumentHost) throws DataSetProcessingException {
        return 0;
    }

    @Override
    public long insertInstrument(Instrument instrument) throws DataSetProcessingException {
        return 0;
    }

    @Override
    public long insertMission(Mission mission) throws DataSetProcessingException {
        return 0;
    }

    @Override
    public long insertTarget(Target target) throws DataSetProcessingException {
        return 0;
    }

    @Override
    public long insertDataSet(DataSet dataSet) throws DataSetProcessingException {
        return 0;
    }

    @Override
    public long insertProduct(long dataSetId, Product product) throws DataSetProcessingException {
        return 0;
    }

    @Override
    public void insertProductDocument(long productId, DataFile dataFile) throws DataSetProcessingException {
    }

    @Override
    public long insertDataSetDocument(long dataSetId, DataFile dataFile) throws DataSetProcessingException {
        return 0;
    }

    @Override
    public void associateTableToProduct(long productId, long dataTableId) throws DataSetProcessingException {
    }

    @Override
    public void associateTableToDataSet(long dataSetId, long dataTableId) throws DataSetProcessingException {
    }

    @Override
    public void insertCassiniObservationInfo(CassiniObservationInfo observationInfo) throws DataSetProcessingException {
    }
}
