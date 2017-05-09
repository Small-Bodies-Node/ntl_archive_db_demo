/*
 * Copyright (C) 2011-2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.services.impl;

import gov.nasa.pds.entities.Loggable;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 * <p>
 * The Helper class.
 * </p>
 * 
 * <p>
 * Version 1.1 changes [PDS - Import and Persistence Update - Assembly Contest]:
 * <ol>
 * <li>Added {@link #readFileContent(String)} method.</li>
 * </ol>
 * </p>
 * 
 * @author KennyAlive
 * @version 1.1
 */
public final class Helper {
    /**
     * The default do nothing constructor.
     */
    private Helper() {
        // Empty
    }

    /**
     * Returns compiled regular expression that matches floating point number (float, double).
     *
     * Based on the following documentation:
     * http://docs.oracle.com/javase/6/docs/api/java/lang/Double.html#valueOf%28java.lang.String%29
     *
     * @return compiled regular expression that matches floating point number
     */
    public static Pattern createFloatingPointRegex() {
        final String Digits = "(\\p{Digit}+)";
        final String HexDigits = "(\\p{XDigit}+)";
        // an exponent is 'e' or 'E' followed by an optionally
        // signed decimal integer.
        final String Exp = "[eE][+-]?" + Digits;
        final String fpRegex =
                ("[\\x00-\\x20]*" + // Optional leading "whitespace"
                        "[+-]?(" + // Optional sign character
                        "NaN|" + // "NaN" string
                        "Infinity|" + // "Infinity" string

                        // A decimal floating-point string representing a finite positive
                        // number without a leading sign has at most five basic pieces:
                        // Digits . Digits ExponentPart FloatTypeSuffix
                        //
                        // Since this method allows integer-only strings as input
                        // in addition to strings of floating-point literals, the
                        // two sub-patterns below are simplifications of the grammar
                        // productions from the Java Language Specification, 2nd
                        // edition, section 3.10.2.

                        // Digits ._opt Digits_opt ExponentPart_opt FloatTypeSuffix_opt
                        "(((" + Digits + "(\\.)?(" + Digits + "?)(" + Exp + ")?)|" +

                        // . Digits ExponentPart_opt FloatTypeSuffix_opt
                        "(\\.(" + Digits + ")(" + Exp + ")?)|" +

                        // Hexadecimal strings
                        "((" +
                        // 0[xX] HexDigits ._opt BinaryExponent FloatTypeSuffix_opt
                        "(0[xX]" + HexDigits + "(\\.)?)|" +

                        // 0[xX] HexDigits_opt . HexDigits BinaryExponent FloatTypeSuffix_opt
                        "(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")" +

                        ")[pP][+-]?" + Digits + "))" +
                        "[fFdD]?))" +
                "[\\x00-\\x20]*");// Optional trailing "whitespace"

