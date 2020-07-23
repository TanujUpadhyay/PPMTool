package com.alphacode.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphacode.ppmtool.domain.Backlog;
import com.alphacode.ppmtool.domain.Project;
import com.alphacode.ppmtool.domain.User;
import com.alphacode.ppmtool.exceptions.ProjectIdException;
import com.alphacode.ppmtool.exceptions.ProjectNotFoundException;
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

		
		if(project.getId()!=null)
		{
			Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
			
			if(existingProject!=null &&(!existingProject.getProjectLeader().equals(username)))
			{
				throw new ProjectNotFoundException("Project not found in your account");
			}
			
			else if(existingProject == null ) 
			{
				throw new ProjectNotFoundException("Project with ID: '"+project.getProjectIdentifier()+"' cannot be updated becasue it dosen't exits");
			}
		}
		
		
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

	public Project findProjectByIdentifer(String projectId,String username) {

		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

		if (project == null) {
			throw new ProjectIdException("Project Id '" + projectId + "' Dosn't exists");
		}

		if(!project.getProjectLeader().equals(username))
		{
			throw new ProjectNotFoundException("Project not found in your account");
		}
		
		
		return project;
	}

	public Iterable<Project> findAllProjects(String username) {

		return projectRepository.findByProjectLeader(username);

	}

	public void deleteProjectByIdentifier(String projectId,String username) {

		projectRepository.delete(findProjectByIdentifer(projectId,username));
	}

}
