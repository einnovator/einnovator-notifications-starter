package org.einnovator.notifications.client.model;

import java.util.ArrayList;
import java.util.List;

public class Delivery {

	private Notification notification;
		
	private List<Target> targets;
	
	public Delivery() {
	}

	public Delivery(Notification notification) {
		this.notification = notification;
	}

	public Delivery(Notification notification, List<Target> targets) {
		this.targets = targets;
	}

	public Delivery(Notification notification, Target target) {
		this.targets = new ArrayList<>();
		this.targets.add(target);
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public List<Target> getTargets() {
		return targets;
	}

	public void setTargets(List<Target> targets) {
		this.targets = targets;
	}
	
	public Target getTarget() {
		if (targets==null) {
			return null;
		}
		if (targets.size()==0) {
			return null;
		}
		return targets.get(0);
	}

	@Override
	public String toString() {
		return "Delivery [" 
				+ (notification != null ? "type=" + notification + ", " : "")
				+ (targets != null ? "targets=" + targets + ", " : "")
				+ "]";
	}
	
	
}
