package org.einnovator.notifications.client.modelx;

import org.einnovator.notifications.client.model.ContentType;
import org.einnovator.notifications.client.model.Medium;
import org.einnovator.notifications.client.model.TemplateCategory;
import org.einnovator.util.model.ToStringCreator;

public class TemplateFilter extends TemplateOptions {
	
	private String q;
	
	private Boolean strict;

	private Medium medium;

	private TemplateCategory category;

	private ContentType contentType;
	
	
	public TemplateFilter() {
	}
	
	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	
	/**
	 * Get the value of property {@code strict}.
	 *
	 * @return the strict
	 */
	public Boolean getStrict() {
		return strict;
	}

	/**
	 * Set the value of property {@code strict}.
	 *
	 * @param strict the strict to set
	 */
	public void setStrict(Boolean strict) {
		this.strict = strict;
	}
	

	/**
	 * Get the value of property {@code medium}.
	 *
	 * @return the medium
	 */
	public Medium getMedium() {
		return medium;
	}

	/**
	 * Set the value of property {@code medium}.
	 *
	 * @param medium the medium to set
	 */
	public void setMedium(Medium medium) {
		this.medium = medium;
	}

	/**
	 * Get the value of property {@code category}.
	 *
	 * @return the category
	 */
	public TemplateCategory getCategory() {
		return category;
	}

	/**
	 * Set the value of property {@code category}.
	 *
	 * @param category the category to set
	 */
	public void setCategory(TemplateCategory category) {
		this.category = category;
	}

	
	/**
	 * Get the value of property {@code contentType}.
	 *
	 * @return the contentType
	 */
	public ContentType getContentType() {
		return contentType;
	}

	/**
	 * Set the value of property {@code contentType}.
	 *
	 * @param contentType the contentType to set
	 */
	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}

	@Override
	public ToStringCreator toString(ToStringCreator creator) {
		return creator
			.append("q", q)
			.append("strict", strict)
			.append("medium", medium)
			.append("category", category)
			.append("contentType", contentType)
			;
	}

	
}
