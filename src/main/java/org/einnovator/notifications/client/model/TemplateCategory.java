/**
 * 
 */
package org.einnovator.notifications.client.model;


public enum TemplateCategory {
	NOTIFICATION("Notification"),
	NOTICE("Notice"),
	NEWSLETTER("Newsletter"),
	PERIODIC("Periodic"),
	SYSTEM("System"),
	OTHER("Other"),
	;
	
	private final String displayValue;

	TemplateCategory(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
	
	public static TemplateCategory parse(String s) {
		for (TemplateCategory e: TemplateCategory.class.getEnumConstants()) {
			if (e.toString().equalsIgnoreCase(s)) {
				return e;
			}
		}
		return null;
	}
	
	public static TemplateCategory parse(String s, TemplateCategory defaultValue) {
		TemplateCategory value = parse(s);
		return value!=null ? value: defaultValue;
	}


}