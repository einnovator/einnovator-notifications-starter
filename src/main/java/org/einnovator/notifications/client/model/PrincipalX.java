package org.einnovator.notifications.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrincipalX extends ObjectBase {

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
}
