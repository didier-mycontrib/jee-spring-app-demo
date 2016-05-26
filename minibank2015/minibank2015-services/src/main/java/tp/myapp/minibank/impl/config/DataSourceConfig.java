package tp.myapp.minibank.impl.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {

	// la source de données MySQL
	// équivalent java de dataSourceSpringConf.xml
	
	@Bean(name="myDataSource")
	public DataSource dataSource() {
		//org.apache.commons.dbcp.BasicDataSource (si .jar de commons-dbcp , commons-pool)
		//org.springframework.jdbc.datasource.DriverManagerDataSource
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/minibank_db_ex1");
		dataSource.setUsername("root");
		dataSource.setPassword("root");//"root" ou "formation" ou "..."
		// NB: idéalement avec dataSource.properties
		return dataSource;
	}
	/*
	@Bean(name="myDataSource")
	public DataSource dataSource() {
		//org.apache.commons.dbcp.BasicDataSource (si .jar de commons-dbcp , commons-pool)
		//org.springframework.jdbc.datasource.DriverManagerDataSource
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
		dataSource.setUrl("jdbc:hsqldb:mem:devise_db_mem");
		dataSource.setUsername("SA");
		dataSource.setPassword("");
		// NB: idéalement avec dataSource.properties
		return dataSource;
	}
	*/

}
