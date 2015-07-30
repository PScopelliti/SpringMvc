package com.springapp.configuration.persistence;

import com.springapp.properties.database.DbConfig;
import com.springapp.properties.database.DbConfigProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Class that define MySQL configuration.
 */
@Configuration
@Profile("production")
public class MySqlDataSourceConfig {

    @Autowired
    private DbConfig dbConfig;

    // Configuring Data Source
    @Bean
    public DataSource dataSource(){
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(dbConfig.getPropertyValue(DbConfigProperty.SPRING_DATASOURCE_URL));
        dataSource.setUsername(dbConfig.getPropertyValue(DbConfigProperty.SPRING_DATASOURCE_USERNAME));
        dataSource.setPassword(dbConfig.getPropertyValue(DbConfigProperty.SPRING_DATASOURCE_PASSWORD));
        return dataSource;
    }

}
