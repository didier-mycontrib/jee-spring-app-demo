package tp.myapp.web.ws.rest.data.auth;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class AuthResponse {
	
	private String username;
	private Boolean authOk; 
	private String authToken; //jwt or uuid or ...
	
	private List<String> roles; //list of roles (ex: "user", "admin" , "manager" , ...)
	
	private Long userId; //may be null (unknown , ...)
	
	public void addRole(String roleName){
		if(roles==null){
			roles=new ArrayList<String>();
		}
		roles.add(roleName);
	}
	
}
