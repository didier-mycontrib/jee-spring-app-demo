package org.mycontrib.generic.security.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter @Setter @NoArgsConstructor
public class AuthResponse {
	
	@ApiModelProperty(value = "username", example = "admin1")
	private String username;
	
	@ApiModelProperty(value = "checked roles", example = "admin,user")
	private String roles;
	
	@ApiModelProperty(value = "status of login", example = "true")
	private Boolean status;
	
	@ApiModelProperty(value = "login outcome", example = "login successful")
	private String message;
	
	@ApiModelProperty(value = "jwt auth token", example = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJodHRwOi8vd3d3Lm15Y29tcGFueSIsInN1YiI6ImFkbWluMSIsImlhdCI6MTU5OTQ5MTUxMCwiYXV0aG9yaXRpZXMiOiJbUk9MRV9BRE1JTiwgUk9MRV9VU0VSXSIsImV4cCI6MTU5OTQ5MTgxMH0.WUxDpddCQ1bAhOb6M4_anBHNa9XgGOKRufqhnw0JlW6D1bdwybatntE44ZrpoEtFFtr1jGwc_-_Zn4zJD-QKlQ")
	private String token; //jeton d'authentification généré
	//...
	
	public AuthResponse() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
	
}
