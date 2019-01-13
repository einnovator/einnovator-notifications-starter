package org.einnovator.notifications.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import org.einnovator.notifications.client.amqp.NotificationListener;
import org.einnovator.notifications.client.model.Notification;

public class ToWebSocketNotificationListener implements NotificationListener {

	private final Log logger = LogFactory.getLog(getClass());
	
	
	@Autowired
    private SimpMessagingTemplate template;
	
	@Autowired
	private NotificationsClient client;

	@Override
	public void onNotification(Notification notification) throws Exception {
		logger.info("onNotification: " + notification);
		client.deliver(template, notification);		
	}
	
	
}
