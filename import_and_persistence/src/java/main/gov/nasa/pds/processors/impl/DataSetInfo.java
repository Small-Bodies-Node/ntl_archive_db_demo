/*
 * Copyright (C) 2012 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.processors.impl;

import java.io.File;

/**
 * <p>
 * The {@code DataSetInfo} class represents information about the dataset.
 * </p>
 *
 * <strong>Thread Safety:</strong> This class is immutable and thread safe.
 *
 * @author KennyAlive
 * @version 1.0
 */
class DataSetInfo implements Comparable<DataSetInfo> {
    /**
     * The dataset archive path.
     */
    private String dataSetArchivePath;

    /**
     * Dataset directory name.
     */
    private String directoryName;

    /**
     * The length of the {@code directoryName} without version suffix
     */
    private int baseDirectoryNameLength;

    /**
     * The major version of the dataset. 0, if dataset directory does not contain version suffix.
     */
    private int majorVersion;

    /**
     * The minor version of the dataset. 0, if dataset directory does not contain version suffix.
     */
    private int minorVersion;

    /**
     * Constructs new {@code DataSetInfo} instance. Also parses dataset version from the provided directory name.
     * 
     * @param dataSetArchivePath
     *            the dataset archive path
     * @param directoryName
     *            the dataset directory name
     */
    public DataSetInfo(String dataSetArchivePath, String directoryName) {
        this.dataSetArchivePath = dataSetArchivePath;
        this.directoryName = directoryName;

        // Parse dataset version.
        boolean majorParsed = false;
        boolean minorParsed = false;
        int dotPosition = -1;

        for (int i = directoryName.length() - 1; i >= 0; i--) {
            char ch = directoryName.charAt(i);

            if (ch == '.') {
                // do validation
                if (minorParsed || i == directoryName.length() - 1) {
                    break;
                }
                // parse minor version
                minorVersion = Integer.parseInt(directoryName.substring(i + 1));
                minorParsed = true;
                dotPosition = i;

            } else if (ch == 'v' || ch == 'V') {
                // do validation
                if (!minorParsed || i + 1 == dotPosition) {
                    break;
                }
                // parse major version
                majorVersion = Integer.parseInt(directoryName.substring(i + 1, dotPosition));
                majorParsed = true;
                baseDirectoryNameLength = i;
                break; // dataset version is parsed successfully

            } else if (!Character.isDigit(ch)) {
                break;
            }
        }

        if (!majorParsed || !minorParsed) {
            baseDirectoryNameLength = directoryName.length();
        }
    }

    /**
     * Gets the dataset archive path.
     *
     * @return the dataset archive path
     */
    public String getDataSetArchivePath() {
        return dataSetArchivePath;
    }

    /**
     * Gets the dataset directory name.
     *
     * @return the dataset directory name
     */
    public String getDirectoryName() {
        return directoryName;
    }

    /**
     * Gets the absolute dataset path.
     *
     * @return the absolute dataset path
     */
    public String getPath() {
        return new File(dataSetArchivePath, directoryName).getAbsolutePath();
    }

    /**
     * Gets the dataset major version.
     *
     * @return the dataset major version
     */
    public int getMajorVersion() {
        return majorVersion;
    }

    /**
     * Gets the dataset minor version.
     *
     * @return the dataset minor version
     */
    public int getMinorVersion() {
        return minorVersion;
    }

    /**
     * Gets the base directory name (dataset directory name without version suffix).
     *
     * @return the base directory name
     */
    public String getBaseDirectoryName() {
        return directoryName.substring(0, baseDirectoryNameLength);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(DataSetInfo other) {
        return directoryName.compareTo(other.directoryName);
    }
}
