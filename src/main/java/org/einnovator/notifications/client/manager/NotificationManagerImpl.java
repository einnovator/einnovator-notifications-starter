package org.einnovator.notifications.client.manager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.einnovator.notifications.client.NotificationsClient;
import org.einnovator.notifications.client.amqp.NotificationListener;
import org.einnovator.notifications.client.model.Action;
import org.einnovator.notifications.client.model.Application;
import org.einnovator.notifications.client.model.Event;
import org.einnovator.notifications.client.model.Notification;
import org.einnovator.notifications.client.model.Target;
import org.einnovator.notifications.client.modelx.NotificationFilter;
import org.einnovator.notifications.client.modelx.NotificationOptions;
import org.einnovator.util.SecurityUtil;
import org.einnovator.util.event.LogoutApplicationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpStatusCodeException;

public class NotificationManagerImpl extends ManagerBase implements NotificationManager, NotificationListener {

	public static final String CACHE_NOTIFICATION = "Notification";
	public static final String CACHE_NOTIFICATION_COUNT = "Notification:count";

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private NotificationsClient client;
	
	private CacheManager cacheManager;
	
	 
	public NotificationManagerImpl(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
	public NotificationManagerImpl(NotificationsClient client, CacheManager cacheManager) {
		this.client = client;
		this.cacheManager = cacheManager;
	}

	@Override
	public void publishEvent(Event event) {
		try {
			client.publishEvent(event);			
		} catch (RuntimeException e) {
			logger.error("publishEvent: " + e + " " + event);
		}
	}
	
	@Override
	public void publishDirect(Notification notification) {
		try {
			client.publishDirect(notification);			
		} catch (RuntimeException e) {
			logger.error("publishEvent: " + e + " " + notification);
		}
		
	}
	
	@Override
	public void publishEventHttp(Event event) {
		try {
			client.publishEventHttp(event);			
		} catch (RuntimeException e) {
			logger.error("publishEvent: " + e + " " + event);
		}
	}
	
	@Override
	public void publishEventAmqp(Event event) {
		try {
			client.publishEventAmqp(event);			
		} catch (RuntimeException e) {
			logger.error("publishEventAmqp: " + e + " " + event);
		}

	}
	
	@Override
	public void publishDirectAmqp(Notification notification) {
		try {
			client.publishDirectAmqp(notification);			
		} catch (RuntimeException e) {
			logger.error("publishDirectAmqp: " + e + " " + notification);
		}

	}

	@Override
	public boolean register(Application app) {
		try {
			client.register(app);			
			return true;
		} catch (RuntimeException e) {
			logger.error("register: " + e + " " + app);
			return false;
		}
	}
	
	@Override
	public boolean register(Application app, OAuth2ProtectedResourceDetails resource, OAuth2ClientContext oAuth2ClientContext) {
		try {
			client.register(app, resource, oAuth2ClientContext);
			return true;
		} catch (RuntimeException e) {
			logger.error("register: " + e + " " + app);
			return false;
		}
	}
	
	@Override
	public boolean register(Application app, OAuth2RestTemplate restTemplate) {
		try {
			client.register(app, restTemplate);
			return true;
		} catch (RuntimeException e) {
			logger.error("register: " + e + " " + app);
			return false;
		}		
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Page<Notification> listNotifications(NotificationFilter filter, Pageable pageable) {
		String username = filter!=null && filter.getUsername()!=null ? filter.getUsername() : SecurityUtil.getPrincipalName();
		Page<Notification> notifications = null;
		if (isKey(true, filter, pageable)) {
			notifications = getCacheValue(Page.class, getNotificationCache(), username);
			if (notifications!=null) {
				return notifications;
			}			
		}
		try {
			notifications = client.listNotifications(filter, pageable);		
			if (isKey(true, filter, pageable)) {
				return putCacheValue(notifications, getNotificationCache(), username);
			}
			
			return putCacheValue(notifications, getNotificationCache(), username);
		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode()!=HttpStatus.NOT_FOUND) {
				logger.error("getNotification:" + e);				
			}
			return null;
		} catch (RuntimeException e) {
			logger.error("getNotification:" + e);
			return null;
		}		
	}
	
	protected boolean isKey(Object obj, boolean emptyKey) {
		if (obj==null) {
			return emptyKey;
		}
		return false;
	}

	protected boolean isKey(boolean emptyKey, Object... objs) {
		if (objs==null || objs.length==0) {
			return emptyKey;
		}
		for (Object obj: objs) {
			if (!isKey(obj, emptyKey)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Long countNotifications(NotificationFilter filter) {
		String username = filter!=null && filter.getUsername()!=null ? filter.getUsername() : SecurityUtil.getPrincipalName();
		Long count = getCacheValue(Long.class, getNotificationCountCache(), username);
		if (count!=null) {
			return count;
		}
		try {
			count = client.countNotifications(filter);	
			return putCacheValue(count, getNotificationCountCache(), username);
		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode()!=HttpStatus.NOT_FOUND) {
				logger.error("countNotifications:" + e);
			}
			return null;
		} catch (RuntimeException e) {
			logger.error("getUserNotifications:" + e);
			return null;
		}		
		
	}
	
	
	@Override
	public void deleteNotification(String id, NotificationOptions options) {
		String username = options!=null && options.getUsername()!=null ? options.getUsername() : SecurityUtil.getPrincipalName();
		try {
			client.deleteNotification(id, options);
			evictCachesForUser(username);				
		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode()!=HttpStatus.NOT_FOUND) {
				logger.error("deleteNotification: " + id);
			}
		} catch (RuntimeException e) {
			logger.error("deleteNotification:" + e);
		}		
	}

	@Override
	public void onNotification(Notification notification) {
		if (notification==null) {
			return;
		}
		try {
			if (notification.getTargets()!=null) {
				for (Target target: notification.getTargets()) {
					String targetId = target.getId();
					if (StringUtils.hasText(targetId)) {
						evictCachesForUser(targetId);
					}
				}
			}
		} catch (RuntimeException e) {
			logger.error("onNotification: " + e);
		}
		logger.debug("onNotification: " + notification);
		if (notification.getAction()!=null) {
			if (Action.ACTION_TYPE_LOGOUT.equalsIgnoreCase(notification.getAction().getType())) {
				if (notification.getPrincipal()!=null && notification.getPrincipal().getId()!=null) {
					logger.info("onNotification: logout:" + notification.getPrincipal().getId());
					eventPublisher.publishEvent(new LogoutApplicationEvent(this, notification.getPrincipal().getId()));					
					return;
				}
			}			
		}
		eventPublisher.publishEvent(new PayloadApplicationEvent<Notification>(this, notification));
	}


	@Override
	public void evictCachesForUser(String userId) {
		Cache cache = getNotificationCache();
		if (cache!=null) {
			cache.evict(userId);
		}
		Cache cache2 = getNotificationCountCache();
		if (cache2!=null) {
			cache2.evict(userId);
		}		
	}
	
	@Override
	public void clearCaches() {
		Cache cache = getNotificationCache();
		if (cache!=null) {
			cache.clear();
		}
		Cache cache2 = getNotificationCountCache();
		if (cache2!=null) {
			cache2.clear();
		}
	}


	@Override
	public Cache getNotificationCache() {
		Cache cache = cacheManager.getCache(NotificationManagerImpl.CACHE_NOTIFICATION);
		return cache;
	}

	@Override
	public Cache getNotificationCountCache() {
		Cache cache = cacheManager.getCache(NotificationManagerImpl.CACHE_NOTIFICATION_COUNT);
		return cache;
	}


}
