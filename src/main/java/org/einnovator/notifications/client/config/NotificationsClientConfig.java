package org.einnovator.notifications.client.config;

import javax.annotation.PostConstruct;

import org.einnovator.notifications.client.NotificationEventPublisher;
import org.einnovator.notifications.client.NotificationsClient;
import org.einnovator.notifications.client.SimpleEventFactory;
import org.einnovator.notifications.client.amqp.AmqpConfig;
import org.einnovator.notifications.client.manager.NotificationManager;
import org.einnovator.notifications.client.manager.NotificationManagerImpl;
import org.einnovator.notifications.client.manager.NotificationsUtil;
import org.einnovator.notifications.client.manager.PreferencesManager;
import org.einnovator.notifications.client.manager.PreferencesManagerImpl;
import org.einnovator.notifications.client.manager.TemplateManager;
import org.einnovator.notifications.client.manager.TemplateManagerImpl;
import org.einnovator.notifications.client.web.PreferenceThemeResolver;
import org.einnovator.notifications.client.web.PreferencesRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.theme.CookieThemeResolver;


@Configuration
@EnableConfigurationProperties(NotificationsClientConfiguration.class)
@Import(AmqpConfig.class)
@Profile("!sso_exclude")
public class NotificationsClientConfig {
	
	@Autowired
	private NotificationsClientConfiguration config;
	
	@Autowired
	private OAuth2ClientContext oauth2ClientContext;

	@Autowired
	private OAuth2ProtectedResourceDetails resourceDetails;

	@Value("${ui.defaultTheme:}")
	private String defaultTheme;

	@Autowired
	private Environment env;
	
	@Bean
	public OAuth2RestTemplate notificationsRestTemplate() {
		OAuth2RestTemplate template = new OAuth2RestTemplate(resourceDetails, oauth2ClientContext);			
		template.setRequestFactory(config.getConnection().makeClientHttpRequestFactory());
		return template;
	}
	
	@Bean
	public NotificationsClient notificationsClient() {
		return new NotificationsClient();
	}

	@Bean
	public NotificationManager notificationManager(CacheManager cacheManager) {
		return new NotificationManagerImpl(cacheManager);
	}
	
	@Bean
	public TemplateManager templateManager(CacheManager cacheManager) {
		return new TemplateManagerImpl(cacheManager);
	}
	
	@Bean
	public PreferencesManager preferencesManager(CacheManager cacheManager) {
		return new PreferencesManagerImpl(cacheManager);
	}
	
	@Bean
	public PreferencesRestController preferencesRestController() {
		return new PreferencesRestController();
	}
	
	@Bean
	public ThemeResolver themeResolver() {
		return new PreferenceThemeResolver(fallbackThemeResolver(), defaultTheme);
	}

	public CookieThemeResolver fallbackThemeResolver() {
		CookieThemeResolver themeResolver = new CookieThemeResolver();
		if (StringUtils.hasText(defaultTheme)) {
			themeResolver.setDefaultThemeName(defaultTheme);
		}
		return themeResolver;
	}

	@Bean
	public NotificationEventPublisher notificationEventPublisher() {
		return new NotificationEventPublisher();
	}

	@Bean
	public SimpleEventFactory simpleEventFactory() {
		return new SimpleEventFactory();
	}

	@PostConstruct
	public void init() {
		Boolean enabled = env.getProperty(NotificationsUtil.NOTIFICATIONS_ENABLED, Boolean.class);
		if (enabled!=null && !Boolean.TRUE.equals(enabled)) {
			config.setEnabled(false);
		}
		Boolean publish = env.getProperty(NotificationsUtil.NOTIFICATIONS_PUBLISH_ENABLED, Boolean.class);
		if (publish!=null && !Boolean.TRUE.equals(publish)) {
			config.setPublishEnabled(false);
		}

	}
}
