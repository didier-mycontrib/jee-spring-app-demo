package org.mycontrib.generic.security.rest;

import org.mycontrib.generic.security.dto.AuthRequest;
import org.mycontrib.generic.security.dto.AuthResponse;
import org.mycontrib.generic.security.util.JwtTokenProvider;
import org.mycontrib.generic.security.util.MySpringSecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

// sign up = subscribe/register = s'inscrire

// sign in = login = se connecter

@Component
public class AuthInternalSubController {
	
	private static Logger logger = LoggerFactory.getLogger(AuthInternalSubController.class);
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired(required=false)
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest loginRequest) {    	
    	logger.debug("/auth , loginRequest:"+loginRequest);
    	
    	//NB: authenticationManager is built/configure in GenericWebSecurityConfig
    	//with AuthenticationManagerBuilder and UserAccountDetailsService
        Authentication authentication = null;
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUsername(loginRequest.getUsername());
        authResponse.setRoles(loginRequest.getRoles());
        try {
			authentication=authenticationManager.authenticate(
			        new UsernamePasswordAuthenticationToken(
			                loginRequest.getUsername(),
			                loginRequest.getPassword()
			        )
			);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = tokenProvider.generateToken(authentication);
			
			if(MySpringSecurityUtil.testRequiredRoles(loginRequest.getRoles(),
					                                  MySpringSecurityUtil.roleNameList(authentication))){
				authResponse.setToken(jwt);
		        authResponse.setStatus(true);
		        authResponse.setMessage("login successful");
		        logger.debug("/login authResponse:" + authResponse.toString());
		        return ResponseEntity.ok(authResponse);
			}else {
				authResponse.setToken(null);
		        authResponse.setStatus(false);
		        authResponse.setMessage("login failed. Good username/password but no required role(s)");
		        logger.debug("/login authResponse:" + authResponse.toString());
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
  		              .body(authResponse);
			}
		} catch (AuthenticationException e) {
			logger.debug("echec authentification:" + e.getMessage()); //for log
			 authResponse.setStatus(false);
		     authResponse.setMessage("echec authentification. wrong username or password");
		     return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
		    		              .body(authResponse);
		    		            
		}
        
    }
}


