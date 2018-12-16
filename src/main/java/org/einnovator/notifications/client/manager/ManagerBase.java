package org.einnovator.notifications.client.manager;

import java.security.Principal;

import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class ManagerBase {

	public String getPrincipalName() {
		Principal principal = getPrincipal();
		return principal!=null ? principal.getName() : null;
	}

	public Principal getPrincipal() {
		Authentication authentication = getAuthentication();
		if (authentication != null) {
			if (authentication instanceof Principal) {
				return authentication;
			}
			if (authentication.getPrincipal() instanceof Principal) {
				return (Principal) authentication.getPrincipal();
			}
		}
		return null;
	}

	public Authentication getAuthentication() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			return context.getAuthentication();
		}
		return null;
	}


	@SuppressWarnings("unchecked")
	protected <T> T getCacheValueForPrincipal(Class<T> type, Cache cache, Object... keys) {
		if (cache==null) {
			return null;
		}
		Principal principal = getPrincipal();
		if (principal==null) {
			return null;
		}
		String key = makeKeyForPrincipal(keys);
		ValueWrapper value = cache.get(principal.getName() + key);
		if (value!=null) {
			return (T)value.get();
		}
		return null;
	}

	protected <T> T putCacheValueForPrincipal(T value, Cache cache, Object... keys) {
		if (cache==null) {
			return value;
		}
		Principal principal = getPrincipal();
		if (principal==null) {
			return value;
		}
		String key = makeKeyForPrincipal(keys);
		cache.put(principal.getName() + key, value);
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
		Principal principal = getPrincipal();
		if (principal==null) {
			return null;
		}
		String key = makeKey(keys);
		return key!=null ? principal.getName() + ":" + key : principal.getName();
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
