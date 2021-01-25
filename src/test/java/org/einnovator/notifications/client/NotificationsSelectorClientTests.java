package org.einnovator.notifications.client;

import static org.einnovator.notifications.client.amqp.NotificationListenerContainer.checkDelivery;
import static org.einnovator.notifications.client.amqp.NotificationListenerAdapter.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import org.einnovator.notifications.client.ActionSelector;
import org.einnovator.notifications.client.NotificationSelector;
import org.einnovator.notifications.client.NotificationsClient;
import org.einnovator.notifications.client.SourceSelector;
import org.einnovator.notifications.client.amqp.NotificationListener;
import org.einnovator.notifications.client.amqp.NotificationListenerContainer;
import org.einnovator.notifications.client.config.NotificationsClientConfig;
import org.einnovator.notifications.client.config.NotificationsClientConfiguration;
import org.einnovator.notifications.client.model.Action;
import org.einnovator.notifications.client.model.ActionType;
import org.einnovator.notifications.client.model.Event;
import org.einnovator.notifications.client.model.Notification;
import org.einnovator.notifications.client.model.Source;
import org.einnovator.notifications.client.model.SourceType;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=WebEnvironment.NONE, classes= {NotificationsClientConfig.class,NotificationsSelectorClientTests.TestConfig.class})
@TestPropertySource(properties= {"notifications.uri=http://localhost:2011"})
public class NotificationsSelectorClientTests {

	public static final String TEST_USERNAME = "tdd@einnovator.org";
	public static final String TEST_USERNAME2 = "jsimao71@gmail.com";
	public static final String TEST_USERNAME3 = "web@einnovator.org";

	@Autowired
	NotificationsClient client;
	
	@Autowired
	NotificationsClientConfiguration config;
	
	
	
	@Autowired
	NotificationListener listener;
	
	
	@Autowired
	NotificationListener documentListener;
	
	@Autowired
	NotificationListener documentUploadListener;
	
	@Autowired
	NotificationListener testListener;
	

	@Autowired
	NotificationListener testListener2;
	
	
	@Autowired
	Object pojoListener;
	
	@Autowired
	Object pojoDocumentListener;
	
	@Autowired
	Errors errors;
	
	public static class Document {
		public Document() {}
	}
	
	static class Errors {
		List<String> errors = new ArrayList<>();
		public Errors() {}
		String push(String error) { errors.add(error); return error;}
	}
	
