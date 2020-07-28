/**
 * 
 */
package org.einnovator.notifications.client.manager;

import static org.einnovator.util.EnvUtil.getEnv;
import static org.einnovator.util.EnvUtil.isSpringProfileActive;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.einnovator.notifications.client.amqp.AmqpConfig;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.util.StringUtils;

/**
 *
 */
public class NotificationsUtil {

	private final static Log logger = LogFactory.getLog(NotificationsUtil.class);

	public static final String NOTIFICATIONS_ENABLED = "NOTIFICATIONS_ENABLED";
	public static final String NOTIFICATIONS_PUBLISH_ENABLED = "NOTIFICATIONS_PUBLISH_ENABLED";
	
	/**
	 * Setup notification from environment variables.
	 *  
	 *  Enable or disable notifications.
	 *  Enable or disable AMQP.
	 *  
	 *  @return array of Spring profiles to enable
	 */
	public static String[] setupFromEnv() {
		StringBuilder exclude = new StringBuilder();
		String notificationsEnabled = getEnv(NOTIFICATIONS_ENABLED);
		String notificationsServer = getEnv("NOTIFICATIONS_SERVER");
		boolean amqp = isSpringProfileActive(AmqpConfig.AMQP);

		List<String> profiles = new ArrayList<>();

		String rabbitHost = getEnv("SPRING_RABBITMQ_HOST");
		boolean excludeRabbit = isEmpty(rabbitHost);
		if (excludeRabbit) {
			exclude.append(RabbitAutoConfiguration.class.getName());
		} else {
			amqp = true;
		}

		if (amqp && !isSpringProfileActive(AmqpConfig.AMQP)) {
			profiles.add(AmqpConfig.AMQP);
		}
		boolean notifications = false;
		boolean publish = false;
		if (notificationsEnabled!=null && notificationsEnabled.equalsIgnoreCase("true")) {
			notifications = true;
			publish = true;
		}
		if (!isEmpty(notificationsServer) && notificationsServer.indexOf("***")<0) {
			notifications = true;
			publish = true;
		}
		if (amqp) {
			publish = true;
		}

		if (notifications) {
			System.setProperty(NOTIFICATIONS_ENABLED, "true");	
			logger.info("setupFromEnv: Notifications enabled");			
		} else {
			System.setProperty(NOTIFICATIONS_ENABLED, "false");				
			logger.info("setupFromEnv: Notifications disabled");
		}
		if (publish) {
			System.setProperty(NOTIFICATIONS_PUBLISH_ENABLED, "true");	
			logger.info("setupFromEnv: Event publish enabled");			
		} else {
			System.setProperty(NOTIFICATIONS_PUBLISH_ENABLED, "false");				
			logger.info("setupFromEnv: Event publish disabled");
		}

		String sexclude = exclude.toString();
		if (StringUtils.hasText(sexclude)) {
			System.setProperty("SPRING_AUTOCONFIGURE_EXCLUDE", sexclude);
		}
		if (!amqp) {
			logger.info("setupFromEnv: AMQP disabled");			
		}
		return profiles.toArray(new String[profiles.size()]);
	}
	
	private static boolean isEmpty(String s) {
		return !StringUtils.hasText(s) || s.contains("***");
	}
}
