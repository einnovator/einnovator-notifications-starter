package org.einnovator.notifications.client.support;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.einnovator.util.MappingUtils;
import org.einnovator.util.PathUtil;
import org.einnovator.notifications.client.model.Action;
import org.einnovator.notifications.client.model.Event;
import org.einnovator.notifications.client.model.EventBuilder;
import org.einnovator.notifications.client.model.Notification;
import org.einnovator.notifications.client.model.PrincipalX;
import org.einnovator.notifications.client.model.Source;
import org.einnovator.notifications.client.model.Target;

public class NotificationsTestHelper {

	protected MultiTemplateManager manager;
	
	protected String TEST_CDN = "http://localhost:8080";
	protected String TEST_BASE_URI = "http://localhost";
	protected String TEST_HOME_URI = "http://home.org";
	protected String TEST_THEME = "theme";

	public void setup() {
		manager = new MultiTemplateManager(new ThymeleafTemplateManager(), new SimpleTemplateManager(), new MustacheTemplateManager());
	}

	private String[] profiles = {"test"};

	protected Map<String, Object> makeEnv(Map<String, Object> env) {
		if (env==null) {
			env = new LinkedHashMap<>();
		}
		env.put("cdn", TEST_CDN);	
		env.put("base-uri", TEST_BASE_URI);	
		env.put("home-uri", TEST_HOME_URI);
		env.put("baseUri", TEST_BASE_URI);	
		env.put("homeUri", TEST_HOME_URI);
		env.put("links", new LinkedHashMap<>());
		env.put("theme", TEST_THEME);
		
		for (String profile: profiles) {
			env.put(profile, true);
		}

		return env;
	}

	protected Map<String, Object> makeEnv(Target target, Map<String, Object> env) {
		if (env==null) {
			env = new HashMap<>();
		}
		env.put("username", target.getName());
		if (env.get("name")==null) {
			env.put("name", target.getName());			
		}
		env.put("target", target);
		return env;
	}
	
	protected Map<String, Object> makeEnv(Notification notification, Map<String, Object> env) {
		if (env==null) {
			env = new LinkedHashMap<>();
		}
		if (notification.getEnv()!=null) {
			env.putAll(notification.getEnv());
		};
		Map<String, Object> map = MappingUtils.toMap(notification);
		env.put("source",  map.get("source"));
		env.put("source2",  map.get("source2"));
		env.put("action",  map.get("action"));
		env.put("principal",  map.get("principal"));
		env.put("meta",  map.get("meta"));
		env.put("details",  map.get("details"));
		env.put("target",  map.get("target"));
		if (notification.getTargets()!=null) {
			Target target = notification.getTargets().get(0);
			makeEnv(target, env);
		}
		
		if (env.get("source-uri")==null) {
			env.put("source-uri", "#");			
		}
		if (env.get("action-uri")==null) {
			env.put("action-uri", "#");			
		}


		return env;
	}

	
	protected Map<String, Object> makeEnv(Notification notification) {
		Map<String, Object> env =  makeEnv((Map<String, Object>)null);
		makeEnv(notification, env);
		return env;
	}
	


	protected void loadTemplate(String template, Map<String, Object> env) {
		String text = manager.expandTemplate(template, env);
		System.out.println("------------");
		System.out.println(template);
		System.out.println(text);
	}
	
	protected void writeTemplate(String template, Map<String, Object> env, String filename) {
		String text = manager.expandTemplate(template, env);
		System.out.println("writeTemplate:" + template + " -> " + filename);
		try (FileWriter writer = new FileWriter(filename)) {
			writer.write(text);			
		} catch (IOException e) {
			System.err.print(e);
		}
	}

	protected void loadTemplates(Map<String, Object> env, String... templates) {
		for (String template: templates) {
			loadTemplate(template, env);
		}
	}
	
	protected void writeTemplates(Map<String, Object> env, String prefix, String... templates) {
		for (String template: templates) {
			String path = PathUtil.concat(prefix, template);
			new File(path).getParentFile().mkdirs();
			writeTemplate(template, env, path);
		}
	}


	protected Source makeSource(Object obj) {
		Map<String, Object> details = MappingUtils.toMap(obj);
		return new Source(obj.getClass().getSimpleName(), (String)details.get("uuid"), details);
	}
	

	protected Event makeEvent(Source source, String action, String principal, String target) {
		return new EventBuilder()
				.source(source)
				.action(new Action(action, null, null))
				.principal(new PrincipalX(PrincipalX.PRINCIPAL_TYPE_USER, principal, principal))
				.targets(new Target(Target.TARGET_TYPE_USER, target, target))
				.build();
	}
}
