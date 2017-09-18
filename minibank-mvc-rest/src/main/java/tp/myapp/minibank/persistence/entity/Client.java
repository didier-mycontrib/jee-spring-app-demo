package tp.myapp.minibank.persistence.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Client")
@Getter @Setter @NoArgsConstructor
public class Client {
	
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
	
	//@OneToOne(fetch=FetchType.LAZY)
	@OneToOne//fetchType.EAGER par defaut sur @OneToOne , 
	          //FetchType.LAZY par defaut sur @OneToMany
	@JoinColumn(name="ref_adressePrincipale")
	//@JsonIgnore
	private Adresse adresse;
	
	@ManyToMany(mappedBy="proprietaires")
	@JsonIgnore
	private List<Compte> comptes;
	
	
	
	
	@Override
	public String toString() {
		return "_Client [numero=" + numero + ", nom=" + nom + ", prenom="
				+ prenom + ", dateNaissance=" + dateNaissance + ", telephone="
				+ telephone + ", email=" + email + ", password=" + password
				+ "]";
	}
	
	

}
