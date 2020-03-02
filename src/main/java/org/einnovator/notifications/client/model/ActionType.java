/**
 * 
 */
package org.einnovator.notifications.client.model;

/**
 * Utility definitions of common types of {@code Action}s.
 *
 * @see Action
 * @see Event
 * @author support@einnovator.org
 *
 */
public class ActionType {

	//
	// CRUD
	//
	
	public static final String CREATE = "Create";
	public static final String UPDATE = "Update";
	public static final String DELETE = "Delete";
	public static final String UPDATE_MANY = "UpdateMany";
	public static final String DELETE_MANY = "DeleteMany";

	// Users
	public static final String JOIN = "Join";
	public static final String JOIN_ORGANIZATION = "Join Organization";
	public static final String BLOCKED = "Blocked";
	public static final String REQUEST_CONNECT = "Connect Request";
	public static final String REQUEST_JOIN_ORGANIZATION = "Join Organization Request";
	public static final String LOGIN = "Login";
	public static final String LOGOUT = "Logout";

	//
	// Invitations / Requests
	//
	
	public static final String ACCEPTED = "Accepted";
	public static final String REJECTED = "Rejected";

	public static final String REQUEST = "Request";

	public static final String ASSIGN = "Assign";
	public static final String UNASSIGN = "Unassign";

	//
	// Documents
	//

	public static final String SHARED = "Shared";
	public static final String UNSHARED = "UnShared";
	public static final String UPLOAD = "Upload";
	public static final String DOWNLOAD = "Download";
	public static final String EXPIRE = "Expire";
	public static final String VIEWED = "Viewed";
	public static final String VIEWED_FIRST_TIME = "Viewed First Time";

	//
	// Generic
	//
	
	public static final String SUCCESS = "Success";
	public static final String FAILED = "Failed";
	
	//
	// Payments
	//

	public static final String SUBMITTED = "Submitted";
	public static final String COMPLETED = "Completed";
	public static final String CANCELED = "Canceled";
	public static final String PENDING = "Pending";

	public static final String TRANFER_PREFIX = "Transfer-";

	public static final String TRANFER_COMPLETED = TRANFER_PREFIX + COMPLETED;
	public static final String TRANFER_SUBMITTED = TRANFER_PREFIX + SUBMITTED;
	public static final String TRANFER_FAILED = TRANFER_PREFIX + FAILED;
	public static final String TRANFER_PENDING = TRANFER_PREFIX + PENDING;

	//
	// Messages / Invitations
	//
	
	public static final String SENT = "Sent";
	public static final String RECEIVED = "Received";


	//
	// Selections
	//
	
	public static final String SELECT = "Select";
	public static final String MSELECT = "MultiSelect";

}
