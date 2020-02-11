package org.mycontrib.generic.security.config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Profile("appDbSecurity") //with jdbc 
public class JdbcAppDbGlobalUserDetailsConfig  {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
   
    private static DataSource realmDataSource;
    
    private static void initRealmDataSource() {
    	DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.h2.Driver");
    	driverManagerDataSource.setUrl("jdbc:h2:~/realmdb");
    	driverManagerDataSource.setUsername("sa");
    	driverManagerDataSource.setPassword("");
    	realmDataSource = driverManagerDataSource;
    }
    
    private boolean isRealmSchemaInitialized() {
    	int nbExistingTablesOfRealmSchema = 0;
    	try {
			Connection cn = realmDataSource.getConnection();
			DatabaseMetaData meta = cn.getMetaData(); 
			String tabOfTableType[] = {"TABLE"};
			ResultSet rs = meta.getTables(null,null,"%",tabOfTableType);
			while(rs.next()){ 
				String existingTableName = rs.getString(3);
				if(existingTableName.equalsIgnoreCase("users")
				    || existingTableName.equalsIgnoreCase("authorities")) {
					nbExistingTablesOfRealmSchema++;
				}
			}
			rs.close(); 
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return (nbExistingTablesOfRealmSchema>=2);
    }
	
	@Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
		initRealmDataSource();
		JdbcUserDetailsManagerConfigurer jdbcUserDetailsManagerConfigurer = 
				auth.jdbcAuthentication()
		        .dataSource(realmDataSource);
		if(isRealmSchemaInitialized()) {
			/*
			 jdbcUserDetailsManagerConfigurer
			.usersByUsernameQuery("select username,password, enabled from users where username=?")
	    	.authoritiesByUsernameQuery("select username, authority from authorities where username=?");
	    	//by default
	    	*/
	    	// or .authoritiesByUsernameQuery("select username, role from user_roles where username=?") if custom schema
		}else {
			//creating default schema and default tables "users" , "authorities"
			jdbcUserDetailsManagerConfigurer.withDefaultSchema();
			//insert default users:
			configureDefaultUsers(jdbcUserDetailsManagerConfigurer);
		}
		 	    
	    
    }
	
	void configureDefaultUsers(UserDetailsManagerConfigurer udmc){
		udmc
		  .withUser("user1").password(passwordEncoder.encode("pwduser1")).roles("USER").and()
	  	  .withUser("admin1").password(passwordEncoder.encode("pwdadmin1")).roles("ADMIN","USER").and()
	  	  .withUser("publisher1").password(passwordEncoder.encode("pwdpublisher1")).roles("PUBLISHER","USER").and()
	  	  .withUser("user2").password(passwordEncoder.encode("pwduser2")).roles("USER").and()
	  	  .withUser("admin2").password(passwordEncoder.encode("pwdadmin2")).roles("ADMIN").and()
	  	  .withUser("publisher2").password(passwordEncoder.encode("pwdpublisher2")).roles("PUBLISHER"); 
	}

    

}
