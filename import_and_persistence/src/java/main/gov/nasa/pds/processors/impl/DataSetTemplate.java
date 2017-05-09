/*
 * Copyright (C) 2012-2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl;

import gov.nasa.pds.entities.Column;
import gov.nasa.pds.entities.MetadataFile;
import gov.nasa.pds.entities.MetadataObject;
import gov.nasa.pds.entities.Property;
import gov.nasa.pds.entities.Table;
import gov.nasa.pds.services.DataSetFiles;
import gov.nasa.pds.services.DataSetProcessingException;
import gov.nasa.pds.services.MetadataFileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.topcoder.commons.utils.LoggingWrapperUtility;
import com.topcoder.util.log.Level;
import com.topcoder.util.log.Log;

/**
 * <p>
 * The {@code DataSetTemplate} class provides methods that simplify access to dataset's data.
 * </p>
 * 
 * <p>
 * <strong>Thread Safety:</strong> This class is thread safe.
 * </p>
 * 
 * <p>
 * Version 1.1 changes [PDS - Import and Persistence Update - Assembly Contest]:
 * <ol>
 * <li>Fixed getLabelsFilenames method: take into account that column can contain multiple items.</li>
 * <li>Moved parseTableRow method into helper class for re-use purposes.</li>
 * </ol>
 * </p>
 * 
 * @author KennyAlive
 * @version 1.1
 */
class DataSetTemplate {
    /**
     * Constant for the class name of this class. Used for logging.
     */
    private static final String CLASS_NAME = DataSetTemplate.class.getName();

    /**
     * The relative path to the index.lbl file.
     */
    private static final String INDEX_LBL_FILEPATH = "index/index.lbl";

    /**
     * The relative path to the index.tab file.
     */
    private static final String INDEX_TAB_FILEPATH = "index/index.tab";

    /**
     * The path to the dataset's root directory.
     */
    private final String dataSetPath;

    /**
     * The reader of the metadata files.
     */
    private final MetadataFileReader reader;

    /**
     * The DataSetFiles instance to get information about dataset files. Initialized in constructor. Can not be null.
     */
    private final DataSetFiles dataSetFiles;

    /**
     * The logger instance used for logging.
     */
    private final Log logger;

    /**
     * Creates an instance of {@code DataSetTemplate}.
     */
    public DataSetTemplate(String dataSetPath, MetadataFileReader reader, DataSetFiles dataSetFiles, Log logger) {
        this.dataSetPath = dataSetPath;
        this.reader = reader;
        this.dataSetFiles = dataSetFiles;
        this.logger = logger;
    }

    /**
     * Reads the list of label file paths from the index table.
     *
     * @return the list of file paths to the label files
     *
     * @throws DataSetProcessingException
     *             if any error occurs
     */
    public List<String> readDataProductLabelsFromIndex() throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".readDataProductLabelsFromIndex()";

        // read index label
        File indexLbl = new File(dataSetPath, INDEX_LBL_FILEPATH);

        if (dataSetFiles.getRealFilePath(INDEX_LBL_FILEPATH) == null) {
            logger.log(Level.INFO, "Found documentation-only dataset (index.lbl does not exist): {0}", dataSetPath);
            return new ArrayList<String>();
        }

        MetadataFile metadataFile = reader.readMetadataInfo(indexLbl.getPath(), dataSetFiles);

        // read index table
        MetadataObject metadataObject = Helper.getRequiredChildObject(metadataFile, "TABLE", logger);
        Table indexTable = new Table();
        indexTable.fromMetadata(metadataObject);

