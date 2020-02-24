/**
 * 
 */
package org.einnovator.notifications.client.config;

import org.einnovator.util.web.ClientContext;

/**
 *
 */
public class NotificationsClientContext extends ClientContext {
	
	private NotificationsClientConfiguration config;
	
	/**
	 * Create instance of {@code DocumentsClientContext}.
	 *
	 */
	public NotificationsClientContext() {
	}

	/**
	 * Get the value of property {@code config}.
	 *
	 * @return the config
	 */
	public NotificationsClientConfiguration getConfig() {
		return config;
	}

	/**
	 * Set the value of property {@code config}.
	 *
	 * @param config the value of property config
	 */
	public void setConfig(NotificationsClientConfiguration config) {
		this.config = config;
	}
	
}
