package org.mycontrib.ext;

import java.util.HashMap;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;


//primary datasource is expected with default auto-config
//use & specify it (aside jta config) or exclude/disable it
/*
@EnableAutoConfiguration(exclude = {
      DataSourceAutoConfiguration.class,
      HibernateJpaAutoConfiguration.class, //if you are using Hibernate
      DataSourceTransactionManagerAutoConfiguration.class
})*/


@Configuration
@ComponentScan
//sub-config in sub-packages : orders.OrdersConfig , customers.CustomersConfig , purchases.PurchasesConfig
public class JtaConfig {
	
	private static Logger logger = LoggerFactory.getLogger(JtaConfig.class);
	
	@Bean(name = "userTransaction")
	public UserTransaction userTransaction() throws Throwable {
		UserTransactionImp userTransactionImp = new UserTransactionImp();
		userTransactionImp.setTransactionTimeout(10000);
		return userTransactionImp;
	}
	
	@Bean(name = "atomikosTransactionManager", initMethod = "init", destroyMethod = "close")
	public TransactionManager atomikosTransactionManager() throws Throwable {
		UserTransactionManager userTransactionManager = new UserTransactionManager();
		userTransactionManager.setForceShutdown(false);
		MyAtomikosJtaPlatform.transactionManager = userTransactionManager;
		return userTransactionManager;
	}

	@Bean(name = "transactionManager")
	@DependsOn({ "userTransaction", "atomikosTransactionManager" })
	public PlatformTransactionManager jtaTransactionManager() throws Throwable {
		UserTransaction userTransaction = this.userTransaction();
		MyAtomikosJtaPlatform.transaction = userTransaction; 
		TransactionManager atomikosTransactionManager = atomikosTransactionManager();
		return new JtaTransactionManager(userTransaction, atomikosTransactionManager);
	}
	
	//fonctions utilitaires (utilisée dans plusieurs autres classes):
	
	public static Database vendorDataBaseFromXaDataSourceClassName(String xaDataSourceClassName) {
		Database db=null;
		switch(xaDataSourceClassName) {
			case "oracle.jdbc.xa.client.OracleXADataSource":
				db=Database.ORACLE;	break;
			case "com.mysql.jdbc.jdbc2.optional.MysqlXADataSource":
			case "com.mysql.cj.jdbc.MysqlXADataSource":
				db=Database.MYSQL;	break;
			case "org.postgresql.xa.PGXADataSource":
				db=Database.POSTGRESQL;	break;
			case "org.h2.jdbcx.JdbcDataSource":
			default:
				db=Database.H2;
		}
		return db;
	}
	
	public static String hibernateDialectFromXaDataSourceClassName(String xaDataSourceClassName) {
		String hbDialect=null;
		switch(xaDataSourceClassName) {
			case "oracle.jdbc.xa.client.OracleXADataSource":
				hbDialect="org.hibernate.dialect.OracleDialect";	break;
			case "com.mysql.jdbc.jdbc2.optional.MysqlXADataSource":
			case "com.mysql.cj.jdbc.MysqlXADataSource":
				/* important : InnoDB engine for transaction, not MyISAM */ 
				hbDialect="org.hibernate.dialect.MySQL5InnoDBDialect";	break;
			case "org.postgresql.xa.PGXADataSource":
				hbDialect="org.hibernate.dialect.PostgreSQLDialect";break;
			case "org.h2.jdbcx.JdbcDataSource":
			default:
				hbDialect="org.hibernate.dialect.H2Dialect";
		}
		return hbDialect;
	}
	
	public static JpaVendorAdapter jpaVendorAdapterFromXaDataSourceClassName(String xaDataSourceClassName) {
		
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		//hibernateJpaVendorAdapter.setShowSql(true);
		/*if(hibernateDdlAuto.equals("create"))
		     hibernateJpaVendorAdapter.setGenerateDdl(true);*/
		Database db = JtaConfig.vendorDataBaseFromXaDataSourceClassName(xaDataSourceClassName);
		hibernateJpaVendorAdapter.setDatabase(db);//Database.H2 or .MYSQL or ...
		return hibernateJpaVendorAdapter;
	}
	
	public static HashMap<String, Object> jpaPropertiesFromXaDataSourceClassNameAndHibernateDdlAuto(
			String xaDataSourceClassName,String hibernateDdlAuto){
		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.transaction.jta.platform", MyAtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");
		/*if(hibernateDdlAuto.equals("create"))
		    properties.put("javax.persistence.schema-generation.database.action", "drop-and-create");*/ 
		properties.put("hibernate.hbm2ddl.auto",hibernateDdlAuto);
		String hbDialect = JtaConfig.hibernateDialectFromXaDataSourceClassName(xaDataSourceClassName);
		properties.put("hibernate.dialect",hbDialect);
		return properties;
	}


}
