package org.einnovator.notifications.client;

import static org.einnovator.util.UriUtils.appendQueryParameters;
import static org.einnovator.util.UriUtils.makeURI;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.einnovator.notifications.client.model.Action;
import org.einnovator.notifications.client.model.Application;
import org.einnovator.notifications.client.model.ErrorReport;
import org.einnovator.notifications.client.model.Event;
import org.einnovator.notifications.client.model.Notification;
import org.einnovator.notifications.client.model.Preference;
import org.einnovator.notifications.client.model.Source;
import org.einnovator.notifications.client.model.Target;
import org.einnovator.notifications.client.modelx.NotificationFilter;
import org.einnovator.notifications.client.modelx.NotificationOptions;
import org.einnovator.util.MappingUtils;
import org.einnovator.util.PageOptions;
import org.einnovator.util.PageUtil;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;

public class NotificationsClient implements NotificationOperationsHttp, NotificationOperationsAmqp {

	private final Log logger = LogFactory.getLog(getClass());

	public static final String TYPE_ID_SEPARATOR = ":";

	@Autowired
	private NotificationsConfiguration config;


	@Qualifier("notificationsRestTemplate")
	@Autowired
	private OAuth2RestTemplate restTemplate;

	@Autowired
	private RabbitTemplate amqpTemplate;

	
	@Autowired
	public NotificationsClient() {
	}

	public NotificationsClient(NotificationsConfiguration config) {
		this.config = config;
	}
		
	public NotificationsClient(OAuth2RestTemplate restTemplate, NotificationsConfiguration config) {
		this.restTemplate = restTemplate;
		this.config = config;
	}
	
	public void publishEvent(Event event) {
		publishEventAmqp(event);
	}
	
	public void publishDirect(Notification notification) {
		publishDirect(notification);
	}
	
	public void publishEventHttp(Event event) {
		URI uri = makeURI(NotificationsEndpoints.event(config));
		RequestEntity<Event> request = RequestEntity.post(uri).body(event);
		exchange(request, Void.class);
	}
	
