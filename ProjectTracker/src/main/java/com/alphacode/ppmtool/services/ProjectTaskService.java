package com.alphacode.ppmtool.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphacode.ppmtool.domain.Backlog;
import com.alphacode.ppmtool.domain.ProjectTask;
import com.alphacode.ppmtool.repositories.BacklogRepository;
import com.alphacode.ppmtool.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	public ProjectTask addProjectTask(String projectIdentifier , ProjectTask projectTask) {
		
		//Exceptions : project not found
		
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

	public Iterable<ProjectTask> findBackLogById(String backlog_id) {

		return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
	}
	
}
