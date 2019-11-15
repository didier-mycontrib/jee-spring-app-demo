package tp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Client {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long numero;
	
	@Column(length=32)
	private String nom;

	@OneToOne(optional=true,cascade={CascadeType.ALL})
	@PrimaryKeyJoinColumn
	private AdresseDuClient adresse;
	
	@Override
	public String toString() {
		return "Client [numero=" + numero + ", nom=" + nom + "]";
	}



	public Client(Long numero, String nom) {
		super();
		this.numero = numero;
		this.nom = nom;
	}
	
	

}
