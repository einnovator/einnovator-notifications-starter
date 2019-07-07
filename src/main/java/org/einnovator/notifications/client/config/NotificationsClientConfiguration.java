package org.einnovator.notifications.client.config;

import javax.annotation.PostConstruct;

import org.einnovator.notifications.client.amqp.AmqpConfiguration;
import org.einnovator.notifications.client.model.NotificationsRegistration;
import org.einnovator.util.StringUtil;
import org.einnovator.util.config.ConnectionConfiguration;
import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.core.env.Environment;

@ConfigurationProperties("notifications")
public class NotificationsClientConfiguration extends ObjectBase {

	private String server = "http://localhost:2010";

	@NestedConfigurationProperty
	private AmqpConfiguration amqp = new AmqpConfiguration();

	@NestedConfigurationProperty
	private TemplatesConfiguration templates = new TemplatesConfiguration();
	
	@NestedConfigurationProperty
	private ConnectionConfiguration connection = new ConnectionConfiguration();
	
	@NestedConfigurationProperty
	private NotificationsRegistration registration = new NotificationsRegistration();
	
	@Autowired
	private Environment env;
	
	public NotificationsClientConfiguration() {
	}


	@PostConstruct
	public void init() {
		if (StringUtil.contains(env.getActiveProfiles(), "dev")) {
			if (templates.getCache()==null) {
				templates.setCache(true);
			}
		}
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
	 * Get the value of property {@code templates}.
	 *
	 * @return the templates
	 */
	public TemplatesConfiguration getTemplates() {
		return templates;
	}

	/**
	 * Set the value of property {@code templates}.
	 *
	 * @param templates the templates to set
	 */
	public void setTemplates(TemplatesConfiguration templates) {
		this.templates = templates;
	}

	/**
	 * Get the value of property {@code registration}.
	 *
	 * @return the registration
	 */
	public NotificationsRegistration getRegistration() {
		return registration;
	}

	/**
	 * Set the value of property {@code registration}.
	 *
	 * @param registration the registration to set
	 */
	public void setRegistration(NotificationsRegistration registration) {
		this.registration = registration;
	}

	@Override
	public ToStringCreator toString(ToStringCreator creator) {
		return creator
				.append("server", server)
				.append("amqp", amqp)
				.append("templates", templates)
				.append("registration", registration)
				;
	}
	
}
