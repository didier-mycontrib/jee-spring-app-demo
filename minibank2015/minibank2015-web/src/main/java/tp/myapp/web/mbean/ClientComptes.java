package tp.myapp.web.mbean;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import tp.myapp.minibank.itf.domain.dto.Client;
import tp.myapp.minibank.itf.domain.dto.Compte;
import tp.myapp.minibank.itf.domain.service.GestionClients;
import tp.myapp.minibank.itf.domain.service.GestionComptes;
import tp.myapp.minibank.itf.domain.service.MyServiceException;

@ManagedBean
@SessionScoped
public class ClientComptes {
	
	private Long numClient;
	private String password;
	private Date dateNaissance;//pour test p:calendar
	
	private boolean identifie;
	private Client client;
	private String message;
	
	private List<Compte> listeComptes;
	
	public void actualiserListeComptes() throws MyServiceException{
		this.listeComptes=getServiceGestionComptes().getComptesOfClient(numClient);
	}
	
	public String identifierClient(){
		String suite=null;message=null;
		try {
			identifie = getServiceGestionClients().isGoodPasswordOfClient(numClient, password);
			if(identifie){
				client=getServiceGestionClients().getClientByNum(numClient);
				actualiserListeComptes();
				suite="listeComptes"; //.jsp
			}
		} catch (Exception e) {
			message="echec identification - " + e.getMessage();
		}
		return suite;
	}
	
	public String deconnexion(){
		message=null;
		identifie = false;
		client = null;
		listeComptes=null;
		numClient=null;
		password=null;
	    return "welcome"; //.jsp		
	}
	
	
	@ManagedProperty(value="#{referentiel}")
	//@Inject
	private Referentiel referentiel;

	public Referentiel getReferentiel() {
		return referentiel;
	}

	public void setReferentiel(Referentiel referentiel) {
		this.referentiel = referentiel;
	}
	
	private GestionComptes getServiceGestionComptes(){
		return referentiel.getFacadeMiniBank().getServiceGestionComptes();
	}
	
	private GestionClients getServiceGestionClients(){
		return referentiel.getFacadeMiniBank().getServiceGestionClients();
	}

	public Long getNumClient() {
		return numClient;
	}

	public void setNumClient(Long numClient) {
		this.numClient = numClient;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isIdentifie() {
		return identifie;
	}

	public void setIdentifie(boolean identifie) {
		this.identifie = identifie;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Compte> getListeComptes() {
		return listeComptes;
	}

	public void setListeComptes(List<Compte> listeComptes) {
		this.listeComptes = listeComptes;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	
	

}
