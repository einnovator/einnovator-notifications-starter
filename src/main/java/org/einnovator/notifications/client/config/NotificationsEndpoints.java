package org.einnovator.notifications.client.config;

public class NotificationsEndpoints {

	public static String event(NotificationsConfiguration config) {
		return api(config) + "/event";
	}
	
	public static String register(NotificationsConfiguration config) {
		return api(config) + "/register";
	}
	
	public static String notifications(NotificationsConfiguration config) {
		return api(config) + "/notification";
	}
	
	public static String notification(String id, NotificationsConfiguration config) {
		return api(config) + "/notification/" + id;
	}
	
	public static String count(NotificationsConfiguration config) {
		return api(config) + "/count";
	}

	public static String preferences(NotificationsConfiguration config) {
		return api(config) + "/preference";
	}

	public static String follow(NotificationsConfiguration config) {
		return api(config) + "/follow";
	}

	public static String unfollow(NotificationsConfiguration config) {
		return api(config) + "/unfollow";
	}

	public static String api(NotificationsConfiguration config) {
		return config.getServer() + "/api";
	}
}
