package org.einnovator.notifications.client.manager;

import org.einnovator.notifications.client.amqp.NotificationListener;
import org.einnovator.notifications.client.model.Application;
import org.einnovator.notifications.client.model.Event;
import org.einnovator.notifications.client.model.Notification;
import org.einnovator.notifications.client.modelx.NotificationFilter;
import org.einnovator.notifications.client.modelx.NotificationOptions;
import org.springframework.cache.Cache;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

public interface NotificationManager extends NotificationListener {

	boolean register(Application app);
	boolean register(Application app, OAuth2RestTemplate restTemplate);

	void publishEvent(Event event);
	void publishDirect(Notification notification);	
	void publishEventHttp(Event event);
	void publishEventAmqp(Event event);
	void publishDirectAmqp(Notification notification);
	
	Page<Notification> listNotifications(NotificationFilter filter, Pageable pageable);
	
	Long countNotifications(NotificationFilter filter);
	
	void deleteNotification(String id, NotificationOptions options);
	
	Cache getNotificationCache();
	Cache getNotificationCountCache();
	void clearCaches();
	void evictCachesForUser(String userId);

    
}
