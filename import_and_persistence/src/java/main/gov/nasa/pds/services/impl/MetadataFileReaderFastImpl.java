/*
 * Copyright (C) 2012-2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services.impl;

import gov.nasa.pds.entities.MetadataFile;
import gov.nasa.pds.entities.MetadataObject;
import gov.nasa.pds.entities.Property;
import gov.nasa.pds.entities.SequenceValueProperty;
import gov.nasa.pds.services.DataSetFiles;
import gov.nasa.pds.services.DataSetProcessingConfigurationException;
import gov.nasa.pds.services.DataSetProcessingException;
import gov.nasa.pds.services.MetadataFileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.InitializingBean;

import com.topcoder.commons.utils.LoggingWrapperUtility;
import com.topcoder.commons.utils.ValidationUtility;
import com.topcoder.util.log.Level;
import com.topcoder.util.log.Log;

/**
 * <p>
 * The {@code MetadataFileReaderFastImpl} class implements {@code MetadataFileReader} interface by providing
 * implementation of {@code readMetadataInfo(String)} method.
 * </p>
 * 
 * <strong>Thread Safety:</strong> This class is mutable since it provides public setter for the logger. But it
 * doesn't change its state and is thread safe when the following conditions are met: this class is initialized by
 * Spring right after construction and its parameters are never changed after that, all entities passed to this class
 * are used by the caller in thread safe manner (accessed from a single thread only).
 * 
 * <p>
 * Version 1.1 changes [PDS - Import and Persistence Update - Assembly Contest]:
 * <ol>
 * <li>Added support for ^STRUCTURE pointers with prefixes, like this: ^LINE_PREFIX_STRUCTURE.</li>
 * <li>Added support for description pointers.</li>
 * </ol>
 * </p>
 * 
 * @author KennyAlive
 * @version 1.1
 */
public class MetadataFileReaderFastImpl implements MetadataFileReader, InitializingBean {
    /**
     * Constant for the class name of this class. Used for logging.
     */
    private static final String CLASS_NAME = MetadataFileReaderFastImpl.class.getName();

    /**
     * Constant for line separator
     */
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * Ignore all objects and properties definitions which contain this word.
     */
    private static final String SFDU_PATTERN = "SFDU_";

    // ---- Keywords and suffixes which indicates that whitespace should be preserved ----
    /**
     * Constant for _NOTE suffix.
     */
    private static final String WHITESPACE_PRESERVE_NOTE_SUFFIX = "_NOTE";
    /**
     * Constant for _DESC suffix.
     */
    private static final String WHITESPACE_PRESERVE_DESC_SUFFIX = "_DESC";
    /**
     * Constant for _DESCRIPTION suffix.
     */
    private static final String WHITESPACE_PRESERVE_DESCRIPTION_SUFFIX = "_DESCRIPTION";
    /**
     * Constant for NOTE keyword.
     */
    private static final String WHITESPACE_PRESERVE_NOTE_KEYWORD = "NOTE";
    /**
     * Constant for DESCRIPTION keyword.
     */
    private static final String WHITESPACE_PRESERVE_DESCRIPTION_KEYWORD = "DESCRIPTION";

    // --- Prefix should be ignored for object names that end with these suffixes -----
    /**
     * Constant for _TABLE suffix
     */
    private static final String IGNORE_PREFIX_INDICATOR_TABLE = "_TABLE";
    /**
     * Constant for _SERIES suffix
     */
    private static final String IGNORE_PREFIX_INDICATOR_SERIES = "_SERIES";
    /**
     * Constant for _LABEL suffix
     */
    private static final String IGNORE_PREFIX_INDICATOR_LABEL = "_LABEL";
    /**
     * Constant for _TEXT suffix
     */
    private static final String IGNORE_PREFIX_INDICATOR_TEXT = "_TEXT";
    /**
     * Constant for _HEADER suffix
     */
    private static final String IGNORE_PREFIX_INDICATOR_HEADER = "_HEADER";
    /**
     * Constant for _IMAGE suffix
     */
    private static final String IGNORE_PREFIX_INDICATOR_IMAGE = "_IMAGE";


    /**
     * This set contains pointer names which refer to files that should be included
     * while parsing the main file.
     */
    private static final Set<String> INCLUDE_POINTERS_NAMES;

