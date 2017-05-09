/*
 * Copyright (C) 2012-2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl;

import gov.nasa.pds.entities.Column;
import gov.nasa.pds.entities.MetadataObject;
import gov.nasa.pds.entities.Product;
import gov.nasa.pds.entities.Property;
import gov.nasa.pds.entities.Table;
import gov.nasa.pds.services.DataSetProcessingException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.topcoder.commons.utils.LoggingWrapperUtility;
import com.topcoder.util.log.Level;
import com.topcoder.util.log.Log;

/**
 * <p>
 * The {@code Helper} class provides utility methods for classes from gov.nasa.pds.processors.impl package.
 * </p>
 * 
 * <p>
 * <strong>Thread Safety:</strong> This class is thread safe since it has no state.
 * </p>
 * 
 * <p>
 * Version 1.1 changes [PDS - Import and Persistence Update - Assembly Contest]:
 * <ol>
 * <li>Added {@link #getRowItemsCount(Table)} method.</li>
 * <li>Added {@link #readFileLines(InputStream)} method.</li>
 * <li>Moved {@link #parseTableRow(String, int)} method from DataSetTemplate class.</li>
 * </ol>
 * </p>
 * 
 * @author KennyAlive
 * @version 1.1
 */
public final class Helper {
    /**
     * Do not allow to instantiate this class.
     */
    private Helper() {
    }

    /**
     * Gets all files from the given directory and all its sub-directories.
     * 
     * @param directory
     *            the directory to scan for files and sub-directories
     * @param result
     *            the resulted list with the found files
     */
    public static void listFiles(File directory, List<File> result) {
        File[] directoryFiles = directory.listFiles();
        List<File> subDirectories = new ArrayList<File>();

        // at first get the files from the current directory
        for (File file : directoryFiles) {
            if (file.isFile()) {
                result.add(file);
            } else {
                subDirectories.add(file);
            }
        }
        // add files from sub-directories only after the files from the current directory
        // were added in order to not intermix files from different directories
        for (File subDirectory : subDirectories) {
            listFiles(subDirectory, result);
        }
    }

    /**
     * Gets the name of the given file or directory without extension.
     *
     * @param fileName
     *            represents file path or directory path or just file name or directory name
     *
     * @return name of the file or directory without extension
     */
    public static String getNameWithoutExtension(String fileName) {
        String name = (new File(fileName)).getName();
        int dotIndex = name.lastIndexOf('.');
        if (dotIndex != -1) {
            name = name.substring(0, dotIndex);
        }
        return name;
    }

