package org.einnovator.notifications.client.model;

import java.util.Date;

import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * {@code Event} options metadata.
 *
 * @see Event
 * @author support@einnovator.org
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Meta extends ObjectBase {


	protected Integer priority;
	
	protected Date expires;

	//
	// Constructors
	//

	/**
	 * Create instance of {@code Meta}.
	 *
	 */
	public Meta() {
	}
	
	/**
	 * Create instance of {@code Meta}.
	 *
	 * @param priority the priority
	 * @param expires the expiration date
	 */
	public Meta(Integer priority, Date expires) {
		super();
		this.priority = priority;
		this.expires = expires;
	}
	
	//
	// Getter/Setters
	//

	/**
	 * Set the value of property {@code priority}.
	 *
	 * @param priority the value of property priority
	 * @return this {@code Meta}
	 */
	public Meta withPriority(Integer priority) {
		this.priority = priority;
		return this;
	}

	/**
	 * Get the value of property {@code priority}.
	 *
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * Set the value of property {@code priority}.
	 *
	 * @param priority the value of property priority
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * Get the value of property {@code expires}.
	 *
	 * @return the expires
	 */
	public Date getExpires() {
		return expires;
	}

	/**
	 * Set the value of property {@code expires}.
	 *
	 * @param expires the value of property expires
	 */
	public void setExpires(Date expires) {
		this.expires = expires;
	}

	//
	// With
	//


	/**
	 * Set the value of property {@code expires}.
	 *
	 * @param expires the value of property expires
	 * @return this {@code Meta}
	 */
	public Meta withExpires(Date expires) {
		this.expires = expires;
		return this;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
				.append("priority", priority)
				.append("expires", expires)
				;
	}
	
	
}
