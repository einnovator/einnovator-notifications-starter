/**
 * 
 */
package org.einnovator.notifications.client;

import org.einnovator.notifications.client.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.PayloadApplicationEvent;

/**
 *
 */
public class NotificationApplicationEventPublisher implements ApplicationEventPublisher {

	@Autowired
	protected NotificationsClient client;

	/**
	 * Create instance of {@code RemoteEventPublisher}.
	 *
	 */
	public NotificationApplicationEventPublisher() {
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationEventPublisher#publishEvent(org.springframework.context.ApplicationEvent)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void publishEvent(ApplicationEvent event) {
		if (event instanceof PayloadApplicationEvent) {
			publishEvent(((PayloadApplicationEvent)event).getPayload());
		} 
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationEventPublisher#publishEvent(java.lang.Object)
	 */
	@Override
	public void publishEvent(Object event) {
		if (event instanceof Event) {
			client.publishEvent((Event)event, null);
		}
	}

}
