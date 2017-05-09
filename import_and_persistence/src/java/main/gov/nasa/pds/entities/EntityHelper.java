/*
 * Copyright (C) 2011-2013 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.topcoder.json.object.JSONArray;
import com.topcoder.json.object.JSONObject;

/**
 * The Helper class for entity. It contains logic to handle JSONObject and MetadataObject.
 * 
 * Thread Safety: This class is immutable and thread safe.
 * 
 * <p>
 * Version 1.1 changes [PDS - Import and Persistence Update - Assembly Contest]:
 * <ol>
 * <li>Added new date format.</li>
 * <li>Made getPropertyXXX methods public in order to get access from other packages.</li>
 * </ol>
 * </p>
 * 
 * @author KennyAlive
 * @version 1.1
 */
final public class EntityHelper {
    /**
     * The list of supported date formats. Add new date formats here if necessary.
     */
    static final List<String> DATE_FORMATS = Arrays.asList(
            "yyyy-MM-dd'T'HH:mm:ss.SSS",
            "yyyy-MM-dd'T'HH:mm:ss",
            "yyyy-ddd'T'HH:mm:ss.SSS");

    /**
     * Convert list to JSONArray.
     *
     * @param list
     *            - the given list to convert
     * @return converted JSONArray object.
     */
    static JSONArray convertListToJSONArray(List<?> list) {
        JSONArray jsonArray = null;
        if (list != null) {
            jsonArray = new JSONArray();
            for (Object object : list) {
                if (object == null) {
                    jsonArray.addNull();
                } else {
                    if (object instanceof String) {
                        jsonArray.addString((String) object);
                    } else if (object instanceof Loggable) {
                        jsonArray.addJSONObject(((Loggable) object).toJSONObject());
                    } else if (object instanceof List<?>) {
                        jsonArray.addArray(convertListToJSONArray((List<?>) object));
                    } else {
                        jsonArray.addString(object.toString());
                    }
                }
            }
        }
        return jsonArray;
    }

    /**
     * Converts Date to String.
     *
     * @param date
     *            - the given value to convert.
     * @return converted value.
     */
    static String convertDateToString(Date date) {
        String result = null;
        if (date != null) {
            result = date.toString();
        }
        return result;
    }

    /**
     * Sets the given key and String value to JSONObject.
     *
     * @param object
     *            - the given JSONObject to set.
     * @param key
     *            - the given key to set.
     * @param value
     *            - the given value to set.
     */
    static void setString(JSONObject object, String key, String value) {
        if (value == null) {
            object.setNull(key);
        } else {
            object.setString(key, value);
        }
    }

    /**
     * Sets the given key and Boolean value to JSONObject.
     *
     * @param object
     *            - the given JSONObject to set.
     * @param key
     *            - the given key to set.
     * @param value
     *            - the given value to set.
     */
    static void setBoolean(JSONObject object, String key, Boolean value) {
        if (value == null) {
            object.setNull(key);
        } else {
            object.setBoolean(key, value);
        }
    }

    /**
     * Sets the given key and Long value to JSONObject.
     *
     * @param object
     *            - the given JSONObject to set.
     * @param key
     *            - the given key to set.
     * @param value
     *            - the given value to set.
     */
    static void setLong(JSONObject object, String key, Long value) {
        if (value == null) {
            object.setNull(key);
        } else {
            object.setLong(key, value);
        }
    }

    /**
     * Sets the given key and list to JSONObject.
     *
     * @param object
     *            - the given JSONObject to set.
     * @param key
     *            - the given key to set.
     * @param list
     *            - the given value to set.
     */
    static void setArray(JSONObject object, String key, List<?> list) {
        if (list == null) {
            object.setNull(key);
        } else {
            object.setArray(key, convertListToJSONArray(list));
        }
    }

    /**
     * Sets the given key and Integer value to JSONObject.
     *
     * @param object
     *            - the given JSONObject to set.
     * @param key
     *            - the given key to set.
     * @param value
     *            - the given value to set.
     */
    static void setInt(JSONObject object, String key, Integer value) {
        if (value == null) {
            object.setNull(key);
        } else {
            object.setInt(key, value);
        }
    }

