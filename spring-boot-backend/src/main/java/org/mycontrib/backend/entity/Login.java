package org.mycontrib.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//@Getter @Setter @NoArgsConstructor
public class Login {
	@Id
	
	private String username; //id/pk (may be userId or unique email)
	
	
	private String password; //may be stored as crypted password
	
	
	private String roles;  //ex: null or "admin,user" or "user" or ...
	

	@Override
	public String toString() {
		return "Login [username=" + username + ", password=" + password + ", roles=" + roles + "]";
	}


	public Login(String username, String password, String roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}


	public Login() {
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
