package org.einnovator.notifications.client;

import static org.einnovator.util.UriUtils.encodeId;
import static org.einnovator.util.UriUtils.makeURI;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.einnovator.notifications.client.config.NotificationsClientConfiguration;
import org.einnovator.notifications.client.config.NotificationsClientContext;
import org.einnovator.notifications.client.config.NotificationsEndpoints;
import org.einnovator.notifications.client.model.Action;
import org.einnovator.notifications.client.model.ErrorReport;
import org.einnovator.notifications.client.model.Event;
import org.einnovator.notifications.client.model.Medium;
import org.einnovator.notifications.client.model.Notification;
import org.einnovator.notifications.client.model.NotificationType;
import org.einnovator.notifications.client.model.NotificationsRegistration;
import org.einnovator.notifications.client.model.Preference;
import org.einnovator.notifications.client.model.Source;
import org.einnovator.notifications.client.model.Target;
import org.einnovator.notifications.client.model.Template;
import org.einnovator.notifications.client.modelx.NotificationFilter;
import org.einnovator.notifications.client.modelx.NotificationTypeFilter;
import org.einnovator.notifications.client.modelx.NotificationTypeOptions;
import org.einnovator.notifications.client.modelx.PreferenceFilter;
import org.einnovator.notifications.client.modelx.TemplateFilter;
import org.einnovator.notifications.client.modelx.TemplateOptions;
import org.einnovator.util.MappingUtils;
import org.einnovator.util.PageResult;
import org.einnovator.util.PageUtil;
import org.einnovator.util.StringUtil;
import org.einnovator.util.UriUtils;
import org.einnovator.util.model.Application;
import org.einnovator.util.script.EnvironmentVariableResolver;
import org.einnovator.util.script.TextTemplates;
import org.einnovator.util.security.ClientTokenProvider;
import org.einnovator.util.web.RequestOptions;
import org.einnovator.util.web.WebUtil;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;

/**
 * Client to the Notifications Hub service.
 * 
 * <p>Provide methods for all server endpoints and resource types. 
 * <p>Including: {@link Event}, {@link Notification}, {@link NotificationType}, and {@link Template}.
 * <p>Errors are propagated using Java runtime exceptions.
 * <p>For caching enabled "high-level" API, see Manager classes.
 * <p>{@code NotificationsClientConfiguration} specifies configuration details, including server URL and client credentials.
 * <p>Property {@link #getConfig()} provides the default {@code NotificationsClientConfiguration} to use.
 * <p>All API methods that invoke a server endpoint accept an <em>optional</em> tail parameter to connect to alternative server
 *  (e.g. for cover the less likely case where an application need to connect to multiple servers in different clusters).
 * <p>Internally, {@code NotificationsClient} uses a {@code OAuth2RestTemplate} to invoke a remote server.
 * <p>When setup as a <b>Spring Bean</b> both {@code NotificationsClientConfiguration} and {@code OAuth2RestTemplate} are auto-configured.
 * <p>Requests use a session-scoped  {@code OAuth2ClientContext} if running in a web-environment.
 * <p>Method {@link #register()} can be used to register custom application notification types with the server.
 * <p>This is automatically performed by if configuration property {@code sso.registration.roles.auto} is set to true.
 * <p>Registration is done using client credentials.
 * 
 * @see org.einnovator.notifications.client.manager.NotificationManager
 * @author support@einnovator.org
 *
 */
public class NotificationsClient implements NotificationOperationsHttp, NotificationOperationsAsync, NotificationOperationsAmqp {

	private final Log logger = LogFactory.getLog(getClass());

	public static final String TYPE_ID_SEPARATOR = ":";

	@Autowired
	private NotificationsClientConfiguration config;


	@Qualifier("notificationsRestTemplate")
	@Autowired
	private OAuth2RestTemplate restTemplate;

	@Autowired(required=false)
	private RabbitTemplate amqpTemplate;

	@Autowired(required=false)
	private ClientTokenProvider clientTokenProvider;

	@Autowired(required=false)
	private Application application;


	private TextTemplates engine;

	@Autowired
	private Environment env;
	
	private OAuth2ClientContext oauth2ClientContext0 = new DefaultOAuth2ClientContext();

	private OAuth2RestTemplate restTemplate0;

