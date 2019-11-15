package tp.web.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//necessary for @PreAuthorize("hasRole('ADMIN or ...')")
public class WebRestSecurityConfig extends WebSecurityConfigurerAdapter {

	private static Logger logger = LoggerFactory.getLogger(WebRestSecurityConfig.class);
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); 
    }
	
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	/*
    @Bean(name = "realmDataSource")
    @Qualifier("forJdbcRealm")
	public DriverManagerDataSource dataSource() {
	     DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	     driverManagerDataSource.setDriverClassName("org.h2.Driver");
	     driverManagerDataSource.setUrl("jdbc:h2:~/realmdb");
	     driverManagerDataSource.setUsername("sa");
	     driverManagerDataSource.setPassword("");
	     return driverManagerDataSource;
	 }
    
	@Autowired
	@Qualifier("forJdbcRealm")
	private DataSource realmDataSource;
    */

    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
	/*
    auth.jdbcAuthentication()
        .dataSource(realmDataSource)
    	.usersByUsernameQuery("select username,password, enabled from users where username=?")
    	.authoritiesByUsernameQuery("select username, authority from authorities where username=?");
    	//.authoritiesByUsernameQuery("select username, role from user_roles where username=?");    	
    */
    /*
    auth.jdbcAuthentication()
        .dataSource(realmDataSource)
        .withDefaultSchema()        
    */	
    auth.inMemoryAuthentication()
	  .withUser("user1").password(passwordEncoder.encode("pwd1")).roles("USER").and()
	  .withUser("admin1").password(passwordEncoder.encode("pwd1")).roles("ADMIN").and()
	  .withUser("user2").password(passwordEncoder.encode("pwd2")).roles("USER").and()
	  .withUser("admin2").password(passwordEncoder.encode("pwd2")).roles("ADMIN");
    
    }

	
	protected void configure(HttpSecurity http) throws Exception {
                    	
        	http
 	 		 .authorizeRequests()
 	 		   .antMatchers("/",
                    "/favicon.ico",
                    "/**/*.png",
                    "/**/*.gif",
                    "/**/*.svg",
                    "/**/*.jpg",
                    "/**/*.html",
                    "/**/*.css",
                    "/**/*.js").permitAll()
 	 		   //.anyRequest().permitAll()
 	 		   .anyRequest().authenticated()
 	 		   .and().formLogin().permitAll()
 	 		   //.and().httpBasic()
 	 		   .and().csrf();

        	
        	logger.debug("configure rest,  http:" + http.toString());
        }
    
}
