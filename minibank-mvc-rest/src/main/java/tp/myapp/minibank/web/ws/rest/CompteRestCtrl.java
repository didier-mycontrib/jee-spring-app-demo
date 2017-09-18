package tp.myapp.minibank.web.ws.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import tp.myapp.minibank.details.DetailsLevel;
import tp.myapp.minibank.itf.domain.service.GestionComptes;
import tp.myapp.minibank.itf.domain.service.MyServiceException;
import tp.myapp.minibank.persistence.entity.Compte;
import tp.myapp.minibank.persistence.entity.Operation;
import tp.myapp.minibank.web.ws.rest.data.Transfert;

@RestController 
@RequestMapping(value="/rest/comptes" , headers="Accept=application/json")
@CrossOrigin(origins="*")
public class CompteRestCtrl {
	@Autowired
	GestionComptes gestionComptes; //internal service
	
	//@JsonView(DetailsLevel.MostDetails.class)
	@JsonView(DetailsLevel.Essential.class)
	@RequestMapping(value="/{numCpt}" , method=RequestMethod.GET)
	public ResponseEntity<Compte> compteByNum(@PathVariable("numCpt") Long numCpt){
		Compte cpt = gestionComptes.getCompteByNum(numCpt, DetailsLevel.DetailsLevelEnum.MOSTDETAILS);
		return new ResponseEntity<Compte>(cpt, HttpStatus.OK);
	}
	
	//.../rest/comptes/duClient?numCli=1
	@JsonView(DetailsLevel.Essential.class)
	@RequestMapping(value="/duClient" , method=RequestMethod.GET)
	public ResponseEntity<List<Compte>> comptesDuClient(@RequestParam("numCli") Long numCli){
		List<Compte> listeCpt = gestionComptes.getComptesOfClient(numCli);
		return new ResponseEntity<List<Compte>>(listeCpt, HttpStatus.OK);
	}
	
		
	@RequestMapping(value="/virement" , method=RequestMethod.POST)
	// pour URL = http://localhost:8080/minibank-mvc-rest/mvc/rest/comptes/virement
	//par défaut , l'objet Transfert est passé comme un seul bloc (en mode json) dans le corps/body de la requete
	//exemple:  { montant: 150 , numCptDeb=1 , numCptCred=2 , ...} 
	//POST --> save/create or saveOrUpdate or specific method ...
	public ResponseEntity<Transfert> postTransfert(@RequestBody Transfert t){
		System.out.println("postTransfert() with t = " + t );
		try {
			gestionComptes.transferer(t.getMontant(), t.getNumCptDeb(), t.getNumCptCred());
			t.setOk(Boolean.TRUE);
		} catch (MyServiceException e) {
			t.setOk(Boolean.FALSE);
			e.printStackTrace();
		}
		return new ResponseEntity<Transfert>(t, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dernieresOperations" , method=RequestMethod.GET)
	@JsonView(DetailsLevel.Essential.class)
	// pour URL http://localhost:8080/minibank-mvc-rest/mvc/rest/comptes/dernieresOperations?numCpt=1
	public ResponseEntity<List<Operation>> dernieresOperations(@RequestParam("numCpt")long numCpt) {
		List<Operation> listeOp = new ArrayList<Operation>();
		try {
			listeOp=gestionComptes.getOperationsOfCompte(numCpt);
		} catch (MyServiceException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Operation>>(listeOp, HttpStatus.OK);
	}
	

}
