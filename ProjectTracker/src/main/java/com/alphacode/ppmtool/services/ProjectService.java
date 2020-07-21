package com.alphacode.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphacode.ppmtool.domain.Backlog;
import com.alphacode.ppmtool.domain.Project;
import com.alphacode.ppmtool.domain.User;
import com.alphacode.ppmtool.exceptions.ProjectIdException;
import com.alphacode.ppmtool.repositories.BacklogRepository;
import com.alphacode.ppmtool.repositories.ProjectRepository;
import com.alphacode.ppmtool.repositories.UserRepositiry;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private UserRepositiry userRepositiry;

	public Project saveOrUpdateProject(Project project,String username) {

		try {
			
			User user= userRepositiry.findByUsername(username);
			
			project.setUser(user);
			project.setProjectLeader(user.getUsername());

			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			
			if(project.getId()==null) {
				Backlog backlog = new Backlog();
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			}
			
			if(project.getId()!=null) {
				project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
			}
			
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIdException(
					"Project Id '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
		}

	}

	public Project findProjectByIdentifer(String projectId) {

		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

		if (project == null) {
			throw new ProjectIdException("Project Id '" + projectId + "' Dosn't exists");
		}

		return project;
	}

	public Iterable<Project> findAllProjects() {

		return projectRepository.findAll();

	}

	public void deleteProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if (project == null) {
			throw new ProjectIdException(
					"can not delete  Project with Id '" + projectId + "' This prject Dosn't exists");
		}

		projectRepository.delete(project);
	}

}
