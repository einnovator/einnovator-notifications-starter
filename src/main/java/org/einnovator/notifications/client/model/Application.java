package org.einnovator.notifications.client.model;

import java.util.List;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Application extends ObjectBase2 {

	private String instanceId;
	
	private List<NotificationType> notificationTypes;
	 
	
	public Application() {
		super();
	}

	public Application(Object obj) {
		super(obj);
	}

	public Application(String type, String id, String name) {
		super(type, id, name);
	}

	public Application(String id, String name) {
		super(null, id, name);
	}

	public Application(String id, String name, List<NotificationType> notificationTypes) {
		super(null, id, name);
		this.notificationTypes = notificationTypes;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public List<NotificationType> getNotificationTypes() {
		return notificationTypes;
	}

	public void setNotificationTypes(List<NotificationType> notificationTypes) {
		this.notificationTypes = notificationTypes;
	}

	public Application name(String name, boolean force) {
		if (force || StringUtils.isEmpty(this.getName())) {
			this.setName(name);
		}
		if (StringUtils.isEmpty(this.getId())) {
			setId(this.getName());
		}
		return this;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [" 
				+ (getType() != null ? "type=" + getType() + ", " : "")
				+ (getId() != null ? "id=" + getId() + ", " : "")
				+ (getName() != null ? "name=" + getName() : "")
				+ (instanceId != null ? "instanceId=" + getInstanceId() : "")
				+ (getUri() != null ? "uri=" + getUri() : "")
				+ (getImg() != null ? "img=" + getImg() : "")
				+ (getDetails() != null ? "details=" + getDetails() : "")
				+ "]";
	}


}
