package org.einnovator.notifications.client.modelx;

import org.einnovator.notifications.client.model.Notification;
import org.einnovator.util.model.EntityOptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Options to retrieve {@code Notification}s.
 *
 * @see Notification
 * @see NotificationFilter
 * @author support@einnovator.org
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class NotificationOptions extends EntityOptions<Notification> {
	
	/**
	 * Create instance of {@code NotificationOptions}.
	 *
	 */
	public NotificationOptions() {
	}

}
