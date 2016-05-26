package tp.app.zz.web.mvc;

//import org.springframework.stereotype.Controller;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tp.app.zz.itf.domain.dto.Devise;
import tp.app.zz.itf.domain.service.GestionDevises;

//@Controller //but not "@Component" for spring web controller
@RestController
@RequestMapping(value="/devises" , headers="Accept=application/json")
public class DeviseListCtrl {
	@Inject
	private GestionDevises serviceDevises; //internal Spring local service 
	
	 @RequestMapping(value="/" , method=RequestMethod.GET)
	    @ResponseBody
	    List<Devise> getAllDevises() {
	       List<Devise> devises = serviceDevises.getListeDevises();
	       return devises;
	    }
	 
	 @RequestMapping(value="/{name}" , method=RequestMethod.GET)
	    @ResponseBody
	    Devise getDeviseByName(@PathVariable("name") String  deviseName) {
	       Devise devise = serviceDevises.getDeviseByName( deviseName);
	       return devise;
	    }
}

    //complete path/url is "http://localhost:8080" 
	//                    + "/deviseSpringBootWeb" (value of server.context-path in application.properties)
	//                    + "/devises"
	

