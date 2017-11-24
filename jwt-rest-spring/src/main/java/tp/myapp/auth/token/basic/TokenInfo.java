package tp.myapp.auth.token.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import tp.myapp.auth.token.TokenManager;

/**
 * TokenInfo est une classe de données
 * regroupant des informations rattachées
 * à un token basic/simple (ex: uuid)
 * et qui doivent être mémorisée coté serveur (en mémoire ou en base)
 * 
 * NB: cette classe est utile dans le cas d'un token élémentaire (uuid)
 *     et l'est beaucoup moins dans le cas d'un jeton sophistiqué
 *     (ex: JWT) comportant en lui même toutes ces informations
 *     d'une manière à la fois facilement extractible et un minimum sécurisée
 */
public class TokenInfo {
	
	private String token; // full uuid token , as tokenId
	private List<String> roles;
	private String username;
	private long nbSecondsBeforeExpiration;
	private Date emitDate;
	

	public TokenInfo(String token, List<String> roles, String username,
			long nbSecondsBeforeExpiration , Date emitDate) {
		super();
		this.token = token;
		this.roles = roles;
		this.username = username;
		this.nbSecondsBeforeExpiration = nbSecondsBeforeExpiration;
		this.emitDate = emitDate;
	}
	
	public TokenInfo(String token, List<String> roles, String username, long nbSecondsBeforeExpiration) {
	   this(token,roles,username,nbSecondsBeforeExpiration,new Date());	
	}
	
	public TokenInfo(String token, List<String> roles, String username) {
		   this(token,roles,username,TokenManager.DEFAULT_EXPIRATION_DELAY);	
	}

	public TokenInfo(String token, List<String> roles) {
		   this(token,roles,TokenManager.DEFAULT_USERNAME);	
	}
	
	public TokenInfo(String token) {		   
		   this(token,Arrays.asList(TokenManager.DEFAULT_ROLES));	
	}


	public TokenInfo() {
		this(null);
	}
	
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public Date getEmitDate() {
		return emitDate;
	}
	public void setEmitDate(Date emitDate) {
		this.emitDate = emitDate;
	}
	public long getNbSecondsBeforeExpiration() {
		return nbSecondsBeforeExpiration;
	}
	public void setNbSecondsBeforeExpiration(long nbSecondsBeforeExpiration) {
		this.nbSecondsBeforeExpiration = nbSecondsBeforeExpiration;
	}
	

	
}
