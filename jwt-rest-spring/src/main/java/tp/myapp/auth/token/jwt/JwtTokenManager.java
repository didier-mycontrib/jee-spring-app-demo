package tp.myapp.auth.token.jwt;

import java.security.Key;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import tp.myapp.auth.token.TokenManager;

/** TokenManager pour token au format JWT */
public class JwtTokenManager implements TokenManager {
	
	private static Logger logger = LoggerFactory.getLogger(JwtTokenManager.class);
	
	// We need a signing key, so we'll create one just for this example. Usually
	// the key would be read from your application configuration instead.
	private static Key key = MacProvider.generateKey();
	
	private static JwtTokenManager uniqueInstance ;
	
	public static JwtTokenManager getInstance(){
		if(uniqueInstance==null)
			uniqueInstance=new JwtTokenManager();
		return uniqueInstance;
	}

	@Override
	public String generateToken(String username) {
		return generateToken(username,Arrays.asList(TokenManager.DEFAULT_ROLES));		
	}

	@Override
	public String generateToken(String username, List<String> roles) {
		Map<String,Object> payloadClaimsAsMap = new HashMap<String,Object>();
		payloadClaimsAsMap.put(Claims.SUBJECT, username);
		payloadClaimsAsMap.put("roles", roles);
			String compactJws = Jwts.builder()
			  //.setSubject(username) //now in payloadClaimsAsMap
			  .setClaims(payloadClaimsAsMap)
			  .signWith(SignatureAlgorithm.HS512, key)
			  .compact();
			logger.debug("generated jwt token:" + compactJws );
			return compactJws;
	}

	private Jws<Claims> extractJwtClaims(String tokenOrTokenId) {
		Jws<Claims> claims = null;
		try {
            String compactJws= tokenOrTokenId;
            claims = Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws);
		    //OK, we can trust this JWT
            logger.debug("valid jwt token was check: " + compactJws);
            logger.debug("body of received jwt: " + claims.getBody());          
		} catch (Exception e){
		    // ExpiredJwtException or SignatureException or ....
		    //don't trust the JWT!
			logger.info("invalid jwt token was received: " + tokenOrTokenId);
		}
		return claims;
	}

	
	@Override
	public boolean verifyToken(String tokenOrTokenId) {
		boolean ok=false;
		Jws<Claims> claims = extractJwtClaims(tokenOrTokenId);
		ok=(claims!=null);
		return ok;
	}

	@Override
	public boolean verifyToken(String tokenOrTokenId, String expectedRoleName) {
		Jws<Claims> claims = extractJwtClaims(tokenOrTokenId);
		if(claims==null)
			return false;
		List<String> roles = (List<String>) claims.getBody().get("roles");
        logger.debug("roles in claims of received jwt: " +  roles);
        if(roles==null)
			return false;
		for(String role : roles){
			if(role.equals(expectedRoleName))
				return true;
		}
		/* else if not found in role list */
		return false;
	}

	@Override
	public void deleteToken(String tokenOrTokenId) {
		//nothing to do with jwt token (no info server side, all is in the token)		
	}

	
}
