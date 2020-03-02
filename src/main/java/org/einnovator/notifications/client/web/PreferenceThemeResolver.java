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


	//
	// Constructor
	//
	
	/**
	 * Create instance of {@code PreferenceThemeResolver}.
	 *
	 */
	@Autowired
	public PreferenceThemeResolver() {
		
	}
	
	/**
	 * Create instance of {@code PreferenceThemeResolver}.
	 *
	 * @param fallback fallback {@code ThemeResolver}
	 */
	public PreferenceThemeResolver(ThemeResolver fallback) {
		this.fallback  = fallback;
	}

	/**
	 * Create instance of {@code PreferenceThemeResolver}.
	 *
	 * @param fallback fallback {@code ThemeResolver}
	 * @param defaultThemeName default theme
	 */
	public PreferenceThemeResolver(ThemeResolver fallback, String defaultThemeName) {
		this.fallback  = fallback;
		this.defaultThemeName = defaultThemeName;
	}


	//
	// Setter/Getters
	//

	/**
	 * Get the value of property {@code manager}.
	 *
	 * @return the manager
	 */
	public PreferencesManager getManager() {
		return manager;
	}

	/**
	 * Set the value of property {@code manager}.
	 *
	 * @param manager the value of property manager
	 */
	public void setManager(PreferencesManager manager) {
		this.manager = manager;
	}

	/**
	 * Get the value of property {@code fallback}.
	 *
	 * @return the fallback
	 */
	public ThemeResolver getFallback() {
		return fallback;
	}

	/**
	 * Set the value of property {@code fallback}.
	 *
	 * @param fallback the value of property fallback
	 */
	public void setFallback(ThemeResolver fallback) {
		this.fallback = fallback;
	}

	/**
	 * Get the value of property {@code key}.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Set the value of property {@code key}.
	 *
	 * @param key the value of property key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Get the value of property {@code defaultThemeName}.
	 *
	 * @return the defaultThemeName
	 */
	public String getDefaultThemeName() {
		return defaultThemeName;
	}

	/**
	 * Set the value of property {@code defaultThemeName}.
	 *
	 * @param defaultThemeName the value of property defaultThemeName
	 */
	public void setDefaultThemeName(String defaultThemeName) {
		this.defaultThemeName = defaultThemeName;
	}

	//
	// ThemeResolver
	//
	
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

