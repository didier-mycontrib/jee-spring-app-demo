package tp.myapp.web.ws.rest.data.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class BasicCredential implements Credential {
	private String username;
	private String password;
}
