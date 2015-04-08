package tp.myapp.minibank.rest.service;


import java.util.ArrayList;
import java.util.List;

import javax.jws.WebParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;

import tp.myapp.minibank.itf.domain.dto.Compte;
import tp.myapp.minibank.itf.domain.dto.Operation;
import tp.myapp.minibank.itf.domain.service.GestionComptes;
import tp.myapp.minibank.itf.domain.service.MyServiceException;
import tp.myapp.minibank.rest.service.data.Transfert;


@Path("/json/gestioncomptes/")
@Produces("application/json")
@Consumes("application/json")
@CrossOriginResourceSharing(allowAllOrigins = true)// ou bien autorisations plus fines
public class CompteRestJsonServiceAdaptor {

    private GestionComptes serviceGestionComptes;//injected by spring
	
	public void setServiceGestionComptes(GestionComptes serviceGestionComptes) {
		this.serviceGestionComptes = serviceGestionComptes;
	}

	@GET
	@Path("comptes/{num}")
	// pour URL = http://localhost:8080/minibank-dry/services/rest/json/gestioncomptes/comptes/1
	public Compte getCompteByNum(@PathParam("num")Long num){		
		Compte cpt = null;
		try {
			cpt = serviceGestionComptes.getCompteByNum(num);
		} catch (MyServiceException e) {
			e.printStackTrace();
		}
		return cpt;
	}
	
	@GET
	@Path("comptes")
	// pour URL = http://localhost:8080/minibank-dry/services/rest/json/gestioncomptes/comptes
	// ou bien http://localhost:8080/minibank-dry/services/rest/json/gestioncomptes/comptes?numClient=1 (numClient may be null)
	public List<Compte> getComptesByCriteria(@QueryParam(value="numClient")Long numClient){
		List<Compte> listeCpt = new ArrayList<Compte>();
		System.out.println("getComptesByCriteria() , numClient:"+numClient );
		if(numClient!=null){
			try {
				listeCpt = serviceGestionComptes.getComptesOfClient(numClient);
			} catch (MyServiceException e) {
				e.printStackTrace();
			}
		}
		return listeCpt;
	}
	
		
	@POST
    @Path("/virement") //ou autre WS-REST pour virement
	// pour URL = http://localhost:8080/minibank-dry/services/rest/json/gestioncomptes/virement
	//par défaut , l'objet Transfert est passé comme un seul bloc (en mode json) dans le corps/body de la requete
	//exemple:  { montant: 150 , numCptDeb=1 , numCptCred=2 , ...} 
	//POST --> save/create or saveOrUpdate or specific method ...
	public Transfert postTransfert(Transfert t){
		System.out.println("postTransfert() with t = " + t );
		try {
			serviceGestionComptes.transferer(t.getMontant(), t.getNumCptDeb(), t.getNumCptCred());
			t.setOk(Boolean.TRUE);
		} catch (MyServiceException e) {
			t.setOk(Boolean.FALSE);
			e.printStackTrace();
		}
		return t;
	}
	
	@GET
	@Path("dernieresOperations")
	// pour URL = http://localhost:8080/minibank-dry/services/rest/json/gestioncomptes/dernieresOperations
	// ou bien http://localhost:8080/minibank-dry/services/rest/json/gestioncomptes/dernieresOperations?numCpt=1 (numCpt may be null)
	public List<Operation> dernieresOperations(@QueryParam("numCpt")long numCpt) {
		List<Operation> listeOp = new ArrayList<Operation>();
		try {
			listeOp=serviceGestionComptes.getOperationsOfCompte(numCpt);
		} catch (MyServiceException e) {
			e.printStackTrace();
		}
		return listeOp;
	}
	
	
	
	

	
}
