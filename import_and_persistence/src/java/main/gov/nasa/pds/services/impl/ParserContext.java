/*
 * Copyright (C) 2012 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services.impl;

import gov.nasa.pds.services.DataSetProcessingException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.topcoder.commons.utils.LoggingWrapperUtility;
import com.topcoder.util.log.Log;

/**
 * <p>
 * The {@code ParserContext} class represents a state of the metadata file parser. The instance of this class is not
 * an instance variable of the parser class but passed as methods parameter to ensure that parser can be used in a
 * thread-safe manner.
 * </p>
 *
 * <strong>Thread Safety:</strong> This class is not thread-safe since it has mutable fields.
 *
 * @author KennyAlive
 * @version 1.0
 */
class ParserContext {
    /**
     * Constant for the class name of this class. Used for logging.
     */
    private static final String CLASS_NAME = ParserContext.class.getName();

    /**
     * The {@code Log} instance used for logging.
     */
    private Log logger;

    /**
     * Represents the filename of the file to parse.
     */
    private final String filename;

    /**
     * The file reader.
     */
    private LineReader reader;

    /**
     * The current line number (1-based).
     */
    private int currentLineIndex;

    /**
     * Represents the line that is being processed.
     */
    private String currentLine;

    /**
     * Lines cache. Used to implement workaround for multi-line comment parsing.
     */
    private List<String> linesCache;

    /**
     * Current position in currentLine.
     */
    private int position;

    /**
     * If true, the next line reading will return currentLine one more time, otherwise the new line will be read.
     */
    private boolean undoLastLineRead;

    /**
     * The position of the matched pattern.
     */
    private int matchEnd;

    /**
     * Creates an instance of {@code ParserContext}.
     *
     * @param filename
     *            the filename of the file to parse
     * @param log
     *            the logger
     *
     * @throws FileNotFoundException
     *             if there is an error while opening the file
     */
    public ParserContext(String filename, Log logger) throws FileNotFoundException {
        this.logger = logger;
        this.filename = filename;
        this.reader = new LineReader(new FileReader(filename), (int) new File(filename).length());
        this.currentLineIndex = 0;
        this.matchEnd = 0;
    }

    /**
     * Creates an instance of {@code ParserContext}.
     *
     * @param inputStream
     *            the input sream
     * @param filename
     *            the filename (used only for logging, real data is streamed from inputStream)
     * @param bytesToRead
     *            the number of bytes to read from input stream
     * @param log
     *            the logger
     */
    public ParserContext(InputStream inputStream, String filename, int bytesToRead, Log logger) {
        this.logger = logger;
        this.filename = filename;
        this.reader = new LineReader(new InputStreamReader(inputStream), bytesToRead);
        this.currentLineIndex = 0;
        this.matchEnd = 0;
    }

