package org.einnovator.notifications.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import org.einnovator.notifications.client.amqp.AmqpConfiguration;

@ConfigurationProperties("notifications")
public class NotificationsConfiguration {

	@NestedConfigurationProperty
	private AmqpConfiguration amqp = new AmqpConfiguration();

	@NestedConfigurationProperty
	private ConnectionConfiguration connection = new ConnectionConfiguration();
	
	private String uri = "http://localhost:9696";
	
	
	public NotificationsConfiguration() {
	}

	public ConnectionConfiguration getConnection() {
		return connection;
	}

	public void setConnection(ConnectionConfiguration connection) {
		this.connection = connection;
	}

	public String getUri() {
		return uri;
	}


	public void setUri(String uri) {
		this.uri = uri;
	}

	public AmqpConfiguration getAmqp() {
		return amqp;
	}

	public void setAmqp(AmqpConfiguration amqp) {
		this.amqp = amqp;
	}

	@Override
	public String toString() {
		return "NotificationsConfiguration [" + (amqp != null ? "amqp=" + amqp + ", " : "")
				+ (uri != null ? "uri=" + uri : "") + "]";
	}
	
	
	
}
