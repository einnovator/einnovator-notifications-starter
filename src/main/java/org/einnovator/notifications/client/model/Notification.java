package org.einnovator.notifications.client.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * A {@code Notification} sent by the Notifications Server to communicate an {@code Event}.
 *
 * @see Event
 * @see Source
 * @see Action
 * @see Target
 * @author support@einnovator.org
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Notification extends ObjectBase2 {

	private String app;
	
	private Preference preference;

	private Source source;
	
	private Source param;

	private Action action;
	
	private PrincipalDetails principal;

	private Meta meta;
	
	private List<Target> targets;
	
	private String uri;
			
	private Long timestamp;
	
	private Date date;
	
	private String formattedDate;

	private String subject;

	private Boolean html;
	
	private String content;

	private String sms;

	private Map<String, Object> env;
	
	//
	// Constructors
	//
	
	/**
	 * Create instance of {@code Notification}.
	 *
	 */
	public Notification() {
	}

	
	/**
	 * Create instance of {@code Notification}.
	 *
	 * @param obj a prototype
	 */
	public Notification(Object obj) {
		super(obj);
	}
	
	//
	// Getters/Setters
	//


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
	 * @param app the value of property app
	 */
	public void setApp(String app) {
		this.app = app;
	}


	/**
	 * Get the value of property {@code preference}.
	 *
	 * @return the preference
	 */
	public Preference getPreference() {
		return preference;
	}


	/**
	 * Set the value of property {@code preference}.
	 *
	 * @param preference the value of property preference
	 */
	public void setPreference(Preference preference) {
		this.preference = preference;
	}


	/**
	 * Get the value of property {@code source}.
	 *
	 * @return the source
	 */
	public Source getSource() {
		return source;
	}


	/**
	 * Set the value of property {@code source}.
	 *
	 * @param source the value of property source
	 */
	public void setSource(Source source) {
		this.source = source;
	}


	/**
	 * Get the value of property {@code param}.
	 *
	 * @return the param
	 */
	public Source getParam() {
		return param;
	}


	/**
	 * Set the value of property {@code param}.
	 *
	 * @param param the value of property param
	 */
	public void setParam(Source param) {
		this.param = param;
	}


	/**
	 * Get the value of property {@code action}.
	 *
	 * @return the action
	 */
	public Action getAction() {
		return action;
	}


	/**
	 * Set the value of property {@code action}.
	 *
	 * @param action the value of property action
	 */
	public void setAction(Action action) {
		this.action = action;
	}


	/**
	 * Get the value of property {@code principal}.
	 *
	 * @return the principal
	 */
	public PrincipalDetails getPrincipal() {
		return principal;
	}


	/**
	 * Set the value of property {@code principal}.
	 *
	 * @param principal the value of property principal
	 */
	public void setPrincipal(PrincipalDetails principal) {
		this.principal = principal;
	}


	/**
	 * Get the value of property {@code meta}.
	 *
	 * @return the meta
	 */
	public Meta getMeta() {
		return meta;
	}


	/**
	 * Set the value of property {@code meta}.
	 *
	 * @param meta the value of property meta
	 */
	public void setMeta(Meta meta) {
		this.meta = meta;
	}


	/**
	 * Get the value of property {@code targets}.
	 *
	 * @return the targets
	 */
	public List<Target> getTargets() {
		return targets;
	}


	/**
	 * Set the value of property {@code targets}.
	 *
	 * @param targets the value of property targets
	 */
	public void setTargets(List<Target> targets) {
		this.targets = targets;
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
	 * @param uri the value of property uri
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}


	/**
	 * Get the value of property {@code timestamp}.
	 *
	 * @return the timestamp
	 */
	public Long getTimestamp() {
		return timestamp;
	}


	/**
	 * Set the value of property {@code timestamp}.
	 *
	 * @param timestamp the value of property timestamp
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}


	/**
	 * Get the value of property {@code date}.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}


	/**
	 * Set the value of property {@code date}.
	 *
	 * @param date the value of property date
	 */
	public void setDate(Date date) {
		this.date = date;
	}


	/**
	 * Get the value of property {@code formattedDate}.
	 *
	 * @return the formattedDate
	 */
	public String getFormattedDate() {
		return formattedDate;
	}


	/**
	 * Set the value of property {@code formattedDate}.
	 *
	 * @param formattedDate the value of property formattedDate
	 */
	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}


	/**
	 * Get the value of property {@code subject}.
	 *
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}


	/**
	 * Set the value of property {@code subject}.
	 *
	 * @param subject the value of property subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}


	/**
	 * Get the value of property {@code html}.
	 *
	 * @return the html
	 */
	public Boolean getHtml() {
		return html;
	}


	/**
	 * Set the value of property {@code html}.
	 *
	 * @param html the value of property html
	 */
	public void setHtml(Boolean html) {
		this.html = html;
	}


	/**
	 * Get the value of property {@code content}.
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
	}


	/**
	 * Set the value of property {@code content}.
	 *
	 * @param content the value of property content
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * @param sms the value of property sms
	 */
	public void setSms(String sms) {
		this.sms = sms;
	}


	/**
	 * Get the value of property {@code env}.
	 *
	 * @return the env
	 */
	public Map<String, Object> getEnv() {
		return env;
	}


	/**
	 * Set the value of property {@code env}.
	 *
	 * @param env the value of property env
	 */
	public void setEnv(Map<String, Object> env) {
		this.env = env;
	}

	//
	// With
	//

	/**
	 * Set the value of property {@code app}.
	 *
	 * @param app the value of property app
	 * @return this {@code Notification}
	 */
	public Notification withApp(String app) {
		this.app = app;
		return this;
	}

	/**
	 * Set the value of property {@code preference}.
	 *
	 * @param preference the value of property preference
	 * @return this {@code Notification}
	 */
	public Notification withPreference(Preference preference) {
		this.preference = preference;
		return this;
	}


	/**
	 * Set the value of property {@code source}.
	 *
	 * @param source the value of property source
	 * @return this {@code Notification}
	 */
	public Notification withSource(Source source) {
		this.source = source;
		return this;
	}


	/**
	 * Set the value of property {@code param}.
	 *
	 * @param param the value of property param
	 * @return this {@code Notification}
	 */
	public Notification withParam(Source param) {
		this.param = param;
		return this;
	}


	/**
	 * Set the value of property {@code action}.
	 *
	 * @param action the value of property action
	 * @return this {@code Notification}
	 */
	public Notification withAction(Action action) {
		this.action = action;
		return this;
	}


	/**
	 * Set the value of property {@code principal}.
	 *
	 * @param principal the value of property principal
	 * @return this {@code Notification}
	 */
	public Notification withPrincipal(PrincipalDetails principal) {
		this.principal = principal;
		return this;
	}


	/**
	 * Set the value of property {@code meta}.
	 *
	 * @param meta the value of property meta
	 * @return this {@code Notification}
	 */
	public Notification withMeta(Meta meta) {
		this.meta = meta;
		return this;
	}


	/**
	 * Set the value of property {@code targets}.
	 *
	 * @param targets the value of property targets
	 * @return this {@code Notification}
	 */
	public Notification withTargets(List<Target> targets) {
		this.targets = targets;
		return this;
	}


	/**
	 * Set the value of property {@code uri}.
	 *
	 * @param uri the value of property uri
	 * @return this {@code Notification}
	 */
	public Notification withUri(String uri) {
		this.uri = uri;
		return this;
	}


	/**
	 * Set the value of property {@code timestamp}.
	 *
	 * @param timestamp the value of property timestamp
	 * @return this {@code Notification}
	 */
	public Notification withTimestamp(Long timestamp) {
		this.timestamp = timestamp;
		return this;
	}


	/**
	 * Set the value of property {@code date}.
	 *
	 * @param date the value of property date
	 * @return this {@code Notification}
	 */
	public Notification withDate(Date date) {
		this.date = date;
		return this;
	}


	/**
	 * Set the value of property {@code formattedDate}.
	 *
	 * @param formattedDate the value of property formattedDate
	 * @return this {@code Notification}
	 */
	public Notification withFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
		return this;
	}


	/**
	 * Set the value of property {@code subject}.
	 *
	 * @param subject the value of property subject
	 * @return this {@code Notification}
	 */
	public Notification withSubject(String subject) {
		this.subject = subject;
		return this;
	}


	/**
	 * Set the value of property {@code html}.
	 *
	 * @param html the value of property html
	 * @return this {@code Notification}
	 */
	public Notification withHtml(Boolean html) {
		this.html = html;
		return this;
	}


	/**
	 * Set the value of property {@code content}.
	 *
	 * @param content the value of property content
	 * @return this {@code Notification}
	 */
	public Notification withContent(String content) {
		this.content = content;
		return this;
	}


	/**
	 * Set the value of property {@code sms}.
	 *
	 * @param sms the value of property sms
	 * @return this {@code Notification}
	 */
	public Notification withSms(String sms) {
		this.sms = sms;
		return this;
	}


	/**
	 * Set the value of property {@code env}.
	 *
	 * @param env the value of property env
	 * @return this {@code Notification}
	 */
	public Notification withEnv(Map<String, Object> env) {
		this.env = env;
		return this;
	}

	//
	// Static util
	//

	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
			.append("app", app)
			.append("source", source)
			.append("param", param)
			.append("action", action)
			.append("principal", principal)
			.append("meta", meta)
			.append("formattedDate", formattedDate)
			.append("targets", targets)
			.append("uri", uri)
			.append("subject", subject)
			.append("html", html)
			.append("content", content)
			.append("sms", sms)
			;
	}
	
	public static Notification find(Notification notification, List<Notification> notifications) {
		if (notification!=null && notifications!=null) {
			for (Notification notification2: notifications) {
				if (notification.getId()!=null && notification.getId().equals(notification2.getId())) {
					return notification2;
				}				
			}
		}
		return null;
	}
	
	
}