	private ClientHttpRequestFactory clientHttpRequestFactory;
	
	/**
	 * Create instance of {@code NotificationsClient}.
	 *
	 */
	@Autowired
	public NotificationsClient() {
	}

	/**
	 * Create instance of {@code NotificationsClient}.
	 *
	 * @param config the {@code NotificationsClientConfiguration}
	 */
	public NotificationsClient(NotificationsClientConfiguration config) {
		this.config = config;
	}
	/**
	 * Create instance of {@code NotificationsClient}.
	 *
	 * @param restTemplate the {@code OAuth2RestTemplate} to use
	 * @param config the {@code NotificationsClientConfiguration}
	 */
	public NotificationsClient(OAuth2RestTemplate restTemplate, NotificationsClientConfiguration config) {
		this.restTemplate = restTemplate;
		this.config = config;
	}
	
	/**
	 * PostConstruct initializer.
	 */
	@PostConstruct
	public void init() {
		 engine = new TextTemplates("{", "}", new EnvironmentVariableResolver(env));
	}
	
	//
	// Getters/Setters
	//

	/**
	 * Get the value of property {@code config}.
	 *
	 * @return the config
	 */
	public NotificationsClientConfiguration getConfig() {
		return config;
	}

	/**
	 * Set the value of property {@code config}.
	 *
	 * @param config the value of property config
	 */
	public void setConfig(NotificationsClientConfiguration config) {
		this.config = config;
	}

	/**
	 * Get the value of property {@code restTemplate}.
	 *
	 * @return the restTemplate
	 */
	public OAuth2RestTemplate getRestTemplate() {
		return restTemplate;
	}

