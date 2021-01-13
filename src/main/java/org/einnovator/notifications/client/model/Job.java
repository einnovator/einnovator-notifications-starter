package org.einnovator.notifications.client.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.einnovator.util.model.EntityBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Job extends EntityBase {

	private String name;

	private String templateName;
	
	private Medium medium;

	private JobStatus status;

	private Date submitDate;
	
	private Date scheduledDate;
	
	private Date startDate;
	
	private Date completedDate;

	private String error;

	private Integer readCount;
	
	private Integer processCount;
	
	private Integer writeCount;
	
	private Integer skipCount;

	private Integer totalCount;

	private Integer progress;

	private Boolean dryrun;
	
	private Boolean test;

	private Boolean async;

	private Integer skip;

	private Integer max;

	private ReceiverType receiverType;

	protected List<Receiver> receivers;

	private SystemGroupType system;

	private String templateId;

	private Map<String, Object> env;
	
	public Job() {
	}
	
	public Job(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
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
	 * @param medium the medium
	 */
	public void setMedium(Medium medium) {
		this.medium = medium;
	}

	/**
	 * Get the value of property {@code env}.
	 *
	 * @return the env
	 */
	public Map<String, Object> getEnv() {
		return env;
	}

	/**
	 * Set the value of property {@code env}.
	 *
	 * @param env the env
	 */
	public void setEnv(Map<String, Object> env) {
		this.env = env;
	}

	/**
	 * Get the value of property {@code submitDate}.
	 *
	 * @return the {@code submitDate}
	 */
	public Date getSubmitDate() {
		return submitDate;
	}


	/**
	 * Set the value of property {@code submitDate}.
	 *
	 * @param submitDate the {@code submitDate}
	 */
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}


	/**
	 * Get the value of property {@code scheduledDate}.
	 *
	 * @return the {@code scheduledDate}
	 */
	public Date getScheduledDate() {
		return scheduledDate;
	}


	/**
	 * Set the value of property {@code scheduledDate}.
	 *
	 * @param scheduledDate the {@code scheduledDate}
	 */
	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	/**
	 * Get the value of property {@code status}.
	 *
	 * @return the {@code status}
	 */
	public JobStatus getStatus() {
		return status;
	}

	/**
	 * Set the value of property {@code status}.
	 *
	 * @param status the {@code status}
	 */
	public void setStatus(JobStatus status) {
		this.status = status;
	}

	/**
	 * Get the value of property {@code startDate}.
	 *
	 * @return the {@code startDate}
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Set the value of property {@code startDate}.
	 *
	 * @param startDate the {@code startDate}
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Get the value of property {@code completedDate}.
	 *
	 * @return the {@code completedDate}
	 */
	public Date getCompletedDate() {
		return completedDate;
	}


	/**
	 * Set the value of property {@code completedDate}.
	 *
	 * @param completedDate the {@code completedDate}
	 */
	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	
	/**
	 * Get the value of property {@code error}.
	 *
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * Set the value of property {@code error}.
	 *
	 * @param error the error
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * Get the value of property {@code readCount}.
	 *
	 * @return the readCount
	 */
	public Integer getReadCount() {
		return readCount;
	}

	/**
	 * Set the value of property {@code readCount}.
	 *
	 * @param readCount the readCount
	 */
	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	/**
	 * Get the value of property {@code processCount}.
	 *
	 * @return the processCount
	 */
	public Integer getProcessCount() {
		return processCount;
	}

	/**
	 * Set the value of property {@code processCount}.
	 *
	 * @param processCount the processCount
	 */
	public void setProcessCount(Integer processCount) {
		this.processCount = processCount;
	}

	/**
	 * Get the value of property {@code writeCount}.
	 *
	 * @return the writeCount
	 */
	public Integer getWriteCount() {
		return writeCount;
	}

	/**
	 * Set the value of property {@code writeCount}.
	 *
	 * @param writeCount the writeCount
	 */
	public void setWriteCount(Integer writeCount) {
		this.writeCount = writeCount;
	}

	/**
	 * Get the value of property {@code skipCount}.
	 *
	 * @return the skipCount
	 */
	public Integer getSkipCount() {
		return skipCount;
	}

	/**
	 * Set the value of property {@code skipCount}.
	 *
	 * @param skipCount the skipCount
	 */
	public void setSkipCount(Integer skipCount) {
		this.skipCount = skipCount;
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
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the value of property {@code templateName}.
	 *
	 * @return the templateName
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * Set the value of property {@code templateName}.
	 *
	 * @param templateName the templateName
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * Get the value of property {@code receivers}.
	 *
	 * @return the receivers
	 */
	public List<Receiver> getReceivers() {
		return receivers;
	}

	/**
	 * Set the value of property {@code receivers}.
	 *
	 * @param receivers the value of property receivers
	 */
	public void setReceivers(List<Receiver> receivers) {
		this.receivers = receivers;
	}

	/**
	 * Get the value of property {@code test}.
	 *
	 * @return the test
	 */
	public Boolean getTest() {
		return test;
	}

	/**
	 * Set the value of property {@code test}.
	 *
	 * @param test the test
	 */
	public void setTest(Boolean test) {
		this.test = test;
	}

	/**
	 * Get the value of property {@code skip}.
	 *
	 * @return the skip
	 */
	public Integer getSkip() {
		return skip;
	}

	/**
	 * Set the value of property {@code skip}.
	 *
	 * @param skip the skip
	 */
	public void setSkip(Integer skip) {
		this.skip = skip;
	}

	/**
	 * Get the value of property {@code max}.
	 *
	 * @return the max
	 */
	public Integer getMax() {
		return max;
	}

	/**
	 * Set the value of property {@code max}.
	 *
	 * @param max the max
	 */
	public void setMax(Integer max) {
		this.max = max;
	}

	/**
	 * Get the value of property {@code templateId}.
	 *
	 * @return the templateId
	 */
	public String getTemplateId() {
		return templateId;
	}

	/**
	 * Set the value of property {@code templateId}.
	 *
	 * @param templateId the value of property templateId
	 */
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	/**
	 * Get the value of property {@code totalCount}.
	 *
	 * @return the value of {@code totalCount}
	 */
	public Integer getTotalCount() {
		return totalCount;
	}

	/**
	 * Set the value of property {@code totalCount}.
	 *
	 * @param totalCount the value of {@code totalCount}
	 */
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * Get the value of property {@code progress}.
	 *
	 * @return the value of {@code progress}
	 */
	public Integer getProgress() {
		return progress;
	}

	/**
	 * Set the value of property {@code progress}.
	 *
	 * @param progress the value of {@code progress}
	 */
	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	/**
	 * Get the value of property {@code dryrun}.
	 *
	 * @return the value of {@code dryrun}
	 */
	public Boolean getDryrun() {
		return dryrun;
	}

	/**
	 * Set the value of property {@code dryrun}.
	 *
	 * @param dryrun the value of {@code dryrun}
	 */
	public void setDryrun(Boolean dryrun) {
		this.dryrun = dryrun;
	}

	/**
	 * Get the value of property {@code async}.
	 *
	 * @return the async
	 */
	public Boolean getAsync() {
		return async;
	}

	/**
	 * Set the value of property {@code async}.
	 *
	 * @param async the async
	 */
	public void setAsync(Boolean async) {
		this.async = async;
	}

	/**
	 * Get the value of property {@code receiverType}.
	 *
	 * @return the receiverType
	 */
	public ReceiverType getReceiverType() {
		return receiverType;
	}

	/**
	 * Set the value of property {@code receiverType}.
	 *
	 * @param receiverType the value of property receiverType
	 */
	public void setReceiverType(ReceiverType receiverType) {
		this.receiverType = receiverType;
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
			.append("templateName", templateName)
			.append("medium", medium)
			.append("status", status)
			.append("readCount", readCount)
			.append("processCount", processCount)
			.append("writeCount", writeCount)
			.append("skipCount", skipCount)
			.append("totalCount", totalCount)
			.append("progress", progress)
			.append("dryrun", dryrun)
			.append("test", test)
			.append("async", async)
			.append("skip", skip)
			.append("max", max)
			.append("submitDate", submitDate)
			.append("scheduledDate", scheduledDate)
			.append("startDate", startDate)
			.append("completedDate", completedDate)
			.append("#receivers", receivers!=null ? receivers.size() : null)
			.append("error", error)
			;
	}


	public void addReceiver(Receiver receiver) {
		if (receivers==null) {
			receivers = new ArrayList<Receiver>();
		}
		receivers.add(receiver);		
	}
	

}
