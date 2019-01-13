package org.einnovator.notifications.client.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Meta {

	public static final Integer PRIORITY_NORMAL = 0;

	public static final Integer PRIORITY_URGENT = -10;

	public static final Integer PRIORITY_IMPORTANT = -100;

	public static final Integer PRIORITY_UNIMPORTANT = 10;
	
	public static final Integer PRIORITY_JUNK = 100;

	private Integer priority;

	private Date date;
	
	private Date expires;

	public Meta() {
	}
	
	public Meta(Integer priority, Date date, Date expires) {
		super();
		this.priority = priority;
		this.date = date;
		this.expires = expires;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getExpires() {
		return expires;
	}

	public void setExpires(Date expires) {
		this.expires = expires;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((expires == null) ? 0 : expires.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meta other = (Meta) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (expires == null) {
			if (other.expires != null)
				return false;
		} else if (!expires.equals(other.expires))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Meta [" + (priority != null ? "priority=" + priority + ", " : "")
				+ (date != null ? "date=" + date + ", " : "") + (expires != null ? "expires=" + expires : "") + "]";
	}
	
	
}
