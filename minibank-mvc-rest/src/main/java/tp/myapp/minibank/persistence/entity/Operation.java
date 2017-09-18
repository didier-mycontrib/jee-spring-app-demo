package tp.myapp.minibank.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tp.myapp.minibank.details.DetailsLevel;

@Entity
@Table(name="Operation")
@Getter @Setter @NoArgsConstructor
public class Operation {
	
	@Id
	@Column(name="numOp")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonView(DetailsLevel.Essential.class)
	private Long numero;
	
	@JsonView(DetailsLevel.Essential.class)
	private String label;
	
	@JsonView(DetailsLevel.Essential.class)
	private Double montant; //positif ou negatif
	
	@JsonView(DetailsLevel.Essential.class)
	private Date dateOp;
	
	@ManyToOne
	@JoinColumn(name="ref_compte")
	
	@JsonView(DetailsLevel.AllDetails.class)
	private Compte compte;
	
		
	@Override
	public String toString() {
		return "_Operation [numero=" + numero + ", label=" + label
				+ ", montant=" + montant + ", dateOp=" + dateOp + "]";
	}

	
	public Operation(String label, Double montant) {
		super();
		this.label = label;
		this.montant = montant;
		this.dateOp = new Date();
	}

	
}
