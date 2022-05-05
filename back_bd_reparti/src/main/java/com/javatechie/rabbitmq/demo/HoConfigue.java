package com.javatechie.rabbitmq.demo;




        import java.util.HashMap;

        import javax.sql.DataSource;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
        import org.springframework.boot.context.properties.ConfigurationProperties;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.context.annotation.Primary;
        import org.springframework.core.env.Environment;
        import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
        import org.springframework.orm.jpa.JpaTransactionManager;
        import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
        import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
        import org.springframework.transaction.PlatformTransactionManager;

        import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.javatechie.rabbitmq.demo.repository.Ho", entityManagerFactoryRef = "HoEntityManager", transactionManagerRef = "HoTransactionManager")
public class HoConfigue {

    @Autowired
    Environment env;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "app.datasource.ho")
    public DataSourceProperties HoDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource HoDataSource() {
        return HoDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean HoEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(HoDataSource());
        em.setPackagesToScan("com.javatechie.rabbitmq.demo.entity.Ho");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    @Primary
    public PlatformTransactionManager HoTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(HoEntityManager().getObject());
        return transactionManager;
    }
}