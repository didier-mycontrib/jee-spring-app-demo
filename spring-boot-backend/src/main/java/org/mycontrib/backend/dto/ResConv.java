package org.mycontrib.backend.dto;
//.payload quelquefois utilisé à la place de .dto

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

//import lombok.AllArgsConstructor;
//import lombok.Data;

// DTO = Data Transfert Object 
//objet de données tranférées à travers le réseau (via REST ou SOAP ou RMI)
//surtout pas de @Entity



//@ToString @Getter @Setter @NoArgsConstructor @AllArgsConstructor
//@Data @AllArgsConstructor
public class ResConv implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "amount to convert", example = "100")
	//or @ApiModelProperty(value = "${SomeModel.someProperty}", ...) 
	//with SomeModel.someProperty=amount to convert in application.properties
	private Double amount;
	
	@ApiModelProperty(value = "source currency code", example = "EUR") 
	private String source;
	
	@ApiModelProperty(value = "target currency code", example = "USD") 
	private String target;
	
	@ApiModelProperty(value = "converted amount", example = "110") 
	private Double result;
	

	@Override
	public String toString() {
		return "ResConv [amount=" + amount + ", source=" + source + ", target=" + target + ", result=" + result + "]";
	}


	public ResConv() {
		super();
	}

	public ResConv(Double amount, String source, String target, Double result) {
		super();
		this.amount = amount;
		this.source = source;
		this.target = target;
		this.result = result;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Double getResult() {
		return result;
	}
	public void setResult(Double result) {
		this.result = result;
	}
	
	

}
