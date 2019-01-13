package org.einnovator.notifications.client.model;

import java.util.Date;

import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ErrorReport extends ObjectBase {
	
	private String id;
	
	private String app;
	
	private String exception;
	
	private String stacktrace;
	
	private String page;

	private String url;
	
	private String query;
	
	private Boolean contact;
	
	private Date date;

	public ErrorReport() {
		date = new Date();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getStacktrace() {
		return stacktrace;
	}

	public void setStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	public Boolean getContact() {
		return contact;
	}

	public void setContact(Boolean contact) {
		this.contact = contact;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ToStringCreator toString0(ToStringCreator creator) {
		return creator
				.append("id", id)
				.append("app", app)
				.append("exception", exception)
				.append("stacktrace", stacktrace)
				.append("page", page)
				.append("url", url)
				.append("query", query)
				.append("contact", contact)
				.append("date", date)
				;
	}
	
}