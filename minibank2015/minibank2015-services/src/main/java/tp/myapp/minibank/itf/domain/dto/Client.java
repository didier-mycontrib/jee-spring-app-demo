package tp.myapp.minibank.itf.domain.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="client")
public class Client {
	private Long numero;
	private String nom;
	private String prenom;
	private Adresse adresse;
	private String telephone;
	private String email;
	//pas de password en clair ici !
	
	
	
	
	public Long getNumero() {
		return numero;
	}
	@Override
	public String toString() {
		return "Client [numero=" + numero + ", nom=" + nom + ", prenom="
				+ prenom + ", telephone=" + telephone + ", email=" + email
				+ "]";
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
