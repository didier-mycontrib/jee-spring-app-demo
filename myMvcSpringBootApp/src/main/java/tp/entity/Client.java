package tp.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="client")
public class Client {
	
	@Id
	@Column(name="num_client")
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long numero;
	private String nom;
	private String prenom;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_naissance")
	private Date dateNaissance;
	
	@ManyToMany()
	@JoinTable(name = "client_compte",
	   joinColumns = {@JoinColumn(name = "num_cli")},
	   inverseJoinColumns = {@JoinColumn(name = "num_cpt")})
	private List<Compte> comptes;
	
	
	public Client() {
		super();
	}
	
	
	public Client(Long numero, String nom, String prenom, Date dateNaissance) {
		super();
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
	}


	@Override
	public String toString() {
		return "Client [numero=" + numero + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance
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
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}


	public List<Compte> getComptes() {
		return comptes;
	}


	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}
	
	

}