    /**
     * Sets the given key and Float value to JSONObject.
     *
     * @param object
     *            - the given JSONObject to set.
     * @param key
     *            - the given key to set.
     * @param value
     *            - the given value to set.
     */
    static void setFloat(JSONObject object, String key, Float value) {
        if (value == null) {
            object.setNull(key);
        } else {
            object.setFloat(key, value);
        }
    }

    /**
     * Converts Object to String.
     *
     * @param obj
     *            - the given value to convert.
     * @return converted value.
     */
    static String convertObjectToString(Object obj) {
        return obj == null ? null : obj.toString();
    }

    /**
     * Sets the given key and Loggable value to JSONObject.
     *
     * @param object
     *            - the given JSONObject to set.
     * @param key
     *            - the given key to set.
     * @param value
     *            - the given value to set.
     */
    static void setNestedObject(JSONObject object, String key, Loggable value) {
        if (value == null) {
            object.setNull(key);
        } else {
            object.setNestedObject(key, value.toJSONObject());
        }
    }

    /**
     * Get Property from given list by names.
     *
     * @param properties
     *            - the given list to search
     * @param names
     *            - the given name to search.
     * @return the found property values, the key is name and the value is property value.
     */
    public static Map<String, Property> getProperties(List<Property> properties, String[] names) {
        Map<String, Property> map = new HashMap<String, Property>();
        if (properties != null) {
            for (Property property : properties) {
                if (property != null) {
                    String name = property.getName();
                    if (name != null) {
                        for (String str : names) {
                            if (name.equals(str)) {
                                map.put(str, property);
                            }
                        }
                    }
                }
            }
        }

        return map;
    }

    /**
     * Gets String value from Property.
     *
     * @param property
     *            - the given Property to get value.
     * @return the String value
     */
    public static String getPropertyStringValue(Property property) {
        String result = null;
        if (property != null) {
            List<String> list = property.getValues();
            if (list != null && !list.isEmpty()) {
                result = list.get(0);
            }
        }
        return result;
    }

    /**
     * Gets RecordType value from Property.
     *
     * @param property
     *            - the given Property to get value.
     * @return the RecordType value
     */
    static RecordType getPropertyRecordTypeValue(Property property) {
        String temp = null;
        if (property != null) {
            List<String> list = property.getValues();
            if (list != null && !list.isEmpty()) {
                temp = list.get(0);
            }
        }

        RecordType type = null;
        if (temp != null) {
            if (temp.equals("FIXED_LENGTH")) {
                type = RecordType.FIXED_LENGTH;
            } else if (temp.equals("STREAM")) {
                type = RecordType.STREAM;
            } else {
                type = RecordType.VARIABLE_LENGTH;
            }
        }
        return type;
    }

    /**
     * Gets TableType value from Property.
     *
     * @param property
     *            - the given Property to get value.
     * @return the TableType value
     */
    static TableType getPropertyTableTypeValue(Property property) {
        String temp = null;
        if (property != null) {
            List<String> list = property.getValues();
            if (list != null && !list.isEmpty()) {
                temp = list.get(0);
            }
        }

        TableType type = null;
        if (temp != null) {
            if (temp.equals("SERIES")) {
                type = TableType.SERIES;
            } else {
                type = TableType.TABLE;
            }
        }
        return type;
    }

    /**
     * Gets InterchangeFormat value from Property.
     *
     * @param property
     *            - the given Property to get value.
     * @return the InterchangeFormat value
     */
    static InterchangeFormat getPropertyInterchangeFormatValue(Property property) {
        String temp = null;
        if (property != null) {
            List<String> list = property.getValues();
            if (list != null && !list.isEmpty()) {
                temp = list.get(0);
            }
        }

        InterchangeFormat format = null;
        if (temp != null) {
            if (temp.equals("ASCII")) {
                format = InterchangeFormat.ASCII;
            } else {
                format = InterchangeFormat.BINARY;
            }
        }
        return format;
    }

    /**
     * Gets Integer value from Property.
     *
     * @param property
     *            - the given Property to get value.
     * @return the Integer value
     */
    public static Integer getPropertyIntegerValue(Property property) {
        Integer result = null;
        String str = getPropertyStringValue(property);
        if (str != null) {
            try {
                result = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                // ignore this exception
            }
        }
        return result;
    }

    /**
     * Gets int value from Property.
     *
     * @param property
     *            - the given Property to get value.
     * @return the int value
     */

