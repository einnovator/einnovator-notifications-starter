/**
 * 
 */
package org.einnovator.notifications.client.model;


/**
 * Enum for type of  {@code Event}.
 *
 * @see Event
 * @author support@einnovator.org
 *
 */
public enum EventType {
	NOTIFICATION("Notification"),
	PREFERENCE("Preference"),
	ERROR("Error")
	;
	
	private final String displayValue;

	EventType(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
	
	public static EventType parse(String s) {
		for (EventType e: EventType.class.getEnumConstants()) {
			if (e.toString().equalsIgnoreCase(s)) {
				return e;
			}
		}
		return null;
	}
	
	public static EventType parse(String s, EventType defaultValue) {
		EventType value = parse(s);
		return value!=null ? value: defaultValue;
	}


}