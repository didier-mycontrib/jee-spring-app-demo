package tp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@NamedQueries({
	@NamedQuery(name="Categorie.findProduitsByCategorie",
			   query="SELECT c.produits FROM Categorie c WHERE c.numero = ?1")
})
public class Categorie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long numero;
	
	@Column(length=64)
	private String label;
	
	@OneToMany()
	@JoinTable(name="categorie_produits",
	  joinColumns={@JoinColumn(name="categorie")},
	  inverseJoinColumns={@JoinColumn(name="produit")})
	private List<Produit> produits;

	@Override
	public String toString() {
		return "Categorie [numero=" + numero + ", label=" + label + "]";
	}
	

}
