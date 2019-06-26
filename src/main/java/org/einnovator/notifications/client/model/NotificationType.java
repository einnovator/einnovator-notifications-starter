package org.einnovator.notifications.client.model;

import java.util.List;

import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class NotificationType extends ObjectBase {

	private String app;
	
	private String typeId;
	
	private String sourceType;
	
	private String actionType;
	
	private List<Trigger> triggers;
	
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
	 * @param app the app to set
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
	 * @param typeId the typeId to set
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
	 * @param sourceType the sourceType to set
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
	 * @param actionType the actionType to set
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	/**
	 * Get the value of property {@code triggers}.
	 *
	 * @return the triggers
	 */
	public List<Trigger> getTriggers() {
		return triggers;
	}

	/**
	 * Set the value of property {@code triggers}.
	 *
	 * @param triggers the triggers to set
	 */
	public void setTriggers(List<Trigger> triggers) {
		this.triggers = triggers;
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
	 * @param priority the priority to set
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
	 * @param category the category to set
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
	 * @param subcategory the subcategory to set
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
	 * @param label the label to set
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
	 * @param description the description to set
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
	 * @param categoryOrder the categoryOrder to set
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
	 * @param subcategoryOrder the subcategoryOrder to set
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
	 * @param order the order to set
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
	 * @param template the template to set
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
	 * @param mailTemplate the mailTemplate to set
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
	 * @param smsTemplate the smsTemplate to set
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
	 * @param enabled the enabled to set
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
	 * @param admin the admin to set
	 */
	public void setAdmin(Boolean admin) {
		this.admin = admin;
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
			.append("triggers", triggers)
			.append("description", description)
			;
	}
			
	
}
