package com.springapp.configuration.database;

import com.springapp.configuration.ConfigProperty;


/**
 * Implementation of {@link com.springapp.configuration.ConfigProperty}
 */
public enum DbConfigProperty implements ConfigProperty {

    // DataSource settings: set here your own configurations for the database
    // connection. In this example we have "netgloo_blog" as database name and
    // "root" as username and password.
    SPRING_DATASOURCE_URL("spring.datasource.url", "jdbc:mysql://localhost:8889/netgloo_blog"),
    SPRING_DATASOURCE_USERNAME("spring.datasource.username", "root"),
    SPRING_DATASOURCE_PASSWORD("spring.datasource.password", "root"),


    //Show or not log for each sql query
    SPRING_SHOW_SQL_LOG("spring.jpa.show-sql","true"),

    // Hibernate ddl auto (create, create-drop, update)
    SPRING_DDL_AUTO("spring.jpa.hibernate.ddl-auto" ,"update"),

    // Naming strategy
    SPRING_NAMING_STRATEGY("spring.jpa.hibernate.naming-strategy","org.hibernate.cfg.ImprovedNamingStrategy"),

    // Use spring.jpa.properties.* for Hibernate native properties (the prefix is
    // stripped before adding them to the entity manager)

    //The SQL dialect makes Hibernate generate better SQL for the chosen database.
    SPRING_JPA_DIALECT("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");


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