package org.einnovator.notifications.client.model;

import org.einnovator.util.model.EntityBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * A type of {@code Notification} registered by an app.
 *
 * @see NotificationsRegistration
 * @author support@einnovator.org
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class NotificationType extends EntityBase {

	private String app;
	
	private String typeId;
	
	private String sourceType;
	
	private String actionType;
	
	private Integer priority;
	
	private String category;
	
	private String subcategory;

	private String label;

	private String description;
	
	private Integer categoryOrder;
	
	private Integer subcategoryOrder;

	private Integer order;
	
	private Template template;

	private Template mailTemplate;
	
	private Template smsTemplate;
	
	private Boolean enabled;

	private Boolean admin;


	public NotificationType() {
	}
	
	//
	// Getters/Setters
	//

	/**
	 * Get the value of property {@code app}.
	 *
	 * @return the app
	 */
	public String getApp() {
		return app;
	}

	/**
	 * Set the value of property {@code app}.
	 *
	 * @param app the value of property app
	 */
	public void setApp(String app) {
		this.app = app;
	}

	/**
	 * Get the value of property {@code typeId}.
	 *
	 * @return the typeId
	 */
	public String getTypeId() {
		return typeId;
	}

	/**
	 * Set the value of property {@code typeId}.
	 *
	 * @param typeId the value of property typeId
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	/**
	 * Get the value of property {@code sourceType}.
	 *
	 * @return the sourceType
	 */
	public String getSourceType() {
		return sourceType;
	}

	/**
	 * Set the value of property {@code sourceType}.
	 *
	 * @param sourceType the value of property sourceType
	 */
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	/**
	 * Get the value of property {@code actionType}.
	 *
	 * @return the actionType
	 */
	public String getActionType() {
		return actionType;
	}

	/**
	 * Set the value of property {@code actionType}.
	 *
	 * @param actionType the value of property actionType
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	/**
	 * Get the value of property {@code priority}.
	 *
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * Set the value of property {@code priority}.
	 *
	 * @param priority the value of property priority
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * Get the value of property {@code category}.
	 *
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Set the value of property {@code category}.
	 *
	 * @param category the value of property category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Get the value of property {@code subcategory}.
	 *
	 * @return the subcategory
	 */
	public String getSubcategory() {
		return subcategory;
	}

	/**
	 * Set the value of property {@code subcategory}.
	 *
	 * @param subcategory the value of property subcategory
	 */
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	/**
	 * Get the value of property {@code label}.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Set the value of property {@code label}.
	 *
	 * @param label the value of property label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Get the value of property {@code description}.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the value of property {@code description}.
	 *
	 * @param description the value of property description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the value of property {@code categoryOrder}.
	 *
	 * @return the categoryOrder
	 */
	public Integer getCategoryOrder() {
		return categoryOrder;
	}

	/**
	 * Set the value of property {@code categoryOrder}.
	 *
	 * @param categoryOrder the value of property categoryOrder
	 */
	public void setCategoryOrder(Integer categoryOrder) {
		this.categoryOrder = categoryOrder;
	}

	/**
	 * Get the value of property {@code subcategoryOrder}.
	 *
	 * @return the subcategoryOrder
	 */
	public Integer getSubcategoryOrder() {
		return subcategoryOrder;
	}

	/**
	 * Set the value of property {@code subcategoryOrder}.
	 *
	 * @param subcategoryOrder the value of property subcategoryOrder
	 */
	public void setSubcategoryOrder(Integer subcategoryOrder) {
		this.subcategoryOrder = subcategoryOrder;
	}

	/**
	 * Get the value of property {@code order}.
	 *
	 * @return the order
	 */
	public Integer getOrder() {
		return order;
	}

	/**
	 * Set the value of property {@code order}.
	 *
	 * @param order the value of property order
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

	/**
	 * Get the value of property {@code template}.
	 *
	 * @return the template
	 */
	public Template getTemplate() {
		return template;
	}

	/**
	 * Set the value of property {@code template}.
	 *
	 * @param template the value of property template
	 */
	public void setTemplate(Template template) {
		this.template = template;
	}

	/**
	 * Get the value of property {@code mailTemplate}.
	 *
	 * @return the mailTemplate
	 */
	public Template getMailTemplate() {
		return mailTemplate;
	}

	/**
	 * Set the value of property {@code mailTemplate}.
	 *
	 * @param mailTemplate the value of property mailTemplate
	 */
	public void setMailTemplate(Template mailTemplate) {
		this.mailTemplate = mailTemplate;
	}

	/**
	 * Get the value of property {@code smsTemplate}.
	 *
	 * @return the smsTemplate
	 */
	public Template getSmsTemplate() {
		return smsTemplate;
	}

	/**
	 * Set the value of property {@code smsTemplate}.
	 *
	 * @param smsTemplate the value of property smsTemplate
	 */
	public void setSmsTemplate(Template smsTemplate) {
		this.smsTemplate = smsTemplate;
	}

	/**
	 * Get the value of property {@code enabled}.
	 *
	 * @return the enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * Set the value of property {@code enabled}.
	 *
	 * @param enabled the value of property enabled
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Get the value of property {@code admin}.
	 *
	 * @return the admin
	 */
	public Boolean getAdmin() {
		return admin;
	}

	/**
	 * Set the value of property {@code admin}.
	 *
	 * @param admin the value of property admin
	 */
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	//
	// With
	//

	/**
	 * Set the value of property {@code app}.
	 *
	 * @param app the value of property app
	 * @return this {@code NotificationType}
	 */
	public NotificationType withApp(String app) {
		this.app = app;
		return this;
	}
	
	/**
	 * Set the value of property {@code typeId}.
	 *
	 * @param typeId the value of property typeId
	 * @return this {@code NotificationType}
	 */
	public NotificationType withTypeId(String typeId) {
		this.typeId = typeId;
		return this;
	}

	/**
	 * Set the value of property {@code sourceType}.
	 *
	 * @param sourceType the value of property sourceType
	 * @return this {@code NotificationType}
	 */
	public NotificationType withSourceType(String sourceType) {
		this.sourceType = sourceType;
		return this;
	}

	/**
	 * Set the value of property {@code actionType}.
	 *
	 * @param actionType the value of property actionType
	 * @return this {@code NotificationType}
	 */
	public NotificationType withActionType(String actionType) {
		this.actionType = actionType;
		return this;
	}

	/**
	 * Set the value of property {@code priority}.
	 *
	 * @param priority the value of property priority
	 * @return this {@code NotificationType}
	 */
	public NotificationType withPriority(Integer priority) {
		this.priority = priority;
		return this;
	}

	/**
	 * Set the value of property {@code category}.
	 *
	 * @param category the value of property category
	 * @return this {@code NotificationType}
	 */
	public NotificationType withCategory(String category) {
		this.category = category;
		return this;
	}

	/**
	 * Set the value of property {@code subcategory}.
	 *
	 * @param subcategory the value of property subcategory
	 * @return this {@code NotificationType}
	 */
	public NotificationType withSubcategory(String subcategory) {
		this.subcategory = subcategory;
		return this;
	}

	/**
	 * Set the value of property {@code label}.
	 *
	 * @param label the value of property label
	 * @return this {@code NotificationType}
	 */
	public NotificationType withLabel(String label) {
		this.label = label;
		return this;
	}

	/**
	 * Set the value of property {@code description}.
	 *
	 * @param description the value of property description
	 * @return this {@code NotificationType}
	 */
	public NotificationType withDescription(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Set the value of property {@code categoryOrder}.
	 *
	 * @param categoryOrder the value of property categoryOrder
	 * @return this {@code NotificationType}
	 */
	public NotificationType withCategoryOrder(Integer categoryOrder) {
		this.categoryOrder = categoryOrder;
		return this;
	}

	/**
	 * Set the value of property {@code subcategoryOrder}.
	 *
	 * @param subcategoryOrder the value of property subcategoryOrder
	 * @return this {@code NotificationType}
	 */
	public NotificationType withSubcategoryOrder(Integer subcategoryOrder) {
		this.subcategoryOrder = subcategoryOrder;
		return this;
	}

	/**
	 * Set the value of property {@code order}.
	 *
	 * @param order the value of property order
	 * @return this {@code NotificationType}
	 */
	public NotificationType withOrder(Integer order) {
		this.order = order;
		return this;
	}

	/**
	 * Set the value of property {@code template}.
	 *
	 * @param template the value of property template
	 * @return this {@code NotificationType}
	 */
	public NotificationType withTemplate(Template template) {
		this.template = template;
		return this;
	}

	/**
	 * Set the value of property {@code mailTemplate}.
	 *
	 * @param mailTemplate the value of property mailTemplate
	 * @return this {@code NotificationType}
	 */
	public NotificationType withMailTemplate(Template mailTemplate) {
		this.mailTemplate = mailTemplate;
		return this;
	}

	/**
	 * Set the value of property {@code smsTemplate}.
	 *
	 * @param smsTemplate the value of property smsTemplate
	 * @return this {@code NotificationType}
	 */
	public NotificationType withSmsTemplate(Template smsTemplate) {
		this.smsTemplate = smsTemplate;
		return this;
	}

	/**
	 * Set the value of property {@code enabled}.
	 *
	 * @param enabled the value of property enabled
	 * @return this {@code NotificationType}
	 */
	public NotificationType withEnabled(Boolean enabled) {
		this.enabled = enabled;
		return this;
	}

	/**
	 * Set the value of property {@code admin}.
	 *
	 * @param admin the value of property admin
	 * @return this {@code NotificationType}
	 */
	public NotificationType withAdmin(Boolean admin) {
		this.admin = admin;
		return this;
	}
			
	
	
	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
			.append("typeId", typeId)
			.append("app", app)
			.append("sourceType", sourceType)
			.append("actionType", actionType)
			.append("label", label)
			.append("category", category)
			.append("subcategory", subcategory)
			.append("priority", priority)
			.append("order", order)
			.append("categoryOrder", categoryOrder)
			.append("subcategoryOrder", subcategoryOrder)
			.append("admin", admin)
			.append("enabled", enabled)
			.append("template", template)
			.append("description", description)
			;
	}

}
