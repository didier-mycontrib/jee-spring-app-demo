package tp.myapp.minibank.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tp.myapp.minibank.details.DetailsFetch;
import tp.myapp.minibank.details.DetailsLevel;
import tp.myapp.minibank.details.DetailsLevel.DetailsLevelEnum;

@Entity
@Table(name="Compte")
@Getter @Setter @NoArgsConstructor
@NamedQueries({
	@NamedQuery(name="Compte.findComptesByNumCli",
			   query="select c.comptes from Client c where c.numero= ?1 ")
})
public class Compte implements DetailsFetch{
	
	@Id
	@Column(name="numCpt")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView(DetailsLevel.Essential.class)
	private Long numero;
	
	@JsonView(DetailsLevel.Essential.class)
	private String label;
	
	@JsonView(DetailsLevel.Essential.class)
	private Double solde;
	
	@ManyToMany
	@JoinTable(name="ClientCompte",joinColumns={@JoinColumn(name="numCpt")},
	inverseJoinColumns={@JoinColumn(name="numCli")})
	//@JsonIgnore
	@JsonView(DetailsLevel.AllDetails.class)
	private List<Client> proprietaires;
	
	@OneToMany(mappedBy="compte",cascade={CascadeType.ALL})
	//@JsonIgnore
	@JsonView(DetailsLevel.MostDetails.class)
	private List<Operation> operations;
	
		
	public void addOperation(Operation op){
		if(operations==null)
			operations=new ArrayList<Operation>();
		op.setCompte(this);
		operations.add(op);
	}
	
	
		
	public Compte(Long numero, String label, Double solde) {
		super();
		this.numero = numero;
		this.label = label;
		this.solde = solde;
	}


	@Override
	public String toString() {
		return "_Compte [numero=" + numero + ", label=" + label + ", solde="
				+ solde + "]";
	}



	@Override
	public void fetchDetails(DetailsLevelEnum detailsLevel) {
		switch(detailsLevel){
		    /* intentionnellement sans break; et par ordre décroissant */
		case ALLDETAILS:
			DetailsFetch.fetchLazyCollection(this.proprietaires);
		case MOSTDETAILS:
			DetailsFetch.fetchLazyCollection(this.operations);
		case MAINDETAILS:
	    default:   
		}
	}
	

}
