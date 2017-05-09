/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.commandline;

import gov.nasa.pds.processors.DataDictionaryImportProcessor;
import gov.nasa.pds.services.DataSetProcessingException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * <p>
 * The <code>DataDictionaryCommandLine</code> class implements a command line utility for importing
 * the data dictionary that will be used for keywords, validation, etc. The sole and required utility's
 * parameter is the filename of the Spring configuration file.
 * </p>
 *
 * <strong>Thread Safety:</strong> This class is immutable and thread safe.
 *
 * @author KennyAlive
 * @version 1.0
 */
public class DataDictionaryCommandLine {
    /**
     * Do not allow to create instances of command line utility class.
     */
    private DataDictionaryCommandLine() {
        // Empty
    }

    /**
     * DataDictionaryCommandLine utility entry point. The sole and required command line argument is the
     * filename of the Spring configuration file.
     *
     * @param args command line arguments
     *
     * @throws BeansException
     *                  in case of loading/parsing errors of configuration file or if the bean could not be found
     * @throws DataSetProcessingException
     *                   if there is an error executing the engine
     */
    public static void main(String[] args) throws DataSetProcessingException {
        if (args.length < 1) {
            System.out.println("Usage: DataDictionaryCommandLine <configuration_xml_filename>");
            return;
        }

        ApplicationContext appContext = new FileSystemXmlApplicationContext(args[0]);
        DataDictionaryImportProcessor processor =
                (DataDictionaryImportProcessor) appContext.getBean("dataDictionaryImportProcessor");

        processor.importDataDictionary();
    }
}
