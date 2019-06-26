/**
 * 
 */
package org.einnovator.notifications.client.model;


public enum Medium {
	EMAIL("Email"),
	SMS("SMS"),
	APP("App");
	
	private final String displayValue;

	Medium(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
	
	public static Medium parse(String s) {
		for (Medium e: Medium.class.getEnumConstants()) {
			if (e.toString().equalsIgnoreCase(s)) {
				return e;
			}
		}
		return null;
	}
	
	public static Medium parse(String s, Medium defaultValue) {
		Medium value = parse(s);
		return value!=null ? value: defaultValue;
	}


}