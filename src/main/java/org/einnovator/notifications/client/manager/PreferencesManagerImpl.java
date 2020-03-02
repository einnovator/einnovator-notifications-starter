package org.einnovator.notifications.client.manager;


import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.einnovator.notifications.client.NotificationsClient;
import org.einnovator.notifications.client.amqp.NotificationListener;
import org.einnovator.notifications.client.config.NotificationsClientConfiguration;
import org.einnovator.notifications.client.config.NotificationsClientContext;
import org.einnovator.notifications.client.model.Event;
import org.einnovator.notifications.client.model.Notification;
import org.einnovator.notifications.client.model.Preference;
import org.einnovator.notifications.client.model.PrincipalDetails;
import org.einnovator.notifications.client.model.ValuePreference;
import org.einnovator.notifications.client.modelx.PreferenceFilter;
import org.einnovator.util.security.SecurityUtil;

public class PreferencesManagerImpl extends ManagerBase implements PreferencesManager, NotificationListener {
	
	public static final String CACHE_PREFERENCES = "Preferences";
	private static final String PREFERENCE_PREFIX = "Preference.";

	public static final String OP_SET = "set";
	public static final String OP_GET = "set";
	public static final String OP_HSET = "hset";
	public static final String OP_HGET = "hget";
	public static final String OP_LHSET = "lhset";


	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private NotificationsClient client;

	@Autowired
	private NotificationsClientConfiguration config;

	private CacheManager cacheManager;

	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	public PreferencesManagerImpl(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public PreferencesManagerImpl(NotificationsClient client, CacheManager cacheManager) {
		this.client = client;
		this.cacheManager = cacheManager;
	}

	@Override
	public Object getValue(HttpSession session, String key) {
		return getValue(session, key, (String)null);
	}

	@Override
	public void setValue(HttpSession session, String key, Object value) {
		operation(session, key, value, (String)null);
	}

	@SuppressWarnings("unchecked")
	public Object getValue(HttpSession session, String key, String op, Object... params) {
		Object value = getValueForPrincipal(session, key);

		if (!StringUtils.hasText(op) || OP_GET.equalsIgnoreCase(op)) {
			return value;
		}
		if (value==null) {
			return null;
		}

		if (OP_HGET.equalsIgnoreCase(op)) {
			if (!(value instanceof Map)) {
				throw new RuntimeException("Invalid Operation: " + op + " on value of type: " + value.getClass().getSimpleName());
			}
			if (params.length!=1) {
				throw new IllegalArgumentException("Expect 1 parameter for operation: " + op + " found:" + params.length);
			}
			Object hkey = params[0];
			if (hkey==null || !(hkey instanceof String)) {
				throw new IllegalArgumentException("Expect string key for operation: " + op + " as first parameter, found: " + hkey==null ? null : hkey.getClass().getSimpleName());				
			}
			return ((Map<String, Object>)value).get(key);
		}
		return null;
	}

	@Override
	public void operation(HttpSession session, String key, Object value, String op, Object... params) {
		ValuePreference pref = makePreference(key, value, op, params);
		value = apply(pref);
		if (session!=null) {
			session.setAttribute(makeAttributeName(key), value);				
		}
		if (config.getEnabled()!=null && Boolean.FALSE.equals(config.getEnabled())) {
			return;
		}
		Event event = makeEvent(pref);
		client.publishEvent(event, null);
	}

	protected String makeAttributeName(String key) {
		return PREFERENCE_PREFIX + key;
	}

	protected String makeKeyFromAttributeName(String name) {
		return name.substring(PREFERENCE_PREFIX.length());
	}

	@Override
	public void remove(String key, HttpSession session) {
		if (session!=null) {
			session.removeAttribute(makeAttributeName(key));
		}
	}
	
	public Object apply(ValuePreference pref) {
		String username = SecurityUtil.getPrincipalName();
		if (username==null) {
			return null;
		}
		return apply(pref, username);
	}

	public Object getValueForPrincipal(String key) {
		return getValueForUser(SecurityUtil.getPrincipalName(), key);
	}

	public Object getValueForPrincipal(HttpSession session, String key) {
		return getValueForUser(SecurityUtil.getPrincipalName(), session, key);
	}

	public Map<String, Object> getAllValuesForUser(String username, NotificationsClientContext context) {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = getCacheValueForUser(Map.class, getPreferencesCache(), username);
		if (map==null) {
			try {
				if (config.getEnabled()!=null && Boolean.FALSE.equals(config.getEnabled())) {
					return null;
				}
				Map<String, Preference> prefs = client.getPreferences((PreferenceFilter)new PreferenceFilter().withRunAs(username), context);
				map = getAllValues(prefs);		
				 if (map!=null) {
					putCacheValueForUser(map, getPreferencesCache(), username);
				 }
			} catch (RuntimeException e) {
				logger.error("getAllValuesForUser: failed to intialize preferences:" + username + " " + e);
			}
			if (map==null) {
				map = new LinkedHashMap<>();
				putCacheValueForUser(map, getPreferencesCache(), username);			
			}
		}
		return map;
	}
	
	private Map<String, Object> getAllValues(Map<String, Preference> prefs) {
		Map<String, Object> values = new LinkedHashMap<>();
		for (Map.Entry<String, Preference> e: prefs.entrySet()) {
			if (!(e.getValue() instanceof ValuePreference)) {
				continue;
			}
			ValuePreference pref = (ValuePreference)e.getValue();
			values.put(pref.getKey(), pref.getValue());
		}
		return values;
	}
	
	public Object getValueForUser(String username, HttpSession session, String key) {
		Object value = getValueForUser(username, key);
		if (value==null && session!=null) {
			value = session.getAttribute(makeAttributeName(key));
		}
		return value;
	}

	public Object getValueForUser(String username, String key) {
		Map<String, Object> map = getAllValuesForUser(username, null);
		Object value = map!=null ? map.get(key) : null;
		logger.debug("getValueForUser:" + username + " " + key + " " + value + " " + map);
		return value;
	}

	public Object setValueForUser(String username, String key, Object value) {
		Map<String, Object> map = getAllValuesForUser(username, null);
		if (map==null) {
			map = new LinkedHashMap<>();
			putCacheValueForUser(map, getPreferencesCache(), username);			
		}
		map.put(key, value);
		logger.debug("setValueForUser:" + username + " " + key + " " + value + " " + map);
		return value;
	}

	public Object apply(ValuePreference pref, String username) {
		Object value = getValueForUser(username, pref.getKey());
		Object value2 = apply(value, pref);
		setValueForUser(username, pref.getKey(), value2);
		logger.info("apply: " + username + " " + value + " " + pref + " " + value2);
		return value2;
	}
	
	@SuppressWarnings("unchecked")
	public Object apply(Object value, ValuePreference pref) {
		if (!StringUtils.hasText(pref.getOp()) || OP_SET.equalsIgnoreCase(pref.getOp())) {
			return pref.getValue();
		}
		if (OP_LHSET.equalsIgnoreCase(pref.getOp())) {
			if (value==null) {
				value = new LinkedHashMap<>();
			} else if (!(value instanceof Map)) {
				throw new RuntimeException("Invalid Operation: " + pref.getOp() + " on value of type: " + value.getClass().getSimpleName());
			}
			Object hkey = pref.getParam(0);
			Object hvalue = pref.getParam(1);
			if (hkey==null || !(hkey instanceof String)) {
				throw new IllegalArgumentException("Expect string key for operation: " + pref.getOp() + " as first parameter, found: " + hkey==null ? null : hkey.getClass().getSimpleName());				
			}
			((Map<String,Object>)value).put((String)hkey, hvalue);
			return value;
		}
		return pref.getValue();
	}
	
	@Override
	public Map<String, String> getAll(HttpSession session) {
		if (session==null) {
			return null;
		}
		Map<String, String> map = new LinkedHashMap<>();
		Enumeration<String> names = session.getAttributeNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			if (name.startsWith(PREFERENCE_PREFIX)) {
				Object value = session.getAttribute(name);
				if (value instanceof String) {
					map.put(makeKeyFromAttributeName(name), (String)value);					
				}
			}
		}
		return map;
	}

