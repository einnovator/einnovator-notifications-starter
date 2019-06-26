/**
 * 
 */
package org.einnovator.notifications.client.model;


public enum ContentType {
	HTML("Html"),
	CSS("Css"),
	TEXT("Text")
	;
	
	private final String displayValue;

	ContentType(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
	
	public static ContentType parse(String s) {
		for (ContentType e: ContentType.class.getEnumConstants()) {
			if (e.toString().equalsIgnoreCase(s)) {
				return e;
			}
		}
		return null;
	}
	
	public static ContentType parse(String s, ContentType defaultValue) {
		ContentType value = parse(s);
		return value!=null ? value: defaultValue;
	}


}