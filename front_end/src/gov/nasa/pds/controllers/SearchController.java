package gov.nasa.pds.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.nasa.pds.entities.DataFile;
import gov.nasa.pds.entities.DataSet;
import gov.nasa.pds.entities.EntityInfo;
import gov.nasa.pds.entities.Instrument;
import gov.nasa.pds.entities.Mission;
import gov.nasa.pds.entities.Page;
import gov.nasa.pds.entities.PagedResults;
import gov.nasa.pds.entities.SearchCriteria;
import gov.nasa.pds.entities.SearchCriteriaList;
import gov.nasa.pds.entities.Target;
import gov.nasa.pds.entities.TargetType;
import gov.nasa.pds.services.DataSetProcessingException;
import gov.nasa.pds.services.DataSetService;
import gov.nasa.pds.services.EntitiesStats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcoder.commons.utils.LoggingWrapperUtility;
import com.topcoder.util.log.Log;

@Controller
@RequestMapping("/search")
public class SearchController {
    /**
     * The class name used for logging.
     */
    private static final String CLASS_NAME = SearchController.class.getName();

    /**
     * The logger for logging support in business methods.
     */
    private Log logger;

    /**
     * The DataSetService instance initialized with Spring setter dependency injection.
     */
    private DataSetService dataSetService;

    /**
     * Creates SearchController instance.
     */
    public SearchController() {
        // Empty
    }

    /**
     * Sets the logger.
     *
     * @param logger
     *              the logger instance to set
     */
    @Autowired
    public void setLogger(Log logger) {
        this.logger = logger;
    }

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

