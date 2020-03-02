package org.einnovator.notifications.client.manager;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.einnovator.notifications.client.NotificationsClient;
import org.einnovator.notifications.client.amqp.NotificationListener;
import org.einnovator.notifications.client.config.NotificationsClientConfiguration;
import org.einnovator.notifications.client.config.NotificationsClientContext;
import org.einnovator.notifications.client.model.ActionType;
import org.einnovator.notifications.client.model.Event;
import org.einnovator.notifications.client.model.Notification;
import org.einnovator.notifications.client.model.NotificationsRegistration;
import org.einnovator.notifications.client.model.Target;
import org.einnovator.notifications.client.modelx.NotificationFilter;
import org.einnovator.util.event.LogoutApplicationEvent;
import org.einnovator.util.security.SecurityUtil;
import org.einnovator.util.web.RequestOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
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

	@Autowired
	private NotificationsClientConfiguration config;
	 
	public NotificationManagerImpl(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
	public NotificationManagerImpl(NotificationsClient client, CacheManager cacheManager) {
		this.client = client;
		this.cacheManager = cacheManager;
	}

	@PostConstruct
	public void initialize() {
		if (config.getRegistration()!=null) {
			if (config.getRegistration().isAuto()) {
				register();
			}
		}
	}
	
	@Override
	public boolean publishEvent(Event event, NotificationsClientContext context) {
		try {
			client.publishEvent(event, context);	
			return true;
		} catch (RuntimeException e) {
			logger.error("publishEvent: " + e + " " + format(event));
			return false;
		}
	}


	@Override
	public boolean publishDirect(Notification notification, NotificationsClientContext context) {
		try {
			client.publishDirect(notification, context);		
			return true;			
		} catch (RuntimeException e) {
			logger.error("publishEvent: " + e + " " + format(notification));
			return false;
		}
		
	}

	@Override
	public boolean publishEventHttp(Event event, NotificationsClientContext context) {
		try {
			client.publishEventHttp(event, context);
			return true;			
		} catch (RuntimeException e) {
			logger.error("publishEvent: " + e + " " + format(event));
			return false;
		}
	}
	
	@Override
	public boolean publishEventAmqp(Event event, NotificationsClientContext context) {
		try {
			client.publishEventAmqp(event, context);			
			return true;
		} catch (RuntimeException e) {
			logger.error("publishEventAmqp: " + e + " " + format(event));
			return false;
		}

	}
	
	@Override
	public boolean publishDirectAmqp(Notification notification, NotificationsClientContext context) {
		try {
			client.publishDirectAmqp(notification, context);			
			return true;
		} catch (RuntimeException e) {
			logger.error("publishDirectAmqp: " + e + " " + format(notification));
			return false;
		}

	}

	@Override
	public boolean register() {
		try {
			client.register();			
			return true;
		} catch (RuntimeException e) {
			logger.error("register: " + e);
			return false;
		}		
	}

	@Override
	public boolean register(NotificationsRegistration registration) {
		try {
			client.register(registration);			
			return true;
		} catch (RuntimeException e) {
			logger.error("register: " + e);
			return false;
		}		
	}

	
	@Override
	public boolean register(NotificationsRegistration registration, OAuth2RestTemplate template) {
		try {
			client.register(registration, template);
			return true;
		} catch (RuntimeException e) {
			logger.error("register: Attempt to register application failed: " + e + " " + registration);
			return false;
		}
	}
	
	
	
	@Override
	@SuppressWarnings("unchecked")
	public Page<Notification> listNotifications(NotificationFilter filter, Pageable pageable, NotificationsClientContext context) {
		if (config.getEnabled()!=null && Boolean.FALSE.equals(config.getEnabled())) {
			logger.debug("listNotifications: not enabled");
			return null;
		}

		String username = filter!=null  ? filter.getRequiredPrincipal() : SecurityUtil.getPrincipalName();
		Page<Notification> notifications = null;
		if (isKey(true, filter, pageable)) {
			notifications = getCacheValue(Page.class, getNotificationCache(), username);
			if (notifications!=null) {
				return notifications;
			}			
		}
		try {
			notifications = client.listNotifications(filter, pageable, null);		
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
	

	@Override
	public Long countNotifications(NotificationFilter filter) {
		return countNotifications(filter, null);
	}

	@Override
	public Long countNotifications(NotificationFilter filter, NotificationsClientContext context) {
		String username = filter!=null  ? filter.getRequiredPrincipal() : SecurityUtil.getPrincipalName();

		if (config.getEnabled()!=null && Boolean.FALSE.equals(config.getEnabled())) {
			logger.debug("countNotifications: not enabled");
			return 0L;
		}

		Long count = getCacheValue(Long.class, getNotificationCountCache(), username);
		if (count!=null) {
			return count;
		}
		try {
			count = client.countNotifications(filter, context);	
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
	public void deleteNotification(String id, RequestOptions options, NotificationsClientContext context) {
		String username = options!=null  ? options.getRequiredPrincipal() : SecurityUtil.getPrincipalName();
		try {
			client.deleteNotification(id, options, context);
			evictCachesForUser(username);				
		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode()!=HttpStatus.NOT_FOUND) {
				logger.error("deleteNotification: " + id);
			}
		} catch (RuntimeException e) {
			logger.error("deleteNotification:" + e);
		}		
	}

	//
	// Caching
	//
	
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
		if (logger.isDebugEnabled()) {
			logger.debug("onNotification: " + format(notification));			
		}
		if (notification.getAction()!=null) {
			if (ActionType.LOGOUT.equalsIgnoreCase(notification.getAction().getType())) {
				if (notification.getPrincipal()!=null && notification.getPrincipal().getId()!=null) {
					if (logger.isDebugEnabled()) {
						logger.debug("onNotification: logout:" + notification.getPrincipal().getId());						
					}
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

	
	private String format(Notification notification) {
		return String.format("%s", notification.getType());
	}

	private String format(Event event) {
		return String.format("%s", event.getType());
	}

}
