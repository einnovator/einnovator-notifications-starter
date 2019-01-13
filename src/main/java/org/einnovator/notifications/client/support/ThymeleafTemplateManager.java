package org.einnovator.notifications.client.support;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;


@Service
public class ThymeleafTemplateManager extends TemplateManagerBase implements Ordered {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());

	TemplateEngine engine;
	
	public ThymeleafTemplateManager() {
		engine = new TemplateEngine();
		engine.setTemplateResolver(new ClassLoaderTemplateResolver()); 
		//engine.addDialect(new StandardDialect());
		//engine.addDialect(new SpringStandardDialect());
	}

	@Override
	public String expand(String text, Map<String, Object> env) {
		try {
			File file = File.createTempFile("notification-", new Long(System.currentTimeMillis()).toString());
			FileOutputStream out = new FileOutputStream(file);
			StreamUtils.copy(text, Charset.forName("UTF-8"), out);
			out.close();
			text = expandTemplate(file.toString(), env);
			file.delete();
			return text;
		} catch (IOException e) {
			return text;
		}
	}

	@Override
	public String expandTemplate(String templateFilename, Map<String, Object> env) {
		final IContext context = new Context(Locale.getDefault(), env);
		return engine.process(templateFilename, context);
	}

	@Override
	public int getOrder() {
		return -1;
	}

	
}
