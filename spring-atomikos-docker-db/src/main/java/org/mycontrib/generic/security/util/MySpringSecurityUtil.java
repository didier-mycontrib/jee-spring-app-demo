package org.mycontrib.generic.security.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MySpringSecurityUtil {
	
	public static String DEFAULT_SPRING_SECURITY_ROLE_PREFIX="ROLE_";
	
	public static List<String> roleNameList(Authentication authentication){
	
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
	    
	    List<String> roleNameList=new ArrayList<String>();
	    for(GrantedAuthority ga : userPrincipal.getAuthorities()){
	    	String springSecurityRoleName=ga.getAuthority();
	    	String roleName=springSecurityRoleName;
	    	//ou bien roleName = springSecurityRoleName moins le préfixe "ROLE_" (affaire de préférence)
	    	/*
	    	if(roleName.startsWith(DEFAULT_SPRING_SECURITY_ROLE_PREFIX)){
	    		roleName = roleName.substring(DEFAULT_SPRING_SECURITY_ROLE_PREFIX.length());
	    	}*/
	    	roleNameList.add(roleName);        	
	    }
	    return roleNameList;
	}
	
	public static boolean testRequiredRoles(String commaSeparedRoleNames,List<String> roleNameList ) {
		boolean ok=true;
		if(commaSeparedRoleNames==null || roleNameList == null) 
			return false;
		String[] tabRequiredRoles = commaSeparedRoleNames.split(",");
		for(int i=0;i<tabRequiredRoles.length;i++) {
			String requiredRole=tabRequiredRoles[i];
			boolean okForOneRequiredRole=false;
			for(String effectiveRoleName : roleNameList) {
				if(effectiveRoleName.startsWith(DEFAULT_SPRING_SECURITY_ROLE_PREFIX)){
					effectiveRoleName = effectiveRoleName.substring(DEFAULT_SPRING_SECURITY_ROLE_PREFIX.length());
		    	}
				if(effectiveRoleName.equalsIgnoreCase(requiredRole))
					okForOneRequiredRole=true;
			}
			if(okForOneRequiredRole==false)
				ok=false;
		}
		return ok;
	}


}
