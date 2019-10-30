package org.mycontrib.api;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TxForJdbcTemplateConfig {
	
	@Bean //default name ="jdbcTxManager" 
	public PlatformTransactionManager jdbcTxManager(DataSource dataSource) {
	    return new DataSourceTransactionManager(dataSource);
	}
	
	//may be used in @Transactional("jdbcTxManager")
	//with a dao based on jdbcTemplate

}
