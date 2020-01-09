package org.mycontrib.xyz.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class MyData {
	private String ref;
	private Double value;
	
	public MyData(String ref, Double value) {
		super();
		this.ref = ref;
		this.value = value;
	}
	
	
}
