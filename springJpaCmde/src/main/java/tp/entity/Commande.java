package tp.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Commande {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long numero;
	
	@Temporal(TemporalType.DATE)
	private Date dateCmde;
	
	@OneToMany(mappedBy="commande" , cascade={CascadeType.REMOVE})
	private List<LigneCommande> lignes;
	
	@ManyToOne
	@JoinColumn(name="client")
	private Client client;
	
	

	@Override
	public String toString() {
		return "Commande [dateCmde=" + dateCmde + ", numero=" + numero + "]";
	}

	public Commande(Long numero, Date dateCmde) {
		super();
		this.numero = numero;
		this.dateCmde = dateCmde;
		if(this.dateCmde==null)
			this.dateCmde=new Date(); //today
	}
	
	

}
