package org.einnovator.notifications.client.model;

import java.util.Map;

import org.einnovator.notifications.client.config.TemplatesConfiguration;
import org.einnovator.util.PathUtil;
import org.einnovator.util.ResourceUtils;
import org.einnovator.util.model.EntityBase;
import org.einnovator.util.model.ToStringCreator;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Template extends EntityBase {
	
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
	
	public Template() {	
	}
	
	public Template(String name) {	
		this.name = name;
	}

	public Template(String name, String basePath) {	
		this.name = name;
		loadContent(basePath, false);
	}

	public Template(Object obj) {	
		super(obj);
	}

	
	/**
	 * Get the value of property {@code medium}.
	 *
	 * @return the medium
	 */
	public Medium getMedium() {
		return medium;
	}

	/**
	 * Set the value of property {@code medium}.
	 *
	 * @param medium the medium to set
	 */
	public void setMedium(Medium medium) {
		this.medium = medium;
	}

	/**
	 * Get the value of property {@code contentType}.
	 *
	 * @return the contentType
	 */
	public ContentType getContentType() {
		return contentType;
	}

	/**
	 * Set the value of property {@code contentType}.
	 *
	 * @param contentType the contentType to set
	 */
	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
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
	 * Get the value of property {@code category}.
	 *
	 * @return the category
	 */
	public TemplateCategory getCategory() {
		return category;
	}

	/**
	 * Set the value of property {@code category}.
	 *
	 * @param category the category to set
	 */
	public void setCategory(TemplateCategory category) {
		this.category = category;
	}

	/**
	 * Get the value of property {@code name}.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the value of property {@code name}.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the value of property {@code fragment}.
	 *
	 * @return the fragment
	 */
	public Boolean getFragment() {
		return fragment;
	}

	/**
	 * Set the value of property {@code fragment}.
	 *
	 * @param fragment the fragment to set
	 */
	public void setFragment(Boolean fragment) {
		this.fragment = fragment;
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
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
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
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * @param env the env to set
	 */
	public void setEnv(Map<String, Object> env) {
		this.env = env;
	}

	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
			.append("name", name)
			.append("medium", medium)
			.append("app", app)
			.append("category", category)
			.append("uri", uri)
			.append("fragment", fragment)
			.append("subject", subject)
			.append("#content", content!=null ? content.length() : null)
			.append("env", env)
			;
	}

		
	public String getRequiredContent(TemplatesConfiguration config) {
		if (content!=null) {
			return content;
		}
		loadContent(false, config);
		return content;
	}
	
	public boolean isAbsoluteUri() {
		if (StringUtils.hasText(uri)) {
			String uri = this.uri.trim();
			return uri.contains("://") || uri.startsWith("/");
		}
		return false;
	}
	
	public Template loadContent(String baseUri, boolean reload) {
		if (content!=null && !reload) {
			return this;
		}
		if (StringUtils.hasText(uri)) {
			String uri = this.uri;
			if (!isAbsoluteUri()) {
				if (baseUri!=null) {
					uri = PathUtil.concat(baseUri, uri);
				}
			}
			if (uri.contains("://")) {
				content = ResourceUtils.readUrlResource(uri);				
			} else {				
				content = ResourceUtils.readResource(uri);
			}
		}
		return this;
	}

	public Template loadContent(boolean reload, TemplatesConfiguration config) {
		return loadContent(getBaseUri(config), reload);
	}

	@JsonIgnore
	public String getBaseUri(TemplatesConfiguration config) {
		if (!isAbsoluteUri()) {
			if (medium==Medium.EMAIL) {
				return config.getMail();
			} else if (getMedium()==Medium.APP) {
				return config.getApp();
			} else if (getMedium()==Medium.SMS) {
				return config.getSms();				
			} else {
				return config.getUri();
			}
		}
		return null;
	}
	
}