	/**
	 * Set the value of property {@code restTemplate}.
	 *
	 * @param restTemplate the value of property restTemplate
	 */
	public void setRestTemplate(OAuth2RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * Get the value of property {@code amqpTemplate}.
	 *
	 * @return the amqpTemplate
	 */
	public RabbitTemplate getAmqpTemplate() {
		return amqpTemplate;
	}

	/**
	 * Set the value of property {@code amqpTemplate}.
	 *
	 * @param amqpTemplate the value of property amqpTemplate
	 */
	public void setAmqpTemplate(RabbitTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}

	/**
	 * Get the value of property {@code clientTokenProvider}.
	 *
	 * @return the clientTokenProvider
	 */
	public ClientTokenProvider getClientTokenProvider() {
		return clientTokenProvider;
	}

	/**
	 * Set the value of property {@code clientTokenProvider}.
	 *
	 * @param clientTokenProvider the value of property clientTokenProvider
	 */
	public void setClientTokenProvider(ClientTokenProvider clientTokenProvider) {
		this.clientTokenProvider = clientTokenProvider;
	}

	/**
	 * Get the value of property {@code application}.
	 *
	 * @return the application
	 */
	public Application getApplication() {
		return application;
	}

	/**
	 * Set the value of property {@code application}.
	 *
	 * @param application the value of property application
	 */
	public void setApplication(Application application) {
		this.application = application;
	}

	/**
	 * Get the value of property {@code engine}.
	 *
	 * @return the engine
	 */
	public TextTemplates getEngine() {
		return engine;
	}

	/**
	 * Set the value of property {@code engine}.
	 *
	 * @param engine the value of property engine
	 */
	public void setEngine(TextTemplates engine) {
		this.engine = engine;
	}

	/**
	 * Get the value of property {@code oauth2ClientContext0}.
	 *
	 * @return the oauth2ClientContext0
	 */
	public OAuth2ClientContext getOauth2ClientContext0() {
		return oauth2ClientContext0;
	}

	/**
	 * Set the value of property {@code oauth2ClientContext0}.
	 *
	 * @param oauth2ClientContext0 the value of property oauth2ClientContext0
	 */
	public void setOauth2ClientContext0(OAuth2ClientContext oauth2ClientContext0) {
		this.oauth2ClientContext0 = oauth2ClientContext0;
	}

	/**
	 * Get the value of property {@code restTemplate0}.
	 *
	 * @return the restTemplate0
	 */
	public OAuth2RestTemplate getRestTemplate0() {
		return restTemplate0;
	}

	/**
	 * Set the value of property {@code restTemplate0}.
	 *
	 * @param restTemplate0 the value of property restTemplate0
	 */
	public void setRestTemplate0(OAuth2RestTemplate restTemplate0) {
		this.restTemplate0 = restTemplate0;
	}

	/**
	 * Get the value of property {@code clientHttpRequestFactory}.
	 *
	 * @return the clientHttpRequestFactory
	 */
	public ClientHttpRequestFactory getClientHttpRequestFactory() {
		return clientHttpRequestFactory;
	}

	/**
	 * Set the value of property {@code clientHttpRequestFactory}.
	 *
	 * @param clientHttpRequestFactory the value of property clientHttpRequestFactory
	 */
	public void setClientHttpRequestFactory(ClientHttpRequestFactory clientHttpRequestFactory) {
		this.clientHttpRequestFactory = clientHttpRequestFactory;
	}
	
	
	//
	// Registration
	//
	
	
	/**
	 * Register application.
	 * 
	 * @param registration the {@code NotificationsRegistration} descriptor
	 */
	public void register(NotificationsRegistration registration) {
		OAuth2RestTemplate restTemplate = this.restTemplate;
		if (clientTokenProvider!=null) {
			clientTokenProvider.setupToken();
			restTemplate = clientTokenProvider.makeOAuth2RestTemplate();
		}
		register(registration, restTemplate);
	}


	/**
	 * Register application using specified {@code OAuth2RestTemplate}.
	 * 
	 * @param registration the {@code NotificationsRegistration} descriptor
	 * @param restTemplate the {@code OAuth2RestTemplate}
	 */
	public void register(NotificationsRegistration registration, OAuth2RestTemplate restTemplate) {
		URI uri = makeURI(NotificationsEndpoints.register(config));
		preprocess(registration);
		RequestEntity<NotificationsRegistration> request = RequestEntity.post(uri).body(registration);
		exchange(restTemplate, request, Void.class);
	}
	

	/**
	 * Register application default {@code Registration} and default server.
	 * 
	 */
	public void register() {
		NotificationsRegistration registration = config.getRegistration();
		register(registration);
	}

	/**
	 * Preprocess {@code NotificationsRegistration} before registration.
	 * 
	 * @param registration the {@code NotificationsRegistration}
	 * @return same {@code NotificationsRegistration}
	 */
	protected NotificationsRegistration preprocess(NotificationsRegistration registration) {
		if (registration.getApplication()==null) {
			registration.setApplication(application);
		}
		if (registration.getTemplates()!=null) {
			for (Template template: registration.getTemplates()) {
				preprocess(template, null);
			}
		}
		if (registration.getTypes()!=null) {
			for (NotificationType type: registration.getTypes()) {
				preprocess(type);
			}
		}
		return registration;
	}
	

	
	/**
	 * Preprocess {@code Template} before registration.
	 * 
	 * @param template the {@code Template}
	 * @param medium the {@code Medium}
	 * @return same {@code Template}
	 */
	protected Template preprocess(Template template, Medium medium) {
		if (template!=null) {
			if (template.getMedium()==null) {
				template.setMedium(medium);
			}
			try {
				if (template.getUri()!=null) {
					String uri = engine.expand(template.getUri(), null);
					template.setUri(uri);
				}
				template.loadContent(true, config.getTemplates());							
			} catch (RuntimeException e) {
				logger.error("preprocess:" + e + " " + template.getName() + " " + template.getUri() + " " + template.isAbsoluteUri() + " " + template.getBaseUri(config.getTemplates())) ;
			}
		}
		return template;
	}

	/**
	 * Preprocess {@code NotificationType} before registration.
	 * 
	 * @param notificationType the {@code NotificationType}
	 * @return same {@code NotificationType}
	 */
	protected NotificationType preprocess(NotificationType notificationType) {
		preprocess(notificationType.getTemplate(), Medium.APP);
		preprocess(notificationType.getMailTemplate(), Medium.EMAIL);
		preprocess(notificationType.getSmsTemplate(), Medium.SMS);
		return notificationType;
	}
	
	
	//
	// Event Publishing
	//
	
	/**
	 * Publish {@code Event} using the default transport.
	 * 
	 * @param event the {@code Event}
	 * @param context optional {@code NotificationsClientContext}
	 */
	public void publishEvent(Event event, NotificationsClientContext context) {
		if (amqpTemplate!=null && (config.getAmqp().getEnabled()==null || Boolean.TRUE.equals(config.getAmqp().getEnabled()))) {
			publishEventAmqp(event, context);			
		} else {
			publishEventHttp(event, context);
		}
	}

	/**
	 * Publish {@code Notification} directly to Broker using asynchronous transport.
	 * 
	 * @param notification the {@code Notification}
	 * @param context optional {@code NotificationsClientContext}
	 */
	public void publishDirect(Notification notification, NotificationsClientContext context) {
		publishDirectAmqp(notification, context);
	}

	/**
	 * Publish {@code Event} using synchronous HTTP request.
	 * 
	 * @param event the {@code Event}
	 * @param context optional {@code NotificationsClientContext}
	 */
	@Override
	public void publishEventHttp(Event event, NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.event(config));
		RequestEntity<Event> request = RequestEntity.post(uri).body(event);
		exchange(request, Void.class, context);
	}