        // check if offset is defined for index table
        int offsetInLines = 0;
        for (Property property : metadataFile.getProperties()) {
            String propertyName = property.getName();
            if (propertyName.startsWith("^") && propertyName.endsWith("TABLE")) {
                if (property.getValues().size() > 1) {
                    int offset = Helper.computeOffsetInBytes(property.getValues().get(1), indexTable.getRowByteSize());
                    // assume this
                    if (offset % indexTable.getRowByteSize() != 0) {
                        throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                                "Incorrect offset value"));
                    }
                    offsetInLines = offset / indexTable.getRowByteSize();
                }
                break;
            }
        }

        // read filenames
        File indexTab = new File(dataSetPath, INDEX_TAB_FILEPATH);
        return getLabelsFilenames(indexTab.getPath(), indexTable, offsetInLines);
    }

    /**
     * Retrieves associated label files from a given index.tab file.
     *
     * @param indexTabFilename
     *            the full filename of the index.tab that should be processed
     * @param indexTable
     *            the index table that defines control parameters
     * @param offsetInLines
     *            the number of lines that should be skipped in index.tab
     * @param log
     *            the logger instance for logging
     *
     * @return the list of filenames of the associated label files
     *
     * @throws DataSetProcessingException
     *             if there is any error
     */
    private List<String> getLabelsFilenames(String indexTabFilename, Table indexTable, int offsetInLines)
            throws DataSetProcessingException {
        final String signature = CLASS_NAME
                + ".getLabelsFilenames(String indexTabFilename, Table indexTable, int offsetInLines)";

        final String FILE_SPECIFICATION_NAME_KEYWORD = "FILE_SPECIFICATION_NAME";
        final String PATH_NAME_KEYWORD = "PATH_NAME";
        final String FILE_NAME_KEYWORD = "FILE_NAME";

        boolean hasFileSpecificationName = false;
        boolean hasPathName = false;
        boolean hasFileName = false;

        int index = -1;
        int index2 = -1;

        int itemIndex = 0;
        for (Column column : indexTable.getColumns()) {
            if (column.getName().equals(FILE_SPECIFICATION_NAME_KEYWORD)) {
                hasFileSpecificationName = true;
                index = itemIndex;
            } else if (column.getName().equals(PATH_NAME_KEYWORD)) {
                hasPathName = true;
                index = itemIndex;
            } else if (column.getName().equals(FILE_NAME_KEYWORD)) {
                hasFileName = true;
                index2 = itemIndex;
            }
            itemIndex += (column.getItems() == null) ? 1 : column.getItems();
        }

        boolean singleColumnFilepath = hasFileSpecificationName;
        boolean twoColumnsFilepath = hasPathName && hasFileName;

        if (!singleColumnFilepath && !twoColumnsFilepath) {
            String message = "Filepath of the label file is not defined";
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(message));
        }

        if (singleColumnFilepath && twoColumnsFilepath) {
            String message = "Both FILE_SPECIFICATION_NAME and (PATH_NAME, FILE_NAME) are provided in the index file";
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(message));
        }

        // open index.tab and read it line by line
        List<String> filenames = new ArrayList<String>();
        Set<String> set = new HashSet<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(indexTabFilename));

            try {
                // skip offset if necessary
                for (int i = 0; i < offsetInLines; i++) {
                    reader.readLine();
                }
                // read file line by line
                int rowCount = 0;
                String line = reader.readLine();
                while (line != null) {
                    if (line.trim().isEmpty()) {
                        line = reader.readLine();
                        continue;
                    }
                    List<String> values = Helper.parseTableRow(line, rowCount + 1);

                    if (values.size() != Helper.getRowItemsCount(indexTable)) {
                        throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                                String.format("Invalid '%s', row %d is malformed", indexTabFilename, rowCount + 1)));
                    }

                    // get filename of the label file
                    String filename;
                    if (singleColumnFilepath) {
                        filename = values.get(index);
                    } else {
                        filename = new File(values.get(index), values.get(index2)).getPath();
                    }
                    // ensure that filename is unique
                    if (!set.contains(filename)) {
                        set.add(filename);
                        filenames.add(filename);
                    }

                    line = reader.readLine();
                    rowCount++;
                }
                if (rowCount != indexTable.getRowCount()) {
                    String message = String.format("Rows count mismatch: index.lbl (%d), index.tab (%d)",
                            indexTable.getRowCount(), rowCount);
                    logger.log(Level.WARN, message);
                }
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            String message = String.format("Failed to read index.tab file: '%s'", indexTabFilename);
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(message, e));
        }
        return filenames;
    }
}
