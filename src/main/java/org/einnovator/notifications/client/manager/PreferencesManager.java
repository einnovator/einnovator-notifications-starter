package org.einnovator.notifications.client.manager;

import java.util.Map;

import javax.servlet.http.HttpSession;

public interface PreferencesManager {

	String getValue(String key, HttpSession session);

	void setValue(String key, String value, HttpSession session);
	
	void remove(String key, HttpSession session);

	Map<String, String> getAll(HttpSession session);

}
