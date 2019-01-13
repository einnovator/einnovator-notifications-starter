package org.einnovator.notifications.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotifyPreference extends Preference {
	
	public static final String CATEGORY_NOTIFY = "Notify";

	public static final String PREFERENCE_NAME_PREFIX = "notify.";
	
	private String notificationTypeId;
	
	private Boolean enabled = true;
	
	public static enum Frequency {
		IMMEDIATE,
		EVERY_HOUR,
		DAILY,
		TWICE_PER_DAY,
		WEEKLY,
		BI_WEEKLY,
		WEEKEND,
		BI_MONTLY,
		MONTLY;
	}
	
	private Boolean email;
	
	private Frequency emailFrequency;

	private Boolean sms;
	
	private Frequency smsFrequency;

	public NotifyPreference() {
	}

	
	public NotifyPreference(String notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
	}

	@Override
	public String getCategory() {
		return CATEGORY_NOTIFY;
	}
	
	public String getNotificationTypeId() {
		return notificationTypeId;
	}

	public void setNotificationTypeId(String notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean isEmail() {
		return email;
	}

	public void setEmail(Boolean email) {
		this.email = email;
	}

	public Frequency getEmailFrequency() {
		return emailFrequency;
	}

	public void setEmailFrequency(Frequency emailFrequency) {
		this.emailFrequency = emailFrequency;
	}

	public Boolean isSms() {
		return sms;
	}

	public void setSms(Boolean sms) {
		this.sms = sms;
	}

	public Frequency getSmsFrequency() {
		return smsFrequency;
	}

	public void setSmsFrequency(Frequency smsFrequency) {
		this.smsFrequency = smsFrequency;
	}
	public Boolean getEnabled() {
		return enabled;
	}

	public Boolean getEmail() {
		return email;
	}

	public Boolean getSms() {
		return sms;
	}

	@Override
	public String toString() {
		return "NotifyPreference ["
				+ (notificationTypeId != null ? "notificationTypeId=" + notificationTypeId + ", " : "")
				+ (enabled != null ? "enabled=" + enabled + ", " : "") + (email != null ? "email=" + email + ", " : "")
				+ (emailFrequency != null ? "emailFrequency=" + emailFrequency + ", " : "")
				+ (sms != null ? "sms=" + sms + ", " : "")
				+ (smsFrequency != null ? "smsFrequency=" + smsFrequency : "") + "]";
	}



	@Override
	public String getName() {
		return PREFERENCE_NAME_PREFIX + notificationTypeId;
	}
	
	@Override
	void setName(String name) {
	}

	@Override
	void setCategory(String category) {
	}
	
	
}
