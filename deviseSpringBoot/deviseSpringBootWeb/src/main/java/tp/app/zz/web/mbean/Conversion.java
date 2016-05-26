package tp.app.zz.web.mbean;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import tp.app.zz.itf.domain.service.GestionDevises;

//@ManagedBean
//@RequestScoped
@Named
@Scope("request")
public class Conversion {
	
	private String monnaie1,monnaie2;
	private Double montant;
	private Double sommeConvertie;
	
	//@ManagedProperty(value="#{gestionDevisesImpl}") //ici  #{idSrping}
	@Inject
	private GestionDevises serviceDevises;
	
	public String convertir(){
		sommeConvertie=serviceDevises.convertir(montant, monnaie1, monnaie2);
		return null;//rester sur meme page
	}
	
	public String getMonnaie1() {
		return monnaie1;
	}
	public void setMonnaie1(String monnaie1) {
		this.monnaie1 = monnaie1;
	}
	public String getMonnaie2() {
		return monnaie2;
	}
	public void setMonnaie2(String monnaie2) {
		this.monnaie2 = monnaie2;
	}
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	public Double getSommeConvertie() {
		return sommeConvertie;
	}
	public void setSommeConvertie(Double sommeConvertie) {
		this.sommeConvertie = sommeConvertie;
	}

	public GestionDevises getServiceDevises() {
		return serviceDevises;
	}

	public void setServiceDevises(GestionDevises serviceDevises) {
		this.serviceDevises = serviceDevises;
	}
	

}
