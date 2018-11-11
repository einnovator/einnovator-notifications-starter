package org.einnovator.notifications.client.amqp;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;

import org.einnovator.notifications.client.ActionSelector;
import org.einnovator.notifications.client.NotificationSelector;
import org.einnovator.notifications.client.NotificationSelectors;
import org.einnovator.notifications.client.SourceSelector;
import org.einnovator.notifications.client.model.Action;
import org.einnovator.notifications.client.model.Notification;
import com.rabbitmq.client.Channel;



public class NotificationListenerContainer implements ChannelAwareMessageListener {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private ApplicationContext context;
	
	private Collection<NotificationListener> listeners = new ArrayList<>();
	
	@Autowired
	private MessageConverter converter;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	public NotificationListenerContainer() {
		logger.debug("init:" + this);

	}
	
	@PostConstruct
	public void init() {
		logger.debug("Searching NotificationListeners...");

		for (String name: context.getBeanNamesForType(NotificationListener.class)) {
			NotificationListener listener = context.getBean(name, NotificationListener.class);
			logger.info("Found NotificationListener: " + listener);
			listeners.add(listener);
		}
		for (String name: context.getBeanNamesForAnnotation(NotificationSelector.class)) {
			Object listener = context.getBean(name);
			if (listener instanceof NotificationListener) {
				continue;
			}
			logger.info("Found @NotificationSelector: " + listener);
			listeners.add(new NotificationListenerAdapter(listener));
		}
		for (String name: context.getBeanDefinitionNames()) {
			Object listener = null;
			try {
				listener = context.getBean(name);				
			} catch (RuntimeException e) {
			}
			if (listener==null) {
				continue;
			}
			if (listener instanceof NotificationListener) {
				continue;
			}
			NotificationSelector selector = AnnotationUtils.findAnnotation(listener.getClass(), NotificationSelector.class);
			if (selector!=null) {
				continue;
			}
			for (Method method: listener.getClass().getMethods()) {
				NotificationSelector methodSelector = AnnotationUtils.findAnnotation(method, NotificationSelector.class);
				if (methodSelector!=null) {
					logger.info("Found POJO with @NotificationSelector: " + listener);
					listeners.add(new NotificationListenerAdapter(listener));
					break;
				}
			}
		}
		logger.debug("DONE: Searching NotificationListeners...");

	}
	

	
	public void onNotification(Notification notification) throws Exception {
		
		logger.debug("onNotification: " + notification);
		for (NotificationListener listener: listeners) {
			try {
				if (checkDelivery(notification, listener)) {
					listener.onNotification(notification);					
				}
			} catch (Exception e) {
				logger.error("onNotification: " + e + " : " + notification);
			}
		}
	}
	
	public static boolean checkDelivery(Notification notification, Object listener) {
		if (listener instanceof NotificationListenerAdapter) {
			return true;
		}

		try {
			Method method = null;
			if (listener instanceof NotificationListener) {
				method = listener.getClass().getMethod("onNotification", new Class[] {Notification.class});		
				return checkDelivery(notification, listener.getClass(), method, true);
			}
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean checkDelivery(Notification notification, Class<?> klass, Method method, boolean defaultDelivery) {
		NotificationSelector selector = AnnotationUtils.findAnnotation(klass, NotificationSelector.class);
		NotificationSelector methodSelector = AnnotationUtils.findAnnotation(method, NotificationSelector.class);
		
		if (checkDelivery(notification, selector, methodSelector, defaultDelivery)) {
			return true;
		}
		
		NotificationSelectors methodSelectors = AnnotationUtils.findAnnotation(method, NotificationSelectors.class);
		if (methodSelectors!=null) {
			for (NotificationSelector methodSelector2: methodSelectors.value()) {
				if (checkDelivery(notification, selector, methodSelector2, defaultDelivery)) {
					return true;
				}				
			}
		}
		
		NotificationSelectors selectors = AnnotationUtils.findAnnotation(klass, NotificationSelectors.class);
		if (selectors!=null) {
			for (NotificationSelector selector2: selectors.value()) {
				if (checkDelivery(notification, selector2, methodSelector, defaultDelivery)) {
					return true;
				}	
				if (methodSelectors!=null) {
					for (NotificationSelector methodSelector2: methodSelectors.value()) {
						if (checkDelivery(notification, selector2, methodSelector2, defaultDelivery)) {
							return true;
						}				
					}
				}
				
			}
		}
		

		return false;
	}

	public static boolean checkDelivery(Notification notification, Class<?> klass, Method method) {
		return checkDelivery(notification, klass, method, false);
	}

	private static boolean checkDelivery(Notification notification, NotificationSelector selector, NotificationSelector methodSelector, boolean defaultDelivery) {
		if (selector==null && methodSelector==null) {
			return defaultDelivery;
		}
		if (selector==null) {
			return checkDelivery(notification, methodSelector);
		}
		if (methodSelector==null) {
			return checkDelivery(notification, selector);
		}

		SourceSelector sourceSelector = methodSelector.source();
		if (sourceSelector==null || !StringUtils.hasText(sourceSelector.value())) {
			sourceSelector = selector.source();
		}
		if (sourceSelector!=null) {
			String sourceType = sourceSelector.value();
			if (StringUtils.hasText(sourceType)) {
				if (!sourceType.equalsIgnoreCase(notification.getSource().getType())) {
					return false;
				}
			}
		}
		ActionSelector actionSelector = methodSelector.action();
		if (actionSelector==null || !StringUtils.hasText(actionSelector.value())) {
			actionSelector = selector.action();
		}
		if (actionSelector!=null) {
			String actionType = actionSelector.value();
			if (StringUtils.hasText(actionType)) {
				Action action = notification.getAction();
				if (action==null || !actionType.equalsIgnoreCase(notification.getAction().getType())) {
					return false;
				}
			}
		}	
		return true;
	}
	private static boolean checkDelivery(Notification notification, NotificationSelector selector) {
		if (selector==null) {
			return true;
		}
		SourceSelector sourceSelector = selector.source();
		if (sourceSelector!=null) {
			String sourceType = sourceSelector.value();
			if (StringUtils.hasText(sourceType)) {
				if (!sourceType.equalsIgnoreCase(notification.getSource().getType())) {
					return false;
				}
			}
		}
		ActionSelector actionSelector = selector.action();
		if (actionSelector!=null) {
			String actionType = actionSelector.value();
			if (StringUtils.hasText(actionType)) {
				if (!actionType.equalsIgnoreCase(notification.getAction().getType())) {
					return false;
				}
			}
		}	
		return true;
	}

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		logger.debug("onMessage: " + message);
		
		try {
			Object payload = converter.fromMessage(message);
			Notification notification = mapper.convertValue(payload, Notification.class);
			onNotification(notification);			
		} catch (RuntimeException e) {
			logger.error("onMessage: " + e + " " + message);
		}
	}

}
