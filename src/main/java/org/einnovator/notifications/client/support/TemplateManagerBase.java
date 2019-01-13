package org.einnovator.notifications.client.support;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

public abstract class TemplateManagerBase implements TemplateManager {

	private final Log logger = LogFactory.getLog(getClass());

	private boolean enabled = true;
	
	public TemplateManagerBase() {
	}

	@Override
	public String expandTemplate(String templateFilename, Map<String, Object> env) {
		try {
			ClassPathResource resource = new ClassPathResource(templateFilename);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			StreamUtils.copy(resource.getInputStream(), out);
			return expand(out.toString(), env);
		} catch (IOException e) {
			logger.error("expandTemplate:" + e + " " + templateFilename);
			return null;
		}
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
	@Override
	public boolean supports(String filename) {
		return true;
	}
}