	@Configuration
	public static class TestConfig {
		@Bean
		public Errors errors() {
			return new Errors();
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
		
		@Bean
		public NotificationListener documentListener() {
			return new NotificationListener() {
				@Autowired Errors errors;

				@Override
				@NotificationSelector(source=@SourceSelector(SourceType.DOCUMENT))
				public void onNotification(Notification notification) throws Exception {
					System.out.println(" DOCUMENT >>> " + notification);
					if (!notification.getSource().getType().equals(SourceType.DOCUMENT)) {
						System.out.println(errors.push(" ERROR: BAD DELIVERY : DOCUMENT >>> " + notification));
					}
				}
			};
		}
		
		@Bean
		public NotificationListener testListener() {
			return new NotificationListener() {
				@Autowired Errors errors;

				@Override
				@NotificationSelector(source=@SourceSelector(SourceType.TEST))
				public void onNotification(Notification notification) throws Exception {
					System.out.println(" TEST >>> " + notification);
					if (!notification.getSource().getType().equals(SourceType.TEST)) {
						System.out.println(errors.push(" ERROR: BAD DELIVERY : TEST >>> " + notification));
					}
				}
			};
		}
		
		@Bean
		public NotificationListener testListener2() {
			return new TESTListener();
		}
		
		@NotificationSelector(source=@SourceSelector(SourceType.TEST))
		public static class TESTListener implements NotificationListener {
			@Autowired Errors errors;

			@Override
			public void onNotification(Notification notification) throws Exception {
				System.out.println(" TEST >>> " + notification);
				if (!notification.getSource().getType().equals(SourceType.TEST)) {
					System.out.println(errors.push(" ERROR: BAD DELIVERY : TEST >>> " + notification));
				}
			}
			
		}
		
		@Bean
		public NotificationListener documentUploadListener() {
			return new DocumentUploadListener();
		}
		
		@NotificationSelector(source=@SourceSelector(SourceType.DOCUMENT), action=@ActionSelector(ActionType.UPLOAD))
		public static class DocumentUploadListener implements NotificationListener {
			@Autowired Errors errors;

			@Override
			public void onNotification(Notification notification) throws Exception {
				System.out.println(" Document Upload >>> " + notification);
				if (!notification.getSource().getType().equals(SourceType.DOCUMENT) ||
						!notification.getAction().getType().equals(ActionType.UPLOAD)) {
					System.out.println(errors.push(" ERROR: BAD DELIVERY : SOURCE/ACTION >>> " + notification));
				}
			}	
		}
		

		@Bean
		public Object pojoListener() {
			return new Object() {
				@Autowired Errors errors;

				@NotificationSelector
				public void processNotification(Notification notification, Source source, Action action) {
					System.out.println(" Notification >>> " + source + " " + action + " " + notification);
				}
				
				@NotificationSelector		
				public void processSourceAction(Source source, Action action) {
					System.out.println(" Source >>> " + source + " Action >>> " + action + " ");
				}

				@SuppressWarnings("unused")
				public void badProcessNotification() {
					System.out.println(" badProcessNotification >>> ");
				}

				@NotificationSelector(source=@SourceSelector(SourceType.DOCUMENT))
				public void processDocumentNotification(Notification notification) {
					System.out.println(" Document Notification >>> " + notification);
					if (!notification.getSource().getType().equals(SourceType.DOCUMENT)) {
						System.out.println(errors.push(" ERROR: BAD DELIVERY : processDocumentNotification " + notification));
					}
				}

			};
		}
		
		@NotificationSelector(source=@SourceSelector(SourceType.DOCUMENT))
		public static class PojoDocumentListener {
			@Autowired Errors errors;

			@NotificationSelector(action=@ActionSelector(ActionType.UPLOAD))			
			public void onDocumentUpload(Document document, Notification notification) throws Exception {
				System.out.println(" POJO Document Upload >>> " + document);
				if (!notification.getSource().getType().equals(SourceType.DOCUMENT) ||
						!notification.getAction().getType().equals(ActionType.UPLOAD)) {
					System.out.println(errors.push(" ERROR: BAD DELIVERY : POJO Document Upload >>> DOCUMENT SOURCE/ACTION >>> " + notification));
				}
			}	
			
			@NotificationSelector(action=@ActionSelector(ActionType.CREATE))			
			public void onDocumentCreate(Document document, Source source, Notification notification) throws Exception {
				System.out.println(" POJO Document Create >>> " + document + " " + source + " " + notification);
				if (!notification.getSource().getType().equals(SourceType.DOCUMENT) || 
						!notification.getAction().getType().equals(ActionType.CREATE)) {
					System.out.println(errors.push(" ERROR: BAD DELIVERY : POJO Document Create >>> DOCUMENT SOURCE/ACTION >>> " + notification));
				}
			}
		}
		@Bean
		public PojoDocumentListener pojoDocumentListener() {
			return new PojoDocumentListener();
		}
		
	}
	
