package org.einnovator.notifications.client;

import static org.einnovator.util.UriUtils.appendQueryParameters;
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
import org.einnovator.notifications.client.modelx.NotificationOptions;
import org.einnovator.notifications.client.modelx.TemplateFilter;
import org.einnovator.notifications.client.modelx.TemplateOptions;
import org.einnovator.util.MappingUtils;
import org.einnovator.util.PageOptions;
import org.einnovator.util.PageResult;
import org.einnovator.util.PageUtil;
import org.einnovator.util.model.Application;
import org.einnovator.util.script.EnvironmentVariableResolver;
import org.einnovator.util.script.TextTemplates;
import org.einnovator.util.security.ClientTokenProvider;
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

public class NotificationsClient implements NotificationOperationsHttp, NotificationOperationsAmqp {

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
	
	@Autowired
	public NotificationsClient() {
	}

	public NotificationsClient(NotificationsClientConfiguration config) {
		this.config = config;
	}
		
	public NotificationsClient(OAuth2RestTemplate restTemplate, NotificationsClientConfiguration config) {
		this.restTemplate = restTemplate;
		this.config = config;
	}
	
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
	// Publish
	//
	
	public void publishEvent(Event event, NotificationsClientContext context) {
		if (amqpTemplate!=null && (config.getAmqp().getEnabled()==null || Boolean.TRUE.equals(config.getAmqp().getEnabled()))) {
			publishEventAmqp(event, context);			
		} else {
			publishEventHttp(event, context);
		}
	}

	public void publishDirect(Notification notification, NotificationsClientContext context) {
		publishDirect(notification, context);
	}

