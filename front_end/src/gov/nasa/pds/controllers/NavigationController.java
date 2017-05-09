package gov.nasa.pds.controllers;

import gov.nasa.pds.entities.DataSet;
import gov.nasa.pds.entities.EntityInfo;
import gov.nasa.pds.entities.Instrument;
import gov.nasa.pds.entities.Mission;
import gov.nasa.pds.entities.Page;
import gov.nasa.pds.entities.PagedResults;
import gov.nasa.pds.entities.Restriction;
import gov.nasa.pds.entities.Target;
import gov.nasa.pds.entities.TargetType;
import gov.nasa.pds.services.DataSetProcessingException;
import gov.nasa.pds.services.DataSetService;
import gov.nasa.pds.services.EntitiesStats;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcoder.commons.utils.LoggingWrapperUtility;
import com.topcoder.util.log.Log;

@Controller
@RequestMapping("/navigation")
public class NavigationController {
    /**
     * The class name used for logging.
     */
    private static final String CLASS_NAME = NavigationController.class.getName();

    /**
     * The logger for logging support in business methods.
     */
    private Log logger;

    /**
     * The DataSetService instance initialized with Spring setter dependency injection.
     */
    private DataSetService dataSetService;

    public NavigationController() {
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
     * Spring MVC Controller that handles '/navigation/targettypes' ajax request
     *
     * @return the navigation view instance
     *
     * @throws PDSControllerException
     *              in case if some error occurred
     */
    @RequestMapping(value="/targettypes", method=RequestMethod.GET)
    public @ResponseBody NavigationView getTargetTypesView(NavigationState navigationState)
        throws PDSControllerException {
        String signature = CLASS_NAME + ".getTargetTypesViewLevel(NavigationState navigationState)";

        try {
            Page pageInfo = getPage(navigationState);
            PagedResults<EntityInfo> targetTypesInfo = dataSetService.getTargetTypesInfo(pageInfo);

            List<EntitiesStats> childrenStats = new ArrayList<EntitiesStats>();
            for (EntityInfo targetTypeInfo : targetTypesInfo.getResults()) {
                childrenStats.add(dataSetService.getTargetTypeChildrenStats(targetTypeInfo.getId()));
            }

            NavigationView view = new NavigationView();
            view.setEntities(targetTypesInfo.getResults());
            view.setChildrenStats(childrenStats);
            view.setTotalEntities(targetTypesInfo.getTotal());

            LoggingWrapperUtility.logExit(logger, signature, new Object[] {view});
            return view;
        } catch (DataSetProcessingException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new PDSControllerException(
                    "Failed to retrieve target types view data", e));
        }
    }