    /**
     * Closes the reader associated with this context.
     */
    public void close() {
        if (this.reader != null) {
            try {
                this.reader.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    /**
     * Gets the filename.
     *
     * @return the filename
     */
    public String getFileName() {
        return filename;
    }

    /**
     * Gets the current line.
     *
     * @return the line that is being processed
     */
    public String getCurrentLine() {
        return currentLine.substring(position);
    }

    /**
     * The next line read request will return current line again.
     */
    public void undoLastLineRead() {
        undoLastLineRead = true;
    }

    /**
     * Reads the next not-empty line.
     *
     * @return false, if the end of the stream has been reached, otherwise true
     *
     * @throws DataSetProcessingException
     *             if there is an error while reading the file
     */
    public boolean readNextNotEmptyLine() throws DataSetProcessingException {
        internalReadNextLine();
        while (currentLine != null && isEmptyLine()) {
            internalReadNextLine();
        }
        return currentLine != null;
    }

    /**
     * Reads the next line from the input file. Throws exception if the end of stream has been reached.
     *
     * @throws DataSetProcessingException
     *             if there is an error while reading the file or the end of stream has been reached
     */
    public void readNextRequiredLine() throws DataSetProcessingException {
        internalReadNextLine();
        if (currentLine == null) {
            final String signature = CLASS_NAME + ".readNextRequiredLine()";
            throw parserException(signature, "Unexpected end of file");
        }
    }

    /**
     * Reads the next not-empty line. Throws exception if the end of stream has been reached.
     *
     * @throws DataSetProcessingException
     *             if there is an error while reading the file or the end of stream has been reached
     */
    public void readNextRequiredNotEmptyLine() throws DataSetProcessingException {
        if (!readNextNotEmptyLine()) {
            final String signature = CLASS_NAME + ".readNextRequiredNotEmptyLine()";
            throw parserException(signature, "Unexpected end of file");
        }
    }

    /**
     * Does nothing if there is token after current position, otherwise reads next not-empty line. Throws exception if
     * the end of stream has been reached.
     *
     * @throws DataSetProcessingException
     *             if there is an error while reading the file
     */
    public void ensureNotEmptyLine() throws DataSetProcessingException {
        if (isEmptyLine()) {
            readNextRequiredNotEmptyLine();
        }
    }

    /**
     * Checks if the rest of the current line is empty.
     *
     * @return true if the rest of the line is empty, otherwise false
     */
    public boolean isEmptyLine() {
        for (int i = position; i < currentLine.length(); i++) {
            char ch = currentLine.charAt(i);
            if (ch > 32) {
                if (ch != '/' || i == currentLine.length() - 1) {
                    return false;
                }
                return currentLine.charAt(i + 1) == '*';
            }
        }
        return true;
    }

    /**
     * Reads the next token.
     *
     * @return the next token
     *
     * @throws DataSetProcessingException
     *             if there are no tokens left in the current line
     */
    public String nextToken() throws DataSetProcessingException {
        String token = peekNextToken();
        if (token == null) {
            final String signature = CLASS_NAME + ".nextToken()";
            throw parserException(signature, "There are no tokens left in current line");
        }
        skipMatch();
        return token;
    }

    /**
     * Gets the next token but does not advance current position (except skipping leading whitespace characters).
     *
     * @return the next token
     */
    public String peekNextToken() {
        matchEnd = 0;
        skipWhitespaces();
        if (position == currentLine.length()) {
            return null;
        }
        int end = position + 1;
        while (end < currentLine.length() && currentLine.charAt(end) > 32) {
            end++;
        }
        matchEnd = end;
        return currentLine.substring(position, end);
    }

    /**
     * Skips last matched pattern.
     *
     * @throws DataSetProcessingException
     *             if the last matched pattern is not available
     */
    public void skipMatch() throws DataSetProcessingException {
        if (matchEnd == 0) {
            final String signature = CLASS_NAME + ".skipMatch()";
            throw parserException(signature, "The last match is not available");
        }
        position = matchEnd;
        matchEnd = 0;
    }

    /**
     * Checks that the next token is a property name and return it.
     *
     * @return the property name or null failed to parse property name
     */
    public String nextPropertyName() throws DataSetProcessingException {
        matchEnd = 0;
        skipWhitespaces();

        if (position == currentLine.length()) {
            return null;
        }

        int end = position;
        if (currentLine.charAt(position) == '^') {
            end++;
        }
        for (; end < currentLine.length(); end++) {
            char ch = currentLine.charAt(end);
            if (ch >= 'a' && ch <= 'z') {
                continue;
            }
            if (ch >= 'A' && ch <= 'Z') {
                continue;
            }
            if (ch >= '0' && ch <= '9') {
                continue;
            }
            if (ch == '_' || ch == ':') {
                continue;
            }
            break;
        }

        if (position == end || (currentLine.charAt(position) == '^' && end == position + 1)) {
            return null;
        }
        if (end != currentLine.length()) {
            char ch = currentLine.charAt(end);
            if (!(ch <= 32 || ch == '=')) {
                return null;
            }
        }

        String propertyName = currentLine.substring(position, end);
        position = end;
        return propertyName;
    }

    /**
     * Checks if the current position matches the given prefix (the leading whitespace characters are skipped).
     *
     * @param str
     *            the prefix string
     *
     * @return true if current position matches the given prefix, otherwise false
     */
    public boolean lookingAt(String str) {
        skipWhitespaces();
        str = str.trim();
        if (currentLine.startsWith(str, position)) {
            matchEnd = position + str.length();
            return true;
        }
        return false;
    }

    /**
     * Parses sequences of comma-separated values. It is assumed that predefined symbol finishes the sequence.
     *
     * @param endSymbol
     *            the sequence terminator symbol
     *
     * @return the parsed values
     *
     * @throws DataSetProcessingException
     *             if some error occurs during parsing
     */
    public List<String> parseCommaSeparatedValues(char endSymbol) throws DataSetProcessingException {
        final String signature = CLASS_NAME + ".parseCommaSeparatedValues(char endSymbol)";

        matchEnd = 0;
        ensureNotEmptyLine();
        skipWhitespaces();

        List<String> values = new ArrayList<String>();
        int pos = position;
        while (currentLine.charAt(pos) != endSymbol) {
            // check if the next value is a quoted string
            boolean quote = false;
            char quoteChar = 0;
            if (currentLine.charAt(pos) == '"' || currentLine.charAt(pos) == '\'') {
                quote = true;
                quoteChar = currentLine.charAt(pos);
            } else if (currentLine.charAt(pos) == ',') {
                // workaround for sequences like (,value1, value2) or (value1, ,value2).
                pos++;
                position = pos; // update position field here, since private methods need updated value
                ensureNotEmptyLine();
                skipWhitespaces();
                pos = position;
                continue;
            }

            // mark start position of the next value
            int beginIndex = pos;

            // get the value
            pos++;
            String value = null;
            if (quote) {
                while (pos < currentLine.length() && currentLine.charAt(pos) != quoteChar) {
                    pos++;
                }
                if (pos == currentLine.length()) {
                    throw parserException(signature, "missing closing quote for opening quote (" + beginIndex + ")");
                }
                value = trimQuotesAndSpaces(currentLine.substring(beginIndex + 1, pos));
                pos++;

                if (pos < currentLine.length()) {
                    char ch = currentLine.charAt(pos);
                    if (ch > 32 && ch != ',' && ch != endSymbol) {
                        throw parserException(signature, "unexpected symbol after closing quote at position " + pos);
                    }
                }
            } else {
                for (; pos < currentLine.length(); pos++) {
                    char ch = currentLine.charAt(pos);
                    if (ch == ',' || ch == endSymbol) {
                        break;
                    }
                }
                value = currentLine.substring(beginIndex, pos).trim();
            }

            // add the value to result list
            values.add(value);

            // advance to the next token
            position = pos; // update position field here, since private methods need updated value
            ensureNotEmptyLine();
            skipWhitespaces();

            if (currentLine.charAt(position) == ',') {
                position++;
                ensureNotEmptyLine();
                skipWhitespaces();
            } else if (currentLine.charAt(position) != endSymbol) {
                throw parserException(signature, "unexpected symbol at position " + position);
            }
            pos = position;
        }
        position++;
        return values;
    }

    /**
     * Creates an instance of {@code DataSetProcessingException}. The provided error message is appended with a file
     * name current line number that approximately indicates where the malformed construction is located. This method
     * also logs the exception before it returns.
     *
     * @param signature
     *            the method signature
     * @param message
     *            the error message
     *
     * @return new instance of {@code DataSetProcessingException}
     */
    public DataSetProcessingException parserException(String signature, String message) {
        String errorMessage = String.format("%s [%s (%d)]", message, filename, currentLineIndex);
        return LoggingWrapperUtility.logException(logger, signature, new DataSetProcessingException(errorMessage));
    }

    /**
     * Reads the next line.
     *
     * @throws DataSetProcessingException
     *             if there is an error while reading the file
     */
    private void internalReadNextLine() throws DataSetProcessingException {
        try {
            if (undoLastLineRead) {
                undoLastLineRead = false;
                // use the same currentLine
            } else {
                //currentLine = reader.readLine();

                // WORKAROUND begin: try to handle multi-line comments which are forbidden by PDS standards but
                // were found in few datasets. The workaround works if: multi-line comment takes only a few lines,
                // begins prefix (/*) and end suffix (*/) are located on separate lines.
                final int maxCommentLines = 10;
                if (linesCache != null && !linesCache.isEmpty()) {
                    currentLine = linesCache.remove(0);
                } else {
                    currentLine = reader.readLine();
                    if (currentLine != null && currentLine.trim().equals("/*")) {
                        if (linesCache == null) {
                            linesCache = new ArrayList<String>();
                        }
                        linesCache.add(currentLine);
                        for (int i = 0; i < maxCommentLines; i++) {
                            String line = reader.readLine();
                            if (line == null) {
                                break;
                            }
                            linesCache.add(line);
                            if (line.trim().equals("*/")) {
                                linesCache.clear();
                                currentLineIndex += i + 2;
                                internalReadNextLine();
                                return;
                            }
                        }
                        internalReadNextLine();
                        return;
                    }
                }
                // WORKAROUND end

                currentLineIndex++;
            }
            position = 0;
        } catch (IOException e) {
            final String signature = CLASS_NAME + ".internalReadNextLine()";
            throw parserException(signature, "Failed to read next line from file");
        }
    }

    /**
     * Skips whitespace characters.
     */
    private void skipWhitespaces() {
        int i = position;
        while (i < currentLine.length() && currentLine.charAt(i) <= 32) {
            i++;
        }
        position = i;
    }

    /**
     * Trims all surrounding whitespace symbols and quotes (both single and regular). It is assumed that the method's
     * {@code String} parameter is not null.
     *
     * @param str
     *            the string to trim surrounding whitespace characters and quotes from
     *
     * @return the copy of the given string without quotes and leading/ending whitespace symbols
     */
    private String trimQuotesAndSpaces(String str) {
        int length = str.length();
        int prevLength = 0;
        while (length != prevLength) {
            str = str.trim();
            if (!str.isEmpty() && str.charAt(0) == '"') {
                str = str.substring(1);
            }
            if (!str.isEmpty() && str.charAt(str.length() - 1) == '"') {
                str = str.substring(0, str.length() - 1);
            }
            if (!str.isEmpty() && str.charAt(0) == '\'') {
                str = str.substring(1);
            }
            if (!str.isEmpty() && str.charAt(str.length() - 1) == '\'') {
                str = str.substring(0, str.length() - 1);
            }
            prevLength = length;
            length = str.length();
        }
        return str;
    }
}
