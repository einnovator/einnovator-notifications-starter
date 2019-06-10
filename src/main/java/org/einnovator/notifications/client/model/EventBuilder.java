package org.einnovator.notifications.client.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EventBuilder {
	
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

	public EventBuilder() {
	}
	
	public EventBuilder source(Source source) {
		this.source = source;
		return this;
	}

	public EventBuilder source2(Source source2) {
		this.source2 = source2;
		return this;
	}

	public EventBuilder action(Action action) {
		this.action = action;
		return this;
	}
	
	public EventBuilder principal(PrincipalX principal) {
		this.principal = principal;
		return this;	
	}

	public EventBuilder targets(List<Target> targets) {
		this.targets = targets;
		return this;		
	}
	
	public EventBuilder targets(Target... targets) {
		if (this.targets==null) {
			this.targets = new ArrayList<>();
		}
		this.targets.addAll(Arrays.asList(targets));
		return this;		
	}

	public EventBuilder meta(Meta meta) {
		this.meta = meta;
		return this;
	}

	public EventBuilder env(Map<String, Object> env) {
		this.env = env;
		return this;
	}


	public EventBuilder subject(String subject) {
		this.subject = subject;
		return this;
	}

	public EventBuilder content(String content) {
		this.content = content;
		return this;
	}

	public EventBuilder sms(String sms) {
		this.sms = sms;
		return this;
	}

	public EventBuilder html(Boolean html) {
		this.html = html;
		return this;
	}

	public Event build() {
		Event event = new Event();
		event.setSource(source);
		event.setSource2(source2);
		event.setAction(action);
		event.setPrincipal(principal);
		event.setMeta(meta);
		event.setTargets(targets);
		event.setEnv(env);
		event.setSubject(subject);
		event.setHtml(html);
		event.setContent(content);
		event.setSms(sms);
		return event;
	}
}
