package org.einnovator.notifications.client.support;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import org.einnovator.util.TextTemplates;

@Service
public class SimpleTemplateManager extends TemplateManagerBase {


	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());

	private TextTemplates textTemplates =  new TextTemplates();
	 	
	public SimpleTemplateManager() {
	}
	
	
	
	@Override
	public String expand(String text, Map<String, Object> env) {
		text = textTemplates.expand(text, env);
		return text;
	}
	

}
