/**
 * 
 */
package org.einnovator.notifications.client.model;


public enum SystemGroupType {
	SUBSCRIBERS("Subscribers"),
	ALL("All"),
	ADMIN("Admin")
	;
	
	private final String displayValue;

	SystemGroupType(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
	
	public static SystemGroupType parse(String s) {
		for (SystemGroupType e: SystemGroupType.class.getEnumConstants()) {
			if (e.toString().equalsIgnoreCase(s)) {
				return e;
			}
		}
		return null;
	}
	
	public static SystemGroupType parse(String s, SystemGroupType defaultValue) {
		SystemGroupType value = parse(s);
		return value!=null ? value: defaultValue;
	}


}