package gov.nasa.pds.ws;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import gov.nasa.pds.entities.ClassXmlAdapter;
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

@WebService(name = "PlanetaryDataSystemSEI",
            targetNamespace = "http://pds.nasa.gov/")
public interface PlanetaryDataSystemSEI {
    // Target Type
    PagedResults<EntityInfo> getTargetTypesInfo(
            @WebParam(name="page") Page page)
                    throws DataSetProcessingException;

    TargetType getTargetType(
            @WebParam(name="id") long targetTypeId)
                    throws DataSetProcessingException;

    // Target
    PagedResults<EntityInfo> getTargetsInfo(
            @WebParam(name="page") Page page,
            @WebParam(name="restriction") Restriction restriction)
                    throws DataSetProcessingException;

    Target getTarget(
            @WebParam(name="id") long targetId)
                    throws DataSetProcessingException;

    // Mission
    PagedResults<EntityInfo> getMissionsInfo(
            @WebParam(name="page") Page page,
            @WebParam(name="restriction") Restriction restriction)
                    throws DataSetProcessingException;

    Mission getMission(
            @WebParam(name="id") long missionId)
                    throws DataSetProcessingException;

    // Instrument
    PagedResults<EntityInfo> getInstrumentsInfo(
            @WebParam(name="page") Page page,
            @WebParam(name="restriction") Restriction restriction)
                    throws DataSetProcessingException;

    Instrument getInstrument(
            @WebParam(name="id") long instrumentId)
                    throws DataSetProcessingException;

    // DataSet
    PagedResults<EntityInfo> getDataSetsInfo(
            @WebParam(name="page") Page page,
            @WebParam(name="restriction") Restriction restriction)
                    throws DataSetProcessingException;

    DataSet getDataSet(
            @WebParam(name="id") long dataSetId)
                    throws DataSetProcessingException;

    List<EntityInfo> getDataSetRelatedEntitites(
            @WebParam(name="dataSetId") long dataSetId,
            @WebParam(name="relatedEntityType") @XmlJavaTypeAdapter (ClassXmlAdapter.class) Class<?> entityClass)
            throws DataSetProcessingException;

    // DataFile
    PagedResults<EntityInfo> getDocumentsInfo(
            @WebParam(name="page") Page page,
            @WebParam(name="restriction") Restriction restriction)
                    throws DataSetProcessingException;

    PagedResults<EntityInfo> getImagesInfo(
            @WebParam(name="page") Page page,
            @WebParam(name="restriction") Restriction restriction)
                    throws DataSetProcessingException;

    WSDataFile getDataFile(
            @WebParam(name="id") long dataFileId)
                    throws DataSetProcessingException;

    String getPreviewImageURL(
            @WebParam(name="imageFileId") long imageFileId)
                    throws DataSetProcessingException;

    // Search
    SearchResults searchEntities(
            @WebParam(name="searchText") String searchText,
            @WebParam(name="page") Page page)
                    throws DataSetProcessingException;

    PagedResults<EntityInfo> searchEntitiesByType(
            @WebParam(name="entityType") @XmlJavaTypeAdapter (ClassXmlAdapter.class) Class<?> entityClass,
            @WebParam(name="searchText") String searchText,
            @WebParam(name="page") Page page,
            @WebParam(name="restriction") Restriction restriction)
                    throws DataSetProcessingException;

    PagedResults<EntityInfo> searchDataSetsByCriteria(
            @WebParam(name="criteria") SearchCriteria criteria,
            @WebParam(name="page") Page page)
                    throws DataSetProcessingException;
}
