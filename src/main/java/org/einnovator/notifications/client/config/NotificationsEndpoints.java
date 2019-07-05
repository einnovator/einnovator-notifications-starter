package org.einnovator.notifications.client.config;

public class NotificationsEndpoints {

	public static String event(NotificationsClientConfiguration config) {
		return api(config) + "/event";
	}
	
	public static String register(NotificationsClientConfiguration config) {
		return api(config) + "/register";
	}
	
	public static String notifications(NotificationsClientConfiguration config) {
		return api(config) + "/notification";
	}
	
	public static String notification(String id, NotificationsClientConfiguration config) {
		return api(config) + "/notification/" + id;
	}
	
	public static String count(NotificationsClientConfiguration config) {
		return api(config) + "/count";
	}

	public static String preferences(NotificationsClientConfiguration config) {
		return api(config) + "/preference";
	}

	public static String follow(NotificationsClientConfiguration config) {
		return api(config) + "/follow";
	}

	public static String unfollow(NotificationsClientConfiguration config) {
		return api(config) + "/unfollow";
	}

	public static String api(NotificationsClientConfiguration config) {
		return config.getServer() + "/api";
	}
	
	public static String templates(NotificationsClientConfiguration config) {
		return api(config) + "/template";
	}
	
	public static String template(String id, NotificationsClientConfiguration config) {
		return api(config) + "/template/" + id;
	}
}
