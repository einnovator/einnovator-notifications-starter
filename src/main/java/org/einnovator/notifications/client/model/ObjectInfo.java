package org.einnovator.notifications.client.model;

import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ObjectInfo extends org.einnovator.util.model.ObjectBase {

	protected String type;
	
	protected String id;
	
	protected String name;

	protected String url;

	public ObjectInfo() {
	}	
	/**
	 * Get the value of property {@code type}.
	 *
	 * @return the value of {@code type}
	 */
	public String getType() {
		return type;
	}

	/**
	 * Set the value of property {@code type}.
	 *
	 * @param type the value of {@code type}
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Get the value of property {@code id}.
	 *
	 * @return the value of {@code id}
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set the value of property {@code id}.
	 *
	 * @param id the value of {@code id}
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Get the value of property {@code name}.
	 *
	 * @return the value of {@code name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the value of property {@code name}.
	 *
	 * @param name the value of {@code name}
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the value of property {@code url}.
	 *
	 * @return the value of {@code url}
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Set the value of property {@code url}.
	 *
	 * @param url the value of {@code url}
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
				.append("type", type)
				.append("id", id)
				.append("name", name)
				.append("url", url)
				;
	}
}
