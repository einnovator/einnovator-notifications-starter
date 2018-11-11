package org.einnovator.notifications.client.model;

import java.util.Map;

public class TemplateBuilder {

	private String name;
	
	private String uri;
	
	private String subject;

	private String content;

	private Boolean html;
	
	private Map<String, Object> env;
	
	private Boolean enabled;

	public TemplateBuilder() {	
	}

	public TemplateBuilder name(String name) {
		this.name = name;
		return this;		
	}

	public TemplateBuilder uri(String uri) {
		this.uri = uri;
		return this;		
	}

	public TemplateBuilder env(Map<String, Object> env) {
		this.env = env;
		return this;
	}

	public TemplateBuilder subject(String subject) {
		this.subject = subject;
		return this;
	}

	public TemplateBuilder content(String content) {
		this.content = content;
		return this;		
	}

	public TemplateBuilder html(Boolean html) {
		this.html = html;
		return this;
	}

	
	public TemplateBuilder enabled(Boolean enabled) {
		this.enabled = enabled;
		return this;
	}

	public Template build() {
		Template template = new Template();
		template.setName(name);
		template.setUri(uri);
		template.setSubject(subject);
		template.setContent(content);
		template.setHtml(html);
		template.setEnv(env);
		template.setEnabled(enabled);
		return template;
	}
	
	public Template build(String basePath) {
		Template template = build();
		template.loadContent(basePath, false);
		return template;
	}
	
	
}
