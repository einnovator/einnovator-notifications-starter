package org.einnovator.notifications.client.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Source extends ObjectBase {

	public static final String SOURCE_TYPE_USER = "User";

	public static final String SOURCE_TYPE_GROUP = "Group";

	public static final String SOURCE_TYPE_ORGANIZATION = "Organization";

	public static final String SOURCE_TYPE_INVITATION = "Invitation";

	public static final String SOURCE_TYPE_ROLE = "Role";

	public static final String SOURCE_TYPE_DOCUMENT = "Document";

	public static final String SOURCE_TYPE_FOLDER = "Folder";

	public static final String SOURCE_TYPE_MESSAGE = "Message";

	public static final String SOURCE_TYPE_APPLICATION = "Application";

	public static final String SOURCE_TYPE_SETTING = "Setting";
	
	public static final String SOURCE_TYPE_PAYMENT = "Payment";

	public static final String SOURCE_TYPE_RFQ = "RFQ";

	public static final String SOURCE_TYPE_QUOTE = "Quote";

	public static final String SOURCE_TYPE_AUDIT = "Audit";
	
	public static final String SOURCE_TYPE_AUDITOR = "Auditor";

	public static final String SOURCE_TYPE_REQUIREMENT = "Requirement";
	
	public static final String SOURCE_TYPE_CONTRACT = "Contract";
	
	public static final String SOURCE_TYPE_AUDIT_DETAILS = "AuditDetails";
	
	public static final String SOURCE_TYPE_AUDIT_PLAN = "AuditPlan";
	


	public Source() {
		super();
	}

	public Source(String type, String id, String name) {
		super(type, id, name);
	}

	public Source(String type, String id, String name, Map<String, Object> details) {
		super(type, id, name, details);
	}

	public Source(String type, String id, Map<String, Object> details) {
		super(type, id, details);
	}

	@Override
	public String toString() {
		return "Source [" + (getType() != null ? "type=" + getType() + ", " : "")
				+ (getId() != null ? "id=" + getId() + ", " : "")
				+ (getName() != null ? "name=" + getName() + ", "  : "") + "]"
				+ (getUri() != null ? "uri=" + getUri() + ", "  : "") 
				+ (getDetails() != null ? "details=" + getDetails() : "");
	}

	
}
