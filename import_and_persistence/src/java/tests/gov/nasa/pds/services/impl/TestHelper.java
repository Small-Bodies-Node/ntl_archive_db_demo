/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services.impl;

import gov.nasa.pds.entities.MetadataObject;
import gov.nasa.pds.entities.Property;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 * <p>
 * Helper class to simplify the unit testing.
 * </p>
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
public final class TestHelper {
    /**
     * Represents the database configure file path.
     */
    private static final String DATABASE_PROPERTY_FILE_PATH = "./config/application.properties";

    /**
     * Represents the database configure file path.
     */
    private static final String DELETE_SQL_FILE_PATH = "./test_files/delete.sql";

    /**
     * The private constructor to avoid creating instance of this class.
     */
    private TestHelper() {
    }

    /**
     * Creates a sql connection.
     *
     * @return the created sql connection
     * @throws Exception if any error occurs
     */
    public static Connection getConnection() throws Exception {
        //gets connection properties from file database.properties
        Properties prop = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(DATABASE_PROPERTY_FILE_PATH);
            prop.load(fis);
        } finally {
            if (fis != null) {
                fis.close();
            }
        }

        //creates the connection
        Class.forName(prop.getProperty("jdbc.driverClassName"));
        return DriverManager.getConnection(
                prop.getProperty("jdbc.url"),
                prop.getProperty("jdbc.username"),
                prop.getProperty("jdbc.password"));
    }

    /**
     * Closes the connection.
     *
     * @param conn the connection to be close
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // Ignore
            }
        }
    }

    /**
     * query the specified SQL in database.
     *
     * @param conn the connection established to the database
     * @param sql the SQL statement to be executed
     * @return the result set
     * @throws SQLException if any error occurs while executing the SQL
     */
    public static ResultSet query(Connection conn, String sql) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    /**
     * query the specified SQL in database.
     *
     * @param conn the connection established to the database
     * @param sql the SQL statement to be executed
     * @throws SQLException if any error occurs while executing the SQL
     */
    public static void execute(Connection conn, String sql) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.execute();
    }

    /**
     * Delete records from tables ISSUE_TYPES, DUMMYUSER and ISSUES.
     *
     * @param conn the connection established to the database.
     * @throws SQLException if any error occurs while executing the SQL.
     * @throws IOException if any io exception occurs
     */
    public static void deleteData(Connection conn) throws SQLException, IOException {
        executeFileSQL(conn, DELETE_SQL_FILE_PATH);
    }

    /**
     * creates a property with specified name and values.
     *
     * @param name the property name
     * @param values the property values
     * @return a metadataObject with specified name and empty properties
     */
    public static Property createProperty(String name, String...values) {
        Property property = new Property();
        property.setName(name);
        property.setValues(new ArrayList<String>());
        for (String value : values) {
            property.getValues().add(value);
        }
        return property;
    }

    /**
     * creates a metadataObject with specified name and empty properties.
     *
     * @param name the meta data object name
     * @return a metadataObject with specified name and empty properties
     */
    public static MetadataObject createMetadataObject(String name) {
        MetadataObject metadataObject = new MetadataObject();
        metadataObject.setName(name);
        metadataObject.setProperties(new ArrayList<Property>());
        return metadataObject;
    }

    /**
     * Gets the value of the private field of a class.
     * @param object the instance of the class
     * @param fieldName the name of the field
     * @param <T> the class
     * @return the value of the field
     */
    @SuppressWarnings("unchecked")
    public static <T> T getObjectProperty(Object object, String fieldName) {
        try {
            Field field = getField(object, fieldName);
            field.setAccessible(true);
            Object value = field.get(object);
            return (T) value;
        } catch (IllegalAccessException e) {
            // Ignore
        }
        return null;
    }

    /**
     * Sets the private field of a class.
     * @param object the instance of the class
     * @param fieldName the name of the field
     * @param value the new value of the field
     */
    public static void setObjectProperty(Object object, String fieldName, Object value) {
        try {
            Field field = getField(object, fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (IllegalAccessException e) {
            // Ignore
        }
    }

    /**
     * Gets the private field of a class.
     * @param object the instance of the class
     * @param fieldName the name of the field
     * @return the field of the class
     */
    private static Field getField(Object object, String fieldName) {
        Field fieldObj = null;
        try {
            try {
                fieldObj = object.getClass().getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                if (!object.getClass().getSuperclass().equals(Object.class)) {
                    try {
                        fieldObj = object.getClass().getSuperclass().getDeclaredField(fieldName);
                    } catch (NoSuchFieldException e1) {
                        // Ignore
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            // Ignore
        } catch (SecurityException e) {
            // Ignore
        }
        return fieldObj;
    }

    /**
     * Executes the specified SQL read from file in database. Empty lines will be ignored.
     *
     * @param connection the connection established to the database
     * @param file the file to be read from
     * @throws IOException if any error occurs during reading
     * @throws SQLException if any error occurs while executing the SQL
     */
    private static void executeFileSQL(Connection connection, String file) throws IOException, SQLException {
        String[] values = readFile(file).split(";");

        Statement statement = connection.createStatement();
        try {
            for (int i = 0; i < values.length; i++) {
                String sql = values[i].trim();
                if (sql.length() != 0) {
                    statement.executeUpdate(sql);
                }
            }
        } finally {
            statement.close();
        }
    }

    /**
     * Reads the content of a given file.
     *
     * @param fileName the name of the file to read.
     * @return a string represents the content.
     * @throws IOException if any error occurs during reading.
     */
    private static String readFile(String fileName) throws IOException {
        Reader reader = new FileReader(fileName);

        try {
            // Create a StringBuilder instance
            StringBuilder sb = new StringBuilder();

            // Buffer for reading
            char[] buffer = new char[1024];

            // Number of read chars
            int k = 0;

            // Read characters and append to string builder
            while ((k = reader.read(buffer)) != -1) {
                sb.append(buffer, 0, k);
            }

            // Return read content
            return sb.toString().replace("\r\n", "\n");
        } finally {
            reader.close();
        }
    }
}
