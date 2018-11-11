package org.einnovator.notifications.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
abstract public class Preference {

	@JsonIgnore
	public abstract String getName();
}
