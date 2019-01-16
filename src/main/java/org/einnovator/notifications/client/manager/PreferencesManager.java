package org.einnovator.notifications.client.manager;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.cache.Cache;

public interface PreferencesManager {

	Object getValue(HttpSession session, String key);
	Object getValue(HttpSession session, String key, String op, Object... params);

	void setValue(HttpSession session, String key, Object value);
	void operation(HttpSession session, String key, Object value, String op, Object... params);

	void remove(String key, HttpSession session);

	Map<String, String> getAll(HttpSession session);

	Cache getPreferencesCache();
	void clearCaches();
	void evictCachesForUser(String userId);

}

