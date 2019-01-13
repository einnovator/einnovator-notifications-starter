package org.einnovator.notifications.client.model;

import java.util.Map;

public class ActionBuilder {
	
	private String type;
	
	private String id;
	
	private String name;
	
	private String uri;

	private String img;
	
	private Map<String, Object> details;
	
	public ActionBuilder() {
	}

	public ActionBuilder type(String type) {
		this.type = type;
		return this;
	}

	public ActionBuilder id(String id) {
		this.id = id;
		return this;
	}

	public ActionBuilder name(String name) {
		this.name = name;
		return this;
	}
	
	public ActionBuilder uri(String uri) {
		this.uri = uri;
		return this;
	}
	
	public ActionBuilder img(String img) {
		this.img = img;
		return this;
	}
	
	public ActionBuilder details(Map<String, Object> details) {
		this.details = details;
		return this;
	}

	public Action build() {
		Action action = new Action();
		action.setId(id);
		action.setName(name);
		action.setType(type);
		action.setUri(uri);
		action.setImg(img);
		action.setDetails(details);		
		return action;
	}
}
