package com.alphacode.ppmtool.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Backlog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer PTSeqence = 0;
	
	private String projectIdentifier;
	
	//OneToOne with project
	
	//OneTOMany project tasks
	
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
	
	
	
	
	
}
