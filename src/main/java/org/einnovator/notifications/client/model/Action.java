package org.einnovator.notifications.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Descriptor of an event {@code Action}.
 *
 * @see Event
 * @author support@einnovator.org
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Action extends ObjectBase2 {

	
	//
	// Constructors
	//
	
	/**
	 * Create instance of {@code Action}.
	 *
	 */
	public Action() {
	}

	/**
	 * Create instance of {@code Action}.
	 *
	 * @param obj a prototype
	 */
	public Action(Object obj) {
		super(obj);
	}

	/**
	 * Create instance of {@code Action}.
	 *
	 * @param type the type
	 * @param id the id
	 * @param name the name
	 */
	public Action(String type, String id, String name) {
		super(type, id, name);
	}

	

}
