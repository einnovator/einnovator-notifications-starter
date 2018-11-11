package org.einnovator.notifications.client.manager;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class PreferencesManagerImpl implements PreferencesManager {
	
	public static final String PREFERENCE_PREFIX = "PREFERENCE:";
	
	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(this.getClass());

	public PreferencesManagerImpl() {
	}
	
	@Override
	public String getValue(String key, HttpSession session) {
		if (session==null) {
			return null;
		}
		String value = (String)session.getAttribute(makeName(key));
		return value;
	}

	@Override
	public void setValue(String key, String value, HttpSession session) {
		if (session!=null) {
			session.setAttribute(makeName(key), value);
		}
	}
	
	protected String makeName(String key) {
		return PREFERENCE_PREFIX + key.toUpperCase();
	}

	protected String makeKey(String name) {
		return name.substring(PREFERENCE_PREFIX.length()).toLowerCase();
	}

	@Override
	public void remove(String key, HttpSession session) {
		if (session!=null) {
			session.removeAttribute(makeName(key));			
		}
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
					map.put(makeKey(name), (String)value);					
				}
			}
		}
		return map;
	}

}