    /**
     * Spring MVC Controller that handles '/navigation/targets' ajax request
     *
     * @return the navigation view instance
     *
     * @throws PDSControllerException
     *              in case if some error occurred
     */
    @RequestMapping(value="/targets", method=RequestMethod.GET)
    public @ResponseBody NavigationView getTargetsView(NavigationState navigationState)
        throws PDSControllerException {
        String signature = CLASS_NAME + ".getTargetsView(NavigationState state)";

        try {
            long targetTypeId = navigationState.getTargetTypeId();
            Page page = getPage(navigationState);
            Restriction restriction = new Restriction(targetTypeId, TargetType.class);
            PagedResults<EntityInfo> targetsInfo = dataSetService.getTargetsInfo(page, restriction);

            List<EntitiesStats> childrenStats = new ArrayList<EntitiesStats>();
            for (EntityInfo targetInfo : targetsInfo.getResults()) {
                childrenStats.add(dataSetService.getTargetChildrenStats(targetInfo.getId()));
            }

            NavigationView view = new NavigationView();
            view.setEntities(targetsInfo.getResults());
            view.setChildrenStats(childrenStats);
            view.setTotalEntities(targetsInfo.getTotal());

            LoggingWrapperUtility.logExit(logger, signature, new Object[] {view});
            return view;
        } catch (DataSetProcessingException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new PDSControllerException(
                    "Failed to browse PDS database", e));
        }
    }

    /**
     * Spring MVC Controller that handles '/navigation/missions' ajax request
     *
     * @return the navigation view instance
     *
     * @throws PDSControllerException
     *              in case if some error occurred
     */
    @RequestMapping(value="/missions", method=RequestMethod.GET)
    public @ResponseBody NavigationView getMissionsView(NavigationState navigationState)
        throws PDSControllerException {
        String signature = CLASS_NAME + ".getMissionsView(NavigationState navigationState)";

        try {
            Restriction restriction = null;
            if (navigationState.getTargetId() != 0) {
                restriction = new Restriction(navigationState.getTargetId(), Target.class);
            } else if (navigationState.getTargetTypeId() != 0) {
                restriction = new Restriction(navigationState.getTargetTypeId(), TargetType.class);
            }

            Page page = getPage(navigationState);
            PagedResults<EntityInfo> missionsInfo = dataSetService.getMissionsInfo(page, restriction);

            List<EntitiesStats> childrenStats = new ArrayList<EntitiesStats>();
            for (EntityInfo missionInfo : missionsInfo.getResults()) {
                childrenStats.add(dataSetService.getMissionChildrenStats(missionInfo.getId()));
            }

            NavigationView view = new NavigationView();
            view.setEntities(missionsInfo.getResults());
            view.setChildrenStats(childrenStats);
            view.setTotalEntities(missionsInfo.getTotal());

            LoggingWrapperUtility.logExit(logger, signature, new Object[] {view});
            return view;
        } catch (DataSetProcessingException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new PDSControllerException(
                    "Failed to browse PDS database", e));
        }
    }

    /**
     * Spring MVC Controller that handles '/navigation/instruments' ajax request
     *
     * @return the navigation view instance
     *
     * @throws PDSControllerException
     *              in case if some error occurred
     */
    @RequestMapping(value="/instruments", method=RequestMethod.GET)
    public @ResponseBody NavigationView getInstrumentsView(NavigationState navigationState)
        throws PDSControllerException {
        String signature = CLASS_NAME + ".getInstrumentsView(NavigationState navigationState)";
        try {
            Restriction restriction = null;
            if (navigationState.getMissionId() != 0) {
                restriction = new Restriction(navigationState.getMissionId(), Mission.class);
            } else if (navigationState.getTargetId() != 0) {
                restriction = new Restriction(navigationState.getTargetId(), Target.class);
            } else if (navigationState.getTargetTypeId() != 0) {
                restriction = new Restriction(navigationState.getTargetTypeId(), TargetType.class);
            } else {
                throw LoggingWrapperUtility.logException(logger, signature, new PDSControllerException(
                        "Incorrect navigation state"));
            }

            Page page = getPage(navigationState);
            PagedResults<EntityInfo> instrumentsInfo = dataSetService.getInstrumentsInfo(page, restriction);

            List<EntitiesStats> childrenStats = new ArrayList<EntitiesStats>();
            for (EntityInfo instrumentInfo : instrumentsInfo.getResults()) {
                childrenStats.add(dataSetService.getInstrumentChildrenStats(instrumentInfo.getId()));
            }

            NavigationView view = new NavigationView();
            view.setEntities(instrumentsInfo.getResults());
            view.setChildrenStats(childrenStats);
            view.setTotalEntities(instrumentsInfo.getTotal());

            LoggingWrapperUtility.logExit(logger, signature, new Object[] {view});
            return view;
        } catch (DataSetProcessingException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new PDSControllerException(
                    "Failed to browse PDS database", e));
        }
    }

    /**
     * Spring MVC Controller that handles '/navigation/datasets' ajax request
     *
     * @return the navigation view instance
     *
     * @throws PDSControllerException
     *              in case if some error occurred
     */
    @RequestMapping(value="/datasets", method=RequestMethod.GET)
    public @ResponseBody NavigationView getDataSetsView(NavigationState navigationState)
        throws PDSControllerException {
        String signature = CLASS_NAME + ".getDataSetsView(NavigationState navigationState)";
        try {
            Restriction restriction = null;
            if (navigationState.getInstrumentId() != 0) {
                restriction = new Restriction(navigationState.getInstrumentId(), Instrument.class);
            } else if (navigationState.getMissionId() != 0) {
                restriction = new Restriction(navigationState.getMissionId(), Mission.class);
            } else if (navigationState.getTargetId() != 0) {
                restriction = new Restriction(navigationState.getTargetId(), Target.class);
            } else if (navigationState.getTargetTypeId() != 0) {
                restriction = new Restriction(navigationState.getTargetTypeId(), TargetType.class);
            } else {
                throw LoggingWrapperUtility.logException(logger, signature, new PDSControllerException(
                        "Incorrect navigation state"));
            }

            Page page = getPage(navigationState);
            PagedResults<EntityInfo> dataSetsInfo = dataSetService.getDataSetsInfo(page, restriction);

            List<EntitiesStats> childrenStats = new ArrayList<EntitiesStats>();
            for (EntityInfo dataSetInfo : dataSetsInfo.getResults()) {
                childrenStats.add(dataSetService.getDataSetChildrenStats(dataSetInfo.getId()));
            }

            NavigationView view = new NavigationView();
            view.setEntities(dataSetsInfo.getResults());
            view.setChildrenStats(childrenStats);
            view.setTotalEntities(dataSetsInfo.getTotal());

            LoggingWrapperUtility.logExit(logger, signature, new Object[] {view});
            return view;
        } catch (DataSetProcessingException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new PDSControllerException(
                    "Failed to browse PDS database", e));
        }
    }

    /**
     * Spring MVC Controller that handles '/navigation/documents' ajax request
     *
     * @return the navigation view instance
     *
     * @throws PDSControllerException
     *              in case if some error occurred
     */
    @RequestMapping(value="/documents", method=RequestMethod.GET)
    public @ResponseBody NavigationView getDocumentsView(NavigationState navigationState)
        throws PDSControllerException {
        String signature = CLASS_NAME + ".getDocumentsView(NavigationState navigationState)";
        try {
            Restriction restriction = null;
            if (navigationState.getDataSetId() != 0) {
                restriction = new Restriction(navigationState.getDataSetId(), DataSet.class);
            } else if (navigationState.getInstrumentId() != 0) {
                restriction = new Restriction(navigationState.getInstrumentId(), Instrument.class);
            } else if (navigationState.getMissionId() != 0) {
                restriction = new Restriction(navigationState.getMissionId(), Mission.class);
            } else if (navigationState.getTargetId() != 0) {
                restriction = new Restriction(navigationState.getTargetId(), Target.class);
            } else if (navigationState.getTargetTypeId() != 0) {
                restriction = new Restriction(navigationState.getTargetTypeId(), TargetType.class);
            } else {
                throw LoggingWrapperUtility.logException(logger, signature, new PDSControllerException(
                        "Incorrect navigation state"));
            }

            Page page = getPage(navigationState);
            PagedResults<EntityInfo> documentsInfo = dataSetService.getDataFilesInfo(page, restriction);

            NavigationView view = new NavigationView();
            view.setEntities(documentsInfo.getResults());
            view.setTotalEntities(documentsInfo.getTotal());

            LoggingWrapperUtility.logExit(logger, signature, new Object[] {view});
            return view;
        } catch (DataSetProcessingException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new PDSControllerException(
                    "Failed to browse PDS database", e));
        }
    }

    private Page getPage(NavigationState navigationState) {
        int pageNumber = navigationState.getPageNumber();
        int itemsPerPage = navigationState.getItemsPerPage();

        if (itemsPerPage == -1) {
            return null;
        }
        return new Page(pageNumber, itemsPerPage);
    }
}
