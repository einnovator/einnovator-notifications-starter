package org.einnovator.notifications.client.web;

import java.security.Principal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.einnovator.notifications.client.manager.NotificationManager;
import org.einnovator.notifications.client.model.Notification;
import org.einnovator.notifications.client.modelx.NotificationFilter;
import org.einnovator.notifications.client.modelx.NotificationOptions;
import org.einnovator.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class NotificationRestController {

	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private NotificationManager manager;
	
	@Autowired
	public NotificationRestController() {
	}

	public NotificationRestController(NotificationManager manager) {
		this.manager =  manager;
	}

	@GetMapping("/api/notification")
	public ResponseEntity<Page<Notification>> notification(NotificationFilter filter, Principal principal) {
		logger.debug("notification:" + principal);
		if (principal==null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		Pageable pageable = null;
		Page<Notification> notifications = manager.listNotifications(filter, pageable);
		logger.debug("notification: #" + PageUtil.toString(notifications) + " " + principal.getName());
		return ResponseEntity.ok(notifications);
    }
	
	@DeleteMapping("/api/notification/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id, NotificationOptions options, Principal principal) {
		if (principal==null) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		logger.debug("delete:" + id + " " + options + " " + principal.getName());
		manager.deleteNotification(id, options);
		return ResponseEntity.noContent().build();
	}
	
}
