package org.einnovator.notifications.client.model;

import org.einnovator.notifications.client.model.NotifyPreference.Frequency;

public class NotifyPreferenceBuilder {
		
	private String notificationTypeId;
	
	private Boolean enabled = true;
	
	private Boolean email;
	
	private Frequency emailFrequency;

	private Boolean sms;
	
	private Frequency smsFrequency;

	public NotifyPreferenceBuilder() {
	}

	public NotifyPreferenceBuilder(String notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
	}

	public void setNotificationTypeId(String notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setEmail(Boolean email) {
		this.email = email;
	}

	public void setEmailFrequency(Frequency emailFrequency) {
		this.emailFrequency = emailFrequency;
	}

	public void setSms(Boolean sms) {
		this.sms = sms;
	}

	public void setSmsFrequency(Frequency smsFrequency) {
		this.smsFrequency = smsFrequency;
	}

	public NotifyPreference build() {
		NotifyPreference preference = new NotifyPreference();
		preference.setNotificationTypeId(notificationTypeId);
		preference.setEnabled(enabled);
		preference.setEmail(email);
		preference.setSms(sms);
		preference.setEmailFrequency(emailFrequency);
		preference.setSmsFrequency(smsFrequency);
		return preference;
	}

}
