/*
 * Copyright (C) 2012 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services;

import java.util.List;

/**
 * The {@code DataSetFiles} interface that provides information about dataset files.
 *
 * <strong>Thread Safety:</strong> The implementations are not required to be thread-safe.
 *
 * @author KennyAlive
 * @version 1.0
 */
public interface DataSetFiles {
    /**
     * Gets the path to the dataset directory.
     *
     * @return the path to the dataset directory
     */
    String getDataSetPath();

    /**
     * Gets the list of all dataset file paths relative to dataset directory.
     * 
     * @return the list of all dataset file paths relative to dataset directory
     */
    List<String> getFiles();

    /**
     * Returns the file path as it's defined by the filesystem (fixes case-sensitivity issues) relative to dataset
     * directory.
     *
     * @param filePath
     *            the file path as it's defined by some configuration file
     *
     * @return the corrected file path relative to dataset directory
     */
    String getRealFilePath(String filePath);

    /**
     * Returns the absolute file path as it's defined by the filesystem (fixes case-sensitivity issues).
     *
     * @param absFilePath
     *            the absolute file path of the form: <dataset_directory_absolute_path> + <relative_path>, where
     *            <relative_path> is the path relative to the dataset directory as defined by some configuration file
     *
     * @return the corrected absolute file path or null if absFilePath is not a dataset's file path
     */
    String getRealAbsoluteFilePath(String absFilePath);
}
