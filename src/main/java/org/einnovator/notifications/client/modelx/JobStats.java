/**
 * 
 */
package org.einnovator.notifications.client.modelx;

import java.util.LinkedHashMap;
import java.util.Map;

import org.einnovator.notifications.client.model.JobStatus;
import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * A descriptor with {@code Job} statistics.
 *
 * @author support@einnovator.org
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobStats extends ObjectBase {

	private Long total;

	private Map<JobStatus, Long> counts = new LinkedHashMap<>();

	//
	// Constructors
	//

	/**
	 * Create instance of {@code JobStats}.
	 *
	 */
	public JobStats() {
	}
	
	/**
	 * Get the value of property {@code total}.
	 *
	 * @return the total
	 */
	public Long getTotal() {
		return total;
	}


	/**
	 * Set the value of property {@code total}.
	 *
	 * @param total the total to set
	 */
	public void setTotal(Long total) {
		this.total = total;
	}

	
	/**
	 * Get the value of property {@code counts}.
	 *
	 * @return the counts
	 */
	public Map<JobStatus, Long> getCounts() {
		return counts;
	}

	/**
	 * Set the value of property {@code counts}.
	 *
	 * @param counts the counts to set
	 */
	public void setCounts(Map<JobStatus, Long> counts) {
		this.counts = counts;
	}

	@Override
	public ToStringCreator toString(ToStringCreator creator) {
		return super.toString(creator
				.append("total", total)
				.append("counts", counts)
				);
	}

}
