package org.einnovator.notifications.client.model;

import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Target extends ObjectBase {

	public static final String TARGET_TYPE_USER = "User";
	public static final String TARGET_TYPE_GROUP = "Group";

	public static final String TARGET_TYPE_ROLE = "Role";
	public static final String TARGET_TYPE_PERMISSION = "Permission";

	public static final String TARGET_TYPE_APP = "App";
	
	static enum Medium {
		ALL,
		APP,
		EMAIL,
		SMS;
	};
	
	private Medium[] mediums;
	
	private String email; //TARGET_TYPE_USER
	
	private String phone; //TARGET_TYPE_USER

	private Integer count;
	
	private String uri;
	
	private Map<String, Object> details;
	
	private String contextId; //Role -> Group; Group -> Parent | Root

	private String tag;
	
	public Target() {
		super();
	}

	public Target(String type, String id, String name) {
		super(type, id, name);
	}

	public Target(String type, String id, String name, Medium[] mediums) {
		super(type, id, name);
		this.mediums = mediums;
	}

	public Medium[] getMediums() {
		return mediums;
	}

	public void setMediums(Medium[] mediums) {
		this.mediums = mediums;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getContextId() {
		return contextId;
	}

	public void setContextId(String contextId) {
		this.contextId = contextId;
	}
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " ["
				+ (getType() != null ? "type=" + getType() + ", " : "")
				+ (getId() != null ? "id=" + getId() + ", " : "")
				+ (getName() != null ? "name=" + getName()  + ", ": "")
				+ (contextId != null ? "contextId=" + contextId + ", " : "")
				+ (tag != null ? "tag=" + tag  + ", ": "")
				+ (getUri() != null ? "uri=" + getUri()  + ", " : "") 
				+ (getDetails() != null ? "uri=" + getDetails() : "")
				+ (email != null ? "email=" + email : "")	
				+ (phone != null ? "phone=" + phone : "")			
				+ (mediums != null ? "mediums=" + Arrays.toString(mediums) + ", " : "")			
				+ (count != null ? "count=" + count : "")
				+ "]";
	}
	
	
	
}
