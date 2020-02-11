package org.mycontrib.ext.devises;

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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
@Configuration
@EnableConfigurationProperties
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "org.mycontrib.ext.devises.dao", 
                       entityManagerFactoryRef = "devisesEntityManagerFactory", 
                       transactionManagerRef = "transactionManager")
public class DevisesConfig {
	
	private static Logger logger = LoggerFactory.getLogger(DevisesConfig.class);
	
	@Bean //default name = method name = "devisesDataSource"
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.devises")
	@Primary //@Primary is required in one of ...DataSourceConfig for H2ConsoleAutoConfiguration
    public DataSource devisesDataSource() {
		logger.trace("init devisesDataSource in DevisesConfig");
		return new AtomikosDataSourceBean();
    }
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String hibernateDdlAuto; // "none or "create" or ...
	
	
	@Value("${spring.jta.atomikos.datasource.devises.xa-data-source-class-name}")
	private String xaDataSourceClassName;
	
	
	@Bean
	@Primary
	public JpaVendorAdapter devisesJpaVendorAdapter() {
		return JtaConfig.jpaVendorAdapterFromXaDataSourceClassName(xaDataSourceClassName);
	}
	
	@Bean(name = "devisesEntityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean devisesEntityManagerFactory() throws Throwable {

		HashMap<String, Object> properties = 
			JtaConfig.jpaPropertiesFromXaDataSourceClassNameAndHibernateDdlAuto(
				xaDataSourceClassName,hibernateDdlAuto);
		
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setJtaDataSource(devisesDataSource());
		entityManagerFactory.setJpaVendorAdapter(devisesJpaVendorAdapter());
		entityManagerFactory.setPackagesToScan("org.mycontrib.ext.devises.entity");
		entityManagerFactory.setPersistenceUnitName("devisesPersistenceUnit");
		entityManagerFactory.setJpaPropertyMap(properties);
		return entityManagerFactory;
	}

}
