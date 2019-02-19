/**
 * 
 */
package org.einnovator.notifications.client;

import org.einnovator.notifications.client.model.Event;
import org.einnovator.util.event.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class NotificationEventPublisher implements EventPublisher {

	@Autowired
	protected NotificationsClient client;

	/**
	 * Create instance of {@code RemoteEventPublisher}.
	 *
	 */
	public NotificationEventPublisher() {
	}


	@Override
	public void publishEvent(Object event) {
		if (event instanceof Event) {
			client.publishEvent((Event)event);
		}
	}

}
