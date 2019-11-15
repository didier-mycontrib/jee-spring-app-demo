package org.mycontrib.generic.server.config;

import org.mycontrib.generic.security.spring.security.config.GenericWebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
public class WebSecurityConfig extends GenericWebSecurityConfig {	
   
    //@Override
    //protected void configure(final HttpSecurity http) throws Exception
	//already in superclass and calling above sub-method:
	
	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
        //AuthenticationManager will be built via
        //protected void configure(AuthenticationManagerBuilder authMgrBuilder)
        //of GenericWebSecurityConfig super class
    }

	@Override
	protected void apiSpecificHttpConfig(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/",
                "/favicon.ico",
                "/**/*.png",
                "/**/*.gif",
                "/**/*.svg",
                "/**/*.jpg",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js").permitAll()
    	.antMatchers("/login").permitAll()
		.antMatchers("/oauth/token/revokeById/**").permitAll()
		.antMatchers("/tokens/**").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().csrf().disable();
		//.httpBasic().realmName("myRealmName");
		
		//NB: no need of .and().httpBasic() nor .antMatchers("/oauth/token/**")...
		//because automatic "oauth2/client" REALM with BasicHttpAuth for client-id:client-secret
	}

}
