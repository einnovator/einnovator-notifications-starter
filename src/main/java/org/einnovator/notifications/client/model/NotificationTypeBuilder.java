package org.einnovator.notifications.client.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotificationTypeBuilder {
	
	private String app;
	
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
	
	public NotificationTypeBuilder() {
	}

	public NotificationTypeBuilder app(String app) {
		this.app = app;
		return this;
	}

	public NotificationTypeBuilder id(String id) {
		this.id = id;
		return this;
	}

	public NotificationTypeBuilder category(String category) {
		this.category = category;
		return this;
	}

	public NotificationTypeBuilder subcategory(String subcategory) {
		this.subcategory = subcategory;
		return this;
	}

	public NotificationTypeBuilder description(String description) {
		this.description = description;
		return this;
	}

	public NotificationTypeBuilder categoryOrder(Integer categoryOrder) {
		this.categoryOrder = categoryOrder;
		return this;
	}

	public NotificationTypeBuilder subcategoryOrder(Integer subcategoryOrder) {
		this.subcategoryOrder = subcategoryOrder;
		return this;
	}

	public NotificationTypeBuilder order(Integer order) {
		this.order = order;
		return this;
	}
	
	public NotificationTypeBuilder sourceType(String sourceType) {
		this.sourceType = sourceType;
		return this;
	}
	
	public NotificationTypeBuilder actionType(String actionType) {
		this.actionType = actionType;
		return this;
	}
	
	public NotificationTypeBuilder triggers(List<Trigger> triggers) {
		this.triggers = triggers;
		return this;
	}
	
	public NotificationTypeBuilder triggers(Trigger... triggers) {
		if (this.triggers==null) {
			this.triggers = new ArrayList<>();
		}
		this.triggers.addAll(Arrays.asList(triggers));
		return this;
	}
	
	public NotificationTypeBuilder priority(Integer priority) {
		this.priority = priority;
		return this;
	}
	
	public NotificationTypeBuilder template(Template template) {
		this.template = template;
		return this;
	}
	
	public NotificationTypeBuilder mailTemplate(Template mailTemplate) {
		this.mailTemplate = mailTemplate;
		return this;
	}
	
	public NotificationTypeBuilder smsTemplate(Template smsTemplate) {
		this.smsTemplate = smsTemplate;
		return this;
	}

	public NotificationType build() {
		NotificationType type = new NotificationType();
		type.setApp(app);
		type.setId(id);
		type.setSourceType(sourceType);
		type.setActionType(actionType);
		type.setTriggers(triggers);
		type.setPriority(priority);
		type.setCategory(category);
		type.setSubcategory(subcategory);
		type.setDescription(description);
		type.setOrder(order);
		type.setCategoryOrder(categoryOrder);
		type.setSubcategoryOrder(subcategoryOrder);
		type.setTemplate(template);
		type.setMailTemplate(mailTemplate);
		type.setSmsTemplate(smsTemplate);
		return type;
	}
}
