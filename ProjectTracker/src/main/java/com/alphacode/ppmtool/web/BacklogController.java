package com.alphacode.ppmtool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alphacode.ppmtool.domain.ProjectTask;
import com.alphacode.ppmtool.services.MapValidationErrorService;
import com.alphacode.ppmtool.services.ProjectTaskService;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {
	
	@Autowired
	private ProjectTaskService projectTaskService;
	
	@Autowired
	private MapValidationErrorService mapValidation;
	
	@PostMapping("/{backlog_id}")
	public ResponseEntity<?> addProjectTastToBacklog(
			@Valid @RequestBody ProjectTask projectTask,
			BindingResult result ,
			@PathVariable String backlog_id
			)
	{
		ResponseEntity<?> erroMap = mapValidation.MapValidationService(result);
		
		if(erroMap!=null) {
			return erroMap;
		}
		
		ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id,projectTask);
		
		
		return new ResponseEntity<ProjectTask>(projectTask1 , HttpStatus.CREATED);
	}
	

}
