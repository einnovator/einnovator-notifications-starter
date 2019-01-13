package org.einnovator.notifications.client.manager;

import org.einnovator.util.SecurityUtil;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;

public class ManagerBase {

	protected <T> T getCacheValueForPrincipal(Class<T> type, Cache cache, Object... keys) {
		return getCacheValueForUser(SecurityUtil.getPrincipalName(), type, cache, keys);
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T getCacheValueForUser(String username, Class<T> type, Cache cache, Object... keys) {
		if (cache==null || username==null) {
			return null;
		}
		String key = makeKeyForUser(username, keys);
		ValueWrapper value = cache.get(key);
		if (value!=null) {
			return (T)value.get();
		}
		return null;
	}

		
	protected <T> T putCacheValueForPrincipal(T value, Cache cache, Object... keys) {
		return putCacheValueForUser(SecurityUtil.getPrincipalName(), value, cache, keys);
	}
	
	protected <T> T putCacheValueForUser(String username, T value, Cache cache, Object... keys) {
		if (cache==null || username==null) {
			return value;
		}
		String key = makeKeyForUser(username, keys);
		cache.put(key, value);
		return value;
	}


	@SuppressWarnings("unchecked")
	protected <T> T getCacheValue(Class<T> type, Cache cache, Object... keys) {
		if (cache==null) {
			return null;
		}
		String key = makeKey(keys);
		if (key!=null) {
			ValueWrapper value = cache.get(key);
			if (value!=null) {
				return (T)value.get();
			}
		}
		return null;
	}

	protected <T> T putCacheValue(T value, Cache cache, Object... keys) {
		if (cache==null) {
			return value;
		}
		String key = makeKey(keys);
		if (key!=null) {
			cache.put(key, value);
		}
		return value;
	}

	protected String makeKeyForPrincipal(Object... keys) {
		return makeKeyForUser(SecurityUtil.getPrincipalName(), keys);
	}
		

	protected String makeKeyForUser(String username, Object... keys) {
		if (username==null) {
			return null;
		}
		String key = makeKey(keys);
		return key!=null ? username + ":" + key : username;		
	}

	protected String makeKey(Object... keys) {
		StringBuilder sb = new StringBuilder();
		for (Object key: keys) {
			if (key!=null) {
				if (sb.length()>0) {
					sb.append(":");						
				}
				if (key instanceof String) {
					sb.append(key);
				} else {
					sb.append(key.hashCode());
				}				
			}
		}
		return sb.toString();
	}


}
