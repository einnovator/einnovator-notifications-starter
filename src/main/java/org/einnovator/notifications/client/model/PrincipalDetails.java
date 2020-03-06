package org.einnovator.notifications.client.model;

import java.security.Principal;

import org.einnovator.util.security.SecurityUtil;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class PrincipalDetails extends ObjectBase2 {

	public static final String PRINCIPAL_TYPE_USER = "User";
	
	/**
	 * Create instance of {@code PrincipalDetails}.
	 *
	 */
	public PrincipalDetails() {
		super();
	}

	/**
	 * Create instance of {@code PrincipalDetails}.
	 *
	 * @param username the username
	 * @param name the name
	 */
	public PrincipalDetails(String username, String name) {
		super(PRINCIPAL_TYPE_USER, username, name);
	}
	
	/**
	 * Create instance of {@code PrincipalDetails}.
	 *
	 * @param username the username
	 */
	public PrincipalDetails(String username) {
		super(PRINCIPAL_TYPE_USER, username, username);
	}
	
	public static PrincipalDetails principal() {
		Principal principal = SecurityUtil.getPrincipal();
		String username = principal!=null ? principal.getName() : null;
		return new PrincipalDetails(username, username);
	}

}
