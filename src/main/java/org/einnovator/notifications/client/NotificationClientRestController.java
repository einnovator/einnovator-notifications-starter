package org.einnovator.notifications.client;

import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.einnovator.notifications.client.model.Notification;

public class NotificationClientRestController {

	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private NotificationsClient client;
	
	@Autowired
	public NotificationClientRestController() {
	}

	public NotificationClientRestController(NotificationsClient client) {
		this.client =  client;
	}

	@GetMapping("/api/notification")
	public ResponseEntity<Notification[]> notification(Principal principal) {
		logger.info("notification:" + principal);
		if (principal==null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		String username = principal.getName();
	
		List<Notification> notifications = client.getUserNotifications(username);
		logger.info("notification: #" + notifications.size());

		return ResponseEntity.ok(notifications.toArray(new Notification[notifications.size()]));
    }
	
	@DeleteMapping("/api/notification/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id, Principal principal) {
		if (principal==null) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		String username = principal.getName();
		logger.info("delete:" + username + " " + id);
		client.deleteUserNotification(username, id);
		return ResponseEntity.noContent().build();
	}
	
}
