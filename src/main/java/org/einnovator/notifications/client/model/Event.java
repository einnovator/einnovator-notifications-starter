package org.einnovator.notifications.client.model;

import java.util.List;
import java.util.Map;

import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Event extends ObjectBase {

	private EventType type;

	private String app;

	private Preference preference;

	private Source source;
	
	private Source source2;

	private Action action;
	
	private PrincipalX principal;

	private Meta meta;
	
	private List<Target> targets;
	
	private Map<String, Object> env;
	
	private String subject;

	private Boolean html;
	
	private String content;

	private String sms;

	public Event() {
	}
	
	public Event(Preference preference, PrincipalX principal) {
		this.type = EventType.PREFERENCE;
		this.preference = preference;
		this.principal = principal;
	}

	public Event(Source source,  Action action, PrincipalX principal, List<Target> targets) {
		this.type = EventType.NOTIFICATION;
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
	
	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
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
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
			.append("type", type)
			.append("app", app)
			.append("preference", preference)
			.append("source", source)
			.append("action", action)
			.append("principal", principal)
			.append("meta", meta)
			.append("targets", targets)
			.append("env", env)
			.append("subject", subject)
			.append("html", html)
			.append("content", content)
			.append("sms", sms)
			;
	}

	
	
}
