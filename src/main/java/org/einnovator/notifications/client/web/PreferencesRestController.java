package org.einnovator.notifications.client.web;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.einnovator.notifications.client.manager.PreferencesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/api/preference")
public class PreferencesRestController extends ControllerBase {

	@Autowired
	protected PreferencesManager manager;

	@PutMapping @GetMapping
	@ResponseBody
	public ResponseEntity<Object> getPreference(String key,
			Principal principal,  HttpServletResponse response, HttpSession session) {
		Object value = manager.getValue(session, key);
		return ok(value, "getPreference", response, key, value);
	}
	
	@PostMapping @PutMapping
	@ResponseBody
	public ResponseEntity<Void> setPreference(String key, @RequestParam(required = false) Object value,
			 Principal principal, HttpServletResponse response, HttpSession session) {
		manager.setValue(session, key, value);
		return nocontent("setPreference", response, key, value);
	}

	
	@DeleteMapping
	@ResponseBody
	public ResponseEntity<Void> removePreference(String key, 
		Principal principal, HttpServletResponse response, HttpSession session) {
		manager.remove(key, session);
		return nocontent("removePreference", response, key);
	}
	
}