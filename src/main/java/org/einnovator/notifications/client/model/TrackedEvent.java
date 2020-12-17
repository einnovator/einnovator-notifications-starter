package org.einnovator.notifications.client.model;

import java.util.Date;
import java.util.Map;

import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class TrackedEvent extends ObjectBase {

	private String id;

	private String uuid;
	
	private EventType type;

	protected String app;

	protected String url;

	protected String xid;

	protected String xname;

	protected String xtype;
	
	protected ObjectInfo source;

	protected ObjectInfo param;

	private String action;


	private String username;

	private Object user;
	
	protected Map<String, Object> data;

	private Boolean admin;
	
	private Long timestamp;

	private String dateFormatted;

	protected Date date;
	
	public TrackedEvent() {
	}

	/**
	 * Get the value of property {@code id}.
	 *
	 * @return the value of {@code id}
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set the value of property {@code id}.
	 *
	 * @param id the value of {@code id}
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Get the value of property {@code uuid}.
	 *
	 * @return the value of {@code uuid}
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * Set the value of property {@code uuid}.
	 *
	 * @param uuid the value of {@code uuid}
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * Get the value of property {@code type}.
	 *
	 * @return the value of {@code type}
	 */
	public EventType getType() {
		return type;
	}

	/**
	 * Set the value of property {@code type}.
	 *
	 * @param type the value of {@code type}
	 */
	public void setType(EventType type) {
		this.type = type;
	}

	/**
	 * Get the value of property {@code app}.
	 *
	 * @return the value of {@code app}
	 */
	public String getApp() {
		return app;
	}

	/**
	 * Set the value of property {@code app}.
	 *
	 * @param app the value of {@code app}
	 */
	public void setApp(String app) {
		this.app = app;
	}

	/**
	 * Get the value of property {@code url}.
	 *
	 * @return the value of {@code url}
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Set the value of property {@code url}.
	 *
	 * @param url the value of {@code url}
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Get the value of property {@code xid}.
	 *
	 * @return the value of {@code xid}
	 */
	public String getXid() {
		return xid;
	}

	/**
	 * Set the value of property {@code xid}.
	 *
	 * @param xid the value of {@code xid}
	 */
	public void setXid(String xid) {
		this.xid = xid;
	}

	/**
	 * Get the value of property {@code xtype}.
	 *
	 * @return the value of {@code xtype}
	 */
	public String getXtype() {
		return xtype;
	}

	/**
	 * Set the value of property {@code xtype}.
	 *
	 * @param xtype the value of {@code xtype}
	 */
	public void setXtype(String xtype) {
		this.xtype = xtype;
	}

	/**
	 * Get the value of property {@code xname}.
	 *
	 * @return the value of {@code xname}
	 */
	public String getXname() {
		return xname;
	}

	/**
	 * Set the value of property {@code xname}.
	 *
	 * @param xname the value of {@code xname}
	 */
	public void setXname(String xname) {
		this.xname = xname;
	}

	/**
	 * Get the value of property {@code source}.
	 *
	 * @return the value of {@code source}
	 */
	public ObjectInfo getSource() {
		return source;
	}

	/**
	 * Set the value of property {@code source}.
	 *
	 * @param source the value of {@code source}
	 */
	public void setSource(ObjectInfo source) {
		this.source = source;
	}

	/**
	 * Get the value of property {@code param}.
	 *
	 * @return the value of {@code param}
	 */
	public ObjectInfo getParam() {
		return param;
	}

	/**
	 * Set the value of property {@code param}.
	 *
	 * @param param the value of {@code param}
	 */
	public void setParam(ObjectInfo param) {
		this.param = param;
	}

	/**
	 * Get the value of property {@code action}.
	 *
	 * @return the value of {@code action}
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Set the value of property {@code action}.
	 *
	 * @param action the value of {@code action}
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Get the value of property {@code username}.
	 *
	 * @return the value of {@code username}
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Set the value of property {@code username}.
	 *
	 * @param username the value of {@code username}
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Get the value of property {@code user}.
	 *
	 * @return the value of {@code user}
	 */
	public Object getUser() {
		return user;
	}

	/**
	 * Set the value of property {@code user}.
	 *
	 * @param user the value of {@code user}
	 */
	public void setUser(Object user) {
		this.user = user;
	}

	/**
	 * Get the value of property {@code data}.
	 *
	 * @return the value of {@code data}
	 */
	public Map<String, Object> getData() {
		return data;
	}

	/**
	 * Set the value of property {@code data}.
	 *
	 * @param data the value of {@code data}
	 */
	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	/**
	 * Get the value of property {@code admin}.
	 *
	 * @return the value of {@code admin}
	 */
	public Boolean getAdmin() {
		return admin;
	}

	/**
	 * Set the value of property {@code admin}.
	 *
	 * @param admin the value of {@code admin}
	 */
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	/**
	 * Get the value of property {@code timestamp}.
	 *
	 * @return the value of {@code timestamp}
	 */
	public Long getTimestamp() {
		return timestamp;
	}

	/**
	 * Set the value of property {@code timestamp}.
	 *
	 * @param timestamp the value of {@code timestamp}
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Get the value of property {@code dateFormatted}.
	 *
	 * @return the value of {@code dateFormatted}
	 */
	public String getDateFormatted() {
		return dateFormatted;
	}

	/**
	 * Set the value of property {@code dateFormatted}.
	 *
	 * @param dateFormatted the value of {@code dateFormatted}
	 */
	public void setDateFormatted(String dateFormatted) {
		this.dateFormatted = dateFormatted;
	}

	/**
	 * Get the value of property {@code date}.
	 *
	 * @return the value of {@code date}
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Set the value of property {@code date}.
	 *
	 * @param date the value of {@code date}
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
			.append("type", type)
			.append("id", id)
			.append("uuid", uuid)
			.append("username", username)
			.append("admin", admin)
			.append("date", date)
			.append("app", app)
			.append("url", url)
			.append("xid", xid)
			.append("xtype", xtype)
			.append("xname", xname)
			.append("source", source)
			.append("param", param)
			.append("action", action)
			;
	}
	
}
