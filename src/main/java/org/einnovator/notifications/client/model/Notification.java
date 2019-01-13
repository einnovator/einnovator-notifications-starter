package org.einnovator.notifications.client.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Notification extends ObjectBase2 {

	private String app;
	
	private Preference preference;

	private Source source;
	
	private Source source2;

	private Action action;
	
	private PrincipalX principal;

	private Meta meta;
	
	private Map<String, Object> details;
	
	private List<Target> targets;
	
	private String uri;
			
	private Date date;
	
	private String formattedDate;

	private String subject;

	private Boolean html;
	
	private String content;

	private String sms;

	private Map<String, Object> env;
	
	public Notification() {
	}
	
	public Notification(Event event) {
		this.app = event.getApp();
		this.preference = event.getPreference();
		this.source = event.getSource();
		this.source2 = event.getSource2();
		this.action = event.getAction();
		this.principal = event.getPrincipal();
		this.targets = event.getTargets();
		this.date = new Date();
		this.details = event.getDetails();
		this.meta = event.getMeta();
		this.env = event.getEnv();
	}
	
	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public Preference getPreference() {
		return preference;
	}

	public void setPreference(Preference preference) {
		this.preference = preference;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}
	
	public Source getSource2() {
		return source2;
	}

	public void setSource2(Source source2) {
		this.source2 = source2;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public PrincipalX getPrincipal() {
		return principal;
	}

	public void setPrincipal(PrincipalX principal) {
		this.principal = principal;
	}

	public List<Target> getTargets() {
		return targets;
	}

	public void setTargets(List<Target> targets) {
		this.targets = targets;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public Map<String, Object> getDetails() {
		return details;
	}

	public void setDetails(Map<String, Object> details) {
		this.details = details;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getHtml() {
		return html;
	}

	public void setHtml(Boolean html) {
		this.html = html;
	}

	public Date getDate() {
		return date;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFormattedDate() {
		return formattedDate;
	}

	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}

	public Map<String, Object> getEnv() {
		return env;
	}

	public void setEnv(Map<String, Object> env) {
		this.env = env;
	}

	@Override
	public String toString() {
		return "Notification [" 
				+ (app != null ? "app=" + app + ", " : "")				
				+ (getType() != null ? "type=" + getType() + ", " : "")
				+ (getId() != null ? "id=" + getId() + ", " : "")
				+ (getName() != null ? "name=" + getName()  + ", " : "")
				+ (source != null ? "source=" + source + ", " : "")
				+ (source2 != null ? "source2=" + source2 + ", " : "")
				+ (action != null ? "action=" + action + ", " : "")
				+ (principal != null ? "principal=" + principal + ", " : "")
				+ (meta != null ? "meta=" + meta  + ", " : "")
				+ (details != null ? "details=" + details : "")
				+ (date != null ? "date=" + date  + ", " : "")				
				+ (formattedDate != null ? "formattedDate=" + formattedDate  + ", " : "")				
				+ (targets != null ? "targets=" + targets + ", " : "")
				+ (uri != null ? "uri=" + uri  + ", " : "")
				+ (subject != null ? "subject=" + subject  + ", " : "")
				+ (html != null ? "html=" + html  + ", ": "")
				+ (content != null ? "content=" + content  + ", " : "")
				+ (sms != null ? "sms=" + sms  : "")
				+ "]";
	}
	
	
}
