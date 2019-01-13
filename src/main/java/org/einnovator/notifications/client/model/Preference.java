package org.einnovator.notifications.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "category",
		defaultImpl=NotifyPreference.class)
@JsonSubTypes({
	@JsonSubTypes.Type(value = ValuePreference.class, name = "Value"),
	@JsonSubTypes.Type(value = NotifyPreference.class, name = "Notify"),
})
abstract public class Preference {

	abstract String getName();
	
	abstract String getCategory();

	abstract void setName(String name);

	abstract void setCategory(String category);

}
