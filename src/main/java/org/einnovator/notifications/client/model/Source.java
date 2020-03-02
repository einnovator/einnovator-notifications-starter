package org.einnovator.notifications.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * A event {@code Source}.
 * 
 * Descriptor for the object or entity the the event action refers to.
 *
 * @author support@einnovator.org
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Source extends ObjectBase2 {


	//
	// Constructors
	//	

	/**
	 * Create instance of {@code Source}.
	 *
	 */
	public Source() {
		super();
	}
	
	/**
	 * Create instance of {@code Source}.
	 *
	 * @param obj a prototype
	 */
	public Source(Object obj) {
		super(obj);
	}

}
