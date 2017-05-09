/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.commandline;

import gov.nasa.pds.processors.DataSetBatchProcessor;

import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * <p>
 * The {@code DataSetProcessingCommandLine} class implements command line utility for data sets processing. The
 * required utility's parameter is the filename of the Spring configuration file. The other parameters can also be
 * defined depending on the specific dataset processor.
 * </p>
 *
 * <strong>Thread Safety:</strong> This class is immutable and thread safe.
 *
 * @author KennyAlive
 * @version 1.0
 */
public class DataSetProcessingCommandLine {
    /**
     * Do not allow to create instances of command line utility class.
     */
    private DataSetProcessingCommandLine() {
        // Empty
    }

    /**
     * DataSetProcessingCommandLine utility entry point.
     *
     * @param args
     *            command line arguments
     *
     * @throws BeansException
     *             in case of loading/parsing errors of configuration file or if the bean could not be found
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: DataSetProcessingCommandLine <configuration_xml_filename> [param1, ...]");
            return;
        }

        ApplicationContext appContext = new FileSystemXmlApplicationContext(args[0]);
        DataSetBatchProcessor processor = (DataSetBatchProcessor) appContext.getBean("dataSetBatchProcessor");

        String[] args2 = Arrays.copyOfRange(args, 1, args.length);
        processor.processDataSets(args2);
    }
}
