package org.einnovator.notifications.client.model;

import java.util.Map;

import org.einnovator.notifications.client.config.TemplatesConfiguration;
import org.einnovator.util.PathUtil;
import org.einnovator.util.ResourceUtils;
import org.einnovator.util.UriUtils;
import org.einnovator.util.model.EntityBase;
import org.einnovator.util.model.ToStringCreator;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * A {@code Notification} template.
 *
 * @see Notification
 * @author support@einnovator.org
 *
 */
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
	
	
	//
	// Constructors
	//
	
	
	/**
	 * Create instance of {@code Template}.
	 *
	 */
	public Template() {	
	}
	
	/**
	 * Create instance of {@code Template}.
	 *
	 * @param name the name
	 */
	public Template(String name) {	
		this.name = name;
	}

	/**
	 * Create instance of {@code Template}.
	 *
	 * @param name the name
	 * @param basePath base path to lookup template resource
	 */
	public Template(String name, String basePath) {	
		this.name = name;
		loadContent(basePath, false);
	}

	/**
	 * Create instance of {@code Template}.
	 *
	 * @param obj a prototype
	 */
	public Template(Object obj) {	
		super(obj);
	}

	//
	// Getters/Setters
	//
	
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
	 * @param medium the value of property medium
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
	 * @param contentType the value of property contentType
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
	 * @param app the value of property app
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
	 * @param category the value of property category
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
	 * @param name the value of property name
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
	 * @param fragment the value of property fragment
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
	 * @param uri the value of property uri
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
	 * @param subject the value of property subject
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
	 * @param content the value of property content
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
	 * @param env the value of property env
	 */
	public void setEnv(Map<String, Object> env) {
		this.env = env;
	}

	//
	// With
	//
	
	/**
	 * Set the value of property {@code medium}.
	 *
	 * @param medium the value of property medium
	 * @return this {@code Template}
	 */
	public Template withMedium(Medium medium) {
		this.medium = medium;
		return this;
	}

	/**
	 * Set the value of property {@code contentType}.
	 *
	 * @param contentType the value of property contentType
	 * @return this {@code Template}
	 */
	public Template withContentType(ContentType contentType) {
		this.contentType = contentType;
		return this;
	}

	/**
	 * Set the value of property {@code app}.
	 *
	 * @param app the value of property app
	 * @return this {@code Template}
	 */
	public Template withApp(String app) {
		this.app = app;
		return this;
	}

	/**
	 * Set the value of property {@code category}.
	 *
	 * @param category the value of property category
	 * @return this {@code Template}
	 */
	public Template withCategory(TemplateCategory category) {
		this.category = category;
		return this;
	}

	/**
	 * Set the value of property {@code name}.
	 *
	 * @param name the value of property name
	 * @return this {@code Template}
	 */
	public Template withName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Set the value of property {@code fragment}.
	 *
	 * @param fragment the value of property fragment
	 * @return this {@code Template}
	 */
	public Template withFragment(Boolean fragment) {
		this.fragment = fragment;
		return this;
	}

	/**
	 * Set the value of property {@code uri}.
	 *
	 * @param uri the value of property uri
	 * @return this {@code Template}
	 */
	public Template withUri(String uri) {
		this.uri = uri;
		return this;
	}

	/**
	 * Set the value of property {@code subject}.
	 *
	 * @param subject the value of property subject
	 * @return this {@code Template}
	 */
	public Template withSubject(String subject) {
		this.subject = subject;
		return this;
	}

	/**
	 * Set the value of property {@code content}.
	 *
	 * @param content the value of property content
	 * @return this {@code Template}
	 */
	public Template withContent(String content) {
		this.content = content;
		return this;
	}

	/**
	 * Set the value of property {@code env}.
	 *
	 * @param env the value of property env
	 * @return this {@code Template}
	 */
	public Template withEnv(Map<String, Object> env) {
		this.env = env;
		return this;
	}

	//
	// Util
	//
	
	@JsonIgnore
	public String getRequiredContent(TemplatesConfiguration config) {
		if (content!=null) {
			return content;
		}
		loadContent(false, config);
		return content;
	}
	
	@JsonIgnore
	public boolean isAbsoluteUri() {
		return UriUtils.isAbsolute(uri);
	}
	
	public Template loadContent(String baseUri, boolean reload) {
		if (content!=null && !reload) {
			return this;
		}
		if (StringUtils.hasText(uri)) {
			if (!isAbsoluteUri()) {
				if (baseUri!=null && !uri.startsWith(baseUri)) {
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
		if (medium==Medium.EMAIL) {
			return PathUtil.concat(config.getUri(), config.getMail());
		} else if (medium==Medium.APP) {
			return PathUtil.concat(config.getUri(), config.getApp());
		} else if (medium==Medium.SMS) {
			return PathUtil.concat(config.getUri(), config.getSms());				
		}
		return config.getUri();
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
}
