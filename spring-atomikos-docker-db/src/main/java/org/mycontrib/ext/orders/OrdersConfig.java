package org.mycontrib.ext.orders;

import java.util.HashMap;

import javax.sql.DataSource;

import org.mycontrib.ext.JtaConfig;
import org.mycontrib.ext.MyAtomikosJtaPlatform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableConfigurationProperties
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "org.mycontrib.ext.orders.dao", 
                       entityManagerFactoryRef = "ordersEntityManagerFactory", 
                       transactionManagerRef = "transactionManager")
public class OrdersConfig {
	
private static Logger logger = LoggerFactory.getLogger(OrdersConfig.class);
	
	@Bean
	@ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.orders")
	public DataSource ordersDataSource() {
		logger.trace("init ordersDataSource in OrdersConfig");
		return new AtomikosDataSourceBean();
	}
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String hibernateDdlAuto; // "none or "create" or ...
	
	
	@Value("${spring.jta.atomikos.datasource.orders.xa-data-source-class-name}")
	private String xaDataSourceClassName;
	
	
	@Bean
	public JpaVendorAdapter ordersJpaVendorAdapter() {
		return JtaConfig.jpaVendorAdapterFromXaDataSourceClassName(xaDataSourceClassName);
	}
	
	@Bean(name = "ordersEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean ordersEntityManagerFactory() throws Throwable {

		HashMap<String, Object> properties = 
				JtaConfig.jpaPropertiesFromXaDataSourceClassNameAndHibernateDdlAuto(
					xaDataSourceClassName,hibernateDdlAuto);
				
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setJtaDataSource(ordersDataSource());
		entityManagerFactory.setJpaVendorAdapter(ordersJpaVendorAdapter());
		entityManagerFactory.setPackagesToScan("org.mycontrib.ext.orders.entity");
		entityManagerFactory.setPersistenceUnitName("ordersPersistenceUnit");
		entityManagerFactory.setJpaPropertyMap(properties);
		return entityManagerFactory;
	}


}
