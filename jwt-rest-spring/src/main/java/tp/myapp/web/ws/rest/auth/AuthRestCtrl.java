
package tp.myapp.web.ws.rest.auth;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tp.myapp.auth.token.TokenManager;
import tp.myapp.auth.token.jwt.JwtTokenManager;
import tp.myapp.web.ws.rest.data.auth.AuthResponse;
import tp.myapp.web.ws.rest.data.auth.BasicCredential;

@RestController 
//@CrossOrigin(origins = "*")
@RequestMapping(value="/public/rest/auth" , headers="Accept=application/json")
public class AuthRestCtrl {
	
	private static Logger logger = LoggerFactory.getLogger(AuthRestCtrl.class);
	
	//private TokenManager tokenManager = BasicUuidTokenManager.getInstance();
	private TokenManager tokenManager = JwtTokenManager.getInstance();
	
	private boolean verifyAuth(BasicCredential basicCredential){
		boolean authOk=false;
		if(basicCredential.getPassword()!= null
		&& basicCredential.getPassword().equals("pwd_"+basicCredential.getUsername())){
			authOk=true;
		}
		return authOk;
	}
	
	@RequestMapping(value="/basic" , method=RequestMethod.POST)
	// pour URL = ...webapp/mvc/public/rest/auth/basic
	// input : BasicCredential (json) with username and password to verify
	// return : AuthResponse (json) with token , roles , authOk=false or true 
	public ResponseEntity<AuthResponse> basicAuth(@RequestBody BasicCredential basicCredential){		
		AuthResponse authResponse = new AuthResponse();
		logger.debug("in AuthRestCtrl.basicAuth, basicCredential: " + basicCredential );
		authResponse.setUsername(basicCredential.getUsername());
		boolean authOk=false;
		HttpHeaders headers = new HttpHeaders();
		authOk=verifyAuth(basicCredential);
		if(authOk){
		  //String newToken = tokenManager.generateToken(basicCredential.getUsername());
		  List<String> roles = Arrays.asList(/*"admin",*/ TokenManager.DEFAULT_ROLENAME );
		  String newToken = tokenManager.generateToken(basicCredential.getUsername(),roles);
		  authResponse.setAuthToken(newToken);
		  authResponse.setRoles(roles);
		  headers.set("AUTH_TOKEN", newToken); //"AUTH_TOKEN" or ???
		}
		authResponse.setAuthOk(authOk);
		ResponseEntity<AuthResponse> response = 
				new ResponseEntity<AuthResponse>(authResponse, headers,HttpStatus.OK);
		return response;
	}

	
}
