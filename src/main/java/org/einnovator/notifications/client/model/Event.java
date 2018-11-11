package org.einnovator.notifications.client.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {

	@Deprecated
	private String app;

	private Application application;

	private Source source;
	
	private Source source2;

	private Action action;
	
	private PrincipalX principal;

	private Meta meta;
	
	private Map<String, Object> details;
	
	private List<Target> targets;
	
	private Map<String, Object> env;
	
	private String subject;

	private Boolean html;
	
	private String content;

	private String sms;

	public Event() {
	}
	
	public Event(Source source,  Action action, PrincipalX principal, List<Target> targets) {
		this.source = source;
		this.action = action;
		this.principal = principal;
		this.targets = targets;
	}
	
	public Event(Source source,  Action action, PrincipalX principal, Meta meta, Map<String, Object> details, List<Target> targets) {
		this(source, action, principal, targets);
	}
	
	public Event(Source source,  Action action, PrincipalX principal, Meta meta, Map<String, Object> details) {
		this(source, action, principal, meta, details, null);
	}
	
	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
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
	
	public Map<String, Object> getEnv() {
		return env;
	}

	public void setEnv(Map<String, Object> env) {
		this.env = env;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Boolean getHtml() {
		return html;
	}

	public void setHtml(Boolean html) {
		this.html = html;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	@Override
	public String toString() {
		return "Event ["
				+ (app != null ? "app=" + app + ", " : "")				
				+ (application != null ? "application=" + application + ", " : "")
				+ (source != null ? "source=" + source + ", " : "")
				+ (source2 != null ? "source2=" + source2 + ", " : "")
				+ (action != null ? "action=" + action + ", " : "")
				+ (principal != null ? "principal=" + principal + ", " : "")
				+ (targets != null ? "targets=" + targets + ", " : "")
				+ (meta != null ? "meta=" + meta : "")
				+ (details != null ? "details=" + details : "")
				+ (env != null ? "env=" + env + ", ": "")		
				+ (subject != null ? "subject=" + subject  + ", " : "")
				+ (html != null ? "html=" + html + ", " : "")
				+ (content != null ? "content=" + content  + ", " : "")
				+ (sms != null ? "sms=" + sms  : "")
				+ "]";
	}
	
	
	
}
