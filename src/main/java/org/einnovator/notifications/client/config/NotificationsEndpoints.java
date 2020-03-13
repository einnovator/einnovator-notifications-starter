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
		return notifications(config) + "/" + id;
	}

	public static String notificationTypes(NotificationsClientConfiguration config) {
		return api(config) + "/notificationtype";
	}
	
	public static String notificationType(String id, NotificationsClientConfiguration config) {
		return notificationTypes(config) + "/" + id;
	}

	public static String count(NotificationsClientConfiguration config) {
		return api(config) + "/count";
	}

	public static String preferences(NotificationsClientConfiguration config) {
		return api(config) + "/preference";
	}

	public static String api(NotificationsClientConfiguration config) {
		return config.getServer() + "/api";
	}
	
	public static String templates(NotificationsClientConfiguration config) {
		return api(config) + "/template";
	}
	
	public static String template(String id, NotificationsClientConfiguration config) {
		return templates(config) + "/" + id;
	}

	public static String jobs(NotificationsClientConfiguration config) {
		return api(config) + "/job";
	}
	
	public static String job(String id, NotificationsClientConfiguration config) {
		return jobs(config) + "/" + id;
	}

	public static String error(NotificationsClientConfiguration config) {
		return api(config) + "/error";
	}
}
