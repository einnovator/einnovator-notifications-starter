package org.einnovator.notifications.client.model;

import java.util.Map;

public class SourceBuilder {
	
	private String type;
	
	private String id;
	
	private String name;
	
	private String uri;
	
	private String img;

	private Map<String, Object> details;
	
	public SourceBuilder() {
	}

	public SourceBuilder type(String type) {
		this.type = type;
		return this;
	}

	public SourceBuilder id(String id) {
		this.id = id;
		return this;
	}

	public SourceBuilder name(String name) {
		this.name = name;
		return this;
	}
	
	public SourceBuilder uri(String uri) {
		this.uri = uri;
		return this;
	}
	
	public SourceBuilder img(String img) {
		this.img = img;
		return this;
	}
	
	public SourceBuilder details(Map<String, Object> details) {
		this.details = details;
		return this;
	}
	
	public Source build() {
		Source source = new Source();
		source.setId(id);
		source.setName(name);
		source.setType(type);
		source.setUri(uri);
		source.setImg(img);
		source.setDetails(details);
		return source;
	}
}
