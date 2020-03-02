package org.einnovator.notifications.client.model;

import java.util.Date;

import org.einnovator.notifications.client.NotificationsClient;
import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * A descriptor for a reported application error.
 *
 * @see ErrorReport
 * @see NotificationsClient#reportError
 * @author support@einnovator.org
 *
 */
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

	
	//
	// Constructors
	//
	
	/**
	 * Create instance of {@code ErrorReport}.
	 *
	 */
	public ErrorReport() {
	}

	//
	// Getters/Setters
	//

	/**
	 * Get the value of property {@code id}.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set the value of property {@code id}.
	 *
	 * @param id the value of property id
	 */
	public void setId(String id) {
		this.id = id;
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
	 * Get the value of property {@code exception}.
	 *
	 * @return the exception
	 */
	public String getException() {
		return exception;
	}

	/**
	 * Set the value of property {@code exception}.
	 *
	 * @param exception the value of property exception
	 */
	public void setException(String exception) {
		this.exception = exception;
	}

	/**
	 * Get the value of property {@code stacktrace}.
	 *
	 * @return the stacktrace
	 */
	public String getStacktrace() {
		return stacktrace;
	}

	/**
	 * Set the value of property {@code stacktrace}.
	 *
	 * @param stacktrace the value of property stacktrace
	 */
	public void setStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
	}

	/**
	 * Get the value of property {@code page}.
	 *
	 * @return the page
	 */
	public String getPage() {
		return page;
	}

	/**
	 * Set the value of property {@code page}.
	 *
	 * @param page the value of property page
	 */
	public void setPage(String page) {
		this.page = page;
	}

	/**
	 * Get the value of property {@code url}.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Set the value of property {@code url}.
	 *
	 * @param url the value of property url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Get the value of property {@code query}.
	 *
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * Set the value of property {@code query}.
	 *
	 * @param query the value of property query
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * Get the value of property {@code contact}.
	 *
	 * @return the contact
	 */
	public Boolean getContact() {
		return contact;
	}

	/**
	 * Set the value of property {@code contact}.
	 *
	 * @param contact the value of property contact
	 */
	public void setContact(Boolean contact) {
		this.contact = contact;
	}

	/**
	 * Get the value of property {@code date}.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Set the value of property {@code date}.
	 *
	 * @param date the value of property date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	
	//
	// With
	//

	/**
	 * Set the value of property {@code id}.
	 *
	 * @param id the value of property id
	 * @return this {@code ErrorReport}
	 */
	public ErrorReport withId(String id) {
		this.id = id;
		return this;
	}

	/**
	 * Set the value of property {@code app}.
	 *
	 * @param app the value of property app
	 * @return this {@code ErrorReport}
	 */
	public ErrorReport withApp(String app) {
		this.app = app;
		return this;
	}

	/**
	 * Set the value of property {@code exception}.
	 *
	 * @param exception the value of property exception
	 * @return this {@code ErrorReport}
	 */
	public ErrorReport withException(String exception) {
		this.exception = exception;
		return this;
	}

	/**
	 * Set the value of property {@code stacktrace}.
	 *
	 * @param stacktrace the value of property stacktrace
	 * @return this {@code ErrorReport}
	 */
	public ErrorReport withStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
		return this;
	}

	/**
	 * Set the value of property {@code page}.
	 *
	 * @param page the value of property page
	 * @return this {@code ErrorReport}
	 */
	public ErrorReport withPage(String page) {
		this.page = page;
		return this;
	}

	/**
	 * Set the value of property {@code url}.
	 *
	 * @param url the value of property url
	 * @return this {@code ErrorReport}
	 */
	public ErrorReport withUrl(String url) {
		this.url = url;
		return this;
	}

	/**
	 * Set the value of property {@code query}.
	 *
	 * @param query the value of property query
	 * @return this {@code ErrorReport}
	 */
	public ErrorReport withQuery(String query) {
		this.query = query;
		return this;
	}

	/**
	 * Set the value of property {@code contact}.
	 *
	 * @param contact the value of property contact
	 * @return this {@code ErrorReport}
	 */
	public ErrorReport withContact(Boolean contact) {
		this.contact = contact;
		return this;
	}

	/**
	 * Set the value of property {@code date}.
	 *
	 * @param date the value of property date
	 * @return this {@code ErrorReport}
	 */
	public ErrorReport withDate(Date date) {
		this.date = date;
		return this;
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