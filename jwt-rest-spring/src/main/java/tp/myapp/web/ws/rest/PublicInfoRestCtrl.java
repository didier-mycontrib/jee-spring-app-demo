package tp.myapp.web.ws.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tp.myapp.web.ws.rest.data.Infos;

@RestController 
//@CrossOrigin(origins = "*")
@RequestMapping(value="/public/rest/infos" , headers="Accept=application/json")
public class PublicInfoRestCtrl {

	//.../mvc/public/rest/infos/?category=news
	@RequestMapping(value="/" , method=RequestMethod.GET) 
	public ResponseEntity<Infos> getInfos(@RequestParam(name="category") String category){		
		Infos infos = new Infos(category,"public_info_xyz");
		return new ResponseEntity<Infos>(infos, HttpStatus.OK);
	}

	
}
