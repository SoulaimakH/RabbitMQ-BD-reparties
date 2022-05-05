package com.javatechie.rabbitmq.demo;


import java.util.HashMap;

        import javax.sql.DataSource;

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

        import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.javatechie.rabbitmq.demo.repository.Bo1", entityManagerFactoryRef = "Bo1EntityManager", transactionManagerRef = "Bo1TransactionManager")
public class Bo1configue {

    @Autowired
    Environment env;

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.bo1")
    public DataSourceProperties productDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource productDataSource() {
        return productDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean Bo1EntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(productDataSource());
        em.setPackagesToScan("com.javatechie.rabbitmq.demo.entity.Bo1");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public PlatformTransactionManager Bo1TransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(Bo1EntityManager().getObject());
        return transactionManager;
    }
}