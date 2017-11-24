package tp.myapp.auth.token;

import java.util.List;

/**
 * 
 * interface abstraite pour gestionnaire de token
 * NB: le jeton/token géré pour être d'un des différents types suivants:
 *   - simple string (ex: uuid) ou bien jsonString (ex: JWT ou autre)
 *   - avec ou sans délai d'expiration (ex: 120 * 60 secondes pour 2h)
 *   D'autre part, les jetons/tokens générés pourront être:
 *   - soit stockés en base de données (mode persistant avec cache)
 *   - soit simplement mémorisés en mémoire (dans map ou ...)
 *
 */
public interface TokenManager {
	
	public static final String ANY_ROLE="any";
	public static final String DEFAULT_ROLENAME=ANY_ROLE;
	public static final String[] DEFAULT_ROLES={DEFAULT_ROLENAME};
	
	public static final String DEFAULT_USERNAME="guest";
	public static final long DEFAULT_EXPIRATION_DELAY=48*60*60;//48h en s
	/** génère un nouveau token sans préciser un délai d'expiration	
	    et le mémorise en mémoire (liste ou map si associé à rôle)*/
    public String generateToken(String username);
    public String generateToken(String username,List<String> roles);
    
    /** génère un nouveau token avec délai d'expiration	souhaité 
     et le mémorise en mémoire (liste ou map si associé à rôle)*/     
    //public String generateToken(String username,long nbSecondsBeforeExpiration);
    //public String generateToken(String username,List<String> roles,long nbSecondsBeforeExpiration);
    
    /** persite le token en base de donnée: dans liste ou map ou SGBDR ou mongoDB ou ...
     *  et retourne la clef : le token lui même ou bien une clef (tokenId ou ...) */
    // public String persistToken(String token); ????
    
    /** verifie la validité d'un token */    
    public boolean verifyToken(String tokenOrTokenId);
    public boolean verifyToken(String tokenOrTokenId,String expectedRoleName);
    
    /** retourne le délai d'expiration d'un token (selon instant présent)
     *  (en nombre de secondes) pour savoir si une réinitialisation/prolongation est nécessaire */ 
    //public long validDurationOfToken(String tokenOrTokenId);
    
    /** demande à réinitialiser/prolonger un token existant
     * et retourne le token réinitialisé (soit le même , soit un autre en remplacement)*/
    //public String reinitExistingToken(String tokenOrTokenId);
    
    /** supprimer un token existant */
    public void deleteToken(String tokenOrTokenId);
    
}
