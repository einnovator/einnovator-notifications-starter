package org.einnovator.notifications.client.web;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.einnovator.notifications.client.manager.PreferencesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/api/preference")
public class PreferencesRestController {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	protected PreferencesManager manager;

	@PutMapping @GetMapping
	@ResponseBody
	public ResponseEntity<String> getPreference(String key, Principal principal, HttpSession session) {
		if (principal == null) {
			logger.error("getPreference: " + format(HttpStatus.UNAUTHORIZED));
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		}
		String value = manager.getValue(key, session);
		logger.info("getPreference: " + key + " " + value);
		ResponseEntity<String> result = new ResponseEntity<String>(value, HttpStatus.OK);
		return result;
	}
	
	@PostMapping @PutMapping
	@ResponseBody
	public ResponseEntity<Void> setPreference(String key, @RequestParam(required = false) String value,
			 Principal principal, HttpSession session) {
		if (principal == null) {
			logger.error("createUser: " + format(HttpStatus.UNAUTHORIZED));
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		manager.setValue(key, value, session);
		logger.info("setPreference: " + key + " " + value);
		ResponseEntity<Void> result = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return result;
	}

	
	@DeleteMapping
	@ResponseBody
	public ResponseEntity<Void> removePreference(String key,  Principal principal, HttpSession session) {
		if (principal == null) {
			logger.error("removePreference: " + format(HttpStatus.UNAUTHORIZED));
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		logger.info("removePreference: " + key);
		manager.remove(key, session);
		ResponseEntity<Void> result = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return result;
	}
	

	protected String format(HttpStatus status) {
		return status + " " + status.getReasonPhrase();
	}
}