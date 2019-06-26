package org.einnovator.notifications.client.model;

import java.util.Map;

public class TemplateBuilder {

	private Medium medium;

	private ContentType contentType;

	private String app;

	private TemplateCategory category;

	private String name;

	private Boolean fragment;
	
	private String uri;
	
	private String subject;

	private String content;

	private Map<String, Object> env;
	
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

	public TemplateBuilder app(String app) {
		this.app = app;
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

	public TemplateBuilder medium(Medium medium) {
		this.medium = medium;
		return this;
	}

	public TemplateBuilder contentType(ContentType contentType) {
		this.contentType = contentType;
		return this;
	}

	public TemplateBuilder fragment(Boolean fragment) {
		this.fragment = fragment;
		return this;
	}

	public Template build() {
		Template template = new Template();
		template.setMedium(medium);
		template.setContentType(contentType);
		template.setApp(app);
		template.setCategory(category);
		template.setName(name);
		template.setFragment(fragment);
		template.setUri(uri);
		template.setSubject(subject);
		template.setContent(content);
		template.setEnv(env);
		return template;
	}
	
	public Template build(String basePath) {
		Template template = build();
		template.loadContent(basePath, false);
		return template;
	}
	
	
}
