package org.einnovator.notifications.client.model;

import java.util.Map;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.einnovator.util.TextTemplates;
import org.einnovator.util.ResourceUtils;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Template {

	private String name;
	
	private String uri;
	
	private String subject;

	private String content;

	private Boolean html;
	
	private Map<String, Object> env;
	
	private Boolean enabled;
	
	public Template() {	
	}
	
	public Template(String name) {	
		this.name = name;
	}

	public Template(String name, String basePath) {	
		this.name = name;
		loadContent(basePath, false);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
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
	
	public Template loadContent(String basePath, boolean reload) {
		if (content!=null && !reload) {
			return this;
		}
		if (StringUtils.hasText(name)) {
			String filename = name;
			if (basePath!=null) {
				filename = basePath + "/" + name;
			}
			content = TextTemplates.readResource(filename);
		} else if (StringUtils.hasText(uri)) {
			content = ResourceUtils.readResource(uri);
		}
		return this;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [" 
				+ (name != null ? "name=" + name + ", " : "") 
				+ (uri != null ? "uri=" + uri + ", " : "")
				+ (subject != null ? "subject=" + subject + ", " : "")
				+ (content != null ? "content=" + content + ", " : "") 
				+ (html != null ? "html=" + html + ", " : "")
				+ (enabled != null ? "enabled=" + enabled : "") 
				+ (env != null ? "env=" + env : "") 
				+ "]";
	}
	
	
}
