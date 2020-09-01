package org.ecards.configuration.core;


import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@PropertySource("classpath:/org/ecards/configuration/properties/hibernate.properties")
public class JpaConfig {
    
    @Autowired 
    private Environment env;
    
    @Autowired 
    private DataSource dataSource;
    
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter hibernateJpa = new HibernateJpaVendorAdapter();
        hibernateJpa.setDatabasePlatform(env.getProperty("hibernate.dialect"));
        hibernateJpa.setShowSql(env.getProperty("hibernate.show_sql", Boolean.class));
        
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("org.ecards.entities");
        emf.setJpaVendorAdapter(hibernateJpa);
        
        return emf;
    }
    
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager txnMgr = new JpaTransactionManager();
        txnMgr.setEntityManagerFactory(entityManagerFactory().getObject());
        return txnMgr;
    }

}
