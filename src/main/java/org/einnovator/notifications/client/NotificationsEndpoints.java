package org.einnovator.notifications.client;

import java.net.URI;
import java.net.URISyntaxException;

public class NotificationsEndpoints {

	public static String event(NotificationsConfiguration config) {
		return config.getUri() + "/api/event";
	}
	
	public static String register(String app, NotificationsConfiguration config) {
		return config.getUri() + "/api/register";
	}
	
	public static String notifications(NotificationsConfiguration config) {
		return config.getUri() + "/api/notifications";
	}
	
	public static String notification(String id, NotificationsConfiguration config) {
		return config.getUri() + "/api/notification/" + id;
	}
	
	public static String count(NotificationsConfiguration config) {
		return config.getUri() + "/api/count";
	}

	public static String preferences(String app, NotificationsConfiguration config) {
		return config.getUri() + "/api/preference";
	}


	//ADMIN
	public static String notifications(String username, NotificationsConfiguration config) {
		return config.getUri() + "/api/admin/notifications?username=" + username;
	}
	
	public static String count(String username, NotificationsConfiguration config) {
		return config.getUri() + "/api/admin/count?username=" + username;
	}
	
	public static String notification(String id, String username, NotificationsConfiguration config) {
		return config.getUri() + "/api/admin/notification/" + id + "?username=" + username;
	}
	
	public static URI makeURI(String uri) {
		try {
			return new URI(uri);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}		
	}
}
