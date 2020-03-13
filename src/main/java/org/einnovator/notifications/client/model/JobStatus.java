/**
 * 
 */
package org.einnovator.notifications.client.model;


public enum JobStatus {
	CREATED("Created"),
	PENDING("Pending"),
	RUNNING("Running"),
	ABORTED("Aborted"),
	STOPPED("Stopped"),
	FAILED("Failed"),
	CANCELED("Canceled"),
	PAUSED("Paused"),
	COMPLETED("Completed"),
	ERROR("Error");
	
	private final String displayValue;

	JobStatus(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public String getDisplayName() {
		return displayValue;
	}

	public static JobStatus parse(String s) {
		for (JobStatus e: JobStatus.class.getEnumConstants()) {
			if (e.toString().equalsIgnoreCase(s)) {
				return e;
			}
		}
		return null;
	}
	
	public static JobStatus parse(String s, JobStatus defaultValue) {
		JobStatus value = parse(s);
		return value!=null ? value: defaultValue;
	}


	public boolean isDone() {
		return this==ABORTED || this==STOPPED || this==FAILED || this==COMPLETED;
	}

}