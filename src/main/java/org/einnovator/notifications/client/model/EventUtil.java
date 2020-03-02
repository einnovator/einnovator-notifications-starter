package org.einnovator.notifications.client.model;

import org.springframework.util.StringUtils;


/**
 * {@code Event} related static utilities.
 *
 * @see Event
 * @author support@einnovator.org
 *
 */
public class EventUtil {

	
	public static String isLogout(Event event) {
		Source source = event.getSource();
		Action action = event.getAction();
		if (source.getType().equalsIgnoreCase(SourceType.USER)) {
			if (action.getType().equalsIgnoreCase(ActionType.LOGOUT)) {
				String username = source.getId();
				if (StringUtils.hasText(username)) {
					return username;
				}
			}
		}
		return null;
	}

}
