package com.springapp.configuration.root;


import com.springapp.Application;
import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

import static org.springframework.context.annotation.ComponentScan.Filter;

/**
 * Class for general application configuration.
 */
@Configuration
@ComponentScan(basePackageClasses = Application.class,
        excludeFilters = @Filter({Controller.class, Configuration.class}))
public class ApplicationConfig {


    @Profile("dev")
    @PostConstruct
    public void startDBManager() {
        //h2
        DatabaseManagerSwing.main(new String[]{"--url", "jdbc:h2:mem:testdb", "--user", "sa", "--password", ""});
    }
}