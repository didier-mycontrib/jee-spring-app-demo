package org.mycontrib.backend;

import org.mycontrib.generic.security.config.AbstractGenericWebSecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@ComponentScan("org.mycontrib.generic.security")
//@ComponentScan() to explore other @Component and
//@Configuration of generic package "org.mycontrib.generic.security"
public class WebSecurityConfig extends AbstractGenericWebSecurityConfig {
	//AbstractGenericWebSecurityConfig is a subclass of WebSecurityConfigurerAdapter
	//with @EnableWebSecurity and other generic security config
	
protected void configure(HttpSecurity http) throws Exception {
		http = super.configureHttpForRestApi(http);
		http = super.configureDefaultPermitAllUrl(http) ;    
    	http
    	 .authorizeRequests()
    	       .antMatchers("/rest/devise-api/public/**").permitAll()
    	       //.antMatchers("/rest/xyz-api/public/**").permitAll()
	 		   .anyRequest().authenticated();
    }

}
