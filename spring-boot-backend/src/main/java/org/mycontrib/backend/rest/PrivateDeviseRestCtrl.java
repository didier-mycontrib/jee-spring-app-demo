package org.mycontrib.backend.rest;

import javax.validation.Valid;

import org.mycontrib.backend.entity.Devise;
import org.mycontrib.backend.service.Convertisseur;
import org.mycontrib.backend.service.DeviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")//pour accepter de répondre à des appels ajax 
                 //provenant d'autres domaines/applications/...

@RestController //@Component de type @RestController
@RequestMapping(value="/rest/devise-api/private/role_admin" , headers="Accept=application/json")
public class PrivateDeviseRestCtrl {

	@Autowired //injection
	private DeviseService deviseService;
	
	//URL= http://localhost:8181/spring-boot-backend/rest/devise-api/private/role_admin/devise
	//en mode POST avec la partie body invisible de la requete contenant
	//{ "code" : "cxy" , "monnaie" : "monnaieXy" , "tauxChange": 2.2  }
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="devise" , method=RequestMethod.POST)
	public Devise createOrUpdateDevise(@RequestBody @Valid Devise devise) {
		deviseService.saveOrUpdate(devise);
		return devise; //quelquefois (pas ici) l'objet retourné comporte id auto_incr
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="devise" , method=RequestMethod.PUT)
	public Devise updateDevise(@RequestBody @Valid Devise devise) {
		return createOrUpdateDevise(devise);
	}
	
	//URL= http://localhost:8181/spring-boot-backend/rest/devise-api/private/role_admin/devise/EUR
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="devise/{codeDevise}" , method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteDeviseByCode(@PathVariable("codeDevise") 
	                                            String codeDevise) {
		//try {
			deviseService.deleteByCode(codeDevise);
			return new ResponseEntity<>(HttpStatus.OK);
		/*} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}*/ //traitement d'exception dejà effectué par defaut
	}
	
	
}
