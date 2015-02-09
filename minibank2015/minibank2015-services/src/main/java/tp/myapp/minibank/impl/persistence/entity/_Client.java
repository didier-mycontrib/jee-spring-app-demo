package tp.myapp.minibank.impl.persistence.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Client")
public class _Client {
	
	@Id
	@Column(name="numClient")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long numero;
	private String nom;
	private String prenom;

	private Date dateNaissance;
	private String telephone;
	private String email;
	private String password;
	
	@OneToOne
	@JoinColumn(name="ref_adressePrincipale")
	private _Adresse adresse;
	
	@ManyToMany(mappedBy="proprietaires")
	private List<_Compte> comptes;
	
	
	
	
	@Override
	public String toString() {
		return "_Client [numero=" + numero + ", nom=" + nom + ", prenom="
				+ prenom + ", dateNaissance=" + dateNaissance + ", telephone="
				+ telephone + ", email=" + email + ", password=" + password
				+ "]";
	}
	public Long getNumero() {
		return numero;
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
	public _Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(_Adresse adresse) {
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
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<_Compte> getComptes() {
		return comptes;
	}
	public void setComptes(List<_Compte> comptes) {
		this.comptes = comptes;
	}
	
	
	

}
