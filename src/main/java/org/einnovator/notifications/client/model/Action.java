package org.einnovator.notifications.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Action extends ObjectBase2 {

	// CRUD
	public static final String ACTION_TYPE_CREATE = "Create";
	public static final String ACTION_TYPE_UPDATE = "Update";
	public static final String ACTION_TYPE_DELETE = "Delete";
	public static final String ACTION_TYPE_ASSIGN = "Assign";

	// Users
	public static final String ACTION_TYPE_JOIN = "Join";
	public static final String ACTION_TYPE_JOIN_ORGANIZATION = "Join Organization";
	public static final String ACTION_TYPE_BLOCKED = "Blocked";
	public static final String ACTION_TYPE_REQUEST_CONNECT = "Connect Request";
	public static final String ACTION_TYPE_REQUEST_JOIN_ORGANIZATION = "Join Organization Request";
	public static final String ACTION_TYPE_LOGIN = "Login";
	public static final String ACTION_TYPE_LOGOUT = "Logout";

	// Invitations
	public static final String ACTION_TYPE_ACCEPTED = "Accepted";
	public static final String ACTION_TYPE_REJECTED = "Rejected";

	// Generic
	public static final String ACTION_TYPE_SUCCESS = "Success";
	public static final String ACTION_TYPE_FAILED = "Failed";

	// Document
	public static final String ACTION_TYPE_UPLOAD = "Upload";
	public static final String ACTION_TYPE_DOWNLOAD = "Download";
	public static final String ACTION_TYPE_EXPIRE = "Expire";
	public static final String ACTION_TYPE_SHARED = "Shared";
	public static final String ACTION_TYPE_UNSHARED = "UnShared";
	public static final String ACTION_TYPE_VIEWED = "Viewed";
	public static final String ACTION_TYPE_VIEWED_FIRST_TIME = "Viewed First Time";
	public static final String ACTION_TYPE_REQUEST_AUTH = "Authentication Request";
	public static final String ACTION_TYPE_AUTH_ACCEPTED = "Authentication Accepted";
	public static final String ACTION_TYPE_AUTH_CANCELED = "Authentication Canceled";
	public static final String ACTION_TYPE_AUTH_PENDING = "Authentication Pending";

	// Payments
	public static final String ACTION_TYPE_COMPLETED = "Completed";
	public static final String ACTION_TYPE_SUBMITTED = "Submitted";
	public static final String ACTION_TYPE_CANCELED = "Canceled";
	public static final String ACTION_TYPE_CANCELED_BY_SITE = "CanceledBySite";
	public static final String ACTION_TYPE_PENDING = "Pending";

	public static final String ACTION_TYPE_TRANFER_PREFIX = "Transfer:";

	public static final String ACTION_TYPE_TRANFER_COMPLETED = ACTION_TYPE_TRANFER_PREFIX + ACTION_TYPE_COMPLETED;
	public static final String ACTION_TYPE_TRANFER_SUBMITTED = ACTION_TYPE_TRANFER_PREFIX + ACTION_TYPE_SUBMITTED;
	public static final String ACTION_TYPE_TRANFER_FAILED = ACTION_TYPE_TRANFER_PREFIX + ACTION_TYPE_FAILED;
	public static final String ACTION_TYPE_TRANFER_PENDING = ACTION_TYPE_TRANFER_PREFIX + ACTION_TYPE_PENDING;

	// Message (and Invitations)
	public static final String ACTION_TYPE_SENT = "Sent";
	public static final String ACTION_TYPE_RECEIVED = "Received";

	// Selections
	public static final String ACTION_TYPE_SELECT = "Select";
	public static final String ACTION_TYPE_PROFILE = "Profile";
	public static final String ACTION_TYPE_MSELECT = "MultiSelect";
	public static final String ACTION_TYPE_MPROFILE = "MultiProfile";

	public static final String ACTION_TYPE_REQUESTED = "Requested";
	public static final String ACTION_TYPE_ASSIGNED = "Assigned";

	public Action() {
		super();
	}

	public Action(String type, String id, String name) {
		super(type, id, name);
	}

	@Override
	public String toString() {
		return "Action [" + (getType() != null ? "type=" + getType() + ", " : "")
				+ (getId() != null ? "id=" + getId() + ", " : "") + (getName() != null ? "name=" + getName() : "")
				+ (getUri() != null ? "uri=" + getUri() : "") + (getDetails() != null ? "uri=" + getDetails() : "")
				+ "]";
	}

}
