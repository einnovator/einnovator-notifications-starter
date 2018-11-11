package org.einnovator.notifications.client.model;

import java.util.Map;

public class TriggerBuilder {
	
	private String type;
	
	private String id;
	
	private String name;
		
	private String uri;
	
	private String imgUri;

	private Map<String, Object> details;
	
	public TriggerBuilder() {
	}

	public TriggerBuilder type(String type) {
		this.type = type;
		return this;
	}

	public TriggerBuilder id(String id) {
		this.id = id;
		return this;
	}

	public TriggerBuilder name(String name) {
		this.name = name;
		return this;
	}
	
	public TriggerBuilder uri(String uri) {
		this.uri = uri;
		return this;
	}
	
	public TriggerBuilder imgUri(String imgUri) {
		this.imgUri = imgUri;
		return this;
	}
	
	public TriggerBuilder details(Map<String, Object> details) {
		this.details = details;
		return this;
	}
	
	
	public Trigger build() {
		Trigger trigger = new Trigger();
		trigger.setId(id);
		trigger.setName(name);
		trigger.setType(type);
		trigger.setUri(uri);
		trigger.setImgUri(imgUri);		
		trigger.setDetails(details);
		return trigger;
	}
}
