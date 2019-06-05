package org.mycontrib.api.rest.security;

import org.mycontrib.api.data.security.AuthRequest;
import org.mycontrib.api.data.security.AuthResponse;
import org.mycontrib.api.security.util.JwtTokenProvider;
import org.mycontrib.api.security.util.MySpringSecurityUtil;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// sign up = subscribe/register = s'inscrire

// sign in = login = se connecter

@RestController
@RequestMapping(value="/rest/auth" , 
                headers="Accept=application/json")
public class AuthController {
	
	private static Logger logger = LoggerFactory.getLogger(AuthController.class);
	

	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired(required=false)
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest loginRequest) {    	
    	logger.debug("/login , loginRequest:"+loginRequest);
    	
    	//NB: authenticationManager is built/configure in GenericWebSecurityConfig
    	//with AuthenticationManagerBuilder and UserAccountDetailsService
        Authentication authentication = null;
        AuthResponse authResponse = new AuthResponse();
        try {
			authentication=authenticationManager.authenticate(
			        new UsernamePasswordAuthenticationToken(
			                loginRequest.getUsername(),
			                loginRequest.getPassword()
			        )
			);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = tokenProvider.generateToken(authentication);
	        authResponse.setAuthToken(jwt);
	        authResponse.setAuthOk(true);
	        authResponse.setRoleNames(MySpringSecurityUtil.roleNameList(authentication));
	        authResponse.setMessage("login successful");
	
	        logger.debug("/login authResponse:" + authResponse.toString());
	        return ResponseEntity.ok(authResponse);
		} catch (AuthenticationException e) {
			logger.debug("echec authentification:" + e.getMessage()); //for log
			 authResponse.setAuthOk(false);
		     authResponse.setMessage("echec authentification");
		     return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
		    		              .body(authResponse);
		    		            
		}
        
    }
}


