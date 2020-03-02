package org.einnovator.notifications.client.manager;

import org.einnovator.notifications.client.amqp.NotificationListener;
import org.einnovator.notifications.client.config.NotificationsClientContext;
import org.einnovator.notifications.client.model.Event;
import org.einnovator.notifications.client.model.Notification;
import org.einnovator.notifications.client.model.NotificationsRegistration;
import org.einnovator.notifications.client.modelx.NotificationFilter;
import org.einnovator.util.web.RequestOptions;
import org.springframework.cache.Cache;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

public interface NotificationManager extends NotificationListener {

	boolean register();
	boolean register(NotificationsRegistration registration);
	boolean register(NotificationsRegistration registration, OAuth2RestTemplate restTemplate);

	boolean publishEvent(Event event, NotificationsClientContext context);
	boolean publishDirect(Notification notification, NotificationsClientContext context);	
	boolean publishEventHttp(Event event, NotificationsClientContext context);
	boolean publishEventAmqp(Event event, NotificationsClientContext context);
	boolean publishDirectAmqp(Notification notification, NotificationsClientContext context);
	
	Page<Notification> listNotifications(NotificationFilter filter, Pageable pageable, NotificationsClientContext context);
	
	Long countNotifications(NotificationFilter filter);
	Long countNotifications(NotificationFilter filter, NotificationsClientContext context);
	
	void deleteNotification(String id, RequestOptions options, NotificationsClientContext context);
	
	Cache getNotificationCache();
	Cache getNotificationCountCache();
	void clearCaches();
	void evictCachesForUser(String userId);

    
}
