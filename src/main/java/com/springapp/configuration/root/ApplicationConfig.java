package com.springapp.configuration.root;


import com.springapp.Application;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import static org.springframework.context.annotation.ComponentScan.Filter;

/**
 *  Class for general application configuration.
 */
@Configuration
@ComponentScan(basePackageClasses = Application.class,
        excludeFilters = @Filter({Controller.class, Configuration.class}))
public class ApplicationConfig {


}