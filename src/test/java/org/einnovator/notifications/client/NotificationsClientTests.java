package org.einnovator.notifications.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.einnovator.notifications.client.amqp.NotificationListener;
import org.einnovator.notifications.client.amqp.NotificationListenerContainer;
import org.einnovator.notifications.client.config.NotificationsClientConfig;
import org.einnovator.notifications.client.config.NotificationsClientConfiguration;
import org.einnovator.notifications.client.manager.PreferencesManagerImpl;
import org.einnovator.notifications.client.model.Action;
import org.einnovator.notifications.client.model.ActionType;
import org.einnovator.notifications.client.model.Event;
import org.einnovator.notifications.client.model.Meta;
import org.einnovator.notifications.client.model.Notification;
import org.einnovator.notifications.client.model.NotificationType;
import org.einnovator.notifications.client.model.NotificationsRegistration;
import org.einnovator.notifications.client.model.Preference;
import org.einnovator.notifications.client.model.PrincipalDetails;
import org.einnovator.notifications.client.model.PriorityType;
import org.einnovator.notifications.client.model.Source;
import org.einnovator.notifications.client.model.SourceType;
import org.einnovator.notifications.client.model.Target;
import org.einnovator.notifications.client.model.TargetParser;
import org.einnovator.notifications.client.modelx.NotificationFilter;
import org.einnovator.sso.client.SsoTestHelper;
import org.einnovator.util.model.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.NONE, classes= {NotificationsClientConfig.class,NotificationsClientTests.TestConfig.class})
@TestPropertySource(properties= {"notifications.uri=http://localhost:2011"})
public class NotificationsClientTests extends SsoTestHelper {

	public static final String TEST_USER = "info@einnovator.org";
	public static final String TEST_USER2 = "jsimao71@gmail.com";
	public static final String TEST_USER3 = "web@einnovator.org";
	public static final String TEST_USER4 = "tdd@einnovator.org";
	public static final String TEST_PASSWORD = "Einnovator123!!";
	private static final String CLIENT_ID = "application";
	private static final String CLIENT_SECRET = "application$123";
	
	
	
	@Autowired
	NotificationsClient client;
	
	@Autowired
	NotificationsClientConfiguration config;
	
	
	@Configuration
	static class TestConfig extends SsoTestHelper.TestConfig {
		
		@Autowired
		public TestConfig(ApplicationContext context) {
			super(TEST_USER2, TEST_PASSWORD, CLIENT_ID, CLIENT_SECRET, context);
		}
		
		@Bean
		public NotificationListenerContainer container() {
			return new NotificationListenerContainer();
		}
		
		@Bean
		public NotificationListener listener() {
			return new NotificationListener() {
				
				@Override
				public void onNotification(Notification notification) throws Exception {
					System.out.println(" >>> " + notification);
				}
			};
		}
	}

	
	@Test
	public void publishEventHttpTest() {
		Event event = makeEvent();
		System.out.println(event);
		
		Target target = event.getTargets().get(0);
		String username = target.getId();
		setPrincipal(username, TEST_PASSWORD);
		NotificationFilter filter = (NotificationFilter)new NotificationFilter().withRunAs(username);

		Long n = client.countNotifications(filter);
		assertNotNull(n);

		client.publishEventHttp(event, null);

		Long m = client.countNotifications(filter);
		assertNotNull(m);
		assertEquals(n+1, m.longValue());
		
		Page<Notification> notifications = client.listNotifications(filter, null);
		assertNotNull(notifications);
		assertNotNull(notifications.getContent());
		assertFalse(notifications.getContent().isEmpty());
		for (Notification notification: notifications) {
			System.out.println(notification);			
		}
	}
	
	@Test
	public void publishEventHttpAndDeleteTest() {
		Event event = makeEvent();
		System.out.println(event);
		
		Target target = event.getTargets().get(0);
		String username = target.getId();
		NotificationFilter filter = (NotificationFilter)new NotificationFilter().withRunAs(username);

		context.setAccessToken(null);
		setPrincipal(username, TEST_PASSWORD);

		Long n = client.countNotifications(filter);
		assertNotNull(n);

		client.publishEventHttp(event, null);

		Long m = client.countNotifications(filter);
		assertNotNull(m);
		assertEquals(n+1, m.longValue());
		
		Page<Notification> notifications = client.listNotifications(filter, null);
		assertNotNull(notifications);
		assertNotNull(notifications.getContent());
		assertFalse(notifications.getContent().isEmpty());
		String id = notifications.getContent().get(0).getId();
		client.deleteNotification(id, filter);
		
		Long m2 = client.countNotifications(filter);
		assertNotNull(m2);
		assertEquals(n.longValue(), m2.longValue());
	}
		
	
	@Test
	public void publishEventHttpAndDeleteAsAdminTest() {
		Event event = makeEvent();
		System.out.println(event);
		
		Target target = event.getTargets().get(0);
		String username = target.getId();
		NotificationFilter filter = (NotificationFilter)new NotificationFilter().withRunAs(username);

		context.setAccessToken(null);
		setPrincipal(username, TEST_PASSWORD);

		Long n = client.countNotifications(filter);
		assertNotNull(n);

		client.publishEventHttp(event, null);

		Long m = client.countNotifications(filter);
		assertNotNull(m);
		assertEquals(n+1, m.longValue());
		
		Page<Notification> notifications = client.listNotifications(filter, null);
		assertNotNull(notifications);
		assertNotNull(notifications.getContent());
		assertTrue(!notifications.getContent().isEmpty());
		String id = notifications.getContent().get(0).getId();
		client.deleteNotification(id, filter);
		
		Long m2 = client.countNotifications(filter);
		assertNotNull(m2);
		assertEquals(n.longValue(), m2.longValue());
	}
		

