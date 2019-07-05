package org.einnovator.notifications.client.manager;

import java.net.URI;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.einnovator.notifications.client.NotificationsClient;
import org.einnovator.notifications.client.config.NotificationsClientConfiguration;
import org.einnovator.notifications.client.model.Medium;
import org.einnovator.notifications.client.model.Notification;
import org.einnovator.notifications.client.model.NotificationsRegistration;
import org.einnovator.notifications.client.model.Template;
import org.einnovator.notifications.client.modelx.TemplateFilter;
import org.einnovator.notifications.client.modelx.TemplateOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class TemplateManagerImpl extends ManagerBase implements TemplateManager {

	public static final String CACHE_TEMPLATE = "Template";

	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	private NotificationsClient notificationsClient;
	
	@Autowired
	private NotificationsClientConfiguration config;
	
	public TemplateManagerImpl(NotificationsClient notificationsClient, CacheManager cacheManager) {
		this.notificationsClient = notificationsClient;
		this.cacheManager = cacheManager;
	}
	
	public TemplateManagerImpl(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
	public TemplateManagerImpl() {
	}

	@Override
	public Template getTemplate(String id, Medium medium, boolean remote, boolean local) {
		if (remote) {
			if (medium!=null) {
				id = medium.name().toLowerCase() + ":" + id;
			}
			Template template = getTemplate(id);
			if (template!=null) {
				return template;
			}
		}
		if (local) {
			Template template = config.getRegistration().findTemplate(id, medium);
			if (template!=null) {
				return template;
			}
		}
		return null;
	}
	
	@Override
	public Template getTemplate(String id) {
		if (id==null) {
			return null;
		}
		try {
			Template template = getCacheValue(Template.class, getTemplateCache(), id);
			if (template!=null) {
				return template;
			}
			template = notificationsClient.getTemplate(id);	
			return putCacheValue(template, getTemplateCache(), id);
		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode()!=HttpStatus.NOT_FOUND) {
				logger.error("getTemplate:" + id + "  " + e);				
			}
			return null;
		} catch (RuntimeException e) {
			logger.error("getTemplate:" + id + "  " + e);
			return null;
		}
	}

	@Override
	public Template getTemplate(String id, TemplateOptions options) {
		if (id==null) {
			return null;
		}
		if (cacheable(options)) {
			Template template = getCacheValue(Template.class, getTemplateCache(), id, options);
			if (template!=null) {
				return template;
			}			
		}
		try {
			Template template = notificationsClient.getTemplate(id, options);	
			if (cacheable(options)) {
				return putCacheValue(template, getTemplateCache(), id, options);				
			}
			return template;
		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode()!=HttpStatus.NOT_FOUND) {
				logger.error("getTemplate:" + id + "  " + options + e);				
			}
			return null;
		} catch (RuntimeException e) {
			logger.error("getTemplate:" + id + "  " + options + " " + e);				
			return null;
		}

	}

	protected boolean cacheable(TemplateOptions options) {
		return options==null;
	}
	
	@Override
	public URI createTemplate(Template template) {
		try {
			return notificationsClient.createTemplate(template);
		} catch (RuntimeException e) {
			logger.error("createTemplate:" + e);
			return null;
		}
	}
	

	@Override
	public Template updateTemplate(Template template) {
		try {
			notificationsClient.updateTemplate(template);
			evictCaches(template.getUuid());
			return template;
		} catch (RuntimeException e) {
			logger.error("updateTemplate:" + e);
			return null;
		}
	}
	
	@Override
	@CacheEvict(value=CACHE_TEMPLATE, key="#id")
	public boolean deleteTemplate(String id) {
		try {
			notificationsClient.deleteTemplate(id);
			return true;
		} catch (RuntimeException e) {
			logger.error("deleteTemplate:" + e);
			return false;
		}
	}
	
	
	@Override
	public Page<Template> listTemplates(TemplateFilter filter, Pageable pageable) {
		try {
			return notificationsClient.listTemplates(filter, pageable);
		} catch (RuntimeException e) {
			logger.error("listTemplates:" + e);
			return null;
		}
	}


	@Override
	public void onTemplateUpdate(String id, Map<String, Object> details) {
		if (id==null) {
			return;
		}
		try {
			Cache cache = getTemplateCache();
			if (cache!=null) {
				ValueWrapper e =  cache.get(id);
				if (e!=null) {
					Template template = (Template)e.get();
					if (details!=null) {
						if (template!=null) {					
							template.updateFrom(details);	
						}
					} else {
						cache.evict(id);
					}
				}
			}
		} catch (RuntimeException e) {
			logger.error("onTemplateUpdate: " + e + " " + id);
		}
	}

	@Override
	public Cache getTemplateCache() {
		Cache cache = cacheManager.getCache(TemplateManagerImpl.CACHE_TEMPLATE);
		return cache;
	}
	
	@Override
	public void clearCache() {
		Cache cache = getTemplateCache();
		if (cache!=null) {
			cache.clear();
		}
	}

	public static final String ACTION_TYPE_LOGOUT = "Logout";

	@EventListener
	@Override
	public void onEvent(ApplicationEvent event) {
		if (!(event instanceof PayloadApplicationEvent)) {
			return;
		}
		Notification obj = (Notification)((PayloadApplicationEvent<?>)event).getPayload();
		if (obj==null || obj.getSource()==null || obj.getSource().getDetails()==null) {
			return;
		}
		Template template= new Template(obj.getSource().getDetails());
		logger.debug("onEvent:" + template + " " + obj);
		if (template!=null) {
			evictCaches(template);
			return;
		}
		
		
	}

	private void evictCaches(Template template) {
		if (template!=null) {
			evictCaches(template.getName());
			evictCaches(template.getUuid());
		}
	}
	
	public void evictCaches(String id) {
		Cache cache = getTemplateCache();
		if (cache != null && id != null) {
			cache.evict(id);
		}
	}


}
