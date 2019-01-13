package org.einnovator.notifications.client.model;

import java.util.Map;

import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;


public class ObjectBase2 extends ObjectBase {

	protected String type;
	
	protected String id;
	
	protected String name;

	protected String uri;

	protected String img;
	
	protected Map<String, Object> details;


	public ObjectBase2(String type, String id, String name, String uri, String img,  Map<String, Object> details) {
		this.type = type;
		this.id = id;
		this.name = name;
		this.uri = uri;
		this.img = img;
		this.details = details;
	}
	
	public ObjectBase2(String type, String id, String name, Map<String, Object> details) {
		this.type = type;
		this.id = id;
		this.name = name;
		this.details = details;
	}

	public ObjectBase2(String type, String id, Map<String, Object> details) {
		this.type = type;
		this.id = id;
		this.details = details;
	}

	public ObjectBase2(String type, String id, String name) {
		this.type = type;
		this.id = id;
		this.name = name;
	}
	
	public ObjectBase2(ObjectBase2 obj) {
		this(obj.getType(), obj.getId(), obj.getName(), obj.getUri(), obj.getImg(), obj.getDetails());
	}
	
	public ObjectBase2() {
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
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public ToStringCreator toString0(ToStringCreator creator) {
		return creator
				.append("type", type)
				.append("id", id)
				.append("name", name)
				.append("uri", uri)
				.append("img", img)
				.append("details", details)
				.append("type", type)
				.append("type", type)
				;
	}

	
}
