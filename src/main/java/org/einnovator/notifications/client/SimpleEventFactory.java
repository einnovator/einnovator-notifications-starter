/**
 * 
 */
package org.einnovator.notifications.client;

import java.util.List;
import java.util.Map;

import org.einnovator.notifications.client.model.Action;
import org.einnovator.notifications.client.model.ActionBuilder;
import org.einnovator.notifications.client.model.Event;
import org.einnovator.notifications.client.model.EventBuilder;
import org.einnovator.notifications.client.model.Meta;
import org.einnovator.notifications.client.model.MetaBuilder;
import org.einnovator.notifications.client.model.PrincipalX;
import org.einnovator.notifications.client.model.PrincipalXBuilder;
import org.einnovator.notifications.client.model.Source;
import org.einnovator.notifications.client.model.SourceBuilder;
import org.einnovator.notifications.client.model.Target;
import org.einnovator.notifications.client.model.TargetParser;
import org.einnovator.util.MappingUtils;


/**
 * @author jsima
 *
 */
public class SimpleEventFactory implements EventFactory {

	/* (non-Javadoc)
	 * @see org.einnovator.notifications.client.support.EventFactory#makeEvent(java.lang.Object, java.lang.String, java.lang.String, java.lang.Object[])
	 */
	@Override
	public Event makeEvent(Object obj, String user, String actionType, Object... targets) {
		
		Source source = makeEventSource(obj);
		Source source2 = makeEventSource2(obj);
		
		Action action = new ActionBuilder()
				.type(actionType)
				.id(null)
				.name(actionType)
				.build();
		
		PrincipalX principal = makeEventPrincipal(user);
		
		
		Meta meta = new MetaBuilder().build();
		
		List<Target> targets2 = TargetParser.makeTargets(targets);
		
		EventBuilder builder = new EventBuilder()
				.source(source)
				.source2(source2)
				.action(action)
				.meta(meta)
				.principal(principal)
				.meta(meta)
				//.details(details)
				.targets(targets2);
		return builder.build();
	}

	
	protected Source makeEventSource(Object obj) {
		Map<String, Object> details = makeSourceDetails(obj);
		String id = getSourceId(obj);
		if (id==null && details!=null) {
			id = (String)details.get("uuid");
		}
		return new SourceBuilder()
				.type(obj.getClass().getSimpleName())
				.id(id)
				.details(details)
				.build();
	}

	/**
	 * @param obj
	 * @return
	 */
	private String getSourceId(Object obj) {
		return null;
	}


	protected Source makeEventSource2(Object obj) {
		return null;
	}

	protected Map<String, Object> makeSourceDetails(Object obj) {
		return MappingUtils.toMap(obj);
	}
	
	protected PrincipalX makeEventPrincipal(String user) {
		String id = user;
		String name = id;
		String img = null;
		return new PrincipalXBuilder()
			.type(PrincipalX.PRINCIPAL_TYPE_USER)
			.id(id)
			.name(name)
			.img(img)
			.build();
	}
	
}
