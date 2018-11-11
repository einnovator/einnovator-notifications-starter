package org.einnovator.notifications.client.model;

import java.util.Date;

public class MetaBuilder {

	private Integer priority;

	private Date expires;

	public MetaBuilder() {
	}
	
	public MetaBuilder priority(Integer priority) {
		this.priority = priority;
		return this;
	}

	public MetaBuilder expires(Date expires) {
		this.expires = expires;
		return this;
	}

	public Meta build() {
		Meta meta = new Meta();
		meta.setPriority(priority);
		meta.setExpires(expires);
		return meta;
	}
}
