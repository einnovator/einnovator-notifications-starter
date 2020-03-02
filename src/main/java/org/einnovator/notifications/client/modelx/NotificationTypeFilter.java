package org.einnovator.notifications.client.modelx;

import org.einnovator.util.model.ToStringCreator;

/**
 * A {@code NotificationTypeFilter}.
 *
 * @author support@einnovator.org
 *
 */
public class NotificationTypeFilter extends NotificationTypeOptions {
	
	private String q;
	
	private String app;
	
	//
	// Constructors
	//
		
	/**
	 * Create instance of {@code NotificationTypeFilter}.
	 *
	 */
	public NotificationTypeFilter() {
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
	
	//
	// With
	//

	/**
	 * Set the value of property {@code q}.
	 *
	 * @param q the value of property q
	 * @return this {@code NotificationTypeFilter}
	 */
	public NotificationTypeFilter withQ(String q) {
		this.q = q;
		return this;
	}


	/**
	 * Set the value of property {@code app}.
	 *
	 * @param app the value of property app
	 * @return this {@code NotificationTypeFilter}
	 */
	public NotificationTypeFilter withApp(String app) {
		this.app = app;
		return this;
	}


	@Override
	public ToStringCreator toString(ToStringCreator creator) {
		return creator
			.append("q", q)
			.append("app", app)
			;
	}

	
}
