package tp.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import tp.entity.Devise;
import tp.service.GestionDevises;

@RestController 
@RequestMapping(value="/rest/devises" , headers="Accept=application/json")
public class DeviseJsonRestCtrl {
	
	
	@Autowired //ou @Inject
	private GestionDevises gestionDevises;
	
	
	 @RequestMapping(value="/" , method=RequestMethod.GET) 
	 @ResponseBody  
	 List<Devise> getDevisesByCriteria(@RequestParam(value="name",required=false)String nomMonnaie) {
		 if(nomMonnaie==null)
			 return gestionDevises.getListeDevises();
		 else{
			 List<Devise> listeDev= new ArrayList<Devise>();
			 Devise devise = gestionDevises.getDeviseByName(nomMonnaie);
			 if(devise!=null)
				 listeDev.add(devise);
			 return listeDev;
		     }
		 } 
	 
	
	 @RequestMapping(value="/" , method=RequestMethod.PUT ) 
	 @ResponseBody  
	 Devise updateDevise(@RequestBody String deviseAsJsonString) {
		 Devise devise=null;
		 try {
			ObjectMapper jacksonMapper = new ObjectMapper();
			//jacksonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			devise = jacksonMapper.readValue(deviseAsJsonString,Devise.class);
			System.out.println("devise to update:" + devise);			
			gestionDevises.updateDevise(devise);
			return devise;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	} 
	 
	 /*//méthode non autorisée ?
	 @RequestMapping(value="/" , method=RequestMethod.PUT )   
	 ResponseEntity<Devise> updateDevise(@RequestBody Devise devise) {
		 try {
			System.out.println("devise to update:" + devise);			
			gestionDevises.updateDevise(devise);
			return new ResponseEntity<Devise>(devise, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			 return new ResponseEntity<Devise>(HttpStatus.NOT_MODIFIED);
		}
	}*/
	 
	 
	 /*
	 @RequestMapping(value="/{codeDevise}" , method=RequestMethod.GET) 
	 @ResponseBody 
	 Devise getDeviseByName(@PathVariable("codeDevise") String  codeDevise) { 
		 return gestionDevises.getDeviseByPk(codeDevise);  
		 }
	 */
	 
	 @RequestMapping(value="/{codeDevise}" , method=RequestMethod.GET) 
	 ResponseEntity<Devise> getDeviseByName(@PathVariable("codeDevise") String  codeDevise) { 
		 Devise dev = gestionDevises.getDeviseByPk(codeDevise);
		 return new ResponseEntity<Devise>(dev, HttpStatus.OK);
		 }
	 
	 //convert?amount=50&src=EUR&target=USD
	 @RequestMapping(value="/convert" , method=RequestMethod.GET , headers="Accept=text/plain") 
	 @ResponseBody 
	 String convert(@RequestParam("amount") double  amount,
			        @RequestParam("src") String  src ,
			        @RequestParam("target") String  target) { 
	double sommeConvertie=gestionDevises.convertir(amount, src, target);
	System.out.println("sommeConvertie="+sommeConvertie);
	return String.valueOf(sommeConvertie);
	}
	 
}

