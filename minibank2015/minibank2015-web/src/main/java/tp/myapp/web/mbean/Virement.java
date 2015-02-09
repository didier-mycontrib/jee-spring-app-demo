package tp.myapp.web.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import tp.myapp.minibank.itf.domain.dto.Compte;
import tp.myapp.minibank.itf.domain.service.GestionComptes;

@ManagedBean
@RequestScoped
public class Virement {
	
	private Long numCptDeb;
	private Long numCptCred;
	private Double montant;
	
	private GestionComptes getServiceGestionComptes(){
		return clientComptes.getReferentiel().getFacadeMiniBank().getServiceGestionComptes();
	}
	
	public List<SelectItem> getComptesPossibles(){
		 List<SelectItem> comptesPossibles = new ArrayList<SelectItem>();
		 for(Compte cpt : clientComptes.getListeComptes()){
			 comptesPossibles.add(new SelectItem(cpt.getNumero(),cpt.toString())); 
		 }
		 return comptesPossibles;
	}
	
	public String effectuerVirement(){
		String suite=null;
		try {
			//effectuer le virement
			getServiceGestionComptes().transferer(montant, numCptDeb, numCptCred);
		    //reactualiser les soldes:
			clientComptes.actualiserListeComptes();
			//message:
			clientComptes.setMessage("virement bien effectué : " +montant +" du compte " + numCptDeb +  " vers le compte " + numCptCred);
			suite="listeComptes"; //.jsp		
		} catch (Exception e) {
			clientComptes.setMessage("echec identification - " + e.getMessage());
		}
		return suite;
	}
	
	
	
	@ManagedProperty(value="#{clientComptes}")
	private ClientComptes clientComptes;

	public ClientComptes getClientComptes() {
		return clientComptes;
	}

	public void setClientComptes(ClientComptes clientComptes) {
		this.clientComptes = clientComptes;
	}

	public Long getNumCptDeb() {
		return numCptDeb;
	}

	public void setNumCptDeb(Long numCptDeb) {
		this.numCptDeb = numCptDeb;
	}

	public Long getNumCptCred() {
		return numCptCred;
	}

	public void setNumCptCred(Long numCptCred) {
		this.numCptCred = numCptCred;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}
	
	

}
