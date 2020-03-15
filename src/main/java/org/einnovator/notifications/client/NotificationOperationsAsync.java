package org.einnovator.notifications.client;


import org.einnovator.notifications.client.model.Event;

public interface NotificationOperationsAsync {

	void publishEventAsync(Event event);
}