	@Test
	public void publishEventAmqpTest() {
		Event event = makeEvent();
		System.out.println(event);
		
		Target target = event.getTargets().get(0);
		String username = target.getId();
		NotificationFilter filter = (NotificationFilter)new NotificationFilter().withRunAs(username);
		
		Long n = client.countNotifications(filter);
		assertNotNull(n);

		client.publishEventAmqp(event);
		sleep(1000);
		
		Long m = client.countNotifications(filter);
		assertNotNull(m);
		assertEquals(n+1, m.longValue());
		
		Page<Notification> notifications = client.listNotifications(filter, null);
		assertNotNull(notifications);
		assertNotNull(notifications.getContent());
		assertFalse(notifications.getContent().isEmpty());
		for (Notification notification: notifications) {
			System.out.println(notification);			
		}
 	}

	public Event makeEvent() {
		return makeEvent(SourceType.DOCUMENT, ActionType.CREATE);
	}



	public static Event makeEvent(String sourceType, String actionType) {
		Source source = (Source)new Source().withType(sourceType).withId("TDD");
		Action action = new Action(actionType);
		PrincipalDetails principal = new PrincipalDetails(TEST_USER);
		Meta meta = new Meta().withPriority(PriorityType.NORMAL);
		Target target1 = Target.user(TEST_USER2);
		Target target2 = Target.user(TEST_USER3);

		Event event = (Event)new Event()
				.withSource(source)
				.withAction(action)
				.withMeta(meta)
				.withPrincipal(principal)
				.withTargets(target1, target2)
				//.withTargets(TargetParser.makeTargets(..))
				;
		return event;
	}
	
	@Test
	public void registerTest() {
		Event event = makeEvent();
		System.out.println(event);
		Application application = new Application();
		application.setId("TestApp");
		List<NotificationType> types = makeNotificationTypes(application.getId());
		NotificationsRegistration registration = new NotificationsRegistration().withApplication(application).withTypes(types);
		client.register(registration);
	}
	
	
	public List<NotificationType> makeNotificationTypes(String app) {
		List<NotificationType> types = new ArrayList<>();
		NotificationType type = new NotificationType()
				.withApp(app)
				.withSourceType(SourceType.DOCUMENT)
				.withActionType(ActionType.CREATE)
				.withDescription("Notified when a Document is Created")
				.withCategory(SourceType.MESSAGE)
				.withOrder(1);
		types.add(type);


		NotificationType type2 = new NotificationType()
				.withApp(app)
				.withSourceType(SourceType.DOCUMENT)
				.withActionType(ActionType.UPDATE)
				.withDescription("Notified when a Document is Updated")
				.withCategory(SourceType.MESSAGE)
				.withOrder(1);
		types.add(type2);
		

		NotificationType type3 = new NotificationType()
				.withApp(app)
				.withSourceType(SourceType.DOCUMENT)
				.withActionType(ActionType.DELETE)
				.withDescription("Notified when a Document is Deleted")
				.withCategory(SourceType.MESSAGE)
				.withOrder(1);
		type3.setApp(app);
		types.add(type3);
		return types;
	}
	
	public List<NotificationType> makeNotificationTypes2(String app) {
		List<NotificationType> types = new ArrayList<>();
		NotificationType type = new NotificationType()
				.withApp(app)
				.withSourceType(SourceType.MESSAGE)
				.withActionType(ActionType.CREATE)
				.withDescription("Notified when a Message arrives in mailbox")
				.withCategory(SourceType.MESSAGE)
				.withOrder(1);
		types.add(type);
		NotificationType type2 = new NotificationType()
				.withApp(app)
				.withSourceType(SourceType.MESSAGE)
				.withActionType(ActionType.CREATE)
				.withPriority(PriorityType.NORMAL)
				.withDescription("Notified when a Urgent Message arrives in mailbox")
				.withCategory(SourceType.MESSAGE)
				.withOrder(1);
		types.add(type2);
		return types;
	}
	
	private void sleep(long s) {
		try {
			synchronized (this) {
				this.wait(s);				
			}
		} catch (InterruptedException | IllegalMonitorStateException e) {
		}
	}
	

	@Test
	public void getPreferencesTest() {
		Map<String, Preference> map = client.getPreferences(null);
		assertNotNull(map);
		for (Map.Entry<String, Preference> e: map.entrySet()) {
			System.out.println(e.getKey() + " = " + e.getValue());
		}
 	}

	@Autowired
	PreferencesManagerImpl prefManager;
	
	@Test
	public void getValuesTest() {
		Map<String, Object> map = prefManager.getAllValuesForUser(null);
		assertNotNull(map);
		for (Map.Entry<String, Object> e: map.entrySet()) {
			System.out.println(e.getKey() + " = " + e.getValue());
		}
 	}

}
