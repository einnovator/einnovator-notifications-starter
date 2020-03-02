/**
 * 
 */
package org.einnovator.notifications.client.model;


public enum TargetType {
	USER("User"),
	GROUP("Group"),
	ROLE("Role"),
	APP("App");
	;
	
	private final String displayValue;

	TargetType(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
	
	public static TargetType parse(String s) {
		for (TargetType e: TargetType.class.getEnumConstants()) {
			if (e.toString().equalsIgnoreCase(s)) {
				return e;
			}
		}
		return null;
	}
	
	public static TargetType parse(String s, TargetType defaultValue) {
		TargetType value = parse(s);
		return value!=null ? value: defaultValue;
	}


}