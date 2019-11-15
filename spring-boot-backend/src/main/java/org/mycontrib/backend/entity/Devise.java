package org.mycontrib.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Devise {

	@Id
	private String code; //EUR , USD , JPY , GBP, ...
	
	private String name; //Euro , Dollar , Yen ,  ...
	
	@Min(value=0,message="le tauxChange doit etre positif")
	@Column(name="tauxChange")
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
