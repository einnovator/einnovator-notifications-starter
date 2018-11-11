package org.einnovator.notifications.client;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import org.einnovator.notifications.client.amqp.NotificationListener;
import org.einnovator.notifications.client.model.Notification;

public class ToWebSocketNotificationListener implements NotificationListener {

	private Logger logger = Logger.getLogger(this.getClass());
	
	
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
