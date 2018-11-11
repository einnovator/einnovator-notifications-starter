package org.einnovator.notifications.client.amqp;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties
public class AmqpConfiguration {
	
	public static final String DEFAULT_EVENTS_EXCHANGE = "events";

	public static final String DEFAULT_NOTIFICATIONS_QUEUE = "notifications";

	public static final String DEFAULT_NOTIFICATIONS_EXCHANGE = "notifications";

	private String eventExchange = DEFAULT_EVENTS_EXCHANGE;

	private String notificationsQueue = DEFAULT_NOTIFICATIONS_QUEUE;
	
	private String notificationsExchange = DEFAULT_NOTIFICATIONS_EXCHANGE;

	public AmqpConfiguration() {
	}
	
	public String getEventExchange() {
		return eventExchange;
	}

	public void setEventExchange(String eventExchange) {
		this.eventExchange = eventExchange;
	}

	public String getNotificationsQueue() {
		return notificationsQueue;
	}

	public void setNotificationsQueue(String notificationsQueue) {
		this.notificationsQueue = notificationsQueue;
	}

	
	public String getNotificationsExchange() {
		return notificationsExchange;
	}

	public void setNotificationsExchange(String notificationsExchange) {
		this.notificationsExchange = notificationsExchange;
	}

	@Override
	public String toString() {
		return "AmqpConfiguration [" + (eventExchange != null ? "eventExchange=" + eventExchange + ", " : "")
				+ (notificationsQueue != null ? "notificationsQueue=" + notificationsQueue + ", " : "")
				+ (notificationsExchange != null ? "notificationsExchange=" + notificationsExchange : "") + "]";
	}
	
	
}
