package org.mycontrib.tp.serverRest.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.mycontrib.tp.serverRest.dto.ResConversion;
import org.mycontrib.tp.serverRest.entity.Devise;
import org.mycontrib.tp.serverRest.service.ServiceDevise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = { "http://localhost:3000" , "http://localhost:4200" } , methods = { RequestMethod.GET , RequestMethod.POST , RequestMethod.PUT , RequestMethod.DELETE })
@RequestMapping(value="/devise-api" , headers="Accept=application/json")
public class DeviseRestCtrl {
	
	@Autowired
	private ServiceDevise serviceDevise;
	
	//localhost:8585/serverRest/devise-api/public/devise/EUR
	@GetMapping("/public/devise/{codeDevise}")
	public Devise getDeviseByCode(@PathVariable("codeDevise") String codeDevise) {
		System.out.println("getDeviseByCode called with codeDevise="+codeDevise);
		return serviceDevise.rechercherDeviseParCode(codeDevise);
	}
	
	
	//V2 avec exception personnalisée et @ResponseStatus
	//URL = localhost:8585/serverRest/devise-api/role_admin/private/devise 
	// a appeler en mode POST via POSTMAN ou autre
	// et avec { "code" : "M1",	"nom" : "monnaie1",	"change" : 345.67 } 
	// dans la partie invisible body de la requete HTTP
	// et avec Content-Type = application/json dans le header de la requête HTTP
	@PostMapping("/private/role_admin/devise")
	@PreAuthorize("hasAuthority('SCOPE_resource.write')")
	public Devise postNewDevise(@Valid @RequestBody Devise d) {
		
			Devise deviseSauvegardee = serviceDevise.createDevise(d);
			return deviseSauvegardee;
	}
	
	//URL = localhost:8585/serverRest/devise-api/private/role_admin/devise 
	// a appeler en mode PUT via POSTMAN ou autre
	// et avec { "code" : "M1",	"nom" : "monnaie1Bis",	"change" : 675.67 } 
	// dans la partie invisible body de la requete HTTP
	// et avec Content-Type = application/json dans le header de la requête HTTP
	//@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/private/role_admin/devise")
	public Devise updateDevise(@RequestBody Devise d) {
			serviceDevise.updateDevise(d);
			return d;//en tant que devise sauvegardée (mise à jour)
	}
	
	//localhost:8585/serverRest/devise-api/private/role_admin/devise/m1 (DELETE)
	@PreAuthorize("hasAuthority('SCOPE_resource.delete')") 
	@DeleteMapping("/private/role_admin/devise/{codeDevise}")
	public ResponseEntity<?> deleteDeviseByCode(@PathVariable("codeDevise") String codeDevise) {
		serviceDevise.deleteDevise(codeDevise);
		Map<String,Object> mapRes = new HashMap<>();
		mapRes.put("message", "devise bien supprimée pour code="+codeDevise);
		//mapRes.put(autreClef, autreValeur);
		return new ResponseEntity<Object>(mapRes,HttpStatus.OK);
	}
	
	
	//localhost:8585/serverRest/devise-api/public/devise
	//localhost:8585/serverRest/devise-api/public/devise?changeMini=1.05
	@GetMapping("/public/devise")
	//@PreAuthorize("hasAuthority('SCOPE_resource.read')")
	public List<Devise> getDevisesByCriteria(
			  @RequestParam(value="changeMini",required=false) Double changeMini) {
		
		System.out.println("getDevisesByCriteria called with changeMini="+changeMini);
		/*
		List<Devise> devises = serviceDevise.rechercherDevises();
		if(changeMini!=null) {
			devises= 
					devises.stream()
			       .filter((d)->d.getChange()>1.05)
			       .collect(Collectors.toList());
		}*/
		List<Devise> devises = null;
		if(changeMini!=null)
			devises= serviceDevise.rechercherDevisesParChangeMini(changeMini);
		else
			devises = serviceDevise.rechercherDevises();
		return devises;
	}
	
	//localhost:8585/serverRest/devise-api/public/convert?amount=200&source=EUR&target=USD
		@GetMapping("/public/convert")
		public ResConversion  getResConversion(
				@RequestParam(value="amount")
				@ApiParam(value = "amount to convert", example = "100") Double amount,
				@RequestParam(value="source") 
				@ApiParam(value = "source currency code", example = "EUR") String source,
				@RequestParam(value="target") 
				@ApiParam(value = "target currency code", example = "USD") String target) {
			ResConversion resConv = new  ResConversion();
			resConv.setAmount(amount);
			resConv.setSource(source);
			resConv.setTarget(target);
			resConv.setResult(serviceDevise.convertir(amount, source, target));
			return resConv;
		}

}