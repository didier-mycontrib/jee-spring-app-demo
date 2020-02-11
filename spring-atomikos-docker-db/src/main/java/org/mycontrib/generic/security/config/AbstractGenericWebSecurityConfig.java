package org.mycontrib.generic.security.config;

import org.mycontrib.generic.security.util.JwtAuthenticationFilter;
import org.mycontrib.generic.security.util.MyNoAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@Configuration in concrete subclass (near MySpringBootApplication/main)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//necessary for @PreAuthorize("hasRole('ADMIN or ...')")
public abstract class AbstractGenericWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    

    @Value("${app.permitAllDevOnly}")
    private boolean permitAllDevOnly;
    
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
	

	@Autowired
    private MyNoAuthenticationEntryPoint unauthorizedHandler;
	

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    protected HttpSecurity configureHttpForRestApi(HttpSecurity http) throws Exception {
    	
    	return http
		// Disable CSRF protection since tokens are immune to it :
		.csrf().disable()
		// Enable Cross Origin Resource Sharing (with @CrossOrigin("...") ...) :
		.cors().and()
		// If the user is not authenticated, returns 401
		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		// This is a stateless application, disable sessions
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		// Custom filter for authenticating users using tokens
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    	
    	//.and().httpBasic() not appropriate
    }
    	
   protected HttpSecurity configureDefaultPermitAllUrl(HttpSecurity http) throws Exception {
    if(this.permitAllDevOnly)
    	return http
    	    	 .authorizeRequests()
    	    	 .antMatchers("/","/*.*","/**/*.*").permitAll().and();
    else
	    return http
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
	 		   .antMatchers("/rest/login-api/public/auth").permitAll()
	 		   .antMatchers("/rest/devise-api/public/**").permitAll()
	 		   .and();
    	
    }
    

}
