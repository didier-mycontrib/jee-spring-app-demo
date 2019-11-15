package tp.web.api.data.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class AuthResponse {
	
	public String authToken; //jeton d'authentification généré
	public Boolean authOk;
	private String message;
	//...

}
