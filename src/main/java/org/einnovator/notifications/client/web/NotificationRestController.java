package org.einnovator.notifications.client.web;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.einnovator.notifications.client.manager.NotificationManager;
import org.einnovator.notifications.client.model.Notification;
import org.einnovator.notifications.client.modelx.NotificationFilter;
import org.einnovator.notifications.client.modelx.NotificationOptions;
import org.einnovator.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class NotificationRestController extends ControllerBase {
	
	@Autowired
	private NotificationManager manager;
	
	@Autowired
	public NotificationRestController() {
	}

	public NotificationRestController(NotificationManager manager) {
		this.manager =  manager;
	}

	@GetMapping("/api/notification")
	public ResponseEntity<Page<Notification>> notifications(NotificationFilter filter,
			 Principal principal, HttpServletResponse response, HttpSession session) {
		Pageable pageable = null;
		Page<Notification> notifications = manager.listNotifications(filter, pageable);
		return ok(notifications, "notifications", response, PageUtil.toString(notifications), filter, principal.getName());
    }
	
	@DeleteMapping("/api/notification/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id, NotificationOptions options, 
			 Principal principal, HttpServletResponse response, HttpSession session) {
		manager.deleteNotification(id, options);
		return nocontent("delete", response, id, options, principal.getName());
	}
	
}
