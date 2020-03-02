/**
 * 
 */
package org.einnovator.notifications.client;

import java.util.List;
import java.util.Map;

import org.einnovator.notifications.client.model.Action;
import org.einnovator.notifications.client.model.Event;
import org.einnovator.notifications.client.model.Meta;
import org.einnovator.notifications.client.model.PrincipalDetails;
import org.einnovator.notifications.client.model.Source;
import org.einnovator.notifications.client.model.Target;
import org.einnovator.notifications.client.model.TargetParser;
import org.einnovator.util.MappingUtils;
import org.einnovator.util.event.EventFactory;


/**
 * @author support@einnovator.org
 *
 */
public class SimpleEventFactory implements EventFactory {

	/* 
	 * @see org.einnovator.notifications.client.support.EventFactory#makeEvent(java.lang.Object, java.lang.String, java.lang.String, java.lang.Object[])
	 */
	@Override
	public Event makeEvent(String principal, Object source, Object param, String actionType, Object... targets) {
		
		Source source2 = makeEventSource(source);
		Source param2 = makeEventParam(param);
		
		Action action = (Action)new Action()
				.withType(actionType)
				.withId(null)
				.withName(actionType);
		
		PrincipalDetails principalx = makeEventPrincipal(principal);
		
		
		Meta meta = new Meta();
		
		List<Target> targets2 = TargetParser.makeTargets(targets);
		
		Event event = (Event)new Event()
				.withSource(source2)
				.withParam(param2)
				.withAction(action)
				.withMeta(meta)
				.withPrincipal(principalx)
				.withTargets(targets2);
		return event;
	}

	
	protected Source makeEventSource(Object obj) {
		if (obj instanceof Source) {
			return (Source)obj;
		}
		Map<String, Object> details = makeSourceDetails(obj);
		String id = getSourceId(obj);
		if (id==null && details!=null) {
			id = getSourceId(details);
		}
		return (Source)new Source()
				.withType(obj.getClass().getSimpleName())
				.withId(id)
				.withDetails(details);
	}


	protected String getSourceId(Object obj) {
		return null;
	}

	protected String getSourceId(Map<String, Object> details) {
		return (String)details.get("uuid");
	}


	protected Source makeEventParam(Object obj) {
		if (obj==null) {
			return null;
		}
		return makeEventSource(obj);
	}

	protected Map<String, Object> makeSourceDetails(Object obj) {
		return MappingUtils.toMap(obj);
	}
	
	protected PrincipalDetails makeEventPrincipal(String user) {
		String id = user;
		String name = id;
		return new PrincipalDetails(id, name);
	}
	
}
