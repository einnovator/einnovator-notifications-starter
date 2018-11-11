package org.einnovator.notifications.client.model;

import java.util.Map;

public class PrincipalXBuilder {
	
	private String type;
	
	private String id;
	
	private String name;
	
	private String uri;
	
	private String imgUri;

	private Map<String, Object> details;
	
	public PrincipalXBuilder() {
	}

	public PrincipalXBuilder type(String type) {
		this.type = type;
		return this;
	}

	public PrincipalXBuilder id(String id) {
		this.id = id;
		return this;
	}

	public PrincipalXBuilder name(String name) {
		this.name = name;
		return this;
	}
	
	public PrincipalXBuilder imgUri(String imgUri) {
		this.imgUri = imgUri;
		return this;
	}
	
	public PrincipalXBuilder uri(String uri) {
		this.uri = uri;
		return this;
	}
	
	public PrincipalXBuilder details(Map<String, Object> details) {
		this.details = details;
		return this;
	}

	public PrincipalX build() {
		PrincipalX principal = new PrincipalX();
		principal.setId(id);
		principal.setName(name);
		principal.setType(type);
		principal.setUri(uri);
		principal.setImgUri(imgUri);				
		principal.setDetails(details);		
		return principal;
	}
}
