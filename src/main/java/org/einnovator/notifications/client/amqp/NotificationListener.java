package org.einnovator.notifications.client.amqp;

import org.einnovator.notifications.client.model.Notification;

public interface NotificationListener {

	void onNotification(Notification notification) throws Exception;

}
