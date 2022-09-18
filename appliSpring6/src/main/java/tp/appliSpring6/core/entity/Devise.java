package tp.appliSpring6.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
//@Getter @Setter
//@NoArgsConstructor 
public class Devise {
	
	@Id
	private String code; //ex: "EUR" , "USD" 
	
    private String nom ; //ex: "euro" , "dollar" 
    
    @Column(name="echange")
    private Double change; //nb unité pour 1 euro
    
    

	public Devise() {
		super();
	}



	public Devise(String code, String nom, Double change) {
		super();
		this.code = code;
		this.nom = nom;
		this.change = change;
	}



	@Override
	public String toString() {
		return "Devise [code=" + code + ", nom=" + nom + ", change=" + change + "]";
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public Double getChange() {
		return change;
	}



	public void setChange(Double change) {
		this.change = change;
	}
    
    

}