    /**
     * Reads file in text mode line-by-line.
     * 
     * @param stream
     *            the input stream
     * 
     * @return the list of lines that is the file content
     * 
     * @throws IOException
     *             if failed to read file content
     * 
     * @since 1.1
     */
    public static List<String> readFileLines(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        List<String> lines = new ArrayList<String>();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } finally {
            reader.close();
        }
        return lines;
    }

    /**
     * Does heuristic check if the given file is a text file.
     *
     * @param file
     *            the file to check
     *
     * @return true if the given file is probably a text file, otherwise false
     */
    public static boolean isProbablyTextFile(File file) {
        try {
            if (!file.exists())
                return false;

            FileInputStream in = new FileInputStream(file);
            int size = in.available();
            if (size > 1000)
                size = 1000;
            byte[] data = new byte[size];
            in.read(data);
            in.close();
            String s = new String(data, "ISO-8859-1");
            String s2 = s.replaceAll(
                    "[a-zA-Z0-9\\.\\*!\"\\$\\%&/()=\\?@~'#:,;\\+><\\|\\[\\]\\{\\}\\^\\\\ \\n\\r\\t_\\-]", "");
            // will delete all text signs
            double d = (double) (s.length() - s2.length()) / (double) (s.length());
            // percentage of text signs in the text
            return d > 0.95;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Gets timing string for the time period between the two given dates.
     *
     * @param startDate
     *            the start date
     * @param endDate
     *            the end date
     *
     * @return the string with timing info between the two given dates
     */
    public static String getTimingString(Date startDate, Date endDate) {
        long seconds = (new Date().getTime() - startDate.getTime()) / 1000;
        long hours = seconds / 3600;
        seconds = seconds % 3600;
        long minutes = seconds / 60;
        seconds = seconds % 60;

        String timeStr;
        if (hours > 0) {
            timeStr = String.format("%d hours, %d minute(s), %d seconds", hours, minutes, seconds);
        } else if (minutes > 0) {
            timeStr = String.format("%d minute(s), %d seconds", minutes, seconds);
        } else {
            timeStr = String.format("%d seconds", seconds);
        }
        return timeStr;
    }

    /**
     * Gets the data offset in bytes defined by the given pointer property or -1 if the property does not define the
     * offset.
     * 
     * @param pointerProperty
     *            the pointer property
     * @param recordSizeInBytes
     *            the record size in bytes (it's used only when offset value does not have <BYTES> suffix)
     * @param logger
     *            the logger
     * 
     * @return the data offset or -1 if it is not defined
     */
    public static int getDataOffset(Property pointerProperty, int recordSizeInBytes, Log logger)
            throws DataSetProcessingException {
        String signature = "Helper.getDataOffset(Property pointerProperty, int recordSizeInBytes, Log logger)";

        String pointerValue = pointerProperty.getValues().get(0).toLowerCase();

        int dataOffset = computeOffsetInBytes(pointerValue, recordSizeInBytes);
        if (dataOffset == -1) { // if label is detached
            if (pointerProperty.getValues().size() > 1) { // check if offset is specified after the filename
                String pointerValue2 = pointerProperty.getValues().get(1);
                dataOffset = computeOffsetInBytes(pointerValue2, recordSizeInBytes);
                if (dataOffset == -1) {
                    throw LoggingWrapperUtility.logException(logger, signature,
                            new DataSetProcessingException("Invalid offset format: " + pointerValue2));
                }
            }
        }
        return dataOffset;
    }

    /**
     * Checks if the data defined by the given pointer property is attached to the label file.
     * 
     * @param pointerProperty
     *            the pointer property
     * @param recordSizeInBytes
     *            the record size in bytes (it's used only when offset value does not have <BYTES> suffix)
     * 
     * @return true if the data is attached to the label file, otherwise false
     */
    public static boolean isDataAttached(Property pointerProperty, int recordSizeInBytes) {
        String pointerValue = pointerProperty.getValues().get(0).toLowerCase();
        int offset = Helper.computeOffsetInBytes(pointerValue, recordSizeInBytes);
        return offset > -1;
    }

    /**
     * Computes offset in bytes for the given offset value.
     * 
     * @param offsetValue
     *            the offset value as specified in the label file
     * @param recordSizeInBytes
     *            the record size in bytes (it's used only when offset value does not have <BYTES> suffix)
     * 
     * @return the offset in bytes or -1 if fails to parse offset value
     */
    public static int computeOffsetInBytes(String offsetValue, int recordSizeInBytes) {
        final String BYTES_SUFFIX = "<BYTES>";

        boolean isBytesOffset = false;
        if (offsetValue.toUpperCase().endsWith(BYTES_SUFFIX)) {
            int endPos = offsetValue.length() - BYTES_SUFFIX.length();
            offsetValue = offsetValue.substring(0, endPos).trim();
            isBytesOffset = true;
        }

        int offset = 0;
        try {
            offset = Integer.valueOf(offsetValue);
            if (!isBytesOffset) {
                offset = (offset - 1) * recordSizeInBytes;
            } else {
                offset = offset - 1;
            }
        } catch (NumberFormatException e) {
            offset = -1;
        }
        return offset;
    }

    /**
     * Checks if the property with a given name is a pointer.
     *
     * @param propertyName
     *            the property name
     *
     * @return true if the property is a pointer, otherwise false
     */
    public static boolean isPointer(String propertyName) {
        return propertyName.startsWith("^");
    }

    /**
     * Gets a short name of the pointer represented by a property with a given name.
     *
     * @param propertyName
     *            the property name
     *
     * @return the pointer short name or null if the property does not represent a pointer
     */
    public static String getPointerShortName(String propertyName) {
        if (!isPointer(propertyName)) {
            return null;
        }
        int underscoreIndex = propertyName.lastIndexOf('_');
        if (underscoreIndex != -1) {
            propertyName = propertyName.substring(underscoreIndex + 1);
        } else {
            propertyName = propertyName.substring(1); // skip leading ^ symbol
        }
        return propertyName;
    }

    /**
     * Check if the given property represents a pointer to the data table.
     *
     * @param propertyName
     *            the property name
     *
     * @return true if the property represents a pointer to the data table, otherwise false
     */
    public static boolean isTablePointer(String propertyName) {
        String shortName = getPointerShortName(propertyName);
        return shortName != null && (shortName.equals("TABLE") || shortName.equals("SERIES"));
    }

    /**
     * Retrieves all children of the metadata object with a given name.
     *
     * @param object
     *            the metadata object to retrieve children from
     * @param childObjectName
     *            the name of the child objects we are looking for
     *
     * @return the children objects with a given name, the empty list is returned if none is found
     */
    public static List<MetadataObject> getChildrenByName(MetadataObject object, String childObjectName) {
        List<MetadataObject> childObjects = new ArrayList<MetadataObject>();
        for (MetadataObject child : object.getChildren()) {
            if (child.getName().equals(childObjectName)) {
                childObjects.add(child);
            }
        }
        return childObjects;
    }

    /**
     * Retrieves the first child metadata object with a given name.
     * 
     * @param object
     *            the metadata object to retrieve child from
     * @param childObjectName
     *            the name of the child object we are looking for
     * @param logger
     *            the logger instance
     * 
     * @return the first child metadata object with a given name
     * 
     * @throws DataSetProcessingException
     *             if the child object is not found
     */
    public static MetadataObject getRequiredChildObject(MetadataObject object, String childObjectName, Log logger)
            throws DataSetProcessingException {
        final String signature = "Helper.getRequiredChildObject(String childObjectName, Log logger)";

        List<MetadataObject> children = getChildrenByName(object, childObjectName);
        if (children.isEmpty()) {
            String message = String.format("Failed to find a child object (%s) in parent (%s) object",
                    childObjectName, object.getName());
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(message));
        }
        return children.get(0);
    }

    /**
     * Gets the object from product by its name. The full object name is used for comparison.
     *
     * @param product
     *            the product that holds the object of interest
     * @param objectName
     *            the object name we are looking for
     *
     * @return found object or null if none found
     */
    public static MetadataObject getObjectFromProduct(Product product, String objectName) {
        for (MetadataObject object : product.getOtherChildren()) {
            if (object.getFullName().equals(objectName)) {
                return object;
            }
        }
        return null;
    }

    /**
     * Returns the extension of the given file without leading dot symbol.
     * 
     * @param fileName
     *            the file name (can also be file path) to extract extension from
     * 
     * @return the extension of the given file or an empty string if the file has no extension
     */
    public static String getFileExtension(String fileName) {
        fileName = new File(fileName).getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            return "";
        }
        return fileName.substring(dotIndex + 1);
    }

    /**
     * Gets column name that is defined by the PDS table and converts it to the name that is a valid SQL column name.
     * 
     * @param identifier
     *            the identifier that should be converted to SQL-compliant identifier
     * 
     * @return the corrected column name
     */
    public static String getSqlIdentifier(String identifier) {
        identifier = identifier.replace(' ', '_');
        identifier = identifier.replace('-', '_');
        identifier = identifier.replace('(', '_');
        identifier = identifier.replace(')', '_');
        identifier = identifier.replace('.', '_');
        return identifier;
    }

    /**
     * Get the list of unique names by appending indices to non-unique names.
     * 
     * @param names
     *           the original list of names
     * 
     * @return the processed list of unique names
     */
    public static List<String> getUniqueNames(List<String> names) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<String> result = new ArrayList<String>();

        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            Integer index = map.get(name);
            if (index != null) {
                index++;
                while (names.contains(name + index)) {
                    index++;
                }
                map.put(name, index);
                name = name + index;
            } else {
                map.put(name, 1);
            }
            result.add(name);
        }
        return result;
    }

    /**
     * Computes the number of items per row for the given table.
     * 
     * @param table
     *            the table instance
     * 
     * @return the number of items per row
     * 
     * @since 1.1
     */
    public static int getRowItemsCount(Table table) {
        int items = 0;
        for (Column column : table.getColumns()) {
            items += (column.getItems() == null) ? 1 : column.getItems();
        }
        return items;
    }

    /**
     * Selects the datasets for processing.
     * 
     * @param dataSetSelector
     *            the dataset selector instance
     * @param startDataSet
     *            the dataset to start processing from
     * @param endDataSet
     *            the last dataset for processing
     * @param logger
     *            the logger instance for logging (can be null)
     * 
     * @return the list of selected datasets (not null, can be empty)
     */
    public static List<DataSetInfo> selectDataSets(DataSetSelector dataSetSelector, String startDataSet,
            String endDataSet, Log logger) {
        Date startDate = new Date();

        // 1. Get configured list of datasets from DataSetSelector instance.
        List<DataSetInfo> selectedDataSets = dataSetSelector.selectDataSetsForProcessing();

        int start = 0;
        int end = selectedDataSets.size() - 1;

        // 2. Take into account startDataSet/endDataSet properties.
        if (startDataSet != null || endDataSet != null) {
            boolean startFound = false;
            boolean endFound = false;

            // locate start/end datasets
            for (int i = 0; i < selectedDataSets.size(); i++) {
                String directoryName = selectedDataSets.get(i).getDirectoryName();
                if (startDataSet != null && directoryName.equals(startDataSet)) {
                    start = i;
                    startFound = true;
                }
                if (endDataSet != null && directoryName.equals(endDataSet)) {
                    end = i;
                    endFound = true;
                }
            }

            // validate start/end datasets
            if (startDataSet != null) {
                if (!startFound) {
                    if (logger != null) {
                        logger.log(Level.ERROR, "startDataSet {0} not found", startDataSet);
                    }
                    return null;
                }
                if (logger != null) {
                    logger.log(Level.INFO, "startDataSet = {0} ({1})", startDataSet, start + 1);
                }
            }

            if (endDataSet != null) {
                if (!endFound) {
                    if (logger != null) {
                        logger.log(Level.ERROR, "endDataSet {0} not found", endDataSet);
                    }
                    return null;
                }
                if (logger != null) {
                    logger.log(Level.INFO, "endDataSet = {0} ({1})", endDataSet, end + 1);
                }
            }

            if (start > end) {
                if (logger != null) {
                    logger.log(Level.ERROR, "Invalid startDataSet or/and endDataSet. "
                            + "Check that startDataSet goes before or equals to endDataSet.");
                }
                return null;
            }
        }

        // 3. Log summary
        if (logger != null) {
            logger.log(Level.INFO, "Selected {0} dataset(s) for processing (in {1})",
                    end - start + 1, Helper.getTimingString(startDate, new Date()));

            StringBuilder sb = new StringBuilder();
            for (int i = start; i <= end; i++) {
                sb.append("\n " + (i + 1) + ") ");
                sb.append(selectedDataSets.get(i).getDirectoryName());
            }
            logger.log(Level.INFO, "Selected datasets: {0}", sb.toString());
            logger.log(Level.INFO, "----------------------------------------");
        }

        return selectedDataSets.subList(start, end + 1);
    }

    /**
     * Parses a single row of values. Used to parse index.tab file, Cassini inventory table, etc.
     * 
     * @param row
     *            the row to parse
     * @param rowNumber
     *            the row number, used for logging
     * 
     * @return the list of parsed column values
     * 
     * @throws DataSetProcessingException
     *             if failed to parse the given row
     * 
     * @since 1.1
     */
    public static List<String> parseTableRow(String row, int rowNumber) throws DataSetProcessingException {
        List<String> values = new ArrayList<String>();
        int i = 0;
        while (true) {
            // skip column separators
            while (i < row.length() && row.charAt(i) <= 32) {
                i++;
            }
            if (i != 0 && i < row.length() && row.charAt(i) == ',') {
                i++;
                while (i < row.length() && row.charAt(i) <= 32) {
                    i++;
                }
            }

            if (i == row.length()) {
                break;
            }

            // check if the next value is a quoted string
            boolean quote = false;
            boolean doubleQuote = false; // fix for the values like this: ""N/A""
            if (row.charAt(i) == '"') {
                quote = true;
                if (i < row.length() - 1 && row.charAt(i + 1) == '"') {
                    doubleQuote = true;
                    i++;
                }
            } else if (row.charAt(i) == ',') {
                throw new DataSetProcessingException("Malformed line: superfluous comma at position " + i + ", row "
                        + rowNumber);
            }

            // mark start position of the next column value
            int beginIndex = i;

            // search for the end of the column value
            i++;
            String value = null;
            if (quote) {
                while (i < row.length() && row.charAt(i) != '"') {
                    i++;
                }
                boolean missedDoubleQuote = doubleQuote && i < row.length() - 1 && row.charAt(i + 1) != '"';
                if (i == row.length() || missedDoubleQuote) {
                    throw new DataSetProcessingException(
                            "Malformed line: missing closed quote for opening quote at position " + beginIndex
                                    + ", row " + rowNumber);
                }
                value = row.substring(beginIndex + 1, i).trim();
                if (doubleQuote) {
                    i++;
                }
                i++;
                if (i < row.length() && !(row.charAt(i) <= 32 || row.charAt(i) == ',')) {
                    throw new DataSetProcessingException(
                            "Malformed line: unexpected symbol after ending quote  at position " + i + ", row "
                                    + rowNumber);
                }
            } else {
                while (i < row.length() && (row.charAt(i) > 32 && row.charAt(i) != ',')) {
                    i++;
                }
                value = row.substring(beginIndex, i);
            }

            // add the column value to result list
            values.add(value);
        }

        return values;
    }
}
