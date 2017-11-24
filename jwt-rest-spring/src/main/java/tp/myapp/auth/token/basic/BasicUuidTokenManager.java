package tp.myapp.auth.token.basic;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tp.myapp.auth.token.TokenManager;
import tp.myapp.web.ws.rest.interceptor.MyMvcAuthInterceptor;

/** TokenManager pour token simple : uuid */
public class BasicUuidTokenManager implements TokenManager {
	
	private static Logger logger = LoggerFactory.getLogger(BasicUuidTokenManager.class);
	
	private static BasicUuidTokenManager uniqueInstance ;
	
	public static BasicUuidTokenManager getInstance(){
		if(uniqueInstance==null)
			uniqueInstance=new BasicUuidTokenManager();
		return uniqueInstance;
	}


	private Map<String,TokenInfo> mapTokenInfo = new HashMap<String,TokenInfo>();
	
	private String generateUuid(){
		return java.util.UUID.randomUUID().toString();
	}
	

	@Override
	public String generateToken(String username) {
		return generateToken(username,Arrays.asList(TokenManager.DEFAULT_ROLES));
	}

	@Override
	public String generateToken(String username, List<String> roles) {
		String newToken = generateUuid(); 
		mapTokenInfo.put(newToken, new TokenInfo(newToken,roles,username));
		return newToken;
	}

	@Override
	public boolean verifyToken(String tokenOrTokenId) {
		removeAllExpiredTokensSinceOneMinute();
		TokenInfo tokenInfo = mapTokenInfo.get(tokenOrTokenId);
		return (tokenInfo==null)?false:true;
	}

	@Override
	public boolean verifyToken(String tokenOrTokenId, String expectedRoleName) {
		removeAllExpiredTokensSinceOneMinute();
		TokenInfo tokenInfo = mapTokenInfo.get(tokenOrTokenId);
		if(tokenInfo==null) return false;
		for( String roleName : tokenInfo.getRoles()){
		    if(roleName.equals(expectedRoleName))
		    	return true;
		}
		return false;
	}

	@Override
	public void deleteToken(String tokenOrTokenId) {
		mapTokenInfo.remove(tokenOrTokenId);
	}
	
	private Date lastRemovalDate;
	
	private void removeAllExpiredTokensSinceOneMinute(){
		//+ if (fait la derniere fois il y a moins de 1 minute)
		removeAllExpiredTokens();
	}

	private void removeAllExpiredTokens(){
		List<String> listOfExpiredToken = new ArrayList<String>();
		for(String token : mapTokenInfo.keySet()){
			TokenInfo tokenInfo = mapTokenInfo.get(token);
			tokenInfo.getEmitDate();
			//+ calcul period et test (via nouveautes jdk 1.8):
			
			
		}
		for(String tokenToDelete : listOfExpiredToken){
			deleteToken(tokenToDelete);
		}
		
	}

}
