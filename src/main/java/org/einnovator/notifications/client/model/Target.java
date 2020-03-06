package org.einnovator.notifications.client.model;

import java.util.Arrays;

import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Target extends ObjectBase2 {
	
	private Medium[] mediums;
	
	private String email;
	
	private String phone;

	private String contextId; //Role -> Group; Group -> Parent | Root

	/**
	 * Create instance of {@code Target}.
	 *
	 */
	public Target() {
		super();
	}

	//
	// Getters/Setters
	//


	/**
	 * Get the value of property {@code mediums}.
	 *
	 * @return the mediums
	 */
	public Medium[] getMediums() {
		return mediums;
	}

	/**
	 * Set the value of property {@code mediums}.
	 *
	 * @param mediums the value of property mediums
	 */
	public void setMediums(Medium[] mediums) {
		this.mediums = mediums;
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
	 * Get the value of property {@code phone}.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Set the value of property {@code phone}.
	 *
	 * @param phone the value of property phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Get the value of property {@code contextId}.
	 *
	 * @return the contextId
	 */
	public String getContextId() {
		return contextId;
	}

	/**
	 * Set the value of property {@code contextId}.
	 *
	 * @param contextId the value of property contextId
	 */
	public void setContextId(String contextId) {
		this.contextId = contextId;
	}


	//
	// With
	//
	
	//
	// With
	//

	/**
	 * Set the value of property {@code type}.
	 *
	 * @param type the value of property type
	 * @return this {@code Target}
	 */
	public Target withType(TargetType type) {
		this.type = type!=null ? type.name() : null;
		return this;
	}
	/**
	 * Set the value of property {@code mediums}.
	 *
	 * @param mediums the value of property mediums
	 * @return this {@code Target}
	 */
	public Target withMediums(Medium[] mediums) {
		this.mediums = mediums;
		return this;
	}
	
	/**
	 * Set the value of property {@code email}.
	 *
	 * @param email the value of property email
	 * @return this {@code Target}
	 */
	public Target withEmail(String email) {
		this.email = email;
		return this;
	}

	/**
	 * Set the value of property {@code phone}.
	 *
	 * @param phone the value of property phone
	 * @return this {@code Target}
	 */
	public Target withPhone(String phone) {
		this.phone = phone;
		return this;
	}

	/**
	 * Set the value of property {@code contextId}.
	 *
	 * @param contextId the value of property contextId
	 * @return this {@code Target}
	 */
	public Target withContextId(String contextId) {
		this.contextId = contextId;
		return this;
	}


	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
			.append("email", email)
			.append("phone", phone)
			.append("mediums", mediums!=null ? Arrays.toString(mediums) : null)
			;
	}
	
	//
	// Static Factory Methods 
	//
	
	public static Target user(String username) {
		return (Target)new Target().withId(username);
	}
}
