package com.springapp.configuration;

import java.util.Map;

/**
 * Represents an abstract client configuration that contains a number of {@link ConfigProperty}s
 */
public interface Config {

    /**
     * Get property value from config property
     *
     * @param property
     * @return
     */
    String getPropertyValue(ConfigProperty property);

    /**
     * Get property value from config property
     *
     * @param property
     * @return -1 if property doesn't have a parsable default we can use (such as null).
     */
    int getPropertyValueAsInt(ConfigProperty property);

    /**
     * Get property value from config property
     *
     * @param property
     * @return -1 if property doesn't have a parsable default we can use (such as null).
     */
    long getPropertyValueAsLong(ConfigProperty property);

    /**
     * Get property value from config property
     *
     * @param property
     * @return true if property string value is "TRUE" or "true" otherwise return false
     */
    boolean getPropertyValueAsBoolean(ConfigProperty property);

    /**
     * Set a given property to its default value if not already set
     *
     * @param property
     * @return
     */
    boolean setPropertyToDefaultIfNotSet(ConfigProperty property);

    /**
     * Get a map of raw properties
     * @return
     */
    Map<String, String> getRawPropertyMap();
}