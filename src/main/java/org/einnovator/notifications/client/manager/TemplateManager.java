package org.einnovator.notifications.client.manager;


import java.net.URI;
import java.util.Map;

import org.einnovator.notifications.client.config.NotificationsClientContext;
import org.einnovator.notifications.client.model.Medium;
import org.einnovator.notifications.client.model.Template;
import org.einnovator.notifications.client.modelx.TemplateFilter;
import org.einnovator.notifications.client.modelx.TemplateOptions;
import org.springframework.cache.Cache;
import org.springframework.context.ApplicationEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TemplateManager {


	Template getTemplate(String id, String app, Medium medium, boolean remote, boolean local, NotificationsClientContext context);

	Template getTemplate(String id, Medium medium, boolean remote, boolean local, NotificationsClientContext context);

	Template getTemplate(String id, NotificationsClientContext context);
	
	Template getTemplate(String id, TemplateOptions options, NotificationsClientContext context);

	Template getLocalTemplate(String id, Medium medium, NotificationsClientContext context);

	URI createTemplate(Template user, NotificationsClientContext context);
	
	Template updateTemplate(Template user, NotificationsClientContext context);

	boolean deleteTemplate(String id, NotificationsClientContext context);
	
	Page<Template> listTemplates(TemplateFilter filter, Pageable options, NotificationsClientContext context);
	
	void onTemplateUpdate(String id, Map<String, Object> details);

	void clearCache();
	
	Cache getTemplateCache();

	void onEvent(ApplicationEvent event);


}
