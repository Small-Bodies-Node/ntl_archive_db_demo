/*
 * Copyright (C) 2012 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl;

import gov.nasa.pds.services.DataSetFiles;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * The {@code DataSetFilesImpl} class implements {@code DataSetFiles} interface and represents a default
 * implementation of this interface.
 * </p>
 *
 * <strong>Thread Safety:</strong> This class is not thread safe.
 *
 * @author KennyAlive
 * @version 1.0
 */
public class DataSetFilesImpl implements DataSetFiles {
    /**
     * The dataset path in the filesystem. Initialized in constructor. Can not be null.
     */
    private String dataSetPath;

    /**
     * The dataset file paths relative to dataset directory. Initialized in constructor or in {@link #getFiles()}
     * method.
     */
    private List<String> filePaths;

    /**
     * Mapping from lower-case file path string to real file path as defined in the filesystem (relative to dataset
     * directory).
     */
    private Map<String, String> lowerCase2RealPath;

    /**
     * Creates an instance of {@code DataSetFiles}.
     *
     * @param dataSetPath
     *            the dataset path
     */
    public DataSetFilesImpl(String dataSetPath) {
        this.dataSetPath = dataSetPath;
    }

    /**
     * Creates an instance of {@code DataSetFiles}.
     *
     * @param dataSetPath
     *            the dataset path
     * @param filePaths
     *            the list of file paths relative to dataset directory
     */
    public DataSetFilesImpl(String dataSetPath, List<String> filePaths) {
        this.dataSetPath = dataSetPath;
        this.filePaths = filePaths;
    }

    /**
     * Gets the path to the dataset directory.
     *
     * @return the path to the dataset directory
     */
    @Override
    public String getDataSetPath() {
        return dataSetPath;
    }

    /**
     * Creates list of all dataset file paths relative to dataset directory.
     *
     * @return the list of all dataset file paths relative to dataset directory
     */
    @Override
    public List<String> getFiles() {
        if (filePaths == null) {
            // get list of all dataset files
            File directory = new File(dataSetPath);
            List<File> files = new ArrayList<File>();
            Helper.listFiles(directory, files);

            // create list of filepaths relative to dataset directory
            String directoryAbsPath = directory.getAbsolutePath();
            filePaths = new ArrayList<String>(files.size());
            for (File file : files) {
                String fileAbsPath = file.getAbsolutePath();
                String relativePath;
                if (fileAbsPath.startsWith(directoryAbsPath) && fileAbsPath.length() > directoryAbsPath.length()) {
                    // It's just faster way to get relativePath. But it does not work in all cases.
                    relativePath = fileAbsPath.substring(directoryAbsPath.length() + 1);
                } else {
                    // It's slower way to get relativePath. But it's work in all cases.
                    relativePath = directory.toURI().relativize(file.toURI()).getPath();
                }
                filePaths.add(relativePath);
            }
        }
        return filePaths;
    }

    /**
     * Returns the file path as it's defined by the filesystem (fixes case-sensitivity issues) relative to dataset
     * directory.
     *
     * @param filePath
     *            the file path as it's defined by some configuration file
     *
     * @return the corrected file path relative to dataset directory
     */
    @Override
    public String getRealFilePath(String filePath) {
        if (lowerCase2RealPath == null) {
            createPathMapping();
        }
        if (filePath.startsWith("./") || filePath.startsWith(".\\")) {
            filePath = filePath.substring(2);
        }
        filePath = filePath.replace('\\', '/');
        filePath = lowerCase2RealPath.get(filePath.toLowerCase());
        return filePath;
    }

    /**
     * Returns the absolute file path as it's defined by the filesystem (fixes case-sensitivity issues).
     *
     * @param absFilePath
     *            the absolute file path of the form: <dataset_directory_absolute_path> + <relative_path>, where
     *            <relative_path> is the path relative to the dataset directory as defined by some configuration file
     *
     * @return the corrected absolute file path or null if absFilePath is not a dataset's file path
     */
    @Override
    public String getRealAbsoluteFilePath(String absFilePath) {
        if (lowerCase2RealPath == null) {
            createPathMapping();
        }
        File dataSetDirectory = new File(dataSetPath);

        File relativeFile = null;
        File file = new File(absFilePath);
        while (file != null && !file.equals(dataSetDirectory)) {
            if (relativeFile == null) {
                relativeFile = new File(file.getName());
            } else {
                relativeFile = new File(file.getName(), relativeFile.getPath());
            }
            file = file.getParentFile();
        }

        if (file == null) {
            return null;
        }
        if (relativeFile == null) {
            return "./";
        }

        String relativePath = relativeFile.getPath();
        if (relativePath.startsWith("./") || relativePath.startsWith(".\\")) {
            relativePath = relativePath.substring(2);
        }
        relativePath = relativePath.replace('\\', '/');
        relativePath = lowerCase2RealPath.get(relativePath.toLowerCase());

        if (relativePath == null) {
            return null;
        }
        return new File(dataSetDirectory, relativePath).getAbsolutePath();
    }

    /**
     * Creates mapping where each value represents file path relative to dataset root directory. The keys for this
     * mapping are the same paths but in lowercase register.
     *
     * This mapping is used by the {@code getRealFilePath} method to get filesystem's file path for the given path
     * that was read from some configuration file. This functionality mostly needed for case-sensitive filesystems
     * since it's a common to have file path specified in different register compared to filesystem.
     */
    private void createPathMapping() {
        if (lowerCase2RealPath == null) {
            lowerCase2RealPath = new HashMap<String, String>();
            for (String relativePath : getFiles()) {
                lowerCase2RealPath.put(relativePath.replace('\\', '/').toLowerCase(), relativePath);
            }
        }
    }
}
