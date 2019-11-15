package org.mycontrib.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
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
	
	
}
