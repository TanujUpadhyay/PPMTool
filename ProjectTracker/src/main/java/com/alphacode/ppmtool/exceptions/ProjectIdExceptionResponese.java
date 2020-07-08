package com.alphacode.ppmtool.exceptions;

public class ProjectIdExceptionResponese {

	private String projectIdentifier;

	public ProjectIdExceptionResponese(String projectIdentifier) {

		this.projectIdentifier = projectIdentifier;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

}
