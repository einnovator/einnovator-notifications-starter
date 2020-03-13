/**
 * 
 */
package org.einnovator.notifications.client.model;


public enum ReceiverType {
	USER("User"),
	GROUP("Group"),
	ROLE("Role"),
	PERMISSION("Permission"),	
	SYSTEM("System")
	;
	
	private final String displayValue;

	ReceiverType(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
	
	public static ReceiverType parse(String s) {
		for (ReceiverType e: ReceiverType.class.getEnumConstants()) {
			if (e.toString().equalsIgnoreCase(s)) {
				return e;
			}
		}
		return null;
	}
	
	public static ReceiverType parse(String s, ReceiverType defaultValue) {
		ReceiverType value = parse(s);
		return value!=null ? value: defaultValue;
	}


}