	/**
	 * Publish {@code Event} using default async transport.
	 * 
	 * @param event the {@code Event}
	 * @param context optional {@code NotificationsClientContext}
	 */
	@Override
	public void publishEventAsync(Event event, NotificationsClientContext context) {
		publishEventAmqp(event, context);
	}

	/**
	 * Publish {@code Event} using the AMQP transport.
	 * 
	 * @param event the {@code Event}
	 * @param context optional {@code NotificationsClientContext}
	 */
	@Override
	public void publishEventAmqp(Event event, NotificationsClientContext context) {
		Object eventData = MappingUtils.convert(event, LinkedHashMap.class);
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("publishEventAmqp: %s", eventData));			
		}
		amqpTemplate.convertAndSend(config.getAmqp().getEventExchange(), "" /*routingKey*/, eventData, new MessagePostProcessor() {
			
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				return message;
			}
		});
	}
	
	/**
	 * Publish {@code Notification} directly using default asynchronous transport.
	 * 
	 * @param notification the {@code Notification}
	 * @param context optional {@code NotificationsClientContext}
	 */
	public void publishDirectAmqp(Notification notification, NotificationsClientContext context) {
		Object notificationData =  MappingUtils.convert(notification, LinkedHashMap.class);
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("publishDirectAmqp: %s", notificationData));			
		}
		amqpTemplate.convertAndSend(config.getAmqp().getNotificationsExchange(), "" /*routingKey*/, notificationData, new MessagePostProcessor() {
			
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				return message;
			}
		});
	}
	
	
	//
	// Error Reporting
	//

	/**
	 * Report an application Error.
	 * 
	 * @param error the {@code ErrorReport}
	 * @param context optional {@code NotificationsClientContext}
	 */
	public void reportError(ErrorReport error, NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.error(config));
		RequestEntity<ErrorReport> request = RequestEntity.post(uri).body(error);
		exchange(request, Void.class, context);
	}


	//
	// Notifications
	//

	/**
	 * List {@code Notification}s.
	 * 
	 * <p><b>Required Security Credentials</b>: Client, Admin (global role ADMIN), or owner.
	 * 
	 * @param filter a {@code NotificationFilter}
	 * @param pageable a {@code Pageable} (optional)
	 * @param context optional {@code NotificationsClientContext}
	 * @throws RestClientException if request fails
	 * @return a {@code Page} with {@code Notification}s, or null if request failed
	 */
	public Page<Notification> listNotifications(NotificationFilter filter, Pageable pageable, NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.notifications(config));
		uri = processURI(uri, filter, pageable);
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		@SuppressWarnings("rawtypes")
		ResponseEntity<Page> result = exchange(request, Page.class);
		return PageUtil.create(result.getBody(),  Notification.class);
	}

	/**
	 * Count {@code Notification}s.
	 * 
	 * <p><b>Required Security Credentials</b>: Client, Admin (global role ADMIN), or owner.
	 * 
	 * @param filter a {@code NotificationFilter}
	 * @param context optional {@code NotificationsClientContext}
	 * @throws RestClientException if request fails
	 * @return the {@code Notification} count
	 */
	public Long countNotifications(NotificationFilter filter, NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.count(config));
		uri = processURI(uri, filter);
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<Long> result = exchange(request, Long.class);
		return result.getBody();
	}

	/**
	 * Delete {@code Notification}
	 * 
	 * 
	 * <p><b>Required Security Credentials</b>: Client, Admin (global role ADMIN), or owner.
	 * 
	 * @param id the {@code Notification} identifier (UUID)
	 * @param options optional {@code RequestOptions}
	 * @param context optional {@code NotificationsClientContext}
	 * @throws RestClientException if request fails
	 */
	public void deleteNotification(String id, RequestOptions options, NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.notification(id, config));
		uri = processURI(uri, options);
		RequestEntity<Void> request = RequestEntity.delete(uri).accept(MediaType.APPLICATION_JSON).build();
		exchange(request, Void.class);
	}
	
	
	
	//
	// Notification Delivery
	//
    
	/**
	 * Deliver a {@code Notification} to a user using a WebSocket.
	 * 
	 * @param template the {@code SimpMessagingTemplate}
	 * @param notification the {@code Notification}
	 * @param target the user {@code Target}
	 */
	public void deliver(SimpMessagingTemplate template, Notification notification, Target target) {
		Map<String, Object> payload = MappingUtils.toMap(notification);
		String username = target.getId();		
		template.convertAndSend("/queue/" + username, payload);
	}
	
	/**
	 * Deliver a {@code Notification} to all {@code Targets} a {@code SimpMessagingTemplate}.
	 * 
	 * @param template the {@code SimpMessagingTemplate}
	 * @param notification the {@code Notification}
	 */
	public void deliver(SimpMessagingTemplate template, Notification notification) {
		List<Target> targets =notification.getTargets();
		if (targets!=null) {
			Map<String, Object> payload = MappingUtils.toMap(notification);
			for (Target target: targets) {
				String username = target.getId();	
				if (!StringUtil.hasText(username)) {
					continue;
				}
				template.convertAndSend("/queue/" + username, payload);
			}
		}
	}
	
	//
	// Preferences
	//

	/**
	 * Get map of {@code Preference}s for a user (the principal, by default).
	 * 
	 * @param filter a {@code PreferenceFilter}
	 * @param context optional {@code NotificationsClientContext}
	 * @return the {@code Preference} map
	 */
	public Map<String, Preference> getPreferences(PreferenceFilter filter, NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.preferences(config));
		uri = processURI(uri, filter);
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> result = exchange(request, Map.class);
		Map<String, Preference> map = new LinkedHashMap<>();
		@SuppressWarnings("unchecked")
		Set<Map.Entry<?, ?>> ee = result.getBody().entrySet();		
		for (Map.Entry<?, ?> e: ee) {
			Preference pref = MappingUtils.convert(e.getValue(), Preference.class);
			map.put(e.getKey().toString(), pref);
		}
		return map;
	}

	
	
	//
	// NotificationTypes
	//

	/**
	 * Get {@code NotificationType} with specified identifier.
	 * 
	 * <p><b>Required Security Credentials</b>: Client or Global Admin Role.
	 * 
	 * @param id the identifier
	 * @param options (optional) the {@code NotificationTypeOptions} that tailor which fields are returned (projection)
	 * @param context optional {@code NotificationsClientContext}
	 * @return the {@code NotificationType}
	 * @throws RestClientException if request fails
	 */
	public NotificationType getNotificationType(String id, NotificationTypeOptions options, NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.notificationType(id, config));
		uri = processURI(uri, options);
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<NotificationType> result = exchange(request, NotificationType.class);
		return result.getBody();
	}
	

	/**
	 * List {@code NotificationType}s.
	 * 
	 * <p><b>Required Security Credentials</b>: Client or Global Admin Role.
	 * 
	 * @param filter a {@code NotificationTypeFilter}
	 * @param pageable a {@code Pageable} (optional)
	 * @param context optional {@code NotificationsClientContext}
	 * @throws RestClientException if request fails
	 * @return a {@code Page} with {@code NotificationType}s, or null if request failed
	 */
	public Page<NotificationType> listNotificationTypes(NotificationTypeFilter filter, Pageable pageable, NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.notificationTypes(config));
		uri = processURI(uri, filter, pageable);
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		@SuppressWarnings("rawtypes")
		ResponseEntity<PageResult> result = exchange(request, PageResult.class);
		return PageUtil.create2(result.getBody(),  NotificationType.class);
	}

	/**
	 * Create a new {@code NotificationType}.
	 * 
	 * <p><b>Required Security Credentials</b>: Client or Global Admin Role.
	 * 
	 * @param notificationType the {@code NotificationType}
	 * @param options optional {@code RequestOptions}
	 * @param context optional {@code NotificationsClientContext}
	 * @return the location {@code URI} for the created {@code NotificationType}
	 * @throws RestClientException if request fails
	 */
	public URI createNotificationType(NotificationType notificationType, RequestOptions options, NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.notificationTypes(config));
		uri = processURI(uri, options);
		RequestEntity<NotificationType> request = RequestEntity.post(uri).accept(MediaType.APPLICATION_JSON).body(notificationType);
		ResponseEntity<Void> result = exchange(request, Void.class);
		return result.getHeaders().getLocation();
	}
	
	
	/**
	 * Update existing {@code NotificationType}
	 * 
	 * <p><b>Required Security Credentials</b>: Client or Global Admin Role.
	 * 
	 * @param notificationType the {@code NotificationType}
	 * @param options optional {@code RequestOptions}
	 * @param context optional {@code NotificationsClientContext}
	 * @throws RestClientException if request fails
	 */
	public void updateNotificationType(NotificationType notificationType, RequestOptions options, NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.notificationType(notificationType.getId(), config));
		uri = processURI(uri, options);
		RequestEntity<NotificationType> request = RequestEntity.put(uri).accept(MediaType.APPLICATION_JSON).body(notificationType);
		exchange(request, NotificationType.class);
	}
	
	/**
	 * Delete existing {@code NotificationType}
	 * 
	 * 
	 * <p><b>Required Security Credentials</b>: Client or Global Admin Role.
	 * 
	 * @param id the {@code NotificationType} identifier (UUID)
	 * @param options optional {@code RequestOptions}
	 * @param context optional {@code NotificationsClientContext}
	 * @throws RestClientException if request fails
	 */
	public void deleteNotificationType(String id, RequestOptions options, NotificationsClientContext context) {
		id = encodeId(id);
		URI uri = makeURI(NotificationsEndpoints.notificationType(id, config));
		uri = processURI(uri, options);
		RequestEntity<Void> request = RequestEntity.delete(uri).accept(MediaType.APPLICATION_JSON).build();		
		exchange(request, Void.class);
	}
	
	//
	// Static util
	//
	
	public static String makeNotificationTypeId(Source source, Action action) {
		return makeNotificationTypeId(source.getType(), action.getType());
	}
	
	public static String makeNotificationTypeId(String sourceType, String actionType) {
		return makeNotificationTypeId(sourceType, actionType, null);
	}
		
	public static String makeNotificationTypeId(String sourceType, String actionType, Object suffix) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.hasText(sourceType)) {
			sb.append(sourceType.toLowerCase());
		}
		if (StringUtils.hasText(actionType)) {
			if (sb.length()>0) {
				sb.append(TYPE_ID_SEPARATOR);
			}
			sb.append(actionType.toLowerCase());
		}
		if (suffix!=null && StringUtils.hasText(suffix.toString())) {
			if (sb.length()>0) {
				sb.append(TYPE_ID_SEPARATOR);
			}
			sb.append(suffix.toString().toLowerCase());
		}
		if (sb.length()==0) {
			return null;
		}
		return sb.toString();
	}

	
	//
	// Templates
	//

	/**
	 * Get {@code Template} with specified identifier.
	 * 
	 * <p><b>Required Security Credentials</b>: any.
	 * 
	 * @param id the identifier
	 * @param options (optional) the {@code TemplateOptions} that tailor which fields are returned (projection)
	 * @param context optional {@code NotificationsClientContext}
	 * @return the {@code Template}
	 * @throws RestClientException if request fails
	 */
	public Template getTemplate(String id, TemplateOptions options, NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.template(id, config));
		uri = processURI(uri, options);
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<Template> result = exchange(request, Template.class);
		return result.getBody();
	}
	

	/**
	 * List {@code Template}s.
	 * 
	 * <p><b>Required Security Credentials</b>: any.
	 * 
	 * @param filter a {@code TemplateFilter}
	 * @param pageable a {@code Pageable} (optional)
	 * @param context optional {@code NotificationsClientContext}
	 * @throws RestClientException if request fails
	 * @return a {@code Page} with {@code Template}s, or null if request failed
	 */
	public Page<Template> listTemplates(TemplateFilter filter, Pageable pageable, NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.templates(config));
		uri = processURI(uri, filter, pageable);
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		@SuppressWarnings("rawtypes")
		ResponseEntity<PageResult> result = exchange(request, PageResult.class);
		return PageUtil.create2(result.getBody(),  Template.class);
	}

	/**
	 * Create a new {@code Template}.
	 * 
	 * <p><b>Required Security Credentials</b>: Client or Global Admin Role.
	 * 
	 * @param template the {@code Template}
	 * @param options optional {@code RequestOptions}
	 * @param context optional {@code NotificationsClientContext}
	 * @return the location {@code URI} for the created {@code Template}
	 * @throws RestClientException if request fails
	 */
	public URI createTemplate(Template template, RequestOptions options, NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.templates(config));
		uri = processURI(uri, options);
		RequestEntity<Template> request = RequestEntity.post(uri).accept(MediaType.APPLICATION_JSON).body(template);
		ResponseEntity<Void> result = exchange(request, Void.class);
		return result.getHeaders().getLocation();
	}
	
	
	/**
	 * Update existing {@code Template}
	 * 
	 * <p><b>Required Security Credentials</b>: Client or Global Admin Role.
	 * 
	 * @param template the {@code Template}
	 * @param options optional {@code RequestOptions}
	 * @param context optional {@code NotificationsClientContext}
	 * @throws RestClientException if request fails
	 */
	public void updateTemplate(Template template, RequestOptions options, NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.template(template.getId(), config));
		uri = processURI(uri, options);
		RequestEntity<Template> request = RequestEntity.put(uri).accept(MediaType.APPLICATION_JSON).body(template);
		exchange(request, Template.class);
	}
	
	/**
	 * Delete existing {@code Template}
	 * 
	 * 
	 * <p><b>Required Security Credentials</b>: Client or Global Admin Role.
	 * 
	 * @param id the {@code Template} identifier (UUID)
	 * @param options optional {@code RequestOptions}
	 * @param context optional {@code NotificationsClientContext}
	 * @throws RestClientException if request fails
	 */
	public void deleteTemplate(String id, RequestOptions options, NotificationsClientContext context) {
		id = encodeId(id);
		URI uri = makeURI(NotificationsEndpoints.template(id, config));
		uri = processURI(uri, options);
		RequestEntity<Void> request = RequestEntity.delete(uri).accept(MediaType.APPLICATION_JSON).build();		
		exchange(request, Void.class);
	}
	
	
	//
	// HTTP transport
	//
	
	protected <T> ResponseEntity<T> exchange(RequestEntity<?> request, Class<T> responseType, NotificationsClientContext context) throws RestClientException {
		OAuth2RestTemplate restTemplate = this.restTemplate;
		if (context!=null && context.getRestTemplate()!=null) {
			restTemplate = context.getRestTemplate();
		} else {
			if (WebUtil.getHttpServletRequest()==null && this.restTemplate0!=null) {
				restTemplate = this.restTemplate0;
			}			
		}
		return exchange(restTemplate, request, responseType);
	}

	protected <T> ResponseEntity<T> exchange(OAuth2RestTemplate restTemplate, RequestEntity<?> request, Class<T> responseType) throws RestClientException {
		if (config.getEnabled()!=null && Boolean.FALSE.equals(config.getEnabled())) {
			throw new RuntimeException("Notifications Not Enabled");
		}
		return restTemplate.exchange(request, responseType);
	}
	
	
	protected <T> ResponseEntity<T> exchange(RequestEntity<?> request, Class<T> responseType) throws RestClientException {
		return exchange(restTemplate, request, responseType);
	}

	//
	// Other
	//

	/**
	 * Process URI by adding parameters from properties of specified objectes.
	 * 
	 * @param uri the {@code URI}
	 * @param objs a variadic array of objects
	 * @return the processed {@code URI}
	 */
	private static URI processURI(URI uri, Object... objs) {
		return UriUtils.appendQueryParameters(uri, objs);
	}
	
	
	
}
