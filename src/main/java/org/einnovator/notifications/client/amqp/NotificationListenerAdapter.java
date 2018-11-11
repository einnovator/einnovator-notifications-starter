package org.einnovator.notifications.client.amqp;

import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.einnovator.notifications.client.NotificationSelector;
import org.einnovator.notifications.client.model.Action;
import org.einnovator.notifications.client.model.Meta;
import org.einnovator.notifications.client.model.Notification;
import org.einnovator.notifications.client.model.PrincipalX;
import org.einnovator.notifications.client.model.Source;

public class NotificationListenerAdapter implements NotificationListener {

	private Logger logger = Logger.getLogger(this.getClass());

	private Object listener;
	
	private Method method;
	
	private Method[] methods;
	
	private String methodName;

	private ObjectMapper mapper = new ObjectMapper();
	
	public NotificationListenerAdapter(Object listener, Method method) {
		this.listener = listener;
		this.method = method;
	}
	
	public NotificationListenerAdapter(Object listener, Method[] methods) {
		this.listener = listener;
		this.methods = methods;
	}

	public NotificationListenerAdapter(Object listener, String methodName) {
		this.listener = listener;
		this.methodName = methodName;
		this.method = findRequiredMethod(methodName);
	}
	
	public NotificationListenerAdapter(Object listener) {
		this.listener = listener;
		if (listener.getClass().getMethods().length==1) {
			method = listener.getClass().getMethods()[0];
		}
		List<Method> methods = new ArrayList<>();
		for (Method method: listener.getClass().getMethods()) {
			NotificationSelector methodSelector = AnnotationUtils.findAnnotation(method, NotificationSelector.class);
			if (methodSelector!=null) {
				methods.add(method);
			}
		}
		this.methods = methods.toArray(new Method[methods.size()]);
	}

	@Override
	public void onNotification(Notification notification) {
		if (method!=null) {
			if (NotificationListenerContainer.checkDelivery(notification, listener.getClass(), method)) {
				invoke(method, notification);				
			}
			return;
		}
		for (Method method: (methods!=null ? methods : listener.getClass().getMethods())) {
			NotificationSelector methodSelector = AnnotationUtils.findAnnotation(method, NotificationSelector.class);
			if (methodSelector==null) {
				continue;
			}
			if (NotificationListenerContainer.checkDelivery(notification, listener.getClass(), method)) {
				try {
					invoke(method, notification);				
				} catch (Exception e) {
					logger.error("onNotification: " + e + " : " + notification);
				}
			}
		}
	}

	private void invoke(Method method, Notification notification) {
		try {
			Class<?>[] parameterTypes = method.getParameterTypes();
			Object[] args = new Object[parameterTypes.length];
			for (int i=0; i<args.length; i++) {
				args[i] = getParameterValue(parameterTypes[i], notification);
			}
			method.setAccessible(true);
			method.invoke(listener, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
	private Object getParameterValue(Class<?> type, Notification notification) {
		if (type.isAssignableFrom(Notification.class) ) {
			return notification;
		}
		if (type.isAssignableFrom(Source.class) ) {
			return notification.getSource();
		}
		if (type.isAssignableFrom(Target[].class) ) {
			if (notification.getTargets()==null) {
				return null;
			}
			return notification.getTargets().toArray(new Target[notification.getTargets().size()]);
		}
		if (type.isAssignableFrom(Target.class) ) {
			if (notification.getTargets()==null || notification.getTargets().isEmpty()) {
				return null;
			}
			return notification.getTargets().get(0);
		}
		if (type.isAssignableFrom(Action.class) ) {
			return notification.getAction();
		}
		if (type.isAssignableFrom(Map.class) ) {
			return notification.getDetails();
		}
		if (type.isAssignableFrom(Meta.class) ) {
			return notification.getMeta();
		}
		if (type.isAssignableFrom(PrincipalX.class) ) {
			return notification.getPrincipal();
		}
		try {
			return mapper.convertValue(notification.getDetails(), type);			
		} catch (RuntimeException e) {
			throw new RuntimeException("Invalid parameter type or failed conversion:" + type + " ; " + e);			
		}
	}
	
	private Method findRequiredMethod(String methodName) {
		Method method = findMethod(methodName);
		if (method==null) {
			throw new RuntimeException("Method not found: " + methodName + " on: " + listener.getClass());
		}
		return method;
	}

	private Method findMethod(String methodName) {
		return findSingleMethod(listener.getClass(), methodName);
	}

	public static Method findRequiredSingleMethod(Class<?> klass, String methodName) {
		Method method = findSingleMethod(klass, methodName);
		if (method==null) {
			throw new RuntimeException("Method not found: " + methodName + " on: " + klass);
		}
		return method;
	}
	
	public static Method findSingleMethod(Class<?> klass, String methodName) {
		Method method = null;
		for (Method method2: klass.getMethods()) {
			if (method2.getName().equals(methodName)) {
				if (method!=null) {
					throw new RuntimeException("Ambigous method name: " + methodName + " on: " + klass);
				}
				method = method2;
			}
		}
		return method;
	}

	public Object getListener() {
		return listener;
	}

	public void setListener(Object listener) {
		this.listener = listener;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Method[] getMethods() {
		return methods;
	}

	public void setMethods(Method[] methods) {
		this.methods = methods;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
}
