package org.mycontrib.ext.devises.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="devise")
public class Devise {

	@Id
	@Column(length=32)
	private String code; //EUR , USD , JPY , GBP, ...
	
	@Column(length=64)
	private String name; //Euro , Dollar , Yen ,  ...
	
	@Min(value=0,message="le tauxChange doit etre positif")
	@Column(name="taux_change")
	private Double change;

	@Override
	public String toString() {
		return "Devise [code=" + code + ", name=" + name + ", change=" + change + "]";
	}

	public Devise(String code, String name, Double change) {
		super();
		this.code = code;
		this.name = name;
		this.change = change;
	}
	
	
}
