package org.mycontrib.generic.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Profile({"basicSecurity","permitAllSecurity"})
public class BasicMemoryGlobalUserDetailsConfig  {
    
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
	auth.inMemoryAuthentication()
	  .withUser("user1").password(passwordEncoder.encode("pwduser1")).roles("USER").and()
	  .withUser("admin1").password(passwordEncoder.encode("pwdadmin1")).roles("ADMIN","USER").and()
	  .withUser("publisher1").password(passwordEncoder.encode("pwdpublisher1")).roles("PUBLISHER","USER").and()
	  .withUser("user2").password(passwordEncoder.encode("pwduser2")).roles("USER").and()
	  .withUser("admin2").password(passwordEncoder.encode("pwdadmin2")).roles("ADMIN").and()
	  .withUser("publisher2").password(passwordEncoder.encode("pwdpublisher2")).roles("PUBLISHER"); 
    }

    

}
