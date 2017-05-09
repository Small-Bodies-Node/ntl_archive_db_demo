package gov.nasa.pds.ws;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.zip.Deflater;

import gov.nasa.pds.entities.DataFile;
import gov.nasa.pds.entities.DataSet;
import gov.nasa.pds.entities.EntityInfo;
import gov.nasa.pds.entities.Instrument;
import gov.nasa.pds.entities.Mission;
import gov.nasa.pds.entities.Page;
import gov.nasa.pds.entities.PagedResults;
import gov.nasa.pds.entities.Restriction;
import gov.nasa.pds.entities.SearchCriteria;
import gov.nasa.pds.entities.SearchResults;
import gov.nasa.pds.entities.Target;
import gov.nasa.pds.entities.TargetType;
import gov.nasa.pds.services.DataSetProcessingException;
import gov.nasa.pds.services.DataSetService;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.jws.WebService;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(targetNamespace = "http://pds.nasa.gov/",
            endpointInterface = "gov.nasa.pds.ws.PlanetaryDataSystemSEI",
            portName = "PlanetaryDataSystemPort",
            serviceName = "PlanetaryDataSystemService")
public class PlanetaryDataSystem implements PlanetaryDataSystemSEI {
    /**
     * The DataSetService instance initialized with Spring setter dependency injection.
     */
    private DataSetService dataSetService;

    /**
     * Sets the DataSetService instance.
     *
     * @param dataSetService
     *              the data set service instance to set
     */
    @Autowired
    public void setDataSetService(DataSetService dataSetService) {
        this.dataSetService = dataSetService;
    }

    public PagedResults<EntityInfo> getTargetTypesInfo(Page page) throws DataSetProcessingException {
        return dataSetService.getTargetTypesInfo(page);
    }
    public TargetType getTargetType(long targetTypeId) throws DataSetProcessingException {
        return dataSetService.getTargetType(targetTypeId);
    }


    public PagedResults<EntityInfo> getTargetsInfo(Page page, Restriction restriction)
        throws DataSetProcessingException {
        return dataSetService.getTargetsInfo(page, restriction);
    }
    public Target getTarget(long targetId) throws DataSetProcessingException {
        return dataSetService.getTarget(targetId);
    }


    public PagedResults<EntityInfo> getMissionsInfo(Page page, Restriction restriction)
        throws DataSetProcessingException {
        return dataSetService.getMissionsInfo(page, restriction);
    }
    public Mission getMission(long missionId) throws DataSetProcessingException {
        return dataSetService.getMission(missionId);
    }


    public PagedResults<EntityInfo> getInstrumentsInfo(Page page, Restriction restriction)
        throws DataSetProcessingException {
        return dataSetService.getInstrumentsInfo(page, restriction);
    }
    public Instrument getInstrument(long instrumentId) throws DataSetProcessingException {
        return dataSetService.getInstrument(instrumentId);
    }


    public PagedResults<EntityInfo> getDataSetsInfo(Page page, Restriction restriction)
        throws DataSetProcessingException {
        return dataSetService.getDataSetsInfo(page, restriction);
    }
    public DataSet getDataSet(long dataSetId) throws DataSetProcessingException {
        return dataSetService.getDataSet(dataSetId);
    }
    public List<EntityInfo> getDataSetRelatedEntitites(long dataSetId, Class<?> entityClass)
            throws DataSetProcessingException {
        return dataSetService.getDataSetRelatedEntitites(dataSetId, entityClass);
    }


    public PagedResults<EntityInfo> getDocumentsInfo(Page page, Restriction restriction)
        throws DataSetProcessingException {
        return dataSetService.getDataFilesInfo(page, restriction);
    }
    public PagedResults<EntityInfo> getImagesInfo(Page page, Restriction restriction)
        throws DataSetProcessingException {
        return dataSetService.getImagesInfo(page, restriction);
    }
    public WSDataFile getDataFile(long dataFileId) throws DataSetProcessingException {
        DataFile dataFile = dataSetService.getDataFile(dataFileId);

        WSDataFile wsDataFile = new WSDataFile();
        wsDataFile.setId(dataFile.getId());
        wsDataFile.setName(dataFile.getName());
        wsDataFile.setContent(dataFile.getContent());
        if (dataFile.getPath() != null) {
            wsDataFile.setFilename(new File(dataFile.getPath()).getName());

            // a. without compression
            // DataSource source = new FileDataSource(new File(dataFile.getPath()));

            // b. with compression
            byte[] input = null;
            try {
                input = IOUtils.toByteArray(new FileInputStream(dataFile.getPath()));
            } catch (FileNotFoundException e) {
                throw new DataSetProcessingException("Failed to get the file", e);
            } catch (IOException e) {
                throw new DataSetProcessingException("Failed to get the file", e);
            }
            Deflater compressor = new Deflater();
            compressor.setLevel(Deflater.BEST_COMPRESSION);
            compressor.setInput(input);
            compressor.finish();
            ByteArrayOutputStream bos = new ByteArrayOutputStream(input.length);
            byte[] buf = new byte[1024];
            while (!compressor.finished()) {
                int count = compressor.deflate(buf);
                bos.write(buf, 0, count);
            }
            try {
                bos.close();
            } catch (IOException e) {
                // Ignore
            }
            byte[] compressedData = bos.toByteArray();
            DataSource source = new ByteArrayDataSource(compressedData, "content/type");

            wsDataFile.setDataHandler(new DataHandler(source));
        }
        return wsDataFile;
    }
    public String getPreviewImageURL(long imageFileId) throws DataSetProcessingException {
        return dataSetService.getPreviewImageURL(imageFileId);
    }


    public SearchResults searchEntities(String searchText, Page page) throws DataSetProcessingException {
        return dataSetService.searchEntities(searchText, page);
    }
    public PagedResults<EntityInfo> searchEntitiesByType(Class<?> entityClass, String searchText, Page page,
        Restriction restriction) throws DataSetProcessingException {
        return dataSetService.searchEntitiesByType(entityClass, searchText, page, restriction);
    }
    public PagedResults<EntityInfo> searchDataSetsByCriteria(SearchCriteria criteria, Page page)
        throws DataSetProcessingException {
        return dataSetService.searchDataSetsByCriteria(criteria, page);
    }
}
