package org.einnovator.notifications.client.model;

import java.security.Principal;

import org.einnovator.util.SecurityUtil;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class PrincipalX extends ObjectBase2 {

	public static final String PRINCIPAL_TYPE_USER = "User";
	public static final String PRINCIPAL_TYPE_ORG = "Organization";
	public static final String PRINCIPAL_TYPE_ADMIN = "Admin";
	public static final String PRINCIPAL_TYPE_ORG_ADMIN = "OrgAdmin";
	public static final String PRINCIPAL_TYPE_APP = "App";
	
	public PrincipalX() {
		super();
	}

	public PrincipalX(String type, String id, String name) {
		super(type, id, name);
	}

	
	@Override
	public String toString() {
		return "PrincipalX ["
				+ (getType() != null ? "type=" + getType() + ", " : "")
				+ (getId() != null ? "id=" + getId() + ", " : "")
				+ (getName() != null ? "name=" + getName() : "")
				+ (getUri() != null ? "uri=" + getUri() : "") 
				+ (getDetails() != null ? "uri=" + getDetails() : "")				
				+ "]";
	}
	
	
	public static PrincipalX makeUserPrincipal() {
		Principal principal = SecurityUtil.getPrincipal();
		String username = principal!=null ? principal.getName() : null;
		return new PrincipalX(PrincipalX.PRINCIPAL_TYPE_USER, username, username);
	}

}