    /**
     * Initializes <code>INCLUDE_POINTERS_NAMES</code> set.
     */
    static {
        Set<String> set = new HashSet<String>();
        set.add("^STRUCTURE");
        set.add("^CATALOG");
        set.add("^DATA_SET_MAP_PROJECTION");
        INCLUDE_POINTERS_NAMES = Collections.unmodifiableSet(set);
    }

    /**
     * Compiled regular expression for floating point number.
     */
    private static final Pattern floatingPointNumberRegex = Helper.createFloatingPointRegex();

    /**
     * The {@code Log} instance used for logging. It is initialized with Spring setter dependency injection. Cannot be
     * {@code null} after initialization. Has a setter.
     */
    private Log logger;

    /**
     * If true, then END token is ignored and everything after it is parsed. If false, then END token finishes parsing
     * process. It is initialized with Spring setter dependency injection. Default value {@code false}. Has a setter.
     */
    private boolean ignoreEndToken;

    /**
     * The {@code DataSetFiles} instance. Initialized in {@code readMetadataInfo} methods. Can be null if parser is
     * used to parse a metadata file that does not belong to any dataset.
     */
    private DataSetFiles dataSetFiles;

    /**
     * Sets the logger.
     *
     * @param logger
     *              the logger to set
     */
    public void setLogger(Log logger) {
        this.logger = logger;
    }

    /**
     * Sets the ignoreEndToken.
     * 
     * @param ignoreEndToken
     *              the ignoreEndToken to set
     */
    public void setIgnoreEndToken(boolean ignoreEndToken) {
        this.ignoreEndToken = ignoreEndToken;
    }

    /**
     * Creates an instance of {@code MetadataFileReaderImpl}.
     */
    public MetadataFileReaderFastImpl() {
        // Empty
    }

    /**
     * Checks whether this class was initialized by Spring properly.
     *
     * Required parameters:
     * <ul>
     * <li>logger</li>
     * </ul>
     *
     * @throws DataSetProcessingConfigurationException
     *            if the class was not initialized properly
     */
    @Override
    public void afterPropertiesSet() {
        ValidationUtility.checkNotNull(logger, "logger", DataSetProcessingConfigurationException.class);
    }

