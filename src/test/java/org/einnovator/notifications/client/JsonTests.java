package org.einnovator.notifications.client;

import static org.junit.Assert.*;

import org.junit.Test;

import org.einnovator.util.MappingUtils;
import org.einnovator.notifications.client.model.Event;
import org.einnovator.notifications.client.model.EventType;
import org.einnovator.notifications.client.model.Notification;
import org.einnovator.notifications.client.model.ValuePreference;

public class JsonTests {

	@Test
	public void test() {
		
		Event event = new Event();
		ValuePreference pref = new ValuePreference();
		event.setPreference(pref);
		event.setType(EventType.PREFERENCE);
		pref.setKey("testkey");
		pref.setValue("testvalue");
		String s = MappingUtils.toJson(event);
		System.out.println(s);
		assertNotNull(s);
		Event event2 = MappingUtils.fromJson(s, Event.class);		
		System.out.println(event2);
		assertNotNull(event2);
	}

	
	@Test
	public void test2() {
		
		Notification notification = new Notification();
		ValuePreference pref = new ValuePreference();
		notification.setPreference(pref);
		pref.setKey("testkey");
		pref.setValue("testvalue");
		String s = MappingUtils.toJson(notification);
		System.out.println(s);
		assertNotNull(s);
		Notification notification2 = MappingUtils.fromJson(s, Notification.class);		
		System.out.println(notification2);
		assertNotNull(notification2);
	}	
}
