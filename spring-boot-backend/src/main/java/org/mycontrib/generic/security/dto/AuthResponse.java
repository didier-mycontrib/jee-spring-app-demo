package org.mycontrib.generic.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class AuthResponse {
	private String username;
	private String roles;
	private Boolean status;
	private String message;
	private String token; //jeton d'authentification généré
	//...

}