    /**
     * Reads the metadata from the given file.
     *
     * @param filePath
     *            the file path of the file with the metadata
     * @param dataSetFiles
     *            the DataSetFiles instance to resolve file names. Can be null.
     *
     * @return the parsed metadata file object
     *
     * @throws DataSetProcessingException
     *             if there is an error while parsing the file
     */
    @Override
    public MetadataFile readMetadataInfo(String filePath, DataSetFiles dataSetFiles) throws DataSetProcessingException {
        String signature = CLASS_NAME + ".readMetadataInfo(String filePath, DataSetFiles dataSetFiles)";

        LoggingWrapperUtility.logEntrance(logger, signature,
                new String[] { "filePath", "dataSetFiles" },
                new Object[] { filePath, dataSetFiles });

        this.dataSetFiles = dataSetFiles;

        // initialize parser context
        ParserContext context = null;
        try {
            if (dataSetFiles != null) {
                filePath = dataSetFiles.getRealAbsoluteFilePath(filePath);
            }
            context = new ParserContext(filePath, logger);
        } catch (FileNotFoundException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Failed to open metadata file", e));
        }

        // parse metadata
        MetadataFile metadataFile = readMetadataInfoInternal(context);

        // Do this check since metadataFile.toJSONString() is quite heavy.
        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logExit(logger, signature, new Object[] { metadataFile.toJSONString() });
        }
        return metadataFile;
    }

    /**
     * Reads the metadata from the given input stream.
     *
     * @param inputStream
     *            the input stream to read metadata from
     * @param bytesToRead
     *            the amount of bytes to read from input steam
     * @param filePath
     *            the file path (used only for logging, real data is streamed from inputStream)
     * @param dataSetFiles
     *            the DataSetFiles instance to resolve file names
     *
     * @throws DataSetProcessingException
     *             if there is any error while parsing the file
     *
     * @return the parsed metadata file object
     */
    @Override
    public MetadataFile readMetadataInfo(InputStream inputStream, long bytesToRead, String filePath,
            DataSetFiles dataSetFiles) throws DataSetProcessingException {
        String signature = CLASS_NAME + ".readMetadataInfo(InputStream inputStream, ...)";

        LoggingWrapperUtility.logEntrance(logger, signature,
                new String[] { "inputStream", "bytesToRead", "filePath", "dataSetFiles" },
                new Object[] { inputStream, bytesToRead, filePath, dataSetFiles });

        this.dataSetFiles = dataSetFiles;

        // initialize parser context
        if (dataSetFiles != null) {
            filePath = dataSetFiles.getRealAbsoluteFilePath(filePath);
        }
        ParserContext context = new ParserContext(inputStream, filePath, (int) bytesToRead, logger);

        // parse metadata
        MetadataFile metadataFile = readMetadataInfoInternal(context);

        // Do this check since metadataFile.toJSONString() is quite heavy.
        if (logger.isEnabled(Level.DEBUG)) {
            LoggingWrapperUtility.logExit(logger, signature, new Object[] { metadataFile.toJSONString() });
        }
        return metadataFile;
    }

    /**
     * Implementation of the metadata reading algorithm.
     *
     * @param context
     *            the parser context
     *
     * @return the metadata file object
     *
     * @throws DataSetProcessingException
     *             if there is an error while parsing the file
     */
    private MetadataFile readMetadataInfoInternal(ParserContext context) throws DataSetProcessingException {
        // prepare containers for parsed data
        List<MetadataObject> metadataObjects = new ArrayList<MetadataObject>();

        List<Property> properties = new ArrayList<Property>();

        // parse objects and properties definitions by reading input file line by line
        boolean firstLine = true;
        while (context.readNextNotEmptyLine()) {
            // ignore the first line if it starts with CCSD prefix
            if (firstLine && context.getCurrentLine().startsWith("CCSD")) {
                firstLine = false;
                continue;
            }
            firstLine = false;
            // END keyword marks the end of PDS label
            if (context.getCurrentLine().trim().equals("END")) {
                if (ignoreEndToken) {
                    continue;
                } else {
                    break;
                }
            }

            String token = context.peekNextToken();
            if (token != null && token.equals("OBJECT")) {
                context.skipMatch();
                MetadataObject metadataObject = parseMetadataObject(context);
                // parseMetadataObject returns null if the object is ignored (contains a property with IGNORE_PATTERN)
                if (metadataObject != null) {
                    metadataObjects.add(metadataObject);
                }
            } else {
                Property property = parseProperty(context);
                // parseProperty returns null if the property is ignored (it contains IGNORE_PATTERN)
                if (property != null) {
                    if (isIncludePointer(property.getName())) {
                        processIncludeProperty(context, property, metadataObjects, properties);
                    } else if (isDescriptionPointer(property.getName())) {
                        processDescriptionProperty(context, property, properties);
                    } else {
                        properties.add(property);
                    }
                }
            }
        }

        context.close();

        // create MetadataFile instance and initialize it with collected data
        MetadataFile metadataFile = new MetadataFile(context.getFileName());
        metadataFile.setChildren(metadataObjects);
        metadataFile.setProperties(properties);
        return metadataFile;
    }

    /**
     * Parses object definition.
     *
     * @param context
     *              the parser context
     *
     * @return the metadata object
     *
     * @throws DataSetProcessingException
     *              if there is an error while parsing the file
     */
    private MetadataObject parseMetadataObject(ParserContext context) throws DataSetProcessingException {
        // parse equals sign token and object name
        parseEqualSign(context);
        String objectName = parseObjectName(context);

        // prepare containers for parsed data
        List<MetadataObject> childObjects = new ArrayList<MetadataObject>();
        List<Property> childProperties = new ArrayList<Property>();

        boolean ignoreThisObject = false; // object is ignored when one of its properties is ignored

        // parse sub-objects/properties of the current object
        while (context.readNextNotEmptyLine()) {
            String token = context.peekNextToken();

            // check for end-of-object mark
            if (token != null && token.equals("END_OBJECT")) {
                context.skipMatch();
                validateEndOfObject(context, objectName);
                break;
            }

            if (token != null && token.equals("OBJECT")) {
                context.skipMatch();
                MetadataObject childObject = parseMetadataObject(context);
                // parseMetadataObject returns null if the object is ignored (contains a property with IGNORE_PATTERN)
                if (childObject != null) {
                    childObjects.add(childObject);
                }
            } else { // 2. Parse property
                Property childProperty = parseProperty(context);
                // parseProperty returns null if the property is ignored (it contains IGNORE_PATTER)
                if (childProperty != null) {
                    if (isIncludePointer(childProperty.getName())) {
                        processIncludeProperty(context, childProperty, childObjects, childProperties);
                    } else if (isDescriptionPointer(childProperty.getName())) {
                        processDescriptionProperty(context, childProperty, childProperties);
                    } else {
                        childProperties.add(childProperty);
                    }
                } else {
                    ignoreThisObject = true;
                }
            }
        }

        // skip optional prefix
        String fullName = objectName;
        int underscoreIndex = objectName.lastIndexOf('_');
        if (underscoreIndex != -1) {
            if (objectName.endsWith(IGNORE_PREFIX_INDICATOR_TABLE)
                    || objectName.endsWith(IGNORE_PREFIX_INDICATOR_SERIES)
                    || objectName.endsWith(IGNORE_PREFIX_INDICATOR_LABEL)
                    || objectName.endsWith(IGNORE_PREFIX_INDICATOR_TEXT)
                    || objectName.endsWith(IGNORE_PREFIX_INDICATOR_HEADER)
                    || objectName.endsWith(IGNORE_PREFIX_INDICATOR_IMAGE)) {
                String oldName = objectName;
                objectName = objectName.substring(underscoreIndex + 1);
                logger.log(Level.DEBUG, "Skipping object name prefix. Original name {0}, new name {1}",
                        oldName, objectName);
            }
        }

        if (ignoreThisObject) {
            return null;
        }
        // create MetadataObject instance and initialize it with collected data
        MetadataObject metadataObject = new MetadataObject(objectName);
        metadataObject.setFullName(fullName);
        metadataObject.setChildren(childObjects);
        metadataObject.setProperties(childProperties);
        return metadataObject;
    }

    /**
     * Checks that END_OBJECT definition is correct.
     *
     * @param context
     *              the parser context
     * @param objectName
     *              the name of the object which definition is finished by the END_OBJECT keyword
     *
     * @throws DataSetProcessingException
     *                  if END_OBJECT definition is malformed
     */
    private void validateEndOfObject(ParserContext context, String objectName) throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".validateEndOfObject(ParserContext context, String objectName)";

        boolean valid = true;
        boolean equalsFound = false;
        boolean objectNameFound = false;

        // process current line
        if (!context.isEmptyLine()) {
            if (context.lookingAt("=")) {
                equalsFound = true;
                context.skipMatch();
            } else {
                valid = false; // after END_OBJECT there is a token, but it's not a '='
            }
        }

        // search for optional '=' token if it is not found yet
        if (valid && !equalsFound) {
            context.ensureNotEmptyLine();
            if (context.lookingAt("=")) {
                equalsFound = true;
                context.skipMatch();
            } else {
                context.undoLastLineRead();
            }
        }

        // search for object name: it should be present if equals sign is already found
        if (valid && equalsFound && !objectNameFound) {
            context.ensureNotEmptyLine();
            if (!objectName.equals(context.nextToken())) {
                valid = false;
            }
            if (context.peekNextToken() != null && !context.peekNextToken().startsWith("/*")) {
                valid = false;
            }
        }

        // throw an exception if END_OBJECT definition is malformed
        if (!valid) {
            throw context.parserException(signature, "Invalid END_OBJECT format");
        }
    }

    /**
     * Processes include pointer.
     * 
     * @param parentContext
     *            the parser context for the file that contains include property
     * @param includeProperty
     *            the include pointer
     * @param metadataObjects
     *            container for included metadata objects
     * @param properties
     *            container for included properties
     * 
     * @throws DataSetProcessingException
     *             if there is an error while parsing the file
     */
    private void processIncludeProperty(ParserContext parentContext, Property includeProperty,
            List<MetadataObject> metadataObjects, List<Property> properties) throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".processIncludeProperty(...)";

        String filePath = resolveIncludePointerFilePath(parentContext, includeProperty);

        // initialize parser context
        ParserContext context = null;
        try {
            context = new ParserContext(filePath, logger);
        } catch (FileNotFoundException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Failed to open file with metadata info: " + filePath, e));
        }

        // parse metadata
        MetadataFile metadataFile = readMetadataInfoInternal(context);
        metadataObjects.addAll(metadataFile.getChildren());
        properties.addAll(metadataFile.getProperties());
    }

    /**
     * Processes description pointer.
     * 
     * @param parentContext
     *            the parser context for the file that contains include property
     * @param descriptionProperty
     *            the description pointer
     * @param properties
     *            container for included properties
     * 
     * @throws DataSetProcessingException
     *             if there is an error while reading the file
     */
    private void processDescriptionProperty(ParserContext parentContext, Property descriptionProperty,
            List<Property> properties) throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".processDescriptionProperty(...)";

        String filePath = resolveIncludePointerFilePath(parentContext, descriptionProperty);

        // create description pointer with value set to the content of the pointed file
        try {
            String value = Helper.readFileContent(filePath);
            Property property = new Property("DESCRIPTION");
            property.setValues(new ArrayList<String>());
            property.getValues().add(value);
            properties.add(property);
        } catch (IOException e) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Failed to read file for description pointer: " + filePath, e));
        }
    }

    /**
     * Resolves the absolute file path of the file that is referenced in the pointer property.
     * 
     * @param parentContext
     *            the parser context for the file that contains pointer property
     * @param pointerProperty
     *            the pointer property
     * @return the absolute file path
     * 
     * @throws DataSetProcessingException
     *             if failed to resolve absolute file path
     */
    private String resolveIncludePointerFilePath(ParserContext parentContext, Property pointerProperty)
            throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".resolveIncludePointerFilePath(...)";

        // at first look for the included file in the same directory as its parent file
        boolean found = true;
        String parentDirectory = new File(parentContext.getFileName()).getParent();
        String filePath = new File(parentDirectory, pointerProperty.getValues().get(0)).getAbsolutePath();

        if (dataSetFiles != null) {
            filePath = dataSetFiles.getRealAbsoluteFilePath(filePath);
            if (filePath == null) {
                found = false;
            }
        }
        if (found && !new File(filePath).isFile()) {
            found = false;
        }
        // if not found in parent directory then search in predefined directories
        if (!found) {
            List<String> predefinedDirectories = new ArrayList<String>();

            if (pointerProperty.getName().equalsIgnoreCase("^STRUCTURE") ||
                    (pointerProperty.getName().startsWith("^")
                    && pointerProperty.getName().endsWith("_STRUCTURE"))) {
                predefinedDirectories.add("LABEL");
            } else if (pointerProperty.getName().equalsIgnoreCase("^CATALOG")) {
                predefinedDirectories.add("CATALOG");
            } else if (pointerProperty.getName().equalsIgnoreCase("^DATA_SET_MAP_PROJECTION")) {
                predefinedDirectories.add("CATALOG");
            } else if (pointerProperty.getName().equalsIgnoreCase("^INDEX_TABLE")) {
                predefinedDirectories.add("INDEX");
            } else if (pointerProperty.getName().equalsIgnoreCase("^TEXT")) {
                predefinedDirectories.add("DOCUMENT");
            } else if (isDescriptionPointer(pointerProperty.getName())) {
                predefinedDirectories.add("DOCUMENT");
                predefinedDirectories.add("LABEL");
            }

            for (String predefinedDirectory : predefinedDirectories) {
                found = true;
                File directory = new File(dataSetFiles.getDataSetPath(), predefinedDirectory);
                filePath = new File(directory, pointerProperty.getValues().get(0)).getAbsolutePath();

                if (dataSetFiles != null) {
                    filePath = dataSetFiles.getRealAbsoluteFilePath(filePath);
                    if (filePath == null) {
                        found = false;
                    }
                }
                if (found && !new File(filePath).isFile()) {
                    found = false;
                }
                if (found) {
                    break;
                }
            }
        }
        // if failed to resolve the file path then throw an exception
        if (!found) {
            throw LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(
                    "Failed to find file specified in the pointer property: " + pointerProperty.getValues().get(0)));
        }
        return filePath;
    }

    /**
     * Parses the property definition.
     * 
     * @param context
     *            the parser context
     * 
     * @return the property object
     * 
     * @throws DataSetProcessingException
     *             if there is an error while parsing the file
     */
    private Property parseProperty(ParserContext context) throws DataSetProcessingException {
        // get property name
        String propertyName = context.nextPropertyName();
        if (propertyName == null) {
            final String signature = CLASS_NAME + ".parseProperty(ParserContext context)";
            throw context.parserException(signature, "Failed to parse property name");
        }

        // parse equals sign token
        parseEqualSign(context);

        // check whether we should preserve whitespace for this property's value
        boolean preserveWhitespace = false;
        if (propertyName.endsWith(WHITESPACE_PRESERVE_NOTE_SUFFIX)
                || propertyName.endsWith(WHITESPACE_PRESERVE_DESC_SUFFIX)
                || propertyName.endsWith(WHITESPACE_PRESERVE_DESCRIPTION_SUFFIX)
                || propertyName.equals(WHITESPACE_PRESERVE_NOTE_KEYWORD)
                || propertyName.equals(WHITESPACE_PRESERVE_DESCRIPTION_KEYWORD)) {
            preserveWhitespace = true;
        }

        // move to the beginning of property's value
        context.ensureNotEmptyLine();

        // parse various classes of property's values
        List<String> values = null;

        if (context.lookingAt("\"")) {
            // this parses single-line/multi-line quoted string or quoted word
            values = parseQuotedString(context, preserveWhitespace, false);
        } else if (context.lookingAt("'")) {
            // this parses single-line/multi-line single quoted string or single quoted word
            values = parseQuotedString(context, preserveWhitespace, true);
        } else if (context.lookingAt("(")) {
            context.skipMatch();
            List<List<String>> sequence = parseSequence(context);
            if (sequence.size() > 0 && sequence.get(0) == null) { // check for 1D sequence indicator
                assert (sequence.size() == 2);
                values = sequence.get(1);
            } else {
                SequenceValueProperty property = new SequenceValueProperty(propertyName);
                property.setSequences(sequence);
                return property;
            }
        } else if (context.lookingAt("{")) {
            context.skipMatch();
            values = parseSet(context);
        } else {
            // this parses word (without quotes), number or number with unit
            values = parseSingleLineValue(context);
        }
        // ignore properties whose value contains ignore pattern
        if (values.size() == 1 && (values.get(0).startsWith(SFDU_PATTERN))) {
            logger.log(Level.DEBUG, "Ignoring property {0} = {1}", propertyName, values.get(0));
            return null;
        }
        Property property = new Property(propertyName);
        property.setValues(values);
        return property;
    }

    /**
     * Parses the single-line value.
     *
     * @param context
     *            the parser context
     *
     * @return list with a single element - the parsed string
     *
     * @throws DataSetProcessingException
     *             if there is an error while parsing the file
     */
    private List<String> parseSingleLineValue(ParserContext context) throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".parseSingleLineValue(ParserContext context)";

        List<String> values = new ArrayList<String>();
        String token = context.nextToken();

        if (!context.isEmptyLine()) {
            if (floatingPointNumberRegex.matcher(token).matches()) {
                // get associated units
                String units = context.getCurrentLine().trim();
                if (!units.startsWith("<") || units.indexOf(">") == -1) {
                    logger.log(Level.WARN, "The units of the number are not surrounded by the angle brackes");
                }
                token += " " + units;
            } else {
                String message = "Invalid property value: unexpected token after property value";
                throw context.parserException(signature, message);
            }
        }

        values.add(token);
        return values;
    }

    /**
     * Parses the quoted string.
     *
     * @param context
     *            the parser context
     * @param preserveWhitespace
     *            defines whether whitespaces should be preserved
     * @param singleQuotes
     *            defines whether a string is quoted with regular or single quotes
     *
     * @return the list with a single element - the parsed string
     *
     * @throws DataSetProcessingException
     *             if there is an error while parsing the file
     */
    private List<String> parseQuotedString(ParserContext context, boolean preserveWhitespace, boolean singleQuotes)
            throws DataSetProcessingException {

        char quoteChar = singleQuotes ? '\'' : '"';
        String line = context.getCurrentLine();
        int start = line.indexOf(quoteChar);
        assert (start != -1);

        int commentPos = line.indexOf("/*");
        // for single-line quoted strings search the closed quote from the end of the line,
        // instead from the position after the opening quote, in order to handle double quoted
        // strings like this: ""LAMBDA"" (it's an example from a real dataset). The parsed string
        // will be: "LAMBDA" (i.e. it will contain one pair of quotes).
        int end = line.lastIndexOf(quoteChar, commentPos == -1 ? line.length() : commentPos);

        String value;
        if (end > start) {
            value = line.substring(start + 1, end);
        } else {
            StringBuilder sb = new StringBuilder(line.substring(start + 1));
            while (true) {
                context.readNextRequiredLine();
                line = context.getCurrentLine();
                end = line.indexOf(quoteChar);
                if (end != -1) {
                    sb.append(LINE_SEPARATOR).append(line.substring(0, end));
                    break;
                }
                sb.append(LINE_SEPARATOR).append(line);
            }
            value = sb.toString();
        }

        String remainder = line.substring(end + 1).trim();
        if (!remainder.isEmpty() && !remainder.startsWith("/*")) {
            final String signature = CLASS_NAME + ".parseQuotedString(...)";
            String message = "Invalid property definition: unexpected token was found after quoted string";
            throw context.parserException(signature, message);
        }

        List<String> result = new ArrayList<String>();
        result.add(preserveWhitespace ? value : value.trim());
        return result;
    }

    /**
     * Parses the set.
     *
     * @param context
     *            the parser context
     *
     * @return the list of set values
     *
     * @throws DataSetProcessingException
     *             if there is an error while parsing the file
     */
    private List<String> parseSet(ParserContext context) throws DataSetProcessingException {
        return context.parseCommaSeparatedValues('}');
    }

    /**
     * Parses the sequence of sets of words.
     *
     * @param context
     *               the parser context
     *
     * @return the list of sets of words
     *
     * @throws DataSetProcessingException
     *              if there is an error while parsing the file
     */
    private List<List<String>> parseSequence(ParserContext context) throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".parseSequence(ParserContext context)";

        List<List<String>> sequence = new ArrayList<List<String>>();
        context.ensureNotEmptyLine();

        if (context.lookingAt("(")) { // we have 2D sequence
            context.skipMatch();
            while (true) {
                List<String> values = context.parseCommaSeparatedValues(')');
                sequence.add(values);
                context.ensureNotEmptyLine();
                if (context.lookingAt(",")) {
                    context.skipMatch();
                    context.ensureNotEmptyLine();
                    if (!context.lookingAt("(")) {
                        throw context.parserException(signature, "missing ( symbol after comma");
                    }
                    context.skipMatch();
                } else if (context.lookingAt("(")) {
                    context.skipMatch();
                } else if (context.lookingAt(")")) {
                    context.skipMatch();
                    break;
                }
            }
        } else { // we have 1D sequence
            sequence.add(null); // it's a special value, indicates that sequence is 1D
            List<String> values = context.parseCommaSeparatedValues(')');
            sequence.add(values);
        }
        return sequence;
    }

    /**
     * Searches for equals sign token: in most cases it is on the same line but can also be on a separate line.
     *
     * @param context
     *              the parser context
     *
     * @throws DataSetProcessingException
     *              if there is an error while parsing the file
     */
    private void parseEqualSign(ParserContext context) throws DataSetProcessingException {
        context.ensureNotEmptyLine();
        if (!context.lookingAt("=")) {
            final String signature = CLASS_NAME + ".parseEqualSign(ParserContext context)";
            String message = "Malformed definition: OBJECT or property name is not followed by '='";
            throw context.parserException(signature, message);
        }
        context.skipMatch();
    }

    /**
     * Parses object name token.
     *
     * @param context
     *            the parser context
     *
     * @return the object name
     *
     * @throws DataSetProcessingException
     *             if there is an error while parsing the file
     */
    private String parseObjectName(ParserContext context) throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".parseObjectName(ParserContext context)";

        context.ensureNotEmptyLine();
        String objectName = context.nextToken();

        // there should be no more tokens after the object name
        if (!context.isEmptyLine()) {
            String message = "Malformed object definition: unexpected token was found after the object name";
            throw context.parserException(signature, message);
        }

        return objectName;
    }

    /**
     * Checks whether the given property is an include pointer.
     * 
     * @param propertyName
     *            the property name
     * @return true if the given property is an include pointer, otherwise false
     */
    private static boolean isIncludePointer(String propertyName) {
        return INCLUDE_POINTERS_NAMES.contains(propertyName)
                || (propertyName.startsWith("^") && propertyName.endsWith("_STRUCTURE"));
    }

    /**
     * Checks whether the given property is a description pointer.
     * 
     * @param propertyName
     *            the property name
     * @return true if the given property is a description pointer, otherwise false
     */
    private static boolean isDescriptionPointer(String propertyName) {
        final String DESC_PATTERN1 = "DESCRIPTION";
        final String DESC_PATTERN2 = "DESC";

        return propertyName.startsWith("^")
                && (propertyName.endsWith(DESC_PATTERN1) || propertyName.endsWith(DESC_PATTERN2));
    }
}
