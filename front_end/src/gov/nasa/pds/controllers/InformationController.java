package gov.nasa.pds.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import gov.nasa.pds.entities.DataFile;
import gov.nasa.pds.entities.DataSet;
import gov.nasa.pds.entities.Instrument;
import gov.nasa.pds.entities.Mission;
import gov.nasa.pds.services.DataSetProcessingException;
import gov.nasa.pds.services.DataSetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcoder.commons.utils.LoggingWrapperUtility;
import com.topcoder.util.log.Log;

@Controller
@RequestMapping("/info")
public class InformationController {
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

    public InformationController() {
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
     * Spring MVC Controller that handles '/info/mission' ajax request
     *
     * @return the mission
     *
     * @throws PDSControllerException
     *              in case if some error occurred
     */
    @RequestMapping(value="/mission", method=RequestMethod.GET)
    public @ResponseBody Mission getMission(long missionId) throws PDSControllerException {
        String signature = CLASS_NAME + ".getMission(long missionId)";

        LoggingWrapperUtility.logEntrance(logger, signature,
                new String[] {"missionId"}, new Object[] {missionId});

        try {
            Mission mission = dataSetService.getMission(missionId);
            LoggingWrapperUtility.logExit(logger, signature, null);
            return mission;
        } catch (DataSetProcessingException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new PDSControllerException(
                    "Failed to retrieve mission", e));
        }
    }

    /**
     * Spring MVC Controller that handles '/info/instrument' ajax request
     *
     * @return the instrument
     *
     * @throws PDSControllerException
     *              in case if some error occurred
     */
    @RequestMapping(value="/instrument", method=RequestMethod.GET)
    public @ResponseBody Instrument getInstrument(long instrumentId) throws PDSControllerException {
        String signature = CLASS_NAME + ".getInstrument(long instrumentId)";

        LoggingWrapperUtility.logEntrance(logger, signature,
                new String[] {"instrumentId"}, new Object[] {instrumentId});

        try {
            Instrument instrument = dataSetService.getInstrument(instrumentId);
            LoggingWrapperUtility.logExit(logger, signature, null);
            return instrument;
        } catch (DataSetProcessingException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new PDSControllerException(
                    "Failed to retrieve instrument", e));
        }
    }

    /**
     * Spring MVC Controller that handles '/info/dataset' ajax request
     *
     * @return the instrument
     *
     * @throws PDSControllerException
     *              in case if some error occurred
     */
    @RequestMapping(value="/dataset", method=RequestMethod.GET)
    public @ResponseBody DataSet getDataSet(long dataSetId) throws PDSControllerException {
        String signature = CLASS_NAME + ".getDataSet(long dataSetId)";

        LoggingWrapperUtility.logEntrance(logger, signature,
                new String[] {"dataSetId"}, new Object[] {dataSetId});

        try {
            DataSet dataSet = dataSetService.getDataSet(dataSetId);
            LoggingWrapperUtility.logExit(logger, signature, null);
            return dataSet;
        } catch (DataSetProcessingException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new PDSControllerException(
                    "Failed to retrieve data set", e));
        }
    }

    /**
     * Spring MVC Controller that handles '/info/document' ajax request
     *
     * @return the data file
     *
     * @throws PDSControllerException
     *              in case if some error occurred
     */
    @RequestMapping(value="/document", method=RequestMethod.GET)
    public @ResponseBody DataFile getDocument(long documentId) throws PDSControllerException {
        String signature = CLASS_NAME + ".getDocument(long documentId)";

        LoggingWrapperUtility.logEntrance(logger, signature,
                new String[] {"documentId"}, new Object[] {documentId});

        try {
            DataFile dataFile = dataSetService.getDataFile(documentId);
            LoggingWrapperUtility.logExit(logger, signature, null);
            return dataFile;
        } catch (DataSetProcessingException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new PDSControllerException(
                    "Failed to retrieve document", e));
        }
    }

    /**
     * Spring MVC Controller that handles '/info/downloadDocument' request
     *
     * @throws PDSControllerException
     *              in case if some error occurred
     */
    @RequestMapping(value="/downloadDocument", method=RequestMethod.GET)
    public void downloadDocument(long documentId, HttpServletResponse response) throws PDSControllerException {
        String signature = CLASS_NAME + ".downloadDocument(long documentId)";

        LoggingWrapperUtility.logEntrance(logger, signature,
                new String[] {"documentId"}, new Object[] {documentId});

        try {
            DataFile dataFile = dataSetService.getDataFile(documentId);
            if (dataFile.getPath() != null) {
                InputStream is = new FileInputStream(dataFile.getPath());
                response.setContentType("application/octet-stream");
                response.addHeader("Content-Length", "" + new File(dataFile.getPath()).length());
                response.setHeader("Content-Disposition","attachment; filename=\"" + dataFile.getName() +"\"");
                FileCopyUtils.copy(is, response.getOutputStream());
                response.flushBuffer();
            }

            LoggingWrapperUtility.logExit(logger, signature, null);
        } catch (DataSetProcessingException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new PDSControllerException(
                    "Failed to download document", e));
        } catch (FileNotFoundException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new PDSControllerException(
                    "Failed to download document", e));
        } catch (IOException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new PDSControllerException(
                    "Failed to download document", e));
        }
    }
}
