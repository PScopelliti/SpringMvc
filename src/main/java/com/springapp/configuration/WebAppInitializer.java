package com.springapp.configuration;

import com.springapp.configuration.persistence.PersistenceConfiguration;
import com.springapp.configuration.root.ApplicationConfig;
import com.springapp.configuration.security.SecurityConfiguration;
import com.springapp.configuration.web.WebMvcConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ApplicationConfig.class,
                PersistenceConfiguration.class,
                SecurityConfiguration.class
               // MethodSecurityConfiguration.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebMvcConfig.class};
    }

    // It's better to setup a profile in tomcat config
//    @Override
//    public void onStartup(final ServletContext servletContext) throws ServletException {
//        super.onStartup(servletContext);
//        servletContext.setInitParameter("spring.profiles.active", "production");
//    }
}