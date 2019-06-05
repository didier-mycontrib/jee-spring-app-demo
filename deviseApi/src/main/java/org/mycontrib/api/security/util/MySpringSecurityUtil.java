package org.mycontrib.api.security.util;

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


}
