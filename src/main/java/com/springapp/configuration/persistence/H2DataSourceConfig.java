package com.springapp.configuration.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Class that define Embedded configuration useful for dev and tests.
 */
@Configuration
@Profile("dev")
public class H2DataSourceConfig {

    // Configuring Data Source
    @Bean
    public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder().
                setType(EmbeddedDatabaseType.H2).
//                addScript("db-schema.sql").
//                addScript("db-test-data.sql").
                build();
    }
}
