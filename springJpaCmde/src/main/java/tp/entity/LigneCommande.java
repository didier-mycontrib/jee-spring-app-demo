package tp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@NamedQueries({
	@NamedQuery(name="LigneCommande.findByNumCommande",
			   query="SELECT l FROM LigneCommande l WHERE l.commande.numero = ?1")
})
public class LigneCommande {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long numLigneCmde;
	
	private Integer quantite;
	
	@ManyToOne
	@JoinColumn(name="cmde")
	private Commande commande;
	
	@ManyToOne
	@JoinColumn(name="prod")
	private Produit produit;

	@Override
	public String toString() {
		return "LigneCommande [quantite=" + quantite + ", produit=" + produit + "]";
	}
	
	

}
