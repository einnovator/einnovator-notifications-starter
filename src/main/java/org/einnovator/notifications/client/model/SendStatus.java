/**
 * 
 */
package org.einnovator.notifications.client.model;


public enum SendStatus {
	PENDING("Pending"),
	SENT("Sent"),
	RETRY("Retry"),
	SKIPPED("Skipped"),
	FAILED("Failed");
	
	private final String displayValue;

	SendStatus(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public String getDisplayName() {
		return displayValue;
	}

	public static SendStatus parse(String s) {
		for (SendStatus e: SendStatus.class.getEnumConstants()) {
			if (e.toString().equalsIgnoreCase(s)) {
				return e;
			}
		}
		return null;
	}
	
	public static SendStatus parse(String s, SendStatus defaultValue) {
		SendStatus value = parse(s);
		return value!=null ? value: defaultValue;
	}


	public boolean isDone() {
		return this==SENT || this==FAILED;
	}

}