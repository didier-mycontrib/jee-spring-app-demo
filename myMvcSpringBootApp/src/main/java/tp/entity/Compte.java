package tp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="compte")
@NamedQueries({
	  @NamedQuery(name="Compte.findByClientNum",query="SELECT cpt FROM Client cli join cli.comptes cpt WHERE cli.numero = ?1"),
	  @NamedQuery(name="Compte.findCompteWithOperations",query="SELECT c FROM Compte c JOIN fetch c.operations o WHERE c.numero = ?1"),
	})
public class Compte {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="num_cpt")
	private Long numero;
	private String label;
	private Double solde;

	@OneToMany(mappedBy="compte")
	private List<Operation> operations;
	
	@ManyToMany(mappedBy="comptes") 
	private List<Client> clients;
	

	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", label=" + label + ", solde=" + solde + "]";
	}
	
	
	public Compte() {
		super();
	}
	

	public Compte(Long numero, String label, Double solde) {
		super();
		this.numero = numero;
		this.label = label;
		this.solde = solde;
	}


	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Double getSolde() {
		return solde;
	}
	public void setSolde(Double solde) {
		this.solde = solde;
	}


	public List<Operation> getOperations() {
		return operations;
	}


	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}


	public List<Client> getClients() {
		return clients;
	}


	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	
	

}
