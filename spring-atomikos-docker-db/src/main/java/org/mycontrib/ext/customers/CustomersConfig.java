package org.mycontrib.ext.customers;

import java.util.HashMap;

import javax.sql.DataSource;

import org.mycontrib.ext.JtaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@EnableConfigurationProperties
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "org.mycontrib.ext.customers.dao", 
                       entityManagerFactoryRef = "customersEntityManagerFactory", 
                       transactionManagerRef = "transactionManager")
public class CustomersConfig {
	
	private static Logger logger = LoggerFactory.getLogger(CustomersConfig.class);
	
	@Bean
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.customers")
    public DataSource customersDataSource() {
		logger.trace("init customersDataSource in CustomersConfig");
		return new AtomikosDataSourceBean();
    }
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String hibernateDdlAuto; // "none or "create" or ...
	
	
	@Value("${spring.jta.atomikos.datasource.customers.xa-data-source-class-name}")
	private String xaDataSourceClassName;
	
	
	@Bean
	public JpaVendorAdapter customersJpaVendorAdapter() {
		return JtaConfig.jpaVendorAdapterFromXaDataSourceClassName(xaDataSourceClassName);
	}
	
	@Bean(name = "customersEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean customersEntityManagerFactory() throws Throwable {

		HashMap<String, Object> properties = 
				JtaConfig.jpaPropertiesFromXaDataSourceClassNameAndHibernateDdlAuto(
					xaDataSourceClassName,hibernateDdlAuto);

		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setJtaDataSource(customersDataSource());
		entityManagerFactory.setJpaVendorAdapter(customersJpaVendorAdapter());
		entityManagerFactory.setPackagesToScan("org.mycontrib.ext.customers.entity");
		entityManagerFactory.setPersistenceUnitName("customersPersistenceUnit");
		entityManagerFactory.setJpaPropertyMap(properties);
		return entityManagerFactory;
	}

}
