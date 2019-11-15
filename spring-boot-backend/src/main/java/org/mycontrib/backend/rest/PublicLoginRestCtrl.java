package org.mycontrib.backend.rest;

import org.mycontrib.generic.security.dto.AuthRequest;
import org.mycontrib.generic.security.rest.AuthInternalSubController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")//pour accepter de répondre à des appels ajax 
                 //provenant d'autres domaines/applications/...

//sign up = subscribe/register = s'inscrire

//sign in = login = se connecter

@RestController //@Component de type @RestController
@RequestMapping(value="/rest/login-api/public" , headers="Accept=application/json")
public class PublicLoginRestCtrl {
	
	@Autowired
	private AuthInternalSubController authInternalSubController;
	
	
	@PostMapping("/auth")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest loginRequest) {
		return authInternalSubController.authenticateUser(loginRequest);
	}

}
