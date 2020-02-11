package org.mycontrib.ext.devises.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="login")
public class Login {
	@Id
	@Column(length=64)
	private String username; //id/pk (may be userId or unique email)
	
	@Column(length=64)
	private String password; //may be stored as crypted password
	
	@Column(length=64)
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
