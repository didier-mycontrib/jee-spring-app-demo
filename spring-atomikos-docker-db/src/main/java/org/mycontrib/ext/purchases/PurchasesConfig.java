package org.mycontrib.ext.purchases;

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
@EnableJpaRepositories(basePackages = "org.mycontrib.ext.purchases.dao", 
                       entityManagerFactoryRef = "purchasesEntityManagerFactory", 
                       transactionManagerRef = "transactionManager")
public class PurchasesConfig {
	
	private static Logger logger = LoggerFactory.getLogger(PurchasesConfig.class);
	
	@Bean
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.purchases")
    public DataSource purchasesDataSource() {
		logger.trace("init purchasesDataSource in PurchasesConfig");
		return new AtomikosDataSourceBean();
    }
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String hibernateDdlAuto; // "none or "create" or ...
	
	
	@Value("${spring.jta.atomikos.datasource.purchases.xa-data-source-class-name}")
	private String xaDataSourceClassName;
	
	@Bean
	public JpaVendorAdapter purchasesJpaVendorAdapter() {
		return JtaConfig.jpaVendorAdapterFromXaDataSourceClassName(xaDataSourceClassName);
	}
	
	@Bean(name = "purchasesEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean purchasesEntityManagerFactory() throws Throwable {

		HashMap<String, Object> properties = 
				JtaConfig.jpaPropertiesFromXaDataSourceClassNameAndHibernateDdlAuto(
					xaDataSourceClassName,hibernateDdlAuto);
		
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setJtaDataSource(purchasesDataSource());
		entityManagerFactory.setJpaVendorAdapter(purchasesJpaVendorAdapter());
		entityManagerFactory.setPackagesToScan("org.mycontrib.ext.purchases.entity");
		entityManagerFactory.setPersistenceUnitName("purchasesPersistenceUnit");
		entityManagerFactory.setJpaPropertyMap(properties);
		return entityManagerFactory;
	}
	

}
