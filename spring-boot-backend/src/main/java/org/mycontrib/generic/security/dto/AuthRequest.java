package org.mycontrib.generic.security.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Getter @Setter
//@NoArgsConstructor
public class AuthRequest {
	@ApiModelProperty(value = "username", example = "admin1")
	private String username;
	
	@ApiModelProperty(value = "password", example = "pwdadmin1")
	private String password;
	
	@ApiModelProperty(value = "asked roles", example = "admin,user")
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



	public AuthRequest() {
		super();
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getRoles() {
		return roles;
	}



	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
	
	
}
