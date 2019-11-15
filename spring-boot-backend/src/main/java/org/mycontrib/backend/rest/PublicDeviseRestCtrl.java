package org.mycontrib.backend.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.mycontrib.backend.dto.ResConv;
import org.mycontrib.backend.entity.Devise;
import org.mycontrib.backend.service.Convertisseur;
import org.mycontrib.backend.service.DeviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")//pour accepter de répondre à des appels ajax 
                 //provenant d'autres domaines/applications/...

@RestController //@Component de type @RestController
@RequestMapping(value="/rest/devise-api/public" , headers="Accept=application/json")
public class PublicDeviseRestCtrl {

	@Autowired //injection du "business service" 
	private Convertisseur convertisseur;
	
	@Autowired 
	private DeviseService deviseService;
	
	
	
	//URL : ..../rest/devise-api/public/convert?source=EUR&target=USD&amount=100
	@RequestMapping(value="/convert" , method=RequestMethod.GET)
	public ResConv convertir(@RequestParam("amount")Double montant,
							@RequestParam("source")String source,
							@RequestParam("target")String cible) {
		Double res = convertisseur.convertir(montant, source, cible);
		return new ResConv(montant, source, cible,res);
	}
	
	
	
	//URL= http://localhost:8181/spring-boot-backend/rest/devise-api/public/devise/EUR
	@RequestMapping(value="devise/{codeDevise}" , method=RequestMethod.GET)
	public ResponseEntity<?> getDeviseByCode(@PathVariable("codeDevise") String codeDevise) {
		Devise d = deviseService.deviseByCode(codeDevise);
		if(d!=null)
			return new ResponseEntity<Devise>(d,HttpStatus.OK);//200=OK
		else
			return new ResponseEntity<String>
		      ("{ \"err\" : \"pas bras, pas de chocolat\"}",
		    		  HttpStatus.NOT_FOUND) ;//404=NOT_FOUND
	}
	
	//URL= http://localhost:8181/spring-boot-backend/rest/devise-api/public/devise
	//URL= http://localhost:8181/spring-boot-backend/rest/devise-api/public/devise?changeMini=1
	@RequestMapping(value="devise" , method=RequestMethod.GET)
	public List<Devise> getDevises(@RequestParam(value="changeMini",
	                               required=false) Double tauxChangeMini) {
		
		List<Devise> toutesDevises = (List<Devise>) deviseService.getAllDevises();
		
		if(tauxChangeMini==null)
			return toutesDevises;
		else
			return toutesDevises.stream()
					  .filter((d)-> d.getChange()>=tauxChangeMini)
					  .collect(Collectors.toList());
	}

}
