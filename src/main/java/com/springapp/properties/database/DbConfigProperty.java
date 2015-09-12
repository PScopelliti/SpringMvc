package com.springapp.properties.database;

import com.springapp.properties.ConfigProperty;


/**
 * Implementation of {@link ConfigProperty}
 */
public enum DbConfigProperty implements ConfigProperty {

    // DataSource settings: set here your own configurations for the database
    // connection. In this example we have "netgloo_blog" as database name and
    // "root" as username and password.
    SPRING_DATASOURCE_URL("spring.datasource.url", "jdbc:mysql://localhost:3306/Spring"),
    SPRING_DATASOURCE_USERNAME("spring.datasource.username", "root"),
    SPRING_DATASOURCE_PASSWORD("spring.datasource.password", "Peppino83!57"),


    //Show or not log for each sql query
    SPRING_SHOW_SQL_LOG("hibernate.show_sql","true"),
    SPRING_FORMAT_SQL_LOG("hibernate.format_sql","true"),

    // Hibernate ddl auto (validate, create, create-drop, update)
    SPRING_DDL_AUTO("hibernate.hbm2ddl.auto" ,"validate"),

    // Naming strategy
    SPRING_NAMING_STRATEGY("hibernate.ejb.naming_strategy","org.hibernate.cfg.ImprovedNamingStrategy"),

    // Use spring.jpa.properties.* for Hibernate native properties (the prefix is
    // stripped before adding them to the entity manager)

    //The SQL dialect makes Hibernate generate better SQL for the chosen database.
    SPRING_JPA_DIALECT("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

    private final String propertyName;
    private final String defaultValue;

    DbConfigProperty(final String propertyName, final String defaultValue) {
        this.propertyName = propertyName;
        this.defaultValue = defaultValue;
    }

    /**
     * {@inheritDoc}
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * {@inheritDoc}
     */
    public String getDefaultValue() {
        return defaultValue;
    }
}