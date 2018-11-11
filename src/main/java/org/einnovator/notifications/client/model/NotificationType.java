package org.einnovator.notifications.client.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationType {

	private String app;
	
	private Application application;

	private String id;
	
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

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	public String toString() {
		return "NotificationType [" + (app != null ? "app=" + app + ", " : "") 
				+ (id != null ? "id=" + id + ", " : "")
				+ (sourceType != null ? "sourceType=" + sourceType + ", " : "")
				+ (actionType != null ? "actionType=" + actionType + ", " : "")
				+ (triggers != null ? "triggers=" + triggers + ", " : "")
				+ (priority != null ? "priority=" + priority + ", " : "")
				+ (category != null ? "category=" + category + ", " : "")
				+ (subcategory != null ? "subcategory=" + subcategory + ", " : "")
				+ (description != null ? "description=" + description + ", " : "")
				+ (categoryOrder != null ? "categoryOrder=" + categoryOrder + ", " : "")
				+ (subcategoryOrder != null ? "subcategoryOrder=" + subcategoryOrder + ", " : "")
				+ (template != null ? "template=" + template + ", " : "")
				+ (mailTemplate != null ? "mailTemplate=" + mailTemplate + ", " : "")
				+ (smsTemplate != null ? "smsTemplate=" + smsTemplate + ", " : "")
				+ (order != null ? "order=" + order : "") + "]";
	}
	
	
	
}
