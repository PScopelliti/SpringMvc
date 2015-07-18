package com.springapp.configuration.persistence;

import com.springapp.properties.database.DbConfig;
import com.springapp.properties.database.DbConfigProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Class that define persistence configuration.
 */
@Configuration
public class PersistenceConfiguration {

    private final DbConfig dbConfig;

    @Autowired
    public PersistenceConfiguration(final DbConfig dbConfig){
        this.dbConfig = dbConfig;
    }

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

    // Configuring entity manager factory bean.
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"org.baeldung.persistence.model"});

        final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    // Configuring transaction manager
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties additionalProperties(){
        final Properties properties = new Properties();
        //Configures the used database dialect. This allows Hibernate to create SQL
        //that is optimized for the used database.
        properties.put("hibernate.dialect", dbConfig.getPropertyValue(DbConfigProperty.SPRING_JPA_DIALECT));

        //Specifies the action that is invoked to the database when the Hibernate
        //SessionFactory is created or closed.
        properties.put("hibernate.hbm2ddl.auto",
                dbConfig.getPropertyValue(DbConfigProperty.SPRING_DDL_AUTO));

        //Configures the naming strategy that is used when Hibernate creates
        //new database objects and schema elements
        properties.put("hibernate.ejb.naming_strategy",
                dbConfig.getPropertyValue(DbConfigProperty.SPRING_NAMING_STRATEGY));

        //If the value of this property is true, Hibernate writes all SQL
        //statements to the console.
        properties.put("hibernate.show_sql",
                dbConfig.getPropertyValue(DbConfigProperty.SPRING_SHOW_SQL_LOG));

        //If the value of this property is true, Hibernate will format the SQL
        //that is written to the console.
        properties.put("hibernate.format_sql",
                dbConfig.getPropertyValue(DbConfigProperty.SPRING_FORMAT_SQL_LOG));

        return properties;
    }
}
