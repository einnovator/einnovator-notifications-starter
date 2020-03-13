package org.einnovator.notifications.client.modelx;

import org.einnovator.notifications.client.model.Notification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * A filter for {@code Notifications}s.
 *
 * @see Notification
 * @author support@einnovator.org
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class NotificationFilter extends NotificationOptions {

	/**
	 * Create instance of {@code NotificationFilter}.
	 *
	 */
	public NotificationFilter() {
	}
	
}
