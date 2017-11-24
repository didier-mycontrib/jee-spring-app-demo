package tp.myapp.web.ws.rest.restrict;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tp.myapp.web.ws.rest.data.Infos;
import tp.myapp.web.ws.rest.interceptor.annotation.AuthTokenRequired;

@RestController 
//@CrossOrigin(origins = "*")
@RequestMapping(value="/private/rest/confidential-infos" , headers="Accept=application/json")
public class ConfidentialInfoRestCtrl {
	
	
	//.../mvc/private/rest/confidential-infos/?category=news
	@RequestMapping(value="/" , method=RequestMethod.GET) 
	@AuthTokenRequired(/*requiredRole="admin"*/) //check by MyMvcAuthInterceptor
	public ResponseEntity<Infos> getInfos(@RequestParam(name="category") String category){				
		   Infos infos = new Infos(category,"confidential_info_xyz");
		   return new ResponseEntity<Infos>(infos, HttpStatus.OK);		
	}

	
}
