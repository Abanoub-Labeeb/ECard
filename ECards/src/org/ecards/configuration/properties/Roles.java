package org.ecards.configuration.properties;

public enum Roles{
	ROLE_ADMIN ("ROLE_ADMIN") ,
	ROLE_USER  ("ROLE_USER") ;
	
	public String value;
	
	Roles(String role) {
        this.value = role;
    }
}