package org.einnovator.notifications.client;


import org.einnovator.notifications.client.model.Event;
import org.einnovator.util.web.RequestOptions;

public interface NotificationOperationsHttp {

	void publishEventHttp(Event event, RequestOptions options);
}
