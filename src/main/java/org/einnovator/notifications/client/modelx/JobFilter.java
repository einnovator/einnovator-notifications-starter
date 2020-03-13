package org.einnovator.notifications.client.modelx;

import org.einnovator.notifications.client.model.JobStatus;
import org.einnovator.util.model.ToStringCreator;

/**
 * A filter for {@code Job}s.
 *
 * @see Jobs
 * @author support@einnovator.org
 *
 */
public class JobFilter extends JobOptions {
	
	private String q;
	
	private Boolean strict;

	private JobStatus status;

	//
	// Constructors
	//

	/**
	 * Create instance of {@code JobFilter}.
	 *
	 */
	public JobFilter() {
	}
	
	//
	// Getters/Setters
	//

	/**
	 * Get the value of property {@code q}.
	 *
	 * @return the q
	 */
	public String getQ() {
		return q;
	}

	/**
	 * Set the value of property {@code q}.
	 *
	 * @param q the value of property q
	 */
	public void setQ(String q) {
		this.q = q;
	}

	/**
	 * Get the value of property {@code strict}.
	 *
	 * @return the strict
	 */
	public Boolean getStrict() {
		return strict;
	}

	/**
	 * Set the value of property {@code strict}.
	 *
	 * @param strict the value of property strict
	 */
	public void setStrict(Boolean strict) {
		this.strict = strict;
	}

	/**
	 * Get the value of property {@code status}.
	 *
	 * @return the status
	 */
	public JobStatus getStatus() {
		return status;
	}

	/**
	 * Set the value of property {@code status}.
	 *
	 * @param status the value of property status
	 */
	public void setStatus(JobStatus status) {
		this.status = status;
	}

	//
	// With
	//

	/**
	 * Set the value of property {@code q}.
	 *
	 * @param q the q to with
	 * @return this {@code JobFilter}
	 */
	public JobFilter withQ(String q) {
		this.q = q;
		return this;
	}

	/**
	 * Set the value of property {@code strict}.
	 *
	 * @param strict the strict to with
	 * @return this {@code JobFilter}
	 */
	public JobFilter withStrict(Boolean strict) {
		this.strict = strict;
		return this;
	}

	/**
	 * Set the value of property {@code status}.
	 *
	 * @param status the status to with
	 * @return this {@code JobFilter}
	 */
	public JobFilter withStatus(JobStatus status) {
		this.status = status;
		return this;
	}
	
	@Override
	public ToStringCreator toString(ToStringCreator creator) {
		return creator
			.append("q", q)
			.append("strict", strict)
			.append("status", status)
			;
	}

	
}