	@Test
	public void publishEventHttpTest() {
		Notification notification = makeNotification(SourceType.DOCUMENT, ActionType.CREATE);
		assertTrue(checkDelivery(notification, listener));
		assertTrue(checkDelivery(notification, documentListener));
		assertFalse(checkDelivery(notification, documentUploadListener));
		assertFalse(checkDelivery(notification, testListener));
		assertFalse(checkDelivery(notification, testListener2));
		assertTrue(checkDelivery(notification, pojoListener.getClass(), findRequiredSingleMethod(pojoListener.getClass(), "processNotification")));
		assertTrue(checkDelivery(notification, pojoListener.getClass(), findRequiredSingleMethod(pojoListener.getClass(), "processSourceAction")));
		assertTrue(checkDelivery(notification, pojoListener.getClass(), findRequiredSingleMethod(pojoListener.getClass(), "processDocumentNotification")));
		assertFalse(checkDelivery(notification, pojoListener.getClass(), findRequiredSingleMethod(pojoListener.getClass(), "badProcessNotification")));
		assertFalse(checkDelivery(notification, pojoDocumentListener.getClass(), findRequiredSingleMethod(pojoDocumentListener.getClass(), "onDocumentUpload")));
		assertTrue(checkDelivery(notification, pojoDocumentListener.getClass(), findRequiredSingleMethod(pojoDocumentListener.getClass(), "onDocumentCreate")));

		

		notification = makeNotification(SourceType.DOCUMENT, ActionType.UPLOAD);
		assertTrue(checkDelivery(notification, listener));
		assertTrue(checkDelivery(notification, documentListener));
		assertTrue(checkDelivery(notification, documentUploadListener));
		assertFalse(checkDelivery(notification, testListener));
		assertFalse(checkDelivery(notification, testListener2));
		assertTrue(checkDelivery(notification, pojoListener.getClass(), findRequiredSingleMethod(pojoListener.getClass(), "processNotification")));
		assertTrue(checkDelivery(notification, pojoListener.getClass(), findRequiredSingleMethod(pojoListener.getClass(), "processSourceAction")));
		assertTrue(checkDelivery(notification, pojoListener.getClass(), findRequiredSingleMethod(pojoListener.getClass(), "processDocumentNotification")));
		assertTrue(checkDelivery(notification, pojoDocumentListener.getClass(), findRequiredSingleMethod(pojoDocumentListener.getClass(), "onDocumentUpload")));
		assertFalse(checkDelivery(notification, pojoDocumentListener.getClass(), findRequiredSingleMethod(pojoDocumentListener.getClass(), "onDocumentCreate")));

		notification = makeNotification(SourceType.TEST, ActionType.CREATE);
		assertTrue(checkDelivery(notification, listener));
		assertFalse(checkDelivery(notification, documentListener));
		assertFalse(checkDelivery(notification, documentUploadListener));
		assertTrue(checkDelivery(notification, testListener));
		assertTrue(checkDelivery(notification, testListener2));
		assertTrue(checkDelivery(notification, pojoListener.getClass(), findRequiredSingleMethod(pojoListener.getClass(), "processNotification")));
		assertTrue(checkDelivery(notification, pojoListener.getClass(), findRequiredSingleMethod(pojoListener.getClass(), "processSourceAction")));
		assertFalse(checkDelivery(notification, pojoListener.getClass(), findRequiredSingleMethod(pojoListener.getClass(), "processDocumentNotification")));
		assertFalse(checkDelivery(notification, pojoDocumentListener.getClass(), findRequiredSingleMethod(pojoDocumentListener.getClass(), "onDocumentUpload")));
		assertFalse(checkDelivery(notification, pojoDocumentListener.getClass(), findRequiredSingleMethod(pojoDocumentListener.getClass(), "onDocumentCreate")));

		Event event = makeEvent(SourceType.DOCUMENT, ActionType.CREATE);
		System.out.println(event);
		client.publishEventHttp(event, null);
		
		event = makeEvent(SourceType.DOCUMENT, ActionType.UPLOAD);
		System.out.println(event);
		client.publishEventHttp(event, null);
		
		event = makeEvent(SourceType.TEST, ActionType.CREATE);
		System.out.println(event);
		client.publishEventHttp(event, null);
		
		sleep(1000);
		if (!errors.errors.isEmpty()) {
			for (String error: errors.errors) {
				System.out.println(error);
			}
		}
		assertTrue(errors.errors.isEmpty());
	}
	
	

	public Event makeEvent(String sourceType, String actionType) {
		return NotificationsClientTests.makeEvent(sourceType, actionType);
	}
	
	public Notification makeNotification(String sourceType, String actionType) {
		Notification notification = new Notification();
		Source source = (Source)new Source().withType(sourceType);
		Action action = new Action(actionType, null, null);
		notification.setSource(source);
		notification.setAction(action);
		return notification;
	}
	
	private void sleep(long s) {
		try {
			synchronized (this) {
				this.wait(s);				
			}
		} catch (InterruptedException | IllegalMonitorStateException e) {
		}
	}
	
}
