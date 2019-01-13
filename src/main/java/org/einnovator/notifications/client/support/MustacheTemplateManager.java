package org.einnovator.notifications.client.support;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

@Service
public class MustacheTemplateManager extends TemplateManagerBase {

	public static final String[] DEFAULT_MUSTACHE_EXTENSIONS = {"mt", "mhtml"};


	private final Log logger = LogFactory.getLog(getClass());

	MustacheFactory mf = new DefaultMustacheFactory();
	 
	public MustacheTemplateManager() {
	}
	
	@Override
	public String expandTemplate(String templateFilename, Map<String, Object> env) {
		try {
			Reader in = new InputStreamReader(new FileInputStream(templateFilename));
			Mustache mustache = mf.compile(in, templateFilename);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Writer writer = new OutputStreamWriter(out);
			mustache.execute(writer, env);
			writer.flush();
			return new String(out.toByteArray());
		} catch (IOException e) {
			logger.error("expandTemplate:" + e + " " + templateFilename);
			return null;
		}
	}
	
	@Override
	public String expand(String text, Map<String, Object> env) {
		try {
			Reader in = new InputStreamReader(new ByteArrayInputStream(text.getBytes()));
			Mustache mustache = mf.compile(in, "");
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Writer writer = new OutputStreamWriter(out);
			mustache.execute(writer, env);
			writer.flush();
			return new String(out.toByteArray());
		} catch (IOException e) {
			logger.error("expandTemplate:" + e + " " + text);
			return null;
		}
	}
	
	
	public boolean supports(String filename) {
		String ext = FilenameUtils.getExtension(filename);
		if (StringUtils.hasText(ext)) {
			for (String mext : DEFAULT_MUSTACHE_EXTENSIONS) {
				if (mext.equalsIgnoreCase(ext)) {
					return true;
				}
			}
		}
		return false;
	}
}
