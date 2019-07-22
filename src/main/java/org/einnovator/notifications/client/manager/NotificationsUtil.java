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
		String notificationsEnabled = getEnv("NOTIFICATIONS_ENABLED");
		String notificationsServer = getEnv("NOTIFICATIONS_SERVER");
		boolean amqp = isSpringProfileActive(AmqpConfig.AMQP);

		List<String> profiles = new ArrayList<>();

		String rabbitHost = getEnv("SPRING_RABBITMQ_HOST");
		boolean excludeRabbit = !StringUtils.hasText(rabbitHost);
		if (excludeRabbit) {
			exclude.append(RabbitAutoConfiguration.class.getName());
		} else {
			amqp = true;
		}

		if (amqp && !isSpringProfileActive(AmqpConfig.AMQP)) {
			profiles.add(AmqpConfig.AMQP);
		}
		boolean notifications = false;
		if (notificationsEnabled!=null && notificationsEnabled.equalsIgnoreCase("true")) {
			notifications = true;
		}
		if (StringUtils.hasText(notificationsServer)) {
			notifications = true;
		}
		if (amqp) {
			notifications = true;			
		}

		if (notifications) {
			System.setProperty("NOTIFICATIONS_ENABLED", "true");	
			logger.info("setupFromEnv: Notifications enabled");			
		} else {
			System.setProperty("NOTIFICATIONS_ENABLED", "false");				
			logger.info("setupFromEnv: Notifications disabled");
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
}