	@Override
	public void publishEventHttp(Event event, NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.event(config));
		RequestEntity<Event> request = RequestEntity.post(uri).body(event);
		exchange(request, Void.class, context);
	}
	
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
	
	public void publishDirectAmqp(Notification notification, NotificationsClientContext context) {
		Object notificationData =  MappingUtils.convert(notification, LinkedHashMap.class);
		logger.debug("publishDirectAmqp:" + notificationData);
		amqpTemplate.convertAndSend(config.getAmqp().getNotificationsExchange(), "" /*routingKey*/, notificationData, new MessagePostProcessor() {
			
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				return message;
			}
		});
	}
	
	
	public void reportError(ErrorReport error, NotificationsClientContext context) {
		
	}

	public void register(NotificationsClientContext context) {
		NotificationsRegistration registration = config.getRegistration();
		if (registration!=null) {
			if (clientTokenProvider!=null) {
				clientTokenProvider.setupToken();
			}
			register(registration, context);			
		}
	}
	
	public void register(NotificationsRegistration registration, NotificationsClientContext context) {
		OAuth2RestTemplate restTemplate = this.restTemplate;
		if (clientTokenProvider!=null) {
			clientTokenProvider.setupToken();
			restTemplate = clientTokenProvider.makeOAuth2RestTemplate();
		}
		register(registration, restTemplate);
	}

	public void register(NotificationsRegistration registration, OAuth2RestTemplate restTemplate) {
		URI uri = makeURI(NotificationsEndpoints.register(config));
		preprocess(registration);
		RequestEntity<NotificationsRegistration> request = RequestEntity.post(uri).body(registration);
		exchange(restTemplate, request, Void.class);
	}
	
	public NotificationsRegistration preprocess(NotificationsRegistration registration) {
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
	

	
	public Template preprocess(Template template, Medium medium) {
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

	public NotificationType preprocess(NotificationType type) {
		preprocess(type.getTemplate(), Medium.APP);
		preprocess(type.getMailTemplate(), Medium.EMAIL);
		preprocess(type.getSmsTemplate(), Medium.SMS);
		return type;
	}


	public Page<Notification> listNotifications(NotificationFilter filter, Pageable pageable, NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.notifications(config));
		if (filter!=null || pageable!=null) {
			Map<String, String> params = new LinkedHashMap<>();
			if (filter!=null) {
				params.putAll(MappingUtils.toMapFormatted(filter));				
			}
			if (pageable!=null) {
				params.putAll(MappingUtils.toMapFormatted(new PageOptions(pageable)));				
			}
			
			uri = appendQueryParameters(uri, params);	
		}
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		@SuppressWarnings("rawtypes")
		ResponseEntity<Page> result = exchange(request, Page.class);
		return PageUtil.create(result.getBody(),  Notification.class);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Notification> getUserNotifications(NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.notifications(config));
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<List> result = exchange(request, List.class);
		return result.getBody();
	}
	
	public Long countNotifications(NotificationFilter filter, NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.count(config));
		if (filter!=null) {
			Map<String, String> params = new LinkedHashMap<>();
			params.putAll(MappingUtils.toMapFormatted(filter));
			uri = appendQueryParameters(uri, params);	
		}
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<Long> result = exchange(request, Long.class);
		return result.getBody();
	}

	public void deleteNotification(String id, NotificationOptions options, NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.notification(id, config));
		if (options!=null) {
			Map<String, String> params = new LinkedHashMap<>();
			params.putAll(MappingUtils.toMapFormatted(options));
			uri = appendQueryParameters(uri, params);	
		}
		RequestEntity<Void> request = RequestEntity.delete(uri).accept(MediaType.APPLICATION_JSON).build();
		exchange(request, Void.class);
	}

	public Map<String, Preference> getPreferences(NotificationsClientContext context) {
		return getPreferences(null, context);
	}

	public Map<String, Preference> getPreferences(String username, NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.preferences(config));
		if (username!=null) {
			Map<String, String> params = new LinkedHashMap<>();
			params.put("username", username);
			uri = appendQueryParameters(uri, MappingUtils.toMapFormatted(params));			
		}

		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> result = restTemplate.exchange(request, Map.class);
		Map<String, Preference> map = new LinkedHashMap<>();
		@SuppressWarnings("unchecked")
		Set<Map.Entry<?, ?>> ee = result.getBody().entrySet();		
		for (Map.Entry<?, ?> e: ee) {
			Preference pref = MappingUtils.convert(e.getValue(), Preference.class);
			map.put(e.getKey().toString(), pref);
		}
		return map;
	}

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
	
	

    
	public void deliver(SimpMessagingTemplate template, Notification notification, Target target) {
		Map<String, Object> payload = MappingUtils.toMap(notification);
		String username = target.getId();		
		template.convertAndSend("/queue/" + username, payload);
	}
	
	public void deliver(SimpMessagingTemplate template, Notification notification) {
		List<Target> targets =notification.getTargets();
		if (targets!=null) {
			Map<String, Object> payload = MappingUtils.toMap(notification);
			for (Target target: targets) {
				String username = target.getId();	
				if (username==null) {
					continue;
				}
				template.convertAndSend("/queue/" + username, payload);
			}
		}
	}
	
	//
	// Templates
	//

	public Template getTemplate(String id, NotificationsClientContext context) {
		return getTemplate(id, null, context);
	}
	
	public Template getTemplate(String id, TemplateOptions options, NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.template(id, config));
		if (options!=null) {
			Map<String, String> params = new LinkedHashMap<>();
			params.putAll(MappingUtils.toMapFormatted(options));
			uri = appendQueryParameters(uri, params);			
		}

		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<Template> result = exchange(request, Template.class);
		return result.getBody();
	}
	

	public Page<Template> listTemplates(TemplateFilter filter, Pageable pageable, NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.templates(config));
		if (filter!=null || pageable!=null) {
			Map<String, String> params = new LinkedHashMap<>();
			if (filter!=null) {
				params.putAll(MappingUtils.toMapFormatted(filter));
			}
			if (pageable!=null) {
				params.putAll(MappingUtils.toMapFormatted(new PageOptions(pageable)));
			}
			uri = appendQueryParameters(uri, params);			
		}
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		@SuppressWarnings("rawtypes")
		ResponseEntity<PageResult> result = exchange(request, PageResult.class);
		return PageUtil.create2(result.getBody(),  Template.class);
	}

	public URI createTemplate(Template template, NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.templates(config));
		RequestEntity<Template> request = RequestEntity.post(uri).accept(MediaType.APPLICATION_JSON).body(template);
		
		ResponseEntity<Void> result = exchange(request, Void.class);
		return result.getHeaders().getLocation();
	}
	
	public void updateTemplate(Template template, NotificationsClientContext context) {
		URI uri = makeURI(NotificationsEndpoints.template(template.getId(), config));
		RequestEntity<Template> request = RequestEntity.put(uri).accept(MediaType.APPLICATION_JSON).body(template);
		
		exchange(request, Template.class);
	}
	
	public void deleteTemplate(String templateId, NotificationsClientContext context) {
		templateId = encodeId(templateId);
		URI uri = makeURI(NotificationsEndpoints.template(templateId, config));
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

}
