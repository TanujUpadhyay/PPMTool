package com.alphacode.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphacode.ppmtool.domain.Project;
import com.alphacode.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;

	
	public Project saveOrUpdateProject(Project project) {
		
		//logic
		
		return projectRepository.save(project);
	}

}
