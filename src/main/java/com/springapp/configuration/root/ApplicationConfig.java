package com.springapp.configuration.root;


import com.springapp.Application;
import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

import java.util.Arrays;

import static org.springframework.context.annotation.ComponentScan.Filter;

/**
 * Class for general application configuration.
 */
@Configuration
@ComponentScan(basePackageClasses = Application.class,
        excludeFilters = @Filter({Controller.class, Configuration.class}))
public class ApplicationConfig {

    @Autowired
    Environment environment;

    @PostConstruct
    public void startDBManager() {

        // If active profile is dev, launch H2 database admin.
        if (Arrays.asList(environment.getActiveProfiles()).contains("dev")) {
            //h2
            DatabaseManagerSwing.main(new String[]{"--url",
                    "jdbc:h2:mem:testdb",
                    "--user",
                    "sa",
                    "--password",
                    ""});
        }
    }
}