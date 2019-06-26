package org.einnovator.notifications.client.config;

import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;

public class TemplatesConfiguration extends ObjectBase {

	public final String DEFAULT_TEMPLATE_URI = "notifications/";

	public final String DEFAULT_APP_TEMPLATE_DIR = DEFAULT_TEMPLATE_URI + "/app";

	public final String DEFAULT_MAIL_TEMPLATE_DIR = DEFAULT_TEMPLATE_URI + "/mail";

	public final String DEFAULT_SMS_TEMPLATE_DIR = DEFAULT_TEMPLATE_URI + "/sms";


	private String uri = DEFAULT_TEMPLATE_URI;

	private String app = DEFAULT_APP_TEMPLATE_DIR;

	private String mail = DEFAULT_MAIL_TEMPLATE_DIR;
	
	private String sms = DEFAULT_SMS_TEMPLATE_DIR;
	
	public TemplatesConfiguration() {
	}
	
	/**
	 * Get the value of property {@code uri}.
	 *
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * Set the value of property {@code uri}.
	 *
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * Get the value of property {@code app}.
	 *
	 * @return the app
	 */
	public String getApp() {
		return app;
	}

	/**
	 * Set the value of property {@code app}.
	 *
	 * @param app the app to set
	 */
	public void setApp(String app) {
		this.app = app;
	}

	/**
	 * Get the value of property {@code mail}.
	 *
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Set the value of property {@code mail}.
	 *
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Get the value of property {@code sms}.
	 *
	 * @return the sms
	 */
	public String getSms() {
		return sms;
	}

	/**
	 * Set the value of property {@code sms}.
	 *
	 * @param sms the sms to set
	 */
	public void setSms(String sms) {
		this.sms = sms;
	}

	@Override
	public ToStringCreator toString(ToStringCreator creator) {
		return creator
				.append("uri", uri)
				.append("app", app)
				.append("mail", mail)
				.append("sms", sms)
				;
	}
	
}
