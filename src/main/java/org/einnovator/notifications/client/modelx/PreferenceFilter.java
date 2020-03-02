package org.einnovator.notifications.client.modelx;

import org.einnovator.util.model.ToStringCreator;

/**
 * A {@code PreferenceFilter}.
 *
 * @author support@einnovator.org
 *
 */
public class PreferenceFilter extends PreferenceOptions {
	
	private String q;
	
	
	public PreferenceFilter() {
	}

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

	@Override
	public ToStringCreator toString(ToStringCreator creator) {
		return creator
			.append("q", q)
			;
	}

	
}
