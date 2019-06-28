package org.einnovator.notifications.client.model;

import java.util.List;
import java.util.Map;

import org.einnovator.util.model.Application;
import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class NotificationsRegistration extends ObjectBase {

	@JsonIgnore
	private boolean auto;
	
	private Application application;
	
	private List<NotificationType> types;

	private List<Template> templates;
	
	private Map<String, Object> properties;

	public NotificationsRegistration() {
	}
	
	
	/**
	 * Get the value of property {@code auto}.
	 *
	 * @return the auto
	 */
	public boolean isAuto() {
		return auto;
	}


	/**
	 * Set the value of property {@code auto}.
	 *
	 * @param auto the auto to set
	 */
	public void setAuto(boolean auto) {
		this.auto = auto;
	}


	/**
	 * Get the value of property {@code application}.
	 *
	 * @return the application
	 */
	public Application getApplication() {
		return application;
	}


	/**
	 * Set the value of property {@code application}.
	 *
	 * @param application the application to set
	 */
	public void setApplication(Application application) {
		this.application = application;
	}


	/**
	 * Get the value of property {@code types}.
	 *
	 * @return the types
	 */
	public List<NotificationType> getTypes() {
		return types;
	}


	/**
	 * Set the value of property {@code types}.
	 *
	 * @param types the types to set
	 */
	public void setTypes(List<NotificationType> types) {
		this.types = types;
	}


	/**
	 * Get the value of property {@code templates}.
	 *
	 * @return the templates
	 */
	public List<Template> getTemplates() {
		return templates;
	}

	/**
	 * Set the value of property {@code templates}.
	 *
	 * @param templates the templates to set
	 */
	public void setTemplates(List<Template> templates) {
		this.templates = templates;
	}


	/**
	 * Get the value of property {@code properties}.
	 *
	 * @return the properties
	 */
	public Map<String, Object> getProperties() {
		return properties;
	}


	/**
	 * Set the value of property {@code properties}.
	 *
	 * @param properties the properties to set
	 */
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}


	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
			.append("auto", auto)
			.append("application", application)
			.append("types", types)
			.append("templates", templates)
			.append("properties", properties)
			;
	}

	
}
