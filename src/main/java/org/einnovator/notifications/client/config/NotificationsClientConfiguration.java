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
	
	private Boolean enabled = true;

	private Boolean publishEnabled = true;

	/**
	 * Create instance of {@code NotificationsClientConfiguration}.
	 *
	 */
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

	

	/**
	 * Get the value of property {@code server}.
	 *
	 * @return the value of {@code server}
	 */
	public String getServer() {
		return server;
	}


	/**
	 * Set the value of property {@code server}.
	 *
	 * @param server the value of {@code server}
	 */
	public void setServer(String server) {
		this.server = server;
	}


	/**
	 * Get the value of property {@code amqp}.
	 *
	 * @return the value of {@code amqp}
	 */
	public AmqpConfiguration getAmqp() {
		return amqp;
	}


	/**
	 * Set the value of property {@code amqp}.
	 *
	 * @param amqp the value of {@code amqp}
	 */
	public void setAmqp(AmqpConfiguration amqp) {
		this.amqp = amqp;
	}


	/**
	 * Get the value of property {@code templates}.
	 *
	 * @return the value of {@code templates}
	 */
	public TemplatesConfiguration getTemplates() {
		return templates;
	}


	/**
	 * Set the value of property {@code templates}.
	 *
	 * @param templates the value of {@code templates}
	 */
	public void setTemplates(TemplatesConfiguration templates) {
		this.templates = templates;
	}


	/**
	 * Get the value of property {@code connection}.
	 *
	 * @return the value of {@code connection}
	 */
	public ConnectionConfiguration getConnection() {
		return connection;
	}


	/**
	 * Set the value of property {@code connection}.
	 *
	 * @param connection the value of {@code connection}
	 */
	public void setConnection(ConnectionConfiguration connection) {
		this.connection = connection;
	}


	/**
	 * Get the value of property {@code registration}.
	 *
	 * @return the value of {@code registration}
	 */
	public NotificationsRegistration getRegistration() {
		return registration;
	}


	/**
	 * Set the value of property {@code registration}.
	 *
	 * @param registration the value of {@code registration}
	 */
	public void setRegistration(NotificationsRegistration registration) {
		this.registration = registration;
	}


	/**
	 * Get the value of property {@code env}.
	 *
	 * @return the value of {@code env}
	 */
	public Environment getEnv() {
		return env;
	}


	/**
	 * Set the value of property {@code env}.
	 *
	 * @param env the value of {@code env}
	 */
	public void setEnv(Environment env) {
		this.env = env;
	}


	/**
	 * Get the value of property {@code enabled}.
	 *
	 * @return the value of {@code enabled}
	 */
	public Boolean getEnabled() {
		return enabled;
	}


	/**
	 * Set the value of property {@code enabled}.
	 *
	 * @param enabled the value of {@code enabled}
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	/**
	 * Get the value of property {@code publishEnabled}.
	 *
	 * @return the value of {@code publishEnabled}
	 */
	public Boolean getPublishEnabled() {
		return publishEnabled;
	}


	/**
	 * Set the value of property {@code publishEnabled}.
	 *
	 * @param publishEnabled the value of {@code publishEnabled}
	 */
	public void setPublishEnabled(Boolean publishEnabled) {
		this.publishEnabled = publishEnabled;
	}


	@Override
	public ToStringCreator toString(ToStringCreator creator) {
		return creator
				.append("server", server)
				.append("amqp", amqp)
				.append("templates", templates)
				.append("registration", registration)
				.append("enabled", enabled)
				.append("publishEnabled", publishEnabled)
				;
	}
	
}
