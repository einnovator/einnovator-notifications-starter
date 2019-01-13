package org.einnovator.notifications.client.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.core.OrderComparator;
import org.springframework.stereotype.Service;


@Service
@Primary
public class MultiTemplateManager implements TemplateManager {

	@Autowired
	private ApplicationContext context;

	private final Log logger = LogFactory.getLog(getClass());

	private List<TemplateManager> managers = new ArrayList<>();

	@Autowired
	public MultiTemplateManager() {
	}

	public MultiTemplateManager(TemplateManager... managers) {
		for (TemplateManager manager: managers) {
			if (manager instanceof MultiTemplateManager) {
				continue;				
			}
			this.managers.add(manager);
		}
	}
	
	@PostConstruct
	public void init() {
		logger.debug("Started:" + this);
		for (String name: context.getBeanNamesForType(TemplateManager.class)) {
			TemplateManager manager = context.getBean(name, TemplateManager.class);
			if (manager instanceof MultiTemplateManager) {
				continue;
			}
			if (!manager.isEnabled()) {
				continue;
			}
			logger.debug("Found:" + manager);
			this.managers.add(manager);
		}
		if (managers.isEmpty()) {
			throw new RuntimeException("Missing " + TemplateManager.class);
		}
		OrderComparator.sort(managers);
	}
	

	@Override
	public String expandTemplate(String templateFilename, Map<String, Object> env) {
		String text = null;
		boolean b = false;
		for (int i=0; i<managers.size(); i++) {
			TemplateManager manager = managers.get(i);
			try {
				if (!b) {
					text = manager.expandTemplate(templateFilename, env);					
				} else {
					text = manager.expand(text, env);
				}
				b = true;
			} catch (RuntimeException e) {
				logger.error("expandTemplate: " + manager.getClass().getSimpleName() + " " + e);
			}
		}
		return text;
	}
	
	@Override
	public String expand(String text, Map<String, Object> env) {
		if (text==null) {
			return null;
		}
		for (int i=0; i<managers.size(); i++) {
			TemplateManager manager = managers.get(i);
			try {
				text = manager.expand(text, env);
			} catch (RuntimeException e) {
				logger.error("expand: " + manager.getClass().getSimpleName() + " " + e);
			}
		}
		return text;
	}
	
	
	public boolean supports(String filename) {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
}