        return Pattern.compile(fpRegex);
    }

    /**
     * Checks whether the given value is not empty (including the case that empty after trimming).
     *
     * @param jdbcTemplate the jdbc template
     * @param sql the sql
     * @param args the value of each row
     * @return the generated id
     */
    public static long insert(JdbcTemplate jdbcTemplate, final String sql, final Object... args) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
            new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps =
                        connection.prepareStatement(sql, new String[] {"id"});
                    int i = 0;
                    for (Object arg : args) {
                        i++;
                        ps.setObject(i, arg);
                    }
                    return ps;
                }
            },
            keyHolder);
        return keyHolder.getKey().longValue();
    }

    /**
     * Inserts record into the database and returns theirs ids.
     *
     * @param jdbcTemplate
     *            the jdbc template
     * @param sql
     *            the sql request
     * @param args
     *            the arguments of the query
     *
     * @return the list of generated ids
     */
    public static List<Long> insertMultipleRecords(JdbcTemplate jdbcTemplate, final String sql, final Object... args) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
                        int i = 0;
                        for (Object arg : args) {
                            i++;
                            ps.setObject(i, arg);
                        }
                        return ps;
                    }
                },
                keyHolder);

        List<Map<String, Object>> keys = keyHolder.getKeyList();
        List<Long> result = new ArrayList<Long>();
        for (Map<String, Object> map : keys) {
            Long key = (Long) map.entrySet().iterator().next().getValue();
            result.add(key);
        }
        return result;
    }

    /**
     * Inserts new lookup value.
     *
     * @param jdbcTemplate
     *            the jdbc template
     * @param keywordId
     *            the keyword id
     * @param value
     *            the value
     *
     * @return the generated id
     */
    public static long insertLookupValue(JdbcTemplate jdbcTemplate, final Long keywordId, final String value) {
        final String insertSql = "insert into lookup_value (keyword_id, value) values (?, ?)";
        return insert(jdbcTemplate, insertSql, keywordId, value);
    }

    /**
     * Gets the keyword id. If not exists, then creates a new record.
     *
     * @param name the keyword name
     * @param jdbcTemplate the jdbc template
     * @return the keyword id
     */
    public static Long getKeywordId(String name, JdbcTemplate jdbcTemplate) {
        Long keywordId = null;
        List<Map<String, Object>> keywordIdList =
            jdbcTemplate.queryForList("select id from keyword where name = ?",
                new Object[] {name});
        if (keywordIdList.size() > 0) {
            keywordId = (Long) keywordIdList.get(0).get("id");
        }
        if (keywordId == null) {
            keywordId = Helper.insert(jdbcTemplate, "insert into keyword (name) values (?)",
                    new Object[] {name});
        }
        return keywordId;
    }

    /**
     * Gets the target type id. If not exists, then creates a new record.
     *
     * @param targetTypeName the name of the target type
     * @param jdbcTemplate the jdbc template
     * @return the target type id
     */
    public static Long getTargetTypeId(String targetTypeName, JdbcTemplate jdbcTemplate) {
        Long targetTypeId = null;
        List<Map<String, Object>> targetTypeIdList =
                jdbcTemplate.queryForList("select id from target_type where name = ?",
                new Object[] {targetTypeName});
        if (targetTypeIdList.size() > 0) {
            targetTypeId = (Long)targetTypeIdList.get(0).get("id");
        }
        if (targetTypeId == null) {
            targetTypeId = Helper.insert(jdbcTemplate, "insert into target_type (name) values (?)",
                    new Object[] {targetTypeName});
        }
        return targetTypeId;
    }

    /**
     * Returns id of the record from the given table with the 'name' column set to the given value.
     *
     * @param objectName
     *              the object's name
     * @param tableName
     *              the table's name
     * @param jdbcTemplate
     *              the JDBC template
     *
     * @return the id of the object with the given name, or null if such object doesn't exist
     */
    public static Long getObjectIdByName(String objectName, String tableName, JdbcTemplate jdbcTemplate) {
        String sql = "select id from " + tableName + " where name = ?";
        List<Map<String, Object>> objectsList = jdbcTemplate.queryForList(sql, objectName);
        return objectsList.isEmpty() ? null : (Long)objectsList.get(0).get("id");
    }

    /**
     * Converts the param value (<code>Object</code>) to a string.
     *
     * @param name the param name
     * @param value the param value
     * @return the string
     */
    private static String toString(String name, Object value) {
        StringBuilder sb = new StringBuilder();
        if (value instanceof Loggable) {
            sb.append(toString(name, (Loggable) value));
        } else if (value instanceof List) {
            sb.append(toString(name, (List<?>) value));
        } else {
            sb.append(name).append(":").append(String.valueOf(value));
        }
        return sb.toString();
    }

    /**
     * Converts the param value (<code>Loggable</code>) value to a string.
     *
     * @param name the param name
     * @param value the param value
     * @return the string
     */
    private static String toString(String name, Loggable value) {
        StringBuilder sb = new StringBuilder();
        if (value == null) {
            sb.append(name).append(":").append(value);
        } else {
            sb.append(name).append(":").append(value.toJSONString());
        }
        return sb.toString();
    }

    /**
     * Converts the param value (<code>List</code>) to a string.
     *
     * @param name the param name
     * @param obj the param value.
     * @return the string.
     */
    private static String toString(String name, List<?> obj) {
        StringBuilder sb = new StringBuilder();

        boolean first = true;
        for (Object element : obj) {
            if (!first) {
                sb.append(", ");
            }
            first = false;
            sb.append(toString(name, element));
        }

        return sb.toString();
    }

    /**
     * Converts the param value (<code>Object</code>) to a string.
     *
     * @param value the param value
     * @return the string
     */
    private static String toString(Object value) {
        if (value instanceof Loggable) {
            return toString((Loggable) value);
        } else if (value instanceof List) {
            return toString((List<?>) value);
        } else {
            return String.valueOf(value);
        }
    }

    /**
     * Converts the param value (<code>Loggable</code>) value to a string.
     *
     * @param value the param value
     * @return the string
     */
    private static String toString(Loggable value) {
        if (value == null) {
            return null;
        } else {
            return value.toJSONString();
        }
    }

    /**
     * Converts the param value (<code>List</code>) to a string.
     *
     * @param obj the param value.
     * @return the string.
     */
    private static String toString(List<?> obj) {
        StringBuilder sb = new StringBuilder();

        boolean first = true;
        for (Object element : obj) {
            if (!first) {
                sb.append(", ");
            }
            first = false;
            sb.append(toString(element));
        }

        return sb.toString();
    }

    /**
     * Reads text file and returns its content.
     * 
     * @param filePath
     *            the file path
     * 
     * @return the file's content
     * 
     * @throws IOException
     *             if failed to read the file's content
     * 
     * @since 1.1
     */
    public static String readFileContent(String filePath) throws IOException {
        final String LINE_SEPARATOR = System.getProperty("line.separator");
        FileInputStream in = new FileInputStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuffer sb = new StringBuffer();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(LINE_SEPARATOR);
            }
        } finally {
            reader.close();
        }
        return sb.toString();
    }
}
