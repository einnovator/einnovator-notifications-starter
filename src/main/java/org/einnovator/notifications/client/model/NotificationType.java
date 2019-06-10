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
	
	private String description;
	
	private Integer categoryOrder;
	
	private Integer subcategoryOrder;

	private Integer order;
	
	private Template template;

	private Template mailTemplate;
	
	private Template smsTemplate;
	
	public NotificationType() {
	}
	
	public String getApp() {
		return app;
	}

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

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public Integer getCategoryOrder() {
		return categoryOrder;
	}

	public void setCategoryOrder(Integer categoryOrder) {
		this.categoryOrder = categoryOrder;
	}

	public Integer getSubcategoryOrder() {
		return subcategoryOrder;
	}

	public void setSubcategoryOrder(Integer subcategoryOrder) {
		this.subcategoryOrder = subcategoryOrder;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public Template getMailTemplate() {
		return mailTemplate;
	}

	public void setMailTemplate(Template mailTemplate) {
		this.mailTemplate = mailTemplate;
	}

	public Template getSmsTemplate() {
		return smsTemplate;
	}

	public void setSmsTemplate(Template smsTemplate) {
		this.smsTemplate = smsTemplate;
	}
	

	public List<Trigger> getTriggers() {
		return triggers;
	}

	public void setTriggers(List<Trigger> triggers) {
		this.triggers = triggers;
	}

	
	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
			.append("typeId", typeId)
			.append("app", app)
			.append("sourceType", sourceType)
			.append("actionType", actionType)
			.append("triggers", triggers)
			.append("priority", priority)
			.append("category", category)
			.append("subcategory", subcategory)
			.append("category", category)
			.append("category", category)
			.append("categoryOrder", categoryOrder)
			.append("subcategoryOrder", subcategoryOrder)
			.append("template", template)
			.append("description", description)
			;
	}
			
	
}
