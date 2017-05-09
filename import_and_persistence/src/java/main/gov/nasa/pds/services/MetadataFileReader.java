/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services;

import gov.nasa.pds.entities.MetadataFile;

import java.io.InputStream;

/**
 * Reads the metadata from the given file.
 *
 * <strong>Thread Safety:</strong> The implementations should be effectively thread-safe.
 *
 * @author KennyAlive
 * @version 1.0
 */
public interface MetadataFileReader {
    /**
     * Reads the metadata from the given file.
     *
     * @param filePath
     *            the file path of the file with the metadata
     * @param dataSetFiles
     *            the DataSetFiles instance to resolve file names
     *
     * @throws DataSetProcessingException
     *             if there is any error while parsing the file
     *
     * @return the parsed metadata file object
     */
    MetadataFile readMetadataInfo(String filePath, DataSetFiles dataSetFiles) throws DataSetProcessingException;

    /**
     * Reads the metadata from the given input stream.
     *
     * @param inputStream
     *            the input stream to read metadata from
     * @param bytesToRead
     *            the amount of bytes to read from input steam
     * @param filePath
     *            the filePath (used only for logging, real data is streamed from inputStream)
     * @param dataSetFiles
     *            the DataSetFiles instance to resolve file names
     *
     * @throws DataSetProcessingException
     *             if there is any error while parsing input stream data
     *
     * @return the parsed metadata file object
     */
    MetadataFile readMetadataInfo(InputStream inputStream, long bytesToRead, String filePath, DataSetFiles dataSetFiles)
            throws DataSetProcessingException;
}
