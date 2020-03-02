package org.einnovator.notifications.client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * A generic {@code Event}.
 *
 * @see Source
 * @see Action
 * @see Preference
 * @author support@einnovator.org
 *
*/
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Event extends ObjectBase {

	private EventType type;

	private String app;

	private Preference preference;

	private ErrorReport error;

	private Source source;
	
	private Source param;

	private Action action;
	
	private PrincipalDetails principal;

	private Meta meta;
	
	private List<Target> targets;
	
	private Map<String, Object> env;
	
	private String subject;

	private Boolean html;
	
	private String content;

	private String sms;

	
	//
	// Constructors
	//
	
	/**
	 * Create instance of {@code Event}.
	 *
	 */
	public Event() {
	}

	
	/**
	 * Create instance of {@code Event}.
	 *
	 * @param obj a prototype
	 */
	public Event(Object obj) {
		super(obj);
	}

	/**
	 * Create {@code Event} with a {@code Source} and {@code Action}.
	 *
	 * @param source the {@code Source}
	 * @param action the {@code Action}
	 * @param targets the {@code Target}s for this event
	 * @param principal the {@code PrincipalDetails}
	 */
	public Event(Source source,  Action action, PrincipalDetails principal, List<Target> targets) {
		this.type = EventType.NOTIFICATION;
		this.source = source;
		this.action = action;
		this.principal = principal;
		this.targets = targets;
	}
	
	
	/**
	 * Create {@code Event} for a {@code Preference} update.
	 *
	 * @param preference the {@code Preference}
	 * @param principal the {@code PrincipalDetails}
	 */
	public Event(Preference preference, PrincipalDetails principal) {
		this.type = EventType.PREFERENCE;
		this.preference = preference;
		this.principal = principal;
	}

	/**
	 * Create {@code Event} for an {@code ErrorReport}.
	 *
	 * @param error the {@code ErrorReport}
	 * @param principal the {@code PrincipalDetails}
	 */
	public Event(ErrorReport error, PrincipalDetails principal) {
		this.type = EventType.PREFERENCE;
		this.error = error;
		this.principal = principal;
	}
	
	//
	// Getters/Setters
	//

	/**
	 * Get the value of property {@code type}.
	 *
	 * @return the type
	 */
	public EventType getType() {
		return type;
	}

	/**
	 * Set the value of property {@code type}.
	 *
	 * @param type the value of property type
	 */
	public void setType(EventType type) {
		this.type = type;
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
	 * Get the value of property {@code error}.
	 *
	 * @return the error
	 */
	public ErrorReport getError() {
		return error;
	}

	/**
	 * Set the value of property {@code error}.
	 *
	 * @param error the value of property error
	 */
	public void setError(ErrorReport error) {
		this.error = error;
	}
	
	//
	// With
	//

	/**
	 * Set the value of property {@code type}.
	 *
	 * @param type the value of property type
	 * @return this {@code Event}
	 */
	public Event withType(EventType type) {
		this.type = type;
		return this;
	}

	/**
	 * Set the value of property {@code app}.
	 *
	 * @param app the value of property app
	 * @return this {@code Event}
	 */
	public Event withApp(String app) {
		this.app = app;
		return this;
	}

	/**
	 * Set the value of property {@code preference}.
	 *
	 * @param preference the value of property preference
	 * @return this {@code Event}
	 */
	public Event withPreference(Preference preference) {
		this.preference = preference;
		return this;
	}

	/**
	 * Set the value of property {@code source}.
	 *
	 * @param source the value of property source
	 * @return this {@code Event}
	 */
	public Event withSource(Source source) {
		this.source = source;
		return this;
	}

	/**
	 * Set the value of property {@code param}.
	 *
	 * @param param the value of property param
	 * @return this {@code Event}
	 */
	public Event withParam(Source param) {
		this.param = param;
		return this;
	}

	/**
	 * Set the value of property {@code action}.
	 *
	 * @param action the value of property action
	 * @return this {@code Event}
	 */
	public Event withAction(Action action) {
		this.action = action;
		return this;
	}

	/**
	 * Set the value of property {@code principal}.
	 *
	 * @param principal the value of property principal
	 * @return this {@code Event}
	 */
	public Event withPrincipal(PrincipalDetails principal) {
		this.principal = principal;
		return this;
	}

	/**
	 * Set the value of property {@code meta}.
	 *
	 * @param meta the value of property meta
	 * @return this {@code Event}
	 */
	public Event withMeta(Meta meta) {
		this.meta = meta;
		return this;
	}

	/**
	 * Set the value of property {@code targets}.
	 *
	 * @param targets the value of property targets
	 * @return this {@code Event}
	 */
	public Event withTargets(List<Target> targets) {
		this.targets = targets;
		return this;
	}
	
	/**
	 * Set the value of property {@code targets}.
	 *
	 * @param targets the value of property targets
	 * @return this {@code Event}
	 */
	public Event withTargets(Target... targets) {
		if (this.targets==null) {
			this.targets = new ArrayList<Target>();
		}
		for (Target target: targets) {
			this.targets.add(target);			
		}
		return this;
	}

	/**
	 * Set the value of property {@code env}.
	 *
	 * @param env the value of property env
	 * @return this {@code Event}
	 */
	public Event withEnv(Map<String, Object> env) {
		this.env = env;
		return this;
	}

	/**
	 * Set the value of property {@code subject}.
	 *
	 * @param subject the value of property subject
	 * @return this {@code Event}
	 */
	public Event withSubject(String subject) {
		this.subject = subject;
		return this;
	}

	/**
	 * Set the value of property {@code html}.
	 *
	 * @param html the value of property html
	 * @return this {@code Event}
	 */
	public Event withHtml(Boolean html) {
		this.html = html;
		return this;
	}

	/**
	 * Set the value of property {@code content}.
	 *
	 * @param content the value of property content
	 * @return this {@code Event}
	 */
	public Event withContent(String content) {
		this.content = content;
		return this;
	}

	/**
	 * Set the value of property {@code sms}.
	 *
	 * @param sms the value of property sms
	 * @return this {@code Event}
	 */
	public Event withSms(String sms) {
		this.sms = sms;
		return this;
	}

	/**
	 * Set the value of property {@code error}.
	 *
	 * @param error the value of property error
	 * @return this {@code Event}
	 */
	public Event withError(ErrorReport error) {
		this.error = error;
		return this;
	}

	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
			.append("type", type)
			.append("app", app)
			.append("source", source)
			.append("param", param)
			.append("action", action)
			.append("principal", principal)
			.append("meta", meta)
			.append("targets", targets)
			.append("env", env)
			.append("subject", subject)
			.append("html", html)
			.append("content", content)
			.append("sms", sms)
			.append("preference", preference)
			.append("error", error)
			;
	}
	
}
