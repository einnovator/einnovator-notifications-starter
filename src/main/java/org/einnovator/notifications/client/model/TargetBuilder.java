package org.einnovator.notifications.client.model;

import java.util.Map;

import org.einnovator.notifications.client.model.Target.Medium;

public class TargetBuilder {
	
	private String type;
	
	private String id;
	
	private String name;
	
	private Medium[] mediums;
	
	private String email;
	
	private String uri;
	
	private String imgUri;

	private Map<String, Object> details;
	
	private String contextId;

	private String tag;

	public TargetBuilder() {
	}

	public TargetBuilder type(String type) {
		this.type = type;
		return this;
	}

	public TargetBuilder id(String id) {
		this.id = id;
		return this;
	}

	public TargetBuilder name(String name) {
		this.name = name;
		return this;
	}
	
	public TargetBuilder email(String email) {
		this.email = email;
		return this;
	}
	
	public TargetBuilder medium(Medium[] mediums) {
		this.mediums = mediums;
		return this;
	}
	
	public TargetBuilder uri(String uri) {
		this.uri = uri;
		return this;
	}
	
	public TargetBuilder imgUri(String imgUri) {
		this.imgUri = imgUri;
		return this;
	}
	
	public TargetBuilder details(Map<String, Object> details) {
		this.details = details;
		return this;
	}
	
	
	public TargetBuilder context(String context) {
		this.contextId = context;
		return this;
	}
	
	public TargetBuilder tags(String tag) {
		this.tag = tag;
		return this;
	}
	
	public Target build() {
		Target target = new Target();
		target.setId(id);
		target.setName(name);
		target.setType(type);
		target.setContextId(contextId);
		target.setTag(tag);
		target.setEmail(email);
		target.setMediums(mediums);
		target.setUri(uri);
		target.setImgUri(imgUri);		
		target.setDetails(details);
		return target;
	}
}
