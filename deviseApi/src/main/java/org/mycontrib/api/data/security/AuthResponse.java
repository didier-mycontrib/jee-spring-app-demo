package org.mycontrib.api.data.security;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class AuthResponse {
	
	private String authToken; //jeton d'authentification généré
	private Boolean authOk;
	private List<String> roleNames;
	private String message;
	//...

}