	protected Event makeEvent(ValuePreference pref) {
		return new Event(pref, PrincipalDetails.makeUserPrincipal());
	}

	protected ValuePreference makePreference(String key, Object value, String op, Object... params) {
		ValuePreference pref = new ValuePreference();
		pref.setKey(key);
		pref.setValue(value);
		pref.setOp(op);
		if (params.length>0) {
			pref.setParams(Arrays.asList(params));			
		}
		return pref;
	}

	@Override
	public void onNotification(Notification notification) {
		if (notification==null) {
			return;
		}
		Preference pref = notification.getPreference();
		if (!(pref instanceof ValuePreference)) {
			return;
		}
		logger.debug("onNotification: " + pref);

		PrincipalDetails principalx = notification.getPrincipal();
		if (principalx==null || !StringUtils.hasText(principalx.getId())) {
			return;
		}
		try {
			apply((ValuePreference)pref, principalx.getId());
		} catch (RuntimeException e) {
			logger.error("onNotification: " + e);
			return;
		}
		logger.debug("onNotification: " + principalx.getName() + " " + pref);
		eventPublisher.publishEvent(new PayloadApplicationEvent<Preference>(this, pref));
	}

	
	@Override
	public void evictCachesForUser(String userId) {
		Cache cache = getPreferencesCache();
		if (cache!=null) {
			cache.evict(userId);
		}
	}
	
	@Override
	public void clearCaches() {
		Cache cache = getPreferencesCache();
		if (cache!=null) {
			cache.clear();
		}
	}


	@Override
	public Cache getPreferencesCache() {
		Cache cache = cacheManager.getCache(PreferencesManagerImpl.CACHE_PREFERENCES);
		if (cache==null) {
			logger.warn("getPreferencesCache: cache not found:" + PreferencesManagerImpl.CACHE_PREFERENCES);
		}
		return cache;
	}

}