    public static int getPropertyIntValue(Property property) {
        int result = 0;
        String str = getPropertyStringValue(property);
        if (str != null) {
            try {
                result = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                // ignore this exception
            }
        }
        return result;
    }

    /**
     * Gets long value from Property.
     *
     * @param property
     *            - the given Property to get value.
     * @return the long value
     */
    public static long getPropertyLongValue(Property property) {
        long result = 0;
        String str = getPropertyStringValue(property);
        if (str != null) {
            try {
                result = Long.parseLong(str);
            } catch (NumberFormatException e) {
                // ignore this exception
            }
        }
        return result;
    }

    /**
     * Gets Date value from Property.
     *
     * @param property
     *            - the given Property to get value.
     * @return the Date value
     */
    public static Date getPropertyDateValue(Property property) {
        Date result = null;
        String str = getPropertyStringValue(property);
        if (str != null) {
            for (int i = 0; i < DATE_FORMATS.size(); i++) {
                DateFormat format = new SimpleDateFormat(DATE_FORMATS.get(i));
                try {
                    result = format.parse(str);
                } catch (ParseException e) {
                    // ignore
                }
                if (result != null) {
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Gets Date value from Property.
     *
     * @param property
     *            - the given Property to get value.
     * @param format
     *            - the format to parse
     * @return the Date value
     */
    public static Date getPropertyDateValue(Property property, String format) {
        Date result = null;
        DateFormat sdf = new SimpleDateFormat(format);
        String str = getPropertyStringValue(property);
        if (str != null) {
            try {
                result = sdf.parse(str);
            } catch (ParseException e) {
            }

        }
        return result;
    }

    /**
     * Gets Float value from Property.
     *
     * @param property
     *            - the given Property to get value.
     * @return the Float value
     */
    public static Float getPropertyFloatValue(Property property) {
        Float result = null;
        String str = getPropertyStringValue(property);
        if (str != null) {
            try {
                result = Float.parseFloat(str);
            } catch (NumberFormatException e) {
                // ignore this exception
            }
        }
        return result;
    }

    /**
     * Populates the given MetaDataObjectConsumer instance by given key and metadataObjects.
     *
     * @param consumer
     *            - the given instance to populate
     * @param key
     *            - the given key to get value.
     * @param metadataObjects
     *            - the given list to get value
     * @return - whether it can populate the instance
     */
    static boolean fromMetadata(MetaDataObjectConsumer consumer, String key, List<MetadataObject> metadataObjects) {
        boolean flag = false;
        if (metadataObjects != null) {
            for (MetadataObject object : metadataObjects) {
                if (object != null && object.getName() != null && object.getName().equals(key)) {
                    consumer.fromMetadata(object);
                    flag = true;
                    break;
                }
            }
        }

        return flag;
    }

    /**
     * Finds the MetadataObject by key from given list.
     *
     * @param key
     *            - the given key to find.
     * @param metadataObjects
     *            - the given list to find.
     * @return - found MetadataObject.
     */
    static MetadataObject findMetadataObject(String key, List<MetadataObject> metadataObjects) {
        MetadataObject foundObject = null;
        if (metadataObjects != null) {
            for (MetadataObject object : metadataObjects) {
                if (object != null && object.getName() != null && object.getName().equals(key)) {
                    foundObject = object;
                    break;
                }
            }
        }

        return foundObject;
    }

    /**
     * Removes used properties in list.
     *
     * @param properties
     *            - the given properties list.
     * @param map
     *            - its values are used to be removed from properties list.
     * @return the properties list.
     */
    static List<Property> removeUsedProperties(List<Property> properties, Map<String, Property> map) {
        if (properties != null && map != null && !map.isEmpty()) {
            properties.removeAll(map.values());
        }
        return properties;
    }

    /**
     * Creates the Reference List.
     *
     * @param key
     *            - the given key to filter
     * @param children
     *            - the given list to find the object.
     * @return - the created Reference list.
     */
    static List<Reference> createReferencesList(String key, List<MetadataObject> children) {
        List<Reference> references = null;
        if (children != null) {
            for (MetadataObject object : children) {
                if (object != null && object.getName() != null && object.getName().equals(key)) {
                    if (references == null) {
                        references = new ArrayList<Reference>();
                    }

                    Reference tempReference = new Reference();
                    tempReference.fromMetadata(object);

                    references.add(tempReference);
                }
            }
        }

        return references;
    }

    /**
     * Creates the Instrument List.
     *
     * @param key
     *            - the given key to filter
     * @param children
     *            - the given list to find the object.
     * @return - the created Instrument list.
     */
    static List<Instrument> createInstrumentsList(String key, List<MetadataObject> children) {
        List<Instrument> instruments = null;
        if (children != null) {
            for (MetadataObject object : children) {
                if (object != null && object.getName() != null && object.getName().equals(key)) {
                    if (instruments == null) {
                        instruments = new ArrayList<Instrument>();
                    }

                    Instrument tempInstrument = new Instrument();
                    tempInstrument.fromMetadata(object);

                    instruments.add(tempInstrument);
                }
            }
        }
        return instruments;
    }

    /**
     * Creates the Column List.
     *
     * @param key
     *            - the given key to filter
     * @param children
     *            - the given list to find the object.
     * @return - the created Column list.
     */
    static List<Column> createColumnsList(String key, List<MetadataObject> children) {
        List<Column> columns = null;
        if (children != null) {
            for (MetadataObject object : children) {
                if (object != null && object.getName() != null && object.getName().equals(key)) {
                    if (columns == null) {
                        columns = new ArrayList<Column>();
                    }

                    Column tempColumn = new Column();
                    tempColumn.fromMetadata(object);

                    columns.add(tempColumn);
                }
            }
        }
        return columns;
    }

    /**
     * Creates the Target List.
     *
     * @param key
     *            - the given key to filter
     * @param children
     *            - the given list to find the object.
     * @return - the created Target list.
     */
    static List<Target> createTargetsList(String key, List<MetadataObject> children) {
        List<Target> targets = null;
        if (children != null) {
            for (MetadataObject object : children) {
                if (object != null && object.getName() != null && object.getName().equals(key)) {
                    if (targets == null) {
                        targets = new ArrayList<Target>();
                    }

                    Target temp = new Target();
                    temp.fromMetadata(object);

                    targets.add(temp);
                }
            }
        }
        return targets;
    }

    /**
     * Creates the Mission List.
     *
     * @param key
     *            - the given key to filter
     * @param children
     *            - the given list to find the object.
     * @return - the created Mission list.
     */
    static List<Mission> createMissionsList(String key, List<MetadataObject> children) {
        List<Mission> missions = null;
        if (children != null) {
            for (MetadataObject object : children) {
                if (object != null && object.getName() != null && object.getName().equals(key)) {
                    if (missions == null) {
                        missions = new ArrayList<Mission>();
                    }

                    Mission temp = new Mission();
                    temp.fromMetadata(object);

                    missions.add(temp);
                }
            }
        }
        return missions;
    }

    /**
     * Creates the InstrumentHost List.
     *
     * @param key
     *            - the given key to filter
     * @param children
     *            - the given list to find the object.
     * @return - the created InstrumentHost list.
     */
    static List<InstrumentHost> createInstrumentHostsList(String key, List<MetadataObject> children) {
        List<InstrumentHost> hosts = null;
        if (children != null) {
            for (MetadataObject object : children) {
                if (object != null && object.getName() != null && object.getName().equals(key)) {
                    if (hosts == null) {
                        hosts = new ArrayList<InstrumentHost>();
                    }

                    InstrumentHost temp = new InstrumentHost();
                    temp.fromMetadata(object);

                    hosts.add(temp);
                }
            }
        }
        return hosts;
    }

    /**
     * Creates the TargetType List.
     *
     * @param key
     *            - the given key to filter
     * @param children
     *            - the given list to find the object.
     * @return - the created TargetType list.
     */
    static List<TargetType> createTargetTypesList(String key, List<MetadataObject> children) {
        List<TargetType> types = null;
        if (children != null) {
            for (MetadataObject object : children) {
                if (object != null && object.getName() != null && object.getName().equals(key)) {
                    if (types == null) {
                        types = new ArrayList<TargetType>();
                    }

                    TargetType temp = new TargetType();
                    temp.fromMetadata(object);

                    types.add(temp);
                }
            }
        }
        return types;
    }
}
