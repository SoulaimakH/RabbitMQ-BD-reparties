package com.javatechie.rabbitmq.demo;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;







@Configuration
@EnableJpaRepositories(basePackages = "com.javatechie.rabbitmq.demo.repository.Bo2", entityManagerFactoryRef = "Bo2EntityManager", transactionManagerRef = "Bo2TransactionManager")
public class Bo2configue {

    @Autowired
    Environment env;

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.bo2")
    public DataSourceProperties BO2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource Bo2DataSource() {
        return BO2DataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean Bo2EntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(Bo2DataSource());
        em.setPackagesToScan("com.javatechie.rabbitmq.demo.entity.Bo2");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public PlatformTransactionManager Bo2TransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(Bo2EntityManager().getObject());
        return transactionManager;
    }
}