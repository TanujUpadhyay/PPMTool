package com.alphacode.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphacode.ppmtool.domain.Backlog;
import com.alphacode.ppmtool.domain.Project;
import com.alphacode.ppmtool.domain.ProjectTask;
import com.alphacode.ppmtool.exceptions.ProjectNotFoundException;
import com.alphacode.ppmtool.repositories.BacklogRepository;
import com.alphacode.ppmtool.repositories.ProjectRepository;
import com.alphacode.ppmtool.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public ProjectTask addProjectTask(String projectIdentifier , ProjectTask projectTask) {
		
		//Exceptions : project not found
		try {
		
		// PTs to be added to a specific project , project !=null
		Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
		
		// set the backlog to PTs 
		projectTask.setBacklog(backlog);
				
		// we want our project sequence to be like in sequence
		Integer BacklogSequence = backlog.getPTSeqence();
		
		// update the BL SEQUENCE 
		BacklogSequence++;
		
		backlog.setPTSeqence(BacklogSequence);
		
		//Add SEQUENCE to project task
		projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
		projectTask.setProjectIdentifier(projectIdentifier);
		
		//INITIAL priority
		if(projectTask.getPririty()==null) {
			projectTask.setPririty(3);
		}
		
		//INITIAL status when status is null
		if(projectTask.getStatus()=="" || projectTask.getStatus()==null) {
			projectTask.setStatus("TO_DO");
			
		}
		
		return projectTaskRepository.save(projectTask);
		
		}
		catch (Exception e) {
			throw new ProjectNotFoundException("Project not Found");
		}
	}

	public Iterable<ProjectTask> findBackLogById(String backlog_id) {
		
		Project project = projectRepository.findByProjectIdentifier(backlog_id);
		if(project==null)
		{
			throw new ProjectNotFoundException("Project with ID : '"+backlog_id+"' does not exit!! ");
		}
		
		return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
	}
	
	
	public ProjectTask findPTByProjectSequence(String backlog_id , String pt_id)
	{
		//make sure we are searching on the right backlog
		Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
		if(backlog==null) {
			throw new ProjectNotFoundException("Project with ID : '"+backlog_id+"' does not exit!! ");
		}
		
		//make sure that our task exists
		ProjectTask projectTask =  projectTaskRepository.findByProjectSequence(pt_id);
		if(projectTask==null) {
			throw new ProjectNotFoundException("Project Task with ID : '"+pt_id+"' not found ");
		}
		
		//make sure that the backlog/project id in the path corresponds to the right project 
		if(!projectTask.getProjectIdentifier().equals(backlog_id)) {
			throw new ProjectNotFoundException("Project Task with ID : '"+pt_id+"' does not exist in project '"+backlog_id);
		}
		
		return projectTask; 
	}
	
	
	public ProjectTask updateProjectSequence(
			ProjectTask updateTask,
			String backlog_id, 
			String pt_id
			)
	{
		ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
		
		projectTask = updateTask;
		
		return projectTaskRepository.save(projectTask);
	}
	
	
}

















