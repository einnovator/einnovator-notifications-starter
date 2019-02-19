/**
 * 
 */
package org.einnovator.notifications.client;

import org.einnovator.notifications.client.model.Event;

/**
 * @author jsima
 *
 */
public interface EventFactory {

	Event makeEvent(Object obj, String principal, String actionType, Object... targets);
}
