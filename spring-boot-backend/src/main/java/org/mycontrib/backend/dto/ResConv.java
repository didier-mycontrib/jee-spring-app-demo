package org.mycontrib.backend.dto;
//.payload quelquefois utilisé à la place de .dto

import java.io.Serializable;

import lombok.AllArgsConstructor;

// DTO = Data Transfert Object 
//objet de données tranférées à travers le réseau (via REST ou SOAP ou RMI)
//surtout pas de @Entity

import lombok.Data;

//@ToString @Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Data @AllArgsConstructor
public class ResConv implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Double amount;
	private String source;
	private String target;
	private Double result;

}
