package org.einnovator.notifications.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

import org.einnovator.notifications.client.amqp.AmqpConfig;
import org.einnovator.notifications.client.manager.PreferencesManager;
import org.einnovator.notifications.client.manager.PreferencesManagerImpl;
import org.einnovator.notifications.client.web.PreferencesRestController;



@Configuration
@EnableConfigurationProperties(NotificationsConfiguration.class)
@Import(AmqpConfig.class)
public class NotificationsClientConfig {
	
	@Autowired
	private NotificationsConfiguration config;
	
	@Autowired
	private OAuth2ClientContext oauth2ClientContext;

	@Autowired
	private OAuth2ProtectedResourceDetails resourceDetails;

	
	private ClientHttpRequestFactory clientHttpRequestFactory() {
		ConnectionConfiguration connectionConf = config.getConnection();
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		
		if(connectionConf.getTimeout() != null) {
			clientHttpRequestFactory.setConnectTimeout(connectionConf.getTimeout());
		}
		if(connectionConf.getRequestTimeout() != null) {
		clientHttpRequestFactory.setConnectionRequestTimeout(connectionConf.getRequestTimeout());
		}
		if(connectionConf.getReadTimeout() != null) {
		clientHttpRequestFactory.setReadTimeout(connectionConf.getReadTimeout());
		}
		return clientHttpRequestFactory;
	}


	@Bean
	public OAuth2RestTemplate notificationsRestTemplate() {
		OAuth2RestTemplate template = new OAuth2RestTemplate(resourceDetails, oauth2ClientContext);			
		template.setRequestFactory(clientHttpRequestFactory());
		return template;
	}

	
	@Bean
	public NotificationsClient notificationsClient() {
		return new NotificationsClient();
	}
	
	@Bean
	public PreferencesManager preferencesManager() {
		return new PreferencesManagerImpl();
	}
	
	@Bean
	public PreferencesRestController preferencesRestController() {
		return new PreferencesRestController();
	}
}
