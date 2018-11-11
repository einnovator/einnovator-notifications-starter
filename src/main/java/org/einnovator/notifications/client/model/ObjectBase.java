package org.einnovator.notifications.client.model;

import java.util.Map;


public class ObjectBase {

	protected String type;
	
	protected String id;
	
	protected String name;

	protected String uri;

	protected String imgUri;
	
	protected Map<String, Object> details;


	public ObjectBase(String type, String id, String name, String uri, String imgUri,  Map<String, Object> details) {
		this.type = type;
		this.id = id;
		this.name = name;
		this.uri = uri;
		this.imgUri = imgUri;
		this.details = details;
	}
	
	public ObjectBase(String type, String id, String name, Map<String, Object> details) {
		this.type = type;
		this.id = id;
		this.name = name;
		this.details = details;
	}

	public ObjectBase(String type, String id, Map<String, Object> details) {
		this.type = type;
		this.id = id;
		this.details = details;
	}

	public ObjectBase(String type, String id, String name) {
		this.type = type;
		this.id = id;
		this.name = name;
	}
	
	public ObjectBase(ObjectBase obj) {
		this(obj.getType(), obj.getId(), obj.getName(), obj.getUri(), obj.getImgUri(), obj.getDetails());
	}
	
	public ObjectBase() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public Map<String, Object> getDetails() {
		return details;
	}

	public void setDetails(Map<String, Object> details) {
		this.details = details;
	}
	
	public String getImgUri() {
		return imgUri;
	}

	public void setImgUri(String imgUri) {
		this.imgUri = imgUri;
	}

	@Override
	public String toString() {
		return "ObjectBase ["
				+ (type != null ? "type=" + type + ", " : "") 
				+ (id != null ? "id=" + id + ", " : "")
				+ (name != null ? "name=" + name : "") 
				+ (uri != null ? "uri=" + uri : "") 
				+ (imgUri != null ? "imgUri=" + imgUri : "") 
				+ (details != null ? "details=" + details : "") 
				+ "]";
	}
	
}
