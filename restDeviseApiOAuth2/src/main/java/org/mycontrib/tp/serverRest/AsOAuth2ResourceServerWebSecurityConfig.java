package org.mycontrib.tp.serverRest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@Profile("asOAuth2ResourceServer")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//necessary for @PreAuthorize("hasRole('ADMIN or ...')")
public class AsOAuth2ResourceServerWebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/*
	private static final String[] SWAGGER_AUTH_WHITELIST = {
			"/swagger-resources/**", "/swagger-ui.html", "/v2/api-docs", "/webjars/**"
			};
	*/
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg",
		"/**/*.jpg", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
		//.antMatchers(HttpMethod.POST,"/devise-api/public/login").permitAll()
		.antMatchers("/devise-api/public/**").permitAll()
		//.antMatchers(SWAGGER_AUTH_WHITELIST).permitAll()
		.antMatchers("/read/**").hasAuthority("SCOPE_resource.read")
        .antMatchers("/write/**").hasAuthority("SCOPE_resource.write")
        .anyRequest().authenticated()
        .and().cors() //enable CORS (avec @CrossOrigin sur class @RestController)
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.oauth2ResourceServer()
		    .jwt();
	}

}