	public void publishEventAmqp(Event event) {
		Object eventData = MappingUtils.convert(event, LinkedHashMap.class);
		logger.debug("publishEventAmqp:" + eventData);
		amqpTemplate.convertAndSend(config.getAmqp().getEventExchange(), "" /*routingKey*/, eventData, new MessagePostProcessor() {
			
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				return message;
			}
		});
	}
	
	public void publishDirectAmqp(Notification notification) {
		Object notificationData =  MappingUtils.convert(notification, LinkedHashMap.class);
		logger.debug("publishDirectAmqp:" + notificationData);
		amqpTemplate.convertAndSend(config.getAmqp().getNotificationsExchange(), "" /*routingKey*/, notificationData, new MessagePostProcessor() {
			
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				return message;
			}
		});
	}
	
	
	public void reportError(ErrorReport error) {
		
	}


	public void register(Application app) {
		register(app, restTemplate);
	}
	
	public void register(Application app, OAuth2ProtectedResourceDetails resource, OAuth2ClientContext oAuth2ClientContext) {
		OAuth2RestTemplate template = new OAuth2RestTemplate(resource, oAuth2ClientContext);
		register(app,  template);
	}

	public void register(Application app, OAuth2RestTemplate restTemplate) {
		URI uri = makeURI(NotificationsEndpoints.register(config));
		if (app!=null) {
			Map<String, String> params = new LinkedHashMap<>();
			params.putAll(MappingUtils.toMapFormatted(app));
			uri = appendQueryParameters(uri, params);	
		}
		RequestEntity<Application> request = RequestEntity.post(uri).body(app);
		exchange(request, Void.class);
	}

	public Page<Notification> listNotifications(NotificationFilter filter, Pageable pageable) {
		URI uri = makeURI(NotificationsEndpoints.notifications(config));
		if (filter!=null || pageable!=null) {
			Map<String, String> params = new LinkedHashMap<>();
			if (filter!=null) {
				params.putAll(MappingUtils.toMapFormatted(filter));				
			}
			if (pageable!=null) {
				params.putAll(MappingUtils.toMapFormatted(new PageOptions(pageable)));				
			}
			
			uri = appendQueryParameters(uri, params);	
		}
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		@SuppressWarnings("rawtypes")
		ResponseEntity<Page> result = exchange(request, Page.class);
		return PageUtil.create(result.getBody(),  Notification.class);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Notification> getUserNotifications() {
		URI uri = makeURI(NotificationsEndpoints.notifications(config));
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<List> result = exchange(request, List.class);
		return result.getBody();
	}
	
	public Long countNotifications(NotificationFilter filter) {
		URI uri = makeURI(NotificationsEndpoints.count(config));
		if (filter!=null) {
			Map<String, String> params = new LinkedHashMap<>();
			params.putAll(MappingUtils.toMapFormatted(filter));
			uri = appendQueryParameters(uri, params);	
		}
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<Long> result = exchange(request, Long.class);
		return result.getBody();
	}

	public void deleteNotification(String id, NotificationOptions options) {
		URI uri = makeURI(NotificationsEndpoints.notification(id, config));
		if (options!=null) {
			Map<String, String> params = new LinkedHashMap<>();
			params.putAll(MappingUtils.toMapFormatted(options));
			uri = appendQueryParameters(uri, params);	
		}
		RequestEntity<Void> request = RequestEntity.delete(uri).accept(MediaType.APPLICATION_JSON).build();
		exchange(request, Void.class);
	}

	public Map<String, Preference> getPreferences() {
		return getPreferences(null);
	}

	public Map<String, Preference> getPreferences(String username) {
		URI uri = makeURI(NotificationsEndpoints.preferences(config));
		if (username!=null) {
			Map<String, String> params = new LinkedHashMap<>();
			params.put("username", username);
			uri = appendQueryParameters(uri, MappingUtils.toMapFormatted(params));			
		}

		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> result = restTemplate.exchange(request, Map.class);
		Map<String, Preference> map = new LinkedHashMap<>();
		@SuppressWarnings("unchecked")
		Set<Map.Entry<?, ?>> ee = result.getBody().entrySet();		
		for (Map.Entry<?, ?> e: ee) {
			Preference pref = MappingUtils.convert(e.getValue(), Preference.class);
			map.put(e.getKey().toString(), pref);
		}
		return map;
	}

	public static String makeNotificationTypeId(Source source, Action action) {
		return makeNotificationTypeId(source.getType(), action.getType());
	}
	
	public static String makeNotificationTypeId(String sourceType, String actionType) {
		return makeNotificationTypeId(sourceType, actionType, null);
	}
		
	public static String makeNotificationTypeId(String sourceType, String actionType, Object suffix) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.hasText(sourceType)) {
			sb.append(sourceType.toLowerCase());
		}
		if (StringUtils.hasText(actionType)) {
			if (sb.length()>0) {
				sb.append(TYPE_ID_SEPARATOR);
			}
			sb.append(actionType.toLowerCase());
		}
		if (suffix!=null && StringUtils.hasText(suffix.toString())) {
			if (sb.length()>0) {
				sb.append(TYPE_ID_SEPARATOR);
			}
			sb.append(suffix.toString().toLowerCase());
		}
		if (sb.length()==0) {
			return null;
		}
		return sb.toString();
	}
	
	

    
	public void deliver(SimpMessagingTemplate template, Notification notification, Target target) {
		Map<String, Object> payload = MappingUtils.toMap(notification);
		String username = target.getId();		
		template.convertAndSend("/queue/" + username, payload);
	}
	
	public void deliver(SimpMessagingTemplate template, Notification notification) {
		List<Target> targets =notification.getTargets();
		if (targets!=null) {
			Map<String, Object> payload = MappingUtils.toMap(notification);
			for (Target target: targets) {
				String username = target.getId();	
				if (username==null) {
					continue;
				}
				template.convertAndSend("/queue/" + username, payload);
			}
		}
	}

	
	protected <T> ResponseEntity<T> exchange(RequestEntity<?> request, Class<T> responseType) throws RestClientException {
		return restTemplate.exchange(request, responseType);
	}

}
