package com.alphacode.ppmtool.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Backlog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer PTSeqence = 0;
	
	private String projectIdentifier;
	
	//OneToOne with project
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="project_id",nullable = false)
	@JsonIgnore
	private Project project ;
	
	
	//OneTOMany project tasks
	@OneToMany(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER ,mappedBy = "backlog" )
	private List<ProjectTask> projectTasks = new ArrayList<>();
	
	public Backlog() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPTSeqence() {
		return PTSeqence;
	}

	public void setPTSeqence(Integer pTSeqence) {
		PTSeqence = pTSeqence;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<ProjectTask> getProjectTasks() {
		return projectTasks;
	}

	public void setProjectTasks(List<ProjectTask> projectTasks) {
		this.projectTasks = projectTasks;
	}
	
	
	
	
	
}
