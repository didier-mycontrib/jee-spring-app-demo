package org.mycontrib.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Getter @Setter @NoArgsConstructor
public class Devise {

	@Id
	@ApiModelProperty(value = "currency code", example = "USD") 
	private String code; //EUR , USD , JPY , GBP, ...
	
	@ApiModelProperty(value = "currency name", example = "Dollar") 
	private String name; //Euro , Dollar , Yen ,  ...
	
	@Min(value=0,message="le tauxChange doit etre positif")
	@Column(name="tauxChange")
	@ApiModelProperty(value = "change (nb unit for 1 euro)", example = "1.1") 
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

	public Devise() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getChange() {
		return change;
	}

	public void setChange(Double change) {
		this.change = change;
	}
	
	
	
	
}
