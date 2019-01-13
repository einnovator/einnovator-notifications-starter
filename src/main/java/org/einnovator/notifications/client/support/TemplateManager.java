package org.einnovator.notifications.client.support;

import java.util.Map;

public interface TemplateManager {
	
	String expandTemplate(String templateFilename, Map<String, Object> env);
	
	String expand(String text, Map<String, Object> env);

	boolean supports(String filename);
	
	boolean isEnabled();

}
