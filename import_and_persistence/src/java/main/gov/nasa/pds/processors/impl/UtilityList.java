/*
 * Copyright (C) 2012 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import com.topcoder.util.log.Level;

/**
 * <p>
 * The {@code UtilityList} class represents a utility for listing datasets available for processing with an option to
 * divide available datasets into groups.
 * </p>
 *
 * <p>
 * <strong>Thread Safety:</strong> This class is effectively thread safe
 * </p>
 *
 * @author KennyAlive
 * @version 1.0
 */
public class UtilityList extends AbstractDataSetUtility {
    /**
     * Constructs new {@code UtilityList} instance.
     */
    public UtilityList() {
        // Empty
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "list";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run(List<DataSetInfo> dataSets, List<String> args, Map<String, String> options) {
        // Parse arguments.
        int groupSize = 0;
        if (args.size() > 0) {
            String arg = args.get(0);
            try {
                groupSize = Integer.parseInt(arg);
            } catch (NumberFormatException e) {
                getLogger().log(Level.INFO, "Invalid argument value: {0}", arg);
                logUsage();
                return;
            }
        }
        if (groupSize < 0) {
            groupSize = 0;
        }

        // Write dataset names to the file.
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File("datasets.txt"));
            int groupNumber = 1;
            int n = 0;
            for (int i = 0; i < dataSets.size(); i++) {
                if (groupSize > 0 && n == 0) {
                    writer.println("---------------------------------------------------");
                    writer.println(String.format(" GROUP %d", groupNumber));
                    writer.println("---------------------------------------------------");
                }

                writer.println(String.format(" %d) %s", i + 1, dataSets.get(i).getDirectoryName()));

                if (groupSize > 0 && ++n == groupSize) {
                    n = 0;
                    groupNumber++;
                    writer.println("");
                }
            }
        } catch (FileNotFoundException e) {
            getLogger().log(Level.WARN, "Failed to create datasets.txt file");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * Logs the arguments usage of this utility.
     */
    private void logUsage() {
        getLogger().log(Level.INFO, "list utility arguments: [group_size]");
    }
}
