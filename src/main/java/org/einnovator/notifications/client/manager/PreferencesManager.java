package org.einnovator.notifications.client.manager;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.einnovator.notifications.client.model.Preference;
import org.springframework.cache.Cache;

/**
 * High-level API for user preferences.
 * 
 * @see Preference
 * @author support@einnovator.org
 *
 */
public interface PreferencesManager {

	//
	// Preferences
	//
	
	/**
	 * Get value of preference with specified key (for the principal).
	 * 
	 * Auto-fetches remote preferences setting on first call if preference map not found in session.
	 * 
	 * @param session {@code HttpSession} session where preference map is fetched/stored
	 * @param key the preference key
	 * @return the preference value
	 */
	Object getValue(HttpSession session, String key);
	
	/**
	 * Set value of preference with specified key (for the principal).
	 * 
	 * Publishes preference update to remote server (asynchronously if transport available).
	 * 
	 * @param session {@code HttpSession} session where preference map is fetched/stored
	 * @param key the preference key
	 * @param value the preference value
	 */
	void setValue(HttpSession session, String key, Object value);

	/**
	 * Get value of preference with specified key by applying operation. (for the principal)
	 * 
	 * Auto-fetches remote preferences setting on first call if preference map not found in session.
	 * 
	 * @param session {@code HttpSession} session where preference map is fetched/stored
	 * @param key the preference key
	 * @param op the operation to apply to the preference value
	 * @param params a variadic array of parameters for the operation 
	 * @return the preference value
	 */
	Object getValue(HttpSession session, String key, String op, Object... params);

	/**
	 * Update value of preference with specified key by applying an operation to the value (for the principal).
	 * 
	 * Publishes preference update to remote server (asynchronously if transport available).
	 * 
	 * @param session {@code HttpSession} session where preference map is fetched/stored
	 * @param key the preference key
	 * @param value of the preference key (or null to use current value)
	 * @param op the operation to apply to the preference value
	 * @param params a variadic array of parameters for the operation 
	 * @param value the preference value
	 */
	void operation(HttpSession session, String key, Object value, String op, Object... params);

	/**
	 * Remove preference  (for the principal).
	 * 
	 * @param key the preference key
	 * @param session {@code HttpSession} session where preference map is fetched/stored
	 */
	void remove(String key, HttpSession session);

	/**
	 * Get map with all preferences values (for the principal).
	 * 
	 * Fetches from remote server if preference map not found in session.
	 * 
	 * @param session {@code HttpSession} session where preference map is fetched/stored
	 * @return the preference map
	 */
	Map<String, String> getAll(HttpSession session);
	

	//
	// Caching
	//
	
	/**
	 * Get the cache for {@code Preference}s.
	 * 
	 * @return the {@code Cache}
	 */
	Cache getPreferencesCache();
	
	/**
	 * Clear the cache for {@code Preference}s.
	 * 
	 */
	void clearCaches();
	
	/**
	 * Evict cache entries for the specified {@code User}.
	 * 
	 * @param userId the username
	 */
	void evictCachesForUser(String userId);

}

