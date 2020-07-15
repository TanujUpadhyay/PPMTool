package com.alphacode.ppmtool.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProjectTask {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable = false , unique = true)
	private String projectSequence;
	
	@NotBlank(message = "Please include a project summary")
	private String summary;
	
	private String acceptanceCriterial;
	
	private String status;
	
	private Integer priority;
	
	
	private Date dueDate;
	
	//ManyToOne with backlog
	
	@ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.REFRESH)
	@JoinColumn(name="backlog_id",updatable = false,nullable = false)
	@JsonIgnore
	private Backlog backlog;
	
	@Column(updatable = false)
	private String projectIdentifier;
	
	
	
	private Date create_At;
	
	private Date upate_At;
	
	@PrePersist
	protected void onCreate() {
		this.create_At = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.upate_At = new Date();
	}
	
	
	public ProjectTask() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectSequence() {
		return projectSequence;
	}

	public void setProjectSequence(String projectSequence) {
		this.projectSequence = projectSequence;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAcceptanceCriterial() {
		return acceptanceCriterial;
	}

	public void setAcceptanceCriterial(String acceptanceCriterial) {
		this.acceptanceCriterial = acceptanceCriterial;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPririty() {
		return priority;
	}

	public void setPririty(Integer pririty) {
		this.priority = pririty;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

	public Date getCreate_At() {
		return create_At;
	}

	public void setCreate_At(Date create_At) {
		this.create_At = create_At;
	}

	public Date getUpate_At() {
		return upate_At;
	}

	public void setUpate_At(Date upate_At) {
		this.upate_At = upate_At;
	}
	
	

	public Backlog getBacklog() {
		return backlog;
	}

	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}

	@Override
	public String toString() {
		return "ProjectTask [id=" + id + ", projectSequence=" + projectSequence + ", summary=" + summary
				+ ", acceptanceCriterial=" + acceptanceCriterial + ", status=" + status + ", pririty=" + priority
				+ ", dueDate=" + dueDate + ", projectIdentifier=" + projectIdentifier + ", create_At=" + create_At
				+ ", upate_At=" + upate_At + "]";
	}
	

	
	
}
