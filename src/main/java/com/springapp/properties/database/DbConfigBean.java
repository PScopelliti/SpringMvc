package com.springapp.properties.database;

import com.springapp.properties.SystemPropertyConfig;
import org.springframework.stereotype.Component;

/**
 * System Property implementation of {@link com.springapp.properties.database.DbConfigBean}
 */
@Component
public class DbConfigBean extends SystemPropertyConfig implements DbConfig {

    /**
     * Use main database.properties
     * @return
     */
    public String getPropFileName() {
        return "database.properties";
    }
}
