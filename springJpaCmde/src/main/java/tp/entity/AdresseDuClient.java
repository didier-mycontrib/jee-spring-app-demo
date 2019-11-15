package tp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class AdresseDuClient {
	
	@Id
	//pas de GeneratedValue/IDENTITY car @OneToOne / @PrimaryKeyJoinColumn
	private Long numClient;
	
	@Column(length=64)
	private String voie; //num et rue et ...
	
	@Column(length=12)
	private String codePostal;
	
	@Column(length=32)
	private String ville;
	//...

	@Override
	public String toString() {
		return "AdresseDuClient [voie=" + voie + ", codePostal=" + codePostal + ", ville=" + ville + "]";
	}
	public AdresseDuClient(String voie, String codePostal, String ville) {
		super();
		this.voie = voie;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
}
