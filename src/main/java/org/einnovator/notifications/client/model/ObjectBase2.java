package org.einnovator.notifications.client.model;

import java.util.Map;

import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
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
		this(obj.type, obj.id, obj.name, obj.uri, obj.img, obj.details);
	}
	
	public ObjectBase2(Object protoype) {
		super(protoype);
	}


	public ObjectBase2() {
	}

	/**
	 * Get the value of property {@code type}.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Set the value of property {@code type}.
	 *
	 * @param type the value of property type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Get the value of property {@code id}.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set the value of property {@code id}.
	 *
	 * @param id the value of property id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Get the value of property {@code name}.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the value of property {@code name}.
	 *
	 * @param name the value of property name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the value of property {@code uri}.
	 *
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * Set the value of property {@code uri}.
	 *
	 * @param uri the value of property uri
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * Get the value of property {@code img}.
	 *
	 * @return the img
	 */
	public String getImg() {
		return img;
	}

	/**
	 * Set the value of property {@code img}.
	 *
	 * @param img the value of property img
	 */
	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * Get the value of property {@code details}.
	 *
	 * @return the details
	 */
	public Map<String, Object> getDetails() {
		return details;
	}

	/**
	 * Set the value of property {@code details}.
	 *
	 * @param details the value of property details
	 */
	public void setDetails(Map<String, Object> details) {
		this.details = details;
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
