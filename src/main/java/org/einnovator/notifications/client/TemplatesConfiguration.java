package org.einnovator.notifications.client;

public class TemplatesConfiguration {

	public final String DEFAULT_TEMPLATE_DIR = "notifications/app";

	public final String DEFAULT_MAIL_TEMPLATE_DIR = "notifications/mail";

	public final String DEFAULT_SMS_TEMPLATE_DIR = "notifications/sms";

	private String app = DEFAULT_TEMPLATE_DIR;

	private String mail = DEFAULT_MAIL_TEMPLATE_DIR;
	
	private String sms = DEFAULT_SMS_TEMPLATE_DIR;

	public TemplatesConfiguration() {
	}
	
	
	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [" 
				+ (app != null ? "app=" + app + ", " : "")
				+ (mail != null ? "mail=" + mail + ", " : "")
				+ (sms != null ? "sms=" + sms : "") + "]";
	}
}
