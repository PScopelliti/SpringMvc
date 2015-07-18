package com.springapp.properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a system property implementation of config
 */
@Component
public class SystemPropertyConfig implements Config {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPropertyValue(final ConfigProperty property) {
        return System.getProperty(property.getPropertyName(), property.getDefaultValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPropertyValueAsInt(final ConfigProperty property) {
        return NumberUtils.toInt(getPropertyValue(property), -1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getPropertyValueAsLong(final ConfigProperty property) {
        return NumberUtils.toLong(getPropertyValue(property), -1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getPropertyValueAsBoolean(final ConfigProperty property) {
        if (StringUtils.equalsIgnoreCase(getPropertyValue(property), "true")) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean setPropertyToDefaultIfNotSet(final ConfigProperty property) {
        if (System.getProperty(property.getPropertyName()) != null) {
            return false;
        }
        System.setProperty(property.getPropertyName(), property.getDefaultValue());
        return true;
    }

    @Override
    public Map<String, String> getRawPropertyMap() {
        final Map<String, String> propertyMap = new HashMap<>();
        for(String name : System.getProperties().stringPropertyNames()) {
            propertyMap.put(name, System.getProperty(name));
        }
        return propertyMap;
    }
}
