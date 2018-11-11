package org.einnovator.notifications.client.model;

import org.springframework.util.StringUtils;


public class EventUtil {

	public static String isLogout(Event event) {
		Source source = event.getSource();
		Action action = event.getAction();
		if (source.getType().equalsIgnoreCase(Source.SOURCE_TYPE_USER)) {
			if (action.getType().equalsIgnoreCase(Action.ACTION_TYPE_LOGOUT)) {
				String username = source.getId();
				if (StringUtils.hasText(username)) {
					return username;
				}
			}
		}
		return null;
	}

}
