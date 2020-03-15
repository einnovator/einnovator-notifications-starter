package org.einnovator.notifications.client;


import org.einnovator.notifications.client.model.Event;

public interface NotificationOperationsAmqp {

	void publishEventAmqp(Event event);
}
