package com.springapp.properties;

/**
 * Represents an abstract client property
 */
public interface ConfigProperty {

    /**
     * Get the property's name
     * @return
     */
    String getPropertyName();

    /**
     * Get the property's default value
     * @return
     */
    String getDefaultValue();
}
