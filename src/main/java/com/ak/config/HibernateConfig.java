package com.ak.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource(value = "classpath:hibernate.properties")
@EnableJpaRepositories(basePackages = "com.ak.dao")
public class HibernateConfig {

    @Autowired
    private Environment settings;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(settings.getRequiredProperty("jdbc.driver.class.name"));
        dataSource.setUsername(settings.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(settings.getRequiredProperty("jdbc.password"));
        dataSource.setUrl(settings.getRequiredProperty("jdbc.url"));

        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", settings.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.show_sql", settings.getProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", settings.getProperty("hibernate.format_sql"));
        properties.put("generate_statistics.hbm2ddl", settings.getProperty("hibernate.generate_statistics"));

        //gł obiekt fabtyki Beanów (potrzebne do mapowania obiektowo-relacyjnego):
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("com.ak.entity");
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setJpaProperties(properties);
        factoryBean.setDataSource(dataSource());

        // definicja beanów wymaga, że wszystkie zostaną zrobiobna zanim zostaną wstrzyknięte (opcjonalne):
        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

    //Obiekt do obsługi transakcji:
    @Bean
    public PlatformTransactionManager transactionManager () {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
        return jpaTransactionManager;
    }

}
