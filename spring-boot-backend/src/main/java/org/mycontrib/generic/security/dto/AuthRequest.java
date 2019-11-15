package org.mycontrib.generic.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
public class AuthRequest {
	private String username;
	private String password;
	private String roles;
	
	
	
	@Override
	public String toString() {
		return "AuthRequest [username=" + username + ", password=" + password + ", roles=" + roles + "]";
	}



	public AuthRequest(String username, String password, String roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	
	
}
