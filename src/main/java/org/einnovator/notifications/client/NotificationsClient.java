package org.einnovator.notifications.client;

import static org.einnovator.notifications.client.NotificationsEndpoints.makeURI;

import java.net.URI;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.util.StringUtils;

import org.einnovator.util.MappingUtils;
import org.einnovator.notifications.client.model.Action;
import org.einnovator.notifications.client.model.Event;
import org.einnovator.notifications.client.model.Notification;
import org.einnovator.notifications.client.model.NotificationType;
import org.einnovator.notifications.client.model.Source;
import org.einnovator.notifications.client.model.Target;

public class NotificationsClient implements NotificationOperationsHttp, NotificationOperationsAmqp {

	private Logger logger = Logger.getLogger(this.getClass());

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
		restTemplate.exchange(request, Void.class);
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
	
	public void register(String app, List<NotificationType> types) {
		register(app, types, restTemplate);
	}
	
	public void register(String app, List<NotificationType> types, OAuth2ClientContext oauth2ClientContext) {
		URI uri = makeURI(NotificationsEndpoints.register(app, config));
		for (NotificationType type: types) {
			type.setApp(app);
		}
		RequestEntity<List<NotificationType>> request = RequestEntity.post(uri).body(types);
		restTemplate.exchange(request, Void.class);
	}

	public void register(String app, List<NotificationType> types, OAuth2ProtectedResourceDetails resource, OAuth2ClientContext oAuth2ClientContext) {
		OAuth2RestTemplate template = new OAuth2RestTemplate(resource, oAuth2ClientContext);
		register(app, types, template);
	}

	public void register(String app, List<NotificationType> types, OAuth2RestTemplate restTemplate) {
		URI uri = makeURI(NotificationsEndpoints.register(app, config));
		for (NotificationType type: types) {
			type.setApp(app);
		}
		RequestEntity<List<NotificationType>> request = RequestEntity.post(uri).body(types);
		restTemplate.exchange(request, Void.class);
	}

	public List<Notification> getUserNotifications(String username) {
		URI uri = makeURI(NotificationsEndpoints.notifications(username, config));
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<Notification[]> result = restTemplate.exchange(request, Notification[].class);
		return Arrays.asList(result.getBody());
	}
	
	public List<Notification> getNotifications() {
		URI uri = makeURI(NotificationsEndpoints.notifications(config));
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<Notification[]> result = restTemplate.exchange(request, Notification[].class);
		return Arrays.asList(result.getBody());
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Notification> getUserNotifications() {
		URI uri = makeURI(NotificationsEndpoints.notifications(config));
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<List> result = restTemplate.exchange(request, List.class);
		return result.getBody();
	}
	
	public Long countNotifications() {
		URI uri = makeURI(NotificationsEndpoints.count(config));
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<Long> result = restTemplate.exchange(request, Long.class);
		return result.getBody();
	}
	
	public Long countUserNotifications(String username) {
		URI uri = makeURI(NotificationsEndpoints.count(username, config));
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<Long> result = restTemplate.exchange(request, Long.class);
		return result.getBody();
	}

	public void deleteUserNotification(String username, String id) {
		URI uri = makeURI(NotificationsEndpoints.notification(id, username, config));
		RequestEntity<Void> request = RequestEntity.delete(uri).accept(MediaType.APPLICATION_JSON).build();
		restTemplate.exchange(request, Void.class);
	}
	
	public void deleteNotification(String id) {
		URI uri = makeURI(NotificationsEndpoints.notification(id, config));
		RequestEntity<Void> request = RequestEntity.delete(uri).accept(MediaType.APPLICATION_JSON).build();
		restTemplate.exchange(request, Void.class);
	}
	
	public String makeNotificationTypeId(Source source, Action action) {
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

	
}
