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

	//
	// contructor
	//

	/**
	 * Create instance of {@code NotificationsRegistration}.
	 *
	 */
	public NotificationsRegistration() {
	}
	
	//
	// Getters/Setters
	//

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
	 * @param auto the auto
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
	 * @param application the application
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
	 * @param types the types
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
	 * @param templates the templates
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
	 * @param properties the properties
	 */
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}


	//
	// With
	//

	/**
	 * Set the value of property {@code auto}.
	 *
	 * @param auto the value of property auto
	 * @return this {@code NotificationsRegistration}
	 */
	public NotificationsRegistration withAuto(boolean auto) {
		this.auto = auto;
		return this;
	}

	/**
	 * Set the value of property {@code application}.
	 *
	 * @param application the value of property application
	 * @return this {@code NotificationsRegistration}
	 */
	public NotificationsRegistration withApplication(Application application) {
		this.application = application;
		return this;
	}

	/**
	 * Set the value of property {@code types}.
	 *
	 * @param types the value of property types
	 * @return this {@code NotificationsRegistration}
	 */
	public NotificationsRegistration withTypes(List<NotificationType> types) {
		this.types = types;
		return this;
	}

	/**
	 * Set the value of property {@code templates}.
	 *
	 * @param templates the value of property templates
	 * @return this {@code NotificationsRegistration}
	 */
	public NotificationsRegistration withTemplates(List<Template> templates) {
		this.templates = templates;
		return this;
	}

	/**
	 * Set the value of property {@code properties}.
	 *
	 * @param properties the value of property properties
	 * @return this {@code NotificationsRegistration}
	 */
	public NotificationsRegistration withProperties(Map<String, Object> properties) {
		this.properties = properties;
		return this;
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

	//
	// Template
	//
	
	
	/**
	 * Find {@code Template} with specified name.
	 * 
	 * @param name the name 
	 * @param medium the {@code Medium}
	 * @return the {@code Template} if found, null otherwise
	 */
	public Template findTemplate(String name, Medium medium) {
		if (templates!=null) {
			for (Template template: templates) {
				if (name.equalsIgnoreCase(template.getName()) && (template.getMedium()==null || template.getMedium().equals(medium))) {
					return template;
				}
			}
		}
		return null;
	}
	
}
