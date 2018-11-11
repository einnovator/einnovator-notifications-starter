package org.einnovator.notifications.client;

import org.einnovator.notifications.client.model.Event;

public interface NotificationOperationsHttp {

	void publishEventHttp(Event event);
}
