package com.jagaad.ExerciseJagaad.configurations;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.jagaad.myapp.repository")
public class H2DatabaseConfiguration{

    /**
     * Configures the H2 database for the application, including the data source, entity manager factory,
     * transaction manager, and additional properties.
     *
     * @return The configured data source
     */
    @Bean
    public DataSource dataSource(){
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2).addScript("schema.sql").addScript("data.sql").build();
    }

    /**
     * Configures the entity manager factory for the application, including the data source, packages to scan for entities,
     * JPA vendor adapter, and additional properties.
     *
     * @return The configured entity manager factory
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.example.myapp.entity");
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    /**
     * Configures the transaction manager for the entity manager factory.
     *
     * @param entityManagerFactory The entity manager factory to configure the transaction manager for
     * @return The configured transaction manager
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

    /**
     * Configures additional properties for the application, such as the Hibernate dialect and database schema creation strategy.
     *
     * @return The additional properties for the application
     */
    private Properties additionalProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto","create-drop");
        properties.setProperty("hibernate.dialect","org.hibernate.dialect.H2Dialect");

        return properties;
    }
}

