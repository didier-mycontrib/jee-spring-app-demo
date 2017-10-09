package tp.myapp.minibank.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Adresse")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Adresse {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idAdr;
	private String rue;
	private String codePostal;
	private String ville;
		

}