    /**
     * Spring MVC Controller that handles '/search' ajax request
     *
     * @return the search result instance
     *
     * @throws PDSControllerException
     *              in case if some error occurred
     */
    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody SearchResults search(String searchText, int pageNumber, int itemsPerPage)
        throws PDSControllerException {
        String signature = CLASS_NAME + ".search(String searchText)";

        LoggingWrapperUtility.logEntrance(logger, signature,
                new String[] {"searchText"}, new Object[] {searchText});

        try {
            SearchResults result = new SearchResults();

            Page page =  (itemsPerPage == -1) ? null : new Page(pageNumber, itemsPerPage);

            // target types
            {
                PagedResults<EntityInfo> targetTypesInfo = dataSetService.searchEntitiesByType(TargetType.class, searchText, page, null);
                if (!targetTypesInfo.getResults().isEmpty()) {
                    List<EntitiesStats> childrenStats = new ArrayList<EntitiesStats>();
                    for (EntityInfo targetTypeInfo : targetTypesInfo.getResults()) {
                        childrenStats.add(dataSetService.getTargetTypeChildrenStats(targetTypeInfo.getId()));
                    }
                    NavigationView view = new NavigationView();
                    view.setEntities(targetTypesInfo.getResults());
                    view.setChildrenStats(childrenStats);
                    view.setTotalEntities(targetTypesInfo.getTotal());
                    result.setTargetTypes(view);
                }
            }
            // targets
            {
                PagedResults<EntityInfo> targetsInfo = dataSetService.searchEntitiesByType(Target.class, searchText, page, null);
                if (!targetsInfo.getResults().isEmpty()) {
                    List<EntitiesStats> childrenStats = new ArrayList<EntitiesStats>();
                    for (EntityInfo targetInfo : targetsInfo.getResults()) {
                        childrenStats.add(dataSetService.getTargetChildrenStats(targetInfo.getId()));
                    }
                    NavigationView view = new NavigationView();
                    view.setEntities(targetsInfo.getResults());
                    view.setChildrenStats(childrenStats);
                    view.setTotalEntities(targetsInfo.getTotal());
                    result.setTargets(view);
                }
            }
            // missions
            {
                PagedResults<EntityInfo> missionsInfo = dataSetService.searchEntitiesByType(Mission.class, searchText, page, null);
                if (!missionsInfo.getResults().isEmpty()) {
                    List<EntitiesStats> childrenStats = new ArrayList<EntitiesStats>();
                    for (EntityInfo missionInfo : missionsInfo.getResults()) {
                        childrenStats.add(dataSetService.getMissionChildrenStats(missionInfo.getId()));
                    }
                    NavigationView view = new NavigationView();
                    view.setEntities(missionsInfo.getResults());
                    view.setChildrenStats(childrenStats);
                    view.setTotalEntities(missionsInfo.getTotal());
                    result.setMissions(view);
                }
            }
            // instruments
            {
                PagedResults<EntityInfo> instrumentsInfo = dataSetService.searchEntitiesByType(Instrument.class, searchText, page, null);
                if (!instrumentsInfo.getResults().isEmpty()) {
                    List<EntitiesStats> childrenStats = new ArrayList<EntitiesStats>();
                    for (EntityInfo instrumentInfo : instrumentsInfo.getResults()) {
                        childrenStats.add(dataSetService.getInstrumentChildrenStats(instrumentInfo.getId()));
                    }
                    NavigationView view = new NavigationView();
                    view.setEntities(instrumentsInfo.getResults());
                    view.setChildrenStats(childrenStats);
                    view.setTotalEntities(instrumentsInfo.getTotal());
                    result.setInstruments(view);
                }
            }
            // datasets
            {
                PagedResults<EntityInfo> dataSetsInfo = dataSetService.searchEntitiesByType(DataSet.class, searchText, page, null);
                if (!dataSetsInfo.getResults().isEmpty()) {
                    List<EntitiesStats> childrenStats = new ArrayList<EntitiesStats>();
                    for (EntityInfo dataSetInfo : dataSetsInfo.getResults()) {
                        childrenStats.add(dataSetService.getDataSetChildrenStats(dataSetInfo.getId()));
                    }
                    NavigationView view = new NavigationView();
                    view.setEntities(dataSetsInfo.getResults());
                    view.setChildrenStats(childrenStats);
                    view.setTotalEntities(dataSetsInfo.getTotal());
                    result.setDataSets(view);
                }
            }
            // documents
            {
                PagedResults<EntityInfo> documentsInfo = dataSetService.searchEntitiesByType(DataFile.class, searchText, page, null);
                if (!documentsInfo.getResults().isEmpty()) {
                    NavigationView view = new NavigationView();
                    view.setEntities(documentsInfo.getResults());
                    view.setTotalEntities(documentsInfo.getTotal());
                    result.setDocuments(view);
                }
            }

            LoggingWrapperUtility.logExit(logger, signature, new Object[] {result});
            return result;
        } catch (DataSetProcessingException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new PDSControllerException(
                    "Failed to handle search request", e));
        }
    }

    /**
     * Spring MVC Controller that handles '/search/advanced' ajax request
     *
     * @return the search result instance
     *
     * @throws PDSControllerException
     *              in case if some error occurred
     */
    @RequestMapping(value="/advanced", method=RequestMethod.GET)
    public String advancedSearch() throws PDSControllerException {
        return "advanced-search";
    }

    /**
     * Spring MVC Controller that handles '/search/doAdvanced' ajax request
     *
     * @return the search result instance
     *
     * @throws PDSControllerException
     *              in case if some error occurred
     */
    @RequestMapping(value="/doAdvanced", method=RequestMethod.POST)
    public @ResponseBody SearchResults doAdvancedSearch(@RequestBody SearchCriteriaList searchCriteriaList,
        int pageNumber, int itemsPerPage, int type) throws PDSControllerException {
        String signature = CLASS_NAME
                + ".doAdvancedSearch(List<DataSetSearchCriteria> searchCriterias, int pageNumber, int itemsPerPage)";

        LoggingWrapperUtility.logEntrance(logger, signature,
                new String[] {"searchCriterias"}, new Object[] {Helper.toString(searchCriteriaList.getList())});

        try {
            List<EntityInfo> allDataSetsInfo = new ArrayList<EntityInfo>();

            List<EntityInfo> allTargetTypesInfo = new ArrayList<EntityInfo>();
            List<EntityInfo> allTargetsInfo = new ArrayList<EntityInfo>();
            List<EntityInfo> allMissionsInfo = new ArrayList<EntityInfo>();
            List<EntityInfo> allInstrumentsInfo = new ArrayList<EntityInfo>();

            // TODO: implement special optimized case when there is only one search criteria group - in this case
            // we can request paginated data from the server. For complex cases with several criteria groups we are
            // forced to get all data in order to correct merge it before pagination.

            for (SearchCriteria criteria : searchCriteriaList.getList()) {
                List<EntityInfo> dataSetsInfo = dataSetService.searchDataSetsByCriteria(criteria, null).getResults();
                allDataSetsInfo.addAll(dataSetsInfo);

                for (EntityInfo dataSetInfo : dataSetsInfo) {
                    long dataSetId = dataSetInfo.getId();

                    allTargetTypesInfo.addAll(dataSetService.getDataSetRelatedEntitites(dataSetId, TargetType.class));
                    allTargetsInfo.addAll(dataSetService.getDataSetRelatedEntitites(dataSetId, Target.class));
                    allMissionsInfo.addAll(dataSetService.getDataSetRelatedEntitites(dataSetId, Mission.class));
                    allInstrumentsInfo.addAll(dataSetService.getDataSetRelatedEntitites(dataSetId, Instrument.class));
                }
            }

            SearchResults result = new SearchResults();

            int start = (pageNumber - 1) * itemsPerPage;

            // prepare target types results
            if (!allTargetTypesInfo.isEmpty() && (type == -1 || type == 0)) {
                HashSet<EntityInfo> set = new HashSet<EntityInfo>(allTargetTypesInfo);
                allTargetTypesInfo.clear();
                allTargetTypesInfo.addAll(set);

                if (itemsPerPage != -1) {
                    int end = Math.min(allTargetTypesInfo.size(), start + itemsPerPage);
                    allTargetTypesInfo = allTargetTypesInfo.subList(start, end);
                }

                List<EntitiesStats> childrenStats = new ArrayList<EntitiesStats>();
                for (EntityInfo targetTypeInfo : allTargetTypesInfo) {
                    childrenStats.add(dataSetService.getTargetTypeChildrenStats(targetTypeInfo.getId()));
                }

                NavigationView view = new NavigationView();
                view.setEntities(allTargetTypesInfo);
                view.setChildrenStats(childrenStats);
                view.setTotalEntities(set.size());
                result.setTargetTypes(view);
            }
            // prepare targets results
            if (!allTargetsInfo.isEmpty() && (type == -1 || type == 1)) {
                HashSet<EntityInfo> set = new HashSet<EntityInfo>(allTargetsInfo);
                allTargetsInfo.clear();
                allTargetsInfo.addAll(set);

                if (itemsPerPage != -1) {
                    int end = Math.min(allTargetsInfo.size(), start + itemsPerPage);
                    allTargetsInfo = allTargetsInfo.subList(start, end);
                }

                List<EntitiesStats> childrenStats = new ArrayList<EntitiesStats>();
                for (EntityInfo targetInfo : allTargetsInfo) {
                    childrenStats.add(dataSetService.getTargetChildrenStats(targetInfo.getId()));
                }

                NavigationView view = new NavigationView();
                view.setEntities(allTargetsInfo);
                view.setChildrenStats(childrenStats);
                view.setTotalEntities(set.size());
                result.setTargets(view);
            }
            // prepare missions results
            if (!allMissionsInfo.isEmpty() && (type == -1 || type == 2)) {
                HashSet<EntityInfo> set = new HashSet<EntityInfo>(allMissionsInfo);
                allMissionsInfo.clear();
                allMissionsInfo.addAll(set);

                if (itemsPerPage != -1) {
                    int end = Math.min(allMissionsInfo.size(), start + itemsPerPage);
                    allMissionsInfo = allMissionsInfo.subList(start, end);
                }

                List<EntitiesStats> childrenStats = new ArrayList<EntitiesStats>();
                for (EntityInfo missionInfo : allMissionsInfo) {
                    childrenStats.add(dataSetService.getMissionChildrenStats(missionInfo.getId()));
                }

                NavigationView view = new NavigationView();
                view.setEntities(allMissionsInfo);
                view.setChildrenStats(childrenStats);
                view.setTotalEntities(set.size());
                result.setMissions(view);
            }
            // prepare instruments results
            if (!allInstrumentsInfo.isEmpty() && (type == -1 || type == 3)) {
                HashSet<EntityInfo> set = new HashSet<EntityInfo>(allInstrumentsInfo);
                allInstrumentsInfo.clear();
                allInstrumentsInfo.addAll(set);

                if (itemsPerPage != -1) {
                    int end = Math.min(allInstrumentsInfo.size(), start + itemsPerPage);
                    allInstrumentsInfo = allInstrumentsInfo.subList(start, end);
                }

                List<EntitiesStats> childrenStats = new ArrayList<EntitiesStats>();
                for (EntityInfo instrumentInfo : allInstrumentsInfo) {
                    childrenStats.add(dataSetService.getInstrumentChildrenStats(instrumentInfo.getId()));
                }

                NavigationView view = new NavigationView();
                view.setEntities(allInstrumentsInfo);
                view.setChildrenStats(childrenStats);
                view.setTotalEntities(set.size());
                result.setInstruments(view);
            }
            // prepare datasets results
            if (!allDataSetsInfo.isEmpty() && (type == -1 || type == 4)) {
                HashSet<EntityInfo> set = new HashSet<EntityInfo>(allDataSetsInfo);
                allDataSetsInfo.clear();
                allDataSetsInfo.addAll(set);

                if (itemsPerPage != -1) {
                    int end = Math.min(allDataSetsInfo.size(), start + itemsPerPage);
                    allDataSetsInfo = allDataSetsInfo.subList(start, end);
                }

                List<EntitiesStats> childrenStats = new ArrayList<EntitiesStats>();
                for (EntityInfo dataSetInfo : allDataSetsInfo) {
                    childrenStats.add(dataSetService.getDataSetChildrenStats(dataSetInfo.getId()));
                }

                NavigationView view = new NavigationView();
                view.setEntities(allDataSetsInfo);
                view.setChildrenStats(childrenStats);
                view.setTotalEntities(set.size());
                result.setDataSets(view);
            }

            return result;
        } catch (DataSetProcessingException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new PDSControllerException(
                    "Failed to handle search request", e));
        }
    }

    @RequestMapping("/results")
    public String home(HttpServletRequest request, HttpServletResponse response) {
        return "browsing";
    }
}
