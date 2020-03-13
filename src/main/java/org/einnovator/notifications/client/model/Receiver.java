package org.einnovator.notifications.client.model;

import java.util.Date;

import org.einnovator.util.model.EntityBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * A {@code Receiver} for a {@code Job} message.
 *
 * @author support@einnovator.org
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Receiver extends EntityBase {


	private ReceiverType type;

	private SendStatus status;

	private SystemGroupType system;

	private String receiverId;

	private String username;

	private String name;

	private String email;

	private String mobile;

	private Date sendDate;

	private Integer retries;
	

	//
	// Constructors
	//
	
	/**
	 * Create instance of {@code Receiver}.
	 *
	 */
	public Receiver() {
	}

	//
	// Getters/Setters
	//
	
	/**
	 * Get the value of property {@code type}.
	 *
	 * @return the type
	 */
	public ReceiverType getType() {
		return type;
	}

	/**
	 * Set the value of property {@code type}.
	 *
	 * @param type the value of property type
	 */
	public void setType(ReceiverType type) {
		this.type = type;
	}

	/**
	 * Get the value of property {@code status}.
	 *
	 * @return the status
	 */
	public SendStatus getStatus() {
		return status;
	}

	/**
	 * Set the value of property {@code status}.
	 *
	 * @param status the value of property status
	 */
	public void setStatus(SendStatus status) {
		this.status = status;
	}

	/**
	 * Get the value of property {@code receiverId}.
	 *
	 * @return the receiverId
	 */
	public String getReceiverId() {
		return receiverId;
	}

	/**
	 * Set the value of property {@code receiverId}.
	 *
	 * @param receiverId the value of property receiverId
	 */
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	/**
	 * Get the value of property {@code username}.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Set the value of property {@code username}.
	 *
	 * @param username the value of property username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Get the value of property {@code email}.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the value of property {@code email}.
	 *
	 * @param email the value of property email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the value of property {@code mobile}.
	 *
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Set the value of property {@code mobile}.
	 *
	 * @param mobile the value of property mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * Get the value of property {@code name}.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the value of property {@code name}.
	 *
	 * @param name the value of property name
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * Get the value of property {@code sendDate}.
	 *
	 * @return the sendDate
	 */
	public Date getSendDate() {
		return sendDate;
	}

	/**
	 * Set the value of property {@code sendDate}.
	 *
	 * @param sendDate the value of property sendDate
	 */
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	/**
	 * Get the value of property {@code retries}.
	 *
	 * @return the retries
	 */
	public Integer getRetries() {
		return retries;
	}

	/**
	 * Set the value of property {@code retries}.
	 *
	 * @param retries the value of property retries
	 */
	public void setRetries(Integer retries) {
		this.retries = retries;
	}

	
	/**
	 * Get the value of property {@code system}.
	 *
	 * @return the system
	 */
	public SystemGroupType getSystem() {
		return system;
	}


	/**
	 * Set the value of property {@code system}.
	 *
	 * @param system the value of property system
	 */
	public void setSystem(SystemGroupType system) {
		this.system = system;
	}


	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
			.append("name", name)
			.append("username", username)
			.append("type", type)
			.append("status", status)
			.append("system", system)
			.append("sendDate", sendDate)
			.append("retries", retries)
			;
	}

}
