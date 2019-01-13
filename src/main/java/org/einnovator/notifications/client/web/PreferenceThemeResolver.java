package org.einnovator.notifications.client.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.einnovator.notifications.client.manager.PreferencesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ThemeResolver;



/**
 * {@link ThemeResolver} implementation that uses user preferences
 * , with a fallback to the default theme.
 *
 * <p>Custom controllers can thus override the user's theme by calling
 * {@code setThemeName}, e.g. responding to a certain theme change request.
 *
 * @author Jorge Simao
 * @since 17.12.2018
 * @see #setThemeName
 */
public class PreferenceThemeResolver implements ThemeResolver {

	@Autowired
	private PreferencesManager manager;
	
	private ThemeResolver fallback;
	
	public final static String ORIGINAL_DEFAULT_THEME_NAME = "theme";

	/**
	 * Name of the request attribute that holds the theme name. Only used
	 * for overriding a cookie value if the theme has been changed in the
	 * course of the current request! Use RequestContext.getTheme() to
	 * retrieve the current theme in controllers or views.
	 * @see org.springframework.web.servlet.support.RequestContext#getTheme
	 */
	public static final String THEME_REQUEST_ATTRIBUTE_NAME = PreferenceThemeResolver.class.getName() + ".THEME";

	public static final String THEME_KEY = "theme";

	private String key = THEME_KEY;

	private String defaultThemeName = ORIGINAL_DEFAULT_THEME_NAME;


	@Autowired
	public PreferenceThemeResolver() {
		
	}
	
	public PreferenceThemeResolver(ThemeResolver fallback) {
		this.fallback  = fallback;
	}

	public PreferenceThemeResolver(ThemeResolver fallback, String defaultThemeName) {
		this.fallback  = fallback;
		this.defaultThemeName = defaultThemeName;
	}


	/**
	 * Set the name of the default theme.
	 */
	public void setDefaultThemeName(String defaultThemeName) {
		this.defaultThemeName = defaultThemeName;
	}

	/**
	 * Return the name of the default theme.
	 */
	public String getDefaultThemeName() {
		return defaultThemeName;
	}


	public PreferencesManager getManager() {
		return manager;
	}

	public void setManager(PreferencesManager manager) {
		this.manager = manager;
	}

	public ThemeResolver getFallback() {
		return fallback;
	}

	public void setFallback(ThemeResolver fallback) {
		this.fallback = fallback;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String resolveThemeName(HttpServletRequest request) {
		// Check request for preparsed or preset theme.
		String themeName = (String) request.getAttribute(THEME_REQUEST_ATTRIBUTE_NAME);
		if (themeName != null) {
			return themeName;
		}
		Object theme = manager.getValue(request.getSession(), key);
		if (theme instanceof String) {
			themeName = (String)theme;
		}
		// Fall back to default theme.
		if (themeName == null) {
			if (fallback!=null) {
				themeName = fallback.resolveThemeName(request);
			}
			if (themeName==null) {
				themeName = getDefaultThemeName();				
			}
		}
		request.setAttribute(THEME_REQUEST_ATTRIBUTE_NAME, themeName);
		return themeName;
	}

	@Override
	public void setThemeName(HttpServletRequest request, HttpServletResponse response, String themeName) {

		manager.setValue(request.getSession(), key, themeName);
		if (fallback!=null) {
			fallback.setThemeName(request, response, themeName);
		}
	}

}

