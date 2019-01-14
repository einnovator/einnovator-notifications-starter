package org.einnovator.notifications.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.ArrayList;
import java.util.List;

import org.einnovator.notifications.client.amqp.AmqpConfiguration;
import org.einnovator.notifications.client.model.NotificationType;
import org.einnovator.util.config.ConnectionConfiguration;
import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;

@ConfigurationProperties("notifications")
public class NotificationsConfiguration extends ObjectBase {

	private String server = "http://localhost:2010";

	@NestedConfigurationProperty
	private AmqpConfiguration amqp = new AmqpConfiguration();

	@NestedConfigurationProperty
	private TemplatesConfiguration templates;
	
	@NestedConfigurationProperty
	private ConnectionConfiguration connection = new ConnectionConfiguration();
	
	@NestedConfigurationProperty
	private List<NotificationType> types = new ArrayList<>();
	
	public NotificationsConfiguration() {
	}

	public ConnectionConfiguration getConnection() {
		return connection;
	}

	public void setConnection(ConnectionConfiguration connection) {
		this.connection = connection;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public AmqpConfiguration getAmqp() {
		return amqp;
	}

	public void setAmqp(AmqpConfiguration amqp) {
		this.amqp = amqp;
	}

	
	/**
	 * Get the value of property {@code types}.
	 *
	 * @return the types
	 */
	public List<NotificationType> getTypes() {
		return types;
	}

	/**
	 * Set the value of property {@code types}.
	 *
	 * @param types the types to set
	 */
	public void setTypes(List<NotificationType> types) {
		this.types = types;
	}

	/* (non-Javadoc)
	 * @see org.einnovator.util.model.ObjectBase#toString(org.einnovator.util.model.ToStringCreator)
	 */
	@Override
	public ToStringCreator toString(ToStringCreator creator) {
		return creator
				.append("server", server)
				.append("amqp", amqp)
				.append("templates", templates)
				.append("types", types)
				;
	}
	
}
