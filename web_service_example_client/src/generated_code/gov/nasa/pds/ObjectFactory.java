
package gov.nasa.pds;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the gov.nasa.pds package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetDataSetsInfo_QNAME = new QName("http://pds.nasa.gov/", "getDataSetsInfo");
    private final static QName _GetTargetTypesInfoResponse_QNAME = new QName("http://pds.nasa.gov/", "getTargetTypesInfoResponse");
    private final static QName _GetDataSetResponse_QNAME = new QName("http://pds.nasa.gov/", "getDataSetResponse");
    private final static QName _DataSetProcessingException_QNAME = new QName("http://pds.nasa.gov/", "DataSetProcessingException");
    private final static QName _GetMissionResponse_QNAME = new QName("http://pds.nasa.gov/", "getMissionResponse");
    private final static QName _GetInstrumentResponse_QNAME = new QName("http://pds.nasa.gov/", "getInstrumentResponse");
    private final static QName _GetDataSetsInfoResponse_QNAME = new QName("http://pds.nasa.gov/", "getDataSetsInfoResponse");
    private final static QName _GetMissionsInfoResponse_QNAME = new QName("http://pds.nasa.gov/", "getMissionsInfoResponse");
    private final static QName _GetTargetTypeResponse_QNAME = new QName("http://pds.nasa.gov/", "getTargetTypeResponse");
    private final static QName _SearchEntitiesByType_QNAME = new QName("http://pds.nasa.gov/", "searchEntitiesByType");
    private final static QName _GetDataSetRelatedEntititesResponse_QNAME = new QName("http://pds.nasa.gov/", "getDataSetRelatedEntititesResponse");
    private final static QName _GetInstrument_QNAME = new QName("http://pds.nasa.gov/", "getInstrument");
    private final static QName _GetDocumentsInfo_QNAME = new QName("http://pds.nasa.gov/", "getDocumentsInfo");
    private final static QName _GetPreviewImageURLResponse_QNAME = new QName("http://pds.nasa.gov/", "getPreviewImageURLResponse");
    private final static QName _GetDataSet_QNAME = new QName("http://pds.nasa.gov/", "getDataSet");
    private final static QName _GetTargetTypesInfo_QNAME = new QName("http://pds.nasa.gov/", "getTargetTypesInfo");
    private final static QName _SearchDataSetsByCriteria_QNAME = new QName("http://pds.nasa.gov/", "searchDataSetsByCriteria");
    private final static QName _GetPreviewImageURL_QNAME = new QName("http://pds.nasa.gov/", "getPreviewImageURL");
    private final static QName _SearchEntities_QNAME = new QName("http://pds.nasa.gov/", "searchEntities");
    private final static QName _GetMissionsInfo_QNAME = new QName("http://pds.nasa.gov/", "getMissionsInfo");
    private final static QName _GetDocumentsInfoResponse_QNAME = new QName("http://pds.nasa.gov/", "getDocumentsInfoResponse");
    private final static QName _GetImagesInfo_QNAME = new QName("http://pds.nasa.gov/", "getImagesInfo");
    private final static QName _GetMission_QNAME = new QName("http://pds.nasa.gov/", "getMission");
    private final static QName _GetImagesInfoResponse_QNAME = new QName("http://pds.nasa.gov/", "getImagesInfoResponse");
    private final static QName _GetTargetsInfoResponse_QNAME = new QName("http://pds.nasa.gov/", "getTargetsInfoResponse");
    private final static QName _SearchDataSetsByCriteriaResponse_QNAME = new QName("http://pds.nasa.gov/", "searchDataSetsByCriteriaResponse");
    private final static QName _GetTargetType_QNAME = new QName("http://pds.nasa.gov/", "getTargetType");
    private final static QName _GetInstrumentsInfo_QNAME = new QName("http://pds.nasa.gov/", "getInstrumentsInfo");
    private final static QName _GetDataFile_QNAME = new QName("http://pds.nasa.gov/", "getDataFile");
    private final static QName _SearchEntitiesByTypeResponse_QNAME = new QName("http://pds.nasa.gov/", "searchEntitiesByTypeResponse");
    private final static QName _GetDataSetRelatedEntitites_QNAME = new QName("http://pds.nasa.gov/", "getDataSetRelatedEntitites");
    private final static QName _SearchEntitiesResponse_QNAME = new QName("http://pds.nasa.gov/", "searchEntitiesResponse");
    private final static QName _GetInstrumentsInfoResponse_QNAME = new QName("http://pds.nasa.gov/", "getInstrumentsInfoResponse");
    private final static QName _GetTargetResponse_QNAME = new QName("http://pds.nasa.gov/", "getTargetResponse");
    private final static QName _GetDataFileResponse_QNAME = new QName("http://pds.nasa.gov/", "getDataFileResponse");
    private final static QName _GetTarget_QNAME = new QName("http://pds.nasa.gov/", "getTarget");
    private final static QName _GetTargetsInfo_QNAME = new QName("http://pds.nasa.gov/", "getTargetsInfo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: gov.nasa.pds
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetInstrumentsInfoResponse }
     * 
     */
    public GetInstrumentsInfoResponse createGetInstrumentsInfoResponse() {
        return new GetInstrumentsInfoResponse();
    }

    /**
     * Create an instance of {@link GetTargetResponse }
     * 
     */
    public GetTargetResponse createGetTargetResponse() {
        return new GetTargetResponse();
    }

    /**
     * Create an instance of {@link GetDataFileResponse }
     * 
     */
    public GetDataFileResponse createGetDataFileResponse() {
        return new GetDataFileResponse();
    }

    /**
     * Create an instance of {@link GetTargetsInfo }
     * 
     */
    public GetTargetsInfo createGetTargetsInfo() {
        return new GetTargetsInfo();
    }

    /**
     * Create an instance of {@link GetTarget }
     * 
     */
    public GetTarget createGetTarget() {
        return new GetTarget();
    }

    /**
     * Create an instance of {@link GetDataFile }
     * 
     */
    public GetDataFile createGetDataFile() {
        return new GetDataFile();
    }

    /**
     * Create an instance of {@link GetInstrumentsInfo }
     * 
     */
    public GetInstrumentsInfo createGetInstrumentsInfo() {
        return new GetInstrumentsInfo();
    }

    /**
     * Create an instance of {@link SearchEntitiesByTypeResponse }
     * 
     */
    public SearchEntitiesByTypeResponse createSearchEntitiesByTypeResponse() {
        return new SearchEntitiesByTypeResponse();
    }

    /**
     * Create an instance of {@link GetDataSetRelatedEntitites }
     * 
     */
    public GetDataSetRelatedEntitites createGetDataSetRelatedEntitites() {
        return new GetDataSetRelatedEntitites();
    }

    /**
     * Create an instance of {@link SearchEntitiesResponse }
     * 
     */
    public SearchEntitiesResponse createSearchEntitiesResponse() {
        return new SearchEntitiesResponse();
    }

    /**
     * Create an instance of {@link GetTargetsInfoResponse }
     * 
     */
    public GetTargetsInfoResponse createGetTargetsInfoResponse() {
        return new GetTargetsInfoResponse();
    }

    /**
     * Create an instance of {@link GetImagesInfoResponse }
     * 
     */
    public GetImagesInfoResponse createGetImagesInfoResponse() {
        return new GetImagesInfoResponse();
    }

    /**
     * Create an instance of {@link GetMission }
     * 
     */
    public GetMission createGetMission() {
        return new GetMission();
    }

    /**
     * Create an instance of {@link SearchDataSetsByCriteriaResponse }
     * 
     */
    public SearchDataSetsByCriteriaResponse createSearchDataSetsByCriteriaResponse() {
        return new SearchDataSetsByCriteriaResponse();
    }

    /**
     * Create an instance of {@link GetTargetType }
     * 
     */
    public GetTargetType createGetTargetType() {
        return new GetTargetType();
    }

    /**
     * Create an instance of {@link GetDocumentsInfoResponse }
     * 
     */
    public GetDocumentsInfoResponse createGetDocumentsInfoResponse() {
        return new GetDocumentsInfoResponse();
    }

    /**
     * Create an instance of {@link GetImagesInfo }
     * 
     */
    public GetImagesInfo createGetImagesInfo() {
        return new GetImagesInfo();
    }

    /**
     * Create an instance of {@link GetDataSet }
     * 
     */
    public GetDataSet createGetDataSet() {
        return new GetDataSet();
    }

    /**
     * Create an instance of {@link GetTargetTypesInfo }
     * 
     */
    public GetTargetTypesInfo createGetTargetTypesInfo() {
        return new GetTargetTypesInfo();
    }

    /**
     * Create an instance of {@link SearchDataSetsByCriteria }
     * 
     */
    public SearchDataSetsByCriteria createSearchDataSetsByCriteria() {
        return new SearchDataSetsByCriteria();
    }

    /**
     * Create an instance of {@link GetPreviewImageURL }
     * 
     */
    public GetPreviewImageURL createGetPreviewImageURL() {
        return new GetPreviewImageURL();
    }

    /**
     * Create an instance of {@link SearchEntities }
     * 
     */
    public SearchEntities createSearchEntities() {
        return new SearchEntities();
    }

    /**
     * Create an instance of {@link GetMissionsInfo }
     * 
     */
    public GetMissionsInfo createGetMissionsInfo() {
        return new GetMissionsInfo();
    }

    /**
     * Create an instance of {@link SearchEntitiesByType }
     * 
     */
    public SearchEntitiesByType createSearchEntitiesByType() {
        return new SearchEntitiesByType();
    }

    /**
     * Create an instance of {@link GetDataSetRelatedEntititesResponse }
     * 
     */
    public GetDataSetRelatedEntititesResponse createGetDataSetRelatedEntititesResponse() {
        return new GetDataSetRelatedEntititesResponse();
    }

    /**
     * Create an instance of {@link GetInstrument }
     * 
     */
    public GetInstrument createGetInstrument() {
        return new GetInstrument();
    }

    /**
     * Create an instance of {@link GetDocumentsInfo }
     * 
     */
    public GetDocumentsInfo createGetDocumentsInfo() {
        return new GetDocumentsInfo();
    }

    /**
     * Create an instance of {@link GetPreviewImageURLResponse }
     * 
     */
    public GetPreviewImageURLResponse createGetPreviewImageURLResponse() {
        return new GetPreviewImageURLResponse();
    }

    /**
     * Create an instance of {@link GetDataSetsInfoResponse }
     * 
     */
    public GetDataSetsInfoResponse createGetDataSetsInfoResponse() {
        return new GetDataSetsInfoResponse();
    }

    /**
     * Create an instance of {@link GetInstrumentResponse }
     * 
     */
    public GetInstrumentResponse createGetInstrumentResponse() {
        return new GetInstrumentResponse();
    }

    /**
     * Create an instance of {@link GetMissionsInfoResponse }
     * 
     */
    public GetMissionsInfoResponse createGetMissionsInfoResponse() {
        return new GetMissionsInfoResponse();
    }

    /**
     * Create an instance of {@link GetTargetTypeResponse }
     * 
     */
    public GetTargetTypeResponse createGetTargetTypeResponse() {
        return new GetTargetTypeResponse();
    }

    /**
     * Create an instance of {@link GetDataSetsInfo }
     * 
     */
    public GetDataSetsInfo createGetDataSetsInfo() {
        return new GetDataSetsInfo();
    }

    /**
     * Create an instance of {@link GetTargetTypesInfoResponse }
     * 
     */
    public GetTargetTypesInfoResponse createGetTargetTypesInfoResponse() {
        return new GetTargetTypesInfoResponse();
    }

    /**
     * Create an instance of {@link GetDataSetResponse }
     * 
     */
    public GetDataSetResponse createGetDataSetResponse() {
        return new GetDataSetResponse();
    }

    /**
     * Create an instance of {@link DataSetProcessingException }
     * 
     */
    public DataSetProcessingException createDataSetProcessingException() {
        return new DataSetProcessingException();
    }

    /**
     * Create an instance of {@link GetMissionResponse }
     * 
     */
    public GetMissionResponse createGetMissionResponse() {
        return new GetMissionResponse();
    }

    /**
     * Create an instance of {@link Page }
     * 
     */
    public Page createPage() {
        return new Page();
    }

    /**
     * Create an instance of {@link Restriction }
     * 
     */
    public Restriction createRestriction() {
        return new Restriction();
    }

    /**
     * Create an instance of {@link WsDataFile }
     * 
     */
    public WsDataFile createWsDataFile() {
        return new WsDataFile();
    }

    /**
     * Create an instance of {@link Property }
     * 
     */
    public Property createProperty() {
        return new Property();
    }

    /**
     * Create an instance of {@link PagedResults }
     * 
     */
    public PagedResults createPagedResults() {
        return new PagedResults();
    }

    /**
     * Create an instance of {@link MetadataObject }
     * 
     */
    public MetadataObject createMetadataObject() {
        return new MetadataObject();
    }

    /**
     * Create an instance of {@link SearchCriteria }
     * 
     */
    public SearchCriteria createSearchCriteria() {
        return new SearchCriteria();
    }

    /**
     * Create an instance of {@link DataSet }
     * 
     */
    public DataSet createDataSet() {
        return new DataSet();
    }

    /**
     * Create an instance of {@link TargetType }
     * 
     */
    public TargetType createTargetType() {
        return new TargetType();
    }

    /**
     * Create an instance of {@link Instrument }
     * 
     */
    public Instrument createInstrument() {
        return new Instrument();
    }

    /**
     * Create an instance of {@link EntityInfo }
     * 
     */
    public EntityInfo createEntityInfo() {
        return new EntityInfo();
    }

    /**
     * Create an instance of {@link InstrumentHost }
     * 
     */
    public InstrumentHost createInstrumentHost() {
        return new InstrumentHost();
    }

    /**
     * Create an instance of {@link Reference }
     * 
     */
    public Reference createReference() {
        return new Reference();
    }

    /**
     * Create an instance of {@link SearchResults }
     * 
     */
    public SearchResults createSearchResults() {
        return new SearchResults();
    }

    /**
     * Create an instance of {@link Mission }
     * 
     */
    public Mission createMission() {
        return new Mission();
    }

    /**
     * Create an instance of {@link Target }
     * 
     */
    public Target createTarget() {
        return new Target();
    }

    /**
     * Create an instance of {@link Volume }
     * 
     */
    public Volume createVolume() {
        return new Volume();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDataSetsInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getDataSetsInfo")
    public JAXBElement<GetDataSetsInfo> createGetDataSetsInfo(GetDataSetsInfo value) {
        return new JAXBElement<GetDataSetsInfo>(_GetDataSetsInfo_QNAME, GetDataSetsInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTargetTypesInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getTargetTypesInfoResponse")
    public JAXBElement<GetTargetTypesInfoResponse> createGetTargetTypesInfoResponse(GetTargetTypesInfoResponse value) {
        return new JAXBElement<GetTargetTypesInfoResponse>(_GetTargetTypesInfoResponse_QNAME, GetTargetTypesInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDataSetResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getDataSetResponse")
    public JAXBElement<GetDataSetResponse> createGetDataSetResponse(GetDataSetResponse value) {
        return new JAXBElement<GetDataSetResponse>(_GetDataSetResponse_QNAME, GetDataSetResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataSetProcessingException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "DataSetProcessingException")
    public JAXBElement<DataSetProcessingException> createDataSetProcessingException(DataSetProcessingException value) {
        return new JAXBElement<DataSetProcessingException>(_DataSetProcessingException_QNAME, DataSetProcessingException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMissionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getMissionResponse")
    public JAXBElement<GetMissionResponse> createGetMissionResponse(GetMissionResponse value) {
        return new JAXBElement<GetMissionResponse>(_GetMissionResponse_QNAME, GetMissionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInstrumentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getInstrumentResponse")
    public JAXBElement<GetInstrumentResponse> createGetInstrumentResponse(GetInstrumentResponse value) {
        return new JAXBElement<GetInstrumentResponse>(_GetInstrumentResponse_QNAME, GetInstrumentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDataSetsInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getDataSetsInfoResponse")
    public JAXBElement<GetDataSetsInfoResponse> createGetDataSetsInfoResponse(GetDataSetsInfoResponse value) {
        return new JAXBElement<GetDataSetsInfoResponse>(_GetDataSetsInfoResponse_QNAME, GetDataSetsInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMissionsInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getMissionsInfoResponse")
    public JAXBElement<GetMissionsInfoResponse> createGetMissionsInfoResponse(GetMissionsInfoResponse value) {
        return new JAXBElement<GetMissionsInfoResponse>(_GetMissionsInfoResponse_QNAME, GetMissionsInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTargetTypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getTargetTypeResponse")
    public JAXBElement<GetTargetTypeResponse> createGetTargetTypeResponse(GetTargetTypeResponse value) {
        return new JAXBElement<GetTargetTypeResponse>(_GetTargetTypeResponse_QNAME, GetTargetTypeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchEntitiesByType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "searchEntitiesByType")
    public JAXBElement<SearchEntitiesByType> createSearchEntitiesByType(SearchEntitiesByType value) {
        return new JAXBElement<SearchEntitiesByType>(_SearchEntitiesByType_QNAME, SearchEntitiesByType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDataSetRelatedEntititesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getDataSetRelatedEntititesResponse")
    public JAXBElement<GetDataSetRelatedEntititesResponse> createGetDataSetRelatedEntititesResponse(GetDataSetRelatedEntititesResponse value) {
        return new JAXBElement<GetDataSetRelatedEntititesResponse>(_GetDataSetRelatedEntititesResponse_QNAME, GetDataSetRelatedEntititesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInstrument }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getInstrument")
    public JAXBElement<GetInstrument> createGetInstrument(GetInstrument value) {
        return new JAXBElement<GetInstrument>(_GetInstrument_QNAME, GetInstrument.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDocumentsInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getDocumentsInfo")
    public JAXBElement<GetDocumentsInfo> createGetDocumentsInfo(GetDocumentsInfo value) {
        return new JAXBElement<GetDocumentsInfo>(_GetDocumentsInfo_QNAME, GetDocumentsInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPreviewImageURLResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getPreviewImageURLResponse")
    public JAXBElement<GetPreviewImageURLResponse> createGetPreviewImageURLResponse(GetPreviewImageURLResponse value) {
        return new JAXBElement<GetPreviewImageURLResponse>(_GetPreviewImageURLResponse_QNAME, GetPreviewImageURLResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDataSet }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getDataSet")
    public JAXBElement<GetDataSet> createGetDataSet(GetDataSet value) {
        return new JAXBElement<GetDataSet>(_GetDataSet_QNAME, GetDataSet.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTargetTypesInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getTargetTypesInfo")
    public JAXBElement<GetTargetTypesInfo> createGetTargetTypesInfo(GetTargetTypesInfo value) {
        return new JAXBElement<GetTargetTypesInfo>(_GetTargetTypesInfo_QNAME, GetTargetTypesInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchDataSetsByCriteria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "searchDataSetsByCriteria")
    public JAXBElement<SearchDataSetsByCriteria> createSearchDataSetsByCriteria(SearchDataSetsByCriteria value) {
        return new JAXBElement<SearchDataSetsByCriteria>(_SearchDataSetsByCriteria_QNAME, SearchDataSetsByCriteria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPreviewImageURL }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getPreviewImageURL")
    public JAXBElement<GetPreviewImageURL> createGetPreviewImageURL(GetPreviewImageURL value) {
        return new JAXBElement<GetPreviewImageURL>(_GetPreviewImageURL_QNAME, GetPreviewImageURL.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchEntities }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "searchEntities")
    public JAXBElement<SearchEntities> createSearchEntities(SearchEntities value) {
        return new JAXBElement<SearchEntities>(_SearchEntities_QNAME, SearchEntities.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMissionsInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getMissionsInfo")
    public JAXBElement<GetMissionsInfo> createGetMissionsInfo(GetMissionsInfo value) {
        return new JAXBElement<GetMissionsInfo>(_GetMissionsInfo_QNAME, GetMissionsInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDocumentsInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getDocumentsInfoResponse")
    public JAXBElement<GetDocumentsInfoResponse> createGetDocumentsInfoResponse(GetDocumentsInfoResponse value) {
        return new JAXBElement<GetDocumentsInfoResponse>(_GetDocumentsInfoResponse_QNAME, GetDocumentsInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetImagesInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getImagesInfo")
    public JAXBElement<GetImagesInfo> createGetImagesInfo(GetImagesInfo value) {
        return new JAXBElement<GetImagesInfo>(_GetImagesInfo_QNAME, GetImagesInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMission }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getMission")
    public JAXBElement<GetMission> createGetMission(GetMission value) {
        return new JAXBElement<GetMission>(_GetMission_QNAME, GetMission.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetImagesInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getImagesInfoResponse")
    public JAXBElement<GetImagesInfoResponse> createGetImagesInfoResponse(GetImagesInfoResponse value) {
        return new JAXBElement<GetImagesInfoResponse>(_GetImagesInfoResponse_QNAME, GetImagesInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTargetsInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getTargetsInfoResponse")
    public JAXBElement<GetTargetsInfoResponse> createGetTargetsInfoResponse(GetTargetsInfoResponse value) {
        return new JAXBElement<GetTargetsInfoResponse>(_GetTargetsInfoResponse_QNAME, GetTargetsInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchDataSetsByCriteriaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "searchDataSetsByCriteriaResponse")
    public JAXBElement<SearchDataSetsByCriteriaResponse> createSearchDataSetsByCriteriaResponse(SearchDataSetsByCriteriaResponse value) {
        return new JAXBElement<SearchDataSetsByCriteriaResponse>(_SearchDataSetsByCriteriaResponse_QNAME, SearchDataSetsByCriteriaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTargetType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getTargetType")
    public JAXBElement<GetTargetType> createGetTargetType(GetTargetType value) {
        return new JAXBElement<GetTargetType>(_GetTargetType_QNAME, GetTargetType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInstrumentsInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getInstrumentsInfo")
    public JAXBElement<GetInstrumentsInfo> createGetInstrumentsInfo(GetInstrumentsInfo value) {
        return new JAXBElement<GetInstrumentsInfo>(_GetInstrumentsInfo_QNAME, GetInstrumentsInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDataFile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getDataFile")
    public JAXBElement<GetDataFile> createGetDataFile(GetDataFile value) {
        return new JAXBElement<GetDataFile>(_GetDataFile_QNAME, GetDataFile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchEntitiesByTypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "searchEntitiesByTypeResponse")
    public JAXBElement<SearchEntitiesByTypeResponse> createSearchEntitiesByTypeResponse(SearchEntitiesByTypeResponse value) {
        return new JAXBElement<SearchEntitiesByTypeResponse>(_SearchEntitiesByTypeResponse_QNAME, SearchEntitiesByTypeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDataSetRelatedEntitites }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getDataSetRelatedEntitites")
    public JAXBElement<GetDataSetRelatedEntitites> createGetDataSetRelatedEntitites(GetDataSetRelatedEntitites value) {
        return new JAXBElement<GetDataSetRelatedEntitites>(_GetDataSetRelatedEntitites_QNAME, GetDataSetRelatedEntitites.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchEntitiesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "searchEntitiesResponse")
    public JAXBElement<SearchEntitiesResponse> createSearchEntitiesResponse(SearchEntitiesResponse value) {
        return new JAXBElement<SearchEntitiesResponse>(_SearchEntitiesResponse_QNAME, SearchEntitiesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInstrumentsInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getInstrumentsInfoResponse")
    public JAXBElement<GetInstrumentsInfoResponse> createGetInstrumentsInfoResponse(GetInstrumentsInfoResponse value) {
        return new JAXBElement<GetInstrumentsInfoResponse>(_GetInstrumentsInfoResponse_QNAME, GetInstrumentsInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTargetResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getTargetResponse")
    public JAXBElement<GetTargetResponse> createGetTargetResponse(GetTargetResponse value) {
        return new JAXBElement<GetTargetResponse>(_GetTargetResponse_QNAME, GetTargetResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDataFileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getDataFileResponse")
    public JAXBElement<GetDataFileResponse> createGetDataFileResponse(GetDataFileResponse value) {
        return new JAXBElement<GetDataFileResponse>(_GetDataFileResponse_QNAME, GetDataFileResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTarget }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getTarget")
    public JAXBElement<GetTarget> createGetTarget(GetTarget value) {
        return new JAXBElement<GetTarget>(_GetTarget_QNAME, GetTarget.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTargetsInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pds.nasa.gov/", name = "getTargetsInfo")
    public JAXBElement<GetTargetsInfo> createGetTargetsInfo(GetTargetsInfo value) {
        return new JAXBElement<GetTargetsInfo>(_GetTargetsInfo_QNAME, GetTargetsInfo.class, null, value);
    }

}
