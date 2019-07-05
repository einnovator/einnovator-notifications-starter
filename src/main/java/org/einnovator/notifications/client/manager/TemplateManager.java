package org.einnovator.notifications.client.manager;


import java.net.URI;
import java.util.Map;

import org.einnovator.notifications.client.model.Medium;
import org.einnovator.notifications.client.model.Template;
import org.einnovator.notifications.client.modelx.TemplateFilter;
import org.einnovator.notifications.client.modelx.TemplateOptions;
import org.springframework.cache.Cache;
import org.springframework.context.ApplicationEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TemplateManager {
	
	Template getTemplate(String id, Medium medium, boolean remote, boolean local);

	Template getTemplate(String id);
	
	Template getTemplate(String id, TemplateOptions options);

	URI createTemplate(Template user);
	
	Template updateTemplate(Template user);

	boolean deleteTemplate(String id);
	
	Page<Template> listTemplates(TemplateFilter filter, Pageable options);
	
	void onTemplateUpdate(String id, Map<String, Object> details);

	void clearCache();
	
	Cache getTemplateCache();

	void onEvent(ApplicationEvent event);

}
