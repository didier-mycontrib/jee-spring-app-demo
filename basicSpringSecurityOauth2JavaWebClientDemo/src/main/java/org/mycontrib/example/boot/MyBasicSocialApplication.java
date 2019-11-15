package org.mycontrib.example.boot;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CompositeFilter;

@SpringBootApplication
@EnableOAuth2Client //in place of @EnableOAuth2Sso for a more explicit configuration
@RestController
public class MyBasicSocialApplication  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	  OAuth2ClientContext oauth2ClientContext;
	
	@RequestMapping("/user")
	  public Principal user(Principal principal) {
	    return principal;
	  }
	
	public static void main(String[] args) {
		SpringApplication.run(MyBasicSocialApplication.class, args);
	}
	
	/*
	//config V1: with redundant code : 
	
	  @Bean
	  @ConfigurationProperties("facebook.client")  // to read properties under facebook.client instead of security.oauth2 in application.properties or application.yml
	  public AuthorizationCodeResourceDetails facebook() {
	    return new AuthorizationCodeResourceDetails();
	  }
	  
	  @Bean
	  @ConfigurationProperties("facebook.resource")
	  public ResourceServerProperties facebookResource() {
	    return new ResourceServerProperties();
	  }
	  
	  @Bean
	  @ConfigurationProperties("github.client")
	  public AuthorizationCodeResourceDetails github() {
	  	return new AuthorizationCodeResourceDetails();
	  }

	  @Bean
	  @ConfigurationProperties("github.resource")
	  public ResourceServerProperties githubResource() {
	  	return new ResourceServerProperties();
	  }
	  
	  private Filter ssoFilter() {

		  CompositeFilter filter = new CompositeFilter();
		  List<Filter> filters = new ArrayList<>();

		  OAuth2ClientAuthenticationProcessingFilter facebookFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/facebook");
		  OAuth2RestTemplate facebookTemplate = new OAuth2RestTemplate(facebook(), oauth2ClientContext);
		  facebookFilter.setRestTemplate(facebookTemplate);
		  UserInfoTokenServices tokenServices = new UserInfoTokenServices(facebookResource().getUserInfoUri(), facebook().getClientId());
		  tokenServices.setRestTemplate(facebookTemplate);
		  facebookFilter.setTokenServices(tokenServices);
		  filters.add(facebookFilter);

		  OAuth2ClientAuthenticationProcessingFilter githubFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/github");
		  OAuth2RestTemplate githubTemplate = new OAuth2RestTemplate(github(), oauth2ClientContext);
		  githubFilter.setRestTemplate(githubTemplate);
		  tokenServices = new UserInfoTokenServices(githubResource().getUserInfoUri(), github().getClientId());
		  tokenServices.setRestTemplate(githubTemplate);
		  githubFilter.setTokenServices(tokenServices);
		  filters.add(githubFilter);

		  filter.setFilters(filters);
		  return filter;

		}
	*/
	
	//Config V2 with wrapper class "ClientResources" and  @NestedConfigurationProperty:
	class ClientResources {

		  @NestedConfigurationProperty
		  private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

		  @NestedConfigurationProperty
		  private ResourceServerProperties resource = new ResourceServerProperties();

		  public AuthorizationCodeResourceDetails getClient() {
		    return client;
		  }

		  public ResourceServerProperties getResource() {
		    return resource;
		  }
		}
	
	@Bean
	@ConfigurationProperties("github")
	public ClientResources github() {
	  return new ClientResources();
	}

	@Bean
	@ConfigurationProperties("facebook")
	public ClientResources facebook() {
	  return new ClientResources();
	}

	private Filter ssoFilter(ClientResources client, String path) {
		  OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
		  OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
		  filter.setRestTemplate(template);
		  UserInfoTokenServices tokenServices = new UserInfoTokenServices(
		      client.getResource().getUserInfoUri(), client.getClient().getClientId());
		  tokenServices.setRestTemplate(template);
		  filter.setTokenServices(tokenServices);
		  return filter;
		}
	
	private Filter ssoFilter() {
		  CompositeFilter filter = new CompositeFilter();
		  List<Filter> filters = new ArrayList<>();
		  filters.add(ssoFilter(facebook(), "/login/facebook"));
		  filters.add(ssoFilter(github(), "/login/github"));
		  filter.setFilters(filters);
		  return filter;
		}
	 //End of V2 configuration of facebook , github , ...     
	
	  @Bean
	  public FilterRegistrationBean oauth2ClientFilterRegistration(
	      OAuth2ClientContextFilter filter) {
	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(filter);
	    registration.setOrder(-100); //IMPORTANT
	    return registration;
	  }
	
	  
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http
	      .antMatcher("/**")
	      .authorizeRequests()
	        .antMatchers("/", "/login**", "/webjars/**", "/error**")
	        .permitAll()
	      .anyRequest()
	        .authenticated()
	    .and().logout().logoutSuccessUrl("/").permitAll() /* build in POST logout */
	    .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
	    .and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
	  }

}
