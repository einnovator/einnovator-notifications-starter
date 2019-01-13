package org.einnovator.notifications.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Trigger extends ObjectBase2 {

	public static final String TRIGGER_TYPE_ACCEPT = "Accept";
	public static final String TRIGGER_TYPE_REJECT = "Reject";
	
	public Trigger() {
		super();
	}

	public Trigger(String type, String id, String name) {
		super(type, id, name);
	}
	
	public Trigger(Trigger trigger) {
		super(trigger);
	}

	@Override
	public String toString() {
		return "Trigger ["
				+ (getType() != null ? "type=" + getType() + ", " : "")
				+ (getId() != null ? "id=" + getId() + ", " : "")
				+ (getName() != null ? "name=" + getName() : "")
				+ (getUri() != null ? "uri=" + getUri() : "") 
				+ (getDetails() != null ? "uri=" + getDetails() : "")
				+ "]";
	}

}
